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
        String sql = "SELECT ClubID FROM Users WHERE UserID = ?";
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
        String sql = "SELECT UserType FROM Users WHERE UserID = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userType = rs.getString("UserType");
                }
            }
        }
        return userType;
    }

    public int getUserIdByUsername(String username) throws SQLException {
        int userId = -1;
        String sql = "SELECT UserID FROM Users WHERE UserName = ?";
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
        String sql = "INSERT INTO finance (club_id, member_id, finance_name, is_expense, amount, finance_date, details) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, finance.getClubId());
            stmt.setInt(2, finance.getMemberId());
            stmt.setString(3, finance.getFinanceName());
            stmt.setString(4, finance.getIsExpense());
            stmt.setBigDecimal(5, finance.getAmount());
            stmt.setTimestamp(6, Timestamp.valueOf(finance.getFinanceDate()));
            stmt.setString(7, finance.getDetails());
            stmt.executeUpdate();
        }
    }

    // (Tùy chọn) Phương thức lấy danh sách Finance theo clubId (không phân trang)
    public List<Finance> getFinancesByClubId(int clubId) throws SQLException {
        List<Finance> finances = new ArrayList<>();
        String sql = "SELECT f.*, c.ClubName, u.Fullname, "
                + "CASE WHEN f.is_expense = 'Yes' THEN 'Expense' ELSE 'Unexpense' END AS formatted_expense, "
                + "f.last_updated "
                + "FROM finance f "
                + "JOIN Clubs c ON f.club_id = c.ClubID "
                + "JOIN Users u ON f.member_id = u.UserID "
                + "WHERE f.club_id = ? "
                + "ORDER BY f.finance_id ASC";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clubId);
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
                        rs.getString("ClubName"),
                        rs.getString("Fullname"),
                        rs.getString("status"), // added status field
                        rs.getTimestamp("updated_date").toLocalDateTime() // added lastUpdated field
                );
            }
        }
        return finance;
    }

    // New method for updating finance record
    public boolean updateFinance(Finance finance) throws SQLException {
        String sql = "UPDATE finance SET finance_name = ?, is_expense = ?, amount = ?, member_id = ?, finance_date = ?, details = ?, status = ? WHERE finance_id = ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, finance.getFinanceName());
            stmt.setString(2, finance.isExpense() ? "Yes" : "No");
            stmt.setBigDecimal(3, finance.getAmount());
            stmt.setInt(4, finance.getMemberId());
            stmt.setTimestamp(5, Timestamp.valueOf(finance.getFinanceDate()));
            stmt.setString(6, finance.getDetails());
            stmt.setString(7, finance.getStatus());
            stmt.setInt(8, finance.getFinanceId());

            return stmt.executeUpdate() > 0;
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

}
