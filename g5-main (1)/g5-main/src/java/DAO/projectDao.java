/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.dao;
import Model.Account;
import Model.Club;
import Model.Event;
import database.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import Model.Project;
import Model.User;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author User
 */
public class projectDao extends DBContext {

    public boolean checkCodeExist(String code) throws SQLException {
        String query = "SELECT 1 FROM Project WHERE Code = ?";
        try (Connection connection = new DBContext().getConnection(); PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, code);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Returns true if a record is found
            }
        }
    }

    public List<User> getMembersByClubId(int clubId) {
        List<User> members = new ArrayList<>();
        String query = "SELECT u.UserID, u.Username "
                + "FROM UserClubs uc "
                + "JOIN Users u ON uc.UserID = u.UserID "
                + "WHERE uc.ClubID = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clubId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("UserID"));
                user.setUserName(rs.getString("Username"));
                members.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public boolean isValidUserId(int userId) {
        String query = "SELECT UserID FROM Users WHERE UserID = ? AND Status = 'Active'";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId); // Set the userId parameter in the query

            try (ResultSet rs = stmt.executeQuery()) {
                // If a record exists, the userId is valid
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return false; // Return false if any issues occurred
    }

    public List<User> getMembersByClubID(int clubId) {
        List<User> members = new ArrayList<>();
        String sql = "SELECT DISTINCT uc.UserID, u.Username "
                + "FROM UserClubs uc "
                + "JOIN Users u ON uc.UserID = u.UserID "
                + "WHERE uc.ClubID = ? AND uc.Status = 'Active'";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clubId); // Bind ClubID parameter
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("UserID")); // Set UserID
                    user.setUserName(rs.getString("Username")); // Set Username
                    members.add(user); // Add member to list
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Debugging log
        }
        return members;
    }

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT p.ProjectID, p.Name, p.Code, p.From_Date, p.To_Date, "
                + "p.Leader_ID, p.Status,p.Description,p.ClubID, u.Username AS NameCreater "
                + "FROM Project p "
                + "JOIN Users u ON p.Leader_ID = u.UserID "
                + // Lấy Username từ bảng Users
                "ORDER BY p.From_Date DESC";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(rs.getInt("ProjectID"));
                project.setName(rs.getString("Name"));
                project.setCode(rs.getString("Code"));
                project.setFromDate(rs.getDate("From_Date"));
                project.setToDate(rs.getDate("To_Date"));
                project.setLeader_ID(rs.getInt("Leader_ID"));
                project.setStatus(rs.getBoolean("Status"));
                project.setDescription(rs.getString("Description"));
                project.setNameCreater(rs.getString("NameCreater"));
                project.setClubID(rs.getInt("ClubID")); // Gán Username vào nameCreater
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public List<Project> getProjectsByPageAndClubIDs(int page, int pageSize, List<Integer> clubIDs) {
        List<Project> projects = new ArrayList<>();

        if (clubIDs.isEmpty()) {
            return projects; // Trả về danh sách rỗng nếu không có ClubID nào
        }

        String sql = "SELECT p.ProjectID, p.Name, p.Code, p.From_Date, p.To_Date, "
                + "p.Leader_ID, p.Status, p.Description, p.ClubID, "
                + "u.Username AS NameCreater "
                + "FROM Project p "
                + "JOIN Users u ON p.Leader_ID = u.UserID "
                + "WHERE p.ClubID IN ("
                + clubIDs.stream().map(id -> "?").collect(Collectors.joining(",")) + ") "
                + "ORDER BY p.From_Date DESC "
                + "LIMIT ? OFFSET ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            int index = 1;

            // Gán danh sách ClubIDs vào câu lệnh SQL
            for (int id : clubIDs) {
                stm.setInt(index++, id);
            }

            // Gán pageSize và offset
            stm.setInt(index++, pageSize);
            stm.setInt(index, (page - 1) * pageSize);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project();
                    project.setProjectID(rs.getInt("ProjectID"));
                    project.setName(rs.getString("Name"));
                    project.setCode(rs.getString("Code"));
                    project.setFromDate(rs.getDate("From_Date"));
                    project.setToDate(rs.getDate("To_Date"));
                    project.setLeader_ID(rs.getInt("Leader_ID"));
                    project.setStatus(rs.getBoolean("Status"));
                    project.setDescription(rs.getString("Description"));
                    project.setNameCreater(rs.getString("NameCreater"));
                    project.setClubID(rs.getInt("ClubID"));

                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

    public List<Event> getEventByPage(int page, int pageSize) {
        List<Event> eventList = new ArrayList<>();
        String sql = "SELECT e.EventID, e.ClubID, e.EventName, e.Description, e.EventDate, e.EndEventDate, "
                + "e.CreatedBy, e.CreatedDate, e.EventImageURL, e.Status, e.Type, e.ParticipantCount, "
                + "c.ClubName, u.Username AS CreatedByUsername "
                + "FROM Events e "
                + "JOIN Clubs c ON e.ClubID = c.ClubID "
                + "LEFT JOIN Users u ON e.CreatedBy = u.UserID "
                + "ORDER BY e.EventDate DESC "
                + "LIMIT ? OFFSET ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, pageSize); // Số lượng sự kiện trên mỗi trang
            stm.setInt(2, (page - 1) * pageSize); // Vị trí offset

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Event event = new Event();
                    event.setId(rs.getInt("EventID"));
                    event.setClubId(rs.getInt("ClubID"));
                    event.setEventName(rs.getString("EventName"));
                    event.setDescription(rs.getString("Description"));
                    event.setEventDate(rs.getString("EventDate"));
                    event.setEndEventDate(rs.getString("EndEventDate")); // Thêm EndEventDate
                    event.setCreatedBy(rs.getInt("CreatedBy"));
                    event.setCreatedDate(rs.getString("CreatedDate"));
                    event.setEventImageURL(rs.getString("EventImageURL"));
                    event.setStatus(rs.getBoolean("Status"));
                    event.setType(rs.getBoolean("Type"));
                    event.setParticipantcount(rs.getInt("ParticipantCount")); // Thêm ParticipantCount
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

    // Lấy danh sách sự kiện với điều kiện tìm kiếm và phân trang
    public List<Event> getEventByPageWithSearch(int page, int pageSize, String keyword, String type, String clubName) {
        List<Event> eventList = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT e.EventID, e.ClubID, e.EventName, e.Description, e.EventDate, e.EndEventDate, "
                + "e.CreatedBy, e.CreatedDate, e.EventImageURL, e.Status, e.Type, e.ParticipantCount, "
                + "c.ClubName, u.Username AS CreatedByUsername "
                + "FROM Events e "
                + "JOIN Clubs c ON e.ClubID = c.ClubID "
                + "LEFT JOIN Users u ON e.CreatedBy = `u`.UserID "
                + "WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND (e.EventName LIKE ? OR e.Description LIKE ?)");
            params.add("%" + keyword + "%");
            params.add("%" + keyword + "%");
        }
        if (type != null && !type.trim().isEmpty()) {
            sql.append(" AND e.Type = ?");
            params.add(Integer.parseInt(type));
        }
        if (clubName != null && !clubName.trim().isEmpty()) {
            sql.append(" AND c.ClubName = ?"); // Tìm kiếm theo ClubName
            params.add(clubName); // ClubName là String, không cần parse
        }

        sql.append(" ORDER BY e.EventDate DESC LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add((page - 1) * pageSize);

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stm.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stm.executeQuery()) {
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
            System.out.println("Lỗi khi phân trang sự kiện với tìm kiếm: " + e.getMessage());
        }
        return eventList;
    }
    
    public List<Event> getEventByPageAndClubIDsSearch(int page, int pageSize, List<Integer> clubIDs, String searchKeyword, Boolean type) {
    List<Event> eventList = new ArrayList<>();

    String sql = "SELECT e.*, c.ClubName, u.Username AS CreatedByUsername "
               + "FROM Events e "
               + "JOIN Clubs c ON e.ClubID = c.ClubID "
               + "LEFT JOIN Users u ON e.CreatedBy = u.UserID "
               + "WHERE 1=1";

    // Lọc theo danh sách ClubIDs nếu có
    if (clubIDs != null && !clubIDs.isEmpty()) {
        sql += " AND e.ClubID IN (" + clubIDs.stream().map(id -> "?").collect(Collectors.joining(",")) + ")";
    }

    // Lọc theo từ khóa tìm kiếm nếu có
    if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
        sql += " AND e.EventName LIKE ?";
    }

    // Lọc theo Type nếu có
    if (type != null) {
        sql += " AND e.Type = ?";
    }

    sql += " ORDER BY e.EventDate DESC LIMIT ? OFFSET ?";

    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        int i = 1;

        // Gán các ClubID
        if (clubIDs != null && !clubIDs.isEmpty()) {
            for (int clubID : clubIDs) {
                ps.setInt(i++, clubID);
            }
        }

        // Gán từ khóa tìm kiếm
        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            ps.setString(i++, "%" + searchKeyword.trim() + "%");
        }

        // Gán Type nếu có
        if (type != null) {
            ps.setBoolean(i++, type);
        }

        // Gán pageSize và offset
        ps.setInt(i++, pageSize);
        ps.setInt(i, (page - 1) * pageSize);

        // Lấy kết quả
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
                eventList.add(event);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Lỗi khi phân trang, tìm kiếm hoặc lọc theo type: " + e.getMessage());
    }

    return eventList;
}
    //
