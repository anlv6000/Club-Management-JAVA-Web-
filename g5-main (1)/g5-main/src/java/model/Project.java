/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author User
 */
public class Project {

    private int ProjectID;
    private String Name;
    private String Code;
    private Date fromDate;
    private Date toDate;
    private int Leader_ID;
    private boolean Status;
    private String nameCreater;
    private String Description;
    private int ClubID;
    private String ClubName;
    

    public String getClubName() {
        return ClubName;
    }

    public void setClubName(String ClubName) {
        this.ClubName = ClubName;
    }

   
    
    

    public Project() {
    }

    public Project(int ProjectID, String Name, String Code, Date fromDate, Date toDate, int Leader_ID, boolean Status, String Description, int ClubID) {
        this.ProjectID = ProjectID;
        this.Name = Name;
        this.Code = Code;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.Leader_ID = Leader_ID;
        this.Status = Status;
        this.Description = Description;
        this.ClubID = ClubID;
    }

    public Project(int ProjectID, String Name, String Code, Date fromDate, Date toDate, int Leader_ID, boolean Status, String nameCreater, String Description, int ClubID) {
        this.ProjectID = ProjectID;
        this.Name = Name;
        this.Code = Code;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.Leader_ID = Leader_ID;
        this.Status = Status;
        this.nameCreater = nameCreater;
        this.Description = Description;
        this.ClubID = ClubID;
    }

    public Project(String name, String code, Date fromDate, Date toDate, int leaderID, boolean status, String description, int clubID) {
        this.Name = name;
        this.Code = code;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.Leader_ID = leaderID;
        this.Status = status;
        this.Description = description;
        this.ClubID = clubID;
    }


    public int getClubID() {
        return ClubID;
    }

    public void setClubID(int ClubID) {
        this.ClubID = ClubID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int ProjectID) {
        this.ProjectID = ProjectID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getLeader_ID() {
        return Leader_ID;
    }

    public void setLeader_ID(int Leader_ID) {
        this.Leader_ID = Leader_ID;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getNameCreater() {
        return nameCreater;
    }

    public void setNameCreater(String nameCreater) {
        this.nameCreater = nameCreater;
    }

    @Override
    public String toString() {
        return "Project{" + "ProjectID=" + ProjectID + ", Name=" + Name + ", Code=" + Code + ", fromDate=" + fromDate + ", toDate=" + toDate + ", Leader_ID=" + Leader_ID + ", Status=" + Status + ", nameCreater=" + nameCreater + ", Description=" + Description + ", ClubID=" + ClubID + '}';
    }

}
