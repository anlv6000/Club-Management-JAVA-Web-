/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/**
 *
 * @author Doan Quan
 */
public class PasswordUtils {
    //tạo 
    public static byte[] getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16]; // 16 bytes salt
        sr.nextBytes(salt);
        return salt;
    }
    // Phương thức băm mật khẩu với muối
    public static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt); // Thêm muối vào băm
            byte[] hashedPassword = md.digest(password.getBytes());

            // Chuyển đổi mảng byte thành chuỗi hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString(); // Trả về mật khẩu đã băm ở định dạng hex
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
