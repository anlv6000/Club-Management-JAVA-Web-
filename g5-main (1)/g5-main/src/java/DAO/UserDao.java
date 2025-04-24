package DAO;

import Model.UserProfile;
import database.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public boolean checkEmailExist(String email) throws SQLException {
        String query = "SELECT 1 FROM Users WHERE Email = ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Returns true if a record is found
            }
        }
    }

    public List<UserProfile> getUserProfilesByUserID(int userID) throws SQLException {
        List<UserProfile> userProfiles = new ArrayList<>();
        String query = "SELECT uc.ClubID, c.ClubName, u.UserID, u.Username, u.Fullname, u.Email, u.Password, uc.Phone, u.ImageURL "
                + "FROM Users u "
                + "LEFT JOIN UserClubs uc ON u.UserID = uc.UserID "
                + "LEFT JOIN Clubs c ON uc.ClubID = c.ClubID "
                + "WHERE u.UserID = ?  and u.status ='Active'";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userID);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    UserProfile userProfile = new UserProfile(
                            rs.getInt("UserID"),
                            rs.getInt("ClubID"),
                            rs.getString("ClubName"),
                            rs.getString("Username"),
                            rs.getString("Fullname"),
                            rs.getString("Password"),
                            rs.getString("Email"),
                            null,
                            null,
                            null,
                            rs.getDouble("Phone"),
                            null,
                            rs.getString("ImageURL") // Lấy imageURL từ ResultSet
                    );
                    userProfiles.add(userProfile);
                }
            }
        }
        return userProfiles;
    }

    public boolean updateUserAndUserClub(int userID, int clubID, String fullname, String email, String password, String imageURL, double phone) throws SQLException {
        // Câu lệnh SQL cập nhật bảng Users
        String userUpdateQuery = "UPDATE Users SET Fullname = ?, Email = ?, Password = ?, ImageURL = ? WHERE UserID = ?";
        String userClubUpdateQuery = "UPDATE UserClubs SET phone = ? WHERE UserID = ? AND ClubID = ?";

        try (Connection connection = new DBContext().getConnection()) {
            connection.setAutoCommit(false); // Bắt đầu transaction

            try (PreparedStatement userStmt = connection.prepareStatement(userUpdateQuery); PreparedStatement userClubStmt = connection.prepareStatement(userClubUpdateQuery)) {

                // Gán giá trị cho câu lệnh UPDATE bảng Users
                userStmt.setString(1, fullname);
                userStmt.setString(2, email);
                userStmt.setString(3, password); // hashedPassword được truyền vào đây
                userStmt.setString(4, imageURL);
                userStmt.setInt(5, userID);
                userStmt.executeUpdate();

                // Gán giá trị cho câu lệnh UPDATE bảng UserClubs
                userClubStmt.setDouble(1, phone); // Gán giá trị phone
                userClubStmt.setInt(2, userID);
                userClubStmt.setInt(3, clubID);
                userClubStmt.executeUpdate();

                connection.commit(); // Commit transaction nếu không có lỗi
                return true;
            } catch (SQLException e) {
                connection.rollback(); // Rollback nếu có lỗi
                throw e;
            }
        }
    }

    public boolean updateFullnameAndPhone(int userId, String fullname, double phone) throws SQLException {
        String updateUsersQuery = "UPDATE Users SET Fullname = ? WHERE UserID = ?";
        String updateUserClubQuery = "UPDATE UserClubs SET phone = ? WHERE UserID = ?";

        // Mở kết nối và thiết lập giao dịch
        try (Connection connection = new DBContext().getConnection()) {
            connection.setAutoCommit(false); // Bắt đầu giao dịch

            try (PreparedStatement updateUsersStmt = connection.prepareStatement(updateUsersQuery); PreparedStatement updateUserClubStmt = connection.prepareStatement(updateUserClubQuery)) {

                // Cập nhật bảng Users
                updateUsersStmt.setString(1, fullname);
                updateUsersStmt.setInt(2, userId);
                int usersUpdated = updateUsersStmt.executeUpdate();

                // Cập nhật bảng UserClub
                updateUserClubStmt.setDouble(1, phone);
                updateUserClubStmt.setInt(2, userId);
                int userClubUpdated = updateUserClubStmt.executeUpdate();

                // Kiểm tra xem cả hai bản ghi đều được cập nhật
                if (usersUpdated > 0 && userClubUpdated > 0) {
                    connection.commit(); // Xác nhận giao dịch
                    return true;
                } else {
                    connection.rollback(); // Hoàn tác nếu bất kỳ bản ghi nào không được cập nhật
                    return false;
                }
            } catch (SQLException e) {
                connection.rollback(); // Hoàn tác nếu xảy ra lỗi
                throw e;
            } finally {
                connection.setAutoCommit(true); // Khôi phục trạng thái tự động commit
            }
        }
    }

    public boolean updateEmail(int userId, String email) throws SQLException {
        String query = "UPDATE Users SET Email = ? WHERE UserID = ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setInt(2, userId);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean updatePassword(int userID, String password) throws SQLException {
        String query = "UPDATE Users SET Password = ? WHERE UserID = ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, password);
            stmt.setInt(2, userID);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean updateProfileImage(int userId, String imageURL) throws SQLException {
        String query = "UPDATE Users SET ImageURL = ? WHERE UserID = ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, imageURL); // Đường dẫn ảnh
            stmt.setInt(2, userId);      // ID người dùng
            return stmt.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        }
    }

}
