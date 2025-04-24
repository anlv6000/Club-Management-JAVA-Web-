CREATE DATABASE club_Manager3;
USE club_Manager2;

CREATE TABLE user_role (
    RoleID INT AUTO_INCREMENT PRIMARY KEY, -- Dùng làm khóa chính
    role ENUM('Admin', 'ClubLeader', 'ClubMember', 'WebUser', 'EventLeader', 'ProjectLeader') not null
    
);
-- Tạo bảng Settings (Bảng 1)
CREATE TABLE Settings (
    SettingID INT AUTO_INCREMENT PRIMARY KEY,
    SettingName VARCHAR(100) NOT NULL,
    SettingType ENUM('String', 'Integer', 'Boolean', 'Date', 'Text') NOT NULL,
    SettingValue TEXT NOT NULL CHECK (LENGTH(SettingValue) <= 100),
    Priority INT DEFAULT 1,
    Status ENUM('Active', 'Inactive') DEFAULT 'Active',
    RoleID INT NOT NULL, -- Liên kết RoleID
    FOREIGN KEY (RoleID) REFERENCES user_role(RoleID), -- Tham chiếu RoleID trong bảng user_role
    Description TEXT
);
CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    ClubID INT,
    Username VARCHAR(50) NOT NULL,
    Fullname VARCHAR(50) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(100),
    RoleID INT NOT NULL, -- Liên kết RoleID
    FOREIGN KEY (RoleID) REFERENCES user_role(RoleID), -- Tham chiếu RoleID trong bảng user_role
    CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    LastLoginDate DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    Status ENUM('Active', 'Inactive') DEFAULT 'Active',
    ImageURL VARCHAR(255),
    google_id VARCHAR(255),
    note TEXT
);	

