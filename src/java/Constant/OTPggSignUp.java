/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Constant;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author Doan Quan
 */
public class OTPggSignUp {
    public static String generateOTP(int length) {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10)); // Chỉ lấy số từ 0-9
        }
        return otp.toString();
    }

    // Hàm gửi OTP qua email
    public static boolean sendOTP(String recipientEmail, String otpCode) {
        final String senderEmail = "xuanquanyd@gmail.com";  // Email của bạn
        final String senderPassword = "rjdf fdgh vqhn hmph"; // Mật khẩu ứng dụng (App Password)

   Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");  // Sử dụng TLS 1.2
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");  // Tin tưởng vào server Gmail

        // Tạo phiên làm việc với tài khoản Gmail
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otpCode);

            Transport.send(message);
            System.out.println("OTP sent successfully!");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        String email = "xuanquanyd@gmail.com"; // Email người nhận
        String otp = generateOTP(6); // Tạo OTP 6 số
        sendOTP(email, otp);
    }
}
