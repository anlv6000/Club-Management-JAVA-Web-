import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet("/SettingServlet")
public class SettingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Simulate getting data from a database (replace with your database logic)
        List<Setting> settingList = new ArrayList<>();
        settingList.add(new Setting(1, "Name 1", "Type 1", "Value 1", 1, "Active"));
        settingList.add(new Setting(2, "Name 2", "Type 2", "Value 2", 2, "Inactive"));
        settingList.add(new Setting(3, "Name 3", "Type 3", "Value 3", 3, "Active"));

        // Set the setting list as a request attribute
        request.setAttribute("settingList", settingList);

        // Forward the request to the JSP page
        request.getRequestDispatcher("settingList.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST requests here, if needed
    }
}
