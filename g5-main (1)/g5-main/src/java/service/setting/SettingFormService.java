package service.setting;

import DAO.dao;
import Model.Setting;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingFormService {

    private dao dao;

    public SettingFormService() {
        dao = new dao(); // Initialize the DAO
    }

    // Validate form input
    public Map<String, String> validateForm(String name, String value, String priorityStr, String status, String type, String roleIdStr) {
        Map<String, String> errors = new HashMap<>();

        // Validate Name (must not exceed 20 characters)
        if (name == null || name.trim().isEmpty()) {
            errors.put("nameError", "Name cannot be empty.");
        } else if (name.length() > 20) {
            errors.put("nameError", "Name must not exceed 20 characters.");
        }

        // Validate Value (must not exceed 50 characters)
        if (value == null || value.trim().isEmpty()) {
            errors.put("valueError", "Value cannot be empty.");
        } else if (value.length() > 50) {
            errors.put("valueError", "Value must not exceed 50 characters.");
        }

        // Validate Priority
        if (priorityStr == null || priorityStr.trim().isEmpty()) {
            errors.put("priorityError", "Priority cannot be empty.");
        } else {
            try {
                int priority = Integer.parseInt(priorityStr);
                if (priority < 0) {
                    errors.put("priorityError", "Priority must be a positive integer.");
                }
            } catch (NumberFormatException e) {
                errors.put("priorityError", "Priority is invalid.");
            }
        }

        // Validate Status
        if (status == null || status.trim().isEmpty()
                || !(status.equals("Active") || status.equals("Inactive"))) {
            errors.put("statusError", "Status must be 'Active' or 'Inactive'.");
        }

        // Validate Type
        if (type == null || type.trim().isEmpty()
                || !(type.equals("String") || type.equals("Integer") || type.equals("Boolean")
                || type.equals("Date") || type.equals("Text"))) {
            errors.put("typeError", "Invalid type. Must be 'String', 'Integer', 'Boolean', 'Date', or 'Text'.");
        }

        // Validate RoleID
        if (roleIdStr == null || roleIdStr.trim().isEmpty()) {
            errors.put("roleIdError", "Role ID cannot be empty.");
        } else {
            try {
                int roleId = Integer.parseInt(roleIdStr);
                if (roleId <= 0) {
                    errors.put("roleIdError", "Role ID must be a positive integer.");
                }
            } catch (NumberFormatException e) {
                errors.put("roleIdError", "Role ID is invalid.");
            }
        }

        return errors;
    }

    // Save settings to the database
    public void saveSetting(String name, String type, String value, int priority, String status, int roleId, String description) throws SQLException {
        Setting setting = new Setting(0, name, type, value, priority, status, roleId, description);
        dao.addSettings(List.of(setting)); // Use DAO to add setting
    }
    // Update an existing setting
    public void updateSetting(Setting setting) throws SQLException {
        dao.updateSettings(List.of(setting)); // Use DAO to update the setting
    }

}