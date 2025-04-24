/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.RegisterByGoogle;

/**
 *
 * @author Quandxnunxi28
 */
import DAO.dao;
import Model.GoogleAccount;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

public class OTPVerificationService {
    private dao userDao;

    public OTPVerificationService() {
        this.userDao = new dao();
    }

    public boolean verifyOTP(String email, String otpInput, HttpSession session) throws SQLException {
        String otpSession = (String) session.getAttribute("otpgg");

        if (otpInput != null && otpInput.equals(otpSession)) {
            GoogleAccount acc = (GoogleAccount) session.getAttribute("account");
            userDao.saveGoogleAccount(acc, email);

            GoogleAccount savedAccount = userDao.loginggg(email);
            session.setAttribute("googleAccount", acc);
            session.setAttribute("name", acc.getName());
            session.setAttribute("accc", savedAccount);

            return true; 
        }
        return false; 
    }
    public String success(){
        return "Register Successfully";
    }
}