package service.eventregistration;

import DAO.EventRegistrationDAO;
import DAO.dao;
import Model.Event;
import Model.EventRegistration;
import Model.User;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class EventService {

    private final EventRegistrationDAO eventRegistrationDAO;

    public EventService() {
        this.eventRegistrationDAO = new EventRegistrationDAO();
    }

    public List<Event> getEventsByEventLeaderId(int eventLeaderId) throws Exception {
        return eventRegistrationDAO.getEventsByEventLeaderId(eventLeaderId);
    }

    public List<String> getRoles() throws Exception {
        return eventRegistrationDAO.getRoles();
    }

    public List<String> getStatuses() throws Exception {
        return eventRegistrationDAO.getStatuses();
    }

    public String getEventNameById(int eventId) throws Exception {
        return eventRegistrationDAO.getEventNameById(eventId);
    }

    public List<EventRegistration> getRegistrationsByEventId(int eventId) throws Exception {
        return eventRegistrationDAO.getRegistrationsByEventId(eventId);
    }

    public List<EventRegistration> searchMembers(String role, String status, String keyword, int eventId) throws Exception {
        return eventRegistrationDAO.searchMembers(role, status, keyword, eventId);
    }

    public EventRegistration getRegistrationDetailsById(int registrationId) throws Exception {
        return eventRegistrationDAO.getRegistrationDetailsById(registrationId);
    }

    public boolean updateRegistrationStatusById(int registrationId, String status) throws Exception {
        return eventRegistrationDAO.updateRegistrationStatusById(registrationId, status);
    }

    public User getUserById(int userId) throws Exception {
        return new dao().getUserById(userId);
    }
    // Thêm phương thức này để lấy eventId từ eventLeaderId

    public int getEventIdByEventLeaderId(int eventLeaderId) throws Exception {
        return eventRegistrationDAO.getEventIdByEventLeaderId(eventLeaderId);
    }
}
