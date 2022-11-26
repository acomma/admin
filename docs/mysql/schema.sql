CREATE DATABASE `admin` DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_bin;

USE `admin`;

CREATE TABLE `menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '上级ID',
  `name` varchar(32) NOT NULL COMMENT '菜单名称',
  `path` varchar(255) DEFAULT NULL COMMENT '菜单路径',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE,
  UNIQUE KEY `uk_parent_id_name` (`parent_id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单';

CREATE TABLE `role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(32) NOT NULL COMMENT '角色名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色';

CREATE TABLE `role_menu` (
  `relation_id` bigint NOT NULL AUTO_INCREMENT COMMENT '关系ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`relation_id`) USING BTREE,
  UNIQUE KEY `uk_role_id_menu_id` (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色菜单关系';

CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户';

CREATE TABLE `user_role` (
  `relation_id` bigint NOT NULL AUTO_INCREMENT COMMENT '关系ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`relation_id`) USING BTREE,
  UNIQUE KEY `uk_user_id_role_id` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色关系';

CREATE TABLE `menu_action` (
  `action_id` bigint NOT NULL AUTO_INCREMENT COMMENT '操作ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  `code` varchar(128) NOT NULL COMMENT '操作编码',
  `name` varchar(128) NOT NULL COMMENT '操作名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`action_id`) USING BTREE,
  UNIQUE KEY `uk_code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单操作';

CREATE TABLE `role_action` (
  `relation_id` bigint NOT NULL AUTO_INCREMENT COMMENT '关系ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `action_id` bigint NOT NULL COMMENT '操作ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`relation_id`) USING BTREE,
  UNIQUE KEY `uk_role_id_action_id` (`role_id`,`action_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色操作关系';
