CREATE DATABASE club_Manager;
USE club_Manager;

-- Tạo bảng Settings (Bảng 1)
CREATE TABLE Settings (
    SettingID INT AUTO_INCREMENT PRIMARY KEY,
    SettingName VARCHAR(100) NOT NULL,
    SettingType ENUM('String', 'Integer', 'Boolean', 'Date', 'Text') NOT NULL,
    SettingValue TEXT NOT NULL,
    Priority INT DEFAULT 1,
    Status ENUM('Active', 'Inactive') DEFAULT 'Active',
    UserType ENUM('Admin', 'ClubLeader', 'ClubMember', 'WebUser') NOT NULL
);

-- Tạo bảng Users
CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(50) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(100),
    UserType ENUM('Admin', 'ClubLeader', 'ClubMember', 'WebUser') DEFAULT 'WebUser',
    SettingID INT,
    CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    LastLoginDate DATETIME DEFAULT NULL,
    AccountCreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    AccountLastModifiedDate DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    Status ENUM('Active', 'Suspended', 'Inactive') DEFAULT 'Active',
    ImageURL VARCHAR(255),
    FOREIGN KEY (SettingID) REFERENCES Settings(SettingID) ON DELETE SET NULL
);

-- Tạo bảng Clubs
CREATE TABLE Clubs (
    ClubID INT AUTO_INCREMENT PRIMARY KEY,
    ClubName VARCHAR(100) NOT NULL,
    Description TEXT,
    CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    IsPublic BOOLEAN DEFAULT TRUE,
    LastModifiedDate DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    ImageURL VARCHAR(255)
);

-- Tạo bảng UserClubs (Quan hệ nhiều-nhiều giữa Users và Clubs)
CREATE TABLE UserClubs (
    UserID INT,
    ClubID INT,
    JoinedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (UserID, ClubID),
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE,
    FOREIGN KEY (ClubID) REFERENCES Clubs(ClubID) ON DELETE CASCADE
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
    FOREIGN KEY (CreatedBy) REFERENCES Users(UserID) ON DELETE SET NULL
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


ALTER TABLE clubs ADD Categories VARCHAR(100) NOT NULL;
ALTER TABLE clubs DROP COLUMN Categories;


-- B? các ràng bu?c d? xóa các b?ng n?u c?n
SET FOREIGN_KEY_CHECKS = 0;

-- Xóa các b?ng cu n?u t?n t?i
DROP TABLE IF EXISTS Events;
DROP TABLE IF EXISTS UserClubs;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Clubs;
DROP TABLE IF EXISTS Settings;
DROP TABLE IF EXISTS UserRole;


SET FOREIGN_KEY_CHECKS = 1;

delete from settings;
select * from settings;
  select * from users;
 select * from clubs limit 45;
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
