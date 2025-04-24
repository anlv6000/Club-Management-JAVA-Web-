/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Event;
import database.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.EventRegistration;
import Model.UserClub;
import java.util.Arrays;

/**
 *
 * @author ADMIN
 */
public class EventRegistrationDAO extends DBContext {

    PreparedStatement ps;
    ResultSet rs;

    public List<EventRegistration> getRegistrationsByUserId(int userId) throws SQLException {
        List<EventRegistration> list = new ArrayList<>();

        String checkQuery = "SELECT EventID FROM Events WHERE EventLeaderId = ?";

        try (PreparedStatement psCheck = getConnection().prepareStatement(checkQuery)) {
            psCheck.setInt(1, userId);
            ResultSet rsCheck = psCheck.executeQuery();

            while (rsCheck.next()) {
                int eventId = rsCheck.getInt("EventID");

                // Nếu là EventLeader thì lấy danh sách đăng ký của sự kiện đódí
                String query = " SELECT er.*, u.ImageURL "
                        + "FROM EventRegistration er "
                        + "JOIN Users u ON er.user_id = u.UserID "
                        + "WHERE er.Event_id = ?";

                try (PreparedStatement ps = getConnection().prepareStatement(query)) {
                    ps.setInt(1, eventId);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        EventRegistration registration = new EventRegistration(
                                rs.getString("ImageURL"),
                                rs.getInt("id"),
                                rs.getInt("Event_id"),
                                rs.getInt("user_id"),
                                rs.getInt("club_id"), // cho phép club_id = null (nếu người đăng ký chưa tham gia câu lạc bộ)
                                rs.getString("fullname"),
                                rs.getString("email"),
                                rs.getString("phone"),
                                rs.getString("note"),
                                rs.getString("status")
                        );
                        list.add(registration);
                    }
                }
            }
        }
        return list;
    }

    public List<EventRegistration> getRegistrationsByEventId(int eventId) throws SQLException {
        List<EventRegistration> list = new ArrayList<>();
        Connection conn = getConnection();

        // Trường hợp 1: Lấy danh sách đăng ký có user_id KHÁC NULL
        String queryWithUser = "SELECT er.*, u.ImageURL, u.email AS user_email "
                + "FROM EventRegistration er "
                + "JOIN Users u ON er.user_id = u.UserID "
                + "WHERE er.Event_id = ? AND er.user_id IS NOT NULL";

        try (PreparedStatement ps = conn.prepareStatement(queryWithUser)) {
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EventRegistration registration = new EventRegistration(
                        rs.getString("ImageURL"),
                        rs.getInt("id"),
                        rs.getInt("Event_id"),
                        rs.getInt("user_id"),
                        rs.getInt("club_id"),
                        rs.getString("fullname"),
                        rs.getString("user_email"), // Lấy email từ Users
                        rs.getString("phone"),
                        rs.getString("note"),
                        rs.getString("status")
                );
                list.add(registration);
            }
        }

        // Trường hợp 2: Lấy danh sách đăng ký có user_id = NULL (khách)
        String queryWithoutUser = "SELECT * FROM EventRegistration WHERE Event_id = ? AND user_id IS NULL";

        try (PreparedStatement ps = conn.prepareStatement(queryWithoutUser)) {
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EventRegistration registration = new EventRegistration(
                        null, // Không có ảnh
                        rs.getInt("id"),
                        rs.getInt("Event_id"),
                        0, // user_id = 0 (hoặc có thể để null)
                        rs.getInt("club_id"),
                        rs.getString("fullname"),
                        rs.getString("email"), // Lấy email từ EventRegistration
                        rs.getString("phone"),
                        rs.getString("note"),
                        rs.getString("status")
                );
                list.add(registration);
            }
        }

        return list;
    }

    public int getEventIdByUserId(int userId) throws SQLException {
        String sql = "SELECT Event_id FROM fEventRegistration WHERE user_id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId); // Gắn userId vào câu truy vấn
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Event_id"); // Trả về eventId nếu có
                }
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy eventId
    }

    public int getEventIdByEventLeaderId(int eventLeaderId) throws SQLException {
        String sql = "SELECT EventID FROM Events WHERE EventLeaderId = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, eventLeaderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("EventID");
                }
            }
        }
        return -1; // Không tìm thấy
    }

    public List<EventRegistration> searchMembers(String role, String status, String keyword, int eventId) throws SQLException {
        List<EventRegistration> members = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT u.UserID, u.Username, u.Email, er.phone, u.ImageURL, er.Fullname, er.Status, ur.role, er.Event_id, er.club_id, er.note "
                + "FROM Users u "
                + "JOIN EventRegistration er ON u.UserID = er.user_id "
                + "JOIN user_role ur ON u.RoleID = ur.RoleID " // Kết nối với bảng user_role
                + "WHERE er.Event_id = ? AND er.status IN ('Registered', 'Reject', 'Active', 'Inactive')");

        // Thêm điều kiện lọc theo role và status
        if (role != null && !role.isEmpty()) {
            sql.append(" AND ur.role = ?");
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" AND er.Status = ?");
        }
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND (er.fullname LIKE ?)");
        }

        try (PreparedStatement ps = getConnection().prepareStatement(sql.toString())) {
            int index = 1;

            // Thêm eventId vào điều kiện lọc
            ps.setInt(index++, eventId);

            // Set các tham số lọc còn lại
            if (role != null && !role.isEmpty()) {
                ps.setString(index++, role);
            }
            if (status != null && !status.isEmpty()) {
                ps.setString(index++, status);
            }
            if (keyword != null && !keyword.isEmpty()) {
                ps.setString(index++, "%" + keyword + "%");
            }

            // Thực thi truy vấn
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    members.add(new EventRegistration(
                            rs.getString("ImageURL"),
                            rs.getInt("UserID"),
                            rs.getInt("Event_id"),
                            rs.getInt("UserID"),
                            rs.getInt("club_id"),
                            rs.getString("Fullname"),
                            rs.getString("Email"),
                            rs.getString("phone"),
                            rs.getString("note"),
                            rs.getString("Status")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Có thể thay bằng log nếu cần
        }

        return members;
    }

    public String getEventNameById(int eventId) throws SQLException {
        String eventName = null;
        String sql = "SELECT EventName FROM Events WHERE EventID = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, eventId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    eventName = rs.getString("EventName");
                }
            }
        }
        return eventName;
    }

    public List<Event> getEventsByEventLeaderId(int eventLeaderId) throws SQLException {
        List<Event> events = new ArrayList<>();
        String query = "SELECT EventID, EventName FROM Events WHERE EventLeaderId = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, eventLeaderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Event event = new Event(
                        rs.getInt("EventID"),
                        rs.getString("EventName")
                );
                events.add(event);
            }
        }
        return events;
    }

    public List<String> getRoles() throws SQLException {
        List<String> roles = new ArrayList<>();
        String sql = "SELECT DISTINCT ur.role "
                + "FROM user_role ur "
                + "JOIN UserClubs uc ON ur.RoleID = uc.RoleID "
                + "WHERE ur.role NOT IN ('Admin', 'EventLeader', 'ProjectLeader')"; // Loại bỏ các role không cần

        try (PreparedStatement ps = getConnection().prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                roles.add(rs.getString("role"));
            }
        }
        return roles;
    }

    public boolean updateRegistrationStatusById(int registrationId, String status) throws SQLException {
        String query = "UPDATE EventRegistration SET Status = ? WHERE id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, registrationId);

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    public List<String> getStatuses() throws SQLException {
        List<String> statuses = new ArrayList<>();
        String sql = "SELECT COLUMN_TYPE "
                + "FROM INFORMATION_SCHEMA.COLUMNS "
                + "WHERE TABLE_NAME = 'EventRegistration' "
                + "AND COLUMN_NAME = 'Status'";

        try (PreparedStatement ps = getConnection().prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String enumValues = rs.getString("COLUMN_TYPE");
                // Lọc lấy giá trị trong ENUM ('value1','value2',...)
                enumValues = enumValues.substring(enumValues.indexOf("(") + 1, enumValues.indexOf(")"));
                String[] values = enumValues.replace("'", "").split(",");
                statuses.addAll(Arrays.asList(values));
            }
        }
        return statuses;
    }

    /*
public static void main(String[] args) {
    EventRegistrationDAO dao = new EventRegistrationDAO();
    int userId = 9; // Thay bằng userId thực tế để test

    try {
        List<EventRegistration> registrations = dao.getRegistrationsByUserId(userId);

        if (registrations.isEmpty()) {
            System.out.println("Không có đăng ký nào được tìm thấy cho userId: " + userId);
        } else {
            System.out.println("Danh sách đăng ký của userId " + userId + ":");
            for (EventRegistration reg : registrations) {
                System.out.println("ID: " + reg.getId());
                System.out.println("Event ID: " + reg.getEventId());
                System.out.println("User ID: " + reg.getUserId());
                System.out.println("Club ID: " + reg.getClubId());
                System.out.println("Full Name: " + reg.getFullName());
                System.out.println("Email: " + reg.getEmail());
                System.out.println("Phone: " + reg.getPhone());
                System.out.println("Note: " + reg.getNote());
                System.out.println("Status: " + reg.getStatus());
                System.out.println("-----------------------------");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}*/
    public int getEventIdByClubId(int clubId) throws SQLException {
        int eventId = -1;
        String sql = "SELECT EventID FROM Events WHERE ClubID = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, clubId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                eventId = rs.getInt("EventID");
            }
        }
        return eventId;
    }
    // ============================== MAIN TEST ==============================

    /* public static void main(String[] args) {
        // Khởi tạo DAO
        EventRegistrationDAO dao = new EventRegistrationDAO();

        // ID của sự kiện cần kiểm tra
        int eventId = 4;
        int userId = 3;

        try {
            // Gọi phương thức để lấy danh sách đăng ký
            List<EventRegistration> registrations = dao.getRegistrationsByEventId(eventId, userId);

            // Kiểm tra nếu không có đăng ký nào
            if (registrations.isEmpty()) {
                System.out.println("Không có đăng ký nào cho sự kiện ID: " + eventId);
            } else {
                System.out.println("Danh sách đăng ký cho sự kiện ID: " + eventId);
                for (EventRegistration registration : registrations) {
                    // In thông tin từng đăng ký ra console
                    System.out.println("ID: " + registration.getId());
                    System.out.println("Event ID: " + registration.getEventId());
                    System.out.println("User ID: " + registration.getUserId());
                    System.out.println("Club ID: " + registration.getClubId());
                    System.out.println("Fullname: " + registration.getFullName());
                    System.out.println("Email: " + registration.getEmail());
                    System.out.println("Phone: " + registration.getPhone());
                    System.out.println("Note: " + registration.getNote());
                    System.out.println("Status: " + registration.getStatus());
                    System.out.println("-------------------------------------");
                }
            }
        } catch (SQLException e) {
            // Xử lý lỗi khi truy vấn hoặc kết nối thất bại
            System.out.println("Lỗi khi lấy dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }*/
    // ============================== MAIN TEST ==============================
    public EventRegistration getRegistrationDetailsById(int registrationId) throws SQLException {
        String query = "SELECT er.*, u.ImageURL, u.Email AS user_email "
                + "FROM EventRegistration er "
                + "LEFT JOIN Users u ON er.user_id = u.UserID "
                + "WHERE er.id = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, registrationId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new EventRegistration(
                        rs.getString("ImageURL"),
                        rs.getInt("id"),
                        rs.getInt("event_id"),
                        rs.getInt("user_id"),
                        rs.getInt("club_id"),
                        rs.getString("fullname"),
                        rs.getString("user_email") != null ? rs.getString("user_email") : rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("note"),
                        rs.getString("status")
                );
            }
        }
        return null;
    }

    public EventRegistration getRegistrationDetails(int userId) throws SQLException {
        Connection conn = getConnection();

        // Trường hợp 1: user_id KHÁC NULL
        if (userId != 0) {
            String queryWithUser = "SELECT er.*, u.ImageURL, u.email AS user_email "
                    + "FROM EventRegistration er "
                    + "JOIN Users u ON er.user_id = u.UserID "
                    + "WHERE er.user_id = ?";

            try (PreparedStatement ps = conn.prepareStatement(queryWithUser)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    return new EventRegistration(
                            rs.getString("ImageURL"),
                            rs.getInt("id"),
                            rs.getInt("Event_id"),
                            rs.getInt("user_id"),
                            rs.getInt("club_id"),
                            rs.getString("fullname"),
                            rs.getString("user_email"), // từ bảng Users
                            rs.getString("phone"),
                            rs.getString("note"),
                            rs.getString("status")
                    );
                }
            }
        }

        // Trường hợp 2: user_id = NULL (guest)
        String queryGuest = "SELECT * FROM EventRegistration WHERE user_id IS NULL";

        try (PreparedStatement ps = conn.prepareStatement(queryGuest)) {
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new EventRegistration(
                        null, // Không có ảnh
                        rs.getInt("id"),
                        rs.getInt("Event_id"),
                        0, // user_id = 0 cho khách
                        rs.getInt("club_id"),
                        rs.getString("fullname"),
                        rs.getString("email"), // email nằm trong EventRegistration
                        rs.getString("phone"),
                        rs.getString("note"),
                        rs.getString("status")
                );
            }
        }

        return null;
    }

    public static void main(String[] args) {
        EventRegistrationDAO dao = new EventRegistrationDAO();

        try {
            int userId = 3; // Thay bằng userId thực tế trong database
            EventRegistration registration = dao.getRegistrationDetails(userId);

            if (registration != null) {
                System.out.println("ID: " + registration.getId());
                System.out.println("Event ID: " + registration.getEventId());
                System.out.println("User ID: " + registration.getUserId());
                System.out.println("Club ID: " + registration.getClubId());
                System.out.println("Fullname: " + registration.getFullName());
                System.out.println("Email: " + registration.getEmail());
                System.out.println("Phone: " + registration.getPhone());
                System.out.println("Note: " + registration.getNote());
                System.out.println("Status: " + registration.getStatus());
                System.out.println("Image URL: " + registration.getImageURL());
            } else {
                System.out.println("No registration details found for userId = " + userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public static  void main(String[] args) {
    // Khởi tạo DAO
    EventRegistrationDAO dao = new EventRegistrationDAO();

    // ID của sự kiện cần kiểm tra
    int eventId = 1; 
    int userId = 7; // ID của người đã đăng ký sự kiện
    int currentUserId = 2; // ID của user hiện tại (người kiểm tra)

    try {
        // Gọi phương thức để lấy chi tiết đăng ký
        EventRegistration registration = dao.getRegistrationDetails(eventId, userId, currentUserId);

        if (registration != null) {
            // In thông tin chi tiết nếu tồn tại
            System.out.println("=== Chi tiết đơn đăng ký ===");
            System.out.println("ID: " + registration.getId());
            System.out.println("Event ID: " + registration.getEventId());
            System.out.println("User ID: " + registration.getUserId());
            System.out.println("Club ID: " + registration.getClubId());
            System.out.println("Fullname: " + registration.getFullName());
            System.out.println("Email: " + registration.getEmail());
            System.out.println("Phone: " + registration.getPhone());
            System.out.println("Note: " + registration.getNote());
            System.out.println("Status: " + registration.getStatus());
        } else {
            System.out.println("❌ Không tìm thấy đơn đăng ký hoặc bạn không có quyền xem.");
        }
    } catch (SQLException e) {
        // Xử lý lỗi khi truy vấn hoặc kết nối thất bại
        System.out.println("Lỗi khi lấy dữ liệu: " + e.getMessage());
        e.printStackTrace();
    }
}*/

    /* public static void main(String[] args) {
    // Khởi tạo DAO
    EventRegistrationDAO dao = new EventRegistrationDAO();

    // ID của sự kiện và người dùng cần cập nhật
    int eventId = 1;
    int userId = 3;
    String status = "Active";

    try {
        // Gọi phương thức để cập nhật trạng thái đăng ký
        boolean isUpdated = dao.updateRegistrationStatus(eventId, userId, status);

        // Kiểm tra và in kết quả ra console
        if (isUpdated) {
            System.out.println("✅ Cập nhật trạng thái thành công cho sự kiện ID: " + eventId + ", User ID: " + userId);
        } else {
            System.out.println("❌ Không tìm thấy bản ghi để cập nhật cho sự kiện ID: " + eventId + ", User ID: " + userId);
        }
    } catch (SQLException e) {
        // Xử lý lỗi khi truy vấn hoặc kết nối thất bại
        System.out.println("❌ Lỗi khi cập nhật trạng thái: " + e.getMessage());
        e.printStackTrace();
    }
}*/

}
