/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public class User {

    private int id;
    private String userName, email, password;
    private Date LastLoginDate;
    private Date accountCreatedDate; // Sửa từ 'accountCreated' thành 'accountCreatedDate'
    private String imageURL;
    String role;
    String fullname;
    private String status;
    String text;
    private List<String> clubs;
    private List<String> events;

    public User() {
    }

    public User(String userName, String password, String role, String fullname, String status) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.fullname = fullname;
        this.status = status;
    }

    public User(int id, String userName, String email, String password, String role, String fullname, String status) {
         this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.fullname = fullname;
        this.status = status;
    }

    public User(int id, String userName, String email, String role, String status) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User(int id, String userName, String email, String password, Date LastLoginDate, Date accountCreatedDate, String imageURL, String role, String fullname, String status, String text) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.LastLoginDate = LastLoginDate;
        this.accountCreatedDate = accountCreatedDate;
        this.imageURL = imageURL;
        this.role = role;
        this.fullname = fullname;
        this.status = status;
        this.text = text;
    }

    

    public User(int id, String userName, String email, String imageURL, String fullname, String status) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.imageURL = imageURL;
        this.fullname = fullname;
        this.status = status;
    }

    public User(int id, String userName, String email, String password, Date LastLoginDate) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.LastLoginDate = LastLoginDate;
    }

    public User(int id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Date getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(Date LastLoginDate) {
        this.LastLoginDate = LastLoginDate;
    }

    public Date getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setAccountCreatedDate(Date accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<String> getClubs() {
        return clubs;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setClubs(List<String> clubs) {
        this.clubs = clubs;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password + '}';
    }

}
