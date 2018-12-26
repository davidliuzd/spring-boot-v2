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

/*Data for the table `sys_permission` */

INSERT  INTO `sys_permission`(`id`,`parent_id`,`res_name`,`res_type`,`permission`,`url`) VALUES (1,NULL,'系统管理','menu','system',NULL),(2,1,'用户管理','menu','systemUserList','/user/userList'),(3,1,'角色管理','menu','systemRole','/roles'),(4,NULL,'一级菜单','menu','menu',NULL),(5,4,'二级菜单1','menu','menuXxx','/xxx'),(6,4,'二级菜单2','menu','menuYyy','/yyy'),(7,2,'用户添加','button','systemUserAdd',NULL);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` BIGINT(20) NOT NULL,
  `role_name` VARCHAR(30) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

INSERT  INTO `sys_role`(`role_id`,`role_name`) VALUES (1,'用户管理员'),(2,'普通用户');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `role_id` BIGINT(20) NOT NULL,
  `permission_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_permission` */

INSERT  INTO `sys_role_permission`(`role_id`,`permission_id`) VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(2,1),(2,2);

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

/*Data for the table `sys_user` */

INSERT  INTO `sys_user`(`user_id`,`user_name`,`full_name`,`password`,`salt`) VALUES (1,'zhangsan','张三','86fb1b048301461bdc71d021d2af3f97','1'),(2,'lisi','李四','c9351e5cf153923f052ebe1462cca93a','2'),(3,'wangwu','王五','92262648696eae1b0a321cbd2b238bf2','3'),(4,'user1','用户1','86fb1b048301461bdc71d021d2af3f97','4'),(5,'user2','用户2','86fb1b048301461bdc71d021d2af3f97','5'),(6,'user3','用户3','86fb1b048301461bdc71d021d2af3f97','5'),(7,'user4','用户4','86fb1b048301461bdc71d021d2af3f97','4'),(8,'user5','用户5','86fb1b048301461bdc71d021d2af3f97','4'),(9,'user6','用户6','86fb1b048301461bdc71d021d2af3f97','4'),(10,'user7','用户7','86fb1b048301461bdc71d021d2af3f97','4'),(11,'user8','用户8','86fb1b048301461bdc71d021d2af3f97','4'),(12,'user9','用户9','86fb1b048301461bdc71d021d2af3f97','4'),(13,'user10','用户10','86fb1b048301461bdc71d021d2af3f97','4');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` BIGINT(20) NOT NULL,
  `role_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

INSERT  INTO `sys_user_role`(`user_id`,`role_id`) VALUES (1,1),(2,2);

/**zhangsan/zhangsan*/
