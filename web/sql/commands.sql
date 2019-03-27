CREATE DATABASE WebPortalDB;
USE WebPortalDB;
CREATE TABLE `users` (
  `netID` varchar(10) NOT NULL,
  `password` varchar(16) NOT NULL,
  `firstName` varchar(100) NOT NULL DEFAULT '',
  `lastName` varchar(100) NOT NULL DEFAULT '',
  `type` int(1) NOT NULL,
  `startTerm` VARCHAR(10) DEFAULT NULL,
  `startYear` INT(4) DEFAULT NULL,
  `program` VARCHAR(4) DEFAULT NULL,
  `department` VARCHAR(100) DEFAULT NULL,
  `phoneNumber` VARCHAR(13) DEFAULT NULL,
  `advisor` TEXT DEFAULT NULL,
  PRIMARY KEY (`netID`)
);
CREATE TABLE `resources` (
  `name` VARCHAR(100) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  `info` TEXT NOT NULL,
  PRIMARY KEY (`name`)
);
CREATE TABLE `reservations` (
  `name` VARCHAR(100) NOT NULL,
  `netID` VARCHAR(10) NOT NULL,
  `slot_date` DATE NOT NULL,
  `slot_time_range` VARCHAR(5),
  FOREIGN KEY (`name`) REFERENCES `resources`(`name`),
  FOREIGN KEY (`netID`) REFERENCES `users`(`netID`),
  PRIMARY KEY (`name`,`netID`,`slot_date`,`slot_time_range`)
);
CREATE TABLE `courses` (
  `number` VARCHAR(10) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `department` VARCHAR(100) NOT NULL,
  `course_syllabus` TEXT NOT NULL,
  `ins_office_hour` VARCHAR(100) DEFAULT NULL,
  `ins_office` VARCHAR(100) DEFAULT NULL,
  `ta_name` VARCHAR(100) DEFAULT NULL,
  `ta_office_hour` VARCHAR(100) DEFAULT NULL,
  `ta_office` VARCHAR(100) DEFAULT NULL,
  `ta_email` VARCHAR(100) DEFAULT NULL,
  `term` VARCHAR(10) NOT NULL,
  `year` INT(4) NOT NULL,
  PRIMARY KEY (`number`, `term`, `year`)
);
CREATE TABLE `course_user` (
  `netID` varchar(10) NOT NULL,
  `number` VARCHAR(10) NOT NULL,
  `term` VARCHAR(10) NOT NULL,
  `year` INT(4) NOT NULL,
  FOREIGN KEY (`netID`) REFERENCES `users`(`netID`),
  FOREIGN KEY (`number`, `term`, `year`) REFERENCES `courses`(`number`, `term`, `year`)
);
CREATE TABLE `alumni` (
  `name` VARCHAR(100) NOT NULL,
  `homepage` VARCHAR(100) DEFAULT NULL,
  `description` TEXT DEFAULT NULL,
  `image` VARCHAR(500) DEFAULT NULL,
  PRIMARY KEY (`name`)
);

CREATE TABLE `announcements` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `netID` VARCHAR(10) NOT NULL,
  `link` VARCHAR(500) DEFAULT NULL,
  `title` VARCHAR(100) DEFAULT NULL,
  `details` VARCHAR(500) DEFAULT NULL,
  `announcement_type` INT(1) NOT NULL,
  `event_datetime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `event_venue` VARCHAR(500) DEFAULT NULL,
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (`netID`) REFERENCES `users`(`netID`)
);

CREATE TABLE `discussion_post` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `discussion_id` INT DEFAULT NULL,
  `netID` VARCHAR(10) NOT NULL,
  `title` TEXT DEFAULT NULL,
  `type` INT(1) NOT NULL DEFAULT 0,
  `details` TEXT DEFAULT NULL,
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (`netID`) REFERENCES `users`(`netID`)
);

CREATE TABLE `exams` (
  `examID` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `netID` VARCHAR(10) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `date_of_exam` DATE NOT NULL,
  `additional_details` TEXT DEFAULT NULL,
  FOREIGN KEY (`netID`) REFERENCES `users`(`netID`)
);

CREATE TABLE `exam_user` (
  `examID` INT NOT NULL,
  `netID` VARCHAR(10) NOT NULL,
  FOREIGN KEY (`netID`) REFERENCES `users`(`netID`),
  FOREIGN KEY (`examID`) REFERENCES `exams`(`examID`)
);

CREATE TABLE `results` (
  `resultID` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `netID` VARCHAR(10) NOT NULL,
  `result_details` TEXT NOT NULL,
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `examID` INT NOT NULL,
  FOREIGN KEY (`netID`) REFERENCES `users`(`netID`),
  FOREIGN KEY (`examID`) REFERENCES `exams`(`examID`)
);

ALTER TABLE `users`
  ADD COLUMN `phoneNumber` VARCHAR(13) DEFAULT NULL,
  ADD COLUMN `advisor` TEXT DEFAULT NULL;