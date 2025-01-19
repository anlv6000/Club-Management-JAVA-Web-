/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Dal.DBContext;
import Model.Account;
import Model.Public_club;
import com.sun.source.tree.ArrayAccessTree;

import org.mindrot.jbcrypt.BCrypt;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Doan Quan
 */
public class dao  extends DBContext{
  Connection con ;
  PreparedStatement stm ;
  ResultSet rs;
  
 //Register (add account)
 
  
 //Register (add account)
    public void addAcount(String username, String password) {
    String sql = "INSERT INTO users (Username, Password ) VALUES (?, ?)";
    try {
         PreparedStatement stm = getConnection().prepareStatement(sql);
        stm.setString(1, username);
        stm.setString(2, password);
        stm.executeUpdate();
        System.out.println("Thêm tài khoản thành công!");
    } catch (SQLException e) {
        System.out.println("Lỗi khi thêm tài khoản: " + e.getMessage());
        e.printStackTrace();
    }

    }
      public boolean checkAccountExist(String user) {
        String query = "select * from users \n"
                + "where Username = ? \n";
        try {
            stm = getConnection().prepareStatement(query);
            stm.setString(1, user);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }
      public Account login(String user, String pass) {
        String query = "SELECT * FROM users WHERE Username = ?";
        try {
            stm = getConnection().prepareStatement(query);
            stm.setString(1, user);
            rs = stm.executeQuery();

            if (rs.next()) {
                // Tạo đối tượng Account từ kết quả truy vấn
            Account a = new Account(rs.getString("Username"), rs.getString("Password"));

                // Kiểm tra mật khẩu đã nhập với mật khẩu hash từ cơ sở dữ liệu
                if (BCrypt.checkpw(pass, a.getPassword() )) {
                    return a; // Nếu mật khẩu đúng, trả về đối tượng Account
                } else {
                    return null; // Mật khẩu sai
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để kiểm tra
        }
        return null; // Trả về null nếu không tìm thấy tài khoản
    }
            public boolean checkLogin2(String username, String password)throws SQLException{
          String sql = " Select Username  "
                        + " FROM users  "
                        + " WHERE Username = ? "
                        + " AND Password = ? ";
          
          try {
              stm=getConnection().prepareStatement(sql);
              stm.setString(1, username);
              stm.setString(2, password);
              rs = stm.executeQuery();
              if(rs.next()){
                 
                 return true;
              }else{
                  return false;
              }
          } catch (Exception e) {
           
          } finally {
              if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
          }
    return  false;
      }
        public List<Public_club> getTop5() {
        List<Public_club> list = new ArrayList<>();
        String query = "select * from clubs LIMIT 5 ";
        try {
            stm = getConnection().prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
        list.add(new Public_club(rs.getString("ClubName"), rs.getString("Description"), rs.getString("ImageURL")));
            }
        } catch (Exception e) {
        }
        return list;
    }
}
