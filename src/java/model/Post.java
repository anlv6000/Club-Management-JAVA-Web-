/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author User
 */
public class Post {
    private int post_id ;
    private int user_id;
    private int club_id;
    private int view ;
    private String image_url;
    private String Status ;
    private String description ;
    private String title;
    private String IsPublic;
    private String post_at;
    private String NameCreated;
    private String NameClub;

    public Post() {
    }

    public Post(int post_id, int user_id, int club_id, int view, String image_url, String Status, String description, String title, String post_at) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.club_id = club_id;
        this.view = view;
        this.image_url = image_url;
        this.Status = Status;
        this.description = description;
        this.title = title;
        this.post_at = post_at;
    }

    public Post(int post_id, int user_id, int club_id, int view, String image_url, String Status, String description, String title, String post_at, String NameCreated, String NameClub) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.club_id = club_id;
        this.view = view;
        this.image_url = image_url;
        this.Status = Status;
        this.description = description;
        this.title = title;
        this.post_at = post_at;
        this.NameCreated = NameCreated;
        this.NameClub = NameClub;
    }
      public Post(int post_id, int user_id, int club_id, String image_url, String IsPublic, String description, String title, String post_at) {
        this.post_id = post_id;
        this.user_id = user_id;
        this.club_id = club_id;
        this.image_url = image_url;
        this.IsPublic = IsPublic;
        this.description = description;
        this.title = title;
        this.post_at = post_at;
    }

    public String getIsPublic() {
        return IsPublic;
    }

    public void setIsPublic(String IsPublic) {
        this.IsPublic = IsPublic;
    }
    

    public int getPost_id() {
        return post_id;
    }
    
    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost_at() {
        return post_at;
    }

    public void setPost_at(String post_at) {
        this.post_at = post_at;
    }

    public String getNameCreated() {
        return NameCreated;
    }

    public void setNameCreated(String NameCreated) {
        this.NameCreated = NameCreated;
    }

    public String getNameClub() {
        return NameClub;
    }

    public void setNameClub(String NameClub) {
        this.NameClub = NameClub;
    }

    @Override
    public String toString() {
        return "Post{" + "post_id=" + post_id + ", user_id=" + user_id + ", club_id=" + club_id + ", view=" + view + ", image_url=" + image_url + ", Status=" + Status + ", description=" + description + ", title=" + title + ", post_at=" + post_at + ", NameCreated=" + NameCreated + ", NameClub=" + NameClub + '}';
    }
    
}
