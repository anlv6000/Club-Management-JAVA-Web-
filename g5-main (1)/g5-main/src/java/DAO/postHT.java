/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import database.DBContext;
import Model.Account;
import Model.Club;
import Model.Event;
import Model.GoogleAccount;
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
import Model.Post;
import org.mindrot.jbcrypt.BCrypt;
public class postHT extends DBContext{
   public List<Post> getAllPosts() {
    List<Post> posts = new ArrayList<>();
    String sql = "SELECT p.post_id, p.user_id, p.club_id, p.views, p.image_url, p.status, p.description, " +
                 "p.title, p.post_at, u.Username AS namecreated, c.ClubName AS nameclub " +
                 "FROM post p " +
                 "JOIN Users u ON p.user_id = u.UserID " +
                 "JOIN Clubs c ON p.club_id = c.ClubID " +
                 "ORDER BY p.post_at DESC";

    try (Connection conn = getConnection(); 
         PreparedStatement stm = conn.prepareStatement(sql); 
         ResultSet rs = stm.executeQuery()) {

        while (rs.next()) {
            Post post = new Post();
            post.setPost_id(rs.getInt("post_id"));
            post.setUser_id(rs.getInt("user_id"));
            post.setClub_id(rs.getInt("club_id"));
            post.setView(rs.getInt("views"));
            post.setImage_url(rs.getString("image_url"));
            post.setStatus(rs.getString("status"));
            post.setDescription(rs.getString("description"));
            post.setTitle(rs.getString("title"));
            post.setPost_at(rs.getString("post_at"));
            post.setNamecreated(rs.getString("namecreated"));
            post.setNameclub(rs.getString("nameclub"));
            posts.add(post);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return posts;
}
   public List<Post> getPostByPage(int page, int pageSize) {
    List<Post> posts = new ArrayList<>();
    String sql = "SELECT p.post_id, p.user_id, p.club_id, p.views, p.image_url, p.status, p.description, " +
                 "p.title, p.post_at, u.Username AS namecreated, c.ClubName AS nameclub " +
                 "FROM post p " +
                 "JOIN Users u ON p.user_id = u.UserID " +
                 "JOIN Clubs c ON p.club_id = c.ClubID " +
                 "ORDER BY p.post_at DESC " +
                 "LIMIT ? OFFSET ?"; 

    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, pageSize);
        ps.setInt(2, (page - 1) * pageSize); // Tính toán vị trí offset dựa vào trang

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Post post = new Post();
                post.setPost_id(rs.getInt("post_id"));
                post.setUser_id(rs.getInt("user_id"));
                post.setClub_id(rs.getInt("club_id"));
                post.setView(rs.getInt("views"));
                post.setImage_url(rs.getString("image_url"));
                post.setStatus(rs.getString("status"));
                post.setDescription(rs.getString("description"));
                post.setTitle(rs.getString("title"));
                post.setPost_at(rs.getString("post_at"));
                post.setNamecreated(rs.getString("namecreated"));
                post.setNameclub(rs.getString("nameclub"));
                posts.add(post);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return posts;
}
   public List<Post> getFilteredPosts(String clubId, String status, String keyword, int offset, int pageSize, String sort) {
    List<Post> posts = new ArrayList<>();
    String sql = "SELECT p.post_id, p.user_id, p.club_id, p.views, p.image_url, p.status, p.description, " +
                 "p.title, p.post_at, u.Username AS NameCreated, c.ClubName AS NameClub " +
                 "FROM post p " +
                 "JOIN Users u ON p.user_id = u.UserID " +
                 "JOIN Clubs c ON p.club_id = c.ClubID " +
                 "WHERE 1=1";

    // Điều kiện lọc
    if (clubId != null && !clubId.isEmpty()) sql += " AND p.club_id = ?";
    if (status != null && !status.isEmpty()) sql += " AND p.status = ?";
    if (keyword != null && !keyword.isEmpty()) sql += " AND p.title LIKE ?";

    // Sắp xếp
    if ("title_asc".equals(sort)) sql += " ORDER BY p.title ASC";
    else if ("title_desc".equals(sort)) sql += " ORDER BY p.title DESC";
    else sql += " ORDER BY p.post_at DESC";

    // Phân trang
    sql += " LIMIT ? OFFSET ?";

    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        int paramIndex = 1;
        if (clubId != null && !clubId.isEmpty()) ps.setInt(paramIndex++, Integer.parseInt(clubId));
        if (status != null && !status.isEmpty()) ps.setString(paramIndex++, status);
        if (keyword != null && !keyword.isEmpty()) ps.setString(paramIndex++, "%" + keyword + "%");
        ps.setInt(paramIndex++, pageSize);
        ps.setInt(paramIndex, offset);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Post post = new Post();
            post.setPost_id(rs.getInt("post_id"));
            post.setUser_id(rs.getInt("user_id"));
            post.setClub_id(rs.getInt("club_id"));
            post.setImage_url(rs.getString("image_url"));
            post.setTitle(rs.getString("title"));
            post.setPost_at(rs.getString("post_at"));
            post.setStatus(rs.getString("status"));
            post.setDescription(rs.getString("description"));
            post.setView(rs.getInt("views"));
            post.setNamecreated(rs.getString("NameCreated"));
            post.setNameclub(rs.getString("NameClub"));
            posts.add(post);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return posts;
}
   public int countFilteredPosts(String clubId, String status, String keyword) {
    int count = 0;
    String sql = "SELECT COUNT(*) FROM post p " +
                 "JOIN Users u ON p.user_id = u.UserID " +
                 "JOIN Clubs c ON p.club_id = c.ClubID " +
                 "WHERE 1=1";

    // Điều kiện lọc
    if (clubId != null && !clubId.isEmpty()) sql += " AND p.club_id = ?";
    if (status != null && !status.isEmpty()) sql += " AND p.status = ?";
    if (keyword != null && !keyword.isEmpty()) sql += " AND p.title LIKE ?";

    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        int paramIndex = 1;
        if (clubId != null && !clubId.isEmpty()) {
            try {
                ps.setInt(paramIndex++, Integer.parseInt(clubId));
            } catch (NumberFormatException e) {
                System.out.println("clubId không hợp lệ: " + clubId);
                return 0;
            }
        }
        if (status != null && !status.isEmpty()) ps.setString(paramIndex++, status);
        if (keyword != null && !keyword.isEmpty()) ps.setString(paramIndex++, "%" + keyword + "%");

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (Exception e) {
        System.out.println("Lỗi trong countFilteredPosts: " + e.getMessage());
        e.printStackTrace();
    }
    return count;
}
   public List<Post> getPostByStatusByPage(String status, int page, int pageSize) {
    List<Post> posts = new ArrayList<>();
    String sql = "SELECT p.post_id, p.user_id, p.club_id, p.views, p.image_url, p.status, p.description, " +
                 "p.title, p.post_at, u.Username AS namecreated, c.ClubName AS nameclub " +
                 "FROM post p " +
                 "JOIN Users u ON p.user_id = u.UserID " +
                 "JOIN Clubs c ON p.club_id = c.ClubID " +
                 "WHERE p.status = ? " + // Thêm điều kiện lọc theo status
                 "ORDER BY p.post_at DESC " +
                 "LIMIT ? OFFSET ?";

    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, status); // Gán giá trị cho điều kiện lọc status
        ps.setInt(2, pageSize);
        ps.setInt(3, (page - 1) * pageSize); // Tính toán vị trí offset dựa vào trang

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Post post = new Post();
                post.setPost_id(rs.getInt("post_id"));
                post.setUser_id(rs.getInt("user_id"));
                post.setClub_id(rs.getInt("club_id"));
                post.setView(rs.getInt("views"));
                post.setImage_url(rs.getString("image_url"));
                post.setStatus(rs.getString("status"));
                post.setDescription(rs.getString("description"));
                post.setTitle(rs.getString("title"));
                post.setPost_at(rs.getString("post_at"));
                post.setNamecreated(rs.getString("namecreated"));
                post.setNameclub(rs.getString("nameclub"));
                posts.add(post);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return posts;
}
   
