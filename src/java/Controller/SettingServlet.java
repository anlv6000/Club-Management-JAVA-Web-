package Controller;

import context.DBContext;
import Model.Setting;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/settings")
public class SettingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBContext dbContext = new DBContext();
        Connection conn = null;
        ArrayList<Setting> settingsList = new ArrayList<>();

        try {
            conn = dbContext.getConnection();
            String sql = "SELECT * FROM settings";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Setting setting = new Setting();
                setting.setId(rs.getInt("SettingID"));
                setting.setName(rs.getString("SettingName"));
                setting.setType(rs.getString("SettingType"));
                setting.setValue(rs.getString("SettingValue"));
                setting.setPriority(rs.getInt("Priority"));
                setting.setStatus(rs.getString("Status"));
                setting.setUserType(rs.getString("UserType"));
                settingsList.add(setting);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbContext.closeConnection(conn);
        }

        request.setAttribute("settingsList", settingsList);
        request.getRequestDispatcher("SettingList.jsp").forward(request, response);
    }
}
