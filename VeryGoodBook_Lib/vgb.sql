DROP DATABASE IF EXISTS vgb;

CREATE DATABASE vgb DEFAULT CHARACTER SET utf8mb4;

USE vgb;

CREATE TABLE `customers` (
  `id` char(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(60) NOT NULL,
  `gender` char(1) NOT NULL,
  `birthday` date NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `married` tinyint(1) DEFAULT NULL,
  `blood_type` varchar(2) DEFAULT NULL,
  `discount` int(3) NOT NULL DEFAULT '0',
  `class_name` varchar(20) NOT NULL DEFAULT 'Customer',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unix_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `products` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `unit_price` double NOT NULL,
  `stock` int(11) NOT NULL DEFAULT '0',
  `description` varchar(200) DEFAULT NULL,
  `photo_url` varchar(200) DEFAULT NULL,
  `colors` varchar(100) NOT NULL DEFAULT '',
  `discount` int(11) NOT NULL DEFAULT '0',
  `class_name` varchar(20) NOT NULL DEFAULT 'Product',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS book_details;
CREATE TABLE book_details (
  product_id int(10) unsigned NOT NULL,
  subtitle varchar(80) DEFAULT NULL,
  authers varchar(200) NOT NULL,
  publisher varchar(20) NOT NULL,
  publish_date date DEFAULT NULL,
  isbn varchar(13) NOT NULL DEFAULT '',
  PRIMARY KEY (product_id),  
  CONSTRAINT FK_book_details_2_products_id FOREIGN KEY (product_id) REFERENCES products (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` char(10) NOT NULL,
  `order_date` date NOT NULL,
  `order_time` time NOT NULL,
  `payment_type` varchar(20) NOT NULL,
  `payment_fee` double NOT NULL DEFAULT '0',
  `payment_note` varchar(100) NOT NULL DEFAULT '',
  `shipping_type` varchar(20) NOT NULL,
  `shipping_fee` double NOT NULL DEFAULT '0',
  `shipping_note` varchar(100) NOT NULL DEFAULT '',
  `recipient_name` varchar(30) NOT NULL,
  `recipient_email` varchar(60) NOT NULL,
  `recipient_phone` varchar(20) NOT NULL,
  `recipient_address` varchar(100) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fkey_orders_to_customers_idx` (`customer_id`),
  CONSTRAINT `fkey_orders_to_customers` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `order_items` (
  `order_id` int(10) unsigned NOT NULL,
  `product_id` int(10) unsigned NOT NULL,
  `color` varchar(30) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`,`color`),
  KEY `fkey_order_items_to _products_idx` (`product_id`),
  CONSTRAINT `fkey_order_items_to _products` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkey_order_items_to_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
