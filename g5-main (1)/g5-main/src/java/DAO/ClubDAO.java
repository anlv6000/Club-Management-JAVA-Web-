package DAO;

import database.DBContext;
import Model.Club;
import Model.User;
import java.util.List;
import java.sql.*;
import java.util.*;
import DAO.UserDao;

/**
 *
 * @author admin
 */
public class ClubDAO extends DBContext {
    public static void main(String[] args) {
        ClubDAO dao = new ClubDAO(); // Thay YourDAOClass bằng tên class chứa method getJoinedClubs
        List<User> users = dao.getUsersJoinedClubOfUser(2); // Test với userID = 1

        if (users.isEmpty()) {
            System.out.println("User không tham gia CLB nào.");
        } else {
            System.out.println("Danh sách CLB đã tham gia:");
            for (User user : users) {
                System.out.println(user.getFullname()); // Phụ thuộc vào phương thức toString của class Club
            }
        }
}
    public List<User> getUser() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT UserID, Username, Fullname FROM users WHERE Status = 'Active' AND ClubID IS NULl;";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int userId = rs.getInt("UserID");
                String username = rs.getString("Username");
                String fullname = rs.getString("Fullname");

                User user = new User(userId, username, fullname);
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    public List<User> getUsersJoinedClubOfUser(int userID) {
        List<User> userList = new ArrayList<>();
        String query = "SELECT DISTINCT u.userID, u.fullname, u.username FROM users u"
                + " JOIN userclubs uc ON u.userID = uc.userID "
                + "WHERE uc.clubID IN ("
                + "SELECT clubID FROM userclubs WHERE userID = ? ) AND u.userID <> ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query) ) {
            ps.setInt(1, userID);
            ps.setInt(2, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("UserID");
                String username = rs.getString("Username");
                String fullname = rs.getString("Fullname");

                User user = new User(userId, username, fullname);
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    
    public List<User> getUserUpdate(int clubID) {
    List<User> userList = new ArrayList<>();
    String query = "SELECT UserID, Username, Fullname FROM users " +
                   "WHERE Status = 'Active' AND " +
                   "(UserID IN (SELECT UserID FROM userClubs WHERE ClubID = ?) OR ClubID IS NULL);";

    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, clubID); // Truyền tham số clubID vào dấu ?

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int userId = rs.getInt("UserID");
                String username = rs.getString("Username");
                String fullname = rs.getString("Fullname");

                User user = new User(userId, username, fullname);
                userList.add(user);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return userList;
}


    public static List<Club> selectClubsByUser(int userId) throws SQLException {
        String sql = "SELECT c.ClubID, c.ClubName "
                + "FROM UserClubs uc "
                + "JOIN Clubs c ON uc.ClubID = c.ClubID "
                + "WHERE uc.UserID = ?";

        List<Club> clubs = new ArrayList<>();
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Club club = new Club();
                club.setClubID(rs.getInt("ClubID"));
                club.setClubName(rs.getString("ClubName"));
                clubs.add(club);
            }
        }
        return clubs;
    }

    public List<Club> getClubs() {
        List<Club> clubs = new ArrayList<>();

        String sql = "SELECT * FROM clubs";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Club club = new Club();
                club.setClubID(rs.getInt("ClubID"));
                club.setClubName(rs.getString("ClubName"));
                club.setCategory(rs.getString("Categories"));
                club.setDescription(rs.getString("Description"));
                club.setIsPublic(rs.getString("IsPublic"));
                club.setImgURL(rs.getString("ImageURL"));
                club.setStatus(rs.getString("status"));
                club.setClubLeader(rs.getString("Clubleader"));
                club.setcontactClub(rs.getString("ContactClub"));
                club.setSchedule(rs.getString("Schedule"));
                clubs.add(club);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }

    //search club by name
    public List<Club> searchClubs(String keyword) {
        List<Club> clubs = new ArrayList<>();

        String sql = "SELECT * FROM clubs WHERE ClubName LIKE ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Club club = new Club();
                club.setClubID(rs.getInt("ClubID"));
                club.setClubName(rs.getString("ClubName"));
                club.setCategory(rs.getString("Categories"));
                club.setDescription(rs.getString("Description"));
                club.setIsPublic(rs.getString("IsPublic"));
                club.setImgURL(rs.getString("ImageURL"));
                club.setStatus(rs.getString("status"));
                club.setClubLeader(rs.getString("Clubleader"));
                club.setcontactClub(rs.getString("ContactClub"));
                club.setSchedule(rs.getString("Schedule"));
                clubs.add(club);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi truy vấn CSDL", e);
        }
        return clubs;
    }

    public void addClub(String name, String category, String description, String isPublic, String image, String status, int leaderID, String contactClub, String schedule) {

        String getUserFullNameSQL = "SELECT Fullname FROM Users WHERE UserID = ?";

        String insertClubSQL = "INSERT INTO Clubs (ClubName, Categories, Description, IsPublic, ImageURL, status, UserID, Clubleader, ContactClub, Schedule) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String updateClubIDSQL = "UPDATE Users SET ClubID = ?, RoleID = 4 WHERE UserID = ?";
        String insertUserClubsSQL = "INSERT INTO UserClubs (UserID, ClubID, RoleID, Status) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement getUserFullNameStmt = conn.prepareStatement(getUserFullNameSQL); PreparedStatement insertClubStmt = conn.prepareStatement(insertClubSQL, Statement.RETURN_GENERATED_KEYS); PreparedStatement updateClubIDStmt = conn.prepareStatement(updateClubIDSQL); PreparedStatement insertUserClubsStmt = conn.prepareStatement(insertUserClubsSQL)) {

            //Get UserID with same Same inputed username
            getUserFullNameStmt.setInt(1, leaderID);
            ResultSet rs = getUserFullNameStmt.executeQuery();

            if (rs.next()) {
                String userFullName = rs.getString("Fullname"); // Set UserFullname from database

                // Insert data into Clubs table
                insertClubStmt.setString(1, name);
                insertClubStmt.setString(2, category);
                insertClubStmt.setString(3, description);
                insertClubStmt.setString(4, isPublic);
                insertClubStmt.setString(5, image);
                insertClubStmt.setString(6, status);
                insertClubStmt.setInt(7, leaderID); // Save UserID to Clubs table
                insertClubStmt.setString(8, userFullName); // Save fullname as Clubleader
                insertClubStmt.setString(9, contactClub);
                insertClubStmt.setString(10, schedule);

                insertClubStmt.executeUpdate();

                // Fetch the generated ClubID
                ResultSet generatedKeys = insertClubStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int createdClubID = generatedKeys.getInt(1);

                    // Update ClubID and RoleID in Users table
                    updateClubIDStmt.setInt(1, createdClubID);
                    updateClubIDStmt.setInt(2, leaderID);
                    updateClubIDStmt.executeUpdate();

                    // Insert data into UserClubs table with Status set to 'Active'
                    insertUserClubsStmt.setInt(1, leaderID);
                    insertUserClubsStmt.setInt(2, createdClubID);
                    insertUserClubsStmt.setInt(3, 4); // Assuming RoleID is 4
                    insertUserClubsStmt.setString(4, "Active"); // Set Status to 'Active'
                    insertUserClubsStmt.executeUpdate();
                }

                System.out.println("Thêm câu lạc bộ và cập nhật ClubID, RoleID, và UserClubs với trạng thái Active thành công");
            } else {
                System.out.println("Không tìm thấy UserID: " + leaderID);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm câu lạc bộ: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT Categories FROM clubs";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                categories.add(rs.getString("Categories"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<String> getPublicStatus() {
        List<String> publicStatus = new ArrayList<>();
        String sql = "SELECT DISTINCT IsPublic FROM clubs";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                publicStatus.add(rs.getString("isPublic"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publicStatus;
    }

    //get club by category
    public List<Club> getClubsByCategory(String category) {
        List<Club> clubs = new ArrayList<>();
        String sql = "SELECT * FROM clubs WHERE Categories = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, category);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Club club = new Club();
                club.setClubID(rs.getInt("ClubID"));
                club.setClubName(rs.getString("ClubName"));
                club.setCategory(rs.getString("Categories"));
                club.setDescription(rs.getString("Description"));
                club.setIsPublic(rs.getString("IsPublic"));
                club.setImgURL(rs.getString("ImageURL"));
                club.setStatus(rs.getString("status"));
                club.setClubLeader(rs.getString("Clubleader"));
                club.setcontactClub(rs.getString("ContactClub"));
                club.setSchedule(rs.getString("Schedule"));
                clubs.add(club);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }

    public List<Club> getClubsByStatus(String status) {
        List<Club> clubs = new ArrayList<>();
        String sql = "SELECT * FROM clubs WHERE status = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, status);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Club club = new Club();
                club.setClubID(rs.getInt("ClubID"));
                club.setClubName(rs.getString("ClubName"));
                club.setCategory(rs.getString("Categories"));
                club.setDescription(rs.getString("Description"));
                club.setIsPublic(rs.getString("IsPublic"));
                club.setImgURL(rs.getString("ImageURL"));
                club.setStatus(rs.getString("status"));
                club.setClubLeader(rs.getString("Clubleader"));
                club.setcontactClub(rs.getString("ContactClub"));
                club.setSchedule(rs.getString("Schedule"));
                clubs.add(club);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }

    // Update club information
    public void updateClub(int clubID, String name, String category, String description, String isPublic, String image, int leaderID, String contactClub, String schedule) {
        String getOldUSerIDSQL = "SELECT UserID FROM clubs WHERE ClubID = ?";
        String getFullNameSQL = "SELECT Fullname FROM users WHERE UserID = ?";
        String lowRoleLeaerSql = "UPDATE Users SET ClubID = ?, RoleID = 1 WHERE UserID = ?";
        String updateClubIDSQL = "UPDATE Users SET ClubID = ?, RoleID = 4 WHERE UserID = ?";
        
        String updateClubSQL = "UPDATE clubs SET ClubName = ?, Categories = ?, Description = ?, IsPublic = ?, ImageURL = ?, UserID = ?, Clubleader = ?, ContactClub = ?, Schedule = ? WHERE ClubID = ?";

        
        String insertUserClubsSQL = "INSERT INTO UserClubs (UserID, ClubID, RoleID, Status) VALUES (?, ?, ?, ?)";
        String updateUserClubsSQL = "UPDATE UserClubs SET RoleID = ? WHERE UserID = ? AND ClubID = ?";
        try (Connection conn = getConnection(); 
                PreparedStatement getOldUserID = conn.prepareStatement(getOldUSerIDSQL);
                PreparedStatement getFullNameStmt = conn.prepareStatement(getFullNameSQL); 
                PreparedStatement updateClubStmt = conn.prepareStatement(updateClubSQL);
                PreparedStatement updateClubIDStmt = conn.prepareStatement(updateClubIDSQL); 
                PreparedStatement lowLeaderStmt = conn.prepareStatement(lowRoleLeaerSql); 
                PreparedStatement insertUserClubsStmt = conn.prepareStatement(insertUserClubsSQL);
                 PreparedStatement updateUserClubsStmt = conn.prepareStatement(updateUserClubsSQL)) {
            // 🔹 Lấy UserID từ bảng users
            getOldUserID.setInt(1, clubID);
            ResultSet rs = getOldUserID.executeQuery();
            int oldUserId;

            if (rs.next()) {
                oldUserId = rs.getInt("UserID"); // Lấy UserID từ bảng clubs
            } else {
                System.out.println("Không tìm thấy UserID: " + leaderID);
                return; // Thoát khỏi phương thức nếu UserID không tồn tại
            }
            // 🔹 Lấy Fullname từ bảng users
            getFullNameStmt.setInt(1, leaderID);
            rs = getFullNameStmt.executeQuery();
            String fullName = null;

            if (rs.next()) {
                fullName = rs.getString("Fullname"); // Lấy tên leader từ bảng users
            } else {
                System.out.println("Không tìm thấy UserID: " + leaderID);
                return; // Thoát khỏi phương thức nếu UserID không tồn tại
            }

            // Update ClubID and RoleID in Users table for old Club leader
            lowLeaderStmt.setInt(1, clubID);
            lowLeaderStmt.setInt(2, oldUserId);
            lowLeaderStmt.executeUpdate();
            
            // Update ClubID and RoleID in Users table
            updateClubIDStmt.setInt(1, clubID);
            updateClubIDStmt.setInt(2, leaderID);
            updateClubIDStmt.executeUpdate();

            // 🔹 Cập nhật dữ liệu vào bảng clubs
            updateClubStmt.setString(1, name);
            updateClubStmt.setString(2, category);
            updateClubStmt.setString(3, description);
            updateClubStmt.setString(4, isPublic);
            updateClubStmt.setString(5, image);
            updateClubStmt.setInt(6, leaderID);  // Lưu UserID của leader
            updateClubStmt.setString(7, fullName); // Lưu Fullname vào Clubleader
            updateClubStmt.setString(8, contactClub);
            updateClubStmt.setString(9, schedule);
            updateClubStmt.setInt(10, clubID); // Điều kiện WHERE ClubID = ?

            updateClubStmt.executeUpdate();
            System.out.println("Cập nhật câu lạc bộ thành công");
            
             // Insert data into UserClubs table with Status set to 'Active'
                    insertUserClubsStmt.setInt(1, leaderID);
                    insertUserClubsStmt.setInt(2, clubID);
                    insertUserClubsStmt.setInt(3, 4); // Assuming RoleID is 4
                    insertUserClubsStmt.setString(4, "Active"); // Set Status to 'Active'
                    insertUserClubsStmt.executeUpdate();
                    
                    
                    // update data in UserClubs
                    updateUserClubsStmt.setInt(1, 2);
                    updateUserClubsStmt.setInt(2, oldUserId);
                    updateUserClubsStmt.setInt(3, clubID); 
                    
                    updateUserClubsStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Club getClubByID(int clubID) {
        Club club = null;
        String fullName = "SELECT Fullname FROM users WHERE UserID = ?";
        String sql = "SELECT * FROM clubs WHERE ClubID = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, clubID);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                club = new Club();
                club.setClubID(rs.getInt("ClubID"));
                club.setClubName(rs.getString("ClubName"));
                club.setCategory(rs.getString("Categories"));
                club.setDescription(rs.getString("Description"));
                club.setIsPublic(rs.getString("IsPublic"));
                club.setImgURL(rs.getString("ImageURL"));
                club.setStatus(rs.getString("status"));
                club.setLeaderID(rs.getString("UserID"));
                club.setClubLeader(rs.getString("Clubleader"));
                club.setcontactClub(rs.getString("ContactClub"));
                club.setSchedule(rs.getString("Schedule"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return club;
    }

    public List<Club> getAllClubs() {

        List<Club> list = new ArrayList<>();
        String sql = "SELECT * FROM clubs ";
        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Club(rs.getInt("ClubID"), rs.getString("ClubName"),
                        rs.getString("Categories"), rs.getString("Description"),
                        rs.getString("Clubleader"),
                        rs.getString("ImageURL"),
                        rs.getString("ContactClub"),
                        rs.getString("Schedule")
                ));

            }
        } catch (Exception e) {
        }
        return list;
    }
    public List<Club> getJoinedClubs(int userID) {

        List<Club> list = new ArrayList<>();
        String sql = "SELECT * FROM clubs "
                + " WHERE ClubID IN "
                + "(SELECT ClubID FROM userclubs where userID = ?)";
        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, userID);
            ResultSet rs = stm.executeQuery();
            
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Club(rs.getInt("ClubID"), rs.getString("ClubName"),
                        rs.getString("Categories"), rs.getString("Description"),
                        rs.getString("Clubleader"),
                        rs.getString("ImageURL"),
                        rs.getString("ContactClub"),
                        rs.getString("Schedule")
                ));

            }
        } catch (Exception e) {
        }
        return list;
    }

    // Deactive a club by ID
    public void deactiveClub(int clubID) {
        String sql = "UPDATE clubs SET Status = 'deactive' WHERE ClubID = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, clubID);
            stm.executeUpdate();
            System.out.println("Xóa câu lạc bộ thành công");
        } catch (SQLException e) {
            System.out.println("Error when delete club: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Deactive a club by ID

    public void activeClub(int clubID) {
        String sql = "UPDATE clubs SET Status = 'active' WHERE ClubID = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, clubID);
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error when active club in database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Delete a club by ID
    public void deleteClub(int clubID) {
        // Câu lệnh xóa dữ liệu từ các bảng phụ
        String deleteTasks = "DELETE FROM task WHERE clubID = ?";
        String deleteEvents = "DELETE FROM events WHERE ClubID = ?";
        String deletePosts = "DELETE FROM post WHERE club_id = ?";
        // Câu lệnh xóa câu lạc bộ
        String deleteClubSQL = "DELETE FROM clubs WHERE ClubID = ?";

        try (Connection conn = getConnection()) {
            // Bắt đầu một giao dịch
            conn.setAutoCommit(false); // Tắt chế độ tự động commit để đảm bảo tính toàn vẹn dữ liệu

            // Xóa dữ liệu từ bảng task
            try (PreparedStatement stm = conn.prepareStatement(deleteTasks)) {
                stm.setInt(1, clubID);
                stm.executeUpdate();
            }

            // Xóa dữ liệu từ bảng events
            try (PreparedStatement stm = conn.prepareStatement(deleteEvents)) {
                stm.setInt(1, clubID);
                stm.executeUpdate();
            }

            // Xóa dữ liệu từ bảng posts
            try (PreparedStatement stm = conn.prepareStatement(deletePosts)) {
                stm.setInt(1, clubID);
                stm.executeUpdate();
            }

            // Xóa câu lạc bộ từ bảng clubs
            try (PreparedStatement stm = conn.prepareStatement(deleteClubSQL)) {
                stm.setInt(1, clubID);
                stm.executeUpdate();
            }

            // Commit giao dịch sau khi tất cả các bước xóa thành công
            conn.commit();
            System.out.println("Xóa câu lạc bộ và dữ liệu liên quan thành công");

        } catch (SQLException e) {
            // Rollback nếu có lỗi
            try (Connection conn = getConnection()) {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println("Error during rollback: " + rollbackEx.getMessage());
            }
            System.out.println("Error when deleting club: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Club> getFilteredClubs(String keyword, String category, String status) {
        List<Club> clubs = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT C.*, U.Fullname "
                + "FROM clubs c "
                + "LEFT JOIN users u ON c.ClubLeader = u.Username "
                + "WHERE 1=1"
        );

        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND ClubName LIKE ?");
        }
        if (category != null && !category.isEmpty()) {
            sql.append(" AND Categories = ?");
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" AND status = ?");
        }

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql.toString())) {
            int index = 1;
            if (keyword != null && !keyword.isEmpty()) {
                stm.setString(index++, "%" + keyword + "%");
            }
            if (category != null && !category.isEmpty()) {
                stm.setString(index++, category);
            }
            if (status != null && !status.isEmpty()) {
                stm.setString(index++, status);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Club club = new Club();
                club.setClubID(rs.getInt("ClubID"));
                club.setClubName(rs.getString("ClubName"));
                club.setCategory(rs.getString("Categories"));
                club.setDescription(rs.getString("Description"));
                club.setIsPublic(rs.getString("IsPublic"));
                club.setImgURL(rs.getString("ImageURL"));
                club.setStatus(rs.getString("status"));
                club.setClubLeader(rs.getString("Clubleader"));
                club.setcontactClub(rs.getString("ContactClub"));
                club.setSchedule(rs.getString("Schedule"));
                club.setFullName(rs.getString("Fullname"));
                clubs.add(club);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }
    
    public boolean isClubNameExist(String name) {
    String sql = "SELECT 1 FROM Clubs WHERE ClubName = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();
        return rs.next(); // Có dữ liệu => tên đã tồn tại
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // hoặc có thể throw exception nếu bạn muốn xử lý ở trên
    }
    }
    public boolean isEmailExist(String email) {
    String sql = "SELECT 1 FROM Clubs WHERE ContactClub = ?";
    try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        return rs.next(); // Có dữ liệu => tên đã tồn tại
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // hoặc có thể throw exception nếu bạn muốn xử lý ở trên
    }
}

}
