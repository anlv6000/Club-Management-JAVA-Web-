Club Management System
Giới thiệu
Hệ thống quản lý câu lạc bộ (Club Management System) là một nền tảng toàn diện được thiết kế để hỗ trợ việc quản lý hoạt động của các câu lạc bộ. Từ việc quản lý thành viên, sự kiện, tài chính đến tích hợp báo cáo chi tiết, giải pháp này giúp các nhà quản lý tiết kiệm thời gian và nâng cao hiệu quả.

Các tính năng chính
. Quản lý thành viên: Thêm, chỉnh sửa, và theo dõi thông tin cá nhân của từng thành viên.
. Quản lý sự kiện: Tổ chức và theo dõi các sự kiện, bao gồm lịch trình và ngân sách.
. Hệ thống báo cáo: Tạo báo cáo về hoạt động, tài chính và thành tích của câu lạc bộ.
. Giao diện dễ sử dụng: Được thiết kế trực quan, phù hợp với mọi đối tượng người dùng.
. Tích hợp API: Hỗ trợ tích hợp với các công cụ và dịch vụ bên ngoài như Google Calendar, Email Notifications.

Cài đặt
Yêu cầu hệ thống:
. Ngôn ngữ lập trình: Java
. Framework: Spring Boot
. Cơ sở dữ liệu: MySQL
. IDE khuyến nghị: IntelliJ IDEA hoặc Eclipse
. Công cụ bổ sung: Git, Maven

Hướng dẫn cài đặt:
1. Clone repository:
   git clone https://github.com/anlv6000/Club-Management-JAVA-Web-.git
   cd Club-Management-JAVA-Web-
2. Cấu hình cơ sở dữ liệu:
Tạo một cơ sở dữ liệu MySQL mới (ví dụ: club_management).
Cập nhật file application.properties với thông tin kết nối:
  spring.datasource.url=jdbc:mysql://localhost:3306/club_management
  spring.datasource.username=your_username
  spring.datasource.password=your_password
3. Build và chạy ứng dụng:
  mvn clean install
  mvn spring-boot:run
4. Truy cập hệ thống:
  Mở trình duyệt và truy cập http://localhost:8080.

Hướng dẫn sử dụng:
. Đăng nhập hoặc tạo tài khoản quản trị viên.

. Thêm thông tin câu lạc bộ và các thành viên.

. Sử dụng giao diện để lên lịch sự kiện, quản lý tài chính và tạo báo cáo.

. Theo dõi thông báo và thống kê trong dashboard chính.

Tác giả và ghi nhận
Dự án được xây dựng và phát triển bởi Nhóm G5 trong khóa học SE1870-NJ SWP490. Xin cảm ơn tất cả thành viên đã đóng góp và hỗ trợ!

Giấy phép
Dự án được phát hành dưới giấy phép MIT License. Vui lòng xem chi tiết trong file LICENSE.

Trạng thái dự án
Dự án đang trong quá trình phát triển. Các tính năng mới sẽ được cập nhật trong các phiên bản tiếp theo.

