<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <title>Admin - Quản lý CLB</title>
    <style>
        .event-ad {
    background-color: #e74c3c;
    color: white;
    padding: 30px;
    border-radius: 10px;
    text-align: center;
}

/* Hàng đầu tiên chứa tiêu đề */
.event-header {
    margin-bottom: 20px;
}

/* Chia hàng thứ hai thành 3 cột */
.event-row {
    display: flex;
    justify-content: space-between;
    gap: 20px; 
}

/* Định dạng từng cột */
.kaka {
    flex: 1;
    background: white;
    color: black;
    padding: 20px;
    border-radius: 10px;
    text-align: center;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

/* Hình ảnh agent */
.fh5co-agent figure img {
    max-width: 100%;
    border-radius: 50%;
}

/* Nút button */
.btn {
    background-color: #2980b9;
    color: white;
    text-decoration: none;
    padding: 8px 12px;
    display: inline-block;
    border-radius: 5px;
}x

.btn:hover {
    background-color: #1a5276;
}
        body {
            background-image: url('https://daihoc.fpt.edu.vn/en/wp-content/uploads/2024/09/DHFPT6-1.webp');
            margin: 0;
            font-family: Arial, sans-serif;
            display: flex;
        }

        /* Sidebar */
        .sidebar {
            width: 250px;
            height: 100vh;
            background-color: #2c3e50;
            color: white;
            position: fixed;
            top: 0;
            left: 0;
            padding-top: 20px;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .sidebar h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .sidebar .menu-section {
            padding: 10px;
        }

        .sidebar .menu-section h4 {
            margin-left: 20px;
            font-size: 16px;
            font-weight: normal;
            color: #bdc3c7;
        }

        .sidebar a {
            display: flex;
            align-items: center;
            color: white;
            padding: 12px 20px;
            text-decoration: none;
            font-size: 18px;
        }

        .sidebar a:hover {
            background-color: #1a252f;
        }

        .sidebar a i {
            color: white;
            margin-right: 10px;
        }

        /* Nội dung chính */
        .main-content {
            margin-left: 250px;
            padding: 20px;
            flex-grow: 1;
            width: calc(100% - 250px);
            
            min-height: 100vh;
        }

        /* Select danh mục */
        .category-select {
            margin-bottom: 20px;
        }

        select {
            padding: 8px;
            font-size: 16px;
            width: 100%;
        }

        /* Danh sách CLB */
        
.club-list {
    display: flex;
    flex-direction: column; /* Xếp các phần tử theo chiều dọc */
    gap: 20px;
}

.club-card {
    display: flex;
    align-items: center;
    gap: 20px;
    width: 100%;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background: #f9f9f9;
    box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
}

.club-card img {
    width: 120px; /* Đặt kích thước ảnh đồng đều */
    height: 120px;
    border-radius: 10px; /* Bo góc ảnh */
    object-fit: cover; /* Đảm bảo ảnh không bị méo */
    border: 3px solid #ddd;
    transition: transform 0.3s ease-in-out;
}

.club-card img:hover {
    transform: scale(1.1); /* Hiệu ứng phóng to khi hover */
}

.club-card div {
    flex: 1;
    font-size: 18px;
}

.club-card button {
    padding: 10px 15px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.club-card button:hover {
    background-color: #0056b3;
}

  

   

    .club-card button {
        padding: 10px 15px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }

    .club-card button:hover {
        background-color: #0056b3;
    }

        .club-card h3 {
            margin: 0 0 10px;
        }

        .club-card button {
            background-color: #2980b9;
            color: white;
            border: none;
            padding: 8px 12px;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 10px;
        }

        .club-card button:hover {
            background-color: #1a5276;
        }

        /* Quảng cáo sự kiện */
        .event-ad {
            background-color: #4CB648;
            color: white;
            padding: 20px;
            border-radius: 10px;
            margin-top: 30px;
            text-align: center;
        }

        .event-ad h2 {
            margin: 0 0 10px;
        }
        .kaka{
            display: flex;
        }

        /* Biển quảng cáo tham gia */
        .join-banner {
            background-color: #27ae60;
            color: white;
            padding: 20px;
            border-radius: 10px;
            margin-top: 20px;
            text-align: center;
            margin-left: 129px;
                margin-right: 129px;
        }

        .join-banner h2 {
            margin: 0 0 10px;
        }

        .join-banner button {
            background-color: white;
            color: #27ae60;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            border-radius: 5px;
        }

        .join-banner button:hover {
            background-color: #2ecc71;
            color: white;
        }
    </style>
</head>
<body>

    <!-- Sidebar -->
    <div class="sidebar">
        <div>
            <h2>Admin</h2>

            <div class="menu-section">
                <h4> Home </h4>
                <a href="${pageContext.request.contextPath}/home1">
                    <i class="fa-solid fa-house"></i> Dashboard
                </a>
            </div>
        </div>
    </div>

    <!-- Nội dung chính -->
    <div class="main-content">
        <h1>Quản lý Câu lạc bộ</h1>

        <!-- Select danh mục -->
        <form action="ClubListUser">
          <div class="category-select">
            <label for="category">Category:</label>
            <select name="category"  id="category" onchange="this.form.submit()">
                
                <c:forEach var="o" items="${listClub}">
                      <option value="${o.category}" ${o.category == param.category ? 'selected' : ''}>
            ${o.category}
        </option>
                </c:forEach>
              <option value="all">Get All Club</option>
                
            </select>
        </div>

        <!-- Danh sách CLB -->
        <div class="club-list">
    <c:forEach var="k" items="${listClubs}">
        <div class="club-card">
            <img src="${k.imgURL}" alt="Club Image">
            <div>
                <h3>${k.clubName}</h3>
                <p>${k.description}</p>
                <button>Xem chi tiết</button>
            </div>
        </div>
    </c:forEach>
</div>
        <div class="club-list">
    <c:forEach var="p" items="${listClubss}">
        <div class="club-card">
            <img src="${p.imgURL}" alt="Club Image">
            <div>
                <h3>${p.clubName}</h3>
                <p>${p.description}</p>
                <button>Xem chi tiết</button>
            </div>
        </div>
    </c:forEach>
</div>
       </form>
        

        
        
        
        
        
        
        <div class="event-ad">
    <div class="container">
        
        <!-- Hàng đầu tiên -->
        <div class="event-header">
            <h2>Our Trusted Agents</h2>
            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
        </div>

        <!-- Hàng thứ hai: Chia thành 3 cột -->
        <div class="event-row">
            <div class="kaka" data-animate-effect="fadeIn">
                <div class="fh5co-agent">
                    <figure>
                        <img src="images/testimonial_person2.jpg" alt="Agent 1">
                    </figure>
                    <h3>John Doe</h3>
                    <p>Veniam laudantium rem odio quod, beatae voluptates natus animi fugiat provident voluptatibus.</p>
                    <p><a href="#" class="btn btn-primary btn-outline">Contact Me</a></p>
                </div>
            </div>

            <div class="kaka" data-animate-effect="fadeIn">
                <div class="fh5co-agent">
                    <figure>
                        <img src="images/testimonial_person3.jpg" alt="Agent 2">
                    </figure>
                    <h3>Jane Smith</h3>
                    <p>Veniam laudantium rem odio quod, beatae voluptates natus animi fugiat provident voluptatibus.</p>
                    <p><a href="#" class="btn btn-primary btn-outline">Contact Me</a></p>
                </div>
            </div>

            <div class="kaka" data-animate-effect="fadeIn">
                <div class="fh5co-agent">
                    <figure>
                        <img src="images/testimonial_person4.jpg" alt="Agent 3">
                    </figure>
                    <h3>Michael Lee</h3>
                    <p>Veniam laudantium rem odio quod, beatae voluptates natus animi fugiat provident voluptatibus.</p>
                    <p><a href="#" class="btn btn-primary btn-outline">Contact Me</a></p>
                </div>
            </div>
        </div>
        
    </div>
</div>
        
        
        
        
        <!-- Quảng cáo sự kiện CLB -->
        <div class="event-ad">
            <h2>Sự kiện CLB</h2>
            <p>Tham gia sự kiện lớn nhất của các CLB vào ngày 10/03!</p>
        </div>

        <!-- Biển quảng cáo "Tham gia ngay" -->
        <div class="join-banner">
            <h2>Muốn tham gia CLB?</h2>
            <p>Đăng ký ngay để kết nối với những người cùng sở thích!</p>
            <button>Tham gia ngay</button>
        </div>
    </div>

</body>
 <!-- jQuery -->
    <script src="js/jquery.min.js"></script>
    <!-- jQuery Easing -->
    <script src="js/jquery.easing.1.3.js"></script>
    <!-- Bootstrap -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Waypoints -->
    <script src="js/jquery.waypoints.min.js"></script>
    <!-- Flexslider -->
    <script src="js/jquery.flexslider-min.js"></script>

    <!-- MAIN JS -->
    <script src="js/main.js">


    </script> 
        <script>
    document.addEventListener("DOMContentLoaded", function () {
    var modal = document.getElementById("addAccountModal");
    var btn = document.getElementById("openAddAccountModal");
    var span = document.getElementsByClassName("close")[0];
    var iframe = document.getElementById("addAccountFrame");

    // Khi click nút "Add New Account", mở modal
    btn.onclick = function () {
        modal.style.display = "block";
        document.body.classList.add("modal-open"); // Chặn cuộn trang
    };

    // Khi click nút đóng (x), đóng modal
    span.onclick = function () {
        modal.style.display = "none";
        document.body.classList.remove("modal-open");
    };

    // Đóng modal nếu click bên ngoài
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
            document.body.classList.remove("modal-open");
        }
    };

    function reloadAccountList() {
    $.ajax({
        url: 'ManageAccount', // Gọi lại servlet để lấy danh sách mới
        type: 'GET',
        success: function(response) {
            $('#accountTable').html($(response).find('#accountTable').html()); // Cập nhật danh sách mà không reload trang
        }
    });
}


  function reloadAccountList() {
    $.ajax({
        url: 'ManageAccount', // Gọi lại servlet để lấy danh sách mới
        type: 'GET',
        success: function(response) {
            $('#accountTable').html($(response).find('#accountTable').html()); // Cập nhật danh sách mà không reload trang
        }
    });
}

       

    function submitSearchForm() {
        document.querySelector("form").submit();
    }
    });
</script>
</html>
