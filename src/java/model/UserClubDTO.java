/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class UserClubDTO {
    private int userID;
    private int clubID;
    private String userName;
    private String fullname;
    private String email;
    private String role;
    private String status;
    private String imageURL; 
    String text;
   private List<String> clubs;
    private List<String> events;

    public UserClubDTO() {
    }

    public UserClubDTO(int userID, int clubID, String userName, String fullname, String email, String role, String status, String imageURL, String text, List<String> clubs, List<String> events) {
        this.userID = userID;
        this.clubID = clubID;
        this.userName = userName;
        this.fullname = fullname;
        this.email = email;
        this.role = role;
        this.status = status;
        this.imageURL = imageURL;
        this.text = text;
        this.clubs = clubs;
        this.events = events;
    }

    public UserClubDTO(int userID, String userName, String fullname, String email, String role, String status, String imageURL) {
        this.userID = userID;
        this.userName = userName;
        this.fullname = fullname;
        this.email = email;
        this.role = role;
        this.status = status;
        this.imageURL = imageURL;
    }

    public UserClubDTO(String userName, String fullname, String email, String status, String imageURL) {
        this.userName = userName;
        this.fullname = fullname;
        this.email = email;
        this.status = status;
        this.imageURL = imageURL;
    }

    public int getUserID() {
        return userID;
    }

    public int getClubID() {
        return clubID;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getText() {
        return text;
    }

    public List<String> getClubs() {
        return clubs;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setClubs(List<String> clubs) {
        this.clubs = clubs;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }
}
