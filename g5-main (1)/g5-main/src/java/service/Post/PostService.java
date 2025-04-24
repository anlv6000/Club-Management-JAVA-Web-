/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.Post;

/**
 *
 * @author Quandxnunxi28
 */

import DAO.dao;
import Model.Club;
import Model.Post;
import java.util.List;

public class PostService {
    private dao dao;

    public PostService() {
        this.dao = new dao();
    }

    public String validateAndFetchPosts(String keyword, String category, String club, List<Club>[] listC, List<Club>[] listClub, List<Post>[] postList) {
        if (keyword != null && keyword.length() > 100) {
            return "Keyword quá dài";
        }
        if (category != null && category.length() > 50) {
            return "Category không hợp lệ";
        }
        if (club != null && club.length() > 50) {
            return "Club không hợp lệ";
        }
        
        listC[0] = dao.getCategory();
        listClub[0] = dao.getAllClubs();
        postList[0] = dao.filterPosts(keyword, category, club);
        
        return null; // Không có lỗi
    }
}
