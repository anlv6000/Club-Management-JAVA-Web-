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
      private boolean isPublic;
      private String imgURL;

    public Club() {
    }
    //construtor without category
    public Club(int clubID, String clubName,String description, boolean isPublic, String imgURL) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.description = description;
        this.isPublic = isPublic;
        this.imgURL = imgURL;
    }
    
    //contructor with name, description, imgUrl
     public Club(String clubName,String description,String imgURL) {
        this.clubName = clubName;
        this.isPublic = isPublic;
        this.imgURL = imgURL;
    }
     
    //contructor with full atribute
    public Club(int clubID, String clubName, String category, String description, boolean isPublic, String imgURL) {
        this.clubID = clubID;
        this.clubName = clubName;
        this.category = category;
        this.description = description;
        this.isPublic = isPublic;
        this.imgURL = imgURL;
    }

    public int getClubID() {
        return clubID;
    }

    public void setClubID(int clubID) {
        this.clubID = clubID;
    }
    

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String descrition) {
        this.description = descrition;
    }
    
    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
    
    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
    
    
      
}
