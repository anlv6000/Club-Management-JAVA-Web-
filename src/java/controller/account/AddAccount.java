package controller.account;

import DAO.dao;
import Model.User;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.PrintWriter;

@WebServlet(name = "AddAccount", urlPatterns = {"/AddAccount"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddAccount extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String userType = request.getParameter("usertype");
        String status = request.getParameter("status");

        dao dao = new dao();

        String usernameError = "";
         String fullnameError = "";
        String emailError = "";
        String usertypeError = "";
        String statusError = "";
        String imageError = "";

        // Kiểm tra các trường thông tin
        if (username == null || username.isEmpty()) {
            usernameError = "Vui lòng nhập tên tài khoản.";
        } else if (username.length() < 8) {
            usernameError = "Tên tài khoản phải có ít nhất 8 ký tự.";
        } else if (dao.checkAccountExist(username)) {
            usernameError = "Tài khoản đã tồn tại! Vui lòng chọn tên khác.";
        }
        if (fullname == null || fullname.isEmpty()) {
            fullnameError = "Vui lòng nhập tên đầy đủ tài khoản.";
        } else if (fullname.length() < 8) {
            fullnameError = "Tên tài khoản phải có ít nhất 8 ký tự.";
            } else if (dao.checkfullnameExist(fullname)) {
            fullnameError = "Tài khoản đã tồn tại! Vui lòng chọn tên khác.";
        }

        if (email == null || email.isEmpty()) {
            emailError = "Vui lòng nhập email.";
        } else if (dao.checkemailExist(email)) {
            emailError = "Email này đã được sử dụng! Vui lòng chọn email khác.";
        }

        if (userType == null || userType.isEmpty()) {
            usertypeError = "Vui lòng chọn vai trò người dùng.";
        }

        if (status == null || status.isEmpty()) {
            statusError = "Vui lòng chọn trạng thái tài khoản.";
        }

        // Nếu có lỗi, chuyển về trang AddAccount.jsp với thông báo lỗi
        if (!usernameError.isEmpty() || !emailError.isEmpty() || !usertypeError.isEmpty() || !statusError.isEmpty()) {
            request.setAttribute("usernameError", usernameError);
            request.setAttribute("fullnameError", fullnameError);
            request.setAttribute("emailError", emailError);
            request.setAttribute("usertypeError", usertypeError);
            request.setAttribute("statusError", statusError);
            request.setAttribute("imageError", imageError);
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("usertype", userType);
            request.setAttribute("status", status);
            request.getRequestDispatcher("/WEB-INF/account/AddAccount.jsp").forward(request, response);
            return;
        }

        // Tạo mật khẩu ngẫu nhiên
        String password = generateRandomPassword(10);

        // Xử lý ảnh đại diện
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
        } else {
            imageError = "Vui lòng chọn ảnh đại diện.";
        }

        // Lưu tài khoản vào database
        if (usernameError.isEmpty() && emailError.isEmpty() && usertypeError.isEmpty() && statusError.isEmpty() && fullnameError.isEmpty()){ 
        dao.insertAccount(username, fullname, password, email, userType, status, imageURL);
        request.getSession().setAttribute("successMessage", "Thêm tài khoản thành công!");
        

        }
// Gửi email mật khẩu
        sendEmail(email, password);

        // Chuyển hướng về danh sách tài khoản
        List<User> accounts = dao.getAllAccounts();
        request.setAttribute("accountlist", accounts);
        // Thay đổi này trong phần cuối của doPost
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.postMessage('accountAdded', '*');");
            out.println("window.parent.postMessage('successMessage', '*');"); // Gửi thông báo thành công
            out.println("window.parent.document.body.classList.remove('modal-open');");
            out.println("window.parent.location.reload();");
            out.println("</script>");
        } finally {
            out.close();
        }

    }

    // Tạo mật khẩu ngẫu nhiên
    private String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password.toString();
    }

    // Gửi email chứa mật khẩu ngẫu nhiên
    private void sendEmail(String recipientEmail, String password) {
        String senderEmail = "tuan71105@gmail.com"; // Thay bằng email của bạn
        String senderPassword = "tuet knic axgk svgw"; // Thay bằng mật khẩu ứng dụng email của bạn

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Tài khoản mới của bạn");
            message.setContent("<p>Chào bạn,</p>"
                    + "<p>Tài khoản của bạn đã được tạo thành công.</p>"
                    + "<p><b>Tên đăng nhập:</b> " + recipientEmail + "</p>"
                    + "<p><b>Mật khẩu:</b> " + password + "</p>"
                    + "<p>Vui lòng đăng nhập và thay đổi mật khẩu.</p>"
                    + "<p>Trân trọng,<br>Hệ thống quản lý tài khoản</p>",
                    "text/html; charset=UTF-8");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
