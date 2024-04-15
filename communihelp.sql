-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 29, 2024 at 03:51 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `communihelp`
--

-- --------------------------------------------------------

--
-- Table structure for table `admintable`
--

CREATE TABLE `admintable` (
  `adminid` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admintable`
--

INSERT INTO `admintable` (`adminid`, `password`) VALUES
('admin', 'welcome');

-- --------------------------------------------------------

--
-- Table structure for table `basiclogin`
--

CREATE TABLE `basiclogin` (
  `username` varchar(30) NOT NULL,
  `userid` varchar(6) NOT NULL,
  `password` varchar(50) NOT NULL,
  `mobileno` bigint(10) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `basiclogin`
--

INSERT INTO `basiclogin` (`username`, `userid`, `password`, `mobileno`, `dob`, `email`) VALUES
('Krishna', 't96c', 'hello123', 7892134760, '2001-12-10', 'krishnaradha12@gmail.com'),
('ChandraShetty', 't24f', 'welcome', 6301440457, '1993-07-07', 'chandrahas12sigili@gmail.com'),
('leela', 't49c', 'hello123', 8431647678, '2003-12-15', 'leelananda122@gmail.com'),
('indra', 't68b', 'welcome', 6304324313, '2002-06-22', 'indras345@gmail.com'),
('VishnuSai', 't18c', 'hii123', 7386484373, '2002-11-28', 'saivishnu980@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `donations`
--

CREATE TABLE `donations` (
  `roomid` varchar(10) NOT NULL,
  `description` text NOT NULL,
  `accbyroomid` varchar(10) NOT NULL,
  `accbymobno` bigint(10) NOT NULL,
  `status` varchar(20) NOT NULL,
  `datatime` datetime NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `donations`
--

INSERT INTO `donations` (`roomid`, `description`, `accbyroomid`, `accbymobno`, `status`, `datatime`, `name`) VALUES
('t24f', 'books', 't68b', 6304324313, 'Available', '2012-12-12 12:00:00', 'Chandra'),
('t96c', 'sofa', 'null', 1, 'Available', '2012-12-12 12:00:00', 'Radha'),
('t24f', 'tcl 32 inch tv', 't96c', 7892134762, 'Available', '2023-12-24 17:30:37', 'ganesh'),
('t24f', 'Circket Kit', 'nul', 1, 'Available', '2024-01-08 19:09:30', 'Penchala'),
('t24f', 'Induction Stove', 'null', 1, 'Available', '2024-01-08 19:13:23', 'Bhanu'),
('t18c', 'Table', 'null', 1, 'Available', '2024-01-08 22:58:32', 'Praveen');

-- --------------------------------------------------------

--
-- Table structure for table `requests`
--

CREATE TABLE `requests` (
  `roomid` varchar(10) NOT NULL,
  `description` text NOT NULL,
  `accbyroomid` varchar(10) NOT NULL,
  `accbymobno` bigint(10) NOT NULL,
  `status` varchar(10) NOT NULL,
  `datatime` datetime NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `requests`
--

INSERT INTO `requests` (`roomid`, `description`, `accbyroomid`, `accbymobno`, `status`, `datatime`, `name`) VALUES
('t24f', 'O+ blood', 't96c', 7892134762, 'Needed', '2012-12-12 12:00:00', 'chandra'),
('t68b', 'Cold Tablets', 't24f', 6301440457, 'Taken', '2012-12-12 12:00:00', 'indra'),
('t96c', '1 Chair', 'null', 1, 'Needed', '2023-12-24 17:41:06', 'Chetan');

-- --------------------------------------------------------

--
-- Table structure for table `servicestore`
--

CREATE TABLE `servicestore` (
  `roomid` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `mblno` bigint(10) NOT NULL,
  `service` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL,
  `datetime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `servicestore`
--

INSERT INTO `servicestore` (`roomid`, `name`, `mblno`, `service`, `status`, `datetime`) VALUES
('t24f', 'Shetty', 2345167809, 'WaterCan', 'booked', '2023-12-18 11:52:06'),
('t18c', 'VishnuSai', 2345167809, 'Electrician', 'Completed', '2023-12-18 12:04:31'),
('t24f', 'Gopal', 9876523453, 'Cleaning', 'Cancelled', '2023-12-18 12:26:54'),
('t24f', 'Bhanu', 8985123245, 'Cleaning Service', 'booked', '2024-01-03 14:24:06'),
('t24f', 'Lokesh', 8985034567, 'WaterCan', 'Cancelled', '2024-01-27 08:51:10');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
