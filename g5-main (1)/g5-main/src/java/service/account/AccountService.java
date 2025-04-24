/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.account;

import DAO.dao;
import Model.User;
import jakarta.servlet.http.Part;
import java.util.List;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author ADMIN
 */
public class AccountService {

    private dao dao;

    public AccountService() {
        this.dao = new dao();
    }
    // Lấy danh sách tất cả tài khoản

    public List<User> getAllAccounts() {
        return dao.getAllAccounts();
    }

    // Tìm kiếm tài khoản theo role, status, keyword
    public List<User> searchAccounts(String role, String status, String keyword) throws SQLException {
        return dao.searchAccounts(role, status, keyword);
    }

    // Lấy tài khoản theo ID
    public User getAccountById(String accountId) {
        return dao.getAccountById(accountId);
    }

    public List<String> getRoles() throws Exception {
        return dao.getRoles();
    }

    public List<String> getStatuses() throws Exception {
        return dao.getStatuses();
    }

    // Kiểm tra dữ liệu đầu vào
    public String validateAccount(String username, String fullname, String email, String userType, String status, Part profileImage) {
        if (username == null || username.isEmpty()) {
            return "Vui lòng nhập tên tài khoản.";
        } else if (username.length() < 8) {
            return "Tên tài khoản phải có ít nhất 8 ký tự.";
        } else if (dao.checkAccountExist(username)) {
            return "Tài khoản đã tồn tại! Vui lòng chọn tên khác.";
        }

        if (fullname == null || fullname.isEmpty()) {
            return "Vui lòng nhập tên đầy đủ.";
        } else if (fullname.length() < 8) {
            return "Tên đầy đủ phải có ít nhất 8 ký tự.";
        } else if (dao.checkfullnameExist(fullname)) {
            return "Tên đầy đủ đã tồn tại! Vui lòng chọn tên khác.";
        }

        if (email == null || email.isEmpty()) {
            return "Vui lòng nhập email.";
        } else if (dao.checkemailExist(email)) {
            return "Email này đã được sử dụng! Vui lòng chọn email khác.";
        }

        if (userType == null || userType.isEmpty()) {
            return "Vui lòng chọn vai trò người dùng.";
        }

        if (status == null || status.isEmpty()) {
            return "Vui lòng chọn trạng thái tài khoản.";
        }
        // ✅ Kiểm tra ảnh đại diện
        if (profileImage == null || profileImage.getSize() <= 0) {
            return "Vui lòng chọn ảnh đại diện.";
        } else {
            String fileName = profileImage.getSubmittedFileName().toLowerCase();
            if (!fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg") && !fileName.endsWith(".png")) {
                return "Chỉ chấp nhận tệp .jpg, .jpeg, .png.";
            }
            if (profileImage.getSize() > 1024 * 1024 * 2) { // Giới hạn 2MB
                return "Kích thước ảnh không được vượt quá 2MB.";
            }
        }

        return null;
    }
// Tạo mật khẩu ngẫu nhiên

    public String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    // Thêm tài khoản mới
    public void insertAccount(String username, String fullname, String password, String email, String userType, String status, String imageURL) {
        dao.insertAccount(username, fullname, password, email, userType, status, imageURL);
    }
    // Cập nhật tài khoản

    public boolean updateAccount(User account) throws SQLException {
        return dao.updateAccount(account);
    }
}
