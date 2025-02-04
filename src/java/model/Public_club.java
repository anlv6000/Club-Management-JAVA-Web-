/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Doan Quan
 */
public class Public_club {
      int ClubID;
      String ClubName;
      String Description;
      String ImgURL;

    public Public_club(int ClubID, String ClubName, String Description, String ImgURL) {
        this.ClubID = ClubID;
        this.ClubName = ClubName;
        this.Description = Description;
        this.ImgURL = ImgURL;
    }

    public Public_club(String ClubName, String Description, String ImgURL) {
        this.ClubName = ClubName;
        this.Description = Description;
        this.ImgURL = ImgURL;
    }

    public int getClubID() {
        return ClubID;
    }

    public void setClubID(int ClubID) {
        this.ClubID = ClubID;
    }

    public String getClubName() {
        return ClubName;
    }

    public void setClubName(String ClubName) {
        this.ClubName = ClubName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImgURL() {
        return ImgURL;
    }

    public void setImgURL(String ImgURL) {
        this.ImgURL = ImgURL;
    }
      
      
}
