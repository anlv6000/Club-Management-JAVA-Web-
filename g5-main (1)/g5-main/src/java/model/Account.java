/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Doan Quan
 */
public class Account {
     private int id;
     
    private String username;
    private String email;
    private String role; // Sửa từ 'role' thành 'userType'
    private String status;
    private Date accountCreatedDate; // Sửa từ 'accountCreated' thành 'accountCreatedDate'
    private Date lastLoginDate; // Sửa từ 'lastLogin' thành 'lastLoginDate'
    private String imageURL; // Sửa từ 'avatar' thành 'imageURL'
    private String password;
    public Account() {
    }
    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    public Account(String username, String email, String role, String status) {
    this.username = username;
    this.email = email;
    this.role = role;
    this.status = status;
}
    public Account(int id, String username, String email, String role, String status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
    }


    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    

    public Account(int id, String password, String username, String email, String role, String status, Date accountCreatedDate, Date lastLoginDate, String imageURL) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
        this.accountCreatedDate = accountCreatedDate;
        this.lastLoginDate = lastLoginDate;
        this.imageURL = imageURL;
    }
    
    
     public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
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

    public Date getAccountCreatedDate() {
        return accountCreatedDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setAccountCreatedDate(Date accountCreatedDate) {
        this.accountCreatedDate = accountCreatedDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


   
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName=" + username + ", email=" + email + ", password=" + password + '}';
    }
    
    
    
}