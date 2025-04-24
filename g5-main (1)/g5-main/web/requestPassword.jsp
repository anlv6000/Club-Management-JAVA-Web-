<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
        <style>
            /* Background với họa tiết nhẹ */
            body {
                background-color: #f8f9fa;
                font-family: "Arial", sans-serif;
                background-image: url('https://www.transparenttextures.com/patterns/white-diamond.png'); 
            }
            
            /* Hộp chứa form */
            .card {
                background-color: #ffffff;
                border: 1px solid #dee2e6;
                border-radius: 12px;
                padding: 25px;
                box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.05);
                position: relative;
            }

            /* Thêm icon chibi */
            .chibi-icon {
                width: 60px;
                position: absolute;
                top: -40px;
                left: 50%;
                transform: translateX(-50%);
            }

            /* Form input */
            .form-control {
                border-radius: 8px;
                border: 1px solid #ced4da;
            }

            /* Nút bấm */
            .btn-custom {
                background-color: #adb5bd;
                color: #fff;
                border-radius: 8px;
                padding: 10px;
                transition: 0.3s;
            }
            .btn-custom:hover {
                background-color: #868e96;
            }

            /* Thêm icon vào label */
            .form-label i {
                margin-right: 8px;
                color: #6c757d;
            }

            /* Hiệu ứng chibi */
            .chibi {
                position: absolute;
                bottom: -50px;
                right: -30px;
                width: 80px;
                opacity: 0.7;
            }
        </style>
    </head>
    <body>
        <section class="d-flex align-items-center" style="min-height: 100vh;">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12 col-md-8 col-lg-6 position-relative">
                        <div class="card text-center">
                            <!-- Icon chibi trên đầu -->
                            <img src="https://cdn-icons-png.flaticon.com/512/4144/4144720.png" class="chibi-icon" alt="Chibi Icon">
                            
                            <h3 class="fw-normal mt-3">Reset Password</h3>
                            <p class="text-muted">Enter your email to receive reset instructions</p>
                            
                            <form action="requestPassword" method="POST">
                                <div class="mb-3 text-start">
                                    <label for="email" class="form-label">
                                        <i class="fas fa-envelope"></i> Email Address
                                    </label>
                                    <input type="email" class="form-control" name="email" id="email" required>
                                </div>
                                <button class="btn btn-custom w-100" type="submit">Send Reset Link</button>
                            </form>
                            
                            <p class="text-danger mt-3">${mess}</p>

                            <!-- Icon chibi nhỏ ở góc -->
                            <img src="https://cdn-icons-png.flaticon.com/512/4144/4144743.png" class="chibi" alt="Chibi Character">
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>