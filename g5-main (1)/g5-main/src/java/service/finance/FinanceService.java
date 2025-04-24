package service.finance;

import DAO.ClubDAO;
import DAO.financeDAO;
import Model.Club;
import Model.Finance;
import database.DBContext;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class FinanceService {

    private final financeDAO financeDAO;

    public FinanceService() {
        financeDAO = new financeDAO();
    }

    // Fetch paginated finances for the default view
    public List<Finance> getPaginatedFinances(int userId, int currentPage, int recordsPerPage) throws SQLException {
        int start = (currentPage - 1) * recordsPerPage;
        return financeDAO.selectFinancesByUserWithPagination(userId, start, recordsPerPage);
    }

    // Fetch filtered finances with pagination for the filtered view
    public List<Finance> getFilteredFinancesWithPagination(int userId, String searchQuery, String expenseFilter, String statusFilter, int start, int recordsPerPage) throws SQLException {
        return financeDAO.getFilteredFinancesWithPagination(userId, searchQuery, expenseFilter, statusFilter, start, recordsPerPage);
    }

    // Get total finance count for the default view
    public int getTotalFinanceCountByUser(int userId) throws SQLException {
        return financeDAO.getTotalFinanceCountByUser(userId);
    }

    // Get total filtered finance count for the filtered view
    public int getTotalFilteredFinanceCount(int userId, String searchQuery, String expenseFilter, String statusFilter) throws SQLException {
        return financeDAO.getTotalFilteredFinanceCount(userId, searchQuery, expenseFilter, statusFilter);
    }
    // Get Paginated Finances

    public List<Finance> getFilteredFinances(String searchQuery, String expenseFilter, String statusFilter, int userId) throws SQLException {
        return financeDAO.getFilteredFinances(searchQuery, expenseFilter, statusFilter, userId);
    }

    // Fetch clubs associated with a specific user
    public List<Club> getClubsByUserId(int userId) throws SQLException {
        return ClubDAO.selectClubsByUser(userId);
    }

    // Insert a new finance record
    public void insertFinance(int userId, int clubId, String financeName, String isExpense, LocalDateTime financeDate, String details, BigDecimal amount, String imgurl) throws SQLException {
        Finance finance = new Finance();
        finance.setClubId(clubId);
        finance.setMemberId(userId);
        finance.setFinanceName(financeName);
        finance.setIsExpense(isExpense);
        finance.setFinanceDate(financeDate);
        finance.setDetails(details);
        finance.setAmount(amount);
        finance.setImgurl(imgurl); // URL hình ảnh
        financeDAO.insertFinance(finance);
    }

    // Check if finance access is active (for security or feature toggles)
    public boolean isFinanceAccessActive() {
        boolean isActive = false;
        String sql = "SELECT Status FROM Settings WHERE SettingName = 'FinanceAccess'";
        try (var conn = new DBContext().getConnection(); var pstmt = conn.prepareStatement(sql); var rs = pstmt.executeQuery()) {

            if (rs.next()) {
                String status = rs.getString("Status");
                isActive = "Active".equalsIgnoreCase(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isActive;
    }
}
