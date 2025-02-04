package Controller;

import Dal.DBContext;
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

@WebServlet(name = "SettingServlet", urlPatterns = {"/settings"})
public class SettingServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        DBContext dbContext = new DBContext();
        Connection conn = null;
        ArrayList<Setting> settingsList = new ArrayList<>();

        // Get sorting parameters from the request
        String typeSort = request.getParameter("typeSort"); // A-Z hoặc Z-A cho loại
        String statusSort = request.getParameter("statusSort"); // A-Z hoặc Z-A cho trạng thá
        String searchKeyword = request.getParameter("searchKeyword"); // nhập từ khóa để tìm kiếm

        try {
            conn = dbContext.getConnection();

            // Build the SQL query with search and sorting options
            StringBuilder sql = new StringBuilder("SELECT * FROM settings WHERE 1=1");

            // Adding search keyword condition
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                sql.append(" AND SettingName LIKE ?");
            }

            if (typeSort != null || statusSort != null) {
                sql.append(" ORDER BY ");
                if ("az".equals(typeSort)) {
                    sql.append("SettingType ASC, ");
                } else if ("za".equals(typeSort)) {
                    sql.append("SettingType DESC, ");
                }

                if ("az".equals(statusSort)) {
                    sql.append("Status ASC, ");
                } else if ("za".equals(statusSort)) {
                    sql.append("Status DESC, ");
                }
                // Remove trailing comma if present
                if (sql.charAt(sql.length() - 2) == ',') {
                    sql.setLength(sql.length() - 2);
                }
            }

            PreparedStatement stmt = conn.prepareStatement(sql.toString());
            int parameterIndex = 1;

            // Set search keyword parameter
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                stmt.setString(parameterIndex, "%" + searchKeyword + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Setting setting = new Setting(
                        rs.getInt("SettingID"),
                        rs.getString("SettingName"),
                        rs.getString("SettingType"),
                        rs.getString("SettingValue"),
                        rs.getInt("Priority"),
                        rs.getString("Status"),
                        rs.getString("UserType")
                );
                settingsList.add(setting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        // Set the attribute for the request
        request.setAttribute("settingsList", settingsList);
        request.getRequestDispatcher("SettingList.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
