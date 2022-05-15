-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 06:06 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogriddb`
--

-- --------------------------------------------------------

--
-- Table structure for table `complain_table`
--

CREATE TABLE `complain_table` (
  `Complain_id` int(5) NOT NULL,
  `Description` varchar(50) NOT NULL,
  `complainDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `User_ID` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `complain_table`
--

INSERT INTO `complain_table` (`Complain_id`, `Description`, `complainDate`, `User_ID`) VALUES
(33, 'Test 123', '2022-05-13 14:44:00', 'US100'),
(37, 'test9', '2022-05-13 18:30:00', 'US100'),
(41, 'test 2', '2022-05-13 17:14:00', '32'),
(42, 'Testttt 445896', '2022-05-13 17:23:00', '33'),
(43, 'Test Update', '2022-05-13 17:26:00', '42');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `complain_table`
--
ALTER TABLE `complain_table`
  ADD PRIMARY KEY (`Complain_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `complain_table`
--
ALTER TABLE `complain_table`
  MODIFY `Complain_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
