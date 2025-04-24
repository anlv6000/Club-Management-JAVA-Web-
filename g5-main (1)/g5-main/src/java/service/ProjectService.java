/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DAO.projectDao;
import Model.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author admin
 */
public class ProjectService {
    projectDao dao = new projectDao();
    
        public Project getProjectById(int projectId) {
            return dao.getProjectById(projectId);
    }
        
        public void updateProject2(int projectID, String name, String code, Date fromDate, Date toDate, boolean status, String description) {
           dao.updateProject(projectID, name, code, fromDate, toDate, status, description);
}
}