//    public List<Event> getEventByPageAndClubIDsSearh(int page, int pageSize, List<Integer> clubIDs, String searchKeyword) {
//    List<Event> eventList = new ArrayList<>();
//
//    String sql = "SELECT e.*, c.ClubName, u.Username AS CreatedByUsername "
//               + "FROM Events e "
//               + "JOIN Clubs c ON e.ClubID = c.ClubID "
//               + "LEFT JOIN Users u ON e.CreatedBy = u.UserID "
//               + "WHERE 1=1";
//
//    if (clubIDs != null && !clubIDs.isEmpty()) {
//        sql += " AND e.ClubID IN (" + clubIDs.stream().map(id -> "?").collect(Collectors.joining(",")) + ")";
//    }
//
//    if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
//        sql += " AND e.EventName LIKE ?";
//    }
//
//    sql += " ORDER BY e.EventDate DESC LIMIT ? OFFSET ?";
//
//    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
//        int i = 1;
//
//        if (clubIDs != null && !clubIDs.isEmpty()) {
//            for (int clubID : clubIDs) {
//                ps.setInt(i++, clubID);
//            }
//        }
//
//        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
//            ps.setString(i++, "%" + searchKeyword.trim() + "%");
//        }
//
//        ps.setInt(i++, pageSize);
//        ps.setInt(i, (page - 1) * pageSize);
//
//        try (ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                Event event = new Event(
//                        rs.getInt("EventID"),
//                        rs.getInt("ClubID"),
//                        rs.getString("EventName"),
//                        rs.getString("Description"),
//                        rs.getString("EventDate"),
//                        rs.getInt("CreatedBy"),
//                        rs.getString("CreatedDate"),
//                        rs.getString("EventImageURL"),
//                        rs.getBoolean("Status"),
//                        rs.getBoolean("Type"),
//                        rs.getString("ClubName"),
//                        rs.getString("CreatedByUsername")
//                );
//                eventList.add(event);
//            }
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//        System.out.println("Lỗi khi phân trang hoặc tìm kiếm sự kiện: " + e.getMessage());
//    }
//    return eventList;
//}
    
    
    // lay so event theo ten va club
