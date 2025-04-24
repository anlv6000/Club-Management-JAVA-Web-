/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Project;

import DAO.projectDao;
import Model.Project;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ProjectService;

/**
 *
 * @author admin
 */
@WebServlet("/ProjectDetails")
public class ProjectDetailsServlet extends HttpServlet {
    private ProjectService service = new ProjectService();

    @Override
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectId = request.getParameter("projectId");

        if (projectId != null) {
            Project project = service.getProjectById(Integer.parseInt(projectId));
            request.setAttribute("project", project);
            request.getRequestDispatcher("/WEB-INF/project/ProjectDetails.jsp").forward(request, response);
        } else {
            response.sendRedirect("/WEB-INF/project/ProjectList.jsp");
        }
    }
}
