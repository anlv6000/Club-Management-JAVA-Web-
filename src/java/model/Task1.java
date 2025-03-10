/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Task1 {
    
    
    // Thuộc tính của task
    private int taskID;
    private int eventID;
    private int clubID;
    private String title;
    private int assignerID;
    private int assigneeID;
    private LocalDate deadline;
    private String status;
    private String description;
    private String priority;

    public Task1() {
    }

    public Task1(int taskID, int eventID, int clubID, String title, int assignerID, int assigneeID, LocalDate deadline, String status, String description, String priority) {
        this.taskID = taskID;
        this.eventID = eventID;
        this.clubID = clubID;
        this.title = title;
        this.assignerID = assignerID;
        this.assigneeID = assigneeID;
        this.deadline = deadline;
        this.status = status;
        this.description = description;
        this.priority = priority;
    }

    public Task1(int clubID, String title, LocalDate deadline, String status, String description, String priority) {
        this.clubID = clubID;
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        this.description = description;
        this.priority = priority;
    }
    
    

    public Task1(String title, LocalDate deadline, String status, String description, String priority) {
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        this.description = description;
        this.priority = priority;
    }

    public Task1(int eventID, int clubID, String title, LocalDate deadline, String status, String description, String priority) {
        this.eventID = eventID;
        this.clubID = clubID;
        this.title = title;
        this.deadline = deadline;
        this.status = status;
        this.description = description;
        this.priority = priority;
    }
      
    
    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAssignerID() {
        return assignerID;
    }

    public void setAssignerID(int assignerID) {
        this.assignerID = assignerID;
    }

    public int getAssigneeID() {
        return assigneeID;
    }

    public void setAssigneeID(int assigneeID) {
        this.assigneeID = assigneeID;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    
}
