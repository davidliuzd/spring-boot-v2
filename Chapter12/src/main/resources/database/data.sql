
INSERT  INTO `sys_permission`(`id`,`parent_id`,`res_name`,`res_type`,`permission`,`url`) VALUES (1,NULL,'用户管理','menu',NULL,NULL),(2,1,'用户列表','menu','userInfo:view','/userInfo/userList'),(3,1,'用户添加','menu','userInfo:add','/userInfo/userAdd'),(4,1,'用户删除','menu','userInfo:del','/userInfo/userDel');

/*Data for the table `sys_role` */

INSERT  INTO `sys_role`(`role_id`,`role_name`) VALUES (1,'用户管理员'),(2,'普通用户'),(3,'添加&删除用户');

/*Data for the table `sys_role_permission` */

INSERT  INTO `sys_role_permission`(`role_id`,`permission_id`) VALUES (1,2),(1,3),(1,4),(2,2),(3,3),(3,4);

/*Data for the table `sys_user` */

INSERT  INTO `sys_user`(`user_id`,`user_name`,`full_name`,`password`,`salt`) VALUES (1,'zhangsan','张三','86fb1b048301461bdc71d021d2af3f97','1'),(2,'lisi','李四','c9351e5cf153923f052ebe1462cca93a','2'),(3,'wangwu','王五','92262648696eae1b0a321cbd2b238bf2','3'),(4,'user1','无权限用户','86fb1b048301461bdc71d021d2af3f97','1');

/*Data for the table `sys_user_role` */

INSERT  INTO `sys_user_role`(`user_id`,`role_id`) VALUES (1,1),(2,2),(3,3);