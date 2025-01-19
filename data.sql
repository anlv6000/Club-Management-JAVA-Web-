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

 

 
 
 select * from clubs limit 5
 INSERT INTO clubs (ClubName, Description, ImageURL) 
VALUES 
('FPTU Vovinam Club', 
 'Câu lạc bộ Vovinam là nơi hội tụ những người yêu thích võ thuật truyền thống Việt Nam. Tại đây, bạn sẽ được rèn luyện sức khỏe, kỹ năng tự vệ và tinh thần thượng võ. Với đội ngũ huấn luyện viên giàu kinh nghiệm, CLB giúp các thành viên phát triển cả về thể chất lẫn tinh thần, hướng tới một lối sống lành mạnh và kỷ luật.', 
 'https://tse1.mm.bing.net/th?id=OIP.B1WXw9eqT0-v3MCgjqUfZwHaHm&rs=1&pid=ImgDetMain&adlt=strict'),
('CNTT Club', 
 'Câu lạc bộ Công nghệ Thông tin (CNTT) là nơi dành cho những bạn trẻ đam mê lập trình, công nghệ và khám phá những giải pháp sáng tạo trong lĩnh vực số. CLB thường xuyên tổ chức các buổi workshop, hackathon và đào tạo về các chủ đề như lập trình, AI, cybersecurity và phát triển ứng dụng. Đây là cơ hội tuyệt vời để học hỏi, kết nối và phát triển sự nghiệp trong ngành CNTT.', 
 'https://thumbs.dreamstime.com/b/js-logo-letter-geometric-golden-style-initial-monogram-shape-design-isolated-background-gold-238640508.jpg'),
('Trượt ván Club', 
 'Câu lạc bộ Trượt ván là nơi dành cho những ai yêu thích sự tự do và cảm giác mạnh trên bánh xe. Dưới sự hướng dẫn của các thành viên kỳ cựu, bạn sẽ học được các kỹ thuật trượt ván cơ bản, biểu diễn động tác và thử thách bản thân với những đường trượt táo bạo. Không chỉ là nơi rèn luyện kỹ năng, CLB còn là không gian giao lưu, sáng tạo và thể hiện cá tính.', 
 'https://truot.vn/public/userfiles/news/TU_VANVNTRUOT-01.jpg'),
('Bóng chuyền Club', 
 'Câu lạc bộ Bóng chuyền là sân chơi năng động dành cho những người yêu thích thể thao đồng đội. Tham gia CLB, bạn sẽ được rèn luyện kỹ năng chuyền, đập bóng và chiến thuật thi đấu. Các buổi tập luyện không chỉ giúp cải thiện thể lực mà còn thúc đẩy tinh thần đoàn kết và gắn kết giữa các thành viên.', 
 'https://makan.vn/wp-content/uploads/2023/04/logo-ao-bong-chuyen-01-2-768x768.jpg'),
('Muay Thai Club', 
 'Câu lạc bộ Muay Thai mang đến cơ hội khám phá môn võ thuật nổi tiếng của Thái Lan. Đây là nơi lý tưởng để rèn luyện sức mạnh, kỹ thuật đối kháng và khả năng chịu đựng. Với sự hướng dẫn tận tình từ huấn luyện viên chuyên nghiệp, bạn sẽ nhanh chóng nâng cao kỹ năng chiến đấu và sự tự tin trong cuộc sống hàng ngày.', 
 'https://tse4.mm.bing.net/th?id=OIP.EC5b6aD_n5ic0QqjYcgAzQHaHa&rs=1&pid=ImgDetMain');