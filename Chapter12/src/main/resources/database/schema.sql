DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` BIGINT(20) NOT NULL,
  `parent_id` BIGINT(20) DEFAULT NULL,
  `res_name` VARCHAR(50) DEFAULT NULL,
  `res_type` VARCHAR(10) DEFAULT NULL,
  `permission` VARCHAR(20) DEFAULT NULL,
  `url` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_role` */
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` BIGINT(20) NOT NULL,
  `role_name` VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `role_id` BIGINT(20) NOT NULL,
  `permission_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` BIGINT(20) NOT NULL,
  `user_name` VARCHAR(50) DEFAULT NULL,
  `full_name` VARCHAR(20) DEFAULT NULL,
  `password` VARCHAR(32) DEFAULT NULL,
  `salt` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` BIGINT(20) NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
/**zhangsan/zhangsan*/
