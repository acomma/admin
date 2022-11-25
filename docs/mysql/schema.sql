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

INSERT INTO `menu` (`menu_id`, `parent_id`, `name`, `path`, `create_time`, `update_time`) VALUES (1000, 0, '系统管理', NULL, NOW(), NOW());
INSERT INTO `menu` (`menu_id`, `parent_id`, `name`, `path`, `create_time`, `update_time`) VALUES (1001, 1000, '菜单管理', '/menu', NOW(), NOW());
INSERT INTO `menu` (`menu_id`, `parent_id`, `name`, `path`, `create_time`, `update_time`) VALUES (1002, 1000, '角色管理', '/role', NOW(), NOW());
INSERT INTO `menu` (`menu_id`, `parent_id`, `name`, `path`, `create_time`, `update_time`) VALUES (1003, 1000, '用户管理', '/user', NOW(), NOW());
INSERT INTO `menu` (`menu_id`, `parent_id`, `name`, `path`, `create_time`, `update_time`) VALUES (1004, 1000, '菜单操作', '/menu/action', NOW(), NOW());

INSERT INTO `role` (`role_id`, `name`, `create_time`, `update_time`) VALUES (1000, '超级管理员', NOW(), NOW());

INSERT INTO `role_menu` (`relation_id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1000, 1000, 1000, NOW(), NOW());
INSERT INTO `role_menu` (`relation_id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1001, 1000, 1001, NOW(), NOW());
INSERT INTO `role_menu` (`relation_id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1002, 1000, 1002, NOW(), NOW());
INSERT INTO `role_menu` (`relation_id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1003, 1000, 1003, NOW(), NOW());
INSERT INTO `role_menu` (`relation_id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1004, 1000, 1004, NOW(), NOW());

INSERT INTO `user` (`user_id`, `username`, `password`, `create_time`, `update_time`) VALUES (1000, 'root', '{noop}123456', NOW(), NOW());

INSERT INTO `user_role` (`relation_id`, `user_id`, `role_id`, `create_time`, `update_time`) VALUES (1000, 1000, 1000, NOW(), NOW());

INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1000, 1001, 'menu:view', '查看菜单', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1001, 1001, 'menu:add', '新增菜单', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1002, 1001, 'menu:update', '更新菜单', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1003, 1001, 'menu:delete', '删除菜单', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1004, 1002, 'role:view', '查看角色', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1005, 1002, 'role:add', '新增角色', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1006, 1002, 'role:update', '更新角色', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1007, 1002, 'role:delete', '删除角色', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1008, 1003, 'user:view', '查看用户', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1009, 1003, 'user:add', '新增用户', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1010, 1003, 'user:update', '更新用户', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1011, 1003, 'user:delete', '删除用户', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1012, 1004, 'menu:action:view', '查看菜单操作', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1013, 1004, 'menu:action:add', '新增菜单操作', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1014, 1004, 'menu:action:update', '更新菜单操作', NOW(), NOW());
INSERT INTO `menu_action`(`action_id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1015, 1004, 'menu:action:delete', '删除菜单操作', NOW(), NOW());

INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1000, 1000, 1000, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1001, 1000, 1001, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1002, 1000, 1002, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1003, 1000, 1003, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1004, 1000, 1004, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1005, 1000, 1005, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1006, 1000, 1006, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1007, 1000, 1007, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1008, 1000, 1008, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1009, 1000, 1009, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1010, 1000, 1010, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1011, 1000, 1011, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1012, 1000, 1012, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1013, 1000, 1013, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1014, 1000, 1014, NOW(), NOW());
INSERT INTO `role_action` (`relation_id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1015, 1000, 1015, NOW(), NOW());
