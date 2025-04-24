package service.setting;

import DAO.dao;
import Model.Setting;
import database.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettingService {

    // Toggle the status of a setting
    public void toggleStatus(int id, String currentStatus) throws SQLException {
        String newStatus = "Active".equals(currentStatus) ? "Inactive" : "Active";
        new dao().updateStatus(id, newStatus);
    }

    // Get a paginated list of settings with optional sorting and search
    public List<Setting> getSettingsList(int currentPage, int recordsPerPage, String typeSort, String statusSort, String searchKeyword) throws SQLException {
        List<Setting> settingsList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM Settings WHERE 1=1");

        // Add search conditions
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql.append(" AND (SettingName LIKE ? OR RoleID = ?)");
        }

        // Add sorting conditions
        if (typeSort != null || statusSort != null) {
            sql.append(" ORDER BY ");
            boolean addComma = false;

            if ("az".equals(typeSort)) {
                sql.append("SettingType ASC");
                addComma = true;
            } else if ("za".equals(typeSort)) {
                sql.append("SettingType DESC");
                addComma = true;
            }

            if ("az".equals(statusSort)) {
                if (addComma) {
                    sql.append(", ");
                }
                sql.append("Status ASC");
            } else if ("za".equals(statusSort)) {
                if (addComma) {
                    sql.append(", ");
                }
                sql.append("Status DESC");
            }
        }

        // Add pagination
        sql.append(" LIMIT ?, ?");

        try (Connection conn = new DBContext().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int parameterIndex = 1;

            // Bind search parameters
            if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
                // Bind the searchKeyword as a string for SettingName
                stmt.setString(parameterIndex++, "%" + searchKeyword + "%");
                try {
                    // Attempt to parse the searchKeyword as an integer for RoleID
                    int roleId = Integer.parseInt(searchKeyword.trim());
                    stmt.setInt(parameterIndex++, roleId);
                } catch (NumberFormatException e) {
                    // If parsing fails, bind a fallback value (-1) for RoleID
                    stmt.setInt(parameterIndex++, -1);
                }
            }

            // Bind pagination parameters
            stmt.setInt(parameterIndex++, (currentPage - 1) * recordsPerPage);
            stmt.setInt(parameterIndex++, recordsPerPage);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Setting setting = new Setting(
                            rs.getInt("SettingID"),
                            rs.getString("SettingName"),
                            rs.getString("SettingType"),
                            rs.getString("SettingValue"),
                            rs.getInt("Priority"),
                            rs.getString("Status"),
                            rs.getInt("RoleID"),
                            rs.getString("Description")
                    );
                    settingsList.add(setting);
                }
            }
        }
        return settingsList;
    }

    // Get the total count of settings, including search conditions
    public int getTotalSettingsCount(String searchKeyword) throws SQLException {
        int totalRecords = 0;
        String sql = "SELECT COUNT(*) FROM Settings WHERE 1=1";

        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            sql += " AND (SettingName LIKE ? OR RoleID = ?)";
        }

        try (Connection conn = new DBContext().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (searchKeyword != null && !searchKeyword.isEmpty()) {
                stmt.setString(1, "%" + searchKeyword + "%");
                try {
                    stmt.setInt(2, Integer.parseInt(searchKeyword));
                } catch (NumberFormatException e) {
                    stmt.setInt(2, -1);
                }
            }

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    totalRecords = rs.getInt(1);
                }
            }
        }
        return totalRecords;
    }
}
