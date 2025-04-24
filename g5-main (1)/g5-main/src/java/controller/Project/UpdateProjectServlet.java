/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Project;

import DAO.projectDao;
import Model.Project;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import service.ProjectService;

/**
 *
 * @author admin
 */
@WebServlet("/UpdateProjectServlet")
public class UpdateProjectServlet extends HttpServlet {
    private ProjectService service = new ProjectService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectIdParam = request.getParameter("ProjectID");
        if (projectIdParam != null) {
             int projectId = Integer.parseInt(projectIdParam);
            projectDao dao = new projectDao();
            Project project = service.getProjectById(projectId);

            if (project != null) {
                request.setAttribute("project", project);
                request.getRequestDispatcher("/WEB-INF/project/UpdateProject.jsp").forward(request, response);
            } else {
                response.getWriter().println("Project not found.");
            }
        } else {
            response.getWriter().println("Invalid Project ID.");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("ProjectID"));
        String name = request.getParameter("Name");
        String code = request.getParameter("Code");
        Date fromDate = Date.valueOf(request.getParameter("FromDate"));
        Date toDate = Date.valueOf(request.getParameter("ToDate"));
        boolean status = Boolean.parseBoolean(request.getParameter("Status"));
        
        String description = request.getParameter("Description");

        projectDao dao = new projectDao();
        service.updateProject2(projectId, name, code, fromDate, toDate, status, description);
        request.getSession().setAttribute("successMessageProject", "Update Project Succes");
        response.sendRedirect("ListProjectServlet");
    }
}
