package util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailUtility {
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";
    private static final String USER = "tuan71105@gmail.com"; // Thay bằng email của bạn
    private static final String PASS = "mmiu jbwz mefw ofzq";        // Thay bằng mật khẩu của bạn

    public static void sendEmail(String recipient, String subject, String content) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER, PASS);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USER));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
         // Thiết lập UTF-8 cho nội dung email
        message.setContent(content, "text/html; charset=UTF-8");

        Transport.send(message);
    }
}
