package controller.profile;

import Constant.OTPggSignUp;
import DAO.UserDao;
import Model.UserProfile;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet("/userProfile")
public class UserProfileServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDao(); // Khởi tạo DAO
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/Login.jsp");
            return;
        }
        String pass = (String) session.getAttribute("pass");
        int userId = (int) session.getAttribute("userId");
        String action = request.getParameter("action");

        try {

            // Xử lý cập nhật Fullname và Phone
            if ("updatePhoneName".equals(action)) {
                String fullname = request.getParameter("fullname");
                String phoneStr = request.getParameter("phone");
                double phone = 0;

                // Kiểm tra trường phoneStr
                if (phoneStr != null && !phoneStr.isEmpty()) {
                    try {
                        phone = Double.parseDouble(phoneStr);
                    } catch (NumberFormatException e) {
                        request.setAttribute("errorMessage", "Số điện thoại không hợp lệ: " + phoneStr);
                        // Forward về JSP với thông báo lỗi, dừng xử lý thêm
                        List<UserProfile> userProfiles = userDao.getUserProfilesByUserID(userId);
                        request.setAttribute("userProfiles", userProfiles);
                        request.getRequestDispatcher("/WEB-INF/user/userProfile.jsp").forward(request, response);
                        return;
                    }
                }

                // Kiểm tra trường fullname
                if (fullname == null || fullname.isEmpty()) {
                    request.setAttribute("errorMessage", "Họ và tên không được để trống.");
                    // Forward về JSP với thông báo lỗi
                    List<UserProfile> userProfiles = userDao.getUserProfilesByUserID(userId);
                    request.setAttribute("userProfiles", userProfiles);
                    request.getRequestDispatcher("/WEB-INF/user/userProfile.jsp").forward(request, response);
                    return;
                }

                // Xử lý cập nhật thông tin
                boolean updateSuccess = userDao.updateFullnameAndPhone(userId, fullname, phone);
                if (updateSuccess) {
                    request.setAttribute("SuccessMessage", "Cập nhật Họ và tên và Số điện thoại thành công!");
                } else {
                    request.setAttribute("errorMessage", "Đã xảy ra lỗi khi cập nhật thông tin.");
                }

                // Tải lại dữ liệu người dùng và chuyển tiếp về trang JSP
                List<UserProfile> userProfiles = userDao.getUserProfilesByUserID(userId);
                request.setAttribute("userProfiles", userProfiles);
                request.getRequestDispatcher("/WEB-INF/user/userProfile.jsp").forward(request, response);
            } else if ("updateEmail".equals(action)) {
                String email = request.getParameter("email");

                try {
                    if (email == null || email.isEmpty() || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                        // Email không hợp lệ
                        request.setAttribute("errorMessage", "Email không hợp lệ. Vui lòng nhập địa chỉ email chính xác.");
                        request.setAttribute("openEmailModal", true);
                    } else if (userDao.checkEmailExist(email)) {
                        // Email đã tồn tại trong hệ thống
                        request.setAttribute("errorMessage", "Email đã tồn tại trong hệ thống. Vui lòng chọn email khác.");
                        request.setAttribute("openEmailModal", true);
                    } else {
                        // Tạo OTP và gửi email
                        String otp = OTPggSignUp.generateOTP(6);
                        boolean isSent = OTPggSignUp.sendOTP(email, otp);

                        if (isSent) {
                            session = request.getSession();
                            session.setAttribute("otpgg", otp);
                            session.setAttribute("newEmail", email);
                            session.setMaxInactiveInterval(1800); // 30 phút

                            // Chuyển đến trang nhập OTP
                            request.getRequestDispatcher("/WEB-INF/account/OTPggInput4.jsp").forward(request, response);
                            return;
                        } else {
                            // Lỗi khi gửi OTP
                            request.setAttribute("errorMessage", "Không thể gửi OTP. Vui lòng thử lại sau.");
                            request.setAttribute("openEmailModal", true);
                        }
                    }

                    // Nếu có lỗi, tải lại dữ liệu người dùng và hiển thị thông báo lỗi
                    List<UserProfile> userProfiles = userDao.getUserProfilesByUserID(userId);
                    request.setAttribute("userProfiles", userProfiles);
                    request.getRequestDispatcher("/WEB-INF/user/userProfile.jsp").forward(request, response);

                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", "Đã xảy ra lỗi khi xử lý yêu cầu. Vui lòng thử lại.");
                    request.setAttribute("openEmailModal", true);

                    // Tải lại dữ liệu người dùng trong trường hợp gặp lỗi
                    List<UserProfile> userProfiles = userDao.getUserProfilesByUserID(userId);
                    request.setAttribute("userProfiles", userProfiles);
                    request.getRequestDispatcher("/WEB-INF/user/userProfile.jsp").forward(request, response);
                }
            } else if ("verifyOTP".equals(action)) {
                String enteredOTP = request.getParameter("otp");
                session = request.getSession(false);

                // Kiểm tra xem session có tồn tại không và OTP có hợp lệ hay không
                if (session == null || session.getAttribute("otpgg") == null) {
                    request.setAttribute("errorMessage", "OTP không hợp lệ hoặc đã hết hạn.");
                    request.getRequestDispatcher("/WEB-INF/account/OTPggInput4.jsp").forward(request, response);
                    return;
                }

                String sessionOTP = (String) session.getAttribute("otpgg");
                String newEmail = (String) session.getAttribute("newEmail");

                // So sánh OTP nhập vào với OTP trong session
                if (enteredOTP.equals(sessionOTP)) {
                    // Cập nhật email vào cơ sở dữ liệu
                    boolean updateSuccess = userDao.updateEmail(userId, newEmail);
                    if (updateSuccess) {
                        // Xóa OTP và email tạm thời khỏi session
                        session.removeAttribute("otpgg");
                        session.removeAttribute("newEmail");

                        // Thiết lập thông báo thành công
                        request.setAttribute("SuccessMessage", "Email đã được cập nhật thành công!");
                    } else {
                        request.setAttribute("errorMessage", "Đã xảy ra lỗi khi cập nhật Email.");
                    }
                } else {
                    // Xử lý trường hợp OTP không đúng
                    request.setAttribute("errorMessage", "OTP không chính xác. Vui lòng thử lại.");
                    request.getRequestDispatcher("/WEB-INF/account/OTPggInput4.jsp").forward(request, response);
                    return;
                }

                // Load lại thông tin người dùng và chuyển hướng về userProfile.jsp
                List<UserProfile> userProfiles = userDao.getUserProfilesByUserID(userId);
                request.setAttribute("userProfiles", userProfiles);
                request.getRequestDispatcher("/WEB-INF/user/userProfile.jsp").forward(request, response);
            } // Xử lý cập nhật Password
            else if ("updatePassword".equals(action)) {
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("confirmPassword");

                boolean hasError = false;

                if (password == null || password.isEmpty()) {
                    request.setAttribute("errorMessage", "Password không được để trống.");
                    hasError = true;
                } else if (confirmPassword == null || confirmPassword.isEmpty()) {
                    request.setAttribute("confirmPasswordError", "Confirm Password không được để trống.");
                    hasError = true;
                } else if (!password.equals(confirmPassword)) {
                    request.setAttribute("confirmPasswordError", "Passwords do not match.");
                    hasError = true;
                } else if (password.length() < 8
                        || !password.matches(".*[A-Z].*")
                        || !password.matches(".*[a-z].*")
                        || !password.matches(".*\\d.*")
                        || !password.matches(".*[!@#$%^&*].*")) {
                    request.setAttribute("errorMessage", "Password must be at least 8 characters long and contain uppercase, lowercase, digits, and special characters.");
                    hasError = true;
                } else {
                    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                    boolean updateSuccess = userDao.updatePassword(userId, hashedPassword);
                    if (updateSuccess) {

                        request.setAttribute("SuccessMessage", "Đổi mật khẩu thành công!");
                        session.setAttribute("pass", password);
                    } else {
                        request.setAttribute("errorMessage", "Đã xảy ra lỗi khi đổi mật khẩu.");
                        hasError = true;
                    }
                }

                // Refresh user profile data to display updated information
                List<UserProfile> userProfiles = userDao.getUserProfilesByUserID(userId);
                request.setAttribute("userProfiles", userProfiles);
                session.setAttribute("pass", password);

                if (hasError) {
                    request.setAttribute("openPasswordModal", true);
                }

                request.getRequestDispatcher("/WEB-INF/user/userProfile.jsp").forward(request, response);
            }// Xử lý cập nhật ảnh đại diện
            else if ("updateProfileImage".equals(action)) {
                Part filePart = request.getPart("profileImage");
                String imageURL = "";

                if (filePart != null && filePart.getSize() > 0) {
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                    String filePath = uploadPath + File.separator + fileName;
                    filePart.write(filePath);

                    imageURL = "uploads/" + fileName;

                    // Cập nhật ảnh vào cơ sở dữ liệu
                    boolean updateSuccess = userDao.updateProfileImage(userId, imageURL);
                    if (updateSuccess) {
                        request.setAttribute("SuccessMessage", "Ảnh đại diện đã được cập nhật!");
                    } else {
                        request.setAttribute("errorMessage", "Đã xảy ra lỗi khi cập nhật ảnh đại diện.");
                    }
                } else {
                    request.setAttribute("errorMessage", "Vui lòng chọn ảnh đại diện.");
                }
            }

            // Lấy mật khẩu từ session
            String sessionPassword = (String) session.getAttribute("pass");
            if (sessionPassword != null) {
                request.setAttribute("password", sessionPassword);
            } else {
                request.setAttribute("password", "Không có mật khẩu trong phiên hiện tại.");
            }

            // Hiển thị lại thông tin người dùng
            List<UserProfile> userProfiles = userDao.getUserProfilesByUserID(userId);
            request.setAttribute("userProfiles", userProfiles);
            request.getRequestDispatcher("/WEB-INF/user/userProfile.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Lỗi khi xử lý yêu cầu cập nhật thông tin người dùng.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
