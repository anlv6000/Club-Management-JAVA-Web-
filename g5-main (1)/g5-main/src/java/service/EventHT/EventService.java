/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.EventHT;


import DAO.dao;
import DAO.projectDao;
import Model.Club;
import Model.Event;
import Model.User;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventService {
    private projectDao projectDao = new projectDao();
    private dao dao = new dao();
   
    // Các phương thức cho ListEvent
    public int getUserIDByUsername(String username) throws SQLException {
        return projectDao.getUserIDByUsername(username);
    }

    public List<Integer> getClubIDsByUserID(int userID) throws SQLException {
        return projectDao.getClubIDsByUserID(userID);
    }

    public List<Club> getClubsByClubIDs(List<Integer> clubIDs) throws SQLException {
        return projectDao.getClubsByClubIDs(clubIDs);
    }

    public List<Event> getEventByPageAndClubIDs(int page, int pageSize, List<Integer> clubIDs) throws SQLException {
        return projectDao.getEventByPageAndClubIDs(page, pageSize, clubIDs);
    }
     public List<Event> getEventByPageAndClubIDsSearh(int page, int pageSize, List<Integer> clubIDs,String searchKeyword,Boolean Type) throws SQLException {
        return projectDao.getEventByPageAndClubIDsSearch(page, pageSize, clubIDs, searchKeyword, Type);
    }
//         public List<Event> getEventNameByPage(int page, int pageSize,String eventName) throws SQLException {
//        return dao.getEventNameByPage(page, pageSize, eventName);
//    }

    public int getTotalEvents() throws SQLException {
        return dao.getTotalEvents();
    }
    public int getTotalEventsByNameAndClubIDs(String eventName, List<Integer> clubIDs,Boolean Type) throws SQLException {
        return projectDao.getTotalEventsByNameAndClubIDs(eventName, clubIDs,Type);
    }
    
     public User getUserByUserID(int userID) throws SQLException {
        return projectDao.getUserByUserID(userID);
    }
    public List<Event> getEventByPage(int page, int pageSize) throws SQLException {
        return dao.getEventByPage(page, pageSize);
    }
  
    // Các phương thức cho AddEvent
    public List<Club> getClubsByUsername(String username) throws SQLException {
        int userID = projectDao.getUserIDByUsername(username);
        List<Integer> clubIDs = projectDao.getClubIDsByUserID(userID);
        return projectDao.getClubsByClubIDs(clubIDs);
    }

    public Map<String, String> validateEvent(String clubIdStr, String eventName, String clubDescription,
                                             String eventTime, String eventTimeEnd, String eventTypeStr, String eventStatusStr) {
        Map<String, String> errors = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date currentTime = new Date();
        if (clubIdStr == null || clubIdStr.trim().isEmpty()) {
            errors.put("clubError", "Vui lòng chọn câu lạc bộ.");
        } else {
            try {
                Integer.parseInt(clubIdStr);
            } catch (NumberFormatException e) {
                errors.put("clubError", "ID câu lạc bộ không hợp lệ.");
            }
        }

        if (eventName == null || eventName.trim().isEmpty()) {
            errors.put("nameError", "Tên sự kiện không được để trống.");
        } else if (eventName.trim().length() <= 9) {
            errors.put("nameError", "Tên sự kiện phải có trên 9 ký tự.");
        } else if (eventName.length() > 100) {
            errors.put("nameError", "Tên sự kiện không được vượt quá 100 ký tự.");
        }

        if (clubDescription == null || clubDescription.trim().isEmpty()) {
            errors.put("descriptionError", "Mô tả không được để trống.");
        } else if (clubDescription.length() > 500) {
            errors.put("descriptionError", "Mô tả không được vượt quá 500 ký tự.");
        }

     
        
           Date startTime = null;
    if (eventTime == null || eventTime.trim().isEmpty()) {
        errors.put("timeError", "Thời gian bắt đầu không được để trống.");
    } else {
        try {
            startTime = formatter.parse(eventTime);
            if (startTime != null && startTime.before(currentTime)) {  // Kiểm tra thời gian bắt đầu phải lớn hơn thời điểm hiện tại
                errors.put("timeError", "Thời gian bắt đầu phải sau thời gian hiện tại.");
            }
        } catch (ParseException e) {
            errors.put("timeError", "Định dạng thời gian bắt đầu không hợp lệ (yyyy-MM-ddTHH:mm).");
        }
    }

        

        Date endTime = null;
        if (eventTimeEnd == null || eventTimeEnd.trim().isEmpty()) {
            errors.put("timeEndError", "Thời gian kết thúc không được để trống.");
        } else {
            try {
                endTime = formatter.parse(eventTimeEnd);
                if (startTime != null && endTime != null && !endTime.after(startTime)) {
                    errors.put("timeEndError", "Thời gian kết thúc phải sau thời gian bắt đầu.");
                }
            } catch (ParseException e) {
                errors.put("timeEndError", "Định dạng thời gian kết thúc không hợp lệ (yyyy-MM-ddTHH:mm).");
            }
        }

        if (eventTypeStr == null || eventTypeStr.trim().isEmpty()) {
            errors.put("typeError", "Loại sự kiện không được để trống.");
        } else if (!"true".equals(eventTypeStr) && !"false".equals(eventTypeStr)) {
            errors.put("typeError", "Loại sự kiện không hợp lệ.");
        }

        if (eventStatusStr == null || eventStatusStr.trim().isEmpty()) {
            errors.put("statusError", "Trạng thái sự kiện không được để trống.");
        } else if (!"true".equals(eventStatusStr) && !"false".equals(eventStatusStr)) {
            errors.put("statusError", "Trạng thái sự kiện không hợp lệ.");
        }

        return errors;
    }

    public void addEvent(int clubId, String eventName, String clubDescription, String eventTime, String eventTimeEnd,
                         String imageUrl, boolean eventType, boolean eventStatus, String username) throws SQLException {
        int userId = dao.getUserIdByUsername(username);
        String currentTime = getCurrentTime();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = inputFormat.parse(eventTime);
            Date end = inputFormat.parse(eventTimeEnd);
            eventTime = outputFormat.format(start);
            eventTimeEnd = outputFormat.format(end);
        } catch (ParseException e) {
            throw new SQLException("Invalid date format", e);
        }
        dao.addEvent(clubId, eventName, clubDescription, eventTime, userId, currentTime, imageUrl, eventStatus, eventType, eventTimeEnd);
    }
    public void updateEvent(int eventId, int clubId, String eventName, String description, String eventTime, String eventTimeEnd,
                            String imageUrl, boolean eventType, boolean eventStatus, int participantCount, Integer leaderId, String username) throws SQLException {
        int userId = dao.getUserIdByUsername(username);
        String currentTime = getCurrentTime();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = inputFormat.parse(eventTime);
            Date end = inputFormat.parse(eventTimeEnd);
            eventTime = outputFormat.format(start);
            eventTimeEnd = outputFormat.format(end);
        } catch (ParseException e) {
            throw new SQLException("Invalid date format", e);
        }
        dao.updateEvent(eventId, clubId, eventName, description, eventTime, userId, currentTime, imageUrl, eventStatus, eventType, eventTimeEnd, participantCount, leaderId);
        if (leaderId != null) {
            projectDao.updateRoleID(leaderId, clubId, 3); // Gọi updateRoleID như trong mã gốc
//            projectDao.updateRoleIDinUser(leaderId, 3);
        }
    }
 public Event getEventById(int eventId) throws SQLException {
        return dao.getEventById(eventId); // Giả sử DAO đã có phương thức này
    }
   public List<Event> getEventNameByPage(int page, int pageSize, String eventName) throws SQLException {
      
        return dao.getEventNameByPage(page, pageSize, eventName); // Cần điều chỉnh tên phương thức trong DAO nếu khác
    }

    public int getTotalEventsByName(String eventName) throws SQLException {
        return dao.getTotalEventsByName(eventName);
    }
    public List<Event> getEventTypeByPage(int page, int pageSize, boolean eventType) throws SQLException {
       
        return dao.getEventTypeByPage(page, pageSize, eventType); // Cần điều chỉnh tên phương thức trong DAO nếu khác
    }

    public int getTotalEventsByType(boolean eventType) throws SQLException {
        return dao.getTotalEventsByType(eventType);
    }
    
    private String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }
    
    public boolean updateEventStatus() throws SQLException {
    return projectDao.updateEventStatus();
}

}