/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.event;

import controller.account.*;
import controller.club.*;
import controller.event.*;
import controller.setting.*;
import DAO.dao;
import DAO.projectDao;
import Model.Club;
import Model.Event;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import service.EventHT.EventService;

/**
 *
 * @author User
 */
public class SearchEvent extends HttpServlet {
   
    private EventService eventService = new EventService(); // Khởi tạo Service

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("txtUsername");
         String searchKeyword = request.getParameter("searchQuery");
        // Lấy page từ request, mặc định là 1
        int page = 1;
        int pageSize = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        try {
              eventService.updateEventStatus();
            // Gọi Service để lấy dữ liệu
            int userID = eventService.getUserIDByUsername(username);
            User u = eventService.getUserByUserID(userID);
            String RoleID =u.getRole();
            if("6".equals(RoleID)){
                 List<Event> eventList = eventService.getEventNameByPage(page, pageSize,searchKeyword);
                  int totalEvents = eventService.getTotalEvents();
            int totalPages = (int) Math.ceil((double) totalEvents / pageSize);

            // Lưu dữ liệu vào request để gửi đến JSP
             request.setAttribute("servlet", "searchEvent");
            request.setAttribute("queryParams", "&searchQuery=" + searchKeyword);
            request.setAttribute("e", eventList);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
          // Nếu cần hiển thị danh sách club
            }else{
            List<Integer> clubIDs = eventService.getClubIDsByUserID(userID);
            List<Club> clubList = eventService.getClubsByClubIDs(clubIDs);
          
            List<Event> eventList = eventService.getEventByPageAndClubIDsSearh(page, pageSize, clubIDs,searchKeyword,null);
            
            
            int totalEvents = eventService.getTotalEventsByNameAndClubIDs(searchKeyword, clubIDs,null);
            int totalPages = (int) Math.ceil((double) totalEvents / pageSize);

            // Lưu dữ liệu vào request để gửi đến JSP
            request.setAttribute("servlet", "searchEvent");
            request.setAttribute("queryParams", "&searchQuery=" + searchKeyword);
            request.setAttribute("e", eventList);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("clubList", clubList); // Nếu cần hiển thị danh sách club
            }
            // Chuyển tiếp đến JSP
            request.getRequestDispatcher("/WEB-INF/event/EventList.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response); // Có thể thêm logic riêng nếu cần
    }

    @Override
    public String getServletInfo() {
        return "Servlet to list events by page and club IDs";
    }

}
