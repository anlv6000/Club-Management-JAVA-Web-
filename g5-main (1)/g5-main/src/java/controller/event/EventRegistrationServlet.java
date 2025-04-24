/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.event;

import DAO.EventRegistrationDAO;
import Model.Event;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.EventRegistration;
import service.eventregistration.EventService;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EventRegistrationServlet", urlPatterns = {"/EventRegistrationServlet"})
public class EventRegistrationServlet extends HttpServlet {
    private final EventService eventService;

    public EventRegistrationServlet() {
        this.eventService = new EventService();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EventRegistrationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EventRegistrationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("Home.jsp");
            return;
        }

        int currentUserId = (int) session.getAttribute("userId");
        List<EventRegistration> registered = new ArrayList<>();
        String eventIdParam = request.getParameter("event");
        int eventId = -1;

        if (eventIdParam != null && !eventIdParam.isEmpty()) {
            try {
                eventId = Integer.parseInt(eventIdParam);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Lấy eventId theo userId nếu không có eventId từ request
        if (eventId == -1) {
            try {
                eventId = eventService.getEventIdByEventLeaderId(currentUserId); // Phương thức mới trong service
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("accessDenied.jsp");
                return;
            }
        }

        try {
            List<String> roles = eventService.getRoles();
            List<String> statuses = eventService.getStatuses();
            List<Event> events = eventService.getEventsByEventLeaderId(currentUserId);
            request.setAttribute("event", events);
            request.setAttribute("roles", roles);
            request.setAttribute("statuses", statuses);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("accessDenied.jsp");
            return;
        }

        // Lấy tên sự kiện từ eventId
        String eventName = "Tên sự kiện không xác định";
        try {
            eventName = eventService.getEventNameById(eventId);
        } catch (SQLException ex) {
            Logger.getLogger(EventRegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EventRegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("eventName", eventName);

        // Lấy thông tin lọc từ request
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        String keyword = request.getParameter("keyword");

        if ("All".equals(role)) role = null;
        if ("All".equals(status)) status = null;

        try {
            if ((role != null && !role.isEmpty()) || (status != null && !status.isEmpty()) || (keyword != null && !keyword.isEmpty())) {
                registered = eventService.searchMembers(role, status, keyword, eventId);
            } else {
                registered = eventService.getRegistrationsByEventId(eventId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            registered = new ArrayList<>();
        }

        // Phân trang
        int page = 1;
        int recordsPerPage = 4;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        int start = (page - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, registered.size());
        List<EventRegistration> pageAccounts = registered.subList(start, end);

        // Set các thuộc tính cho request
        request.setAttribute("eventRegistration", pageAccounts);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", (int) Math.ceil((double) registered.size() / recordsPerPage));

        // Hiển thị thông báo nếu có
        String message = (String) session.getAttribute("message");
        if (message != null) {
            request.setAttribute("message", message);
            session.removeAttribute("message"); // Xóa thông báo khỏi session sau khi sử dụng
        }

        // Chuyển tiếp tới JSP
        request.getRequestDispatcher("/WEB-INF/event/registrationList.jsp").forward(request, response);
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
