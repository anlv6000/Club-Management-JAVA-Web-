/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Dal.DBContext;
import Model.Account;
import Model.Event;
import Model.GoogleAccount;
import Model.Public_club;
import Model.Setting;
import Model.TokenFogotPass;
import com.sun.source.tree.ArrayAccessTree;
import entity.User;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Doan Quan
 */
public class dao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;

    //Register (add account)
    //Register (add account)
    public void addAcount(String username, String password, String email) {
        String sql = "INSERT INTO users (Username, Password, Email ) VALUES (?, ?, ?)";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, email);
            stm.executeUpdate();
            System.out.println("Thêm tài khoản thành công!");
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm tài khoản: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public boolean checkAccountExist(String user) {
        String query = "select * from users \n"
                + "where Username = ? \n";
        try {
            stm = getConnection().prepareStatement(query);
            stm.setString(1, user);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public Account login(String user, String pass) {
        String query = "SELECT * FROM users WHERE Username = ?";
        try {
            stm = getConnection().prepareStatement(query);
            stm.setString(1, user);
            rs = stm.executeQuery();

            if (rs.next()) {
                // Tạo đối tượng Account từ kết quả truy vấn
                Account a = new Account(rs.getString("Username"), rs.getString("Password"),
                        rs.getString("UserType"));

                // Kiểm tra mật khẩu đã nhập với mật khẩu hash từ cơ sở dữ liệu
                if (BCrypt.checkpw(pass, a.getPassword())) {
                    return a; // Nếu mật khẩu đúng, trả về đối tượng Account
                } else {
                    return null; // Mật khẩu sai
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra
        }
        return null; // Trả về null nếu không tìm thấy tài khoản
    }

    public boolean checkLogin2(String username, String password) throws SQLException {
        String sql = " Select Username  "
                + " FROM users  "
                + " WHERE Username = ? "
                + " AND Password = ? ";

        try {
            stm = getConnection().prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        return false;
    }

    public List<Public_club> getTop5() {
        List<Public_club> list = new ArrayList<>();
        String query = "select * from clubs LIMIT 6 ";
        try {
            stm = getConnection().prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Public_club(rs.getString("ClubName"), rs.getString("Description"), rs.getString("ImageURL")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void addSettings(List<Setting> settings) throws SQLException {
        String sql = "INSERT INTO Settings (SettingName, SettingType, SettingValue, Priority, Status, UserType) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            for (Setting setting : settings) {
                stm.setString(1, setting.getName());
                stm.setString(2, setting.getType());
                stm.setString(3, setting.getValue());
                stm.setInt(4, setting.getPriority());
                stm.setString(5, setting.getStatus());
                stm.setString(6, setting.getUserType());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void updateSettings(List<Setting> settings) throws SQLException {
        String sql = "UPDATE Settings "
                + "SET SettingName = ?, "
                + "SettingType = ?, "
                + "SettingValue = ?, "
                + "Priority = ?, "
                + "Status = ?, "
                + "UserType = ? "
                + "WHERE SettingID = ?";
        try (Connection con = getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            for (Setting setting : settings) {
                stm.setString(1, setting.getName());
                stm.setString(2, setting.getType());
                stm.setString(3, setting.getValue());
                stm.setInt(4, setting.getPriority());
                stm.setString(5, setting.getStatus());
                stm.setString(6, setting.getUserType());
                stm.setInt(7, setting.getId()); // SettingID
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            throw e;
        }
    }

    public Setting getSettingById(int id) throws SQLException {
        String sql = "SELECT * FROM Settings WHERE SettingID = ?";
        try (Connection con = getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return new Setting(
                            rs.getInt("SettingID"),
                            rs.getString("SettingName"),
                            rs.getString("SettingType"),
                            rs.getString("SettingValue"),
                            rs.getInt("Priority"),
                            rs.getString("Status"),
                            rs.getString("UserType")
                    );
                }
            }
        } catch (SQLException e) {
            throw e;
        }
        return null;
    }

    public void updateStatus(int id, String newStatus) throws SQLException {
        String sql = "UPDATE Settings SET Status = ? WHERE SettingID = ?";
        try (Connection con = getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, newStatus);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public Account UserType(String username) {
        List<Account> list = new ArrayList<>();
        String sql = "select * from users where Username = ?; ";
        try {
            stm = getConnection().prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                Account acc = new Account(rs.getString("Username"), rs.getString("Password"), rs.getString("UserType"));
                return acc;

            }
        } catch (Exception e) {

        }
        return null;

    }

    public boolean isGoogleAccountExist(String googleId) throws SQLException {
        String query = "SELECT * FROM google_accounts WHERE google_id = ?";
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, googleId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {

        }
        return false;

    }

    public void saveGoogleAccount(GoogleAccount googleAccount, String email) {
        String query = "INSERT INTO google_accounts (google_id, name, email) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, googleAccount.getId());
            pstmt.setString(2, googleAccount.getName());
            pstmt.setString(3, email);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public GoogleAccount loginggg(String email) {
        String query = "SELECT * FROM google_accounts WHERE email = ?";
        try {
            stm = getConnection().prepareStatement(query);
            stm.setString(1, email);
            rs = stm.executeQuery();

            if (rs.next()) {
                // Tạo đối tượng Account từ kết quả truy vấn
                GoogleAccount a = new GoogleAccount(rs.getString("email"), rs.getString("UserType"));

                // Kiểm tra mật khẩu đã nhập với mật khẩu hash từ cơ sở dữ liệu
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra
        }
        return null; // Trả về null nếu không tìm thấy tài khoản
    }

    public String getFormatDate(LocalDateTime myDateObj) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    public boolean insertTokenForget(TokenFogotPass tokenForget) {
        String sql = "INSERT INTO tokenForgetPassword (token, expiryTime, isUsed, userId) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, tokenForget.getToken());
            ps.setTimestamp(2, Timestamp.valueOf(getFormatDate(tokenForget.getExpiryTime())));
            ps.setBoolean(3, tokenForget.isIsUsed());
            ps.setInt(4, tokenForget.getUserId());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public TokenFogotPass getTokenPassword(String token) {
        String sql = "Select * from tokenForgetPassword where token = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, token);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new TokenFogotPass(
                        rs.getInt("id"),
                        rs.getInt("userId"),
                        rs.getBoolean("isUsed"),
                        rs.getString("token"),
                        rs.getTimestamp("expiryTime").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateStatus(TokenFogotPass token) {
        System.out.println("token = " + token);
        String sql = "UPDATE tokenForgetPassword \n"
                + "   SET isUsed = ?\n"
                + " WHERE token = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setBoolean(1, token.isIsUsed());
            st.setString(2, token.getToken());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public User getUserByEmail(String email) {
        String sql = "Select * from users where Email = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("Password"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public User getUserById(int userId) {
        String sql = "Select * from users where UserID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("Password"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void updatePassword(String email, String password) {
        String sql = "UPDATE users \n"
                + "   SET  Password = ?\n"
                + " WHERE Email = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, email);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean insertAccount(String username, String password, String email, String userType, String status, String imageURL) {
        String sql = "INSERT INTO Users (Username, Password, Email, UserType, Status, AccountCreatedDate, LastLoginDate, ImageURL) "
                + "VALUES (?, ?, ?, ?, ?, NOW(), NULL, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
            ps.setString(2, hashedPassword);
            ps.setString(3, email);
            ps.setString(4, userType);
            ps.setString(5, status);
               
            ps.setString(6, imageURL);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                accounts.add(new Account(
                        rs.getInt("UserID"),
                        rs.getString("Password"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("UserType"),
                        rs.getString("Status"),
                        rs.getDate("AccountCreatedDate"),
                        rs.getTimestamp("LastLoginDate"),
                        rs.getString("ImageURL")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public Account getAccountById(String accountId) {
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, accountId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt("UserID"),
                        rs.getString("Password"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("UserType"),
                        rs.getString("Status"),
                        rs.getDate("AccountCreatedDate"),
                        rs.getDate("LastLoginDate"),
                        rs.getString("ImageURL")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAccount(Account account) throws SQLException {
        String sql = "UPDATE Users SET Username = ?, Email = ?, UserType = ?, Status = ? WHERE UserID = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getRole());
            ps.setString(4, account.getStatus());
            ps.setInt(5, account.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAccount(String accountId) throws SQLException {
        String sql = "DELETE FROM Users WHERE UserID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, accountId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Account> searchAccounts(String role, String status, String keyword) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Users WHERE 1=1"); // Adjust table name if needed

        if (role != null && !role.isEmpty()) {
            sql.append(" AND UserType = ?"); // Adjust field names if needed
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" AND Status = ?");
        }
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND (Username LIKE ? OR Email LIKE ?)");
        }

        try (PreparedStatement ps = getConnection().prepareStatement(sql.toString())) {
            int index = 1;

            // Set the dynamic parameters
            if (role != null && !role.isEmpty()) {
                ps.setString(index++, role);
            }
            if (status != null && !status.isEmpty()) {
                ps.setString(index++, status);
            }
            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(index++, "%" + keyword + "%");
                ps.setString(index++, "%" + keyword + "%");
            }

            // Execute the query and process the results
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    accounts.add(new Account(
                            rs.getInt("UserID"),
                            rs.getString("Password"),
                            rs.getString("Username"),
                            rs.getString("Email"),
                            rs.getString("UserType"),
                            rs.getString("Status"),
                            rs.getDate("AccountCreatedDate"),
                            rs.getDate("LastLoginDate"),
                            rs.getString("ImageURL")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging the error for better monitoring
        }

        return accounts;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Events";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("EventID"),
                        rs.getInt("ClubID"),
                        rs.getString("EventName"),
                        rs.getString("Description"),
                        rs.getString("EventDate"),
                        rs.getInt("CreatedBy"), // Lấy giá trị CreatedBy, có thể là null
                        rs.getString("CreatedDate"),
                        rs.getString("EventImageURL")
                );
                events.add(event);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách sự kiện: " + e.getMessage());
        }
        return events;
    }

    public boolean addEvent(int clubId, String eventName, String description, String eventDate, int createdBy, String createdDate, String eventImageURL) {
        String sql = "INSERT INTO Events (ClubID, EventName, Description, EventDate, CreatedBy, CreatedDate, EventImageURL) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, clubId);
            ps.setString(2, eventName);
            ps.setString(3, description);
            ps.setString(4, eventDate);

            if (createdBy != -1) {
                ps.setInt(5, createdBy);
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }

            ps.setString(6, createdDate);
            ps.setString(7, eventImageURL);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm sự kiện: " + e.getMessage());
            return false;
        }
    }

    public boolean editEvent(int eventID, String eventName, String description, String eventDate, String eventImageURL) {
        String sql = "UPDATE Events SET EventName = ?, Description = ?, EventDate = ?, EventImageURL = ? WHERE EventID = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Gán giá trị các tham số vào câu lệnh SQL
            ps.setString(1, eventName);
            ps.setString(2, description);
            ps.setString(3, eventDate);
            ps.setString(4, eventImageURL);
            ps.setInt(5, eventID); // Không thay đổi EventID, chỉ sử dụng để tìm sự kiện cần sửa

            // Thực thi câu lệnh UPDATE và kiểm tra số dòng bị ảnh hưởng
            int rowsAffected = ps.executeUpdate();

            // Nếu có ít nhất một dòng bị thay đổi, trả về true, ngược lại trả về false
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi chỉnh sửa sự kiện: " + e.getMessage());
        }
        return false; // Trả về false nếu có lỗi xảy ra
    }

    public boolean deleteEvent(int eventID) {
        String sql = "DELETE FROM Events WHERE EventID = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Gán giá trị EventID vào tham số của câu lệnh SQL
            ps.setInt(1, eventID);

            // Thực thi câu lệnh DELETE và kiểm tra số dòng bị ảnh hưởng
            int rowsAffected = ps.executeUpdate();

            // Nếu có ít nhất một dòng bị xóa, trả về true, ngược lại trả về false
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa sự kiện: " + e.getMessage());
        }
        return false; // Trả về false nếu có lỗi xảy ra
    }

    public Event getEventById(int eventId) {
        String sql = "SELECT * FROM Events WHERE EventID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Thiết lập tham số cho câu lệnh SQL
            ps.setInt(1, eventId);

            // Thực thi truy vấn
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Tạo đối tượng Event từ dữ liệu trong ResultSet
                    return new Event(
                            rs.getInt("EventID"),
                            rs.getInt("ClubID"),
                            rs.getString("EventName"),
                            rs.getString("Description"),
                            rs.getString("EventDate"),
                            rs.getInt("CreatedBy"), // Lấy giá trị CreatedBy, có thể là null
                            rs.getString("CreatedDate"),
                            rs.getString("EventImageURL")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy sự kiện theo ID: " + e.getMessage());
        }
        return null; // Trả về null nếu không tìm thấy sự kiện
    }

    public List<Event> eventSearchByName(String eventName) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Events WHERE EventName LIKE ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Sử dụng dấu '%' để tìm kiếm theo tên sự kiện có chứa chuỗi tìm kiếm
            ps.setString(1, "%" + eventName + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Event event = new Event(
                            rs.getInt("EventID"),
                            rs.getInt("ClubID"),
                            rs.getString("EventName"),
                            rs.getString("Description"),
                            rs.getString("EventDate"),
                            rs.getInt("CreatedBy"),
                            rs.getString("CreatedDate"),
                            rs.getString("EventImageURL")
                    );
                    events.add(event);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm sự kiện: " + e.getMessage());
        }
        return events;
    }

    public boolean updateEvent(int eventId, int clubId, String eventName, String description, String eventDate, Integer createdBy, String createdDate, String eventImageURL) {
        String sql = "UPDATE Events SET ClubID = ?, EventName = ?, Description = ?, EventDate = ?, "
                + "CreatedBy = ?, CreatedDate = ?, EventImageURL = ? WHERE EventID = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Thiết lập các tham số cho câu lệnh SQL
            ps.setInt(1, clubId);
            ps.setString(2, eventName);
            ps.setString(3, description);
            ps.setString(4, eventDate);

            // Xử lý trường hợp CreatedBy có thể là null
            if (createdBy != null) {
                ps.setInt(5, createdBy);
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }

            ps.setString(6, createdDate);
            ps.setString(7, eventImageURL);
            ps.setInt(8, eventId); // EventID để xác định sự kiện cần cập nhật

            // Thực thi câu lệnh SQL
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu cập nhật thành công

        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật sự kiện: " + e.getMessage());
            e.printStackTrace(); // In lỗi ra console
            return false;
        }
    }

    public static void main(String[] args) {
        dao eventDAO = new dao();
// check list
        List<Event> events = eventDAO.getAllEvents();

        // In ra danh sách sự kiện
        for (Event event : events) {
            System.out.println(event);
        }

        boolean isSuccess = eventDAO.updateEvent(
                6, // EventID cần cập nhật
                1, // ClubID
                "Workshop Lập Trình Java Nâng Cao", // EventName (cập nhật)
                "Học lập trình Java từ cơ bản đến nâng cao với chuyên gia", // Description (cập nhật)
                "2024-11-25 14:00:00", // EventDate (cập nhật)
                null, // CreatedBy (có thể là null)
                "2023-10-10 12:00:00", // CreatedDate
                "java_workshop_updated.jpg" // EventImageURL (cập nhật)
        );

        if (isSuccess) {
            System.out.println("Cập nhật sự kiện thành công!");
        } else {
            System.out.println("Cập nhật sự kiện thất bại.");
        }

    }

}
