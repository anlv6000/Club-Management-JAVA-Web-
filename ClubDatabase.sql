-- Create Settings table for system settings with user status
CREATE TABLE Settings (
    SettingID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT FOREIGN KEY REFERENCES Users(UserID),
    SettingName NVARCHAR(100) NOT NULL,
    SettingValue NVARCHAR(MAX),
    Description NVARCHAR(500),
    UserStatus NVARCHAR(50) -- 'Active', 'Banned', etc.
);

-- Modify Users table to include ClubID (if not done already)
ALTER TABLE Users
ADD ClubID INT FOREIGN KEY REFERENCES Clubs(ClubID);

-- Create Users table to store all types of users
CREATE TABLE Users (
    UserID INT IDENTITY(1,1) PRIMARY KEY,
    Username NVARCHAR(50) NOT NULL UNIQUE,
    Password NVARCHAR(255) NOT NULL,
    Email NVARCHAR(100) NOT NULL UNIQUE,
    UserType NVARCHAR(20) NOT NULL, -- 'Guest', 'WebUser', 'SystemAdmin', 'ClubMember'
    CreatedDate DATETIME DEFAULT GETDATE(),
    LastLoginDate DATETIME,
    ClubID INT FOREIGN KEY REFERENCES Clubs(ClubID) -- New column to reference Clubs table
);

-- Other tables remain the same
CREATE TABLE Clubs (
    ClubID INT IDENTITY(1,1) PRIMARY KEY,
    ClubName NVARCHAR(100) NOT NULL,
    Description NVARCHAR(MAX),
    CreatedDate DATETIME DEFAULT GETDATE(),
    IsPublic BIT DEFAULT 1
);

CREATE TABLE Events (
    EventID INT IDENTITY(1,1) PRIMARY KEY,
    ClubID INT FOREIGN KEY REFERENCES Clubs(ClubID),
    EventName NVARCHAR(200) NOT NULL,
    Description NVARCHAR(MAX),
    EventDate DATETIME,
    CreatedBy INT FOREIGN KEY REFERENCES Users(UserID),
    CreatedDate DATETIME DEFAULT GETDATE()
);

CREATE TABLE AccountDetails (
    AccountID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT FOREIGN KEY REFERENCES Users(UserID),
    AccountInfo NVARCHAR(MAX),
    CreatedDate DATETIME DEFAULT GETDATE(),
    LastModifiedDate DATETIME
);

CREATE TABLE ClubDetails (
    ClubDetailID INT IDENTITY(1,1) PRIMARY KEY,
    ClubID INT FOREIGN KEY REFERENCES Clubs(ClubID),
    DetailInfo NVARCHAR(MAX),
    LastModifiedDate DATETIME DEFAULT GETDATE()
);