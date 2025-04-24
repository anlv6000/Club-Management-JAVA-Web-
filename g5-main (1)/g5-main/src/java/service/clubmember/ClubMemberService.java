package service.clubmember;

import DAO.ClubMemberDAO;
import DAO.ClubMemberDAO;
import DAO.dao;
import Model.Post;
import Model.Task;
import Model.User;
import Model.UserClub;
import controller.member.ClubMember;
import java.util.List;
import Model.UserClub;
import Model.Post;
import Model.Task;
import Model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import util.EmailUtility;

public class ClubMemberService {

    private ClubMemberDAO ClubMemberDAO;
     private dao dao = new dao();

    public ClubMemberService() {
        ClubMemberDAO = new ClubMemberDAO();
    }

    /**
     * Lấy danh sách thành viên chính thức của câu lạc bộ
     *
     * @param clubId ID của câu lạc bộ
     * @return Danh sách thành viên
     */
   
    // Các phương thức nghiệp vụ khác liên quan đến thành viên câu lạc bộ
    
    public List<UserClub> getClubsByUserId(int userId) throws Exception {
        return ClubMemberDAO.getClubsByUserId(userId);
    }

    public int getClubIdByUserId(int userId) throws Exception {
        return ClubMemberDAO.getClubIdByUserId(userId);
    }

    public String getClubNameById(int clubId) throws Exception {
        return ClubMemberDAO.getClubNameById(clubId);
    }

    public List<String> getRoles() throws Exception {
        return ClubMemberDAO.getRoles();
    }

    public List<String> getStatuses() throws Exception {
        return ClubMemberDAO.getStatuses();
    }

    public List<UserClub> getMemberRegistrations(int clubId) {
        return ClubMemberDAO.getMemberRegistrations(clubId);
    }

    public List<UserClub> searchMembers(String role, String status, String keyword, int clubId) {
        try {
            return ClubMemberDAO.searchMembers(role, status, keyword, clubId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<UserClub> getOfficialMembers(int clubId) {
        try {
            return ClubMemberDAO.getOfficialMembers(clubId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    public UserClub getClubMemberByUserId(int userId) {
        return ClubMemberDAO.getClubMemberByUserId(userId);
    }

    /**
     * Lấy danh sách ngẫu nhiên 5 thành viên của CLB
     **/
    public List<UserClub> getRandomMembers(int clubId) {
        return ClubMemberDAO.getRandomMembers(clubId);
    }

    /**
     * Lấy thông tin chi tiết một thành viên theo ID
     **/
    public UserClub getMemberById(int userId) {
        return ClubMemberDAO.getMemberById(userId);
    }

    public Post getLatestPostByUser(int userId) {
        return ClubMemberDAO.getLatestPostByUserId(userId);
    }

    public List<Post> getPostsByUserId(int userId) {
        return ClubMemberDAO.getPostsByUserId(userId);
    }

    public List<Task> getUserTasksWithUsers(int userId) {
        return ClubMemberDAO.getUserTasksWithUsers(userId);
    }

    public boolean updateMemberRoleAndStatus(int memberId, String role, String status, String description) throws SQLException {
        // Gọi DAO để cập nhật dữ liệu
        return ClubMemberDAO.updateMemberRoleAndStatus(memberId, role, status, description);
    }
     

    // Phê duyệt thành viên
    public void approveMember(int userId, int clubId) throws Exception {
        ClubMemberDAO.approveMember(userId, clubId);
        sendEmailToUser(userId, "Member approval notice", 
            "Congratulations on being accepted as a member of the club!");
    }

    // Từ chối thành viên
    public void rejectMember(int userId, int clubId) throws Exception {
        ClubMemberDAO.rejectMember(userId, clubId);
        sendEmailToUser(userId, "Member approval notice", 
            "Sorry you are not accepted as a member of the club!");
    }

    // Gửi email cho thành viên
    private void sendEmailToUser(int userId, String subject, String content) throws Exception {
        User member = dao.getUserById(userId);
        if (member != null && member.getEmail() != null) {
            String recipient = member.getEmail();
            EmailUtility.sendEmail(recipient, subject, content);
        }
    }
}
