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
import model.Messages;
import Model.Userrole;
import Model.Comment;
import java.util.Arrays;
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
        String sql = "INSERT INTO users (Username, Password, Email,Fullname, RoleID ) VALUES (?, ?, ?,?, ?)";
        try {
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setString(3, email);
            stm.setString(4, fullname);
            stm.setInt(5, 1);

            stm.executeUpdate();
            System.out.println("Thêm tài khoản thành công!");
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm tài khoản: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static List<User> selectAllUsers() throws SQLException {
        String sql = "SELECT UserID, Username FROM Users";

        List<User> users = new ArrayList<>();
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("UserID")); // Đảm bảo UserID được gán vào `id`
                user.setUserName(rs.getString("Username"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi truy vấn tất cả users: " + e.getMessage());
            throw e;
        }
        return users;
    }

    public List<User> getClubMembersByClubId(int clubId) {
        List<User> members = new ArrayList<>();
        String sql = "SELECT u.UserID, u.Username "
                + "FROM UserClubs uc "
                + "JOIN Users u ON uc.UserID = u.UserID "
                + "WHERE uc.ClubID = ?";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clubId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("UserID")); // Mapping từ cột `UserID` trong bảng cơ sở dữ liệu sang thuộc tính `id`
                user.setUserName(rs.getString("Username"));
                members.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT UserID, Username FROM Users";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                users.add(new User(rs.getInt("UserID"), rs.getString("Username")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> listUser() {
        List<User> listU = new ArrayList<>();
        String sql = "SELECT DISTINCT u.* \n"
                + "FROM users u\n"
                + "JOIN events e ON u.userID = e.EventLeaderId ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listU.add(new User(rs.getInt("UserID"), rs.getString("Username"), rs.getString("Email"), rs.getString("Password")));
            }

        } catch (Exception e) {
        }
        return listU;

    }

    public List<GoogleAccount> listUsert() {
        List<GoogleAccount> listU = new ArrayList<>();
        String sql = "SELECT DISTINCT u.* \n"
                + "FROM users u\n"
                + "JOIN events e ON u.userID = e.EventLeaderId ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listU.add(new GoogleAccount(rs.getString("UserID"), rs.getString("Email"), rs.getString("Username"), rs.getString("RoleID"), rs.getString("Fullname"), rs.getString("Status")));
            }

        } catch (Exception e) {
        }
        return listU;

    }

    public List<GoogleAccount> listuerr() {
        List<GoogleAccount> listU = new ArrayList<>();
        String sql = "SELECT DISTINCT u.* \n"
                + "FROM users u \n"
                + "JOIN project p ON u.userID = p.Leader_ID  ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listU.add(new GoogleAccount(rs.getString("UserID"), rs.getString("Email"), rs.getString("Username"), rs.getString("RoleID"), rs.getString("Fullname"), rs.getString("Status")));
            }

        } catch (Exception e) {
        }
        return listU;

    }

    public List<User> listuer() {
        List<User> listU = new ArrayList<>();
        String sql = "SELECT DISTINCT u.* \n"
                + "FROM users u \n"
                + "JOIN project p ON u.userID = p.Leader_ID  ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listU.add(new User(rs.getInt("UserID"), rs.getString("Username"), rs.getString("Email"), rs.getString("Password")));
            }

        } catch (Exception e) {
        }
        return listU;

    }

    public List<Event> getAllEvent() {

        List<Event> list = new ArrayList<>();
        String sql = "SELECT * FROM events ";
        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Event(rs.getInt("EventID"), rs.getInt("ClubID"), rs.getString("EventName"), rs.getString("Description"), rs.getString("EventDate"), rs.getInt("CreatedBy"), rs.getString("CreatedDate"), rs.getString("EventImageURL"), rs.getBoolean("Status"), rs.getBoolean("Type")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Event> geteventByclubID(String name) {
        List<Event> c = new ArrayList<>();
        String sql = " select * from events where ClubName = ? ";
        try {
            Connection con = getConnection();

            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                c.add(new Event(rs.getInt("EventID"),
                        rs.getString("EventName"), rs.getString("EventImageurl"), rs.getBoolean("Status"),
                        rs.getBoolean("Type"),
                        rs.getString("ClubName"), rs.getString("EventYear"), rs.getString("EventMonth"),
                        rs.getString("EventDay"), rs.getString("EndYear"), rs.getString("EndMonth"),
                        rs.getString("EndDay"), rs.getInt("ParticipantCount")));
            }

        } catch (Exception e) {
        }
        return c;

    }

    public List<Club> getClub() {
        List<Club> list = new ArrayList<>();
        String sql = " SELECT * FROM clubs  ";
        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Club(rs.getInt("ClubID"), rs.getString("ClubName"), rs.getString("Description"), rs.getString("IsPublic"), rs.getString("ImageURL")));
            }
        } catch (Exception e) {
        }
        return list;

    }

    public boolean checkfullnameExist(String fullname) {
        String query = "select * from users \n "
                + "where Fullname = ? \n ";
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
        String query = " select * from users \n"
                + " where Email = ? \n";
        try {
            stm = getConnection().prepareStatement(query);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;

    }

    public boolean checkAccountExist(String user) {
        String query = "select * from users  \n "
                + "   where  Username  =  ?  \n ";
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
        String query = " SELECT u.*, ur.role as UserType \n"
                + " FROM users u \n"
                + " LEFT JOIN user_role ur ON u.RoleID = ur.RoleID \n"
                + " WHERE u.Username = ? OR u.email = ?   ";
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
        String sql = "INSERT INTO Settings (SettingName, SettingType, SettingValue, Priority, Status, RoleID, Description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            for (Setting setting : settings) {
                stm.setString(1, setting.getName());
                stm.setString(2, setting.getType());
                stm.setString(3, setting.getValue());
                stm.setInt(4, setting.getPriority());
                stm.setString(5, setting.getStatus());
                stm.setInt(6, setting.getRoleId());
                stm.setString(7, setting.getDescription());
                stm.addBatch();
            }
            stm.executeBatch();
        }
    }

    public void updateSettings(List<Setting> settings) throws SQLException {
        String sql = "UPDATE Settings SET SettingName = ?, SettingType = ?, SettingValue = ?, Priority = ?, Status = ?, RoleID = ?, Description = ? WHERE SettingID = ?";
        try (Connection con = getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            for (Setting setting : settings) {
                stm.setString(1, setting.getName());
                stm.setString(2, setting.getType());
                stm.setString(3, setting.getValue());
                stm.setInt(4, setting.getPriority());
                stm.setString(5, setting.getStatus());
                stm.setInt(6, setting.getRoleId());
                stm.setString(7, setting.getDescription());
                stm.setInt(8, setting.getId());
                stm.addBatch();
            }
            stm.executeBatch();
        }
    }

    public Setting getSettingById(int id) throws SQLException {
        String sql = "SELECT * FROM Settings WHERE SettingID = ?";
        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Setting(
                            rs.getInt("SettingID"),
                            rs.getString("SettingName"),
                            rs.getString("SettingType"),
                            rs.getString("SettingValue"),
                            rs.getInt("Priority"),
                            rs.getString("Status"),
                            rs.getInt("RoleID"), // Ensure RoleID is fetched correctly
                            rs.getString("Description")
                    );
                }
            }
        }
        return null; // Return null if no matching Setting is found
    }

    public void updateStatus(int id, String newStatus) throws SQLException {
        String sql = "UPDATE Settings SET Status = ? WHERE SettingID = ?";
        try (Connection con = getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, newStatus);
            stm.setInt(2, id);
            stm.executeUpdate();
        }
    }

