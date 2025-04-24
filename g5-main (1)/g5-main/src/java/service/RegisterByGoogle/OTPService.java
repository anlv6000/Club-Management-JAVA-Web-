package  service.RegisterByGoogle;

import Constant.OTPggSignUp;
import DAO.dao;
import Model.GoogleAccount;
import controller.account.ggRegister;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class OTPService {
    private dao userDao;

    public OTPService() {
        this.userDao = new dao();
    }

    public boolean processOTPRequest(String code, HttpSession session) throws SQLException, IOException {
        ggRegister gg = new ggRegister();
        String accessToken = gg.getToken(code);
        GoogleAccount acc = gg.getUserInfo(accessToken);

        if (userDao.isGoogleAccountExist(acc.getId()) || userDao.checkemailExist(acc.getEmail())) {
            GoogleAccount existingAccount = userDao.loginggg(acc.getEmail());
            session.setAttribute("accc", existingAccount);
            session.setAttribute("name", existingAccount.getName());
            session.setAttribute("googleAccount", existingAccount);
            return false; // Tài khoản đã tồn tại
        } else {
            String otp = OTPggSignUp.generateOTP(6);
            boolean isSent = OTPggSignUp.sendOTP(acc.getEmail(), otp);

            if (isSent) {
                session.setAttribute("email", acc.getEmail());
                session.setAttribute("account", acc);
                session.setAttribute("otpgg", otp);
                session.setAttribute("code", code);
                return true; // OTP đã gửi thành công
            } else {
                return false; // Gửi OTP thất bại
            }
        }
    }
}