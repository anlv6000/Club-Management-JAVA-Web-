/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Event;
import Model.Post;
import Model.Task;
import database.DBContext;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import Model.UserClub;


/**
 * DAO xử lý dữ liệu thành viên câu lạc bộ
 *
 */
public class ClubMemberDAO extends DBContext {

    PreparedStatement ps;
    ResultSet rs;

    public List<UserClub> getOfficialMembers(int clubId) {
        List<UserClub> members = new ArrayList<>();
       String sql = "SELECT u.UserID, u.Username, u.Email, u.ImageURL, u.Fullname, cm.phone, cm.status, cm.role "
        + "FROM Users u "
        + "JOIN UserClubs cm ON u.UserID = cm.UserID "
         
        + "WHERE cm.ClubID = ? AND cm.status IN ('Active', 'Inactive')";


        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
              ps.setInt(1, clubId); 
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    members.add(new UserClub(rs.getInt("UserID"), rs.getString("Username"),
                            rs.getString("Fullname"), rs.getString("Email"),rs.getString("phone"),
                            rs.getString("role"), rs.getString("Status"), rs.getString("ImageURL")));
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        }
        return members;
    }
// Phương thức để lấy club_id từ user_id
    public int getClubIdByUserId(int userId) throws SQLException {
        int clubId = -1;
        String sql = "SELECT ClubID FROM Users WHERE UserID = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                clubId = rs.getInt("ClubID");
            }
        }
        return clubId;
    }
    public List<UserClub> getMemberRegistrations(int clubId) {
        List<UserClub> members = new ArrayList<>();
        String sql = "SELECT cm.UserID, cm.ClubID, u.Username, u.Fullname, u.Email, cm.Status, cm.role, u.ImageURL "
        + "FROM Users u "
        + "JOIN UserClubs cm ON u.UserID = cm.UserID "
        
        + "WHERE cm.ClubID = ? AND  cm.Status = 'Registered'";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
             ps.setInt(1, clubId); 
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                members.add(new UserClub(rs.getInt("UserID"),rs.getInt("ClubID"),rs.getString("Username"), rs.getString("Fullname"),
                        rs.getString("Email"), rs.getString("Status"),
                        
                        rs.getString("ImageURL")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }
     public List<UserClub> searchMembers(String role, String status, String keyword, int clubId) throws SQLException {
    List<UserClub> members = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT u.UserID, u.Username, u.Email, cm.phone, u.ImageURL, u.Fullname, cm.Status, cm.role "
            + "FROM Users u "
            + "JOIN UserClubs cm ON u.UserID = cm.UserID "
            + "WHERE cm.ClubID = ? AND cm.status IN ('Active', 'Inactive')"); // Thêm điều kiện lọc theo ClubID

    if (role != null && !role.isEmpty()) {
        sql.append(" AND cm.role = ?");
    }
    if (status != null && !status.isEmpty()) {
        sql.append(" AND cm.Status = ?");
    }
    if (keyword != null && !keyword.isEmpty()) {
        sql.append(" AND (u.Username LIKE ? OR u.Email LIKE ? OR u.Fullname LIKE ?)");
    }

    try (PreparedStatement ps = getConnection().prepareStatement(sql.toString())) {
        int index = 1;

        // Thêm ClubID vào điều kiện lọc
        ps.setInt(index++, clubId);

        // Set các tham số tìm kiếm khác
        if (role != null && !role.isEmpty()) {
            ps.setString(index++, role);
        }
        if (status != null && !status.isEmpty()) {
            ps.setString(index++, status);
        }
        if (keyword != null && !keyword.isEmpty()) {
            ps.setString(index++, "%" + keyword + "%");
            ps.setString(index++, "%" + keyword + "%");
            ps.setString(index++, "%" + keyword + "%");
        }

        // Thực thi truy vấn
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                members.add(new UserClub(rs.getInt("UserID"), rs.getString("Username"),
                        rs.getString("Fullname"), rs.getString("Email"),rs.getString("phone"),
                        rs.getString("role"), rs.getString("Status"), rs.getString("ImageURL")));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Có thể thay bằng ghi log nếu cần
    }

    return members;
}


  public void approveMember(int userId, int clubId) {
    String updateUserClubsSql = "UPDATE UserClubs SET Status = 'Active', role = 'ClubMember' WHERE UserID = ? AND ClubID = ?";
    String updateUsersSql = "UPDATE Users SET UserType = 'ClubMember' WHERE UserID = ?";

    // Cập nhật bảng UserClubs
    try (PreparedStatement ps1 = getConnection().prepareStatement(updateUserClubsSql)) {
        ps1.setInt(1, userId);
        ps1.setInt(2, clubId);
        ps1.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Lỗi khi cập nhật UserClubs: " + e.getMessage());
    }

    // Cập nhật bảng Users
    try (PreparedStatement ps2 = getConnection().prepareStatement(updateUsersSql)) {
        ps2.setInt(1, userId);
        ps2.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Lỗi khi cập nhật Users: " + e.getMessage());
    }
}

    


public void rejectMember(int userId, int clubId) {
    String deleteUserClubsSql = "DELETE FROM UserClubs WHERE UserID = ? AND ClubID = ?";
    String updateUsersSql = "UPDATE Users SET UserType = 'WebUser' WHERE UserID = ? AND NOT EXISTS (SELECT 1 FROM UserClubs WHERE UserID = ?)";

    // Xóa user khỏi bảng UserClubs
    try (PreparedStatement ps1 = getConnection().prepareStatement(deleteUserClubsSql)) {
        ps1.setInt(1, userId);
        ps1.setInt(2, clubId);
        ps1.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Lỗi khi xóa khỏi UserClubs: " + e.getMessage());
    }

    // Cập nhật bảng Users nếu user không còn trong câu lạc bộ nào khác
    try (PreparedStatement ps2 = getConnection().prepareStatement(updateUsersSql)) {
        ps2.setInt(1, userId);
        ps2.setInt(2, userId);
        ps2.executeUpdate();
    } catch (SQLException e) {
        System.err.println("Lỗi khi cập nhật Users: " + e.getMessage());
    }
}

     public UserClub getClubMemberByUserId(int userId) {
        UserClub clubMember = null;
        String sql = "SELECT ClubID, UserID, role, Status "
                + "FROM UserClubs "
                + "WHERE UserID = ?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    clubMember = new UserClub();
                    clubMember.setClubID(rs.getInt("ClubID"));
                    clubMember.setUserID(rs.getInt("UserID"));
                    clubMember.setRole(rs.getString("role"));
                    clubMember.setStatus(rs.getString("Status"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubMember;
    }


     public UserClub getMemberById(int userId) {
    String sql = "SELECT uc.UserID, u.Fullname, u.Email, u.Username, uc.role, uc.Status, uc.phone, uc.JoinedDate, u.ImageURL " +
                 "FROM Users u " +
                 "LEFT JOIN UserClubs uc ON u.UserID = uc.UserID " +
                 "WHERE uc.UserID = ?";

    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
        ps.setInt(1, userId);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                UserClub userClub = new UserClub();
                userClub.setUserID(rs.getInt("UserID"));
                userClub.setFullname(rs.getString("Fullname"));
                userClub.setEmail(rs.getString("Email"));
                userClub.setUserName(rs.getString("Username"));
                userClub.setRole(rs.getString("role"));
                userClub.setStatus(rs.getString("Status"));
                userClub.setPhone(rs.getString("phone"));
                userClub.setJoinedDate(rs.getTimestamp("JoinedDate"));
                userClub.setImageURL(rs.getString("ImageURL"));
                return userClub;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


    public List<UserClub> getRandomMembers(int clubId) {
    List<UserClub> members = new ArrayList<>();
    String sql = "SELECT u.UserID, u.Fullname, u.Email, u.Username, uc.role, uc.Status, uc.phone, uc.JoinedDate, u.ImageURL " +
                 "FROM Users u " +
                 "JOIN UserClubs uc ON u.UserID = uc.UserID " +
                 "WHERE uc.ClubID = ? AND uc.Status != 'Registered' " +
                 "ORDER BY RAND() LIMIT 5";

    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
        ps.setInt(1, clubId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                UserClub userClub = new UserClub();
                userClub.setUserID(rs.getInt("UserID"));
                userClub.setFullname(rs.getString("Fullname"));
                userClub.setEmail(rs.getString("Email"));
                userClub.setUserName(rs.getString("Username"));
                userClub.setRole(rs.getString("role"));
                userClub.setStatus(rs.getString("Status"));
                userClub.setPhone(rs.getString("phone"));
                userClub.setJoinedDate(rs.getTimestamp("JoinedDate"));
                userClub.setImageURL(rs.getString("ImageURL"));
                members.add(userClub);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return members;
}
//  public static void main(String[] args) {
//        ClubMemberDAO clubMemberService = new ClubMemberDAO();
//        
//        // Giả sử Club ID = 1 (Bạn có thể thay đổi ID khác từ DB)
//        int clubId = 1;
//
//        // ✅ Test lấy danh sách thành viên chính thức của CLB
//        List<User> officialMembers = clubMemberService.getOfficialMembersByClubId(clubId);
//        System.out.println("=== Official Members of Club ID: " + clubId + " ===");
//        for (User member : officialMembers) {
//            System.out.println("ID: " + member.getUser_id() + 
//                               ", Name: " + member.getFull_name() + 
//                               ", Role: " + member.getValue() +
//                               ", Status: " + member.getStatus());
//        }
//        
//        // ✅ Test lấy ngẫu nhiên 5 thành viên từ CLB
//        List<User> randomMembers = clubMemberService.getRandomMembers(clubId);
//        System.out.println("\n=== Random 5 Members from Club ID: " + clubId + " ===");
//        for (User member : randomMembers) {
//            System.out.println("ID: " + member.getUser_id() + 
//                               ", Name: " + member.getFull_name() + 
//                               ", Role: " + member.getValue() +
//                               ", Status: " + member.getStatus());
//        }
//
//        // ✅ Test lấy thông tin chi tiết của một thành viên (lấy ID của member đầu tiên)
//        if (!officialMembers.isEmpty()) {
//            int userId = officialMembers.get(0).getUser_id();  // Lấy ID của member đầu tiên
//            User user = clubMemberService.getMemberById(userId);
//            System.out.println("\n=== Detail of Member ID: " + userId + " ===");
//            System.out.println("ID: " + user.getUser_id() + 
//                               ", Name: " + user.getFull_name() + 
//                               ", Email: " + user.getEmail() +
//                               ", Phone: " + user.getPhoneNumber() +
//                               ", Role: " + user.getValue() +
//                               ", Status: " + user.getStatus());
//        } else {
//            System.out.println("\nNo official members found in Club ID: " + clubId);
//        }
//    }
   public Post getLatestPostByUserId(int userId) {
    String sql = "SELECT * FROM post WHERE user_id = ? ORDER BY post_at DESC LIMIT 1";

    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
        ps.setInt(1, userId);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                Post post = new Post();
                post.setPost_id(rs.getInt("post_id"));
                post.setUser_id(rs.getInt("user_id"));
                post.setClub_id(rs.getInt("club_id"));
                post.setImage_url(rs.getString("image_url"));
                 post.setTitle(rs.getString("title"));
                post.setStatus(rs.getString("status"));
                post.setDescription(rs.getString("description"));
                post.setPost_at(rs.getString("post_at"));
                return post;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

//    public static void main(String[] args) {
//        ClubMemberDAO postService = new ClubMemberDAO();
//        int userId = 1; // Thay đổi ID để kiểm thử
//        
//        Post latestPost = postService.getLatestPostByUserId(userId);
//        
//        if (latestPost != null) {
//            System.out.println("Latest Post:");
//            System.out.println("ID: " + latestPost.getPostId());
//            System.out.println("User ID: " + latestPost.getUserId());
//            System.out.println("Club ID: " + latestPost.getClubId());
//            System.out.println("Title: " + latestPost.getTitle());
//            System.out.println("Status: " + latestPost.getStatus());
//            System.out.println("Description: " + latestPost.getDescription());
//            System.out.println("Posted At: " + latestPost.getPostedAt());
//        } else {
//            System.out.println("No posts found for user ID: " + userId);
//        }
//    }
    public List<Post> getPostsByUserId(int userId) {
    List<Post> posts = new ArrayList<>();
    String sql = "SELECT * FROM post WHERE user_id = ? ORDER BY post_at DESC";

    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
        ps.setInt(1, userId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Post post = new Post();
                post.setPost_id(rs.getInt("post_id"));
                post.setUser_id(rs.getInt("user_id"));
                post.setClub_id(rs.getInt("club_id"));
                post.setImage_url(rs.getString("image_url"));
                 post.setTitle(rs.getString("title"));
                post.setStatus(rs.getString("status"));
                post.setDescription(rs.getString("description"));
                post.setPost_at(rs.getString("post_at"));
                posts.add(post);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return posts;
}
//    public static void main(String[] args) {
//        ClubMemberDAO postDAO = new ClubMemberDAO();
//        int userId = 1; // Thay đổi user ID nếu cần
//        List<Post> posts = postDAO.getPostsByUserId(userId);
//
//        System.out.println("Danh sách bài viết của user_id = " + userId);
//        for (Post post : posts) {
//            System.out.println("--------------------------------------------------");
//            System.out.println("Post ID   : " + post.getPostId());
//            System.out.println("Title     : " + post.getTitle());
//            System.out.println("Posted At : " + post.getPostedAt());
//            System.out.println("Status    : " + post.getStatus());
//            System.out.println("Description: " + post.getDescription());
//            System.out.println("Image URL : " + post.getImageUrl());
//        }
//    }
   public List<Task> getUserTasksWithUsers(int userId) {
    List<Task> tasks = new ArrayList<>();
    String sql = "SELECT task.*, " +
                 "a.Fullname AS assigner_name, a.Email AS assigner_email, " +
                 "b.Fullname AS assignee_name, b.Email AS assignee_email " +
                 "FROM task t " +
                 "JOIN Users a ON t.assigner_id = a.UserID " +
                 "JOIN Users b ON t.assignee_id = b.UserID " +
                 "WHERE t.assigner_id = ? OR t.assignee_id = ? " +
                 "ORDER BY t.deadline ASC";

    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
        ps.setInt(1, userId);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Task task = new Task();
                task.setTaskId(rs.getInt("task_id"));
                task.setEventId(rs.getInt("event_id"));
                task.setClubId(rs.getInt("club_id"));
                task.setTitle(rs.getString("title"));
                task.setAssignerId(rs.getInt("assigner_id"));
                task.setAssigneeId(rs.getInt("assignee_id"));
                task.setDeadline(rs.getTimestamp("deadline").toLocalDateTime());
                task.setStatus(rs.getString("status"));
                task.setDescription(rs.getString("description"));
                task.setPriority(rs.getInt("priority"));

                // Thêm thông tin người giao và người nhận
                task.setAssignerName(rs.getString("assigner_name"));
                task.setAssignerEmail(rs.getString("assigner_email"));
                task.setAssigneeName(rs.getString("assignee_name"));
                task.setAssigneeEmail(rs.getString("assignee_email"));

                tasks.add(task);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return tasks;
}

//    public static void main(String[] args) {
//        int userId = 1; // ID của user cần lấy task
//
//        List<Task> tasks = getUserTasksWithUsers(userId);
//
//        System.out.println("📌 Danh sách Task của User ID: " + userId);
//        for (Task task : tasks) {
//            System.out.println("🔹 Task ID: " + task.getTaskId() + " | Title: " + task.getTitle());
//            System.out.println("   📍 Giao bởi: " + task.getAssignerName() + " (" + task.getAssignerRole() + ")");
//            System.out.println("   🎯 Nhận bởi: " + task.getAssigneeName() + " (" + task.getAssigneeRole() + ")");
//            System.out.println("   📅 Deadline: " + task.getDeadline() + " | Status: " + task.getStatus());
//            System.out.println("---------------------------------------------------");
//        }
//    }

    public boolean updateMemberRoleAndStatus(int memberId, String role, String status) {
    String sql = "UPDATE UserClubs SET role = ?, Status = ? WHERE UserID = ?";

    // Kiểm tra input hợp lệ trước khi cập nhật
    if (memberId <= 0 || role == null || role.trim().isEmpty() || status == null || status.trim().isEmpty()) {
        System.out.println("Invalid input data for updating member.");
        return false;
    }

    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
        ps.setString(1, role);
        ps.setString(2, status);
        ps.setInt(3, memberId);

        int rowsUpdated = ps.executeUpdate();
        return rowsUpdated > 0;  // Trả về true nếu có dòng bị ảnh hưởng
    } catch (SQLException e) {
        System.err.println("Error updating member role and status: " + e.getMessage());
        return false;
    }
}

   

    

 
}