//
    public User UserType(String username) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT  \n "
                + "    users.*, \n "
                + "    user_role.role AS UserType \n "
                + "FROM users \n "
                + "JOIN user_role ON users.RoleID = user_role.RoleID  \n "
                + "WHERE users.Username = ? ";
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
        String query = "INSERT INTO users (google_id, Username, Email ,Password , RoleID , Fullname ) VALUES (?, ?, ?, ?, ?, ?) ";
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, googleAccount.getId());
            pstmt.setString(2, googleAccount.getName());
            pstmt.setString(3, email);
            pstmt.setString(4, email);
            pstmt.setInt(5, 1);
            pstmt.setString(6, googleAccount.getName());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public GoogleAccount loginggg(String email) {
        GoogleAccount a = new GoogleAccount();
        String query = " SELECT  \n"
                + "    users.*,   \n"
                + "    user_role.role AS UserType  \n"
                + "  FROM users \n "
                + "  JOIN user_role ON users.RoleID = user_role.RoleID \n"
                + "  WHERE users.Email = ? ";
        try {
            stm = getConnection().prepareStatement(query);
            stm.setString(1, email);
            rs = stm.executeQuery();

            if (rs.next()) {
                // Tạo đối tượng Account từ kết quả truy vấn
                a = new GoogleAccount(rs.getString("UserID"), rs.getString("Email"), rs.getString("Username"), rs.getString("UserType"), rs.getString("Fullname"), rs.getString("Status"));

                // Kiểm tra mật khẩu đã nhập với mật khẩu hash từ cơ sở dữ liệu
            }
            return a;
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra
        }
        return a; // Trả về null nếu không tìm thấy tài khoản
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
        String sql
                = "  UPDATE users  \n"
                + "   SET  Password = ?   \n"
                + " WHERE Email =    ?";
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
        // Lấy RoleID từ user_role dựa trên role (userType)
        String getRoleIdQuery = "SELECT RoleID FROM user_role WHERE role = ?";
        int roleId = -1;

        try (PreparedStatement ps = getConnection().prepareStatement(getRoleIdQuery)) {
            ps.setString(1, userType);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    roleId = rs.getInt("RoleID");
                } else {
                    System.out.println("Role not found!");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        // Nếu lấy được RoleID thì tiếp tục thêm vào Users
        String sql = "INSERT INTO Users (Username, Fullname, Password, Email, RoleID, Status, CreatedDate, LastLoginDate, ImageURL) "
                + "VALUES (?, ?, ?, ?, ?, ?, NOW(), NULL, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, fullname);
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
            ps.setString(3, hashedPassword);
            ps.setString(4, email);
            ps.setInt(5, roleId); // Truyền RoleID thay vì role
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
        String sql = "SELECT  \n"
                + "    users.*,  \n"
                + "    user_role.role AS UserType \n"
                + "FROM users \n"
                + "JOIN user_role ON users.RoleID = user_role.RoleID ";
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

    public List<String> getRoles() throws SQLException {
        List<String> roles = new ArrayList<>();
        String sql = "SELECT role FROM user_role";
        try (PreparedStatement ps = getConnection().prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                roles.add(rs.getString("role"));
            }
        }
        return roles;
    }

    public List<String> getStatuses() throws SQLException {
        List<String> statuses = new ArrayList<>();
        String sql = "SELECT COLUMN_TYPE "
                + "FROM INFORMATION_SCHEMA.COLUMNS "
                + "WHERE TABLE_NAME = 'Users' "
                + "AND COLUMN_NAME = 'Status'";

        try (PreparedStatement ps = getConnection().prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String enumValues = rs.getString("COLUMN_TYPE");
                // Loại bỏ phần 'enum(' và ')' rồi tách các giá trị
                enumValues = enumValues.replace("enum(", "").replace(")", "").replace("'", "");
                String[] values = enumValues.split(",");
                statuses.addAll(Arrays.asList(values));
            }
        }
        return statuses;
    }

    public User getAccountById(String accountId) {
        String sql = "SELECT  \n"
                + "    users.*, \n"
                + "    user_role.role AS UserType \n"
                + "FROM users \n"
                + "JOIN user_role ON users.RoleID = user_role.RoleID \n"
                + "WHERE users.UserID = ? ";
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
        String sql = "UPDATE Users SET Username = ?, Email = ?, RoleID = ?, Status = ?, Fullname = ?, note = ? WHERE UserID = ?";
        String checkExistSql = "SELECT COUNT(*) FROM UserClubs WHERE UserID = ?";
        String updateUsersSql = "UPDATE UserClubs SET RoleID = ? WHERE UserID = ?";

        Connection conn = getConnection();

        try {
            conn.setAutoCommit(false);

            //  Cập nhật trong bảng Users
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, account.getUserName());
                ps.setString(2, account.getEmail());
                ps.setInt(3, getRoleIdByRoleName(account.getRole())); // Lấy RoleID từ role
                ps.setString(4, account.getStatus());
                ps.setString(5, account.getFullname());
                ps.setString(6, account.getText());
                ps.setInt(7, account.getId());
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected <= 0) {
                    throw new SQLException("Không thể cập nhật bảng Users!");
                }
            }
