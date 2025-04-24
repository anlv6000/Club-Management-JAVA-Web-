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
import service.account.AccountService;

@WebServlet(name = "AddAccount", urlPatterns = {"/AddAccount"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddAccount extends HttpServlet {
        private AccountService accountService;

    @Override
    public void init() throws ServletException {
        accountService = new AccountService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String username = request.getParameter("username");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String userType = request.getParameter("usertype");
        String status = request.getParameter("status");

        Part filePart = request.getPart("profileImage");

        // ✅ Kiểm tra hợp lệ bằng AccountService
        String validationError = accountService.validateAccount(username, fullname, email, userType, status, filePart);
        if (validationError != null) {
            request.setAttribute("errorMessage", validationError);
            request.getRequestDispatcher("/WEB-INF/account/AddAccount.jsp").forward(request, response);
            return;
        }

        // ✅ Tạo mật khẩu ngẫu nhiên
        String password = accountService.generateRandomPassword(10);

        // ✅ Xử lý ảnh đại diện
        String imageURL = "";
        if (filePart != null && filePart.getSize() > 0) {
            try {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);

                imageURL = "uploads/" + fileName;
            } catch (IOException e) {
                request.setAttribute("errorMessage", "Lỗi khi tải ảnh đại diện: " + e.getMessage());
                request.getRequestDispatcher("/WEB-INF/account/AddAccount.jsp").forward(request, response);
                return;
            }
        }

        // ✅ Thêm tài khoản vào cơ sở dữ liệu
        accountService.insertAccount(username, fullname, password, email, userType, status, imageURL);
         sendEmail(email, password);

        // Chuyển hướng về danh sách tài khoản
        List<User> accounts = accountService.getAllAccounts();
        request.setAttribute("accountlist", accounts);
        // Thay đổi này trong phần cuối của doPost
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.postMessage('accountAdded', '*');");
            request.getSession().setAttribute("successMessage", "Tài khoản đã được thêm thành công!");
            out.println("window.parent.document.body.classList.remove('modal-open');");
            out.println("window.parent.location.reload();");
            out.println("</script>");
        } finally {
            out.close();
        }

    }


    // Gửi email chứa mật khẩu ngẫu nhiên
    private void sendEmail(String recipientEmail, String password) {
        String senderEmail = "tuan71105@gmail.com"; // Thay bằng email của bạn
        String senderPassword = "mmiu jbwz mefw ofzq"; // Thay bằng mật khẩu ứng dụng email của bạn

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
