package DAO;

import database.DBContext;
import Model.Finance;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class financeDAO {

    // Phương thức để lấy club_id từ user_id
    public int getClubIdByUserId(int userId) throws SQLException {
        int clubId = -1;
        String sql = "SELECT ClubID FROM UserClubs WHERE UserID = ? and status ='Active'";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                clubId = rs.getInt("ClubID");
            }
        }
        return clubId;
    }

    public String getUserTypeByUserId(int userId) throws SQLException {
        String userType = null;
        String sql = "SELECT ur.role AS UserType "
                + "FROM UserClubs uc "
                + "JOIN user_role ur ON uc.RoleID = ur.RoleID "
                + "WHERE uc.UserID = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId); // Set the UserID parameter
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userType = rs.getString("UserType"); // Get the role from the user_role table
                }
            }
        }
        return userType; // Return the retrieved UserType
    }

    public int getUserIdByUsername(String username) throws SQLException {
        int userId = -1;
        String sql = "SELECT UserID FROM UserClubs WHERE UserName = ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("UserID");
            }
        }
        return userId;
    }

    public List<Finance> selectFinancesByClubWithPagination(int clubId, int start, int total) throws SQLException {
        List<Finance> finances = new ArrayList<>();
        String sql = "SELECT f.*, c.ClubName, u.Fullname, "
                + "CASE WHEN f.is_expense = 'Yes' THEN 'Expense' ELSE 'Unexpense' END AS formatted_expense, "
                + "f.updated_date "
                + "FROM finance f "
                + "JOIN Clubs c ON f.club_id = c.ClubID "
                + "JOIN Users u ON f.member_id = u.UserID "
                + "WHERE f.club_id = ? "
                + "ORDER BY f.finance_id ASC "
                + "LIMIT ?, ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clubId);
            stmt.setInt(2, start);
            stmt.setInt(3, total);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Finance finance = new Finance(
                        rs.getInt("finance_id"),
                        rs.getInt("club_id"),
                        rs.getInt("member_id"),
                        rs.getString("finance_name"),
                        rs.getString("formatted_expense"), // formatted expense
                        rs.getBigDecimal("amount"),
                        rs.getTimestamp("finance_date").toLocalDateTime(),
                        rs.getString("details"),
                        rs.getString("ClubName"),
                        rs.getString("Fullname"),
                        rs.getString("status"), // added status field
                        rs.getTimestamp("updated_date").toLocalDateTime() // added updatedDate field
                );
                finances.add(finance);
            }
        }
        return finances;
    }
    // Phương thức lấy danh sách Finance theo clubId với phân trang

    // Phương thức để đếm tổng số bản ghi Finance theo clubId (cho phân trang)
    public int getTotalFinanceCountByClub(int clubId) throws SQLException {
        int totalRecords = 0;
        String sql = "SELECT COUNT(*) FROM finance WHERE club_id = ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clubId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalRecords = rs.getInt(1);
            }
        }
        return totalRecords;
    }

    // Phương thức thêm Finance (giữ nguyên)
    public void insertFinance(Finance finance) throws SQLException {
        String sql = "INSERT INTO finance (club_id, member_id, finance_name, is_expense, amount, finance_date, details, imgurl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, finance.getClubId());
            stmt.setInt(2, finance.getMemberId());
            stmt.setString(3, finance.getFinanceName());
            stmt.setString(4, finance.getIsExpense());
            stmt.setBigDecimal(5, finance.getAmount());
            stmt.setTimestamp(6, Timestamp.valueOf(finance.getFinanceDate()));
            stmt.setString(7, finance.getDetails());
            stmt.setString(8, finance.getImgurl()); // URL hình ảnh
            stmt.executeUpdate();
        }
    }

    public List<Finance> getFinancesByUserId(int userId) throws SQLException {
        List<Finance> finances = new ArrayList<>();
        String sql = "SELECT f.*, c.ClubName, u.Fullname, "
                + "CASE WHEN f.is_expense = 'Yes' THEN 'Expense' ELSE 'Unexpense' END AS formatted_expense, "
                + "f.updated_date "
                + "FROM finance f "
                + "JOIN Clubs c ON f.club_id = c.ClubID "
                + "JOIN Users u ON f.member_id = u.UserID "
                + "JOIN UserClubs uc ON f.club_id = uc.ClubID "
                + "WHERE uc.UserID = ? "
                + "ORDER BY f.finance_id ASC";

        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId); // Truyền UserID vào câu truy vấn
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Finance finance = new Finance(
                        rs.getInt("finance_id"),
                        rs.getInt("club_id"),
                        rs.getInt("member_id"),
                        rs.getString("finance_name"),
                        rs.getString("formatted_expense"),
                        rs.getBigDecimal("amount"),
                        rs.getTimestamp("finance_date").toLocalDateTime(),
                        rs.getString("details"),
                        rs.getString("ClubName"),
                        rs.getString("Fullname"),
                        rs.getString("status"),
                        rs.getTimestamp("updated_date").toLocalDateTime()
                );
                finances.add(finance);
            }
        }
        return finances;
    }

    // (Cập nhật) Phương thức đếm tổng số bản ghi Finance (có thể không cần nếu đã dùng phương thức theo clubId)
    public int getTotalFinanceCount() throws SQLException {
        int totalRecords = 0;
        String sql = "SELECT COUNT(*) FROM finance";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalRecords = rs.getInt(1);
            }
        }
        return totalRecords;
    }

    // (Tùy chọn) Phương thức lấy danh sách Finance với phân trang (không lọc theo club)
    // Bạn có thể giữ lại hoặc xóa đi nếu không cần thiết
    public List<Finance> selectFinancesWithPagination(int start, int total) throws SQLException {
        List<Finance> finances = new ArrayList<>();
        String sql = "SELECT f.*, c.ClubName, u.Fullname, "
                + "CASE WHEN f.is_expense = 'Yes' THEN 'Expense' ELSE 'Unexpense' END AS formatted_expense, "
                + "f.last_updated "
                + "FROM finance f "
                + "JOIN Clubs c ON f.club_id = c.ClubID "
                + "JOIN Users u ON f.member_id = u.UserID "
                + "ORDER BY f.finance_id ASC "
                + "LIMIT ?, ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, start);
            stmt.setInt(2, total);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Finance finance = new Finance(
                        rs.getInt("finance_id"),
                        rs.getInt("club_id"),
                        rs.getInt("member_id"),
                        rs.getString("finance_name"),
                        rs.getString("formatted_expense"), // formatted expense
                        rs.getBigDecimal("amount"),
                        rs.getTimestamp("finance_date").toLocalDateTime(),
                        rs.getString("details"),
                        rs.getString("ClubName"),
                        rs.getString("Fullname"),
                        rs.getString("status"), // added status field
                        rs.getTimestamp("updated_date").toLocalDateTime() // added lastUpdated field
                );
                finances.add(finance);
            }
        }
        return finances;
    }

    public List<Finance> selectFinancesByUserWithPagination(int userId, int start, int total) throws SQLException {
        List<Finance> finances = new ArrayList<>();
        String sql = "SELECT f.*, c.ClubName, u.Fullname, "
                + "CASE WHEN f.is_expense = 'Yes' THEN 'Expense' ELSE 'Unexpense' END AS formatted_expense, "
                + "f.updated_date "
                + "FROM finance f "
                + "JOIN Clubs c ON f.club_id = c.ClubID "
                + "JOIN Users u ON f.member_id = u.UserID "
                + "JOIN UserClubs uc ON f.club_id = uc.ClubID "
                + "WHERE uc.UserID = ? "
                + "ORDER BY f.finance_id ASC "
                + "LIMIT ?, ?";

        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, start);
            stmt.setInt(3, total);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Finance finance = new Finance(
                        rs.getInt("finance_id"),
                        rs.getInt("club_id"),
                        rs.getInt("member_id"),
                        rs.getString("finance_name"),
                        rs.getString("formatted_expense"),
                        rs.getBigDecimal("amount"),
                        rs.getTimestamp("finance_date").toLocalDateTime(),
                        rs.getString("details"),
                        rs.getString("ClubName"),
                        rs.getString("Fullname"),
                        rs.getString("status"),
                        rs.getTimestamp("updated_date").toLocalDateTime()
                );
                finances.add(finance);
            }
        }
        return finances;
    }

    public int getTotalFinanceCountByUser(int userId) throws SQLException {
        int totalRecords = 0;
        String sql = "SELECT COUNT(*) FROM finance f "
                + "JOIN UserClubs uc ON f.club_id = uc.ClubID "
                + "WHERE uc.UserID = ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalRecords = rs.getInt(1);
            }
        }
        return totalRecords;
    }

    public Finance selectFinanceById(int financeId) throws SQLException {
        Finance finance = null;
        String sql = "SELECT f.*, c.ClubName, u.Fullname, "
                + "CASE WHEN f.is_expense = 'Yes' THEN 'Expense' ELSE 'Unexpense' END AS formatted_expense "
                + "FROM finance f "
                + "JOIN Clubs c ON f.club_id = c.ClubID "
                + "JOIN Users u ON f.member_id = u.UserID "
                + "WHERE f.finance_id = ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, financeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                finance = new Finance(
                        rs.getInt("finance_id"),
                        rs.getInt("club_id"),
                        rs.getInt("member_id"),
                        rs.getString("finance_name"),
                        rs.getString("formatted_expense"), // formatted expense
                        rs.getBigDecimal("amount"),
                        rs.getTimestamp("finance_date").toLocalDateTime(),
                        rs.getString("details"),
                        rs.getString("ClubName"), // Club name added
                        rs.getString("Fullname"), // Fullname added
                        rs.getString("status"), // Status field
                        rs.getTimestamp("updated_date").toLocalDateTime() // Last updated
                );
                // Thêm đường dẫn ảnh vào đối tượng Finance
                finance.setImgurl(rs.getString("imgurl")); // Proof image URL
            }
        }
        return finance;
    }

    // New method for updating finance record
    public boolean updateFinance(Finance finance) throws SQLException {
        String sql = "UPDATE finance SET finance_name = ?, is_expense = ?, amount = ?, member_id = ?, finance_date = ?, details = ?, status = ?, imgurl = ? WHERE finance_id = ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, finance.getFinanceName());
            stmt.setString(2, finance.isExpense() ? "Yes" : "No");
            stmt.setBigDecimal(3, finance.getAmount());
            stmt.setInt(4, finance.getMemberId());
            stmt.setTimestamp(5, Timestamp.valueOf(finance.getFinanceDate()));
            stmt.setString(6, finance.getDetails());
            stmt.setString(7, finance.getStatus());
            stmt.setString(8, finance.getImgurl()); // Đường dẫn ảnh bằng chứng
            stmt.setInt(9, finance.getFinanceId());

            return stmt.executeUpdate() > 0;
        }
    }

    public void updateProofImage(int financeId, String newImgurl) throws SQLException {
        String sql = "UPDATE finance SET imgurl = ? WHERE finance_id = ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newImgurl);
            stmt.setInt(2, financeId);
            int rowsUpdated = stmt.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

            if (rowsUpdated == 0) {
                System.out.println("No rows were updated. Check if Finance ID exists.");
            }
        }
    }

    public boolean isUserIdExists(int userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE UserID = ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // New method for deleting finance record
    public boolean deleteFinance(int financeId) throws SQLException {
        String sql = "DELETE FROM finance WHERE finance_id = ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, financeId);
            return stmt.executeUpdate() > 0;
        }
    }

    //filter
    public List<Finance> getFilteredFinances(String searchQuery, String expenseFilter, String statusFilter, int userId) throws SQLException {
        List<Finance> finances = new ArrayList<>();
        String sql = "SELECT f.finance_id, f.club_id, f.member_id, f.finance_name, "
                + "       CASE WHEN f.is_expense = 'Yes' THEN 'Expense' "
                + "            WHEN f.is_expense = 'No' THEN 'Unexpense' "
                + "            ELSE 'Unknown' END AS formatted_expense, "
                + "       f.amount, f.finance_date, f.details, f.status, f.created_date, f.updated_date, "
                + "       c.ClubName, u.Fullname, uc.RoleID AS user_role "
                + "FROM finance f "
                + "JOIN Clubs c ON f.club_id = c.ClubID "
                + "JOIN Users u ON f.member_id = u.UserID "
                + "JOIN UserClubs uc ON uc.ClubID = f.club_id "
                + // Lọc dựa vào UserClubs
                "WHERE uc.UserID = ? "
                + // Lọc chỉ lấy các club mà user tham gia
                "  AND (f.finance_name LIKE ? OR f.details LIKE ?) ";

        // Thêm điều kiện cho expenseFilter
        if (expenseFilter != null && !"all".equalsIgnoreCase(expenseFilter)) {
            sql += " AND f.is_expense = ? ";
        }

        // Lọc theo statusFilter
        sql += " AND (? = 'all' OR f.status = ?) "
                + " ORDER BY f.finance_date DESC";

        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            // Thiết lập các tham số
            int paramIndex = 1;
            stmt.setInt(paramIndex++, userId); // Lọc theo UserID
            String searchPattern = "%" + (searchQuery != null ? searchQuery : "") + "%";
            stmt.setString(paramIndex++, searchPattern); // Tìm kiếm trong finance_name
            stmt.setString(paramIndex++, searchPattern); // Tìm kiếm trong details

            if (expenseFilter != null && !"all".equalsIgnoreCase(expenseFilter)) {
                stmt.setString(paramIndex++, expenseFilter); // Lọc theo loại chi tiêu
            }
            stmt.setString(paramIndex++, statusFilter != null ? statusFilter : "all"); // Lọc theo trạng thái
            stmt.setString(paramIndex++, statusFilter != null ? statusFilter : "all"); // Lặp lại lọc trạng thái

            // Thực thi truy vấn và ánh xạ dữ liệu
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Finance finance = new Finance(
                            rs.getInt("finance_id"),
                            rs.getInt("club_id"),
                            rs.getInt("member_id"),
                            rs.getString("finance_name"),
                            rs.getString("formatted_expense"),
                            rs.getBigDecimal("amount"),
                            rs.getTimestamp("finance_date") != null ? rs.getTimestamp("finance_date").toLocalDateTime() : null,
                            rs.getString("details") != null ? rs.getString("details") : "",
                            rs.getString("ClubName") != null ? rs.getString("ClubName") : "Unknown Club",
                            rs.getString("Fullname") != null ? rs.getString("Fullname") : "Unknown User",
                            rs.getString("status"),
                            rs.getTimestamp("updated_date") != null ? rs.getTimestamp("updated_date").toLocalDateTime() : null
                    );
                    finances.add(finance);
                }
            }
        }
        return finances;
    }

    public List<Finance> getFilteredFinancesWithPagination(int userId, String searchQuery, String expenseFilter, String statusFilter, int start, int recordsPerPage) throws SQLException {
        List<Finance> finances = new ArrayList<>();
        String sql = "SELECT f.finance_id, f.club_id, f.member_id, f.finance_name, "
                + "       CASE WHEN f.is_expense = 'Yes' THEN 'Expense' "
                + "            WHEN f.is_expense = 'No' THEN 'Unexpense' "
                + "            ELSE 'Unknown' END AS formatted_expense, "
                + "       f.amount, f.finance_date, f.details, f.status, f.created_date, f.updated_date, "
                + "       c.ClubName, u.Fullname "
                + "FROM finance f "
                + "JOIN Clubs c ON f.club_id = c.ClubID "
                + "JOIN UserClubs uc ON uc.ClubID = f.club_id "
                + "JOIN Users u ON f.member_id = u.UserID "
                + "WHERE uc.UserID = ? "
                + "  AND f.member_id = ? "
                + // Match finance records specifically belonging to this user
                "  AND (f.finance_name LIKE ? OR f.details LIKE ?) "
                + (expenseFilter != null && !"all".equalsIgnoreCase(expenseFilter) ? " AND f.is_expense = ? " : "")
                + (statusFilter != null && !"all".equalsIgnoreCase(statusFilter) ? " AND f.status = ? " : "")
                + " ORDER BY f.finance_date DESC LIMIT ?, ?";

        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            int index = 1;
            stmt.setInt(index++, userId); // UserID for UserClubs
            stmt.setInt(index++, userId); // UserID for finance's member_id
            String searchPattern = "%" + searchQuery + "%";
            stmt.setString(index++, searchPattern); // Matches finance_name
            stmt.setString(index++, searchPattern); // Matches details

            if (expenseFilter != null && !"all".equalsIgnoreCase(expenseFilter)) {
                stmt.setString(index++, expenseFilter); // Expense filter
            }
            if (statusFilter != null && !"all".equalsIgnoreCase(statusFilter)) {
                stmt.setString(index++, statusFilter); // Status filter
            }
            stmt.setInt(index++, start);              // Pagination start
            stmt.setInt(index++, recordsPerPage);     // Pagination limit

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Finance finance = new Finance(
                            rs.getInt("finance_id"),
                            rs.getInt("club_id"),
                            rs.getInt("member_id"),
                            rs.getString("finance_name"),
                            rs.getString("formatted_expense"),
                            rs.getBigDecimal("amount"),
                            rs.getTimestamp("finance_date") != null ? rs.getTimestamp("finance_date").toLocalDateTime() : null,
                            rs.getString("details") != null ? rs.getString("details") : "",
                            rs.getString("ClubName") != null ? rs.getString("ClubName") : "Unknown Club",
                            rs.getString("Fullname") != null ? rs.getString("Fullname") : "Unknown User",
                            rs.getString("status"),
                            rs.getTimestamp("updated_date") != null ? rs.getTimestamp("updated_date").toLocalDateTime() : null
                    );
                    finances.add(finance);
                }
            }
        }
        return finances;
    }

    public int getTotalFilteredFinanceCount(int userId, String searchQuery, String expenseFilter, String statusFilter) throws SQLException {
        String sql = "SELECT COUNT(*) AS total "
                + "FROM finance f "
                + "JOIN Clubs c ON f.club_id = c.ClubID "
                + "JOIN UserClubs uc ON uc.ClubID = f.club_id "
                + "WHERE uc.UserID = ? "
                + "  AND (f.finance_name LIKE ? OR f.details LIKE ?) "
                + (expenseFilter != null && !"all".equalsIgnoreCase(expenseFilter) ? " AND f.is_expense = ? " : "")
                + (statusFilter != null && !"all".equalsIgnoreCase(statusFilter) ? " AND f.status = ? " : "");

        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            int index = 1;
            stmt.setInt(index++, userId);
            String searchPattern = "%" + searchQuery + "%";
            stmt.setString(index++, searchPattern); // Matches finance_name
            stmt.setString(index++, searchPattern); // Matches details

            if (expenseFilter != null && !"all".equalsIgnoreCase(expenseFilter)) {
                stmt.setString(index++, expenseFilter); // Expense filter
            }
            if (statusFilter != null && !"all".equalsIgnoreCase(statusFilter)) {
                stmt.setString(index++, statusFilter); // Status filter
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        }
        return 0;
    }

}
