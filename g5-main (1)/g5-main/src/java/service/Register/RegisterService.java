package service.Register;
import DAO.dao;
import Constant.OTPggSignUp;
import Model.User;
import org.mindrot.jbcrypt.BCrypt;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

public class RegisterService {
    
    private dao dao;

    public RegisterService() {
        this.dao = new dao();
    }

    public boolean registerUser(HttpServletRequest request) throws SQLException {
        String fullname = request.getParameter("fullname");
        String user = request.getParameter("user");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("re_pass");

        HttpSession session = request.getSession();
        boolean hasErrors = false;

        // Validate fullname
        if (fullname == null || fullname.trim().isEmpty() || 
            !fullname.matches("[a-zA-ZÀ-ỹ0-9_ ]+") || fullname.length() >= 30) {
            request.setAttribute("erf", "Invalid fullname. Only letters, digits, underscores, and spaces are allowed, or too long.");
            hasErrors = true;
        }

        // Validate username
        if (user == null || user.trim().isEmpty() || !user.matches("[a-zA-Z0-9_]+") || user.length() >= 30) {
            request.setAttribute("eru", "Invalid username. Only letters, digits, and underscores are allowed, or too long.");
            hasErrors = true;
        }

        // Validate password
        if (pass == null || pass.length() < 8 || !pass.matches(".*[A-Z].*") || 
            !pass.matches(".*[a-z].*") || !pass.matches(".*\\d.*") || 
            !pass.matches(".*[!@#$%^&*].*")) {
            request.setAttribute("erp", "Password must be at least 8 characters long and contain uppercase, lowercase, digits, and special characters.");
            hasErrors = true;
        }

        // Check password match
        if (!pass.equals(re_pass)) {
            request.setAttribute("err", "Passwords do not match.");
            hasErrors = true;
        }

        // Validate email format
        if (!email.contains("@")) {
            request.setAttribute("erre", "Invalid email format.");
            hasErrors = true;
        }

        // Check if username or email already exists
        if (dao.checkAccountExist(user) || dao.checkemailExist(email)) {
            request.setAttribute("mess", "Username or Email is already taken.");
            hasErrors = true;
        }   

        if (hasErrors) {
            return false;
        }

        // Hash password
        String hashedPassword = BCrypt.hashpw(pass, BCrypt.gensalt());

        session.setAttribute("hashedPassword", hashedPassword);
        session.setAttribute("email", email);
        session.setAttribute("user", user);
        session.setAttribute("fullname", fullname);

        // Generate and send OTP
        String otp = OTPggSignUp.generateOTP(6);
        boolean isSent = OTPggSignUp.sendOTP(email, otp);

        if (isSent) {
            session.setAttribute("otpgg", otp);
            session.setMaxInactiveInterval(1800);
            return true;
        }

        return false;
    }
}
