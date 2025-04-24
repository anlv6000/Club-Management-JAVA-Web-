/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class EventRegistration {
    private String imageURL;

    private int id;
    private int eventId;
    private int userId;
    private int clubId;
    private String fullName;
    private String email;
    private String phone;
    private String note;
    private String status;

    public EventRegistration() {
    }

    public EventRegistration(String imageURL,int id, int eventId, int userId, int clubId, String fullName, String email, String phone, String note, String status) {
        this.imageURL = imageURL;
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.clubId = clubId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.note = note;
        this.status = status;
    }
    
    public int getId() {
        return id;
    }

    // Getters và Setters
    public void setId(int id) {    
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getImageURL() {
    return imageURL;
}

public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
}
}

