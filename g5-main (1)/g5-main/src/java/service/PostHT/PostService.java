package service.PostHT;

import DAO.postHT;
import DAO.projectDao;
import Model.Club;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostService {
    private projectDao projectDao = new projectDao();
    private postHT postDao = new postHT();

    // Lấy danh sách câu lạc bộ theo userId (cho doGet)
    public List<Club> getClubsByUserId(int userId) throws SQLException {
        List<Integer> clubIDs = projectDao.getClubIDsByUserID2(userId);
        return projectDao.getClubsByClubIDs(clubIDs);
    }

    // Validation dữ liệu bài đăng
    public Map<String, String> validatePost(String title, String status, String content, String clubIdStr, String imageUrl) {
        Map<String, String> errors = new HashMap<>();

        // Kiểm tra title
        if (title == null || title.trim().isEmpty()) {
            errors.put("titleError", "Tiêu đề không được để trống.");
        } else if (title.length() > 100) {
            errors.put("titleError", "Tiêu đề không được vượt quá 100 ký tự.");
        }

        // Kiểm tra status
        if (status == null || status.trim().isEmpty()) {
            errors.put("statusError", "Trạng thái không được để trống.");
        }

        // Kiểm tra content
        if (content == null || content.trim().isEmpty()) {
            errors.put("contentError", "Nội dung không được để trống.");
        } else if (content.length() > 1000) {
            errors.put("contentError", "Nội dung không được vượt quá 1000 ký tự.");
        }

        // Kiểm tra clubId
        if (clubIdStr == null || clubIdStr.trim().isEmpty()) {
            errors.put("clubError", "Vui lòng chọn câu lạc bộ.");
        } else {
            try {
                Integer.parseInt(clubIdStr);
            } catch (NumberFormatException e) {
                errors.put("clubError", "ID câu lạc bộ không hợp lệ.");
            }
        }

        // Kiểm tra imageUrl
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            errors.put("imageError", "Hình ảnh không được để trống.");
        }

        return errors;
    }

    // Thêm bài đăng
    public void addPost(int userId, int clubId, String imageUrl, String title, String status, String content, int likeCount) throws SQLException {
        postDao.addPost(userId, clubId, imageUrl, title, status, content, likeCount);
    }
}