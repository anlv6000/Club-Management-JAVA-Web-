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
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EditSettingServlet", urlPatterns = {"/updateSetting"})
public class EditSettingServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String value = request.getParameter("value");
        String priorityStr = request.getParameter("priority");
        String status = request.getParameter("status");
        String userType = request.getParameter("userType");
        String description = request.getParameter("description");

        int id = 0;
        int priority = 0;
        try {
            if (idStr != null && !idStr.trim().isEmpty()) {
                id = Integer.parseInt(idStr);
            }
            if (priorityStr != null && !priorityStr.trim().isEmpty()) {
                priority = Integer.parseInt(priorityStr);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("Invalid input for id or priority");
            return;
        }

        // In ra giá trị kiểm tra
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Value: " + value);
        System.out.println("Priority: " + priority);
        System.out.println("Status: " + status);
        System.out.println("UserType: " + userType);
        System.out.println("Description: " + description);

        List<Setting> settings = new ArrayList<>();
        settings.add(new Setting(id, name, type, value, priority, status, userType, description));

        dao dao = new dao();
        try {
            dao.updateSettings(settings);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error: " + e.getMessage());
        }

        // Redirect to settings list with success parameter
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
        return "Servlet to update settings";
    }
}