   public List<Post> getPostByTitleByPage(String title, int page, int pageSize) {
    List<Post> posts = new ArrayList<>();
    String sql = "SELECT p.post_id, p.user_id, p.club_id, p.views, p.image_url, p.status, p.description, " +
                 "p.title, p.post_at, u.Username AS namecreated, c.ClubName AS nameclub " +
                 "FROM post p " +
                 "JOIN Users u ON p.user_id = u.UserID " +
                 "JOIN Clubs c ON p.club_id = c.ClubID " +
                 "WHERE p.title LIKE ? " + // Tìm kiếm theo title
                 "ORDER BY p.post_at DESC " +
                 "LIMIT ? OFFSET ?";

    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, "%" + title + "%"); // Tìm kiếm title chứa từ khóa
        ps.setInt(2, pageSize);
        ps.setInt(3, (page - 1) * pageSize); // Tính toán offset cho phân trang

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Post post = new Post();
                post.setPost_id(rs.getInt("post_id"));
                post.setUser_id(rs.getInt("user_id"));
                post.setClub_id(rs.getInt("club_id"));
                post.setView(rs.getInt("views"));
                post.setImage_url(rs.getString("image_url"));
                post.setStatus(rs.getString("status"));
                post.setDescription(rs.getString("description"));
                post.setTitle(rs.getString("title"));
                post.setPost_at(rs.getString("post_at"));
                post.setNamecreated(rs.getString("namecreated"));
                post.setNameclub(rs.getString("nameclub"));
                posts.add(post);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return posts;
}

   public int getTotalPosts() {
    String sql = "SELECT COUNT(*) FROM post";
    try (Connection conn = getConnection(); 
         PreparedStatement ps = conn.prepareStatement(sql); 
         ResultSet rs = ps.executeQuery()) {
        
        if (rs.next()) {
            return rs.getInt(1); // Lấy tổng số bài viết từ kết quả truy vấn
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // Nếu có lỗi, trả về 0 bài viết
}
   
   
   
   public int getTotalPostsByStatus(String status) {
    String sql = "SELECT COUNT(*) FROM post WHERE status = ?";
    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, status); // Gán giá trị cho điều kiện lọc status
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {  
                return rs.getInt(1); // Lấy tổng số bài viết có status được chỉ định
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // Nếu có lỗi, trả về 0 bài viết
}
   
   public int getTotalPostsByTitle(String title) {
    String sql = "SELECT COUNT(*) FROM post WHERE title LIKE ?";
    try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, "%" + title + "%"); // Tìm kiếm title có chứa từ khóa
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {  
                return rs.getInt(1); // Lấy tổng số bài viết phù hợp
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0; // Nếu có lỗi, trả về 0 bài viết
}
   
   
   
  public boolean addPost(int userId, int clubId, String imageUrl, String title, String status, String description, int views) {
    String sql = "INSERT INTO post (user_id, club_id, image_url, title, status, description, views) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = getConnection(); 
         PreparedStatement stm = conn.prepareStatement(sql)) {

        stm.setInt(1, userId);
        stm.setInt(2, clubId);
        stm.setString(3, imageUrl);
        stm.setString(4, title);
        stm.setString(5, status);
        stm.setString(6, description);
        stm.setInt(7, views);

        int rowsInserted = stm.executeUpdate();
        return rowsInserted > 0; // Trả về true nếu thêm thành công
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Trả về false nếu có lỗi
}
    public List<Post> getPostsByStatus(String status) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM post WHERE status = ? ORDER BY post_at DESC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Post post = new Post();
                post.setPost_id(rs.getInt("post_id"));
                post.setUser_id(rs.getInt("user_id"));
                post.setClub_id(rs.getInt("club_id"));
                post.setImage_url(rs.getString("image_url"));
                post.setTitle(rs.getString("title"));
                post.setPost_at(rs.getString("post_at"));
                post.setStatus(rs.getString("status"));
                post.setDescription(rs.getString("description"));
                post.setView(rs.getInt("views"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    
     public List<Post> getPostsByClubID(int clubId) {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT p.post_id, p.user_id, p.club_id, p.views, p.image_url, p.status, p.description, " +
                 "p.title, p.post_at, u.Username AS namecreated, c.ClubName AS nameclub " +
                 "FROM post p " +
                 "JOIN Users u ON p.user_id = u.UserID " +
                 "JOIN Clubs c ON p.club_id = c.ClubID " +
                 "WHERE p.club_id = ? " +
                 "ORDER BY p.post_at DESC";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, clubId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Post post = new Post();
                post.setPost_id(rs.getInt("post_id"));
                post.setUser_id(rs.getInt("user_id"));
                post.setClub_id(rs.getInt("club_id"));
                post.setImage_url(rs.getString("image_url"));
                post.setTitle(rs.getString("title"));
                post.setPost_at(rs.getString("post_at"));
                post.setStatus(rs.getString("status"));
                post.setDescription(rs.getString("description"));
                post.setView(rs.getInt("views"));
                post.setNamecreated(rs.getString("namecreated"));
                post.setNameclub(rs.getString("nameclub"));
                posts.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }
    public void updatePostView(int postId) {
    String sql = "UPDATE post SET views = views + 1 WHERE post_id = ?";
    
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
         
        ps.setInt(1, postId);
        ps.executeUpdate();
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}
     
     public boolean deletePostByPostID(int postId) {
    String sql = "DELETE FROM post WHERE post_id = ?";
    
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
         
        ps.setInt(1, postId);
        int affectedRows = ps.executeUpdate();
        return affectedRows > 0; // Trả về true nếu xóa thành công
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false; // Trả về false nếu có lỗi
}
     public Post getPostByPostID(int postId) {
    String sql = "SELECT p.post_id, p.user_id, p.club_id, p.views, p.image_url, p.status, p.description, " +
                 "p.title, p.post_at, u.Username AS namecreated, c.ClubName AS nameclub " +
                 "FROM post p " +
                 "JOIN Users u ON p.user_id = u.UserID " +
                 "JOIN Clubs c ON p.club_id = c.ClubID " +
                 "WHERE p.post_id = ?";

    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, postId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Post post = new Post();
            post.setPost_id(rs.getInt("post_id"));
            post.setUser_id(rs.getInt("user_id"));
            post.setClub_id(rs.getInt("club_id"));
            post.setView(rs.getInt("views"));
            post.setImage_url(rs.getString("image_url"));
            post.setStatus(rs.getString("status"));
            post.setDescription(rs.getString("description"));
            post.setTitle(rs.getString("title"));
            post.setPost_at(rs.getString("post_at"));
            post.setNamecreated(rs.getString("namecreated"));
            post.setNameclub(rs.getString("nameclub"));
            return post;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null; // Trả về null nếu không tìm thấy bài viết
}

     
     public List<Post> getRecentPosts() {
    List<Post> posts = new ArrayList<>();
    String sql = "SELECT p.post_id, p.user_id, p.club_id, p.views, p.image_url, p.status, p.description, " +
                 "p.title, p.post_at, u.Username AS namecreated, c.ClubName AS nameclub " +
                 "FROM post p " +
                 "JOIN Users u ON p.user_id = u.UserID " +
                 "JOIN Clubs c ON p.club_id = c.ClubID " +
                 "ORDER BY p.post_at DESC " +  // Sắp xếp theo ngày đăng mới nhất
                 "LIMIT 4";  // Giới hạn 4 bài mới nhất

    try (Connection conn = getConnection(); 
         PreparedStatement stm = conn.prepareStatement(sql); 
         ResultSet rs = stm.executeQuery()) {

        while (rs.next()) {
            Post post = new Post();
            post.setPost_id(rs.getInt("post_id"));
            post.setUser_id(rs.getInt("user_id"));
            post.setClub_id(rs.getInt("club_id"));
            post.setView(rs.getInt("views"));
            post.setImage_url(rs.getString("image_url"));
            post.setStatus(rs.getString("status"));
            post.setDescription(rs.getString("description"));
            post.setTitle(rs.getString("title"));
            post.setPost_at(rs.getString("post_at"));
            post.setNamecreated(rs.getString("namecreated"));
            post.setNameclub(rs.getString("nameclub"));
            posts.add(post);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return posts;
}
     
    public boolean UpdatePostByPostID(int post_id, int user_id, int club_id, String title, String image_url, String status, String description) {
    String sql = "UPDATE post SET user_id = ?, club_id = ?, title = ?, image_url = ?, status = ?, description = ? WHERE post_id = ?";
    
    try (Connection conn = getConnection(); 
         PreparedStatement stm = conn.prepareStatement(sql)) {
        
        stm.setInt(1, user_id);
        stm.setInt(2, club_id);
        stm.setString(3, title);
        stm.setString(4, image_url);
        stm.setString(5, status);
        stm.setString(6, description);
        stm.setInt(7, post_id);
        
        int rowsUpdated = stm.executeUpdate();
        return rowsUpdated > 0;
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
     
     
     
     
   public static void main(String[] args) {
       postHT postDao = new postHT();
       //check getALL
//       List<Post> posts = postDao.getAllPosts();
//       for (Post post : posts) {
//           System.out.println(post);
//       }

      //check add 
//      postDao.addPost(1,1,"abc.jpg","ah the ak","Public","ko sao ca ",0);
//   }

     
//       List<Post> posts = postDao.getPostByTitleByPage("Hành",1,1);
//       for (Post post : posts) {
//           System.out.println(post);
//       }

//         postDao.deletePostByPostID(4);
//
//        Post post=postDao.getPostByPostID(1);
//         System.out.println(post); 


//      postDao.UpdatePostByPostID(1, 1, 1, "UpdatePostByPostID", "123.jpg", "Draft", "check update");
System.out.println("Trường hợp 1: Lọc bài đăng với club_id = 1, status = Draft, keyword = Business");
    List<Post> posts = postDao.getFilteredPosts("", "Published", "", 1, 5, "title_desc");
    if (posts.isEmpty()) {
        System.out.println("Không tìm thấy bài đăng nào.");
    } else {
        for (Post post : posts) {
            System.out.println(post);
        }
    }
    System.out.println("Số bài đăng thỏa mãn: " + postDao.countFilteredPosts("1", "Draft", "Business"));
    System.out.println("------------------------");
   }
   
   
   
}
