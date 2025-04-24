package Model;



public class UserProfile {

    private int userID;
    private int clubID;
    private String clubName;
    private String username; // NOT NULL
    private String fullname; // NOT NULL
    private String password; // NOT NULL
    private String email; // NOT NULL
    private String role; // NOT NULL
    private String status; // NOT NULL
    private String note;
    private double phone; // NOT NULL
    private String joinedDate; // NOT NULL
    private String imageURL; // Thêm thuộc tính imageURL

    // Constructor
    public UserProfile(int userID, int clubID, String clubName, String username, String fullname,
            String password, String email, String role, String status,
            String note, double phone, String joinedDate,String imageURL) {
        this.userID = userID;
        this.clubID = clubID;
        this.clubName = clubName;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.role = role;
        this.status = status;
        this.note = note;
        this.phone = phone;
        this.joinedDate = joinedDate;
        this.imageURL = imageURL; // Gán giá trị imageURL

    }

    public UserProfile(int userID, int clubID, String clubName, String username, String fullname, String email, double phone) {
        this.userID = userID;
        this.clubID = clubID;
        this.clubName = clubName;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.imageURL = imageURL; // Gán giá trị imageURL

    }

    // Default Constructor
    public UserProfile() {
    }

    // Getters and Setters
    // Getter và Setter cho imageURL
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getPhone() {
        return phone;
    }

    public void setPhone(double phone) {
        this.phone = phone;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }

    @Override
    public String toString() {
        return "UserProfile{"
                + "userID=" + userID
                + ", clubID=" + clubID
                + ", clubName='" + clubName + '\''
                + ", username='" + username + '\''
                + ", fullname='" + fullname + '\''
                + ", password='" + password + '\''
                + ", email='" + email + '\''
                + ", role='" + role + '\''
                + ", status='" + status + '\''
                + ", note='" + note + '\''
                + ", phone=" + phone
                + ", joinedDate=" + joinedDate
                + ", imageURL='" + imageURL + '\''
                + +'}';
    }
}
