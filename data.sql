CREATE DATABASE club_Manager;
USE club_Manager;

-- T?o b?ng Settings (B?ng 1)
CREATE TABLE Settings (
    SettingID INT AUTO_INCREMENT PRIMARY KEY,
    SettingName VARCHAR(100) NOT NULL,
    SettingType ENUM('String', 'Integer', 'Boolean', 'Date', 'Text') NOT NULL, -- Lo?i d? li?u c?a setting
    SettingValue TEXT NOT NULL, -- Giá tr? c?a setting
    Priority INT DEFAULT 1, -- M?c d? uu tiên c?a setting
    Status ENUM('Active', 'Inactive') DEFAULT 'Active', -- Tr?ng thái c?a setting
    UserType ENUM('Admin', 'ClubLeader', 'ClubMember', 'WebUser') NOT NULL -- Lo?i ngu?i dùng áp d?ng setting
);

-- T?o b?ng Users (B?ng N)
CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(100) ,
    UserType ENUM('Admin', 'ClubLeader', 'ClubMember', 'WebUser') DEFAULT 'WebUser', -- Lo?i ngu?i dùng
    SettingID INT, -- Khóa ngo?i liên k?t d?n b?ng Settings
    CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    LastLoginDate DATETIME DEFAULT NULL,
    AccountCreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    AccountLastModifiedDate DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    Status ENUM('Active', 'Suspended', 'Inactive') DEFAULT 'Active', -- Tr?ng thái tài kho?n ngu?i dùng
    ImageURL VARCHAR(255), -- Luu tr? ?nh ngu?i dùng
    FOREIGN KEY (SettingID) REFERENCES Settings(SettingID) ON DELETE SET NULL -- Thi?t l?p FK d?n b?ng Settings
);

-- T?o b?ng Clubs
CREATE TABLE Clubs (
    ClubID INT AUTO_INCREMENT PRIMARY KEY,
    ClubName VARCHAR(100) NOT NULL,
    Catagories VARCHAR(100) NOT NULL,
    Description TEXT,
    CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    IsPublic BOOLEAN DEFAULT TRUE,
    LastModifiedDate DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    ImageURL VARCHAR(255) -- Luu tr? ?nh câu l?c b?
);

-- T?o b?ng UserClubs (Quan h? N-N gi?a Users và Clubs)
CREATE TABLE UserClubs (
    UserID INT, -- Khóa ngo?i liên k?t d?n b?ng Users
    ClubID INT, -- Khóa ngo?i liên k?t d?n b?ng Clubs
    JoinedDate DATETIME DEFAULT CURRENT_TIMESTAMP, -- Ngày gia nh?p
    PRIMARY KEY (UserID, ClubID), -- Khóa chính là s? k?t h?p gi?a UserID và ClubID
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE,
    FOREIGN KEY (ClubID) REFERENCES Clubs(ClubID) ON DELETE CASCADE
);

-- T?o b?ng Events
CREATE TABLE Events (
    EventID INT AUTO_INCREMENT PRIMARY KEY,
    ClubID INT NOT NULL, -- Khóa ngo?i liên k?t d?n b?ng Clubs
    EventName VARCHAR(100) NOT NULL,
    Description TEXT,
    EventDate DATETIME NOT NULL,
    CreatedBy INT, -- Khóa ngo?i liên k?t d?n b?ng Users (ngu?i t?o)
    CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ClubID) REFERENCES Clubs(ClubID) ON DELETE CASCADE,
    FOREIGN KEY (CreatedBy) REFERENCES Users(UserID) ON DELETE SET NULL
);

-- B? các ràng bu?c d? xóa các b?ng n?u c?n
SET FOREIGN_KEY_CHECKS = 0;

-- Xóa các b?ng cu n?u t?n t?i
DROP TABLE IF EXISTS Events;
DROP TABLE IF EXISTS UserClubs;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Clubs;
DROP TABLE IF EXISTS Settings;

SET FOREIGN_KEY_CHECKS = 1;


 
 select * from clubs limit 45;
