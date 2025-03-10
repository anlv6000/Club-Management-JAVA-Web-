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
                 "p.title, p.post_at, u.Username AS NameCreated, c.ClubName AS NameClub " +
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
            post.setNameCreated(rs.getString("NameCreated"));
            post.setNameClub(rs.getString("NameClub"));
            posts.add(post);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return posts;
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
                 "p.title, p.post_at, u.Username AS NameCreated, c.ClubName AS NameClub " +
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
                post.setNameCreated(rs.getString("NameCreated"));
                post.setNameClub(rs.getString("NameClub"));
                posts.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
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
                 "p.title, p.post_at, u.Username AS NameCreated, c.ClubName AS NameClub " +
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
            post.setNameCreated(rs.getString("NameCreated"));
            post.setNameClub(rs.getString("NameClub"));
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
                 "p.title, p.post_at, u.Username AS NameCreated, c.ClubName AS NameClub " +
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
            post.setNameCreated(rs.getString("NameCreated"));
            post.setNameClub(rs.getString("NameClub"));
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

     
//       List<Post> posts = postDao.getPostsByClubID(1);
//       for (Post post : posts) {
//           System.out.println(post);
//       }

//         postDao.deletePostByPostID(4);
//
//        Post post=postDao.getPostByPostID(1);
//         System.out.println(post); 


      postDao.UpdatePostByPostID(1, 1, 1, "UpdatePostByPostID", "123.jpg", "Draft", "check update");
   }
   
   
   
}
