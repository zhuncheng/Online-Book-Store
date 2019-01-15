-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.21 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5288
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for ckccassignmentdb
CREATE DATABASE IF NOT EXISTS `ckccassignmentdb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ckccassignmentdb`;

-- Dumping structure for table ckccassignmentdb.address
CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `postalCode` varchar(50) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table ckccassignmentdb.address: 8 rows
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` (`id`, `street`, `city`, `state`, `country`, `postalCode`, `customer_id`) VALUES
	(1, '2', 'PP', 'PP', 'Cambodia', '885', 1),
	(2, '2', 'PP', 'PP', 'Cambodia', '885', 1),
	(3, '1', 'Kasukabe', 'Kyouto', 'Japan', '123', 2),
	(4, '2', 'Kasaukabe', 'Kyouto', 'Japan', '123', 2),
	(5, '66', 'Kasukabe', 'Kyouuto', 'Japan', '999', 3),
	(6, '99', 'Kasukabe', 'Kyouto', 'Japan', '999', 3),
	(7, '11', 'Kasukabe', 'Kyouto', 'Japan', '8888', 4),
	(8, '11', 'Kasukabe', 'Kyouto', 'Japan', '888', 4);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;

-- Dumping structure for table ckccassignmentdb.book
CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT '0',
  `publisher` varchar(255) DEFAULT '0',
  `publishedYear` varchar(10) DEFAULT '0',
  `ISBN` varchar(100) DEFAULT '0',
  `price` double DEFAULT '0',
  `author` varchar(255) DEFAULT '0',
  `edition` int(11) DEFAULT '0',
  `volume` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COMMENT='table book';

-- Dumping data for table ckccassignmentdb.book: 9 rows
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`id`, `title`, `publisher`, `publishedYear`, `ISBN`, `price`, `author`, `edition`, `volume`) VALUES
	(1, 'Voodoo Island', 'Bookworm', '2008', 'B001', 10, 'Michael Duckworth', 1, 10),
	(2, 'Oliber Twist', 'Oxford Boookworm Library', '2000', 'B002', 20, 'Chales Dicken', 10, 10),
	(3, 'Robinson Crusoe', 'Oxford Bookworm Library', '1999', 'B003', 10, 'Daniel Defoe', 1, 10),
	(4, 'Agatha Christie', 'Oxford Bookworm Library', '2008', 'B004', 5, 'John Escott', 1, 10),
	(5, 'One Punch man', 'Jame', '2000', 'B005', 5, 'Marren', 1, 10),
	(6, 'Forrest Gump', 'NA', '1994', 'B006', 10, 'Dunno', 1, 10),
	(7, 'Your Name', 'Dunno', '2016', 'B007', 10, 'Hiroshii', 1, 10),
	(8, 'Book', 'Dunno', '2018', 'B007', 10, 'Dunno', 1, 10),
	(9, 'Kumino Nawa', 'Dunno', '2019', 'B010', 10, 'Author', 1, 10);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;

-- Dumping structure for table ckccassignmentdb.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '0',
  `email` varchar(100) NOT NULL DEFAULT '0',
  `phone` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table ckccassignmentdb.customer: 4 rows
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`id`, `name`, `email`, `phone`) VALUES
	(1, 'Leang Chan', 'leangchan@gmail.com', '098998766'),
	(2, 'Pengleng Chan', 'pengleng@gmail.com', '0979109999'),
	(3, 'Shin Chan', 'shinchan@gmail.com', '0456789'),
	(4, 'Nana Ko', 'Nanako@gmail.com', '097893457411');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table ckccassignmentdb.orderbook
CREATE TABLE IF NOT EXISTS `orderbook` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL,
  `discount` double NOT NULL,
  `total` double NOT NULL,
  `remark` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table ckccassignmentdb.orderbook: 4 rows
/*!40000 ALTER TABLE `orderbook` DISABLE KEYS */;
INSERT INTO `orderbook` (`id`, `customer_id`, `discount`, `total`, `remark`) VALUES
	(1, 1, 5, 17.1, 'paid'),
	(2, 2, 10, 16.2, 'Paid'),
	(3, 3, 10, 24.3, 'paid'),
	(4, 4, 10, 12.15, 'Paid');
/*!40000 ALTER TABLE `orderbook` ENABLE KEYS */;

-- Dumping structure for table ckccassignmentdb.orderdetail
CREATE TABLE IF NOT EXISTS `orderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `qty` double NOT NULL,
  `discount` double NOT NULL,
  `subtotal` double NOT NULL,
  `price` double NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table ckccassignmentdb.orderdetail: 6 rows
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` (`id`, `product_id`, `qty`, `discount`, `subtotal`, `price`, `order_id`) VALUES
	(1, 6, 2, 10, 18, 10, 1),
	(2, 7, 2, 10, 18, 10, 2),
	(3, 3, 1, 10, 9, 10, 3),
	(4, 7, 2, 10, 18, 10, 3),
	(5, 9, 1, 10, 9, 10, 4),
	(6, 5, 1, 10, 4.5, 5, 4);
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;

-- Dumping structure for table ckccassignmentdb.stock
CREATE TABLE IF NOT EXISTS `stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Dumping data for table ckccassignmentdb.stock: 8 rows
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` (`id`, `book_id`, `qty`) VALUES
	(1, 1, 10),
	(2, 2, 10),
	(3, 3, 9),
	(9, 9, 99),
	(5, 5, 29),
	(6, 6, 10),
	(7, 7, 16),
	(8, 8, 20);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
