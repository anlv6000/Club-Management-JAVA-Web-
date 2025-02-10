package Controller;

import DAO.dao;
import Model.Account;
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
        String email = request.getParameter("email");
        String userType = request.getParameter("usertype");
        String status = request.getParameter("status");

        dao dao = new dao();

        if (username == null || username.isEmpty() || email == null || email.isEmpty()) {
            request.setAttribute("mess", "Vui lòng nhập đầy đủ thông tin.");
            request.getRequestDispatcher("AddAccount.jsp").forward(request, response);
            return;
        }

        if (dao.checkAccountExist(username)) {
            request.setAttribute("mess", "Tài khoản đã tồn tại! Vui lòng chọn tên khác.");
            request.getRequestDispatcher("AddAccount.jsp").forward(request, response);
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
        }

        // Lưu tài khoản vào database
        dao.insertAccount(username, password, email, userType, status, imageURL);

        // Gửi email mật khẩu
        sendEmail(email, password);

        // Chuyển hướng về danh sách tài khoản
        List<Account> accounts = dao.getAllAccounts();
        request.setAttribute("accountlist", accounts);
        request.getRequestDispatcher("Account.jsp").forward(request, response);
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
            message.setContent("<p>Chào bạn,</p>" +
                   "<p>Tài khoản của bạn đã được tạo thành công.</p>" +
                   "<p><b>Tên đăng nhập:</b> " + recipientEmail + "</p>" +
                   "<p><b>Mật khẩu:</b> " + password + "</p>" +
                   "<p>Vui lòng đăng nhập và thay đổi mật khẩu.</p>" +
                   "<p>Trân trọng,<br>Hệ thống quản lý tài khoản</p>",
                   "text/html; charset=UTF-8");


            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
