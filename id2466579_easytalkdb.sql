-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 06, 2017 at 03:21 PM
-- Server version: 10.1.20-MariaDB
-- PHP Version: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id2466579_easytalkdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `message` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `users_id` int(11) NOT NULL,
  `sentat` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id`, `message`, `users_id`, `sentat`) VALUES
(1, 'Hello', 11, '2017-08-05 15:15:32'),
(2, 'Hello my friend', 11, '2017-08-05 15:17:30'),
(3, 'Hallo', 11, '2017-08-05 16:57:52'),
(4, 'Hello World', 11, '2017-08-05 17:38:55'),
(5, 'Hello World', 11, '2017-08-05 17:39:11'),
(6, 'Geht schonmal alles iO', 11, '2017-08-05 17:48:22'),
(7, 'TestHide', 11, '2017-08-05 17:51:49'),
(8, '', 11, '2017-08-05 17:51:51'),
(9, '', 11, '2017-08-05 17:51:53'),
(10, 'Hallo', 13, '2017-08-05 18:43:22'),
(11, 'Test', 13, '2017-08-05 18:59:00'),
(12, 'test', 13, '2017-08-05 19:21:20'),
(13, 'Chat', 13, '2017-08-05 19:41:18'),
(14, '', 11, '2017-08-05 19:48:37'),
(15, 'Hall', 11, '2017-08-05 19:49:00'),
(16, 'Wie gehts', 13, '2017-08-05 19:49:22'),
(17, 'Ganz gut', 14, '2017-08-05 20:03:31'),
(18, 'we stehts bei dir', 11, '2017-08-05 20:03:51'),
(19, 'Istklar', 12, '2017-08-05 20:22:11'),
(20, 'ich wiss', 11, '2017-08-05 20:22:20'),
(21, 'ja', 12, '2017-08-05 20:22:25'),
(22, 'Test', 11, '2017-08-05 21:28:05'),
(23, 'Testnachricht', 15, '2017-08-05 21:56:27'),
(24, 'Nachrichten test', 16, '2017-08-05 22:24:37'),
(25, 'Lusche', 11, '2017-08-05 22:26:46'),
(26, 'du selber', 16, '2017-08-05 22:26:57'),
(27, 'Alles klar bei dir?', 16, '2017-08-05 22:43:35'),
(28, 'Ja und selbst', 11, '2017-08-05 22:43:50'),
(29, 'Ja bei mir auch', 11, '2017-08-05 22:48:05'),
(30, 'Jo laeuft', 11, '2017-08-05 22:53:31'),
(31, 'das ist gut', 16, '2017-08-05 22:55:57'),
(32, 'Test', 16, '2017-08-05 23:10:30'),
(33, 'Hallo', 18, '2017-08-06 09:53:44'),
(34, 'Hallo Martin?', 18, '2017-08-06 10:04:34'),
(35, 'Jo ich bins hab neuen Account', 19, '2017-08-06 11:18:48'),
(36, 'Okay habs verstanden', 18, '2017-08-06 11:19:29'),
(37, 'genau ich mag dich nicht', 19, '2017-08-06 11:19:53'),
(38, 'Q.qQ', 18, '2017-08-06 11:20:17'),
(39, '', 19, '2017-08-06 11:20:34'),
(40, 'Danke!', 18, '2017-08-06 11:24:35'),
(41, 'Hallo Florian', 19, '2017-08-06 12:42:04'),
(42, 'Hi Martin', 18, '2017-08-06 12:42:38'),
(43, 'Was machst du heute?', 19, '2017-08-06 12:43:20');

-- --------------------------------------------------------

--
-- Table structure for table `readmessages`
--

CREATE TABLE `readmessages` (
  `users_id` int(11) NOT NULL,
  `messages_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `readmessages`
--

INSERT INTO `readmessages` (`users_id`, `messages_id`) VALUES
(13, 10),
(13, 10),
(13, 10),
(13, 10),
(13, 10),
(13, 10),
(13, 10),
(13, 10),
(13, 10),
(13, 10),
(13, 10),
(11, 1),
(11, 1),
(11, 1),
(11, 1),
(11, 1),
(11, 1),
(11, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(24) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `user_id`) VALUES
('Michel', 'Michel', 11),
('MichelTest', 'Michel', 12),
('Testuser', 'Testuser', 13),
('TestMichel', 'Test', 14),
('User', 'Password', 15),
('Martin', 'Martin', 16),
('Benny', 'Benny', 17),
('Florian', '$2y$10$g3jZiZXNAfEEk8fvDBV9DOH9vnSaEZZgnoc9BV3qrbP85pmK8gKTu', 18),
('TestHash', '$2y$10$.RbACBqLQKpgs1fJoJd1Fe0ZpbVhfuuAn0mGvVS4yhAEkQQ7CVRde', 19);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `messages_users` (`users_id`);

--
-- Indexes for table `readmessages`
--
ALTER TABLE `readmessages`
  ADD KEY `read_users` (`users_id`),
  ADD KEY `read_messages` (`messages_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_users` FOREIGN KEY (`users_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `readmessages`
--
ALTER TABLE `readmessages`
  ADD CONSTRAINT `read_messages` FOREIGN KEY (`messages_id`) REFERENCES `messages` (`id`),
  ADD CONSTRAINT `read_users` FOREIGN KEY (`users_id`) REFERENCES `user` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
