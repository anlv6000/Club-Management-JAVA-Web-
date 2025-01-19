package Controller;

import DAO.dao;
import Model.Setting;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "NewSettingServlet", urlPatterns = {"/newSetting"})
public class NewSettingServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Lấy dữ liệu từ form
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String value = request.getParameter("value");
        int priority = Integer.parseInt(request.getParameter("priority"));
        String status = request.getParameter("status");
        String userType = request.getParameter("userType");

        // Tạo danh sách các thiết lập mới từ dữ liệu đầu vào
        List<Setting> settings = new ArrayList<>();
        settings.add(new Setting(0, name, type, value, priority, status, userType));

        dao dao = new dao();
        try {
            dao.addSettings(settings);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        }

        // Chuyển hướng đến trang settings
        response.sendRedirect("settings");
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
