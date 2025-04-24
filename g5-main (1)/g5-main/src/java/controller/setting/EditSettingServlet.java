package controller.setting;

import Model.Setting;
import service.setting.SettingFormService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EditSettingServlet", urlPatterns = {"/updateSetting"})
public class EditSettingServlet extends HttpServlet {

    private SettingFormService settingFormService;

    @Override
    public void init() {
        settingFormService = new SettingFormService(); // Initialize the service
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/setting/settingForm.jsp").forward(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String value = request.getParameter("value");
        String priorityStr = request.getParameter("priority");
        String status = request.getParameter("status");
        String type = request.getParameter("type");
        String roleIdStr = request.getParameter("roleId");
        String description = request.getParameter("description");

        // Validate the form using the service
        Map<String, String> errors = settingFormService.validateForm(name, value, priorityStr, status, type, roleIdStr);

        if (!errors.isEmpty()) {
            // If there are errors, forward them back to the form with the input data
            request.setAttribute("errors", errors);
            request.setAttribute("id", idStr);
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
                // Prepare the Setting object for updating
                int id = idStr != null && !idStr.trim().isEmpty() ? Integer.parseInt(idStr) : 0;
                int priority = Integer.parseInt(priorityStr);
                int roleId = Integer.parseInt(roleIdStr);

                Setting setting = new Setting(id, name, type, value, priority, status, roleId, description);

                // Save the updated setting using the service
                settingFormService.updateSetting(setting);

                // Redirect to the settings list with success message
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
