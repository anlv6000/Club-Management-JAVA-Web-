package controller.setting;

import controller.account.*;
import controller.club.*;
import controller.event.*;
import controller.setting.*;
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
        int priority = 0;
        try {
            priority = Integer.parseInt(request.getParameter("priority"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().write("Invalid priority value.");
            return;
        }
        String status = request.getParameter("status");
        String userType = request.getParameter("userType");
        String description = request.getParameter("description");

        // Kiểm tra và sửa dữ liệu đầu vào SettingType
        if (!type.equals("String") && !type.equals("Integer") && !type.equals("Boolean") && !type.equals("Date") && !type.equals("Text")) {
            response.getWriter().write("Invalid SettingType value.");
            return;
        }

        // Tạo danh sách các thiết lập mới từ dữ liệu đầu vào
        List<Setting> settings = new ArrayList<>();
        settings.add(new Setting(0, name, type, value, priority, status, userType, description));

        dao dao = new dao();
        try {
            dao.addSettings(settings);
            System.out.println("Settings added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("SQL Error: " + e.getMessage());
            return;
        }
        
        // Chuyển hướng đến trang settings
        response.sendRedirect("settings?success=true");
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


