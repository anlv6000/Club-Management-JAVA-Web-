package controller.setting;

import controller.account.*;
import controller.club.*;
import controller.event.*;
import controller.setting.*;
import database.DBContext;
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

        switch (action) {
            case "/toggleStatus":
                handleToggleStatus(request, response);
                break;

            default:
                handleSettingsList(request, response);
                break;
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
            // Cập nhật chỉ trạng thái
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

        // Lấy các tham số phân trang từ request
        int currentPage = 1;
        int recordsPerPage = 5;

        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        // Lấy các tham số sắp xếp từ request
        String typeSort = request.getParameter("typeSort"); // A-Z hoặc Z-A cho loại
        String statusSort = request.getParameter("statusSort"); // A-Z hoặc Z-A cho trạng thái
        String searchKeyword = request.getParameter("searchKeyword"); // nhập từ khóa để tìm kiếm

        try {
            conn = dbContext.getConnection();

            // Xây dựng câu lệnh SQL với các tùy chọn tìm kiếm và sắp xếp
            StringBuilder sql = new StringBuilder("SELECT * FROM settings WHERE 1=1");

            // Thêm điều kiện tìm kiếm từ khóa cho SettingName hoặc UserType
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                sql.append(" AND (SettingName LIKE ? OR UserType LIKE ?)");
            }

            if (typeSort != null || statusSort != null) {
                sql.append(" ORDER BY ");
                boolean addComma = false;
                if ("az".equals(typeSort)) {
                    sql.append("UserType ASC");
                    addComma = true;
                } else if ("za".equals(typeSort)) {
                    sql.append("UserType DESC");
                    addComma = true;
                }

                if ("az".equals(statusSort)) {
                    if (addComma) {
                        sql.append(", ");
                    }
                    sql.append("Status ASC");
                } else if ("za".equals(statusSort)) {
                    if (addComma) {
                        sql.append(", ");
                    }
                    sql.append("Status DESC");
                }
            }

            // Thêm điều kiện phân trang
            sql.append(" LIMIT ?, ?");
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            int parameterIndex = 1;

            // Đặt tham số tìm kiếm từ khóa cho SettingName và UserType
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                stmt.setString(parameterIndex, "%" + searchKeyword + "%");
                parameterIndex++;
                stmt.setString(parameterIndex, "%" + searchKeyword + "%");
                parameterIndex++;
            }

            // Đặt tham số phân trang
            stmt.setInt(parameterIndex++, (currentPage - 1) * recordsPerPage);
            stmt.setInt(parameterIndex++, recordsPerPage);

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

            // Kiểm tra nếu không có kết quả tìm kiếm
            if (settingsList.isEmpty()) {
                // Truy vấn lại để lấy tất cả cài đặt
                sql = new StringBuilder("SELECT * FROM settings");
                stmt = conn.prepareStatement(sql.toString());

                rs = stmt.executeQuery();

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

                // Đặt thuộc tính báo không có kết quả tìm kiếm
                request.setAttribute("noResults", true);
            }

            // Tính toán tổng số bản ghi
            stmt = conn.prepareStatement("SELECT COUNT(*) FROM settings WHERE 1=1");

            // Thêm điều kiện tìm kiếm từ khóa cho SettingName hoặc UserType
            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                stmt = conn.prepareStatement(sql.toString().replace(" LIMIT ?, ?", ""));
                stmt.setString(1, "%" + searchKeyword + "%");
                stmt.setString(2, "%" + searchKeyword + "%");
            }
            rs = stmt.executeQuery();
            rs.next();
            int totalRecords = rs.getInt(1);
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            // Đặt thuộc tính cho request
            request.setAttribute("settingsList", settingsList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", currentPage);

            request.getRequestDispatcher("/WEB-INF/setting/SettingList.jsp").forward(request, response);
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
