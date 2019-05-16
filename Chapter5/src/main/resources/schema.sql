drop table if exists city;
drop table if exists hotel;

CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `country` varchar(255) NOT NULL,
  `map` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `hotel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `zip` varchar(255) NOT NULL,
  `city_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_city_id_name` (`city_id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



