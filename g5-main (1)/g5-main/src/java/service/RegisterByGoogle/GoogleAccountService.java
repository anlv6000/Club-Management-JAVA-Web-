/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.RegisterByGoogle;
import DAO.dao;
import Model.GoogleAccount;
import java.sql.SQLException;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 *
 * @author Quandxnunxi28
 */
public class GoogleAccountService {
     private final dao accountDao;

    public GoogleAccountService() {
        this.accountDao = new dao();
    }
      
   public GoogleAccount loginOrRegisterGoogleAccount(String googleId, String email, String name, HttpServletRequest request, HttpServletResponse response) throws SQLException {
    GoogleAccount acc = accountDao.loginggg(email);
    HttpSession session = request.getSession();
    dao dao = new dao();
    
    List<GoogleAccount> u = dao.listUsert();
    boolean isUserExist = u.stream().anyMatch(users -> users.getId() == acc.getId());
    if (isUserExist) {
        session.setAttribute("ex", "ex");
    }
    
    List<GoogleAccount> uk = dao.listuerr();
    boolean isUserExistt = uk.stream().anyMatch(userss -> userss.getId() == acc.getId());
    if (isUserExistt) {
        session.setAttribute("ek", "ek");
    }

    if (accountDao.isGoogleAccountExist(googleId) || accountDao.checkemailExist(email)) {
        return accountDao.loginggg(email); // Lấy thông tin tài khoản từ DB
    }

    return null; // Trả về null nếu tài khoản chưa tồn tại
}
}
