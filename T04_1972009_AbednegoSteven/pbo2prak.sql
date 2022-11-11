-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 14, 2021 at 10:22 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pbo2prak`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `created` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `created`) VALUES
(11, 'tes', '2021-10-08 12:03:48'),
(1231, 'assd', '2021-10-08 11:55:04'),
(1234, 'tesLagi', '2021-10-08 12:08:41'),
(12311, 'asda', '2021-10-08 11:55:15'),
(12345, 'apalagi sih', '2021-10-08 14:05:01'),
(1231131, 'asdadaasdasdasda', '2021-10-08 12:11:45'),
(1231312321, 'asdada', '2021-10-08 11:55:25');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(100) NOT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT current_timestamp(),
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `name`, `price`, `description`, `photo`, `created`, `category_id`) VALUES
(1231, 'barang lama', 0, 'asda', NULL, '2021-10-08 11:57:03', 1231312321),
(12313, 'barang baru', 1231, 'asda', NULL, '2021-10-08 11:59:17', 12311),
(12314, 'asda', 1231, 'asda', NULL, '2021-10-08 12:00:48', 12311),
(12315, 'sadsa', 1231, 'asda', NULL, '2021-10-08 12:02:08', 1231),
(12316, 'asda', 12313, 'assdsda', NULL, '2021-10-08 12:09:01', 1234),
(12317, 'assdad', 123132, 'adsa', NULL, '2021-10-08 12:11:39', 11),
(12318, 'asdasd', 1231312, 'asda', NULL, '2021-10-08 14:05:40', 12345),
(12319, 'asda', 123, 'asda', NULL, '2021-10-08 14:06:30', 1234),
(12320, 'asda', 1231, 'asdas', NULL, '2021-10-08 14:58:25', 12345),
(12321, 'asdas', 1232131, 'asdsa', NULL, '2021-10-14 01:15:36', 12345),
(12322, 'asdasdasdasd', 123131, 'asdasd', NULL, '2021-10-14 01:15:49', 1234);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Items_Category_idx` (`category_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1231312322;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12323;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `fk_Items_Category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
