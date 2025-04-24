/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.event;

import DAO.EventRegistrationDAO;
import DAO.dao;
import Model.EventRegistration;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.eventregistration.EventService;
import util.EmailUtility;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EventRegistrationDetail", urlPatterns = {"/EventRegistrationDetail"})
public class EventRegistrationDetail extends HttpServlet {

    private final EventService eventService;

    public EventRegistrationDetail() {
        this.eventService = new EventService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            int registrationId = Integer.parseInt(request.getParameter("ID"));

            EventRegistration registered = eventService.getRegistrationDetailsById(registrationId);
            if (registered != null) {
                request.setAttribute("eventRegistration", registered);
            }

            List<String> statuses = eventService.getStatuses();
            request.setAttribute("statuses", statuses);

            request.getRequestDispatcher("/WEB-INF/event/registrationListdetail.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra khi lấy dữ liệu.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        try {
            // Kiểm tra userID từ request
            String userIdParam = request.getParameter("ID");
            if (userIdParam == null || userIdParam.isEmpty()) {
                // Trường hợp userID null hoặc rỗng
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing userID parameter.");
                return;
            }

            // Phân tích cú pháp chỉ khi userID không null/rỗng
            int registrationId = Integer.parseInt(userIdParam);
            String status = request.getParameter("status");

            boolean updated = eventService.updateRegistrationStatusById(registrationId, status);
            EventRegistration registered = eventService.getRegistrationDetailsById(registrationId);

            if (updated) {
                session.setAttribute("message", "Update status successful!");

                String recipient = registered.getEmail();
                if (recipient != null && !recipient.isEmpty()) {
                    String subject = "Event registration form approval notice";
                    String content = "Congratulations on being accepted as a member of the event!";
                    EmailUtility.sendEmail(recipient, subject, content);
                }
            } else {
                session.setAttribute("message", "Update status failed!");

                String recipient = registered.getEmail();
                if (recipient != null && !recipient.isEmpty()) {
                    String subject = "Event registration form reject notice";
                    String content = "Sorry, your registration was not accepted.";
                    EmailUtility.sendEmail(recipient, subject, content);
                }
            }

            response.sendRedirect("EventRegistrationServlet");

        } catch (NumberFormatException ex) {
            // Xử lý trường hợp userID không phải số
            Logger.getLogger(EventRegistrationDetail.class.getName()).log(Level.SEVERE, "Invalid userID format: " + ex.getMessage(), ex);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid userID parameter. It must be a number.");
        } catch (Exception ex) {
            // Xử lý lỗi hệ thống khác
            Logger.getLogger(EventRegistrationDetail.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating registration status.");
        }
    }
}
