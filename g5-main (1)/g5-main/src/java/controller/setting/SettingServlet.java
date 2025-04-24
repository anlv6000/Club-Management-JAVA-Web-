package controller.setting;

import service.setting.SettingService;
import DAO.dao;
import controller.account.*;
import controller.club.*;
import controller.event.*;
import controller.setting.*;
import database.DBContext;
import Model.Setting;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "SettingServlet", urlPatterns = {"/settings", "/toggleStatus"})
public class SettingServlet extends HttpServlet {

    private SettingService settingService;

    @Override
    public void init() {
        settingService = new SettingService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/toggleStatus":
                    handleToggleStatus(request, response);
                    break;
                default:
                    handleSettingsList(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request.");
        }
    }

    private void handleToggleStatus(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String currentStatus = request.getParameter("currentStatus");

        settingService.toggleStatus(id, currentStatus);
        response.sendRedirect("settings");
    }

    private void handleSettingsList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int currentPage = 1;
        int recordsPerPage = 5;

        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        String typeSort = request.getParameter("typeSort");
        String statusSort = request.getParameter("statusSort");
        String searchKeyword = request.getParameter("searchKeyword");

        List<Setting> settingsList = settingService.getSettingsList(currentPage, recordsPerPage, typeSort, statusSort, searchKeyword);
        int totalRecords = settingService.getTotalSettingsCount(searchKeyword);
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        request.setAttribute("settingsList", settingsList);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);

        request.getRequestDispatcher("/WEB-INF/setting/SettingList.jsp").forward(request, response);
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
}