USE `admin`;

INSERT INTO `menu` (`id`, `parent_id`, `name`, `path`, `create_time`, `update_time`) VALUES (1000, 0, '系统管理', NULL, NOW(), NOW());
INSERT INTO `menu` (`id`, `parent_id`, `name`, `path`, `create_time`, `update_time`) VALUES (1001, 1000, '菜单管理', '/menu', NOW(), NOW());
INSERT INTO `menu` (`id`, `parent_id`, `name`, `path`, `create_time`, `update_time`) VALUES (1002, 1000, '角色管理', '/role', NOW(), NOW());
INSERT INTO `menu` (`id`, `parent_id`, `name`, `path`, `create_time`, `update_time`) VALUES (1003, 1000, '用户管理', '/user', NOW(), NOW());
INSERT INTO `menu` (`id`, `parent_id`, `name`, `path`, `create_time`, `update_time`) VALUES (1004, 1000, '菜单操作', '/menu/action', NOW(), NOW());

INSERT INTO `role` (`id`, `name`, `create_time`, `update_time`) VALUES (1000, '系统管理员', NOW(), NOW());

INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1000, 1000, 1000, NOW(), NOW());
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1001, 1000, 1001, NOW(), NOW());
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1002, 1000, 1002, NOW(), NOW());
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1003, 1000, 1003, NOW(), NOW());
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`, `create_time`, `update_time`) VALUES (1004, 1000, 1004, NOW(), NOW());

INSERT INTO `user` (`id`, `username`, `password`, `create_time`, `update_time`) VALUES (1000, 'admin', '{noop}123456', NOW(), NOW());

INSERT INTO `user_role` (`id`, `user_id`, `role_id`, `create_time`, `update_time`) VALUES (1000, 1000, 1000, NOW(), NOW());

INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1000, 1001, 'menu:view', '查看菜单', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1001, 1001, 'menu:add', '新增菜单', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1002, 1001, 'menu:update', '更新菜单', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1003, 1001, 'menu:delete', '删除菜单', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1004, 1002, 'role:view', '查看角色', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1005, 1002, 'role:add', '新增角色', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1006, 1002, 'role:update', '更新角色', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1007, 1002, 'role:delete', '删除角色', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1008, 1003, 'user:view', '查看用户', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1009, 1003, 'user:add', '新增用户', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1010, 1003, 'user:update', '更新用户', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1011, 1003, 'user:delete', '删除用户', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1012, 1004, 'menu:action:view', '查看菜单操作', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1013, 1004, 'menu:action:add', '新增菜单操作', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1014, 1004, 'menu:action:update', '更新菜单操作', NOW(), NOW());
INSERT INTO `action`(`id`, `menu_id`, `code`, `name`, `create_time`, `update_time`) VALUES (1015, 1004, 'menu:action:delete', '删除菜单操作', NOW(), NOW());

INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1000, 1000, 1000, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1001, 1000, 1001, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1002, 1000, 1002, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1003, 1000, 1003, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1004, 1000, 1004, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1005, 1000, 1005, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1006, 1000, 1006, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1007, 1000, 1007, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1008, 1000, 1008, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1009, 1000, 1009, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1010, 1000, 1010, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1011, 1000, 1011, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1012, 1000, 1012, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1013, 1000, 1013, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1014, 1000, 1014, NOW(), NOW());
INSERT INTO `role_action` (`id`, `role_id`, `action_id`, `create_time`, `update_time`) VALUES (1015, 1000, 1015, NOW(), NOW());
