/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import database.DBContext;
import Model.Club;
import java.util.List;
import java.sql.*;
import java.util.*;

/**
 *
 * @author admin
 */
public class ClubDAO extends DBContext {

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

            stm.setString(1, "%" + keyword + "%");  // Tìm kiếm với từ khóa
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Club club = new Club();
                club.setClubID(rs.getInt("ClubID"));
                club.setClubName(rs.getString("ClubName"));
                club.setCategory(rs.getString("Categories"));
                club.setDescription(rs.getString("Description"));
                club.setIsPublic(rs.getString("IsPublic"));
                club.setImgURL(rs.getString("ImageURL"));

                clubs.add(club);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }

    //Insert into Database
    public void addClub(String name, String category, String description, boolean isPublic, String image) {
        String sql = "INSERT INTO clubs (ClubName, Categories, Description, IsPublic, ImageURL) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, name);
            stm.setString(2, category);
            stm.setString(3, description);
            stm.setBoolean(4, isPublic);
            stm.setString(5, image);
            stm.executeUpdate();
            System.out.println("Thêm câu lạc bộ thành công");
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
                club.setIsPublic(rs.getString("IsPublic"));
                club.setImgURL(rs.getString("ImageURL"));
                clubs.add(club);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clubs;
    }

    // Update club information
    public void updateClub(int clubID, String name, String category, String description, boolean isPublic, String image) {
        String sql = "UPDATE clubs SET ClubName = ?, Categories = ?, Description = ?, IsPublic = ?, ImageURL = ? WHERE ClubID = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, name);
            stm.setString(2, category);
            stm.setString(3, description);
            stm.setBoolean(4, isPublic);
            stm.setString(5, image);
            stm.setInt(6, clubID);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Club getClubByID(int clubID) {
        Club club = null;
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return club;
    }

// Delete a club by ID
    public void deleteClub(int clubID) {
        String sql = "DELETE FROM clubs WHERE ClubID = ?";

        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setInt(1, clubID);
            stm.executeUpdate();
            System.out.println("Xóa câu lạc bộ thành công");
        } catch (SQLException e) {
            System.out.println("Error when delete club: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Club> getAllClubs() {

        List<Club> list = new ArrayList<>();
        String sql = "SELECT * FROM clubs ";
        try (Connection conn = getConnection(); PreparedStatement stm = conn.prepareStatement(sql)) {

            ResultSet rs = stm.executeQuery();
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

}
