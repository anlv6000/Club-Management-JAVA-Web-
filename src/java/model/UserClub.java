/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
import java.util.List;

public class UserClub {
     private int userID;
    private int clubID;
    private String userName;
    private String fullname;
    private String phone;
    private String email;
    private String role;
    private String status;
    private Date joinedDate;
    private String imageURL; 
    String text;

    public UserClub() {
    }

    public UserClub(int userID, int clubID, String userName, String fullname, String email, String status, String imageURL) {
        this.userID = userID;
        this.clubID = clubID;
        this.userName = userName;
        this.fullname = fullname;
        this.email = email;
        this.status = status;
        this.imageURL = imageURL;
    }

    public UserClub(int userID, int clubID, String userName, String fullname, String email, String role, String status, String imageURL, String text) {
        this.userID = userID;
        this.clubID = clubID;
        this.userName = userName;
        this.fullname = fullname;
        this.email = email;
        
        this.role = role;
        this.status = status;
        this.imageURL = imageURL;
        this.text = text;
      
    }

    public UserClub(int userID, String userName, String fullname, String email, String phone, String role, String status, String imageURL) {
        this.userID = userID;
        this.userName = userName;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = status;
        this.imageURL = imageURL;
    }

    public UserClub(int userID, String userName, String fullname, String email, String status, String imageURL) {
        this.userID = userID;
        this.userName = userName;
        this.fullname = fullname;
        this.email = email;
        this.status = status;
        this.imageURL = imageURL;
    }

    public UserClub(String userName, String fullname, String email, String status, String imageURL) {
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


    public String getPhone() {
        return phone;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setJoinedDate(Date JoinedDate) {
        this.joinedDate = JoinedDate;
    }
    
}
