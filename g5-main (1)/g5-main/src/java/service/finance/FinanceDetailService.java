package service.finance;

import DAO.financeDAO;
import Model.Finance;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FinanceDetailService {

    private financeDAO financeDAO;

    public FinanceDetailService() {
        financeDAO = new financeDAO(); // Initialize the DAO
    }

    // Fetch a single Finance record by ID
    public Finance getFinanceById(int financeId) throws SQLException {
        return financeDAO.selectFinanceById(financeId);
    }

    // Update a Finance record with optional Proof Image
    public void updateFinanceWithImage(int financeId, String financeName, String isExpenseParam, BigDecimal amount,
            String details, String financeDateStr, String action, String newImgurl) throws SQLException {
        Finance finance = financeDAO.selectFinanceById(financeId);
        if (finance == null) {
            throw new SQLException("Finance record not found for ID: " + financeId);
        }

        // Parse date
        LocalDateTime financeDate = parseLocalDateTime(financeDateStr);

        // Determine status from the action
        String status = switch (action) {
            case "submit" ->
                "Submitted for Approval";
            case "approve" ->
                "Approve";
            case "reject" ->
                "Reject";
            default ->
                "Saved";
        };

        // Update finance fields
        finance.setFinanceName(financeName);
        finance.setIsExpense("true".equalsIgnoreCase(isExpenseParam) ? "Yes" : "No");
        finance.setAmount(amount);
        finance.setFinanceDate(financeDate);
        finance.setDetails(details);
        finance.setStatus(status);

        // Update Proof Image if provided
        if (newImgurl != null && !newImgurl.isEmpty()) {
            finance.setImgurl(newImgurl);
        }

        // Execute update via DAO
        financeDAO.updateFinance(finance);
    }

    // Delete a Finance record by ID
    public void deleteFinance(int financeId) throws SQLException {
        financeDAO.deleteFinance(financeId);
    }

    // Fetch User Type by User ID
    public String getUserTypeByUserId(int userId) throws SQLException {
        return financeDAO.getUserTypeByUserId(userId);
    }

    // Update only Proof Image for a Finance record
    public void updateProofImage(int financeId, String newImgurl) throws SQLException {
        System.out.println("Updating Proof Image for Finance ID: " + financeId + " with URL: " + newImgurl);
        financeDAO.updateProofImage(financeId, newImgurl);
    }

    // Utility: Parse LocalDateTime from string
    private LocalDateTime parseLocalDateTime(String dateTimeStr) {
        DateTimeFormatter formatterWithSeconds = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTimeFormatter formatterWithoutSeconds = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        try {
            return LocalDateTime.parse(dateTimeStr, formatterWithSeconds);
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(dateTimeStr, formatterWithoutSeconds);
        }
    }
}
