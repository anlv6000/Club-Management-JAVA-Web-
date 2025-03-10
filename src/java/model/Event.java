/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author User
 */
public class Event {
    private int id;
    private int clubId;
    private String eventName;
    private String description;
    private String eventDate;
    private Integer createdBy; // Cho phép null
    private String createdDate;
    private String eventImageURL;
    private Boolean Status;
    private Boolean Type;
    private String NameCLub;
    private String UserCreat;
    public Event() {
    }

    public Event(int id, int clubId, String eventName, String description, String eventDate, Integer createdBy, String createdDate, String eventImageURL, Boolean Status, Boolean Type) {
        this.id = id;
        this.clubId = clubId;
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.eventImageURL = eventImageURL;
        this.Status = Status;
        this.Type = Type;
    }

    public Event(int id, int clubId, String eventName, String description, String eventDate, Integer createdBy, String createdDate, String eventImageURL, Boolean Status, Boolean Type, String NameCLub, String UserCreat) {
        this.id = id;
        this.clubId = clubId;
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.eventImageURL = eventImageURL;
        this.Status = Status;
        this.Type = Type;
        this.NameCLub = NameCLub;
        this.UserCreat = UserCreat;
    }
    

    public Event(int id, int clubId, String eventName, String description, String eventDate, Integer createdBy, String createdDate, String eventImageURL) {
        this.id = id;
        this.clubId = clubId;
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.eventImageURL = eventImageURL;
    }
    public Event(int clubId, String eventName, String description, int createdBy,  String eventImageURL, String eventDate) {
        this.clubId = clubId;   
        this.eventName = eventName;
        this.description = description;
        this.createdBy = createdBy;
        this.eventDate = eventDate;
        this.eventImageURL = eventImageURL;
    }
      public Event(int id, int clubId, String eventName, String description, String eventDate, int createdBy) {
        this.id = id;
        this.clubId = clubId;
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.createdBy = createdBy;
    }
    public Event(int id, int clubId, String eventName, String description, String eventDate, String createdDate, String eventImageURL) {
        this.id = id;
        this.clubId = clubId;
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.createdDate = createdDate;
        this.eventImageURL = eventImageURL;
        this.createdBy = null; // Không có người tạo
    }

    public String getNameCLub() {
        return NameCLub;
    }

    public String getUserCreat() {
        return UserCreat;
    }
    
    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }

    public Boolean getType() {
        return Type;
    }

    public void setType(Boolean Type) {
        this.Type = Type;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getEventImageURL() {
        return eventImageURL;
    }

    public void setEventImageURL(String eventImageURL) {
        this.eventImageURL = eventImageURL;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", clubId=" + clubId + ", eventName=" + eventName + ", description=" + description + ", eventDate=" + eventDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", eventImageURL=" + eventImageURL + ", Status=" + Status + ", Type=" + Type + ", NameCLub=" + NameCLub + ", UserCreat=" + UserCreat + '}';
    }
  
  
    
    
}

