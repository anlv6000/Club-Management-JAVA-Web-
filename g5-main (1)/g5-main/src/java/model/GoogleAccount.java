/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class GoogleAccount {
    private String  id, email, name, first_name, given_name, family_name, picture;
    private boolean verified_email;
    private String role;
    private String fullname;
    private String status;
    public GoogleAccount() {
    }

    public GoogleAccount(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public GoogleAccount(String email, String name, String role) {
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public GoogleAccount(String id, String email, String name, String role, String fullname, String status) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.fullname = fullname;
        this.status = status;
    }
    
    

    public GoogleAccount(String id, String email, String name, String first_name, String given_name, String family_name, String picture, boolean verified_email) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.first_name = first_name;
        this.given_name = given_name;
        this.family_name = family_name;
        this.picture = picture;
        this.verified_email = verified_email;
    }

    public GoogleAccount(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isVerified_email() {
        return verified_email;
    }

    public void setVerified_email(boolean verified_email) {
        this.verified_email = verified_email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        return "GoogleAccount{" + "id=" + id + ", email=" + email + ", name=" + name + ", first_name=" + first_name + ", given_name=" + given_name + ", family_name=" + family_name + ", picture=" + picture + ", verified_email=" + verified_email + '}';
    }
    
}
