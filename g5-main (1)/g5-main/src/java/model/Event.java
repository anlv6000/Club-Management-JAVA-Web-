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
    private String endEventDate;
    private String eventy;
    private String eventm;
    private String eventd;
    private String endy;
    private String endm;
    private String end;
    private int participantcount;
    private int evenLeaderID;
    public Event() {
    }
    
    public Event(int id, String eventName) {
        this.id = id;
        this.eventName = eventName;
    }
    
    
    public Event(int id, String eventName, String eventImageURL, Boolean Status, Boolean Type, String NameCLub, String eventy, String eventm, String eventd, String endy, String endm, String end,int participantcount ) {
        this.id = id;
        this.eventName = eventName;
        this.eventImageURL = eventImageURL;
        this.Status = Status;
        this.Type = Type;
        this.NameCLub = NameCLub;
        this.eventy = eventy;
        this.eventm = eventm;
        this.eventd = eventd;
        this.endy = endy;
        this.endm = endm;
        this.end = end;
        this.participantcount = participantcount;
    }
    public Event(int id, int clubId, String eventName, String description, String eventDate, Integer createdBy, String createdDate, String eventImageURL, Boolean Status, Boolean Type, String NameCLub, String UserCreat, String endEventDate, int participantcount) {
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
        this.endEventDate = endEventDate;
        this.participantcount = participantcount;
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

    public Event(int clubId) {
        this.clubId = clubId;
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

    public Event(int id, String eventName, String eventDate, String eventImageURL, Boolean Status, Boolean Type, String NameCLub, String endEventDate) {
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventImageURL = eventImageURL;
        this.Status = Status;
        this.Type = Type;
        this.NameCLub = NameCLub;
        this.endEventDate = endEventDate;
    }

    public Event(int id, int clubId, String eventName, String description, String eventDate, Integer createdBy, String createdDate, String eventImageURL, Boolean Status, Boolean Type, String NameCLub, String UserCreat, String endEventDate, int participantcount, int evenLeaderID) {
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
        this.endEventDate = endEventDate;
        this.participantcount = participantcount;
        this.evenLeaderID = evenLeaderID;
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

    public Event(int id, int clubId, String eventName, String description, String eventDate, String createdDate, String eventImageURL, Boolean Status, Boolean Type, String endEventDate) {
        this.id = id;
        this.clubId = clubId;
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.createdDate = createdDate;
        this.eventImageURL = eventImageURL;
        this.Status = Status;
        this.Type = Type;
        this.endEventDate = endEventDate;
    }
    
    

    public String getEndEventDate() {
        return endEventDate;
    }

    public int getEvenLeaderID() {
        return evenLeaderID;
    }

    public void setEvenLeaderID(int evenLeaderID) {
        this.evenLeaderID = evenLeaderID;
    }

    public void setEndEventDate(String endEventDate) {
        this.endEventDate = endEventDate;
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

    public void setNameCLub(String NameCLub) {
        this.NameCLub = NameCLub;
    }

    public void setUserCreat(String UserCreat) {
        this.UserCreat = UserCreat;
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

    public String getEventy() {
        return eventy;
    }

    public void setEventy(String eventy) {
        this.eventy = eventy;
    }

    public int getParticipantcount() {
        return participantcount;
    }

    public void setParticipantcount(int participantcount) {
        this.participantcount = participantcount;
    } 
      public void setParticipantcount() {
        this.participantcount = participantcount;
    }
    

   

    public String getEventm() {
        return eventm;
    }

    public void setEventm(String eventm) {
        this.eventm = eventm;
    }

    public String getEventd() {
        return eventd;
    }

    public void setEventd(String eventd) {
        this.eventd = eventd;
    }

    public String getEndy() {
        return endy;
    }

    public void setEndy(String endy) {
        this.endy = endy;
    }

    public String getEndm() {
        return endm;
    }

    public void setEndm(String endm) {
        this.endm = endm;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", clubId=" + clubId + ", eventName=" + eventName + ", description=" + description + ", eventDate=" + eventDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", eventImageURL=" + eventImageURL + ", Status=" + Status + ", Type=" + Type + ", NameCLub=" + NameCLub + ", UserCreat=" + UserCreat + '}';
    }
  
  
    
    
}

