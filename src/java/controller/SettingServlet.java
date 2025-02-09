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

@WebServlet(name = "SettingServlet", urlPatterns = {"/settings", "/toggleStatus"})
public class SettingServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getServletPath();

        if ("/toggleStatus".equals(action)) {
            handleToggleStatus(request, response);
        } else {
            handleSettingsList(request, response);
        }
    }

    private void handleToggleStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String currentStatus = request.getParameter("currentStatus");

        int id = 0;
        if (idStr != null && !idStr.trim().isEmpty()) {
            id = Integer.parseInt(idStr);
        }

        // Đổi trạng thái giữa Active và Inactive
        String newStatus = "Active".equals(currentStatus) ? "Inactive" : "Active";

        DBContext dbContext = new DBContext();
        try (Connection conn = dbContext.getConnection()) {
            String sql = "UPDATE Settings SET Status = ? WHERE SettingID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, newStatus);
                stmt.setInt(2, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Chuyển hướng lại trang danh sách thiết lập
        response.sendRedirect("settings");
    }

    private void handleSettingsList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBContext dbContext = new DBContext();
        Connection conn = null;
        ArrayList<Setting> settingsList = new ArrayList<>();

        // Get sorting parameters from the request
        String typeSort = request.getParameter("typeSort"); // A-Z hoặc Z-A cho loại
        String statusSort = request.getParameter("statusSort"); // A-Z hoặc Z-A cho trạng thái
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
                parameterIndex++;
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
                        rs.getString("UserType"),
                        rs.getString("Description") // thêm Description
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
        return "Servlet to manage settings including toggling status";
    }
}
