package service;

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

public class ClubMemberService {

    private ClubMemberDAO ClubMemberDAO;

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

    public boolean updateMemberRoleAndStatus(int memberId, String role, String status) {
        // Gọi DAO để cập nhật dữ liệu
        return ClubMemberDAO.updateMemberRoleAndStatus(memberId, role, status);
    }
}
