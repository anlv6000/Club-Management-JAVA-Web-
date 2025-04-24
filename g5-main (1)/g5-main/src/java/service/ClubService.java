/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DAO.ClubDAO;
import Model.Club;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
public class ClubService {

    private final ClubDAO clubDAO;

    public ClubService() {
        this.clubDAO = new ClubDAO();
    }

    public List<Club> getAllClubs() {
        return clubDAO.getClubs();
    }

    public Club getClubByID(int clubID) {

        return clubDAO.getClubByID(clubID);
    }

    public List<Club> searchClubs(String keyword) {
        return clubDAO.searchClubs(keyword);
    }

    public List<String> getCategories() {
        return clubDAO.getAllCategories();
    }

    public List<Club> filterClub(String keyword, String category, String status) {
        return clubDAO.getFilteredClubs(keyword, category, status);
    }

  

    public void updateClub(int clubID, String name, String category, String description, String isPublic,
            String imgURL, int leader, String contact, String schedule) {
        List<String> errors = validateClub(name, category, contact, schedule);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("; ", errors));
        }

        clubDAO.updateClub(clubID, name, category, description, isPublic, imgURL, leader, contact, schedule);
    }

    private List<String> validateClub(String name, String category, String contact, String schedule) {
        List<String> errors = new ArrayList<>();

        if (!name.matches("^[\\p{L} ]{3,50}$")) {
            errors.add("Club name must be 3-50 characters and contain only letters.");
        }
        if (!category.matches("^[\\p{L} ]{3,50}$")) {
            errors.add("Category must be 3-50 characters and contain only letters.");
        }
        if (!contact.matches("^[a-zA-Z0-9._%+-]+@\\$")) {
            errors.add("Contact email must be in the form of Gmail (example@).");
        }
        if (schedule.length() > 50) {
            errors.add("Activity schedule must not exceed 50 characters.");
        }

        return errors;
    }

    public void deleteClub(int clubID) {
        clubDAO.deleteClub(clubID);
    }

    public void deactiveClub(int clubID) {
        clubDAO.deactiveClub(clubID);
    }
    public void activeClub(int clubID) {
        clubDAO.activeClub(clubID);
    }

    public void updateClub2(int clubID, String name, String category, String description, String isPublic,
            String imgURL, int leader, String contact, String schedule) throws IllegalArgumentException  {

        clubDAO.updateClub(clubID, name, category, description, isPublic, imgURL, leader, contact, schedule);
    }

    public void addClub2(String name, String category, String description, String isPublic, String imgURL,
            String status, int leaderID, String contact, String schedule) {
         List<String> errors = new ArrayList<>();
         
         // Validate tên câu lạc bộ: chỉ chứa chữ cái và khoảng trắng, dài 3-100 ký tự
    if (name == null || !name.matches("^[A-Za-zÀ-ỹ\\s]{3,100}$")) {
        errors.add("Club name must contain only letters and be 3 to 100 characters long.");
    }

    // Kiểm tra xem tên đã tồn tại trong hệ thống chưa
    if (clubDAO.isClubNameExist(name)) {
        errors.add("Club name already exists.");
    }
    if (clubDAO.isEmailExist(contact)) {
        errors.add("Email contact already exists.");
    }
    

    // Nếu có lỗi, ném IllegalArgumentException
    if (!errors.isEmpty()) {
        throw new IllegalArgumentException(String.join("; ", errors));
    }
        clubDAO.addClub(name, category, description, isPublic, imgURL, status, leaderID, contact, schedule);
    }

    public List<User> getUser() {
        return clubDAO.getUser();
    }
    public List<User> getUserUpdate(int clubID) {
        return clubDAO.getUserUpdate(clubID);
    }
    
    public List<Club> getJoinedClubs(int userID) {
        return clubDAO.getJoinedClubs(userID);
    }
      public List<User> getUsersJoinedClubOfUser(int userID) {
        
        return clubDAO.getUsersJoinedClubOfUser(userID);
    }
}