//    public int getTotalEventsByNameAndClubIDs(String eventName, List<Integer> clubIDs) {
//    int totalEvents = 0;
//
//    String sql = "SELECT COUNT(*) AS total FROM Events WHERE 1=1";
//
//    // Thêm điều kiện lọc theo EventName nếu có
//    if (eventName != null && !eventName.trim().isEmpty()) {
//        sql += " AND EventName LIKE ?";
//    }
//
//    // Thêm điều kiện lọc theo danh sách ClubIDs nếu có
//    if (clubIDs != null && !clubIDs.isEmpty()) {
//        sql += " AND ClubID IN (" + clubIDs.stream().map(id -> "?").collect(Collectors.joining(",")) + ")";
//    }
//
//    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
//        int i = 1;
//
//        // Gán giá trị EventName nếu có
//        if (eventName != null && !eventName.trim().isEmpty()) {
//            ps.setString(i++, "%" + eventName.trim() + "%");
//        }
//
//        // Gán các ClubID nếu có
//        if (clubIDs != null && !clubIDs.isEmpty()) {
//            for (int clubID : clubIDs) {
//                ps.setInt(i++, clubID);
//            }
//        }
//
//        try (ResultSet rs = ps.executeQuery()) {
//            if (rs.next()) {
//                totalEvents = rs.getInt("total");
//            }
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//        System.out.println("Lỗi khi lấy tổng số sự kiện theo tên và clubIDs: " + e.getMessage());
//    }
//
//    return totalEvents;
//}
public int getTotalEventsByNameAndClubIDs(String eventName, List<Integer> clubIDs, Boolean type) {
    int totalEvents = 0;

    String sql = "SELECT COUNT(*) AS total FROM Events WHERE 1=1";

    // Thêm điều kiện lọc theo EventName nếu có
    if (eventName != null && !eventName.trim().isEmpty()) {
        sql += " AND EventName LIKE ?";
    }

    // Thêm điều kiện lọc theo danh sách ClubIDs nếu có
    if (clubIDs != null && !clubIDs.isEmpty()) {
        sql += " AND ClubID IN (" + clubIDs.stream().map(id -> "?").collect(Collectors.joining(",")) + ")";
    }

    // Thêm điều kiện lọc theo Type nếu có
    if (type != null) {
        sql += " AND Type = ?";
    }

    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        int i = 1;

        // Gán giá trị EventName nếu có
        if (eventName != null && !eventName.trim().isEmpty()) {
            ps.setString(i++, "%" + eventName.trim() + "%");
        }

        // Gán các ClubID nếu có
        if (clubIDs != null && !clubIDs.isEmpty()) {
            for (int clubID : clubIDs) {
                ps.setInt(i++, clubID);
            }
        }

        // Gán giá trị Type nếu có
        if (type != null) {
            ps.setBoolean(i++, type);
        }

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalEvents = rs.getInt("total");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Lỗi khi lấy tổng số sự kiện theo tên, clubIDs và type: " + e.getMessage());
    }

    return totalEvents;
}
// Đếm tổng số sự kiện với điều kiện tìm kiếm
    public int getTotalEventsWithSearch(String keyword, String type, String clubName) {
        StringBuilder sql = new StringBuilder(
                "SELECT COUNT(*) as total FROM Events e "
                + "JOIN Clubs c ON e.ClubID = c.ClubID "
                + "WHERE 1=1"
        );
        List<Object> params = new ArrayList<>();
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql.append(" AND (e.EventName LIKE ? OR e.Description LIKE ?)");
            params.add("%" + keyword + "%");
            params.add("%" + keyword + "%");
        }
        if (type != null && !type.trim().isEmpty()) {
            sql.append(" AND e.Type = ?");
            params.add(Integer.parseInt(type));
        }
        if (clubName != null && !clubName.trim().isEmpty()) {
            sql.append(" AND c.ClubName = ?");
            params.add(clubName);
        }

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stm.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Event> getEventByPageAndClubIDs(int page, int pageSize, List<Integer> clubIDs) {
        List<Event> eventList = new ArrayList<>();

        // Xây dựng câu lệnh SQL với điều kiện lọc danh sách ClubIDs
        String sql = "SELECT e.*, c.ClubName, u.Username AS CreatedByUsername "
                + "FROM Events e "
                + "JOIN Clubs c ON e.ClubID = c.ClubID "
                + "LEFT JOIN Users u ON e.CreatedBy = u.UserID "
                + "WHERE 1=1";

        // Thêm điều kiện lọc nếu danh sách ClubIDs không rỗng
        if (clubIDs != null && !clubIDs.isEmpty()) {
            sql += " AND e.ClubID IN ("
                    + clubIDs.stream().map(id -> "?").collect(Collectors.joining(",")) + ")";
        }

        sql += " ORDER BY e.EventDate DESC LIMIT ? OFFSET ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            int i = 1;

            // Gán danh sách ClubIDs vào câu truy vấn SQL
            if (clubIDs != null && !clubIDs.isEmpty()) {
                for (int clubID : clubIDs) {
                    ps.setInt(i++, clubID);
                }
            }

            // Thiết lập số lượng bản ghi trên mỗi trang
            ps.setInt(i++, pageSize);

            // Tính toán vị trí bắt đầu của dữ liệu trên trang hiện tại
            int offset = (page - 1) * pageSize;
            ps.setInt(i, offset);

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

    public List<User> getUsersByEventID(int eventId) {
        List<User> users = new ArrayList<>();

        String sql = "SELECT DISTINCT u.UserID, u.Username, u.Fullname, u.Email, u.ImageURL, u.Status "
                + "FROM events e "
                + "JOIN userclubs uc ON e.ClubID = uc.ClubID "
                + "JOIN users u ON uc.UserID = u.UserID "
                + "WHERE e.EventID = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            // Gán giá trị EventID vào câu truy vấn
            stm.setInt(1, eventId);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    User user = new User(); // Tạo đối tượng User rỗng

                    // Gán từng thuộc tính bằng setter
                    user.setId(rs.getInt("UserID"));
                    user.setUserName(rs.getString("Username"));
                    user.setFullname(rs.getString("Fullname"));
                    user.setEmail(rs.getString("Email"));
                    user.setImageURL(rs.getString("ImageURL"));
                    user.setStatus(rs.getString("Status"));

                    // Thêm User vào danh sách
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi lấy danh sách người dùng theo EventID: " + e.getMessage());
        }

        return users;
    }
    // Các function khác (addEvent, getEventsByClubId, v.v.) vẫn giữ nguyên nếu bạn cần

    public boolean updateRoleID(int userID, int clubID, int newRoleID) {
        String sql = "UPDATE UserClubs SET RoleID = ? WHERE UserID = ? AND ClubID = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            // Gán giá trị cho các tham số trong câu lệnh SQL
            stm.setInt(1, newRoleID); // Giá trị RoleID mới
            stm.setInt(2, userID);   // Giá trị UserID
            stm.setInt(3, clubID);   // Giá trị ClubID

            // Thực thi truy vấn
            int rowsUpdated = stm.executeUpdate();

            // Kiểm tra xem có dòng nào được cập nhật hay không
            return rowsUpdated > 0; // Trả về true nếu thành công
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi cập nhật RoleID: " + e.getMessage());
            return false; // Trả về false nếu có lỗi xảy ra
        }

    }

    public boolean updateRoleIDinUser(int userID, int newRoleID) {
        String sql = "UPDATE users SET RoleID = ? WHERE UserID = ? ";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            // Gán giá trị cho các tham số trong câu lệnh SQL
            stm.setInt(1, newRoleID); // Giá trị RoleID mới

            stm.setInt(2, userID);   // Giá trị UseID

            // Thực thi truy vấn
            int rowsUpdated = stm.executeUpdate();

            // Kiểm tra xem có dòng nào được cập nhật hay không
            return rowsUpdated > 0; // Trả về true nếu thành công
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi cập nhật RoleID: " + e.getMessage());
            return false; // Trả về false nếu có lỗi xảy ra
        }

    }

    //// dung de tu dong update cac Status cua cac Event Neu da qua thoi gian 
    public boolean updateEventStatus() {
        String sql = "UPDATE events SET Status = 0 WHERE endEventDate < NOW() AND Status != 0";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Thực thi truy vấn cập nhật
            int rowsUpdated = stmt.executeUpdate();

            // Kiểm tra xem có dòng nào được cập nhật hay không
            if (rowsUpdated > 0) {
                System.out.println("Updated " + rowsUpdated + " events to Inactive.");
            } else {
                System.out.println("No events needed status update.");
            }

            return rowsUpdated > 0; // Trả về true nếu có ít nhất một dòng được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi cập nhật trạng thái sự kiện: " + e.getMessage());
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    public int countFilteredProjects(String clubId, String status, String keyword) {
        int count = 0;

        String sql = "SELECT COUNT(*) FROM Project p WHERE 1=1";

        if (clubId != null && !clubId.isEmpty()) {
            sql += " AND p.ClubID = ?";
        }
        if (status != null && !status.isEmpty()) {
            sql += " AND p.Status = ?";
        }
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND p.Name LIKE ?";
        }

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            int i = 1;
            if (clubId != null && !clubId.isEmpty()) {
                stm.setInt(i++, Integer.parseInt(clubId));
            }
            if (status != null && !status.isEmpty()) {
                stm.setBoolean(i++, status.equals("1"));
            }
            if (keyword != null && !keyword.isEmpty()) {
                stm.setString(i++, "%" + keyword + "%");
            }

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public int countFilteredProjectsByClubId(String clubId, List<Integer> clubIDs, String status, String keyword) {
        int count = 0;

        String sql = "SELECT COUNT(*) FROM Project p WHERE 1=1";

        // Điều kiện kiểm tra clubId
        if (clubId != null && !clubId.isEmpty()) {
            sql += " AND p.ClubID = ?";
        }

        // Điều kiện kiểm tra danh sách ClubIDs
        if (clubIDs != null && !clubIDs.isEmpty()) {
            sql += " AND p.ClubID IN ("
                    + clubIDs.stream().map(id -> "?").collect(Collectors.joining(",")) + ")";
        }

        // Điều kiện trạng thái
        if (status != null && !status.isEmpty()) {
            sql += " AND p.Status = ?";
        }

        // Điều kiện tìm kiếm từ khóa
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND p.Name LIKE ?";
        }

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            int i = 1;

            // Gán giá trị cho clubId nếu có
            if (clubId != null && !clubId.isEmpty()) {
                stm.setInt(i++, Integer.parseInt(clubId));
            }

            // Gán danh sách ClubIDs nếu có
            if (clubIDs != null && !clubIDs.isEmpty()) {
                for (int clubID : clubIDs) {
                    stm.setInt(i++, clubID);
                }
            }

            // Gán trạng thái nếu có
            if (status != null && !status.isEmpty()) {
                stm.setBoolean(i++, status.equals("1")); // "1" là Active
            }

            // Gán giá trị từ khóa nếu có
            if (keyword != null && !keyword.isEmpty()) {
                stm.setString(i++, "%" + keyword + "%");
            }

            // Thực thi truy vấn và lấy kết quả
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public List<Project> getFilteredProjectsByID(List<Integer> clubIDs, String status, String keyword, int offset, int pageSize, String sort) {
        List<Project> projects = new ArrayList<>();

        String sql = "SELECT p.ProjectID, p.Name, p.Code, p.From_Date, p.To_Date, "
                + "p.Leader_ID, p.Status, p.Description, p.ClubID, "
                + "u.Username AS NameCreater "
                + "FROM Project p "
                + "JOIN Users u ON p.Leader_ID = u.UserID "
                + "WHERE 1=1";

        // Thêm điều kiện kiểm tra danh sách ClubIDs
        if (clubIDs != null && !clubIDs.isEmpty()) {
            sql += " AND p.ClubID IN ("
                    + clubIDs.stream().map(id -> "?").collect(Collectors.joining(",")) + ")";
        }

        // Thêm điều kiện lọc nếu có
        if (status != null && !status.isEmpty()) {
            sql += " AND p.Status = ?";
        }
        if (keyword != null && !keyword.isEmpty()) {
            sql += " AND p.Name LIKE ?";
        }

        // Thêm điều kiện sắp xếp
        if ("name_asc".equals(sort)) {
            sql += " ORDER BY p.Name ASC";
        } else if ("name_desc".equals(sort)) {
            sql += " ORDER BY p.Name DESC";
        } else {
            sql += " ORDER BY p.From_Date DESC"; // mặc định
        }

        sql += " LIMIT ? OFFSET ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            int i = 1;

            // Gán giá trị danh sách ClubIDs vào SQL
            if (clubIDs != null && !clubIDs.isEmpty()) {
                for (int clubID : clubIDs) {
                    stm.setInt(i++, clubID);
                }
            }

            // Gán giá trị trạng thái
            if (status != null && !status.isEmpty()) {
                stm.setBoolean(i++, status.equals("1")); // "1" là Active
            }

            // Gán giá trị từ khóa tìm kiếm
            if (keyword != null && !keyword.isEmpty()) {
                stm.setString(i++, "%" + keyword + "%");
            }

            // Gán giá trị phân trang (LIMIT và OFFSET)
            stm.setInt(i++, pageSize);
            stm.setInt(i, offset);

            // Thực thi truy vấn và xử lý kết quả
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project();
                    project.setProjectID(rs.getInt("ProjectID"));
                    project.setName(rs.getString("Name"));
                    project.setCode(rs.getString("Code"));
                    project.setFromDate(rs.getDate("From_Date"));
                    project.setToDate(rs.getDate("To_Date"));
                    project.setLeader_ID(rs.getInt("Leader_ID"));
                    project.setStatus(rs.getBoolean("Status"));
                    project.setDescription(rs.getString("Description"));
                    project.setNameCreater(rs.getString("NameCreater"));
                    project.setClubID(rs.getInt("ClubID"));

                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

//    public List<Project> getFilteredProjects(String clubId, String status, String keyword, int offset, int pageSize, String sort) {
//        List<Project> projects = new ArrayList<>();
//
//        String sql = "SELECT p.ProjectID, p.Name, p.Code, p.From_Date, p.To_Date, "
//                + "p.Leader_ID, p.Status, p.Description, p.ClubID, "
//                + "u.Username AS NameCreater "
//                + "FROM Project p "
//                + "JOIN Users u ON p.Leader_ID = u.UserID "
//                + "WHERE 1=1";
//
//        // Thêm điều kiện lọc nếu có
//        if (clubId != null && !clubId.isEmpty()) {
//            sql += " AND p.ClubID = ?";
//        }
//        if (status != null && !status.isEmpty()) {
//            sql += " AND p.Status = ?";
//        }
//        if (keyword != null && !keyword.isEmpty()) {
//            sql += " AND p.Name LIKE ?";
//        }
//
//        // Thêm điều kiện sắp xếp
//        if ("name_asc".equals(sort)) {
//            sql += " ORDER BY p.Name ASC";
//        } else if ("name_desc".equals(sort)) {
//            sql += " ORDER BY p.Name DESC";
//        } else {
//            sql += " ORDER BY p.From_Date DESC"; // mặc định
//        }
//
//        sql += " LIMIT ? OFFSET ?";
//
//        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
//
//            int i = 1;
//            if (clubId != null && !clubId.isEmpty()) {
//                stm.setInt(i++, Integer.parseInt(clubId));
//            }
//            if (status != null && !status.isEmpty()) {
//                stm.setBoolean(i++, status.equals("1")); // "1" là Active
//            }
//            if (keyword != null && !keyword.isEmpty()) {
//                stm.setString(i++, "%" + keyword + "%");
//            }
//
//            stm.setInt(i++, pageSize);
//            stm.setInt(i, offset);
//
//            try (ResultSet rs = stm.executeQuery()) {
//                while (rs.next()) {
//                    Project project = new Project();
//                    project.setProjectID(rs.getInt("ProjectID"));
//                    project.setName(rs.getString("Name"));
//                    project.setCode(rs.getString("Code"));
//                    project.setFromDate(rs.getDate("From_Date"));
//                    project.setToDate(rs.getDate("To_Date"));
//                    project.setLeader_ID(rs.getInt("Leader_ID"));
//                    project.setStatus(rs.getBoolean("Status"));
//                    project.setDescription(rs.getString("Description"));
//                    project.setNameCreater(rs.getString("NameCreater"));
//                    project.setClubID(rs.getInt("ClubID"));
//
//                    projects.add(project);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return projects;
//    }
    public List<Project> getFilteredProjects(String clubId, List<Integer> clubIDs, String status, String keyword, int offset, int pageSize, String sort) {
    List<Project> projects = new ArrayList<>();

    StringBuilder sql = new StringBuilder(
        "SELECT p.ProjectID, p.Name, p.Code, p.From_Date, p.To_Date, "
        + "p.Leader_ID, p.Status, p.Description, p.ClubID, "
        + "u.Username AS NameCreater "
        + "FROM Project p "
        + "JOIN Users u ON p.Leader_ID = u.UserID "
        + "WHERE 1=1 "
    );

    // Nếu clubId có giá trị thì lọc theo clubId
    if (clubId != null && !clubId.isEmpty()) {
        sql.append("AND p.ClubID = ? ");
    } else if (clubIDs != null && !clubIDs.isEmpty()) {
        // Nếu không có clubId cụ thể thì lọc theo danh sách clubIDs
        String placeholders = clubIDs.stream().map(id -> "?").collect(Collectors.joining(","));
        sql.append("AND p.ClubID IN (").append(placeholders).append(") ");
    }

    // Thêm điều kiện lọc theo status và keyword nếu có
    if (status != null && !status.isEmpty()) {
        sql.append("AND p.Status = ? ");
    }
    if (keyword != null && !keyword.isEmpty()) {
        sql.append("AND p.Name LIKE ? ");
    }

    // Sắp xếp
    if ("name_asc".equals(sort)) {
        sql.append("ORDER BY p.Name ASC ");
    } else if ("name_desc".equals(sort)) {
        sql.append("ORDER BY p.Name DESC ");
    } else {
        sql.append("ORDER BY p.From_Date DESC ");
    }

    sql.append("LIMIT ? OFFSET ?");

    try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql.toString())) {
        int i = 1;

        // Gán clubId hoặc danh sách clubIDs
        if (clubId != null && !clubId.isEmpty()) {
            stm.setInt(i++, Integer.parseInt(clubId));
        } else if (clubIDs != null && !clubIDs.isEmpty()) {
            for (int id : clubIDs) {
                stm.setInt(i++, id);
            }
        }

        // Gán các giá trị còn lại
        if (status != null && !status.isEmpty()) {
            stm.setBoolean(i++, status.equals("1")); // true nếu là "1"
        }
        if (keyword != null && !keyword.isEmpty()) {
            stm.setString(i++, "%" + keyword + "%");
        }

        stm.setInt(i++, pageSize);
        stm.setInt(i, offset);

        try (ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Project project = new Project();
                project.setProjectID(rs.getInt("ProjectID"));
                project.setName(rs.getString("Name"));
                project.setCode(rs.getString("Code"));
                project.setFromDate(rs.getDate("From_Date"));
                project.setToDate(rs.getDate("To_Date"));
                project.setLeader_ID(rs.getInt("Leader_ID"));
                project.setStatus(rs.getBoolean("Status"));
                project.setDescription(rs.getString("Description"));
project.setNameCreater(rs.getString("NameCreater"));
                project.setClubID(rs.getInt("ClubID"));

                projects.add(project);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return projects;
}
    public int getTotalProjectsByClubIDs(List<Integer> clubIDs) {
        if (clubIDs.isEmpty()) {
            return 0; // Trả về 0 nếu danh sách ClubIDs rỗng
        }

        // Xây dựng câu lệnh SQL với điều kiện WHERE ClubID IN (...)
        String sql = "SELECT COUNT(*) FROM Project WHERE ClubID IN ("
                + clubIDs.stream().map(id -> "?").collect(Collectors.joining(",")) + ")";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            // Gán giá trị cho các tham số trong câu truy vấn
            int index = 1;
            for (int id : clubIDs) {
                ps.setInt(index++, id);
            }

            // Thực thi truy vấn và lấy kết quả
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1); // Lấy tổng số dự án
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi
        }

        return 0; // Trả về 0 nếu có lỗi
    }

    public Integer getUserIDByUsername(String username) {
        String sql = "SELECT UserID FROM Users WHERE Username = ?";
        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getInt("UserID"); // Trả về ID của User nếu tìm thấy
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy User
    }

    // lay List ClubId cua cac cau lac bo ung voi  UserID 
    public List<Integer> getClubIDsByUserID(int userID) {
        List<Integer> clubIDs = new ArrayList<>();
        String sql = "SELECT ClubID FROM UserClubs WHERE UserID = ? And RoleID = 4";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, userID);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                clubIDs.add(rs.getInt("ClubID")); // Thêm ClubID vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clubIDs; // Trả về danh sách ClubID
    }
       public List<Integer> getClubIDsByUserID2(int userID) {
        List<Integer> clubIDs = new ArrayList<>();
        String sql = "SELECT ClubID FROM UserClubs WHERE UserID = ? ";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, userID);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                clubIDs.add(rs.getInt("ClubID")); // Thêm ClubID vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clubIDs; // Trả về danh sách ClubID
    }


    public List<Club> getClubsByClubIDs(List<Integer> clubIDs) {
        List<Club> clubs = new ArrayList<>();

        if (clubIDs.isEmpty()) {
            return clubs; // Trả về danh sách rỗng nếu không có ClubID nào
        }

        String sql = "SELECT * FROM Clubs WHERE ClubID IN ("
                + clubIDs.stream().map(id -> "?").collect(Collectors.joining(",")) + ")";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            int index = 1;
            for (int id : clubIDs) {
                stm.setInt(index++, id);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Club club = new Club();
                club.setClubID(rs.getInt("ClubID"));
                club.setClubName(rs.getString("ClubName"));
                club.setCategory(rs.getString("Categories"));
                club.setDescription(rs.getString("Description"));
                club.setClubLeader(rs.getString("Clubleader"));
                club.setImgURL(rs.getString("ImageURL"));
                club.setcontactClub(rs.getString("ContactClub"));
                club.setSchedule(rs.getString("Schedule"));
                club.setCreateDate(rs.getString("CreatedDate"));
                club.setIsPublic(rs.getString("IsPublic"));
                club.setStatus(rs.getString("status"));

                clubs.add(club);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clubs;
    }

    public void updateProject(int projectID, String name, String code, Date fromDate, Date toDate, boolean status, String description) {
        String sql = "UPDATE Project SET Name=?, Code=?, From_Date=?, To_Date=?, Status=?, Description=? WHERE ProjectID=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, code);
            ps.setDate(3, new java.sql.Date(fromDate.getTime()));
            ps.setDate(4, new java.sql.Date(toDate.getTime()));
            ps.setBoolean(5, status);
            ps.setString(6, description);
            ps.setInt(7, projectID);
            System.out.println("Updated completed");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Project> getProjectsByPage(int page, int pageSize) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT p.ProjectID, p.Name, p.Code, p.From_Date, p.To_Date, "
                + "p.Leader_ID, p.Status, p.Description, p.ClubID, "
                + "u.Username AS NameCreater "
                + "FROM Project p "
                + "JOIN Users u ON p.Leader_ID = u.UserID "
                + "ORDER BY p.From_Date DESC "
                + "LIMIT ? OFFSET ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, pageSize); // Số lượng project trên mỗi trang
            stm.setInt(2, (page - 1) * pageSize); // Vị trí offset

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project();
                    project.setProjectID(rs.getInt("ProjectID"));
                    project.setName(rs.getString("Name"));
                    project.setCode(rs.getString("Code"));
                    project.setFromDate(rs.getDate("From_Date"));
                    project.setToDate(rs.getDate("To_Date"));
                    project.setLeader_ID(rs.getInt("Leader_ID"));
                    project.setStatus(rs.getBoolean("Status"));
                    project.setDescription(rs.getString("Description"));
                    project.setNameCreater(rs.getString("NameCreater"));
                    project.setClubID(rs.getInt("ClubID"));

                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }

    public int getTotalProjects() {
        String sql = "SELECT COUNT(*) FROM Project";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1); // Lấy tổng số dự án từ kết quả truy vấn
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Nếu có lỗi, trả về 0 dự án
    }

    // lay List ClubId cua cac cau lac bo ung voi  UserID 
    public boolean addProject(Project project) {
        String sql = "INSERT INTO Project (Name, Code, From_Date, To_Date, Leader_ID, Status, Description, ClubID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            // Set prepared statement parameters
            stm.setString(1, project.getName());          // Project Name
            stm.setString(2, project.getCode());          // Project Code
            stm.setObject(3, project.getFromDate());      // Directly use java.util.Date
            stm.setObject(4, project.getToDate());        // Directly use java.util.Date
            stm.setInt(5, project.getLeader_ID());        // Leader ID
            stm.setBoolean(6, project.isStatus());        // Project Status
            stm.setString(7, project.getDescription());   // Description
            stm.setInt(8, project.getClubID());           // Club ID

            // Execute the query and verify success
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            // Log detailed error for debugging
            System.err.println("Error adding project: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Integer getClubIdByUserId(int userId) {
        String sql = "SELECT ClubID FROM Users WHERE UserID = ? LIMIT 1"; // Truy vấn ClubID theo UserID
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId); // Thiết lập tham số UserID
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ClubID"); // Lấy giá trị ClubID nếu có
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi
        }
        return null; // Trả về null nếu không tìm thấy ClubID
    }

    public Project getProjectById(int projectId) {
        String sql = "SELECT * FROM Project WHERE ProjectID = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, projectId);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return new Project(
                        rs.getInt("ProjectID"),
                        rs.getString("Name"),
                        rs.getString("Code"),
                        rs.getDate("From_Date"),
                        rs.getDate("To_Date"),
                        rs.getInt("Leader_ID"),
                        rs.getBoolean("Status"),
                        rs.getString("Description"),
                        rs.getInt("ClubID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
//public void updateProject(int projectID, String name, String code, Date fromDate, Date toDate, boolean status, String description) {
//    String sql = "UPDATE Project SET Name=?, Code=?, From_Date=?, To_Date=?, Status=?, Description=? WHERE ProjectID=?";
//    try (Connection conn = getConnection();
//         PreparedStatement ps = conn.prepareStatement(sql)) {
//        ps.setString(1, name);
//        ps.setString(2, code);
//        ps.setDate(3, new java.sql.Date(fromDate.getTime()));
//        ps.setDate(4, new java.sql.Date(toDate.getTime()));
//        ps.setBoolean(5, status);
//        ps.setString(6, description);
//        ps.setInt(7, projectID);
//        System.out.println("Updated completed");
//        ps.executeUpdate();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}

    public User getUserByUserID(int userID) {
        User user = null;
        String sql = "SELECT * FROM users WHERE UserID = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, userID);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("UserID"));
                user.setUserName(rs.getString("Username"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setLastLoginDate(rs.getDate("LastLoginDate"));
                user.setAccountCreatedDate(rs.getDate("CreatedDate"));
                user.setImageURL(rs.getString("ImageURL"));
                user.setRole(rs.getString("RoleID"));
                user.setFullname(rs.getString("Fullname"));
                user.setStatus(rs.getString("Status"));
                user.setText(rs.getString("note"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {

        projectDao Daoproject = new projectDao();
        
        
        
          // Đổi tên class nếu bạn đặt khác

        // Giả lập dữ liệu đầu vào
        List<Integer> clubIDs = Arrays.asList(1, 2); // Giả sử đang tìm các project của clubID 1 và 2
        String status = "1";                         // "1" là đang hoạt động
        String keyword = "";                       // Tìm theo tên chứa "AI"
        int offset = 0;                              // Bắt đầu từ trang đầu
        int pageSize = 5;                            // Mỗi trang 5 item
        String sort = "";                    // Sắp xếp theo tên tăng dần

        // Gọi phương thức
        List<Project> projects = Daoproject.getFilteredProjectsByID(clubIDs, status, keyword, offset, pageSize, sort);

        // In kết quả ra màn hình
        System.out.println("Danh sách Project tìm được:");
        for (Project p : projects) {
            System.out.println("ID: " + p.getProjectID()
                    + ", Name: " + p.getName()
                    + ", Code: " + p.getCode()
                    + ", From: " + p.getFromDate()
                    + ", To: " + p.getToDate()
                    + ", Leader: " + p.getLeader_ID()
                    + ", Status: " + (p.isStatus() ? "Active" : "Inactive")
                    + ", ClubID: " + p.getClubID()
                    + ", Người tạo: " + p.getNameCreater());
        }
    

//        List<Project> list = Daoproject.getAllProjects();
//        for (Project o : list) {
//            System.out.println(o);

  //      }
  
 
  

// int a= Daoproject.getUserIDByUsername("Tuan123");
// System.out.println(a);
//List<Integer> clubIDs = Daoproject.getClubIDsByUserID(1);
//    System.out.println("Danh sách ClubID của UserID =1  " + clubIDs);
//   
//List<Club> clubNames = Daoproject.getClubsByClubIDs(clubIDs);
//   for (Club o : clubNames) {
//           System.out.println(o);
//       
        // Tạo một đối tượng DAO
        projectDao daoProject = new projectDao();
        List<User> clubMembers = daoProject.getMembersByClubId(20);
        if (clubMembers == null || clubMembers.isEmpty()) {
            System.err.println("Không tìm thấy thành viên nào trong câu lạc bộ với ID = 1.");
        } else {
            System.out.println("Danh sách thành viên của câu lạc bộ với ID = 1:");
            for (User member : clubMembers) {
                System.out.println("User ID: " + member.getId() + ", Username: " + member.getUserName());
            }
        }
    }
}
