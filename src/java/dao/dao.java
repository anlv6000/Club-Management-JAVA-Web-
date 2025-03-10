/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import database.DBContext;
import Model.User;
import Model.Club;
import Model.Event;
import Model.GoogleAccount;
import Model.Post;
import Model.Public_club;
import Model.Setting;
import Model.TokenFogotPass;
import com.sun.source.tree.ArrayAccessTree;

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
import Model.User;
import Model.UserClub;
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
    public void addAcount(String username, String password, String email, String fullname) {
        String sql = "INSERT INTO users (Username, Password, Email,Fullname ) VALUES (?, ?, ?,?)";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, email);
            stm.setString(4, fullname);
            stm.executeUpdate();
            System.out.println("Thêm tài khoản thành công!");
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm tài khoản: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public boolean checkfullnameExist(String fullname) {
        String query = "select * from users \n"
                + "where Fullname = ? \n";
        try {
            stm = getConnection().prepareStatement(query);
            stm.setString(1, fullname);
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

    public boolean checkemailExist(String email) {
        String query = "select * from users \n"
                + "where Email = ? \n";
        try {
            stm = getConnection().prepareStatement(query);
            stm.setString(1, email);
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

    public User login(String user, String pass) {
        String query = "SELECT * FROM users WHERE Username = ? or email = ? ";
        try {
            stm = getConnection().prepareStatement(query);
            stm.setString(1, user);
            stm.setString(2, user);
            rs = stm.executeQuery();

            if (rs.next()) {
                // Tạo đối tượng Account từ kết quả truy vấn
                User a = new User(rs.getInt("UserID"), rs.getString("Username"), rs.getString("Email"), rs.getString("Password"), rs.getString("UserType"), rs.getString("Fullname"), rs.getString("Status"));

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

    public List<Club> getTop5() {
        List<Club> list = new ArrayList<>();
        String query = "select * from clubs LIMIT 3 ";
        try {
            stm = getConnection().prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Club(rs.getString("ClubName"),
                        rs.getString("Categories"), rs.getString("Description"),
                        rs.getString("ImageURL"), rs.getString("ContactClub"),
                        rs.getString("Schedule")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Club getdetail(String url) {
        List<Club> list = new ArrayList<>();
        String query = "select * from clubs where CLubName = ? ";
        try {
            stm = getConnection().prepareStatement(query);
            stm.setString(1, url);
            rs = stm.executeQuery();

            if (rs.next()) {

                return new Club(rs.getString("ClubName"),
                        rs.getString("Categories"), rs.getString("Description"),
                        rs.getString("ImageURL"), rs.getString("ContactClub"),
                        rs.getString("Schedule"));
            }
        } catch (Exception e) {
        }
        return null;

    }

//setting
    public void addSettings(List<Setting> settings) throws SQLException {
        String sql = "INSERT INTO Settings (SettingName, SettingType, SettingValue, Priority, Status, UserType, Description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            for (Setting setting : settings) {
                stm.setString(1, setting.getName());
                stm.setString(2, setting.getType());
                stm.setString(3, setting.getValue());
                stm.setInt(4, setting.getPriority());
                stm.setString(5, setting.getStatus());
                stm.setString(6, setting.getUserType());
                stm.setString(7, setting.getDescription());
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            System.err.println("Error inserting settings: " + e.getMessage());
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
                + "UserType = ?, "
                + "Description = ? "
                + "WHERE SettingID = ?";
        try (Connection con = getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            for (Setting setting : settings) {
                stm.setString(1, setting.getName());
                stm.setString(2, setting.getType());
                stm.setString(3, setting.getValue());
                stm.setInt(4, setting.getPriority());
                stm.setString(5, setting.getStatus());
                stm.setString(6, setting.getUserType());
                stm.setString(7, setting.getDescription());
                stm.setInt(8, setting.getId()); // SettingID
                stm.addBatch();
            }
            stm.executeBatch();
        } catch (SQLException e) {
            System.err.println("Error updating settings: " + e.getMessage());
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
                            rs.getString("UserType"),
                            rs.getString("Description") // thêm Description
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving setting: " + e.getMessage());
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

//
    public User UserType(String username) {
        List<User> list = new ArrayList<>();
        String sql = "select * from users where Username = ?; ";
        try {
            stm = getConnection().prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                User acc = new User(rs.getString("Username"), rs.getString("Password"), rs.getString("UserType"));
                return acc;

            }
        } catch (Exception e) {

        }
        return null;

    }

    public boolean isGoogleAccountExist(String googleId) throws SQLException {
        String query = "SELECT * FROM users WHERE google_id = ?";
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
        String query = "INSERT INTO users (google_id, Username, Email ,Password ) VALUES (?, ?, ?, ?) ";
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, googleAccount.getId());
            pstmt.setString(2, googleAccount.getName());
            pstmt.setString(3, email);
            pstmt.setString(4, email);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public GoogleAccount loginggg(String email) {
        String query = "SELECT * FROM users WHERE Email = ?";
        try {
            stm = getConnection().prepareStatement(query);
            stm.setString(1, email);
            rs = stm.executeQuery();

            if (rs.next()) {
                // Tạo đối tượng Account từ kết quả truy vấn
                GoogleAccount a = new GoogleAccount(rs.getString("Email"), rs.getString("Username"), rs.getString("UserType"));

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

    public boolean insertAccount(String username, String fullname, String password, String email, String userType, String status, String imageURL) {
        String sql = "INSERT INTO Users (Username, Fullname, Password, Email, UserType, Status, CreatedDate, LastLoginDate, ImageURL) "
                + "VALUES (?, ?, ?, ?, ?, ?, NOW(), NULL, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, fullname);
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
            ps.setString(3, hashedPassword);
            ps.setString(4, email);
            ps.setString(5, userType);
            ps.setString(6, status);
            ps.setString(7, imageURL);

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllAccounts() {
        List<User> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                accounts.add(new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getTimestamp("LastLoginDate"),
                        rs.getDate("CreatedDate"),
                        rs.getString("ImageURL"),
                        rs.getString("UserType"),
                        rs.getString("Fullname"),
                        rs.getString("Status"),
                        rs.getString("note")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public User getAccountById(String accountId) {
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);

            ps.setString(1, accountId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getTimestamp("LastLoginDate"),
                        rs.getTimestamp("CreatedDate"),
                        rs.getString("ImageURL"),
                        rs.getString("UserType"),
                        rs.getString("Fullname"),
                        rs.getString("Status"),
                        rs.getString("note")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAccount(User account) throws SQLException {
        String sql = "UPDATE Users SET Username = ?, Email = ?, UserType = ?, Status = ?, Fullname = ?, note = ? WHERE UserID = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, account.getUserName());
            ps.setString(2, account.getEmail());
            ps.setString(3, account.getRole());
            ps.setString(4, account.getStatus());
            ps.setString(5, account.getFullname());
            ps.setString(6, account.getText());
            ps.setInt(7, account.getId());
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

    public List<User> searchAccounts(String role, String status, String keyword) throws SQLException {
        List<User> accounts = new ArrayList<>();
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
                    accounts.add(new User(
                            rs.getInt("UserID"),
                            rs.getString("Username"),
                            rs.getString("Email"),
                            rs.getString("Password"),
                            rs.getTimestamp("LastLoginDate"),
                            rs.getDate("CreatedDate"),
                            rs.getString("ImageURL"),
                            rs.getString("UserType"),
                            rs.getString("Fullname"),
                            rs.getString("Status"),
                            rs.getString("note")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging the error for better monitoring
        }

        return accounts;
    }

    public List<User> getAccountsFiltered(String role, String status, String keyword, String sortField, String sortOrder) {
        List<User> accounts = new ArrayList<>();
        String query = "SELECT * FROM Users WHERE 1=1";

        if (role != null && !role.isEmpty()) {
            query += " AND UserType = ?";
        }
        if (status != null && !status.isEmpty()) {
            query += " AND Status = ?";
        }
        if (keyword != null && !keyword.isEmpty()) {
            query += " AND (Username LIKE ? OR Email LIKE ?)";
        }
        if (sortField != null && !sortField.isEmpty()) {
            query += " ORDER BY " + sortField;
            if (sortOrder != null && sortOrder.equals("desc")) {
                query += " DESC";
            } else {
                query += " ASC";
            }
        }

        try (PreparedStatement ps = getConnection().prepareStatement(query.toString())) {
            int index = 1;
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

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accounts.add(new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getTimestamp("LastLoginDate"),
                        rs.getDate("CreatedDate"),
                        rs.getString("ImageURL"),
                        rs.getString("UserType"),
                        rs.getString("Fullname"),
                        rs.getString("Status"),
                        rs.getString("note")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                        rs.getInt("CreatedBy"), // Có thể null
                        rs.getString("CreatedDate"),
                        rs.getString("EventImageURL"),
                        rs.getBoolean("Status"), // Cột mới
                        rs.getBoolean("Type") // Cột mới
                );
                events.add(event);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách sự kiện: " + e.getMessage());
        }
        return events;
    }

    public boolean addEvent(int clubId, String eventName, String description, String eventDate, int createdBy, String createdDate, String eventImageURL, boolean type, boolean status) {
        String sql = "INSERT INTO Events (ClubID, EventName, Description, EventDate, CreatedBy, CreatedDate, EventImageURL, Status, Type) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            ps.setBoolean(8, status);   // Thêm status
            ps.setBoolean(9, type); // Thêm type

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm sự kiện: " + e.getMessage());
            return false;
        }
    }

    public Club getClubByName(String clubName) {
        Club club = null;
        String sql = "SELECT * FROM clubs WHERE ClubName = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, clubName);
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
                club.setClubleader(rs.getString("Clubleader"));
                club.setContactClub(rs.getString("ContactClub"));
                club.setSchedule(rs.getString("Schedule"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return club;
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
    String sql = "SELECT " +
            "e.EventID, e.EventName, e.Description, e.EventDate, e.CreatedDate, " +
            "e.EventImageURL, e.ClubID, e.CreatedBy, e.Status, e.Type, " +
            "c.ClubName, u.Username AS CreatedByUsername " +
            "FROM Events e " +
            "JOIN Clubs c ON e.ClubID = c.ClubID " +
            "LEFT JOIN Users u ON e.CreatedBy = u.UserID " +
            "WHERE e.EventID = ?";

    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, eventId);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Event(
                        rs.getInt("EventID"),
                        rs.getInt("ClubID"),
                        rs.getString("EventName"),
                        rs.getString("Description"),
                        rs.getString("EventDate"),
                        rs.getInt("CreatedBy"), 
                        rs.getString("CreatedDate"),
                        rs.getString("EventImageURL"),
                        rs.getBoolean("Status"), 
                        rs.getBoolean("Type"), 
                        rs.getString("ClubName"),
                        rs.getString("CreatedByUsername")
                );
            }
        }
    } catch (SQLException e) {
        System.out.println("Lỗi khi lấy sự kiện: " + e.getMessage());
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
                            rs.getInt("CreatedBy"), // Có thể null
                            rs.getString("CreatedDate"),
                            rs.getString("EventImageURL"),
                            rs.getBoolean("Status"), // Cột mới
                            rs.getBoolean("Type") // Cột mới
                    );
                    events.add(event);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm sự kiện: " + e.getMessage());
        }
        return events;
    }

    public List<Event> searchByType(boolean eventType) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Events WHERE Type = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, eventType);

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
                            rs.getString("EventImageURL"),
                            rs.getBoolean("Status"),
                            rs.getBoolean("Type")
                    );
                    events.add(event);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm sự kiện theo loại: " + e.getMessage());
        }
        return events;
    }

    public List<Event> getEventTypeByPage(int page, int pageSize, boolean type) {
        List<Event> eventList = new ArrayList<>();
        String sql = "SELECT e.*, c.ClubName, u.Username AS CreatedByUsername "
                + "FROM Events e "
                + "JOIN Clubs c ON e.ClubID = c.ClubID "
                + "LEFT JOIN Users u ON e.CreatedBy = u.UserID "
                + "WHERE e.Type = ? "
                + // Lọc theo type
                "ORDER BY e.EventDate DESC "
                + "LIMIT ? OFFSET ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Thiết lập giá trị lọc theo Type
            ps.setBoolean(1, type);

            // Thiết lập số lượng bản ghi trên mỗi trang
            ps.setInt(2, pageSize);

            // Tính toán vị trí bắt đầu của dữ liệu trên trang hiện tại
            int offset = (page - 1) * pageSize;
            ps.setInt(3, offset);

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
                            rs.getString("EventImageURL"),
                            rs.getBoolean("Status"),
                            rs.getBoolean("Type"),
                            rs.getString("ClubName"), // Lấy thêm tên CLB
                            rs.getString("CreatedByUsername") // Lấy thêm Username của người tạo
                    );
                    eventList.add(event);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi phân trang sự kiện theo Type: " + e.getMessage());
        }
        return eventList;
    }

    public int getTotalEventsByType(boolean type) {
        int totalEvents = 0;
        String sql = "SELECT COUNT(*) AS total FROM Events WHERE Type = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Thiết lập giá trị lọc theo Type
            ps.setBoolean(1, type);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalEvents = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi lấy tổng số sự kiện theo Type: " + e.getMessage());
        }
        return totalEvents;
    }

    public List<Event> getEventNameByPage(int page, int pageSize, String eventName) {
        List<Event> eventList = new ArrayList<>();
        String sql = "SELECT e.*, c.ClubName, u.Username AS CreatedByUsername "
                + "FROM Events e "
                + "JOIN Clubs c ON e.ClubID = c.ClubID "
                + "LEFT JOIN Users u ON e.CreatedBy = u.UserID "
                + "WHERE e.EventName LIKE ? "
                + // Tìm kiếm theo tên sự kiện
                "ORDER BY e.EventDate DESC "
                + "LIMIT ? OFFSET ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Thiết lập giá trị lọc theo EventName (tìm kiếm tương đối)
            ps.setString(1, "%" + eventName + "%");

            // Thiết lập số lượng bản ghi trên mỗi trang
            ps.setInt(2, pageSize);

            // Tính toán vị trí bắt đầu của dữ liệu trên trang hiện tại
            int offset = (page - 1) * pageSize;
            ps.setInt(3, offset);

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
                            rs.getString("EventImageURL"),
                            rs.getBoolean("Status"),
                            rs.getBoolean("Type"),
                            rs.getString("ClubName"), // Lấy thêm tên CLB
                            rs.getString("CreatedByUsername") // Lấy thêm Username của người tạo
                    );
                    eventList.add(event);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi phân trang sự kiện theo tên: " + e.getMessage());
        }
        return eventList;
    }

    public int getTotalEventsByName(String eventName) {
        int totalEvents = 0;
        String sql = "SELECT COUNT(*) AS total FROM Events WHERE EventName LIKE ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Thiết lập giá trị lọc theo EventName (tìm kiếm tương đối)
            ps.setString(1, "%" + eventName + "%");

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalEvents = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi lấy tổng số sự kiện theo tên: " + e.getMessage());
        }
        return totalEvents;
    }

    public boolean updateEvent(int eventId, int clubId, String eventName, String description, String eventDate, Integer createdBy, String createdDate, String eventImageURL, boolean eventStatus, boolean eventType) {
        String sql = "UPDATE Events SET ClubID = ?, EventName = ?, Description = ?, EventDate = ?, "
                + "CreatedBy = ?, CreatedDate = ?, EventImageURL = ?, Type = ?, Status = ? WHERE EventID = ?";

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
            ps.setBoolean(8, eventStatus); // Thêm Type
            ps.setBoolean(9, eventType); // Thêm Status
            ps.setInt(10, eventId); // EventID để xác định sự kiện cần cập nhật

            // Thực thi câu lệnh SQL
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu cập nhật thành công

        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật sự kiện: " + e.getMessage());
            e.printStackTrace(); // In lỗi ra console để dễ debug
            return false;
        }
    }

//    public List<Event> getEventByPage(int page, int pageSize) {
//    List<Event> eventList = new ArrayList<>();
//    String sql = "SELECT * FROM Events ORDER BY EventDate DESC LIMIT ? OFFSET ?";
//
//    try (Connection conn = getConnection();
//          PreparedStatement ps = conn.prepareStatement(sql)) {
//        
//        // Thiết lập số lượng bản ghi trên mỗi trang
//        ps.setInt(1, pageSize);
//        
//        // Tính toán vị trí bắt đầu của dữ liệu trên trang hiện tại
//        int offset = (page - 1) * pageSize;
//        ps.setInt(2, offset);
//
//        try (ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                Event event = new Event(
//                    rs.getInt("EventID"),
//                    rs.getInt("ClubID"),
//                    rs.getString("EventName"),
//                    rs.getString("Description"),
//                    rs.getString("EventDate"),
//                    rs.getInt("CreatedBy"),
//                    rs.getString("CreatedDate"),
//                    rs.getString("EventImageURL"),
//                    rs.getBoolean("Status"),
//                    rs.getBoolean("Type")
//                );
//                eventList.add(event);
//            }
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//        System.out.println("Lỗi khi phân trang sự kiện: " + e.getMessage());
//    }
//    return eventList;
//}
    public List<Event> getEventByPage(int page, int pageSize) {
        List<Event> eventList = new ArrayList<>();
        String sql = "SELECT e.*, c.ClubName, u.Username AS CreatedByUsername "
                + "FROM Events e "
                + "JOIN Clubs c ON e.ClubID = c.ClubID "
                + "LEFT JOIN Users u ON e.CreatedBy = u.UserID "
                + "ORDER BY e.EventDate DESC "
                + "LIMIT ? OFFSET ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Thiết lập số lượng bản ghi trên mỗi trang
            ps.setInt(1, pageSize);

            // Tính toán vị trí bắt đầu của dữ liệu trên trang hiện tại
            int offset = (page - 1) * pageSize;
            ps.setInt(2, offset);

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
                            rs.getString("EventImageURL"),
                            rs.getBoolean("Status"),
                            rs.getBoolean("Type"),
                            rs.getString("ClubName"), // Lấy thêm tên CLB
                            rs.getString("CreatedByUsername") // Lấy thêm Username của người tạo
                    );
                    eventList.add(event);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi phân trang sự kiện: " + e.getMessage());
        }
        return eventList;
    }

    public int getTotalEvents() {
        String sql = "SELECT COUNT(*) FROM Events";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Nếu có lỗi, trả về 0 sự kiện
    }

    public int getClubIDByName(String clubName) {
        String sql = "SELECT ClubID FROM clubs WHERE ClubName = ?";
        int clubID = -1; // Mặc định -1 nếu không tìm thấy

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, clubName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    clubID = rs.getInt("ClubID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubID;
    }

    public int getUserIdByUsername(String username) {
        String sql = "SELECT UserID FROM Users WHERE Username = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("UserID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy User
    }

    public List<Event> getAllEventByClubID(int clubID) {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT \n"
                + "    e.EventID,\n"
                + "    e.EventName,\n"
                + "    e.Description,\n"
                + "    e.EventDate,\n"
                + "    e.CreatedDate,\n"
                + "    e.EventImageURL,\n"
                + "    e.ClubID,\n"
                + "    e.CreatedBy,\n"
                + "    e.Status,\n"
                + "    e.Type,\n"
                + "    c.ClubName,\n"
                + "    u.Username AS CreatedByUsername\n"
                + "FROM Events e\n"
                + "JOIN Clubs c ON e.ClubID = c.ClubID\n"
                + "LEFT JOIN Users u ON e.CreatedBy = u.UserID\n"
                + "WHERE e.ClubID = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clubID);
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
                            rs.getString("EventImageURL"),
                            rs.getBoolean("Status"),
                            rs.getBoolean("Type"),
                            rs.getString("ClubName"),
                            rs.getString("CreatedByUsername")
                    );
                    events.add(event);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách sự kiện theo ClubID: " + e.getMessage());
        }
        return events;
    }
    public List<Event> getAllEventByyClubID(int ClubID) {
        List<Event> EventList = new ArrayList<>();
        String sql = " SELECT *   \n" +
" FROM events   \n " +
"    \n" +
"ORDER BY EventID DESC   \n" +
"LIMIT 3 ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, ClubID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                EventList.add(new Event(rs.getInt("EventID"), rs.getInt("ClubID"), rs.getString("EventName"), rs.getString("Description"), rs.getString("EventDate"), rs.getInt("CreatedBy"), rs.getString("CreatedDate"), rs.getString("EventImageURL"), rs.getBoolean("Status"), rs.getBoolean("Type")));
                
            }
        } catch (Exception e) {
        }
        return EventList;
    }

    public List<Event> getLatest4Events() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT e.EventID, e.EventName, e.Description, e.EventDate, e.CreatedDate, e.EventImageURL, "
                + "e.ClubID, e.CreatedBy, e.Status, e.Type, c.ClubName, u.Username AS CreatedByUsername "
                + "FROM Events e "
                + "JOIN Clubs c ON e.ClubID = c.ClubID "
                + "LEFT JOIN Users u ON e.CreatedBy = u.UserID "
                + "ORDER BY e.CreatedDate DESC LIMIT 4";  // Lấy 4 sự kiện mới nhất

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("EventID"),
                        rs.getInt("ClubID"),
                        rs.getString("EventName"),
                        rs.getString("Description"),
                        rs.getString("EventDate"),
                        rs.getInt("CreatedBy"),
                        rs.getString("CreatedDate"),
                        rs.getString("EventImageURL"),
                        rs.getBoolean("Status"),
                        rs.getBoolean("Type"),
                        rs.getString("ClubName"),
                        rs.getString("CreatedByUsername")
                );
                events.add(event);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy 4 sự kiện gần nhất: " + e.getMessage());
        }
        return events;
    }

    public static void main(String[] args) {
        dao eventDAO = new dao();
// check list
        List<Event> events = eventDAO.getAllEvents();

//        // In ra danh sách sự kiện
//        for (Event event : events) {
//            System.out.println(event);
//        }
// check add 
//    boolean result = eventDAO.addEvent(1, "ABCD", "Yearly gathering for club members",
//                                      "2025-03-10 15:00:00", -1,"2025-03-10 15:00:00" , "https://example.com/event.jpg",Boolean.parseBoolean("true"),
//                                   Boolean.parseBoolean("true"));
//    System.out.println("Kết quả thêm sự kiện: " + result);
//check edit
//boolean isEdited = eventDAO.editEvent(6, 
//                                           "Workshop Kỹ Năng Lãnh Đạo", 
//                                           "Chương trình phát triển kỹ năng lãnh đạo cho sinh viên", 
//                                           "2024-11-30", 
//                                           "leadership_workshop.jpg",Boolean.parseBoolean("true"),
//                                   Boolean.parseBoolean("false"));
//if (isEdited) {
//    System.out.println("Sự kiện đã được chỉnh sửa thành công.");
//} else {
//    System.out.println("Không thể chỉnh sửa sự kiện.");
//}
//
// check search       
//              List<Event> events = eventDAO.eventSearchByName("lập trình");
//
//        // In ra danh sách sự kiện
//        for (Event event : events) {
//            System.out.println(event);
//        }
//      
        //check xoa 
//        boolean isDeleted = eventDAO.deleteEvent(11);
//
//        if (isDeleted) {
//            System.out.println("Sự kiện đã được xóa thành công.");
//        } else {
//            System.out.println("Không thể xóa sự kiện.");
//        }
//check event byID
//Event e = eventDAO.getEventById(6);
//          System.out.println(e);
        // Gọi hàm updateEvent để cập nhật sự kiện
//        boolean isSuccess = eventDAO.updateEvent(
//                1, // EventID cần cập nhật
//                1, // ClubID
//                "Workshop Lập Trình Java Nâng Cao", // EventName (cập nhật)
//                "Học lập trình Java từ cơ bản đến nâng cao với chuyên gia", // Description (cập nhật)
//                "2024-11-25 14:00:00", // EventDate (cập nhật)
//                null, // CreatedBy (có thể là null)
//                "2023-10-10 12:00:00", // CreatedDate
//                "java_workshop_updated.jpg", // EventImageURL (cập nhật)
//                Boolean.parseBoolean("false"),
//                           Boolean.parseBoolean("false")
//        );
//
//        if (isSuccess) {
//            System.out.println("Cập nhật sự kiện thành công!");
//        } else {
//            System.out.println("Cập nhật sự kiện thất bại.");
//        }
//        List<Event> events = eventDAO.searchByType(Boolean.parseBoolean("false"));
        // In ra danh sách sự kiện
        for (Event event : events) {
            System.out.println(event);
        }

    }

    public List<Event> gettop3EventPublic() {
        List<Event> listEvent = new ArrayList<>();
        String sql = " SELECT * FROM events \n"
                + " WHERE status = 1 \n"
                + " ORDER BY EventID DESC  \n"
                + " LIMIT 3 ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listEvent.add(
                        new Event(rs.getInt("ClubID"),
                                rs.getString("EventName"),
                                rs.getString("Description"),
                                rs.getInt("CreatedBy"),
                                rs.getString("EventImageURL"),
                                rs.getString("EventDate")));
            }

        } catch (SQLException e) {

        }
        return listEvent;

    }

    public Club getClubById(int id) {
        Club c = new Club();
        String sql = " select * from clubs where ClubID = ? ";
        try {
            Connection con = getConnection();

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                c = new Club(rs.getInt("ClubID"), rs.getString("ClubName"),
                        rs.getString("Categories"), rs.getString("Description"),
                        rs.getString("Clubleader"),
                        rs.getString("ImageURL"),
                        rs.getString("ContactClub"),
                        rs.getString("Schedule"), rs.getString("CreatedDate"));
            }
            return c;
        } catch (Exception e) {
        }
        return c;

    }

    public void insertbyRegistered(int uid, int cid, String note, String phone) {
        String sql = " INSERT INTO UserClubs (`UserID`, `ClubID`, `note`, `phone`) VALUES (?, ?, ?, ?) ";

        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, uid);
            stm.setInt(2, cid);
            stm.setString(3, note);
            stm.setString(4, phone);
            stm.executeUpdate();
        } catch (SQLException e) {
        }

    }

    public void updateUseridWithClubs(int userID, int clubID) {
        String sql = "UPDATE clubs SET UserID = ? WHERE ClubID = ? ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, userID);
            stm.setInt(2, clubID);
            stm.executeUpdate();

        } catch (Exception e) {
        }
    }

    public void updateClubidWithUsers(int userID, int clubID) {
        String sql = "UPDATE users SET ClubID = ? WHERE UserID = ? ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, clubID);
            stm.setInt(2, userID);
            stm.executeUpdate();

        } catch (Exception e) {
        }
    }

    public List<Post> publicPost() {
        List<Post> listPost = new ArrayList<>();
        String sql = "SELECT * FROM post \n"
                + "	WHERE IsPublic = 'yes' \n"
               
                + "	LIMIT 3 ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listPost.add(new Post(rs.getInt("post_id"), rs.getInt("user_id"),
                        rs.getInt("club_id"), rs.getString("image_url"), rs.getString("IsPublic"),
                        rs.getString("description"), rs.getString("title"), rs.getString("post_at")));
            }
        } catch (Exception e) {
        }
        return listPost;

    }

    public Event getEventByID(int eventID) {
        String sql = "select * from events where EventID =  ? ";
        Event ev = new Event();

        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, eventID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                ev = new Event(rs.getInt("EventID"), rs.getInt("ClubID"), rs.getString("EventName"), rs.getString("Description"), rs.getString("EventDate"), rs.getInt("CreatedBy"), rs.getString("CreatedDate"), rs.getString("EventImageURL"), rs.getBoolean("Status"), rs.getBoolean("Type"));
            }
            return ev;
        } catch (SQLException e) {

        }

        return ev;

    }

    public void registerEvent(int eventID, int userID, int ClubID, String fullname, String email, String phone, String note) {
        String sql = "INSERT INTO EventRegistration ( Event_id,user_id,  club_id, fullname, email, phone, note) \n"
                + " VALUES (?,?, ?, ?, ?,?, ?) ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, eventID);
            stm.setInt(2, userID);
            stm.setInt(3, ClubID);
            stm.setString(4, fullname);

            stm.setString(5, email);
            stm.setString(6, phone);
            stm.setString(7, note);

            stm.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Club> getClubWithCategory(String category) {
        List<Club> list = new ArrayList<>();
        String sql = "select * from clubs where Categories = ? ";
        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, category);
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

    public List<Club> getCategory() {
        List<Club> list = new ArrayList<>();
        String sql = "SELECT DISTINCT Categories FROM clubs ";
        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Club(
                        rs.getString("Categories")
                ));
            }
        } catch (Exception e) {
        }
        return list;

    }

    public List<Club> getAllClubs() {

        List<Club> list = new ArrayList<>();
        String sql = "SELECT * FROM clubs ";
        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Club(rs.getInt("ClubID"), rs.getString("ClubName"),
                        rs.getString("Categories"), rs.getString("Description"),
                        rs.getString("Clubleader"),
                        rs.getString("ImageURL"),
                        rs.getString("ContactClub"),
                        rs.getString("Schedule"), rs.getString("CreatedDate")
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Post getPostByID(int postID) {
        String sql = "select * from post where post_id =  ? ";
        Post ev = new Post();

        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, postID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                ev = new Post(rs.getInt("post_id"), rs.getInt("user_id"),
                        rs.getInt("club_id"), rs.getString("image_url"), rs.getString("IsPublic"),
                        rs.getString("description"), rs.getString("title"), rs.getString("post_at"));
            }

            return ev;
        } catch (SQLException e) {

        }

        return ev;

    }

    public boolean checkRegisExist(int userid, int clubID) {
        boolean kaka;
        String sql = "select * from userclubs where UserID = ? and ClubID = ? ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, userid);
            stm.setInt(2, clubID);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                return true;
            }

        } catch (Exception e) {
        }

        return false;

    }

    public List<Post> filterPosts(String keyword, String category, String club) {
        List<Post> listPost = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM post WHERE IsPublic = 'Yes'");

        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND (title LIKE ? OR description LIKE ?)");
        }
        if (category != null && !category.trim().isEmpty()) {
            sql.append(" AND club_id IN (SELECT ClubID FROM Clubs WHERE Categories = ?)");
        }
        if (club != null && !club.trim().isEmpty()) {
            sql.append(" AND club_id IN (SELECT ClubID FROM Clubs WHERE ClubName = ?)");
        }

        try (Connection con = getConnection(); PreparedStatement stm = con.prepareStatement(sql.toString())) {

            int index = 1;
            if (keyword != null && !keyword.trim().isEmpty()) {
                stm.setString(index++, "%" + keyword + "%");
                stm.setString(index++, "%" + keyword + "%");
            }
            if (category != null && !category.trim().isEmpty()) {
                stm.setString(index++, category);
            }
            if (club != null && !club.trim().isEmpty()) {
                stm.setString(index++, club);
            }

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    listPost.add(new Post(rs.getInt("post_id"), rs.getInt("user_id"),
                            rs.getInt("club_id"), rs.getString("image_url"), rs.getString("IsPublic"),
                            rs.getString("description"), rs.getString("title"), rs.getString("post_at")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listPost;
    }

    public boolean checkeventExist(int eventID, int userid, int clubID) {
        boolean kaka;
        String sql = "select * from eventregistration where Event_id = ? and user_id = ? and club_id = ?  ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, eventID);
            stm.setInt(2, userid);
            stm.setInt(3, clubID);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                return true;
            }

        } catch (Exception e) {
        }

        return false;

    }

}
