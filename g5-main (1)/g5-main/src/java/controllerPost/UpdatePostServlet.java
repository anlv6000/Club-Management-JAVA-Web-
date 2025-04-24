/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllerPost;

import DAO.postHT;
import Model.Post;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;



/**
 *
 * @author User
 */
@WebServlet(name="UpdatePostServlet", urlPatterns={"/UpdatePostServlet"})
@MultipartConfig(
    maxFileSize = 1024 * 1024 * 5, // 5MB
    maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class UpdatePostServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdatePostServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePostServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
          request.getRequestDispatcher("BlogListMember").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Lấy dữ liệu từ form
        int post_id;
        try {
            post_id = Integer.parseInt(request.getParameter("post_id"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID bài đăng không hợp lệ!");
            request.getRequestDispatcher("DetailPost").forward(request, response);
            return;
        }

        String title = request.getParameter("title");
        String clubId = request.getParameter("clubId");
        String status = request.getParameter("status");
        String description = request.getParameter("description");

        // Lấy thông tin user từ session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("acc") == null) {
            request.setAttribute("error", "Bạn cần đăng nhập để thực hiện thao tác này!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        User user = (User) session.getAttribute("acc");
        int userId = user.getId();

        // Lấy bài đăng hiện tại để lấy image_url nếu không có file mới
        postHT postDao = new postHT();
        Post posta = postDao.getPostByPostID(post_id);
        if (posta == null) {
            request.setAttribute("error", "Bài đăng không tồn tại!");
            request.getRequestDispatcher("DetailPost?post_id=" + post_id).forward(request, response);
            return;
        }

        // Xử lý file hình ảnh
        String image_url = posta.getImage_url();
        try {
            Part filePart = request.getPart("image");
            System.out.println("File part: " + filePart); // Debug
            System.out.println("File size: " + (filePart != null ? filePart.getSize() : "null")); // Debug

            if (filePart != null && filePart.getSize() > 0) { // Kiểm tra nếu có file được chọn
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                // Tạo tên file duy nhất bằng cách thêm timestamp
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String fileNameWithoutExtension = originalFileName.substring(0, originalFileName.lastIndexOf("."));
                String uniqueFileName = fileNameWithoutExtension + "_" + System.currentTimeMillis() + fileExtension;

                String uploadPath = getServletContext().getRealPath("") + "uploads";
                System.out.println("Upload path: " + uploadPath); // Debug

                // Tạo thư mục nếu chưa tồn tại
                Files.createDirectories(Paths.get(uploadPath));

                // Đường dẫn đầy đủ của file
                String filePath = uploadPath + "/" + uniqueFileName;
                System.out.println("File path: " + filePath); // Debug

                // Copy file vào thư mục uploads
                try (InputStream fileContent = filePart.getInputStream()) {
                    Files.copy(fileContent, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                    image_url = "uploads/" + uniqueFileName;
                } catch (IOException e) {
                    System.out.println("Lỗi khi upload file: " + e.getMessage());
                    e.printStackTrace();
                    request.setAttribute("error", "Lỗi khi upload file: " + e.getMessage());
                    request.getRequestDispatcher("DetailPost?post_id=" + post_id).forward(request, response);
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi xử lý file upload: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Lỗi khi xử lý file upload: " + e.getMessage());
            request.getRequestDispatcher("DetailPost?post_id=" + post_id).forward(request, response);
            return;
        }

        // Cập nhật bài đăng trong cơ sở dữ liệu
        try {
            postDao.UpdatePostByPostID(post_id, userId, Integer.parseInt(clubId), title, image_url, status, description);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Club ID không hợp lệ!");
            request.getRequestDispatcher("DetailPost?post_id=" + post_id).forward(request, response);
            return;
        }

        // Chuyển hướng về trang danh sách bài đăng
        request.setAttribute("message", "Cập nhật bài đăng thành công!");
        request.getRequestDispatcher("BlogListMember").forward(request, response);
    }
    
     
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
