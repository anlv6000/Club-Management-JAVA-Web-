<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <title>Admin</title>
        <style>
            body {
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

            .sidebar .menu-section h3 {
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
            .sidebar a[href*="home1"] {
    margin-bottom: 30px; /* Thêm kho?ng cách 1cm (10px) gi?a Dashboard và Manager */
}


            .sidebar a i {
                color: white;
                margin-right: 10px;
            }

            /* Dashboard Button */
            .dashboard-btn {
                text-align: center;
                margin-bottom: 20px;
            }
            .sidebar .menu-section h4 {
                margin-left: 20px;
                font-size: 16px;
                font-weight: normal;
                color: #bdc3c7;
            }


            .dashboard-btn a {
                display: inline-flex;
                align-items: center;
                justify-content: center;
                background-color: #2980b9;
                padding: 12px 20px;
                border-radius: 5px;
                font-size: 18px;
                width: 80%;
                transition: 0.3s;
            }

            .dashboard-btn a:hover {
                background-color: #1a5276;
            }
        </style>
    </head>
    
    <body>

        <!-- Sidebar -->
        <div class="sidebar">
            <div>
                <h2>Admin</h2>

                <!-- Qu?n lý -->
                <div class="menu-section">
                    <h4> Home </h4>
                    <a href="${pageContext.request.contextPath}/home1">
                        <i class="fa-solid fa-house"></i> Dashboard
                    </a>



                    <h3>Manager</h3>
                    <a href="${pageContext.request.contextPath}/ClubServlet">
                        <i class="fa-solid fa-users"></i> Club
                    </a>
                    <a href="${pageContext.request.contextPath}/settings">
                        <i class="fa-solid fa-cog"></i> Settings
                    </a>
                    <a href="ManageAccount">
                        <i class="fa-solid fa-user"></i> Manage Account
                    </a>
                    <a href="${pageContext.request.contextPath}/listEvent">
                        <i class="fa-solid fa-calendar"></i> Event
                    </a>
                </div>
            </div>




        </div>

    </body>
</html>
