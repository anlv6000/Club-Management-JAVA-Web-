package Model;

public class Event {
    private int id;
    private int clubId;
    private String eventName;
    private String description;
    private String eventDate;
    private Integer createdBy; // Cho phép null
    private String createdDate;
    private String eventImageURL;
    private boolean status; // Cột mới
    private boolean type; // Cột mới

    public Event() {
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

    public Event(int id, int clubId, String eventName, String description, String eventDate, Integer createdBy, String createdDate, String eventImageURL, boolean status, boolean type) {
        this.id = id;
        this.clubId = clubId;
        this.eventName = eventName;
        this.description = description;
        this.eventDate = eventDate;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.eventImageURL = eventImageURL;
        this.status = status;
        this.type = type;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", clubId=" + clubId + ", eventName='" + eventName + '\'' +
                ", description='" + description + '\'' + ", eventDate='" + eventDate + '\'' +
                ", createdBy=" + createdBy + ", createdDate='" + createdDate + '\'' +
                ", eventImageURL='" + eventImageURL + '\'' + ", status=" + status + 
                ", type=" + type + '}';
    }
}
