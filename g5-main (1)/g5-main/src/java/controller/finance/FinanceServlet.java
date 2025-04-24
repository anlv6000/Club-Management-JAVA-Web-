package controller.finance;

import Model.Club;
import service.finance.FinanceService;
import Model.Finance;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet("/finance")
public class FinanceServlet extends HttpServlet {

    private FinanceService financeService;

    @Override
    public void init() {
        financeService = new FinanceService();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check Finance Access Active
        if (!financeService.isFinanceAccessActive()) {
            response.sendRedirect("accessDenied.jsp");
            return;
        }

        // Check User Session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/Login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");

        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "list";
        }

        try {
            switch (action) {
                case "list":
                    listFinances(request, response, userId);
                    break;

                case "insert":
                    insertFinance(request, response, userId);
                    break;

                case "filter":
                    filterFinances(request, response, userId);
                    break;

                default:
                    listFinances(request, response, userId);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listFinances(HttpServletRequest request, HttpServletResponse response, int userId)
            throws SQLException, ServletException, IOException {
        int currentPage = 1;
        int recordsPerPage = 6;

        // Check if the user has provided the current page
        if (request.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }

        // Capture filter/search parameters from the request
        String searchQuery = request.getParameter("search");
        String expenseFilter = request.getParameter("type");
        String statusFilter = request.getParameter("status");

        // Determine if the user has applied filters or a search query
        boolean isFiltered = (searchQuery != null && !searchQuery.trim().isEmpty())
                || (expenseFilter != null && !"all".equalsIgnoreCase(expenseFilter))
                || (statusFilter != null && !"all".equalsIgnoreCase(statusFilter));

        try {
            List<Finance> financeList;
            int totalRecords;

            if (isFiltered) {
                // Fetch filtered and paginated finances
                financeList = financeService.getFilteredFinancesWithPagination(
                        userId, searchQuery, expenseFilter, statusFilter,
                        (currentPage - 1) * recordsPerPage, recordsPerPage
                );
                totalRecords = financeService.getTotalFilteredFinanceCount(userId, searchQuery, expenseFilter, statusFilter);
            } else {
                // Fetch default paginated finances
                financeList = financeService.getPaginatedFinances(userId, currentPage, recordsPerPage);
                totalRecords = financeService.getTotalFinanceCountByUser(userId);
            }

            // Calculate the total number of pages
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

            // Fetch clubs associated with the user (unchanged from default)
            List<Club> clubList = financeService.getClubsByUserId(userId);
            request.setAttribute("clubsList", clubList);

            // Set attributes for the JSP
            request.setAttribute("financeList", financeList);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("search", searchQuery); // Preserve search value
            request.setAttribute("type", expenseFilter); // Preserve type filter
            request.setAttribute("status", statusFilter); // Preserve status filter

            // Forward the request to the JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/finance/FinanceList.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching finance and club lists.");
        }
    }

    private void filterFinances(HttpServletRequest request, HttpServletResponse response, int userId)
            throws SQLException, ServletException, IOException {
        try {
            // Fetch filtering parameters
            String searchQuery = request.getParameter("search");
            String typeFilter = request.getParameter("type");
            String statusFilter = request.getParameter("status");

            // Process filtering
            List<Finance> filteredFinances = financeService.getFilteredFinances(searchQuery, typeFilter, statusFilter, userId);

            // Set attributes for the JSP
            request.setAttribute("financeList", filteredFinances);
            request.setAttribute("search", searchQuery);
            request.setAttribute("type", typeFilter);
            request.setAttribute("status", statusFilter);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/finance/FinanceList.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error filtering finances.");
        }
    }

    private void insertFinance(HttpServletRequest request, HttpServletResponse response, int userId)
            throws SQLException, ServletException, IOException {
        try {
            // Retrieve form data
            int clubId = Integer.parseInt(request.getParameter("club_id"));
            String financeName = request.getParameter("finance_name");
            String isExpense = request.getParameter("is_expense");
            String financeDateStr = request.getParameter("finance_date");
            String details = request.getParameter("details");
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime financeDate = LocalDateTime.parse(financeDateStr, formatter);

            // Process file upload
            Part filePart = request.getPart("imgurl"); // Lấy file từ input name="imgurl"
            String imgurl = "";

            if (filePart != null && filePart.getSize() > 0) {
                // Lấy tên file
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                // Đường dẫn lưu file
                String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

                // Tạo thư mục nếu chưa tồn tại
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                // Lưu file vào server
                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);

                // Lưu đường dẫn file vào biến imgurl
                imgurl = "uploads/" + fileName;
            }

            // Call Service to Insert Finance
            financeService.insertFinance(userId, clubId, financeName, isExpense, financeDate, details, amount, imgurl);

            // Redirect to finance list
            response.sendRedirect("finance?action=list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting finance record.");
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
        processRequest(request, response);
    }
}