INSERT INTO clubs (ClubName, Catagories, Description, ImageURL) 
VALUES 
('CLB Văn hóa nghệ thuật', 'Nghệ thuật', 'Câu lạc bộ văn hóa nghệ thuật dành cho những ai yêu thích sáng tạo và nghệ thuật.', 'https://image1.jpg'),
('CLB Melody', 'Âm nhạc', 'Câu lạc bộ Melody dành cho những người đam mê âm nhạc và ca hát.', 'https://image2.jpg'),
('CLB Tiếng Trung', 'Ngôn ngữ', 'Câu lạc bộ Tiếng Trung giúp nâng cao khả năng giao tiếp và học ngôn ngữ Trung Quốc.', 'https://image3.jpg'),
('CLB Business', 'Kinh doanh', 'Câu lạc bộ Business giúp phát triển kỹ năng lãnh đạo và quản lý trong lĩnh vực kinh doanh.', 'https://image4.jpg'),
('CLB Tình nguyện – vì cộng đồng iGo', 'Tình nguyện', 'Câu lạc bộ iGo hoạt động tình nguyện vì cộng đồng, mang đến những hành động ý nghĩa.', 'https://image5.jpg'),
('CLB Kỹ sư phần mềm Nhật Bản JS', 'Công nghệ', 'Câu lạc bộ JS giúp học hỏi kỹ năng lập trình phần mềm với phương pháp Nhật Bản.', 'https://image6.jpg'),
('CLB Guitar', 'Âm nhạc', 'Câu lạc bộ Guitar dành cho những người yêu thích chơi đàn và âm nhạc.', 'https://image7.jpg');
-- ('CLB Street Workout', 'Thể thao', 'Câu lạc bộ Street Workout giúp phát triển sức mạnh cơ thể qua các bài tập ngoài trời.', 'https://image8.jpg'),
-- ('Unicamp', 'Công nghệ', 'Câu lạc bộ Unicamp chuyên tổ chức các chương trình công nghệ sáng tạo cho sinh viên.', 'https://image9.jpg'),
-- ('CLB F-Code', 'Công nghệ', 'Câu lạc bộ F-Code phát triển kỹ năng lập trình và ứng dụng phần mềm.', 'https://image10.jpg'),
-- ('CLB Design', 'Nghệ thuật', 'Câu lạc bộ Design chuyên về thiết kế đồ họa và sáng tạo.', 'https://image11.jpg'),
-- ('CLB íSkate', 'Thể thao', 'Câu lạc bộ íSkate giúp rèn luyện kỹ năng trượt ván và thể thao mạo hiểm.', 'https://image12.jpg'),
-- ('CLB Photography', 'Nghệ thuật', 'Câu lạc bộ Photography dành cho những ai đam mê nhiếp ảnh và sáng tạo hình ảnh.', 'https://image13.jpg'),
-- ('CLB No Shy', 'Văn hóa', 'Câu lạc bộ No Shy giúp các thành viên tự tin thể hiện bản thân và tham gia các hoạt động.', 'https://image14.jpg'),
-- ('CLB Vovinam', 'Võ thuật', 'Câu lạc bộ Vovinam giúp phát triển thể chất và tinh thần qua võ thuật Việt Nam.', 'https://image15.jpg'),
-- ('CLB Mây Mưa Club', 'Văn hóa', 'Câu lạc bộ Mây Mưa dành cho những bạn yêu thích văn hóa Nhật Bản.', 'https://image16.jpg'),
-- ('Quỹ vỏ chai', 'Tình nguyện', 'Quỹ vỏ chai kêu gọi bảo vệ môi trường bằng cách thu gom và tái chế vỏ chai.', 'https://image17.jpg'),
-- ('Đội tuyển bóng đá', 'Thể thao', 'Đội tuyển bóng đá là câu lạc bộ thể thao thi đấu bóng đá.', 'https://image18.jpg'),
-- ('CLB Sách', 'Văn hóa', 'Câu lạc bộ Sách tạo ra không gian đọc sách và chia sẻ văn học.', 'https://image19.jpg'),
-- ('CLB Bóng chuyền', 'Thể thao', 'Câu lạc bộ Bóng chuyền giúp cải thiện kỹ năng thi đấu môn bóng chuyền.', 'https://image20.jpg'),
-- ('CLB Esports', 'Thể thao', 'Câu lạc bộ Esports thi đấu các trò chơi điện tử và tổ chức sự kiện.', 'https://image21.jpg');
