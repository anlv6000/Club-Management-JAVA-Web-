/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Doan Quan
 */
public class Club {
      private int clubID;
      private String clubName;
      private String category;
      private String description;
      private String isPublic;
      private String imgURL;
      private String ContactClub;
      private String Schedule;
      private String numbersofmember;
      private String status;
      private String clubleader;
      private String createDate;
    public Club() {
    }

    public Club(String category) {
        this.category = category;
    }
    
    //Constructor with name, category, description, isPublic, imgURL
    public Club(int clubID, String clubName,String description, String isPublic, String imgURL) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.description = description;
        this.isPublic = isPublic;
        this.imgURL = imgURL;
    }
    
    
    //Constructor with name, category, description, isPublic, imgURL, status and club leader
    public Club(int clubID, String clubName,String description, String isPublic, String imgURL, String status, String clubLeader) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.description = description;
        this.isPublic = isPublic;
        this.imgURL = imgURL;
        this.status = status;
        this.clubleader = clubLeader;
    }
    
    //contructor with name, description, imgUrl
     public Club(String clubName,String description,String imgURL) {
        this.clubName = clubName;
        this.description = description;
        this.imgURL = imgURL;
    }

    public Club(String clubName, String category, String imgURL, String ContactClub, String Schedule) {
        this.clubName = clubName;
        this.category = category;
        
        this.imgURL = imgURL;
        this.ContactClub = ContactClub;
        this.Schedule = Schedule;
    }
    
    
    //constructor with full attribute
    public Club(int clubID, String clubName, String category, String description, String clubleader, String imgURL, String ContactClub, String Schedule, String createDate) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.category = category;
        this.description = description;
        this.clubleader = clubleader;
        this.imgURL = imgURL;
        this.ContactClub = ContactClub;
        this.Schedule = Schedule;
        this.createDate= createDate;
    
    }
    public Club(int clubID, String clubName, String category, String description, String clubleader, String imgURL, String ContactClub, String Schedule) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.category = category;
        this.description = description;
        this.clubleader = clubleader;
        this.imgURL = imgURL;
        this.ContactClub = ContactClub;
        this.Schedule = Schedule;
    }
   

    public Club(String clubName, String category, String description, String imgURL, String ContactClub, String Schedule) {
        this.clubName = clubName;
        this.category = category;
        this.description = description;
        this.imgURL = imgURL;
        this.ContactClub = ContactClub;
        this.Schedule = Schedule;
    }

    public int getClubID() {
        return clubID;
    }

    public String getClubName() {
        return clubName;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getContactClub() {
        return ContactClub;
    }

    public String getNumbersofmember() {
        return numbersofmember;
    }

    public String getSchedule() {
        return Schedule;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public void setContactClub(String ContactClub) {
        this.ContactClub = ContactClub;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public void setNumbersofmember(String numbersofmember) {
        this.numbersofmember = numbersofmember;
    }

    public void setSchedule(String Schedule) {
        this.Schedule = Schedule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setClubleader(String Clubleader) {
        this.clubleader = Clubleader;
    }

    public String getClubleader() {
        return clubleader;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    
    
    
      
}
