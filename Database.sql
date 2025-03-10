





/*part 3": */
CREATE DATABASE club_Manager;
USE club_Manager;
select *from users;
update users
set UserType ='Admin'
where userid ='1';
-- Tạo bảng Settings (Bảng 1)
CREATE TABLE Settings (
    SettingID INT AUTO_INCREMENT PRIMARY KEY,
    SettingName VARCHAR(100) NOT NULL,
    SettingType ENUM('String', 'Integer', 'Boolean', 'Date', 'Text') NOT NULL,
    SettingValue TEXT NOT NULL CHECK (LENGTH(SettingValue) <= 100),	
    Priority INT DEFAULT 1,
    Status ENUM('Active', 'Inactive') DEFAULT 'Active',
    UserType ENUM('Admin', 'ClubLeader', 'ClubMember', 'WebUser') NOT NULL,
    Description TEXT
);

CREATE TABLE tokenForgetPassword (
    id INT AUTO_INCREMENT PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    expiryTime TIMESTAMP NOT NULL,
    isUsed BOOLEAN NOT NULL,
    userId INT NOT NULL,
    FOREIGN KEY (userId) REFERENCES Users(UserID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Tạo bảng Users
CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    ClubID INT,
    Username VARCHAR(50) not null  ,
    Fullname VARCHAR(50) not null  ,
    Password VARCHAR(255) not null ,
    Email VARCHAR(100),
    UserType ENUM('Admin', 'ClubLeader', 'ClubMember', 'WebUser') DEFAULT 'WebUser',
    SettingID INT,
    CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    LastLoginDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    Status ENUM('Active', 'Inactive') DEFAULT 'Active',
    ImageURL VARCHAR(255),
    google_id varchar(255),
    FOREIGN KEY (SettingID) REFERENCES Settings(SettingID) ON DELETE SET NULL,
    note TEXT
);



-- Tạo bảng Clubs
CREATE TABLE Clubs (
    ClubID INT AUTO_INCREMENT PRIMARY KEY,
	UserID INT,
    ClubName VARCHAR(100) NOT NULL,
    Description TEXT,
    CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    IsPublic  VARCHAR(3) CHECK (IsPublic  IN ('Yes', 'No')) NOT NULL,
    status VARCHAR(20) NOT NULL,
    LastModifiedDate DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    ImageURL VARCHAR(255),
    Schedule varchar(255),
    ContactClub varchar(255),
    Categories VARCHAR(100) NOT NULL,
    Clubleader  VARCHAR(50) NOT NULL
);



-- Tạo bảng Events
CREATE TABLE Events (
    EventID INT AUTO_INCREMENT PRIMARY KEY,
    ClubID INT NOT NULL,
    EventName VARCHAR(100) NOT NULL,
    Description TEXT,
    EventDate DATETIME NOT NULL,
    CreatedBy INT,
    CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    EventImageURL VARCHAR(255),
    FOREIGN KEY (ClubID) REFERENCES Clubs(ClubID) ON DELETE CASCADE,
    FOREIGN KEY (CreatedBy) REFERENCES Users(UserID) ON DELETE SET NULL,
	Status BOOLEAN DEFAULT TRUE,
    Type BOOLEAN DEFAULT TRUE
);

-- Tạo bảng UserRole (Liên kết nhiều-nhiều giữa Users và Settings)
CREATE TABLE UserRole (
    UserID INT,
    SettingID INT,
    Status ENUM('Active', 'Inactive') DEFAULT 'Active',
    PRIMARY KEY (UserID, SettingID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE,
    FOREIGN KEY (SettingID) REFERENCES Settings(SettingID) ON DELETE CASCADE
);

CREATE TABLE post (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    club_id INT NOT NULL,
    image_url VARCHAR(255),
    status VARCHAR(20) NOT NULL, 
    description TEXT,
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



CREATE TABLE task (
    taskID INT AUTO_INCREMENT PRIMARY KEY,
    eventID INT NOT NULL,
    clubID INT NOT NULL,
    title VARCHAR(50) NOT NULL UNIQUE,
    assignerID INT NOT NULL,
    assigneeID INT NOT NULL,
    deadline DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    description TEXT NOT NULL,
    priority VARCHAR(20) NULL,
    FOREIGN KEY (clubID) REFERENCES Clubs(ClubID),
    FOREIGN KEY (eventID) REFERENCES Events(EventID),
    FOREIGN KEY (assignerID) REFERENCES Users(UserID),
    FOREIGN KEY (assigneeID) REFERENCES Users(UserID)
);



-- Tạo bảng UserClubs (Quan hệ nhiều-nhiều giữa Users và Clubs)
CREATE TABLE UserClubs (
    UserID INT,
    ClubID INT,
    role  ENUM('WebUser', 'ClubLeader', 'ClubMember') DEFAULT 'WebUser',
    note text,
    phone Double,
	Status Enum('Registered','Active', 'Inactive' ) default 'Registered',
    JoinedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (UserID, ClubID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE,
    FOREIGN KEY (ClubID) REFERENCES Clubs(ClubID) ON DELETE CASCADE
);

-- Thêm dữ liệu vào bảng Events
INSERT INTO Events (ClubID, EventName, Description, EventDate, CreatedBy, EventImageURL, Status, Type)
VALUES 
    (1, 'Annual Club Meeting', 'Description of the annual club meeting', '2025-03-01 14:00:00', 1, 'image1.jpg', TRUE, TRUE),
    (2, 'Spring Festival', 'Description of the spring festival', '2025-04-15 09:00:00', 2, 'image2.jpg', TRUE, TRUE),
    (1, 'Charity Run', 'Description of the charity run event', '2025-05-20 07:00:00', 3, 'image3.jpg', TRUE, TRUE);


-- Thêm dữ liệu vào bảng post
INSERT INTO post (user_id, club_id, image_url, status, description)
VALUES 
    (1, 1, 'image1.jpg', 'active', 'Description of post 1'),
    (2, 1, 'image2.jpg', 'inactive', 'Description of post 2'),
    (3, 2, 'image3.jpg', 'active', 'Description of post 3');

-- Thêm dữ liệu vào bảng finance
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


-- Thêm dữ liệu vào bảng task
INSERT INTO task (event_id, club_id, title, assigner_id, assignee_id, deadline, status, description, priority)
VALUES 
    (1, 1, 'Task 1', 1, 2, '2025-03-01 12:00:00', 'ongoing', 'Description of task 1', 1),
    (1, 1, 'Task 2', 2, 3, '2025-03-02 12:00:00', 'completed', 'Description of task 2', 2),
    (2, 2, 'Task 3', 3, 1, '2025-03-03 12:00:00', 'not started', 'Description of task 3', 3);


INSERT INTO Users (Username, Password, Email, UserType, SettingID, CreatedDate, LastLoginDate, Status, ImageURL, google_id)
VALUES 
('admin1', 'password123', 'admin1@example.com', 'Admin', 1, '2023-04-05 14:00:00', '2023-04-10 10:00:00', 'Active', 'http://example.com/image1.jpg', 'google1'),
('clubLeader1', 'password123', 'leader1@example.com', 'ClubLeader', 2, '2023-04-05 14:00:00', '2023-04-10 10:00:00', 'Active', 'http://example.com/image2.jpg', 'google2'),
('clubMember1', 'password123', 'member1@example.com', 'ClubMember', 3, '2023-04-05 14:00:00', '2023-04-10 10:00:00', 'Active', 'http://example.com/image3.jpg', 'google3'),
('webUser1', 'password123', 'user1@example.com', 'WebUser', 4, '2023-04-05 14:00:00', NULL, 'Inactive', 'http://example.com/image4.jpg', 'google4');


INSERT INTO Settings (SettingName, SettingType, SettingValue, Description, Priority, Status, UserType)
VALUES 
('SiteTitle', 'String', 'My Awesome Website', 'Title of the website displayed in the browser tab.', 1, 'Active', 'Admin'),
('MaxUsers', 'Integer', '100', 'Maximum number of users allowed to register.', 2, 'Active', 'Admin'),
('EnableDarkMode', 'Boolean', 'true', 'Option to enable dark mode for the website.', 3, 'Active', 'WebUser'),
('RegistrationDeadline', 'Date', '2025-12-31', 'Deadline date for club registration.', 1, 'Active', 'ClubLeader'),
('WelcomeMessage', 'Text', 'Welcome to our club!', 'Message displayed to new club members.', 2, 'Active', 'ClubMember'),
('PublicAccess', 'Boolean', 'false', 'Whether the site is accessible to the public or not.', 3, 'Active', 'WebUser');

INSERT INTO UserClubs (UserID, ClubID, role, note, phone, Status, JoinedDate) VALUES
(1, 1, 'WebUser', 'Note for User 1 in Club 1', 1234567890, 'Registered', '2025-02-26 22:41:00'),
(2, 1, 'ClubMember', 'Note for User 2 in Club 1', 2345678901, 'Active', '2025-02-26 22:42:00'),
(3, 2, 'ClubLeader', 'Note for User 3 in Club 2', 3456789012, 'Inactive', '2025-02-26 22:43:00'),
(2, 2, 'WebUser', 'Note for User 4 in Club 2', 4567890123, 'Active', '2025-02-26 22:44:00');


 INSERT INTO Clubs (UserID, ClubName, Categories, Description, ImageURL, Schedule, ContactClub, IsPublic, status, Clubleader) 
VALUES 
(1, 'FPTU Vovinam Club', 'Nghệ thuật',
 'Câu lạc bộ Vovinam là nơi hội tụ những người yêu thích võ thuật truyền thống Việt Nam. Tại đây, bạn sẽ được rèn luyện sức khỏe, kỹ năng tự vệ và tinh thần thượng võ. Với đội ngũ huấn luyện viên giàu kinh nghiệm, CLB giúp các thành viên phát triển cả về thể chất lẫn tinh thần, hướng tới một lối sống lành mạnh và kỷ luật.', 
 'https://tse1.mm.bing.net/th?id=OIP.B1WXw9eqT0-v3MCgjqUfZwHaHm&rs=1&pid=ImgDetMain&adlt=strict', 
 'Thứ 2, 4, 6 - 18:00 đến 20:00', 'vovinamclub@fpt.edu.vn', 'Yes', 'active', 'leader_name'),

(1, 'CNTT Club', 'Nghệ thuật',
 'Câu lạc bộ Công nghệ Thông tin (CNTT) là nơi dành cho những bạn trẻ đam mê lập trình, công nghệ và khám phá những giải pháp sáng tạo trong lĩnh vực số.', 
 'https://thumbs.dreamstime.com/b/js-logo-letter-geometric-golden-style-initial-monogram-shape-design-isolated-background-gold-238640508.jpg',
 'Thứ 7, Chủ Nhật - 14:00 đến 17:00', 'cnttclub@fpt.edu.vn', 'Yes', 'active', 'leader_name'),

(1, 'Trượt ván Club', 'Nghệ thuật',
 'Câu lạc bộ Trượt ván là nơi dành cho những ai yêu thích sự tự do và cảm giác mạnh trên bánh xe.', 
 'https://truot.vn/public/userfiles/news/TU_VANVNTRUOT-01.jpg',
 'Thứ 3, 5, 7 - 16:00 đến 19:00', 'skateclub@fpt.edu.vn', 'Yes', 'active', 'leader_name'),

(1, 'Bóng chuyền Club', 'Nghệ thuật',
 'Câu lạc bộ Bóng chuyền là sân chơi năng động dành cho những người yêu thích thể thao đồng đội.', 
 'https://makan.vn/wp-content/uploads/2023/04/logo-ao-bong-chuyen-01-2-768x768.jpg',
 'Thứ 3, 5 - 17:00 đến 19:00', 'volleyballclub@fpt.edu.vn', 'Yes', 'active', 'leader_name'),

(1, 'Muay Thai Club', 'Nghệ thuật',
 'Câu lạc bộ Muay Thai mang đến cơ hội khám phá môn võ thuật nổi tiếng của Thái Lan.', 
 'https://tse4.mm.bing.net/th?id=OIP.EC5b6aD_n5ic0QqjYcgAzQHaHa&rs=1&pid=ImgDetMain',
 'Thứ 2, 4, 6 - 19:00 đến 21:00', 'muaythaiclub@fpt.edu.vn', 'Yes', 'active', 'leader_name'),

(1, 'Hackathon Club', 'Nghệ thuật',
 'Câu lạc bộ dành cho những bạn trẻ đam mê lập trình, công nghệ và khám phá những giải pháp sáng tạo.', 
 'https://yt3.ggpht.com/a/AGF-l7-MtSt82Zw_Uk4ap9HhboZSCUCondclQ6cMQg=s900-c-k-c0xffffffff-no-rj-mo',
 'Thứ 7, Chủ Nhật - 10:00 đến 16:00', 'hackathonclub@fpt.edu.vn', 'Yes', 'active', 'leader_name'),

(1, 'CLB Văn hóa nghệ thuật', 'Nghệ thuật',
 'Câu lạc bộ văn hóa nghệ thuật dành cho những ai yêu thích sáng tạo và nghệ thuật.', 
 'https://th.bing.com/th/id/OIP.O9hT2bIXaWFpdUX-lxh4MAHaHa?rs=1&pid=ImgDetMain',
 'Thứ 4, 6 - 17:30 đến 19:30', 'artclub@fpt.edu.vn', 'Yes', 'active', 'leader_name'),

(1, 'CLB Melody', 'Âm nhạc',
 'Câu lạc bộ Melody dành cho những người đam mê âm nhạc và ca hát.', 
 'https://static.vecteezy.com/system/resources/previews/015/718/690/non_2x/melody-logo-colorful-gradient-style-vector.jpg',
 'Thứ 3, 5, 7 - 18:00 đến 20:00', 'melodyclub@fpt.edu.vn', 'Yes', 'active', 'leader_name'),

(1, 'CLB Tiếng Trung', 'Ngôn ngữ',
 'Câu lạc bộ Tiếng Trung giúp nâng cao khả năng giao tiếp và học ngôn ngữ Trung Quốc.', 
 'https://files01.duytan.edu.vn/svruploads/ktiengtrung-duytan/upload/images/638670938698138746-49.jpg',
 'Thứ 4, 6 - 16:30 đến 18:30', 'chineseclub@fpt.edu.vn', 'Yes', 'active', 'leader_name'),

(1, 'CLB Business', 'Kinh doanh',
 'Câu lạc bộ Business giúp phát triển kỹ năng lãnh đạo và quản lý trong lĩnh vực kinh doanh.', 
 'https://cdn.pixabay.com/photo/2015/04/22/21/19/club-735353_960_720.png',
 'Thứ 5, 7 - 18:00 đến 20:00', 'businessclub@fpt.edu.vn', 'Yes', 'active', 'leader_name'),

(1, 'CLB Tình nguyện – vì cộng đồng iGo', 'Tình nguyện',
 'Câu lạc bộ iGo hoạt động tình nguyện vì cộng đồng, mang đến những hành động ý nghĩa.', 
 'https://haitrieu.com/wp-content/uploads/2022/01/Logo-Chien-Dich-Xuan-Tinh-Nguyen-635x642.png',
 'Thứ 7, Chủ Nhật - 08:00 đến 12:00', 'igo@fpt.edu.vn', 'Yes', 'active', 'leader_name'),

(1, 'CLB Kỹ sư phần mềm Nhật Bản JS', 'Công nghệ',
 'Câu lạc bộ JS giúp học hỏi kỹ năng lập trình phần mềm với phương pháp Nhật Bản.', 
 'https://gudlogo.com/wp-content/uploads/2019/05/logo-nhat-ban-42.jpg',
 'Thứ 2, 4, 6 - 18:00 đến 21:00', 'jsclub@fpt.edu.vn', 'Yes', 'active', 'leader_name'),

(1, 'CLB Guitar', 'Âm nhạc',
 'Câu lạc bộ Guitar dành cho những người yêu thích chơi đàn và âm nhạc.', 
 'https://th.bing.com/th/id/OIP.4OaanNRSUFnIkMnVPCpiVwHaHa?rs=1&pid=ImgDetMain',
 'Thứ 3, 5, 7 - 17:30 đến 19:30', 'guitarclub@fpt.edu.vn', 'Yes', 'active', 'leader_name');

select * from userclubs;
select * from users;
select * from clubs;
select * from settings;
select * from finance;
select * from events;

update finance set amount = '1' where finance_id = '1';
update users set usertype = 'ClubMember' where userid = '3';
update users set ClubID= NULL where userid = '5';

SET FOREIGN_KEY_CHECKS = 0;

-- Xóa các b?ng cu n?u t?n t?i
DROP TABLE IF EXISTS Events;
DROP TABLE IF EXISTS UserClubs;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Clubs;
DROP TABLE IF EXISTS Settings;
DROP TABLE IF EXISTS UserRole;
DROP TABLE IF EXISTS tokenForgetPassword;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS finance;
DROP TABLE IF EXISTS task;


SET FOREIGN_KEY_CHECKS = 1;

SELECT Status FROM Settings WHERE SettingName = 'FinanceAccess';


INSERT INTO userclubs (UserID, ClubID, phone, note)
VALUES (2, 2, '0913966163', 'kakakakaka') AS new_values
ON DUPLICATE KEY UPDATE 
    phone = new_values.phone,
    note = new_values.note;
  
  SELECT * FROM UserClubs;

SELECT cm.UserID, u.Username, u.Email, u.ImageURL, u.Fullname, cm.status, cm.role
FROM Users u
JOIN UserClubs cm ON u.UserID = cm.UserID
WHERE cm.ClubID = 1 AND (cm.status = 'Active' OR cm.status = 'Inactive');

delete  from finance where finance_id ='43';
