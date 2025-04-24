package controller.setting;

import service.setting.SettingFormService;
import DAO.dao;
import Model.Setting;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "NewSettingServlet", urlPatterns = {"/newSetting"})
public class NewSettingServlet extends HttpServlet {

    private SettingFormService settingFormService;

    @Override
    public void init() {
        settingFormService = new SettingFormService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/setting/settingForm.jsp").forward(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String value = request.getParameter("value");
        String priorityStr = request.getParameter("priority");
        String status = request.getParameter("status");
        String type = request.getParameter("type");
        String roleIdStr = request.getParameter("roleId");
        String description = request.getParameter("description");

        // Validate the form
        Map<String, String> errors = settingFormService.validateForm(name, value, priorityStr, status, type, roleIdStr);

        if (!errors.isEmpty()) {
            // If there are validation errors, set them as request attributes
            request.setAttribute("errors", errors);
            request.setAttribute("name", name);
            request.setAttribute("value", value);
            request.setAttribute("priority", priorityStr);
            request.setAttribute("status", status);
            request.setAttribute("type", type);
            request.setAttribute("roleId", roleIdStr);
            request.setAttribute("description", description);
            request.getRequestDispatcher("/WEB-INF/setting/settingForm.jsp").forward(request, response);
        } else {
            try {
                // If no errors, save the setting
                int priority = Integer.parseInt(priorityStr);
                int roleId = Integer.parseInt(roleIdStr);
                settingFormService.saveSetting(name, type, value, priority, status, roleId, description);
                response.sendRedirect("settings?success=true");
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write("SQL Error: " + e.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