//  Chỉ cập nhật trong UserClubs nếu UserID đã tồn tại
            try (PreparedStatement checkStmt = conn.prepareStatement(checkExistSql)) {
                checkStmt.setInt(1, account.getId());
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    try (PreparedStatement ps = conn.prepareStatement(updateUsersSql)) {
                        ps.setInt(1, getRoleIdByRoleName(account.getRole())); // Truyền RoleID thay vì role trực tiếp
                        ps.setInt(2, account.getId());
                        int rowsUpdated = ps.executeUpdate();
                        if (rowsUpdated <= 0) {
                            throw new SQLException("Không thể cập nhật bảng UserClubs!");
                        }
                    }
                }
            }

            conn.commit();
            System.out.println("Đã cập nhật thành công thông tin tài khoản.");
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // ✅ Rollback nếu có lỗi
                    System.err.println("Lỗi: Đã rollback do lỗi: " + e.getMessage());
                } catch (SQLException rollbackEx) {
                    System.err.println("Lỗi rollback: " + rollbackEx.getMessage());
                }
            }
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // ✅ Bật lại autoCommit
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private int getRoleIdByRoleName(String roleName) throws SQLException {
        String sql = "SELECT RoleID FROM user_role WHERE role = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, roleName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("RoleID");
            }
        }
        throw new SQLException("Không tìm thấy RoleID cho role: " + roleName);
    }

    public List<User> searchAccounts(String role, String status, String keyword) throws SQLException {
        List<User> accounts = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT  \n"
                + "    users.*,  \n"
                + "    user_role.role AS UserType \n"
                + "FROM users \n"
                + "JOIN user_role ON users.RoleID = user_role.RoleID  WHERE 1=1"); // Adjust table name if needed

        if (role != null && !role.isEmpty()) {
            sql.append(" AND user_role.role = ?");
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

    public boolean addEvent(int clubId, String eventName, String description, String eventDate, int createdBy, String createdDate, String eventImageURL, boolean type, boolean status, String eventTimeEnd) {
        String sql = "INSERT INTO Events (ClubID, EventName, Description, EventDate, CreatedBy, CreatedDate, EventImageURL, Status, Type ,endEventDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

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
            ps.setString(10, eventTimeEnd);
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
                club.setClubLeader(rs.getString("Clubleader"));
                club.setcontactClub(rs.getString("ContactClub"));
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
        String sql = "SELECT "
                + "e.EventID, e.EventName, e.Description, e.EventDate, e.CreatedDate, "
                + "e.EventImageURL, e.ClubID, e.CreatedBy, e.Status, e.Type,e.ParticipantCount ,e.endEventDate,e.EventLeaderId,  "
                + "c.ClubName, u.Username AS CreatedByUsername "
                + "FROM Events e "
                + "JOIN Clubs c ON e.ClubID = c.ClubID "
                + "LEFT JOIN Users u ON e.CreatedBy = u.UserID "
                + "WHERE e.EventID = ?";

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
                            rs.getString("CreatedByUsername"),
                            rs.getString("endEventDate"),
                            rs.getInt("ParticipantCount"),
                            rs.getInt("EventLeaderId")
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

    public boolean updateEvent(int eventId, int clubId, String eventName, String description, String eventDate, Integer createdBy, String createdDate, String eventImageURL, boolean eventStatus, boolean eventType, String endEventDate, int ParticipantCount, Integer EventLeaderId) {
        String sql = "UPDATE Events SET ClubID = ?, EventName = ?, Description = ?, EventDate = ?, "
                + "CreatedBy = ?, CreatedDate = ?, EventImageURL = ?, Type = ?, Status = ?,endEventDate = ?,ParticipantCount = ?,EventLeaderId= ? WHERE EventID = ?";

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
            ps.setBoolean(9, eventType);
            ps.setString(10, endEventDate);// Thêm Status
            ps.setInt(11, ParticipantCount);
            // xu ly truong hop EventLeaderID co the null 
            if (EventLeaderId != null) {
                ps.setInt(12, EventLeaderId);
            } else {
                ps.setNull(12, java.sql.Types.INTEGER);
            }
//            ps.setInt(12, EventLeaderId);
            ps.setInt(13, eventId); // EventID để xác định sự kiện cần cập nhật

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
                    Event event = new Event();
                    event.setId(rs.getInt("EventID"));
                    event.setClubId(rs.getInt("ClubID"));
                    event.setEventName(rs.getString("EventName"));
                    event.setDescription(rs.getString("Description"));
                    event.setEventDate(rs.getString("EventDate"));
                    event.setEndEventDate(rs.getString("EndEventDate"));
                    event.setCreatedBy(rs.getInt("CreatedBy"));
                    event.setCreatedDate(rs.getString("CreatedDate"));
                    event.setEventImageURL(rs.getString("EventImageURL"));
                    event.setStatus(rs.getBoolean("Status"));
                    event.setType(rs.getBoolean("Type"));
                    event.setParticipantcount(rs.getInt("ParticipantCount"));
                    event.setNameCLub(rs.getString("ClubName"));
                    event.setUserCreat(rs.getString("CreatedByUsername"));
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
        String sql = " SELECT \n"
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
        String sql = " SELECT *   \n"
                + " FROM events   \n "
                + "    \n"
                + "ORDER BY EventID DESC   \n"
                + "LIMIT 3 ";
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
        List<Event> events = eventDAO.getEventNameByPage(1, 5, "a");
        for (Event event : events) {
            System.out.println(event);
        }

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
                + " WHERE status = 0 \n"
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
        return null;

    }

    public void insertbyRegistered(int uid, int cid, String note, String phone) {
        String sql = " INSERT INTO UserClubs (`UserID`, `ClubID`, `note`, `phone`,RoleID  ) VALUES (?, ?, ?, ?, ?) ";

        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, uid);
            stm.setInt(2, cid);
            stm.setString(3, note);
            stm.setString(4, phone);
            stm.setInt(5, 1);

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
        String sql = " SELECT * FROM post \n"
                + "	WHERE status = 'Published' \n"
                + "	LIMIT 3 ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listPost.add(new Post(rs.getInt("post_id"), rs.getInt("user_id"),
                        rs.getInt("club_id"), rs.getString("image_url"), rs.getString("status"),
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
                ev = new Event(rs.getInt("EventID"), rs.getInt("ClubID"), rs.getString("EventName"), rs.getString("Description"), rs.getString("EventDate"), rs.getString("CreatedDate"), rs.getString("EventImageURL"), rs.getBoolean("Status"), rs.getBoolean("Type"), rs.getString("endEventDate"));
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

    public void registerEvent_2(int eventID, int ClubID, String fullname, String email, String phone, String note) {
        String sql = "INSERT INTO EventRegistration ( Event_id,  club_id, fullname, email, phone, note) \n"
                + " VALUES (?, ?, ?, ?,?, ?) ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, eventID);

            stm.setInt(2, ClubID);
            stm.setString(3, fullname);

            stm.setString(4, email);
            stm.setString(5, phone);
            stm.setString(6, note);

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
                        rs.getInt("club_id"), rs.getString("image_url"), rs.getString("status"),
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
        StringBuilder sql = new StringBuilder("SELECT * FROM post WHERE status = 'Published'");

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
                            rs.getInt("club_id"), rs.getString("image_url"), rs.getString("status"),
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

    public List<Post> getRecentPost(int ClubID) {
        List<Post> listPost = new ArrayList<>();
        String sql = "  SELECT * FROM post  \n "
                + "                	 WHERE status = 'Published' and club_id = ? \n"
                + " ORDER BY post_id DESC "
                + "                	 LIMIT 3  ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, ClubID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listPost.add(new Post(rs.getInt("post_id"), rs.getInt("user_id"),
                        rs.getInt("club_id"), rs.getString("image_url"), rs.getString("status"),
                        rs.getString("description"), rs.getString("title"), rs.getString("post_at")));
            }
        } catch (Exception e) {
        }
        return listPost;

    }

    public List<Club> getallClub() {
        List<Club> list = new ArrayList<>();
        String query = " select * from clubs where IsPublic = 'Yes'  ";
        try {
            stm = getConnection().prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Club(rs.getInt("ClubID"), rs.getString("ClubName"),
                        rs.getString("Categories"), rs.getString("Description"),
                        rs.getString("Clubleader"),
                        rs.getString("ImageURL"),
                        rs.getString("ContactClub"),
                        rs.getString("Schedule"), rs.getString("CreatedDate")));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<User> getTOP3poster() {
        List<User> listpost = new ArrayList<>();
        String sql = "SELECT \n"
                + "    u.UserID, \n"
                + "    u.Username, \n"
                + "    u.Password, \n"
                + "    u.ImageURL, \n"
                + "    u.UserType,  -- Thiếu dấu phẩy trước COUNT\n"
                + "    COUNT(p.post_id) AS post_count \n"
                + "FROM post p  \n"
                + "JOIN users u ON p.user_id = u.UserID  \n"
                + "GROUP BY u.UserID, u.Username, u.Password, u.ImageURL, u.UserType  \n"
                + "ORDER BY post_count DESC  \n"
                + "LIMIT 3;";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listpost.add(new User(rs.getString("Username"), rs.getString("Password"), rs.getString("ImageURL"), rs.getString("UserType")));
            }
        } catch (Exception e) {
        }
        return listpost;
    }

    public int checklateEventRegistration(int eventID) {
        int kaka = -99;
        String sql = " SELECT COUNT(*) AS isValid  \n"
                + " FROM events  \n"
                + " WHERE EventID = ?  \n"
                + " AND NOW() <= DATE_SUB(eventDate, INTERVAL 2 DAY)";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, eventID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                kaka = rs.getInt("isValid");
            }

            return kaka;
        } catch (Exception e) {
        }
        return kaka;
    }

    public boolean saveMessage(Messages message) {
        String sql = "INSERT INTO messages (sender_id, receiver_id, content) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, message.getSenderId());
            stmt.setInt(2, message.getReceiverId());
            stmt.setString(3, message.getContent());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Messages> getMessages(int userId1, int userId2) {
        List<Messages> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?) ORDER BY timestamp ASC";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, userId1);
            stmt.setInt(2, userId2);
            stmt.setInt(3, userId2);
            stmt.setInt(4, userId1);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                messages.add(new Messages(rs.getInt("sender_id"), rs.getInt("receiver_id"), rs.getString("content")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public boolean areFriends(int user1, int user2) {
        String sql = "SELECT * FROM friends WHERE ((user1_id = ? AND user2_id = ?) OR (user1_id = ? AND user2_id = ?)) AND status = 'accepted'";

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setInt(1, user1);
            stmt.setInt(2, user2);
            stmt.setInt(3, user2);
            stmt.setInt(4, user1);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Nếu có kết quả, nghĩa là đã là bạn bè
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Event> geteventAll() {
        List<Event> listE = new ArrayList<>();
        String sql = " SELECT e.EventID, e.EventName, e.ParticipantCount,   \n"
                + "        YEAR(e.EventDate) AS EventYear,    \n"
                + "        MONTH(e.EventDate) AS EventMonth,    \n"
                + "        DAY(e.EventDate) AS EventDay,    \n"
                + "        YEAR(e.endEventDate) AS EndYear,    \n"
                + "        MONTH(e.endEventDate) AS EndMonth,     \n"
                + "        DAY(e.endEventDate) AS EndDay,    \n"
                + "        e.EventImageURL, c.ClubName,   \n"
                + "        e.Status, e.Type \n"
                + " FROM events e \n"
                + "INNER JOIN clubs c ON e.ClubID = c.ClubID;  ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listE.add(new Event(rs.getInt("EventID"),
                        rs.getString("EventName"), rs.getString("EventImageurl"), rs.getBoolean("Status"),
                        rs.getBoolean("Type"),
                        rs.getString("ClubName"), rs.getString("EventYear"), rs.getString("EventMonth"),
                        rs.getString("EventDay"), rs.getString("EndYear"), rs.getString("EndMonth"),
                        rs.getString("EndDay"), rs.getInt("ParticipantCount")));
            }
        } catch (Exception e) {
        }
        return listE;

    }

    public Post listPostkaka(int postID) {
        String sql = " SELECT  \n"
                + "   p.*,  \n"
                + "   c.Categories,   \n"
                + "   u.Username     \n"
                + "FROM post p\n"
                + "JOIN clubs c ON p.club_id = c.ClubID \n"
                + "JOIN users u ON p.user_id = u.UserID   \n"
                + "WHERE p.post_id = ?  ";
        Post p = new Post();
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, postID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                p = new Post(rs.getString("Categories"), rs.getString("Username"));
            }
            return p;
        } catch (Exception e) {
        }
        return p;
    }

    public void comment(String name, String email, String cmt) {
        String sql = " INSERT INTO comments (author, email, comment) VALUES (?, ?, ?) ";
        try {
            Connection con = getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, email);

            stm.setString(3, cmt);
            stm.executeUpdate();

        } catch (Exception e) {
        }
    }

    public List<Comment> listComment() {
        List<Comment> comments = new ArrayList<>();
        String sql = " SELECT author, comment, created_at FROM comments ORDER BY created_at DESC ";
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                comments.add(new Comment(rs.getString("author"), rs.getString("comment"), rs.getString("created_at")));
            }
        } catch (Exception e) {
        }
        return comments;
    }

    public List<Event> getAllEventActive() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT EventID, EventName FROM events WHERE status = 1"; // Giả sử bảng events có cột eventID và eventName

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                events.add(new Event(rs.getInt("eventID"), rs.getString("eventName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;

    }

    public class EventDAO extends DBContext {

        public List<Event> getAllEvents() {
            List<Event> events = new ArrayList<>();
            String sql = "SELECT EventID, EventName FROM events"; // Giả sử bảng events có cột eventID và eventName

            try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    events.add(new Event(rs.getInt("eventID"), rs.getString("eventName")));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return events;
        }
    }

    public List<Club> getClubbyname(String nameclub) {
        List<Club> list = new ArrayList<>();
        String sql = " SELECT * FROM clubs WHERE ClubName LIKE ?  ";
        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, "%" + nameclub + "%");
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

    public int getTotalActiveRegistrationsByEventId(int eventId) {
        int total = 0;
        String sql = "SELECT COUNT(*) AS total FROM eventregistration WHERE Status = 'Active' AND Event_id = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Thiết lập giá trị Event_id cần đếm
            ps.setInt(1, eventId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi đếm số bản ghi Active theo Event_id: " + e.getMessage());
        }

        return total;
    }

    public boolean updateEventParticipantCount(int eventId, int count) {
        String sql = "UPDATE events SET ParticipantCount = ? WHERE EventID = ?";
        boolean isUpdated = false;

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, count);     // Giá trị mới của ParticipantCount
            ps.setInt(2, eventId);   // ID của sự kiện cần cập nhật

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                isUpdated = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi cập nhật ParticipantCount: " + e.getMessage());
        }

        return isUpdated;
    }
}