CREATE TABLE tokenForgetPassword (
    id INT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    expiryTime TIMESTAMP NOT NULL,
    isUsed BOOLEAN NOT NULL,
    userId INT NOT NULL,
    FOREIGN KEY (userId) REFERENCES Users(UserID) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE Clubs (
    ClubID INT AUTO_INCREMENT PRIMARY KEY,
	UserID INT,
    ClubName VARCHAR(100) NOT NULL,
    Description TEXT,
    CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    IsPublic  VARCHAR(3) CHECK (IsPublic  IN ('Yes', 'No')) NOT NULL,
    status VARCHAR(20) NOT NULL,
    LastModifiedDate DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    URLclub VARCHAR(255) ,
    ImageURL VARCHAR(255),
    Schedule varchar(255),
    ContactClub varchar(255),
    Categories VARCHAR(100) NOT NULL,
    Clubleader  VARCHAR(50) NOT NULL
);

CREATE TABLE Events (
    EventID INT AUTO_INCREMENT PRIMARY KEY,
    ClubID INT NOT NULL,
    EventLeaderId int not null,
    EventName VARCHAR(100) NOT NULL,
    Description TEXT,
    EventDate DATETIME NOT NULL,
    endEventDate DATETIME,               -- Ngày kết thúc sự kiện (đã thêm sau)
    CreatedBy INT,
    CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    EventImageURL VARCHAR(255),
    Status BOOLEAN DEFAULT TRUE,
    Type BOOLEAN DEFAULT TRUE,
    ParticipantCount INT DEFAULT 0,      -- Số người tham gia (đã thêm sau)
    FOREIGN KEY (EventLeaderId) REFERENCES users(UserID) ON DELETE CASCADE,
    FOREIGN KEY (ClubID) REFERENCES Clubs(ClubID) ON DELETE CASCADE,
    FOREIGN KEY (CreatedBy) REFERENCES Users(UserID) ON DELETE SET NULL
);  
CREATE TABLE post (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    club_id INT NOT NULL,
    image_url VARCHAR(255),
    title text,
	post_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL, 
    description TEXT,
    views INT DEFAULT 0, 
    FOREIGN KEY (user_id) REFERENCES Users(UserID),
    FOREIGN KEY (club_id) REFERENCES Clubs(ClubID)
);

CREATE TABLE finance (
    finance_id INT AUTO_INCREMENT PRIMARY KEY,
    club_id INT NOT NULL,
    member_id INT NOT NULL,
    finance_name VARCHAR(255) NOT NULL,  -- Added column for finance name
    is_expense ENUM('Yes', 'No') NOT NULL,
    amount DECIMAL(10, 3) NOT NULL,
    finance_date DATETIME NOT NULL,
    details TEXT,
    status ENUM('Saved', 'Submitted for Approval', 'Approve', 'Reject') NOT NULL DEFAULT 'Saved',  
    created_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (club_id) REFERENCES Clubs(ClubID),
    FOREIGN KEY (member_id) REFERENCES Users(UserID)
);
CREATE TABLE EventRegistration(
id INT AUTO_INCREMENT PRIMARY KEY,
Event_id INT ,
user_id INT not null,
club_id INT not null,
fullname varchar(255),
email varchar(255),
phone varchar(255),
note text,
FOREIGN KEY (Event_id) REFERENCES Events(EventID),
    FOREIGN KEY (club_id) REFERENCES clubs(ClubID),
     FOREIGN KEY (user_id) REFERENCES users(UserID),
	Status Enum('Registered','Reject','Active', 'Inactive' ) default 'Registered'
);
CREATE TABLE UserClubs (
    UserID INT,
    ClubID INT,
    RoleID INT,
    note TEXT,
    phone VARCHAR(15),
    Status ENUM('Registered', 'Reject', 'Active', 'Inactive') DEFAULT 'Registered',
    JoinedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    
    PRIMARY KEY (UserID, ClubID),
    FOREIGN KEY (RoleID) REFERENCES user_role(RoleID) ON DELETE CASCADE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE,
    FOREIGN KEY (ClubID) REFERENCES Clubs(ClubID) ON DELETE CASCADE
);
CREATE TABLE Project (
    ProjectID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(255) NOT NULL,
    Code VARCHAR(50) UNIQUE NOT NULL,
    From_Date DATE NOT NULL,
    To_Date DATE NOT NULL,
    Leader_ID INT NOT NULL,
    Status BOOLEAN NOT NULL DEFAULT TRUE, -- TRUE: Đang hoạt động, FALSE: Đã hoàn thành/hủy
    Description TEXT,
    ClubID INT, -- Liên kết với bảng Clubs
    FOREIGN KEY (Leader_ID) REFERENCES Users(UserID) ON DELETE CASCADE,
    FOREIGN KEY (ClubID) REFERENCES Clubs(ClubID) ON DELETE SET NULL
);

CREATE TABLE task (
    taskID INT AUTO_INCREMENT PRIMARY KEY,
    eventID INT NOT NULL,
    clubID INT NOT NULL,
    title VARCHAR(50) NOT NULL UNIQUE,
    assignerID INT NULL,
    assigneeID INT NULL,
    deadline DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    description TEXT NOT NULL,
    priority VARCHAR(20) NULL,
    FOREIGN KEY (clubID) REFERENCES Clubs(ClubID),
    FOREIGN KEY (eventID) REFERENCES Events(EventID),
    FOREIGN KEY (assignerID) REFERENCES Users(UserID),
    FOREIGN KEY (assigneeID) REFERENCES Users(UserID)
);
-- SET FOREIGN_KEY_CHECKS = 0;


-- -- Xóa các b?ng cu n?u t?n t?i
-- DROP TABLE IF EXISTS Events;
-- DROP TABLE IF EXISTS eventregistration;

-- DROP TABLE IF EXISTS UserClubs;
-- DROP TABLE IF EXISTS Users;
-- DROP TABLE IF EXISTS Clubs;
-- DROP TABLE IF EXISTS Settings;
-- DROP TABLE IF EXISTS UserRole;
-- DROP TABLE IF EXISTS tokenForgetPassword;
-- DROP TABLE IF EXISTS post;
-- DROP TABLE IF EXISTS finance;
-- DROP TABLE IF EXISTS task;

-- DROP TABLE IF EXISTS friend_requests;
-- DROP TABLE IF EXISTS messages;
-- SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO user_role (RoleID, role) VALUES
(1, 'WebUser'),
(2, 'ClubMember'),
(3, 'ClubLeader'),
(4, 'EventLeader'),
(5, 'ProjectLeader'),
(6, 'Admin');


-- Mật khẩu: 123456789aA@
INSERT INTO Users (ClubID, Username, Fullname, Password, Email, RoleID, CreatedDate, LastLoginDate, Status, ImageURL, google_id, note) VALUES
-- WebUser (RoleID = 1)
(1, 'user01', 'Nguyen Van A', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'user01@example.com', 1, NOW(), NOW(), 'Active', 'img/user01.jpg', NULL, 'Người dùng web bình thường'),
(1, 'user02', 'Tran Thi B', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'user02@example.com', 1, NOW(), NULL, 'Active', 'img/user02.jpg', NULL, 'Tài khoản đăng ký trên website'),
(1, 'user03', 'Le Van C', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'user03@example.com', 1, NOW(), NOW(), 'Inactive', 'img/user03.jpg', NULL, 'Tài khoản bị vô hiệu hóa'),

-- ClubMember (RoleID = 2)
(2, 'member01', 'Pham Van D', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'member01@example.com', 2, NOW(), NOW(), 'Active', 'img/member01.jpg', NULL, 'Thành viên CLB IT'),
(2, 'member02', 'Hoang Thi E', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'member02@example.com', 2, NOW(), NULL, 'Active', 'img/member02.jpg', NULL, 'Thành viên CLB Âm Nhạc'),
(2, 'member03', 'Bui Van F', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'member03@example.com', 2, NOW(), NOW(), 'Inactive', 'img/member03.jpg', NULL, 'Tạm ngưng hoạt động'),

-- ClubLeader (RoleID = 3)
(3, 'leader01', 'Ngo Thi G', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'leader01@example.com', 3, NOW(), NOW(), 'Active', 'img/leader01.jpg', NULL, 'Chủ nhiệm CLB Robotics'),
(3, 'leader02', 'Dang Van H', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'leader02@example.com', 3, NOW(), NULL, 'Active', 'img/leader02.jpg', NULL, 'Chủ nhiệm CLB Bóng Đá'),
(3, 'leader03', 'Do Thi I', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'leader03@example.com', 3, NOW(), NOW(), 'Active', 'img/leader03.jpg', NULL, 'Chủ nhiệm CLB Mỹ Thuật'),

-- EventLeader (RoleID = 4)
(4, 'event01', 'Nguyen Van J', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'event01@example.com', 4, NOW(), NOW(), 'Active', 'img/event01.jpg', NULL, 'Trưởng ban tổ chức hội thảo'),
(4, 'event02', 'Tran Thi K', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'event02@example.com', 4, NOW(), NULL, 'Active', 'img/event02.jpg', NULL, 'Tổ chức workshop kỹ năng mềm'),
(4, 'event03', 'Le Van L', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'event03@example.com', 4, NOW(), NOW(), 'Active', 'img/event03.jpg', NULL, 'Phụ trách sự kiện âm nhạc'),

-- ProjectLeader (RoleID = 5)
(5, 'project01', 'Pham Van M', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'project01@example.com', 5, NOW(), NOW(), 'Active', 'img/project01.jpg', NULL, 'Dự án phát triển phần mềm'),
(5, 'project02', 'Hoang Thi N', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'project02@example.com', 5, NOW(), NULL, 'Active', 'img/project02.jpg', NULL, 'Quản lý dự án nghiên cứu AI'),
(5, 'project03', 'Bui Van O', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'project03@example.com', 5, NOW(), NOW(), 'Active', 'img/project03.jpg', NULL, 'Phụ trách dự án IoT'),

-- Admin (RoleID = 6)
(6, 'admin01', 'Ngo Thi P', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'admin01@example.com', 6, NOW(), NOW(), 'Active', 'img/admin01.jpg', NULL, 'Quản trị viên hệ thống'),
(6, 'admin02', 'Dang Van Q', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'admin02@example.com', 6, NOW(), NULL, 'Active', 'img/admin02.jpg', NULL, 'Admin cấp cao'),
(6, 'admin03', 'Do Thi R', '$2a$10$KOquAsI1lqlMFn.gXQ5Fq.T51B2GTqZ91gRti/MczguTxXo9RMwre', 'admin03@example.com', 6, NOW(), NOW(), 'Active', 'img/admin03.jpg', NULL, 'Quản lý tài khoản người dùng');

INSERT INTO Clubs (UserID, ClubName, Categories, Description, ImageURL, Schedule, ContactClub, IsPublic, status, ClubLeader) 
VALUES 
(1, 'FPTU Vovinam Club', 'Nghệ thuật',
 'Câu lạc bộ Vovinam là nơi hội tụ những người yêu thích võ thuật truyền thống Việt Nam.', 
 'https://tse1.mm.bing.net/th?id=OIP.B1WXw9eqT0-v3MCgjqUfZwHaHm&rs=1&pid=ImgDetMain&adlt=strict', 
 'Thứ 2, 4, 6 - 18:00 đến 20:00', 'vovinamclub@fpt.edu.vn', 'Yes', 'active', 
 (SELECT Fullname FROM Users WHERE UserID = 1)),

(2, 'CNTT Club', 'Công nghệ',
 'Câu lạc bộ Công nghệ Thông tin (CNTT) là nơi dành cho những bạn trẻ đam mê lập trình.', 
 'https://thumbs.dreamstime.com/b/js-logo-letter-geometric-golden-style-initial-monogram-shape-design-isolated-background-gold-238640508.jpg',
 'Thứ 7, Chủ Nhật - 14:00 đến 17:00', 'cnttclub@fpt.edu.vn', 'Yes', 'active', 
 (SELECT Fullname FROM Users WHERE UserID = 2)),

(3, 'Trượt ván Club', 'Thể thao',
 'Câu lạc bộ Trượt ván là nơi dành cho những ai yêu thích sự tự do và cảm giác mạnh trên bánh xe.', 
 'https://truot.vn/public/userfiles/news/TU_VANVNTRUOT-01.jpg',
 'Thứ 3, 5, 7 - 16:00 đến 19:00', 'skateclub@fpt.edu.vn', 'Yes', 'active', 
 (SELECT Fullname FROM Users WHERE UserID = 3)),

(4, 'Bóng chuyền Club', 'Thể thao',
 'Câu lạc bộ Bóng chuyền là sân chơi năng động dành cho những người yêu thích thể thao đồng đội.', 
 'https://makan.vn/wp-content/uploads/2023/04/logo-ao-bong-chuyen-01-2-768x768.jpg',
 'Thứ 3, 5 - 17:00 đến 19:00', 'volleyballclub@fpt.edu.vn', 'Yes', 'active', 
 (SELECT Fullname FROM Users WHERE UserID = 4)),

(5, 'Muay Thai Club', 'Thể thao',
 'Câu lạc bộ Muay Thai mang đến cơ hội khám phá môn võ thuật nổi tiếng của Thái Lan.', 
 'https://tse4.mm.bing.net/th?id=OIP.EC5b6aD_n5ic0QqjYcgAzQHaHa&rs=1&pid=ImgDetMain',
 'Thứ 2, 4, 6 - 19:00 đến 21:00', 'muaythaiclub@fpt.edu.vn', 'Yes', 'active', 
 (SELECT Fullname FROM Users WHERE UserID = 5)),

(6, 'Hackathon Club', 'Công nghệ',
 'Câu lạc bộ dành cho những bạn trẻ đam mê lập trình và công nghệ.', 
 'https://yt3.ggpht.com/a/AGF-l7-MtSt82Zw_Uk4ap9HhboZSCUCondclQ6cMQg=s900-c-k-c0xffffffff-no-rj-mo',
 'Thứ 7, Chủ Nhật - 10:00 đến 16:00', 'hackathonclub@fpt.edu.vn', 'Yes', 'active', 
 (SELECT Fullname FROM Users WHERE UserID = 6)),

(7, 'Guitar Club', 'Nghệ thuật',
 'Câu lạc bộ Guitar là nơi dành cho những ai yêu thích âm nhạc và nghệ thuật biểu diễn.', 
 'https://cdn.tgdd.vn/Files/2017/07/03/994224/b-760x367.jpg',
 'Thứ 2, 4, 6 - 17:30 đến 19:30', 'guitarclub@fpt.edu.vn', 'Yes', 'active', 
 (SELECT Fullname FROM Users WHERE UserID = 7)),

(8, 'Dance Club', 'Nghệ thuật',
 'Câu lạc bộ Dance là sân chơi dành cho những bạn yêu thích vũ đạo và muốn thể hiện bản thân.', 
 'https://tse1.mm.bing.net/th?id=OIP.VuJY3cNqD0ZmtdubEHEoWAHaE7&rs=1&pid=ImgDetMain',
 'Thứ 3, 5, 7 - 18:00 đến 20:00', 'danceclub@fpt.edu.vn', 'Yes', 'active', 
 (SELECT Fullname FROM Users WHERE UserID = 8)),

(9, 'Photography Club', 'Nghệ thuật',
 'Câu lạc bộ Nhiếp ảnh là nơi dành cho những ai có đam mê với nghệ thuật chụp ảnh và sáng tạo nội dung.', 
 'https://tse2.mm.bing.net/th?id=OIP.PF6A8Vu3Z4JmBZoIYa_gAAHaE7&rs=1&pid=ImgDetMain',
 'Thứ 4, 6 - 15:00 đến 17:00', 'photoclub@fpt.edu.vn', 'Yes', 'active', 
 (SELECT Fullname FROM Users WHERE UserID = 9)),

(10, 'E-Sports Club', 'Công nghệ',
 'Câu lạc bộ E-Sports là nơi giao lưu của những game thủ đam mê các trò chơi điện tử đối kháng.', 
 'https://tse3.mm.bing.net/th?id=OIP.Iu6tNqOp7f1p9H7vH23DAAAAAA&rs=1&pid=ImgDetMain',
 'Thứ 6, Chủ Nhật - 19:00 đến 22:00', 'esportsclub@fpt.edu.vn', 'Yes', 'active', 
 (SELECT Fullname FROM Users WHERE UserID = 10)),

(11, 'Kịch nghệ Club', 'Nghệ thuật',
 'Câu lạc bộ Kịch nghệ là nơi các diễn viên trẻ có thể thể hiện tài năng sân khấu của mình.', 
 'https://tse1.mm.bing.net/th?id=OIP.NTGxvA2j_gC6kRW1rbzg-wHaE8&rs=1&pid=ImgDetMain',
 'Thứ 4, 6 - 18:00 đến 20:00', 'drama@fpt.edu.vn', 'Yes', 'active', 
 (SELECT Fullname FROM Users WHERE UserID = 11)),

(12, 'Robotics Club', 'Công nghệ',
 'Câu lạc bộ Robotics dành cho những sinh viên yêu thích kỹ thuật và chế tạo robot.', 
 'https://tse2.mm.bing.net/th?id=OIP.oxkOEbqx2LTb3hGR7owFbQHaHa&rs=1&pid=ImgDetMain',
 'Thứ 5, 7 - 14:00 đến 17:00', 'robotics@fpt.edu.vn', 'Yes', 'active', 
 (SELECT Fullname FROM Users WHERE UserID = 12)),

(13, 'Chạy bộ Club', 'Thể thao',
 'Câu lạc bộ Chạy bộ là nơi dành cho những người yêu thích thể thao và rèn luyện sức khỏe.', 
 'https://tse1.mm.bing.net/th?id=OIP.7aP89MdpzzBbDQcoyV_70wHaHa&rs=1&pid=ImgDetMain',
 'Thứ 2, 4, 6 - 6:00 đến 7:00', 'runningclub@fpt.edu.vn', 'Yes', 'active', 
 (SELECT Fullname FROM Users WHERE UserID = 13));


INSERT INTO Project (ProjectID, Name, Code, From_Date, To_Date, Leader_ID, Status, Description, ClubID) 
VALUES 
(2, 'New Cinema System', 'NCS2025', '2025-04-01', '2025-05-01', 1, 1, NULL, 1),
(3, 'Hệ thống quản lý sinh viên', 'PRJ001', '2024-01-01', '2024-06-01', 1, 1, 'Phát triển hệ thống quản lý sinh viên cho trường đại học', 1),
(4, 'Ứng dụng đặt vé xem phim', 'PRJ002', '2024-02-10', '2024-07-10', 1, 1, 'Ứng dụng web/mobile đặt vé và chọn ghế trực tuyến', 2),
(5, 'Trang thương mại điện tử mini', 'PRJ003', '2024-03-05', '2024-08-05', 1, 1, 'Xây dựng website bán hàng mini với giỏ hàng và thanh toán', 3),
(6, 'Quản lý thư viện', 'PRJ004', '2024-01-20', '2024-06-20', 1, 1, 'Hệ thống quản lý mượn trả sách trong thư viện', 1),
(7, 'Ứng dụng lịch học trực tuyến', 'PRJ005', '2024-02-01', '2024-07-01', 1, 1, 'Tạo thời khóa biểu và đồng bộ với Google Calendar', 2),
(8, 'Cổng thông tin nội bộ công ty', 'PRJ006', '2024-04-01', '2024-09-01', 1, 1, 'Phát triển hệ thống nội bộ cho nhân viên và phòng ban', 3),
(9, 'Ứng dụng theo dõi chi tiêu', 'PRJ007', '2024-03-15', '2024-08-15', 1, 1, 'Ghi nhận và thống kê chi tiêu cá nhân', 1);
delete from events where eventid ='2'
select * from events
INSERT INTO events (
    EventID, ClubID,EventLeaderId, EventName, Description, EventDate, CreatedBy, CreatedDate, EventImageURL,
    Status, Type, endEventDate, ParticipantCount
)
VALUES
(1, 1, 10, 'Vovinam championship', 'gải đấu giữa các câu lạc bộ võ thuật', '2025-11-25 12:00:00', 4, '2025-03-02 17:24:46', 'https://thethaonamviet.vn/wp-content/uploads/2024/04/vovinam-11.jpg', 0, 0, '2025-11-26 09:00:00', 0),

(2, 1,11, 'Tuyển Chọn THành Viên', 'tuyển chọn thanh viên đi thi đấu', '2025-02-13 08:22:00', 4, '2025-03-02 17:49:54', 'https://rikaidoshop.com/wp-content/uploads/2019/09/MG_0320-scaled.jpg', 1, 1, '2025-02-13 11:22:00', 0),

(3, 1,12,  'Ngày VoViNam', 'Kỳ nhiệm ngày thành lập câu lạc bộ', '2025-02-26 08:23:00', 4, '2025-03-02 17:48:52', 'https://vnmedia2.monkeyuni.net/upload/web/storage_web/05-09-2023_16:38:52_hoc-vo-vovinam.jpg', 1, 1, '2025-02-26 10:23:00', 0),

(4, 4, 10, 'Bóng chuyền buổi 234', 'Tập luyện bóng truyền cùng các bậc thầy !', '2025-12-12 09:00:00', 4, '2025-03-25 21:18:21', 'https://thethaonamviet.vn/wp-content/uploads/2024/04/vo-thuat-7.jpg', 0, 0, '2025-05-30 11:00:00', 123),

(5, 4,11, 'Bóng Chuyền Xuân', 'Giao hưu các đội bống truyền mùa xuân', '2025-02-27 22:12:00', 4, '2025-03-02 17:29:05', 'https://nld.mediacdn.vn/291774122806476800/2024/1/14/sv-2-1705207685412493633037.jpg', 1, 1, '2025-02-28 03:12:00', 0),

(6, 10,12, 'Workshop  Coin', 'Thảo luận về xu hướng phát triển của Coin', '2025-02-28 21:15:00', 4, '2025-03-02 17:27:25', 'https://accesstrade.vn/wp-content/uploads/2024/06/bitcoin-btc.png', 1, 1, '2025-03-01 15:15:00', 0),

(7, 11,10, 'Tình nguyện xanh !!!!!', 'Đi tình nguyện ở khu vực núi cao Hòa Bình', '2025-02-28 22:04:00', 4, '2025-03-11 23:58:19', 'https://gatewaycms.hpu.vn/media/8l55nmVLclM0g6jMHNRH90fSBAKkLhSsuFmF1eJeONySSeHV4eDOJeBV-QUapKgL.jpg', 0, 0, '2025-03-02 01:04:00', 0);


INSERT INTO post (user_id, club_id, image_url, title, post_at, status, description, views) VALUES  
-- FPTU Vovinam Club (club_id = 1)
(1, 1, 'https://image.sggp.org.vn/w680/Uploaded/2025/dqmbbcvo/2022_01_14/img_0484_RMAO.JPG.webp', 'Tuyển Quân 2025', '2025-03-01 22:16:26', 'Draft', 'Trong lần chuẩn bị cho SEA Games 31 tại Việt Nam, đội tuyển đã tập trung những VĐV tốt nhất...', 6),  
(2, 1, 'https://hocvovinam.files.wordpress.com/2013/08/vo-duong-lam-son-2.jpg?w=720', 'Mục tiêu của VoViNam', '2025-03-02 10:56:48', 'Draft', 'Về Võ Ðạo: Môn phái luyện tập cho môn sinh có một thân hình dắn dỏi, vững vàng...', 1),  
(3, 1, 'https://imagevietnam.vnanet.vn//MediaUpload/Org/2023/05/08/vna-potal-sea-games-32-vovinam-tiep-tuc-gianh-huy-chuong-vang-o-noi-dung-tu-ve-nu-67194228-17-34-22.jpeg', 'Chiến Thắng 123!', '2025-03-02 14:07:33', 'Published', '🎉 CHĂM HOA - LỜI CẢM ƠN 🎉 🔥Tất Niên 2024 - Chăm Hoa đã chính thức khép lại...', 3),  

-- CNTT Club (club_id = 2)
(4, 2, 'https://giadinh.edu.vn/upload/elfinder/%C4%91%E1%BB%93%20h%E1%BB%8Da/NganhCongngheThongtinCocansusangtaoWEB-01.jpg', 'Công nghệ thông tin - Đam mê và sáng tạo', '2025-03-12 10:35:01', 'Draft', 'CLB Công nghệ thông tin là sân chơi dành cho các bạn trẻ yêu thích lập trình...', 5),  

-- Trượt ván Club (club_id = 3)
(5, 3, 'https://hoalac-school.fpt.edu.vn/wp-content/uploads/SKATE-8.jpg', 'Cộng đồng trượt ván FPTU', '2025-03-12 10:35:01', 'Draft', 'Bạn là người yêu thích trượt ván và cảm giác chinh phục những thử thách trên đường phố?', 2),  

-- Bóng chuyền Club (club_id = 4)
(6, 4, 'https://file.hstatic.net/200000333667/article/image.jpg_4b1e1c8ce20f4c32824f275f34ff30af_1024x1024.jpg', 'Sân chơi của những chiến binh bóng chuyền', '2025-03-12 10:35:01', 'Draft', 'CLB Bóng chuyền FPTU mang đến một sân chơi bổ ích và lành mạnh cho các bạn sinh viên yêu thích môn thể thao này...', 2),  

-- Muay Thai Club (club_id = 5)
(7, 5, 'https://cdn2.tuoitre.vn/thumb_w/480/471584752817336320/2024/9/2/base64-17252934472421913895135.jpeg', 'Muay Thai - Võ thuật đối kháng', '2025-03-12 10:35:01', 'Draft', 'Muay Thai không chỉ là một môn võ, mà còn là một bộ môn rèn luyện thể lực cực kỳ hiệu quả...', 2),  

-- Hackathon Club (club_id = 6)
(8, 6, 'https://cdn.langsontv.vn/upload/news/11_2024/1731977234966_07471419112024.jpg', 'Hackathon - Thử thách lập trình', '2025-03-12 10:35:01', 'Draft', 'Bạn có đam mê lập trình? Bạn muốn thử sức với những bài toán khó và sáng tạo những sản phẩm công nghệ mới?', 2),  

-- CLB Văn hóa nghệ thuật (club_id = 7)
(9, 7, 'https://uploads.nguoidothi.net.vn/content/d0c61095-2567-43a3-a85f-8e495aaf6305.jpg', 'Cộng đồng yêu nghệ thuật', '2025-03-12 10:35:01', 'Draft', 'Nếu bạn có đam mê hội họa, nhiếp ảnh, âm nhạc hoặc diễn xuất, thì CLB Văn hóa Nghệ thuật chính là nơi dành cho bạn.', 2),  

-- CLB Melody (club_id = 8)
(10, 8, 'https://melody-club.com/wp-content/uploads/2025/01/music-club.jpg', 'Melody Club - Nơi hội tụ âm nhạc', '2025-03-12 10:35:01', 'Draft', 'CLB Melody là nơi dành cho những ai yêu thích âm nhạc và nghệ thuật biểu diễn...', 3),  

-- CLB Tiếng Trung (club_id = 9)
(11, 9, 'https://tiectrung.com/wp-content/uploads/2025/01/chinese-club.jpg', 'CLB Tiếng Trung - Giao lưu và học hỏi', '2025-03-12 10:35:01', 'Draft', 'CLB Tiếng Trung FPTU giúp bạn nâng cao khả năng giao tiếp và hiểu biết về văn hóa Trung Hoa.', 1),  

-- CLB Business (club_id = 10)
(12, 10, 'https://businessclub.com/wp-content/uploads/2025/01/business-event.jpg', 'Khởi nghiệp cùng CLB Business', '2025-03-12 10:35:01', 'Draft', 'Bạn có đam mê kinh doanh và khởi nghiệp? CLB Business là nơi giúp bạn phát triển kỹ năng...', 4),  

-- CLB Tình nguyện – vì cộng đồng iGo (club_id = 11)
(13, 11, 'https://volunteerclub.com/wp-content/uploads/2025/01/volunteer-event.jpg', 'Cùng nhau làm tình nguyện', '2025-03-12 10:35:01', 'Draft', 'CLB iGo mang đến cơ hội cho sinh viên tham gia các hoạt động thiện nguyện và hỗ trợ cộng đồng.', 6),  

-- CLB Kỹ sư phần mềm Nhật Bản JS (club_id = 12)
(14, 12, 'https://js-club.com/wp-content/uploads/2025/01/software-engineering.jpg', 'CLB JS - Học tập và phát triển', '2025-03-12 10:35:01', 'Draft', 'CLB JS là sân chơi cho các bạn đam mê công nghệ và muốn tìm hiểu về kỹ thuật phần mềm Nhật Bản.', 3),  

-- CLB Guitar (club_id = 13)
(15, 13, 'https://guitarclub.com/wp-content/uploads/2025/01/guitar-performance.jpg', 'Cộng đồng Guitar FPTU', '2025-03-12 10:35:01', 'Draft', 'Nếu bạn yêu thích guitar và muốn giao lưu với những người cùng đam mê, CLB Guitar là nơi dành cho bạn!', 5);
select * from post
INSERT INTO finance (club_id, member_id, finance_name, is_expense, amount, finance_date, details, status) 
VALUES 
(1, 1, 'Monthly Fee', 'Yes', 5000, '2025-02-23 10:00:00', 'Monthly club fee', 'Approve'),
(2, 3, 'Fund Donation', 'No', 15000, '2025-02-22 15:30:00', 'Fund donation', 'Approve'),
(1, 2, 'Event Expense', 'Yes', 750, '2025-02-21 09:00:00', 'Event expense', 'Approve'),
(3, 2, 'Sponsorship', 'No', 2000, '2025-02-20 13:45:00', 'Sponsorship', 'Approve'),
(2, 1, 'Supplies Purchase', 'Yes', 1200, '2025-02-19 11:15:00', 'Supplies purchase', 'Approve'),
(1, 3, 'Membership Renewal', 'Yes', 4500, '2025-02-18 14:00:00', 'Membership renewal fee', 'Saved'),
(2, 2, 'Equipment Rental', 'Yes', 3000, '2025-02-17 16:30:00', 'Rental of equipment for event', 'Submitted for Approval'),
(3, 1, 'Food Donation', 'No', 6000, '2025-02-16 12:00:00', 'Food donation for charity event', 'Submitted for Approval'),
(1, 1, 'Facility Maintenance', 'Yes', 2500, '2025-02-15 10:45:00', 'Maintenance of club facilities', 'Saved'),
(2, 3, 'Prize Fund', 'No', 8000, '2025-02-14 18:20:00', 'Prize fund for competition', 'Reject');
select * from task
INSERT INTO task (taskID, eventID, clubID, title, assignerID, assigneeID, deadline, status, description, priority)
VALUES 
(1, 1, 1, 'Chuẩn bị sân đấu', 4, 10, '2025-11-20 08:00:00', 'In Progress', 'Dọn dẹp và chuẩn bị sân đấu cho giải Vovinam', 'High'),
(2, 1, 1, 'Mời trọng tài', 4, 11, '2025-11-22 10:00:00', 'Pending', 'Liên hệ và xác nhận danh sách trọng tài', 'Medium'),
(3, 1, 1, 'Thiết kế banner sự kiện', 4, 12, '2025-11-23 15:00:00', 'Not Started', 'Thiết kế và in ấn banner giải đấu', 'Low'),
(4, 2, 1, 'Chuẩn bị hồ sơ tuyển chọn', 4, 10, '2025-02-10 09:00:00', 'In Progress', 'Tạo hồ sơ và danh sách ứng viên thi đấu', 'High'),
(5, 2, 1, 'Tổ chức buổi tập huấn', 4, 11, '2025-02-12 14:00:00', 'Pending', 'Chuẩn bị nội dung và huấn luyện các ứng viên', 'Medium'),
(6, 3, 1, 'Chuẩn bị nội dung kỷ niệm', 4, 12, '2025-02-24 10:00:00', 'Not Started', 'Soạn nội dung phát biểu và lịch trình sự kiện', 'Low'),
(7, 3, 1, 'Chuẩn bị quà tặng', 4, 10, '2025-02-25 11:30:00', 'Completed', 'Mua và chuẩn bị quà tặng cho khách mời', 'Medium'),
(8, 4, 4, 'Chuẩn bị sân bóng chuyền', 4, 11, '2025-12-10 07:00:00', 'In Progress', 'Dọn dẹp và chuẩn bị dụng cụ thi đấu', 'High'),
(9, 4, 4, 'Mời đội bóng khách mời', 4, 12, '2025-12-11 13:00:00', 'Pending', 'Liên hệ các đội bóng khách mời', 'Medium'),
(10, 5, 4, 'Tổ chức lễ khai mạc', 4, 10, '2025-02-27 20:00:00', 'Completed', 'Chuẩn bị bài phát biểu và lễ khai mạc', 'High'),
(11, 5, 4, 'Quay phim và chụp ảnh sự kiện', 4, 11, '2025-02-28 01:00:00', 'Not Started', 'Tổ chức đội quay phim, chụp ảnh', 'Low'),
(12, 6, 10, 'Chuẩn bị tài liệu workshop', 4, 12, '2025-02-27 18:00:00', 'Pending', 'Chuẩn bị tài liệu thảo luận về Coin', 'Medium'),
(13, 6, 10, 'Mời diễn giả', 4, 10, '2025-02-28 09:00:00', 'In Progress', 'Liên hệ các diễn giả để tham gia thảo luận', 'High'),
(14, 7, 11, 'Chuẩn bị đồ dùng tình nguyện', 4, 11, '2025-02-27 19:00:00', 'Not Started', 'Mua và đóng gói đồ dùng cần thiết', 'Low'),
(15, 7, 11, 'Liên hệ chính quyền địa phương', 4, 12, '2025-02-28 10:00:00', 'Completed', 'Xác nhận lịch trình và sự hỗ trợ từ địa phương', 'Medium'),
(16, 1, 1, 'Kiểm tra hệ thống âm thanh', 4, 10, '2025-11-24 08:00:00', 'Pending', 'Test micro, loa và các thiết bị âm thanh', 'Low'),
(17, 2, 1, 'Tạo danh sách tuyển chọn cuối cùng', 4, 11, '2025-02-13 07:00:00', 'Completed', 'Lọc và chọn danh sách ứng viên cuối cùng', 'High'),
(18, 3, 1, 'Đặt suất ăn cho buổi kỷ niệm', 4, 12, '2025-02-25 09:00:00', 'Pending', 'Liên hệ nhà cung cấp và đặt suất ăn', 'Medium'),
(19, 4, 4, 'Chuẩn bị trang phục thi đấu', 4, 10, '2025-12-11 08:00:00', 'In Progress', 'Kiểm tra và phân phát đồng phục cho các đội', 'High'),
(20, 5, 4, 'Tổ chức buổi tổng duyệt', 4, 11, '2025-02-26 16:30:00', 'Pending', 'Diễn tập toàn bộ chương trình', 'High');

INSERT INTO Settings (SettingName, SettingType, SettingValue, Description, Priority, Status, RoleID)
VALUES 
('SiteTitle', 'String', 'My Awesome Website', 'Title of the website displayed in the browser tab.', 1, 'Active', 1),
('MaxUsers', 'Integer', '100', 'Maximum number of users allowed to register.', 2, 'Active', 2),
('EnableDarkMode', 'Boolean', 'true', 'Option to enable dark mode for the website.', 3, 'Active',3 ),
('RegistrationDeadline', 'Date', '2025-12-31', 'Deadline date for club registration.', 1, 'Active', 4),
('WelcomeMessage', 'Text', 'Welcome to our club!', 'Message displayed to new club members.', 2, 'Active', 5),
('PublicAccess', 'Boolean', 'false', 'Whether the site is accessible to the public or not.', 3, 'Active',6 );

INSERT INTO UserClubs (UserID, ClubID, RoleID, note, phone, Status, JoinedDate) VALUES
(1, 1, 3 , 'Note for User 1 in Club 1', 1234567890, 'Registered', '2025-02-26 22:41:00'),
(2, 1, 4, 'Note for User 2 in Club 1', 2345678901, 'Active', '2025-02-26 22:42:00'),
(3, 2, 4, 'Note for User 3 in Club 2', 3456789012, 'Inactive', '2025-02-26 22:43:00'),
(2, 2, 3 , 'Note for User 4 in Club 2', 4567890123, 'Active', '2025-02-26 22:44:00');

select * from users
