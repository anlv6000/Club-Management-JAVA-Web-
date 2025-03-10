package Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Finance {

    private int financeId;
    private int clubId;
    private int memberId;
    private String financeName;
    private String isExpense;
    private BigDecimal amount;
    private LocalDateTime financeDate;
    private String details;
    private String clubName;
    private String memberName;
    private String status;
    private LocalDateTime updatedDate; // Added updatedDate field

    // Constructors
    public Finance() {
    }

    public Finance(int clubId, int memberId, String financeName, String isExpense, BigDecimal amount, LocalDateTime financeDate, String details, String clubName, String memberName, String status, LocalDateTime updatedDate) {
        this.clubId = clubId;
        this.memberId = memberId;
        this.financeName = financeName;
        this.isExpense = isExpense;
        this.amount = amount;
        this.financeDate = financeDate;
        this.details = details;
        this.clubName = clubName;
        this.memberName = memberName;
        this.status = status;
        this.updatedDate = updatedDate; // Added updatedDate field
    }

    public Finance(int financeId, int clubId, int memberId, String financeName, String isExpense, BigDecimal amount, LocalDateTime financeDate, String details, String clubName, String memberName, String status, LocalDateTime updatedDate) {
        this.financeId = financeId;
        this.clubId = clubId;
        this.memberId = memberId;
        this.financeName = financeName;
        this.isExpense = isExpense;
        this.amount = amount;
        this.financeDate = financeDate;
        this.details = details;
        this.clubName = clubName;
        this.memberName = memberName;
        this.status = status;
        this.updatedDate = updatedDate; // Added updatedDate field
    }

    public Finance(int financeId, String financeName, boolean isExpense, BigDecimal amount, String memberName, LocalDateTime financeDate, String status) {
        this.financeId = financeId;
        this.financeName = financeName;
        this.isExpense = isExpense ? "Yes" : "No";
        this.amount = amount;
        this.memberName = memberName;
        this.financeDate = financeDate;
        this.status = status;

    }


    // Getters and Setters
    public int getFinanceId() {
        return financeId;
    }

    public void setFinanceId(int financeId) {
        this.financeId = financeId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName;
    }

    public String getIsExpense() {
        return isExpense;
    }

    public void setIsExpense(String isExpense) {
        this.isExpense = isExpense;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(LocalDateTime financeDate) {
        this.financeDate = financeDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isExpense() {
        return "Yes".equalsIgnoreCase(this.isExpense);
    }

}
