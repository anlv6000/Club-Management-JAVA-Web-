/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
import java.time.LocalDateTime;

public class Task {
    private int taskId;
    private int eventId;
    private int clubId;
    private String title;
    private int assignerId;
    private int assigneeId;
    private LocalDateTime deadline;
    private String status;
    private String description;
    private int priority;
    private String assignerName;
    private String assignerEmail;
    private String assigneeName;
    private String assigneeEmail;
    // Constructor không tham số
    public Task() {}

    // Constructor đầy đủ tham số

    public Task(int taskId, int eventId, int clubId, String title, int assignerId, int assigneeId, LocalDateTime deadline, String status, String description, int priority, String assignerName, String assignerEmail, String assigneeName, String assigneeEmail) {
        this.taskId = taskId;
        this.eventId = eventId;
        this.clubId = clubId;
        this.title = title;
        this.assignerId = assignerId;
        this.assigneeId = assigneeId;
        this.deadline = deadline;
        this.status = status;
        this.description = description;
        this.priority = priority;
        this.assignerName = assignerName;
        this.assignerEmail = assignerEmail;
        this.assigneeName = assigneeName;
        this.assigneeEmail = assigneeEmail;
    }
    

    // Getter và Setter
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(int assignerId) {
        this.assignerId = assignerId;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getAssignerName() {
        return assignerName;
    }

    public void setAssignerName(String assignerName) {
        this.assignerName = assignerName;
    }

    public String getAssignerEmail() {
        return assignerEmail;
    }

    public void setAssignerEmail(String assignerEmail) {
        this.assignerEmail = assignerEmail;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getAssigneeEmail() {
        return assigneeEmail;
    }

    public void setAssigneeEmail(String assigneeEmail) {
        this.assigneeEmail = assigneeEmail;
    }

   

    // Phương thức toString để dễ dàng debug
    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", eventId=" + eventId +
                ", clubId=" + clubId +
                ", title='" + title + '\'' +
                ", assignerId=" + assignerId +
                ", assigneeId=" + assigneeId +
                ", deadline=" + deadline +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                '}';
    }
}
