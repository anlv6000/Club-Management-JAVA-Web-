

/*part 3": */
<<<<<<< HEAD
CREATE DATABASE club_Manager1;
USE club_Manager1;
=======
CREATE DATABASE club_Manager;
>>>>>>> f070f6629f27f2e5e7822a22bc8b8b972c053149
USE club_Manager;

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
    Username VARCHAR(50) not null  ,
    Password VARCHAR(255) not null ,
    Email VARCHAR(100),
    UserType ENUM('Admin', 'ClubLeader', 'ClubMember', 'WebUser') DEFAULT 'WebUser',
    SettingID INT,
    CreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    LastLoginDate DATETIME DEFAULT NULL,
    AccountCreatedDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    AccountLastModifiedDate DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    Status ENUM('Active', 'Suspended', 'Inactive') DEFAULT 'Active',
    ImageURL VARCHAR(255),
    google_id varchar(255),
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
    ImageURL VARCHAR(255),
    Categories VARCHAR(100) NOT NULL
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

	

 INSERT INTO clubs (ClubName, Categories, Description, ImageURL) 
VALUES 
('FPTU Vovinam Club', 'Nghệ thuật',
 'Câu lạc bộ Vovinam là nơi hội tụ những người yêu thích võ thuật truyền thống Việt Nam. Tại đây, bạn sẽ được rèn luyện sức khỏe, kỹ năng tự vệ và tinh thần thượng võ. Với đội ngũ huấn luyện viên giàu kinh nghiệm, CLB giúp các thành viên phát triển cả về thể chất lẫn tinh thần, hướng tới một lối sống lành mạnh và kỷ luật.', 
 'https://tse1.mm.bing.net/th?id=OIP.B1WXw9eqT0-v3MCgjqUfZwHaHm&rs=1&pid=ImgDetMain&adlt=strict'),
('CNTT Club', 'Nghệ thuật',
 'Câu lạc bộ Công nghệ Thông tin (CNTT) là nơi dành cho những bạn trẻ đam mê lập trình, công nghệ và khám phá những giải pháp sáng tạo trong lĩnh vực số. CLB thường xuyên tổ chức các buổi workshop, hackathon và đào tạo về các chủ đề như lập trình, AI, cybersecurity và phát triển ứng dụng. Đây là cơ hội tuyệt vời để học hỏi, kết nối và phát triển sự nghiệp trong ngành CNTT.', 
 'https://thumbs.dreamstime.com/b/js-logo-letter-geometric-golden-style-initial-monogram-shape-design-isolated-background-gold-238640508.jpg'),
('Trượt ván Club', 'Nghệ thuật',
 'Câu lạc bộ Trượt ván là nơi dành cho những ai yêu thích sự tự do và cảm giác mạnh trên bánh xe. Dưới sự hướng dẫn của các thành viên kỳ cựu, bạn sẽ học được các kỹ thuật trượt ván cơ bản, biểu diễn động tác và thử thách bản thân với những đường trượt táo bạo. Không chỉ là nơi rèn luyện kỹ năng, CLB còn là không gian giao lưu, sáng tạo và thể hiện cá tính.', 
 'https://truot.vn/public/userfiles/news/TU_VANVNTRUOT-01.jpg'),
('Bóng chuyền Club', 'Nghệ thuật',
 'Câu lạc bộ Bóng chuyền là sân chơi năng động dành cho những người yêu thích thể thao đồng đội. Tham gia CLB, bạn sẽ được rèn luyện kỹ năng chuyền, đập bóng và chiến thuật thi đấu. Các buổi tập luyện không chỉ giúp cải thiện thể lực mà còn thúc đẩy tinh thần đoàn kết và gắn kết giữa các thành viên.', 
 'https://makan.vn/wp-content/uploads/2023/04/logo-ao-bong-chuyen-01-2-768x768.jpg'),
('Muay Thai Club', 'Nghệ thuật',
 'Câu lạc bộ Muay Thai mang đến cơ hội khám phá môn võ thuật nổi tiếng của Thái Lan. Đây là nơi lý tưởng để rèn luyện sức mạnh, kỹ thuật đối kháng và khả năng chịu đựng. Với sự hướng dẫn tận tình từ huấn luyện viên chuyên nghiệp, bạn sẽ nhanh chóng nâng cao kỹ năng chiến đấu và sự tự tin trong cuộc sống hàng ngày.', 
 'https://tse4.mm.bing.net/th?id=OIP.EC5b6aD_n5ic0QqjYcgAzQHaHa&rs=1&pid=ImgDetMain'),
 ('Hackathon Club'	, 'Nghệ thuật',
 'Câu lạc bộ Công nghệ Thông tin (CNTT) là nơi dành cho những bạn trẻ đam mê lập trình, công nghệ và khám phá những giải pháp sáng tạo trong lĩnh vực số. CLB thường xuyên tổ chức các buổi workshop, hacka...'	,
 'https://yt3.ggpht.com/a/AGF-l7-MtSt82Zw_Uk4ap9HhboZSCUCondclQ6cMQg=s900-c-k-c0xffffffff-no-rj-mo'),
('CLB Văn hóa nghệ thuật', 'Nghệ thuật', 'Câu lạc bộ văn hóa nghệ thuật dành cho những ai yêu thích sáng tạo và nghệ thuật.',
 'https://th.bing.com/th/id/OIP.O9hT2bIXaWFpdUX-lxh4MAHaHa?rs=1&pid=ImgDetMain'),
('CLB Melody', 'Âm nhạc', 'Câu lạc bộ Melody dành cho những người đam mê âm nhạc và ca hát.', 
'https://static.vecteezy.com/system/resources/previews/015/718/690/non_2x/melody-logo-colorful-gradient-style-vector.jpg'),
('CLB Tiếng Trung', 'Ngôn ngữ', 'Câu lạc bộ Tiếng Trung giúp nâng cao khả năng giao tiếp và học ngôn ngữ Trung Quốc.', 
'https://files01.duytan.edu.vn/svruploads/ktiengtrung-duytan/upload/images/638670938698138746-49.jpg'),
('CLB Business', 'Kinh doanh', 'Câu lạc bộ Business giúp phát triển kỹ năng lãnh đạo và quản lý trong lĩnh vực kinh doanh.',
'https://cdn.pixabay.com/photo/2015/04/22/21/19/club-735353_960_720.png'),
('CLB Tình nguyện – vì cộng đồng iGo', 'Tình nguyện', 'Câu lạc bộ iGo hoạt động tình nguyện vì cộng đồng, mang đến những hành động ý nghĩa.', 
'https://haitrieu.com/wp-content/uploads/2022/01/Logo-Chien-Dich-Xuan-Tinh-Nguyen-635x642.png'),
('CLB Kỹ sư phần mềm Nhật Bản JS', 'Công nghệ', 'Câu lạc bộ JS giúp học hỏi kỹ năng lập trình phần mềm với phương pháp Nhật Bản.', 
'https://gudlogo.com/wp-content/uploads/2019/05/logo-nhat-ban-42.jpg'),
('CLB Guitar', 'Âm nhạc', 'Câu lạc bộ Guitar dành cho những người yêu thích chơi đàn và âm nhạc.', 
'https://th.bing.com/th/id/OIP.4OaanNRSUFnIkMnVPCpiVwHaHa?rs=1&pid=ImgDetMain');
