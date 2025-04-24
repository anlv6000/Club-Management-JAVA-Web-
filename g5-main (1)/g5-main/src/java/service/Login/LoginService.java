package service.Login;

import DAO.dao;
import Model.User;
import Constant.OTPggSignUp;
import java.sql.SQLException;
import java.util.ArrayList;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class LoginService {

    private dao dao;

    public LoginService() {
        this.dao = new dao();
    }

    public User authenticateUser(String username, String password) throws SQLException {
        User user = dao.login(username, password);
        if (user != null && (BCrypt.checkpw(password, user.getPassword()) || dao.checkLogin2(username, password))) {
            return user;
        }
        return null;
    }

    public boolean isUserSessionActive(HttpSession session, User user) {
        ArrayList<User> listUser = (ArrayList<User>) session.getAttribute("listuser");
        return listUser != null && listUser.stream().anyMatch(u -> u.getUserName().equals(user.getUserName()));
    }

    public boolean sendOTP(User user, HttpSession session) {
        String otp = OTPggSignUp.generateOTP(6);
        boolean isSent = OTPggSignUp.sendOTP(user.getEmail(), otp);
        if (isSent) {
            session.setAttribute("otpgg", otp);
            session.setAttribute("email", user.getEmail());
        }
        return isSent;
    }

    public void setupUserSession(HttpSession session, User user) {
        ArrayList<User> listUser = (ArrayList<User>) session.getAttribute("listuser");
        if (listUser == null) {
            listUser = new ArrayList<>();
        }
        if (!isUserSessionActive(session, user)) {
            listUser.add(user);
        }

        session.setAttribute("txtUsername", user.getUserName());
        session.setAttribute("acc", user);
        session.setAttribute("userId", user.getId());
        session.setAttribute("clubId", user.getClubs());
        session.setAttribute("role", user.getRole());
        session.setAttribute("listuser", listUser);
        session.setAttribute("fullName", user.getFullname());
        dao dao = new dao();
       List<User> u = dao.listUser();
                boolean isUserExist = u.stream().anyMatch(users -> users.getId() == user.getId());
                if (isUserExist) {
                    session.setAttribute("ex", "ex");
                }
                List<User> uk = dao.listuer();
                boolean isUserExistt = uk.stream().anyMatch(userss -> userss.getId() == user.getId());
                if (isUserExistt) {
                    session.setAttribute("ek", "ek");
                }
        session.setMaxInactiveInterval(3000);
    }
}
