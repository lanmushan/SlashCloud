/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : db_slash

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 28/03/2021 19:07:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_fk_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `auth_fk_role_resource`;
CREATE TABLE `auth_fk_role_resource`  (
  `id` bigint(20) NOT NULL,
  `fk_resource_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `fk_role_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `fk_resource_id`(`fk_resource_id`, `fk_role_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 410 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_fk_role_resource
-- ----------------------------
INSERT INTO `auth_fk_role_resource` VALUES (825445885621043200, 13, '2021-03-27 11:07:15', '2021-03-27 11:07:15', 'admin');
INSERT INTO `auth_fk_role_resource` VALUES (825448790491136000, 825387741163290624, '2021-03-27 11:18:48', '2021-03-27 11:18:48', 'anon');
INSERT INTO `auth_fk_role_resource` VALUES (825450490182500352, 825387569872109568, '2021-03-27 11:25:33', '2021-03-27 11:25:33', 'admin');
INSERT INTO `auth_fk_role_resource` VALUES (825450490182500353, 825387569872109568, '2021-03-27 11:25:33', '2021-03-27 11:25:33', 'anon');
INSERT INTO `auth_fk_role_resource` VALUES (825453074574213120, 825369038333935616, '2021-03-27 11:35:49', '2021-03-27 11:35:49', 'admin');
INSERT INTO `auth_fk_role_resource` VALUES (825453074574213121, 825369038333935616, '2021-03-27 11:35:49', '2021-03-27 11:35:49', 'anon');
INSERT INTO `auth_fk_role_resource` VALUES (825453096804024320, 825376991187304448, '2021-03-27 11:35:54', '2021-03-27 11:35:54', 'admin');
INSERT INTO `auth_fk_role_resource` VALUES (825453096804024321, 825376991187304448, '2021-03-27 11:35:54', '2021-03-27 11:35:54', 'anon');
INSERT INTO `auth_fk_role_resource` VALUES (825482815624708096, 825482815251415040, '2021-03-27 13:34:00', '2021-03-27 13:34:00', 'anon');
INSERT INTO `auth_fk_role_resource` VALUES (825483049079668736, 825483048630878208, '2021-03-27 13:34:56', '2021-03-27 13:34:56', 'anon');

-- ----------------------------
-- Table structure for auth_fk_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_fk_user_role`;
CREATE TABLE `auth_fk_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `fk_user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user_account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_user_account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1',
  `fk_role_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 825481614766112769 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_fk_user_role
-- ----------------------------
INSERT INTO `auth_fk_user_role` VALUES (825465510249365504, 32, '2021-03-27 12:24:56', '2021-03-27 12:24:56', 'admin', 'admin', 'admin');
INSERT INTO `auth_fk_user_role` VALUES (825465824738279424, 825465818962722816, '2021-03-27 12:26:28', '2021-03-27 12:26:28', 'admin', 'admin', 'admin');
INSERT INTO `auth_fk_user_role` VALUES (825467669607088128, 825467669250572288, '2021-03-27 12:33:49', '2021-03-27 12:33:49', 'admin', 'admin', 'admin');
INSERT INTO `auth_fk_user_role` VALUES (825468325600428032, 825468325239717888, '2021-03-27 12:36:25', '2021-03-27 12:36:25', 'admin', 'admin', 'admin');
INSERT INTO `auth_fk_user_role` VALUES (825468543146393600, 825468542789877760, '2021-03-27 12:37:17', '2021-03-27 12:37:17', 'admin', 'admin', 'admin');
INSERT INTO `auth_fk_user_role` VALUES (825469474055389184, 825469473019396096, '2021-03-27 12:40:59', '2021-03-27 12:40:59', 'admin', 'admin', 'admin');
INSERT INTO `auth_fk_user_role` VALUES (825469474428682240, 825469473019396096, '2021-03-27 12:40:59', '2021-03-27 12:40:59', 'admin', 'admin', 'anon');
INSERT INTO `auth_fk_user_role` VALUES (825470560950550528, 825470560199770112, '2021-03-27 12:45:18', '2021-03-27 12:45:18', 'admin', 'admin', 'admin');
INSERT INTO `auth_fk_user_role` VALUES (825471379364118528, 825471378571395072, '2021-03-27 12:48:14', '2021-03-27 12:48:14', 'admin', 'admin', 'admin');
INSERT INTO `auth_fk_user_role` VALUES (825471914242736128, 825471913798139904, '2021-03-27 12:50:37', '2021-03-27 12:50:37', 'admin', 'admin', 'admin');
INSERT INTO `auth_fk_user_role` VALUES (825471914595057664, 825471913798139904, '2021-03-27 12:50:37', '2021-03-27 12:50:37', 'admin', 'admin', 'anon');
INSERT INTO `auth_fk_user_role` VALUES (825481614766112768, 825472764809838592, '2021-03-27 13:29:13', '2021-03-27 13:29:13', NULL, 'admin', 'anon');

-- ----------------------------
-- Table structure for auth_tb_datascope
-- ----------------------------
DROP TABLE IF EXISTS `auth_tb_datascope`;
CREATE TABLE `auth_tb_datascope`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `app_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `database_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `table_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rule_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rule_priority` int(11) NULL DEFAULT NULL,
  `rule_value` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rule_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rule_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for auth_tb_dept
-- ----------------------------
DROP TABLE IF EXISTS `auth_tb_dept`;
CREATE TABLE `auth_tb_dept`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `dept_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '部门名称',
  `dept_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fk_parent_dept_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `full_dept_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fk_leader_user_id` int(11) NULL DEFAULT NULL,
  `order_index` int(11) NULL DEFAULT 0 COMMENT '显示顺序',
  `create_user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dept_code`(`dept_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 823661187391553537 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_tb_dept
-- ----------------------------
INSERT INTO `auth_tb_dept` VALUES (1, '组织架构', '0', '0', '组织架构', NULL, 0, '0', '2021-03-22 20:49:41', '0', '2021-03-22 20:49:47', NULL);
INSERT INTO `auth_tb_dept` VALUES (823660377471451136, '市场部', '02', '0', '组织架构\\市场部', NULL, 0, '', '2021-03-22 12:52:17', '', '2021-03-22 12:52:17', NULL);
INSERT INTO `auth_tb_dept` VALUES (823661187391553536, '研发部', '01', '0', '组织架构\\研发部', NULL, 0, '', '2021-03-22 12:55:30', '', '2021-03-22 12:55:30', NULL);

-- ----------------------------
-- Table structure for auth_tb_post
-- ----------------------------
DROP TABLE IF EXISTS `auth_tb_post`;
CREATE TABLE `auth_tb_post`  (
  `id` bigint(20) NOT NULL COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '岗位名称',
  `post_sort` int(11) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '状态（0正常 1停用）',
  `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_tb_post
-- ----------------------------
INSERT INTO `auth_tb_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2018-03-16 11:33:00', NULL, '2018-03-16 11:33:00', '');
INSERT INTO `auth_tb_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2018-03-16 11:33:00', NULL, '2018-03-16 11:33:00', '');
INSERT INTO `auth_tb_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2018-03-16 11:33:00', NULL, '2018-03-16 11:33:00', '');
INSERT INTO `auth_tb_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2018-03-16 11:33:00', NULL, '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for auth_tb_resource
-- ----------------------------
DROP TABLE IF EXISTS `auth_tb_resource`;
CREATE TABLE `auth_tb_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `resource_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `resource_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源描述',
  `resource_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源地址',
  `icon_default` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `fk_parent_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '上级',
  `resource_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'menu,elm,page',
  `disabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '禁用',
  `resource_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用于页面元素',
  `order_index` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `create_user_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `app_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_attr` json NULL COMMENT 'menu{isTop:1}',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `resource_url`(`resource_url`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 825483048630878209 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_tb_resource
-- ----------------------------
INSERT INTO `auth_tb_resource` VALUES (2, '权限中心', NULL, NULL, '<span></span>', 0, 'menu', 0, NULL, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (3, '用户管理', NULL, '/pages/AuthApp/AuthUser/AuthUserList', '<span></span>', 2, 'menu', 0, NULL, 0, NULL, '2021-02-24 20:51:24', '2021-03-17 12:12:42', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (4, '角色管理', '0', '/pages/AuthApp/AuthRole/AuthRoleList', '<span></span>', 2, 'menu', 0, NULL, 0, NULL, '2021-02-24 20:51:27', '2021-03-17 12:12:54', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (5, '菜单管理', NULL, '/pages/AuthApp/AuthResource/AuthMenuList', '<span></span>', 2, 'menu', 0, NULL, 0, NULL, '2021-02-24 20:51:30', '2021-03-17 12:08:16', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (6, '接口管理', NULL, '/pages/AuthApp/AuthResource/AuthApiList', '<span></span>', 2, 'menu', 0, NULL, 0, NULL, '2021-02-24 20:51:32', '2021-03-17 12:13:23', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (8, '数据加工系统', '1', '', '<span></span>', 7, 'menu', 1, NULL, 0, NULL, '2021-02-24 20:51:36', '2021-03-22 12:12:52', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (9, '产品配置', NULL, '/pages/dpsApp/engineFlow/list', '<span></span>', 8, 'menu', 0, NULL, 0, NULL, '2021-02-24 20:51:38', '2021-03-17 12:11:50', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (10, '服务配置', NULL, '/pages/dpsApp/engineFlowPort/engineFlowPortList', '<span></span>', 8, 'menu', 0, NULL, 0, NULL, '2021-02-24 20:51:41', '2021-03-17 12:12:14', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (11, '物料配置', NULL, '/pages/dpsApp/engineTbMateriel/engineTbMaterielList', '&#xe741;', 8, 'menu', 0, NULL, 0, NULL, '2021-02-24 20:51:46', '2021-02-24 21:41:30', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (12, 'CMS建站系统', NULL, NULL, '<span></span>', 0, 'menu', 0, NULL, 3, NULL, '2021-02-24 21:40:40', '2021-03-17 12:01:44', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (13, '数据源管理', NULL, '/pages/cmsApp/cmsDatasource/cmsDatasourceList', '<span></span>', 12, 'menu', 0, NULL, 0, NULL, '2021-02-24 21:40:43', '2021-03-20 05:19:15', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (15, '文章管理', NULL, '/pages/cmsApp/cmsContent/cmsContentList', '<span></span>', 12, 'menu', 0, NULL, 0, NULL, '2021-02-24 21:40:45', '2021-03-17 12:06:11', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (16, '系统配置', NULL, NULL, '<span></span>', 0, 'menu', 0, NULL, 3, NULL, '2021-02-24 21:40:48', '2021-03-17 12:02:06', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (17, '字典组配置', NULL, '/pages/SysApp/SysTbDictGroup/SysTbDictGroupList', '<span></span>', 16, 'menu', 0, NULL, 0, NULL, '2021-02-24 21:40:50', '2021-03-17 12:06:46', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (18, '字典配置', NULL, '/pages/SysApp/SysTbDict/SysTbDictList', '<span></span>', 16, 'menu', 0, NULL, 0, NULL, '2021-02-24 21:40:53', '2021-03-17 12:07:01', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (19, '文件管理', NULL, '/pages/cmsApp/CmsLocalResource/CmsLocalResourceEditor', '<span></span>', 12, 'menu', 0, NULL, 0, NULL, '2021-02-24 21:40:56', '2021-03-17 12:06:24', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (20, '请求管理', NULL, '/pages/cmsApp/CmsTbRequestMapping/CmsTbRequestMappingList', '<span></span>', 12, 'menu', 0, NULL, 0, NULL, '2021-02-24 21:40:58', '2021-03-17 12:06:35', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (22, '数据权限', NULL, '/pages/AuthApp/DataScope/AuthDataScopeList', '<span></span>', 2, 'menu', 0, NULL, 0, NULL, '2021-03-20 12:29:28', '2021-03-17 12:08:38', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (23, '系统监视', NULL, '/pages/SysApp/SysMonitor/SysMonitorList', '<span></span>', 16, 'menu', 0, NULL, 0, NULL, '2021-03-20 12:29:26', '2021-03-18 13:34:55', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (24, '在线用户', NULL, '/pages/AuthApp/OnlineUser/AuthOnlieUserList', '<span></span>', 2, 'menu', 0, NULL, 0, NULL, '2021-03-20 12:29:21', '2021-03-20 12:29:30', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (25, '部门管理', '2222222222222', '/pages/AuthApp/AuthDept/AuthDeptList', '<span></span>', 2, 'menu', 0, NULL, 1, NULL, NULL, '2021-03-20 07:11:21', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (825100619474796544, '阿斯顿发', '阿斯顿发', '阿斯顿发', '<span></span>', 825100429464436736, 'menu', 0, NULL, 0, NULL, '2021-03-26 12:15:17', '2021-03-26 12:15:17', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (825369038333935616, '根目录', '111', '/', NULL, 0, 'api', 0, NULL, 0, NULL, '2021-03-27 06:01:53', '2021-03-27 11:35:49', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (825376991187304448, 'favicon.ico', '1111', '/favicon.ico', NULL, 0, 'api', 0, NULL, 0, NULL, '2021-03-27 06:33:29', '2021-03-27 11:35:54', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (825387569872109568, 'CMS异步查询接口', '11', '/select/**', NULL, 0, 'api', 0, NULL, 0, NULL, '2021-03-27 07:15:32', '2021-03-27 11:25:33', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (825387741163290624, 'site-favicon.ico', '11', '/site-favicon.ico', NULL, 0, 'api', 0, NULL, 0, NULL, '2021-03-27 07:16:12', '2021-03-27 11:18:48', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (825482815251415040, '/css/**', '/css/**', '/css/**', NULL, 0, 'api', 0, NULL, 0, NULL, '2021-03-27 13:34:00', '2021-03-27 13:34:00', NULL, NULL);
INSERT INTO `auth_tb_resource` VALUES (825483048630878208, '/js/**', '/js/**', '/js/**', NULL, 0, 'api', 0, NULL, 0, NULL, '2021-03-27 13:34:56', '2021-03-27 13:34:56', NULL, NULL);

-- ----------------------------
-- Table structure for auth_tb_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_tb_role`;
CREATE TABLE `auth_tb_role`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `role_name` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_code` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '角色编码',
  `is_default` tinyint(1) NOT NULL COMMENT '是否默认',
  `priority_level` int(11) NULL DEFAULT NULL COMMENT '优先级',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `disabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_tb_role
-- ----------------------------
INSERT INTO `auth_tb_role` VALUES (1, '超级管理员', 'admin', 0, 1, '2018-10-17 17:48:46', '2021-03-27 07:46:32', 0);
INSERT INTO `auth_tb_role` VALUES (824039851459346432, '匿名', 'anon', 1, 0, '2021-03-23 14:00:11', '2021-03-27 07:46:39', 0);

-- ----------------------------
-- Table structure for auth_tb_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_tb_user`;
CREATE TABLE `auth_tb_user`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `fk_dept_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '身份证号',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别',
  `account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `login_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `operate_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '操作密码',
  `salt` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `fk_post_id` bigint(20) NULL DEFAULT NULL,
  `is_lock` tinyint(1) NULL DEFAULT NULL COMMENT '锁定',
  `account_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号类型',
  `head_img_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '头像',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除1是',
  `create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account`(`account`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auth_tb_user
-- ----------------------------
INSERT INTO `auth_tb_user` VALUES (32, NULL, NULL, '管理员', '12345678913', NULL, '123@qq.com', 0, 'admin', 'c25c171bf2d51ab2ac443403b93de45f', NULL, '8pop1w', '2019-07-13 22:22:50', '2019-04-14 13:03:18', NULL, 0, 'user', NULL, 0, NULL);
INSERT INTO `auth_tb_user` VALUES (825471913798139904, NULL, 'admin1', 'admin1', 'admin1', 'admin1', 'admin1', NULL, 'admin1', 'e01daa41abba59949eb8f017cd280b00', '0', 'n1wnhg', '2021-03-27 12:50:37', '2021-03-27 12:50:37', NULL, 0, 'default', '0', 0, NULL);
INSERT INTO `auth_tb_user` VALUES (825472764809838592, '01', 'asdfa', 'asdfa', '12345678911', '632323190605269302', 'asdfa', NULL, 'asdfa', 'c0e22845e003b51c9e17533ebd2ce369', '0', 'hk63v7', '2021-03-27 12:53:59', '2021-03-27 13:29:13', NULL, 0, 'default', '0', 0, NULL);

-- ----------------------------
-- Table structure for auth_tb_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `auth_tb_user_login_log`;
CREATE TABLE `auth_tb_user_login_log`  (
  `id` bigint(20) NOT NULL COMMENT '编号',
  `login_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `login_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  `login_msg` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录消息',
  `fk_user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户编号',
  `login_browser` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_os` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_source` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录来源',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '登陆时间',
  `update_time` datetime(0) NULL DEFAULT NULL,
  `login_out_time` datetime(0) NULL DEFAULT NULL,
  `login_duration` bigint(20) NULL DEFAULT NULL COMMENT '分钟',
  `session_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录时sesstion',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_user_id`(`fk_user_id`) USING BTREE,
  INDEX `login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户登录记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_tb_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_tb_category`;
CREATE TABLE `cms_tb_category`  (
  `id` bigint(20) NOT NULL,
  `category_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fk_parent_category_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `category_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `category_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `disabled` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_tb_category
-- ----------------------------
INSERT INTO `cms_tb_category` VALUES (0, 'root', 'root', 'genuine', '文章类别', '2021-01-17 15:13:47', '2021-01-17 15:13:49', NULL);
INSERT INTO `cms_tb_category` VALUES (1, 'blacklist', 'root', 'asdfas', '产品介绍', '2021-01-17 15:23:08', '2021-01-17 15:23:10', NULL);
INSERT INTO `cms_tb_category` VALUES (2, 'house', 'root', '四大法师', '企业文化', '2021-01-17 15:31:27', '2021-01-17 15:31:32', NULL);
INSERT INTO `cms_tb_category` VALUES (3, 'stu_status', 'root', NULL, '企业动态', '2021-01-17 15:54:53', '2021-01-17 15:54:54', NULL);
INSERT INTO `cms_tb_category` VALUES (4, 'flour_elm', 'root', NULL, '沟通交流', '2021-01-17 15:54:57', '2021-01-17 15:54:59', NULL);
INSERT INTO `cms_tb_category` VALUES (5, 'test', 'root', NULL, '地址', '2021-01-30 17:52:16', '2021-01-30 17:52:14', NULL);
INSERT INTO `cms_tb_category` VALUES (6, 'test2', 'test', NULL, '测试', '2021-01-30 17:52:09', '2021-01-30 17:52:10', NULL);

-- ----------------------------
-- Table structure for cms_tb_content
-- ----------------------------
DROP TABLE IF EXISTS `cms_tb_content`;
CREATE TABLE `cms_tb_content`  (
  `id` bigint(20) UNSIGNED NOT NULL,
  `fk_category_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属类别',
  `content_details` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `content_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `content_keyword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字',
  `content_description` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `content_imgs` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章缩略图',
  `content_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章来源',
  `content_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章作者',
  `content_display` int(11) NULL DEFAULT NULL COMMENT '是否显示',
  `content_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章类型',
  `content_datetime` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `content_sort` int(11) NULL DEFAULT NULL COMMENT '自定义顺序',
  `content_hit` int(11) NULL DEFAULT NULL COMMENT '点击次数',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `deleted` int(11) NULL DEFAULT 0 COMMENT '删除标记',
  `recommend` int(11) NULL DEFAULT NULL COMMENT '推荐指数',
  `top` tinyint(1) NULL DEFAULT NULL COMMENT '置顶',
  `support_count` int(11) NULL DEFAULT NULL COMMENT '点赞数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_category_id`(`fk_category_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_tb_content
-- ----------------------------
INSERT INTO `cms_tb_content` VALUES (805503128450367488, 'blacklist', '<p>111111士大夫</p>', '撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水撒发大水', '撒的发生', '阿斯顿发达阿斯顿发达阿斯顿发达阿斯顿发达阿斯顿发达阿斯顿发达阿斯顿发达阿斯顿发达阿斯顿发达阿斯顿发达阿斯顿发达阿斯顿发达阿斯顿发达阿斯顿发达阿斯顿发达', '/images/1487741311729677.jpg', '阿斯顿发', '士大夫撒', 0, NULL, '2021-02-15 20:17:30', NULL, 1, '2021-03-01 12:05:30', '2021-01-31 10:21:51', 0, 1, 0, 1);
INSERT INTO `cms_tb_content` VALUES (805503476938309632, 'house', '<p>我是内容</p>', '文章标题', '阿斯顿发大水', '阿斯顿发生第三方', '/images/16144198567471497926857103747.jpg', '阿斯顿发', '的撒发的', 0, NULL, '2021-02-15 20:17:32', NULL, 0, '2021-03-01 12:02:01', '2021-01-31 10:23:14', 0, 2, 0, 2);
INSERT INTO `cms_tb_content` VALUES (810976330555326464, 'house ', '<p>时尚魅力短裤穿出个性风采，把妹子的媚尽情绽放，碎花里彰显大长美腿，摩登时尚感超强美女芙蓉如面真动人，婀娜好身姿充满魅力青春，引起潮流趋势的美范穿出性感妩媚！</p><p><img src=\"http://www.nvmoo.com/uploads/allimg/201708/1502195997786224.jpg\" alt=\"夏季短裤让你秒变时尚又清凉\"></p><p>性感好身材短裤神秘又个性，永不过时。气质温婉，时髦又性感充满文静温婉的淑女气质，窈窕可人美女爆款好身材真有料，一双修长的美腿婀娜，爱美的你值得拥有<a href=\"http://www.nvmoo.com/street/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(254, 115, 2);\">街拍</a>范穿出你的性感大长美腿，柔弱知性美！</p><p><img src=\"http://www.nvmoo.com/uploads/allimg/201708/1502196003750859.jpg\"></p><p>这样的日子难耐，清爽短裤穿出气质，最做漂亮的姑娘，带点欧式柔情，完美展现性感凸凹，清秀美女娇柔惹人爱怜，超直美腿高挑而充满活力，几种最潮的<a href=\"http://www.nvmoo.com/fashion/dapei/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(254, 115, 2);\">搭配</a>穿出女神的性感！</p><p><img src=\"http://www.nvmoo.com/uploads/allimg/201708/1502196008483211.jpg\"></p><p>这样穿搭短裤做工很精美，上身都典雅，美女妹子都可以轻松驾驭尽显曼妙好身材，这个姿势美女优雅迷人，好漂亮，各种极品美腿高挑身姿尽显魅力，很多女生选择的美范吸引眼球！</p><p><img src=\"http://www.nvmoo.com/uploads/allimg/201708/1502196012181211.jpg\"></p><p>小家碧玉最美短裤臀大的妹子穿出不一样的风情魅力，时尚露背尽显清新优雅。神仙玉骨美丽美女明艳动人性感妩媚，女性优美身姿展现女人青春性感气息，女生这么穿风格穿出女神的知性美！撩人心扉！</p><p><img src=\"http://www.nvmoo.com/uploads/allimg/201708/1502196017547506.jpg\"></p><p>充满女性时尚魅力的短裤穿出满满大长腿的美感，妹子就是这么好看，穿出大长腿的既视感，休闲街头风格充满优雅美女气质，吸引男人灼热眼光，一个妆容让人如痴如醉时尚美女气质优雅，仙姿绰约，女性曼妙的身姿曼妙妩媚。潮流时尚的美范的性感小美人。</p><p><img src=\"http://www.nvmoo.com/uploads/allimg/201708/1502196022667625.jpg\" alt=\"夏季短裤让你秒变时尚又清凉\"></p><p>女性一生中最为美好的一段时光，性感短裤穿出淡然恬静出众气质，柔情时尚不呆板尽显小女人的迷人魅力，温婉动人美女身材惹火，尤其是那修长美腿引人侧目，面对妹子的美，不要只顾羡慕，说不定也很适合你的女神范穿上娇俏动人！</p>', '夏季短裤让你秒变时尚又清凉', '夏季短裤让你秒变时尚又清凉', '夏季短裤让你秒变时尚又清凉', '/images/1493900375339205.jpg', NULL, '小颜', 1, NULL, '2021-02-15 20:52:08', NULL, 0, '2021-02-27 10:03:46', '2021-02-15 12:50:24', 0, 3, 1, 3);
INSERT INTO `cms_tb_content` VALUES (810978610813861888, 'house ', '<h1>我是标题我啊打</h1><h2><strong class=\"ql-font-serif\">性感印花V领开叉紧身连衣裙</strong></h2><p><img src=\"http://www.nvmoo.com/uploads/allimg/201706/1498025824829893.jpg\" alt=\"能把自己迷住的连衣裙搭配\"></p><p>紧身的<a href=\"http://www.nvmoo.com/fashion/dress/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(254, 115, 2);\">裙子</a>穿在身上是十分的修身显瘦的，还展现凹凸有致的好身材，让男人移不开视线。经典的V领设计，是深V领口的设计，展现你的性感事业线的同时也是十分的时尚的</p><p><img src=\"http://www.nvmoo.com/uploads/allimg/201706/1498025829379505.jpg\"></p><p>白色的裙身上还有着清新颜色的蝴蝶印花的设计，更是显得十分的好看。收腰与<a href=\"http://www.nvmoo.com/fashion/baotun/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(254, 115, 2);\">包臀裙</a>的设计更是显得身材超棒。搭配一双高跟鞋更是显得身材高挑迷人哦。</p><p><strong>无袖撞色名媛紧身包臀短裙</strong></p><p><img src=\"http://www.nvmoo.com/uploads/allimg/201706/1498025834355475.jpg\" alt=\"能把自己迷住的连衣裙搭配\"></p><p><a href=\"http://www.nvmoo.com/fashion/dress/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(254, 115, 2);\">裙子</a>采用拼接的设计，将黑与白的撞色展现的非常的有时尚感，脖子是圆领的设计，但是只有一圈，袖子根本没有，显得十分的有创意。</p><p><img src=\"http://www.nvmoo.com/uploads/allimg/201706/1498025838694576.jpg\"></p><p>更是在领口上还有两片布料的结合，从领口上出发，分别蔓延到腰部，更显个性。然后胸部下方是黑色的裙身，修身的版型更显瘦，凸显凹凸有致的好身材。穿上高跟鞋更显身材的高挑。</p><p><strong>镂空蕾丝短袖连衣裙</strong></p><p><img src=\"http://www.nvmoo.com/uploads/allimg/201706/1498025843623621.jpg\" alt=\"能把自己迷住的连衣裙搭配\"></p><p>裙子是采用拼接的工艺将蕾丝与布料相连接，显得非常的有个性也有时尚感。领口是采用蕾丝的布料，镂空的设计更像是刺绣一样，看上去非常的有时尚感</p><p><img src=\"http://www.nvmoo.com/uploads/allimg/201706/1498025848612959.jpg\"></p><p>微透明的设计更是显得神秘而性感。胸前斜着的拼接设计更是显得十分的有个性，家伙是哪个修身的版型，更显性感迷人。</p><p><strong>性感短裙海军风裙子</strong></p><p><img src=\"http://www.nvmoo.com/uploads/allimg/201706/1498025852260722.jpg\" alt=\"能把自己迷住的连衣裙搭配\"></p><p>这条<a href=\"http://www.nvmoo.com/fashion/dress/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(254, 115, 2);\">裙子</a>的版型用料设计看上去更像是学生装，但是版型比学生装更加的性感。因为它有着深V的领口设计，展露你性感的事业线还有着精美的锁骨，短短的袖子更是显得手臂纤细修长，非常的性感哦</p><p><img src=\"http://www.nvmoo.com/uploads/allimg/201706/1498025855801026.jpg\"></p><p>这款<a href=\"http://www.nvmoo.com/fashion/duanqun/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(254, 115, 2);\">短裙</a>紧身的腰身更是显得十分的修身显瘦，搭配短短的裙摆，更显大长腿。在领口与裙摆上还有着撞色的设计，更是显得十分的有时尚感哦。</p>', '能把自己迷住的连衣裙，才是女人的真爱！', '能把自己迷住的连衣裙,才是女人的真爱！', NULL, '/images/1493345362729438.jpg', '', '小颜', 0, NULL, '2021-02-15 21:00:26', NULL, 0, '2021-02-27 10:48:20', '2021-02-15 12:59:28', 0, 4, 1, 4);
INSERT INTO `cms_tb_content` VALUES (811654580398981120, 'house', '<h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><h1>maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh</h1><p><br></p><p><br></p><p><br></p>', 'maven插件a1', 'maven插件assembly使用及springboot启动脚本start.sh和停止脚本 stop.sh', '大方法发生11111111111111111111111111111111', '/images/1497924744427730.jpg', '安防十大', '小颜', 1, NULL, NULL, NULL, 0, '2021-03-01 11:09:38', '2021-02-17 09:45:32', 0, NULL, 1, NULL);
INSERT INTO `cms_tb_content` VALUES (816027263140102144, 'house ', '<p>1111<img src=\"http://127.0.0.1:8080//images/1614610899959038CD975973C1D0C74EA0218D7C515AD.png\"></p>', '11111111111111', '111111111111111111111111111111111111111111', NULL, '/images/IMG_0631(20201017-145940).JPG', NULL, NULL, 1, NULL, NULL, NULL, 0, '2021-03-01 15:03:45', '2021-03-01 11:21:01', 0, NULL, NULL, NULL);
INSERT INTO `cms_tb_content` VALUES (816027705446236160, 'house ', '<p>111</p>', NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 0, '2021-03-01 11:22:46', '2021-03-01 11:22:46', 0, NULL, NULL, NULL);
INSERT INTO `cms_tb_content` VALUES (816028434097504256, 'house ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, 0, '2021-03-01 11:25:43', '2021-03-01 11:25:40', 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for cms_tb_datasource
-- ----------------------------
DROP TABLE IF EXISTS `cms_tb_datasource`;
CREATE TABLE `cms_tb_datasource`  (
  `id` bigint(20) NOT NULL,
  `datasource_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `datasource_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `datasource_source` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fk_datasource_type_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `datasource_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `datasource_params` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_tb_datasource
-- ----------------------------
INSERT INTO `cms_tb_datasource` VALUES (810526070464839680, '站点名称', 'siteName', 'String', 'cms_basics', '我是网站名称', NULL, '2021-02-14 07:01:14', '2021-03-17 14:31:16');
INSERT INTO `cms_tb_datasource` VALUES (810629964352192512, '友情链接', 'friendChain', 'JsonArray', 'cms_basics', '[\n  {\n    \"name\": \"百度\",\n    \"url\": \"http://www.baidu.com\"\n  },  {\n    \"name\": \"谷歌\",\n    \"url\": \"http://www.baidu.com\"\n  }\n]\n\n\n', NULL, '2021-02-14 13:54:04', '2021-02-15 15:42:53');
INSERT INTO `cms_tb_datasource` VALUES (810631669068333056, '阅读量更新', 'readCountUpdate', 'GroovyScript', 'article', 'import org.springframework.beans.factory.annotation.Autowired\nimport site.lanmushan.cms.api.entity.CmsTbContent\nimport site.lanmushan.cms.mapper.CmsTbContentMapper\n\nclass DataSource001 {\n    @Autowired\n    CmsTbContentMapper cmsTbContentMapper;\n\n    def Object doHandle(String dataSourceName, String content, Map<String, Object> params) {\n        List<CmsTbContent> list = cmsTbContentMapper.selectAll();\n        System.out.println(\"执行数据源脚本\"+list.size());\n        return list;\n    }\n}\n', '{\n	\"id\":\"\"\n}', '2021-02-14 14:00:51', '2021-02-14 14:33:01');
INSERT INTO `cms_tb_datasource` VALUES (810914739533119488, '查询数据最新文章', 'newArticle', 'Sql', 'article', 'SELECT l.*,r.category_name from cms_tb_content l LEFT JOIN cms_tb_category  r ON l.fk_category_code=r.category_code where l.content_display=1 ORDER BY create_time DESC LIMIT 0,5', '{\n  \"categoryCode\": \"\"\n  \"count\":\"3\"\n}\n\n\n', '2021-02-15 08:45:40', '2021-02-17 09:29:40');
INSERT INTO `cms_tb_datasource` VALUES (810920164823400448, '站点栏目', 'navList', 'Inner', 'article', NULL, NULL, '2021-02-15 09:07:13', '2021-02-15 09:39:30');
INSERT INTO `cms_tb_datasource` VALUES (810928483399106560, 'LOGO', 'logo', 'String', 'cms_basics', '/images/logo.png', NULL, '2021-02-15 09:40:17', '2021-02-15 09:53:50');
INSERT INTO `cms_tb_datasource` VALUES (810934585062850560, 'favicon', 'favicon', 'String', 'cms_basics', '/site-favicon.ico', NULL, '2021-02-15 10:04:32', '2021-02-15 10:45:35');
INSERT INTO `cms_tb_datasource` VALUES (810935923393953792, '首页大图展示', 'banner', 'Sql', 'article', 'SELECT * from cms_tb_content WHERE content_display=1 and top=1 ORDER BY create_time DESC LIMIT 0,3', NULL, '2021-02-15 10:09:51', '2021-02-17 09:32:45');
INSERT INTO `cms_tb_datasource` VALUES (810952966193283072, '推荐文章', 'recommend', 'Sql', 'article', 'SELECT l.*,r.category_name from cms_tb_content l LEFT JOIN cms_tb_category  r ON l.fk_category_code=r.category_code WHERE l.content_display=1 ORDER BY recommend DESC LIMIT 0,1', NULL, '2021-02-15 11:17:34', '2021-02-17 09:36:30');
INSERT INTO `cms_tb_datasource` VALUES (810961921288175616, '类别热门文章', 'hotList', 'GroovyScript', 'article', 'import org.springframework.beans.factory.annotation.Autowired\nimport site.lanmushan.cms.api.entity.CmsTbContent\nimport site.lanmushan.cms.api.entity.CmsTbCategory;\nimport site.lanmushan.cms.mapper.CmsTbContentMapper\nimport com.alibaba.fastjson.JSONObject;\nimport com.github.pagehelper.PageHelper;\nimport site.lanmushan.cms.api.service.CmsTbCategoryService;\nimport java.util.ArrayList;\nimport java.util.List;\nimport java.util.Map;\nimport site.lanmushan.framework.dto.QueryInfo;\nclass HotList {\n    @Autowired\n    CmsTbContentMapper cmsTbContentMapper;\n    @Autowired\n    CmsTbCategoryService categoryService;\n\n    def Object doHandle(String dataSourceName, String content, Map<String, Object> params) {\n        List<CmsTbCategory> list = categoryService.selectTreeList(new QueryInfo());\n            List resultList = list.get(0).getChildren();\n            List<JSONObject> resultJsonList=new ArrayList<>();\n            for (int i = 0; i < resultList.size(); i++) {\n                CmsTbCategory cmsTbCategory = (CmsTbCategory) resultList.get(i);\n                JSONObject json = (JSONObject) JSONObject.toJSON(cmsTbCategory);\n                cmsTbCategory.getCategoryCode();\n                CmsTbContent query = new CmsTbContent();\n                query.setFkCategoryCode(cmsTbCategory.getCategoryCode());\n                query.setContentDisplay(1);\n                PageHelper.orderBy(\"content_hit desc\");\n                PageHelper.startPage(1, 2);\n                List<CmsTbContent> contentList = cmsTbContentMapper.select(query);\n                json.put(\"articleList\", contentList);\n                resultJsonList.add(json);\n            }\n            return resultJsonList;\n    }\n}\n', NULL, '2021-02-15 11:53:09', '2021-03-27 07:21:45');
INSERT INTO `cms_tb_datasource` VALUES (810989359414116352, '置顶文章', 'topList', 'Sql', 'article', 'SELECT * from cms_tb_content where content_display=1 and top=1 ORDER BY recommend DESC LIMIT 0,2', NULL, '2021-02-15 13:42:11', '2021-02-17 09:34:22');
INSERT INTO `cms_tb_datasource` VALUES (811000771691675648, '推荐列表', 'recommendList', 'Sql', 'article', 'SELECT * from cms_tb_content WHERE content_display=1 ORDER BY recommend DESC LIMIT 1,10', NULL, '2021-02-15 14:27:32', '2021-02-17 09:37:00');
INSERT INTO `cms_tb_datasource` VALUES (811012660760936448, '点赞排序', 'supportList', 'Sql', 'article', 'SELECT * from cms_tb_content WHERE content_display=1 ORDER BY support_count DESC LIMIT 0,5', NULL, '2021-02-15 15:14:46', '2021-02-17 09:39:46');
INSERT INTO `cms_tb_datasource` VALUES (811230861004374016, '最近更新', 'updateArticleList', 'Sql', 'article', 'SELECT l.*,r.category_name from cms_tb_content l LEFT JOIN cms_tb_category  r ON l.fk_category_code=r.category_code WHERE l.content_display=1 ORDER BY update_time DESC LIMIT 0,5', NULL, '2021-02-16 05:41:49', '2021-02-17 09:41:36');
INSERT INTO `cms_tb_datasource` VALUES (811236984696602624, '根据id查询文章', 'idArticle', 'Sql', 'article', 'SELECT l.*,r.category_code,r.category_name FROM cms_tb_content as l LEFT JOIN cms_tb_category as r  ON l.fk_category_code=r.category_code  WHERE l.id=${id}', '{\n  \"id\": \"\"\n}\n\n\n', '2021-02-16 06:06:09', '2021-02-16 08:32:46');
INSERT INTO `cms_tb_datasource` VALUES (811276061819011072, '上一篇', 'lastArticle', 'Sql', 'article', 'SELECT * FROM cms_tb_content as c WHERE c.create_time<(SELECT create_time from cms_tb_content WHERE cms_tb_content.id=${id}) ORDER BY create_time DESC LIMIT 0,1 ', '{\n  \"id\": \"\"\n}\n\n\n', '2021-02-16 08:41:26', '2021-02-16 08:44:34');
INSERT INTO `cms_tb_datasource` VALUES (811276480779649024, '下一篇', 'nextArticle', 'Sql', 'article', 'SELECT * FROM cms_tb_content as c WHERE c.create_time>(SELECT create_time from cms_tb_content WHERE id=${id}) ORDER BY create_time ASC LIMIT 0,1 ', '{\n	\"id\":\"\"\n}', '2021-02-16 08:43:06', '2021-02-16 08:43:06');
INSERT INTO `cms_tb_datasource` VALUES (811284350925537280, '按类别查询最新文章', 'categoryNewArticle', 'Sql', 'article', 'SELECT * from cms_tb_content WHERE fk_category_code=\"${code}\" AND content_display=1 ORDER BY create_time desc ', '{\n  \"code\": \"\",\n  \"currentPage\": 1,\n  \"pageSize\": 10\n}\n\n\n', '2021-02-16 09:14:22', '2021-02-17 09:42:53');
INSERT INTO `cms_tb_datasource` VALUES (811312841301688320, '按类别查询最新文章总数', 'categoryNewArticleTotal', 'Sql', 'article', 'SELECT COUNT(*) as total from cms_tb_content WHERE fk_category_code=\"${code}\" AND content_display=1', '{\n  \"code\": \"\"\n}\n\n\n', '2021-02-16 11:07:35', '2021-02-17 09:43:00');
INSERT INTO `cms_tb_datasource` VALUES (811333458415583232, '主体关键字', 'siteKeywords', 'String', 'cms_basics', '主题关键字', NULL, '2021-02-16 12:29:30', '2021-02-16 12:31:20');
INSERT INTO `cms_tb_datasource` VALUES (811334208831094784, '主体描述', 'siteDescription', 'String', 'cms_basics', '站点主体描述', NULL, '2021-02-16 12:32:29', '2021-02-16 12:32:29');
INSERT INTO `cms_tb_datasource` VALUES (811348537148178432, '版权声明', 'siteCopyRight', 'String', 'cms_basics', '本站资源均网络搜集，如有关视频侵犯了你的权益，请联系邮箱：xxxx#qq.com(#改为)，本站将于24小时内删除!\n\n', NULL, '2021-02-16 13:29:25', '2021-02-16 13:42:34');
INSERT INTO `cms_tb_datasource` VALUES (811372182251765760, '查询文章类别', 'curCategory', 'Sql', 'article', 'SELECT *  from cms_tb_category WHERE category_code=\"${code}\"', '{\n  \"code\": \"\"\n}\n\n\n', '2021-02-16 15:03:23', '2021-02-16 15:04:06');
INSERT INTO `cms_tb_datasource` VALUES (823633062024708096, 'aaa', 'aaa', 'String', NULL, 'aaaaaaaaaaaaaaaaaa', NULL, '2021-03-22 11:03:44', '2021-03-22 11:03:44');
INSERT INTO `cms_tb_datasource` VALUES (823633138289737728, 'aaa', 'aaa', 'String', NULL, 'aaaaaa', NULL, '2021-03-22 11:04:03', '2021-03-22 11:04:03');

-- ----------------------------
-- Table structure for cms_tb_datasource_type
-- ----------------------------
DROP TABLE IF EXISTS `cms_tb_datasource_type`;
CREATE TABLE `cms_tb_datasource_type`  (
  `id` bigint(20) NOT NULL,
  `type_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fk_parent_type_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for cms_tb_request_mapping
-- ----------------------------
DROP TABLE IF EXISTS `cms_tb_request_mapping`;
CREATE TABLE `cms_tb_request_mapping`  (
  `id` bigint(20) NOT NULL,
  `request_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mapping_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '映射类型',
  `request_tpl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板',
  `encoder_script` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '加工脚本',
  `page_re_write` int(11) NULL DEFAULT NULL COMMENT '静态化',
  `target_page` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_tb_request_mapping
-- ----------------------------
INSERT INTO `cms_tb_request_mapping` VALUES (2, '/index.html', 'GET', 'view', 'index.ftl', '', 5000, NULL, '2021-02-14 19:27:04', '2021-02-19 13:14:37');
INSERT INTO `cms_tb_request_mapping` VALUES (811233527004659712, '/detail/{id}.html', 'GET', 'view', 'detail.ftl', NULL, 1, NULL, '2021-02-16 05:52:25', '2021-02-28 12:31:52');
INSERT INTO `cms_tb_request_mapping` VALUES (811281699819225088, '/category/{code}.html', 'GET', 'view', 'category.ftl', NULL, NULL, NULL, '2021-02-16 09:03:50', '2021-02-19 13:16:16');
INSERT INTO `cms_tb_request_mapping` VALUES (825369342798462976, '/', 'GET', 'forward', NULL, NULL, 500, 'index.html', '2021-03-27 06:03:06', '2021-03-27 07:22:55');

-- ----------------------------
-- Table structure for cms_tb_request_mapping_datasource
-- ----------------------------
DROP TABLE IF EXISTS `cms_tb_request_mapping_datasource`;
CREATE TABLE `cms_tb_request_mapping_datasource`  (
  `id` bigint(20) NOT NULL,
  `fk_request_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fk_datasource_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fk_datasource_params_mapping` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `allow_null` tinyint(4) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_tb_request_mapping_datasource
-- ----------------------------
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (810921211507769344, '/index.html', 'siteName', NULL, NULL, '2021-02-15 09:11:23', '2021-02-15 09:11:23');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (810921260220416000, '/index.html', 'navList', NULL, NULL, '2021-02-15 09:11:35', '2021-02-15 09:11:35');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (810928563871023104, '/index.html', 'logo', NULL, NULL, '2021-02-15 09:40:36', '2021-02-15 09:40:36');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (810934641736286208, '/index.html', 'favicon', NULL, NULL, '2021-02-15 10:04:45', '2021-02-15 10:04:45');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (810935993975701504, '/index.html', 'banner', NULL, NULL, '2021-02-15 10:10:07', '2021-02-15 10:10:07');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (810953064117698560, '/index.html', 'recommend', NULL, NULL, '2021-02-15 11:17:57', '2021-02-15 11:17:57');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (810955437963739136, '/index.html', 'newArticle', '\n\n', NULL, '2021-02-15 11:27:23', '2021-02-15 11:27:23');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (810962011105001472, '/index.html', 'hotList', NULL, NULL, '2021-02-15 11:53:30', '2021-02-15 11:53:30');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (810989592067964928, '/index.html', 'topList', NULL, NULL, '2021-02-15 13:43:06', '2021-02-15 13:43:06');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811005663252578304, '/index.html', 'recommendList', NULL, NULL, '2021-02-15 14:46:58', '2021-02-15 14:46:58');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811012747482365952, '/index.html', 'supportList', NULL, NULL, '2021-02-15 15:15:07', '2021-02-15 15:15:07');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811018988673826816, '/index.html', 'friendChain', NULL, NULL, '2021-02-15 15:39:55', '2021-02-15 15:39:55');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811234527501352960, '/detail/{id}.html', 'navList', NULL, NULL, '2021-02-16 05:56:23', '2021-02-16 05:56:23');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811236181760016384, '/detail/{id}.html', 'logo', NULL, NULL, '2021-02-16 06:02:58', '2021-02-16 06:02:58');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811237601007632384, '/detail/{id}.html', 'idArticle', '{\n	\"id\":\"${requestBody.id}\"\n}', NULL, '2021-02-16 06:08:36', '2021-02-16 06:08:36');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811242848929185792, '/detail/{id}.html', 'favicon', NULL, NULL, '2021-02-16 06:29:27', '2021-02-16 06:29:27');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811276632743477248, '/detail/{id}.html', 'lastArticle', '{\n  \"id\": \"${requestBody.id}\"\n}\n\n\n', NULL, '2021-02-16 08:43:42', '2021-02-16 08:45:20');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811277860424646656, '/detail/{id}.html', 'nextArticle', '{ \"id\": \"${requestBody.id}\" }\n', NULL, '2021-02-16 08:48:35', '2021-02-16 08:48:35');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811279828274642944, '/detail/{id}.html', 'supportList', NULL, NULL, '2021-02-16 08:56:24', '2021-02-16 08:56:24');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811280805455200256, '/detail/{id}.html', 'friendChain', NULL, NULL, '2021-02-16 09:00:17', '2021-02-16 09:00:17');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811282245087133696, '/category/{code}.html', 'friendChain', NULL, NULL, '2021-02-16 09:06:00', '2021-02-16 09:06:00');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811282278339575808, '/category/{code}.html', 'recommendList', NULL, NULL, '2021-02-16 09:06:08', '2021-02-16 09:06:08');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811282297008422912, '/category/{code}.html', 'recommend', NULL, NULL, '2021-02-16 09:06:13', '2021-02-16 09:06:13');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811282574247723008, '/category/{code}.html', 'navList', NULL, NULL, '2021-02-16 09:07:19', '2021-02-16 09:07:19');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811282726563872768, '/category/{code}.html', 'logo', NULL, NULL, '2021-02-16 09:07:55', '2021-02-16 09:07:55');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811282747589918720, '/category/{code}.html', 'favicon', NULL, NULL, '2021-02-16 09:08:00', '2021-02-16 09:08:00');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811283185747886080, '/category/{code}.html', 'hotList', NULL, NULL, '2021-02-16 09:09:44', '2021-02-16 09:09:44');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811284499445841920, '/category/{code}.html', 'categoryNewArticle', '{\n  \"code\": \"${requestBody.code}\",\n  \"currentPage\": ${requestBody.currentPage!=null?requestBody.currentPage:1},\n  \"pageSize\": ${requestBody.pageSize!=null?requestBody.pageSize:10}\n}\n\n\n', NULL, '2021-02-16 09:14:58', '2021-02-16 11:10:57');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811285315581902848, '/category/{code}.html', 'supportList', NULL, NULL, '2021-02-16 09:18:12', '2021-02-16 09:18:12');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811313160450473984, '/category/{code}.html', 'categoryNewArticleTotal', '{\n	\"code\":\"${requestBody.code}\"\n}', NULL, '2021-02-16 11:08:51', '2021-02-16 11:08:51');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811333546936369152, '/index.html', 'siteKeywords', NULL, NULL, '2021-02-16 12:29:51', '2021-02-16 12:29:51');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811334257107533824, '/index.html', 'siteDescription', NULL, NULL, '2021-02-16 12:32:41', '2021-02-16 12:32:41');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811348988056829952, '/index.html', 'siteCopyRight', '', NULL, '2021-02-16 13:31:13', '2021-02-16 13:42:24');
INSERT INTO `cms_tb_request_mapping_datasource` VALUES (811372447843483648, '/category/{code}.html', 'curCategory', '{\n  \"code\": \"${requestBody.code}\"\n}\n\n\n', NULL, '2021-02-16 15:04:26', '2021-02-16 15:04:26');

-- ----------------------------
-- Table structure for sys_tb_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_tb_dict`;
CREATE TABLE `sys_tb_dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dict_value` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '映射值',
  `dict_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '映射名称',
  `dict_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '映射编码',
  `fk_dict_group_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属分组',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `disabled` tinyint(1) NULL DEFAULT 0 COMMENT '禁用',
  `create_user_account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '创建人',
  `is_default` tinyint(1) NULL DEFAULT 0 COMMENT '默认',
  `order_index` int(11) NULL DEFAULT 0 COMMENT '排序',
  `allow_edit` tinyint(1) NULL DEFAULT 0 COMMENT '允许编辑',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_code`(`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 825364332995936257 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_tb_dict
-- ----------------------------
INSERT INTO `sys_tb_dict` VALUES (806647397865226240, 'cms_basics', 'CMS站点基础数据', 'cms_basics', 'cms_datasource_type', '2021-02-03 14:08:46', '2021-02-03 14:08:46', 0, 'admin', 1, 1, 0);
INSERT INTO `sys_tb_dict` VALUES (810630258662309888, 'article', 'CMS文章数据', 'article', 'cms_datasource_type', '2021-02-14 13:55:14', '2021-02-14 13:55:27', 0, 'admin', 1, 0, 0);
INSERT INTO `sys_tb_dict` VALUES (825360989137403904, '11', '11', '11', 'test_code', '2021-03-27 05:29:54', '2021-03-27 05:29:54', 0, 'admin', 1, 1, 0);
INSERT INTO `sys_tb_dict` VALUES (825364332995936256, '11', '111', '111', 'test_code', '2021-03-27 05:43:12', '2021-03-27 05:43:12', 0, 'admin', 1, 111, 0);

-- ----------------------------
-- Table structure for sys_tb_dict_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_tb_dict_group`;
CREATE TABLE `sys_tb_dict_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dict_group_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `dict_group_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `allow_edit` tinyint(1) NULL DEFAULT 0 COMMENT '允许编辑',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `create_user_account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `deleted` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 825360921223233537 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_tb_dict_group
-- ----------------------------
INSERT INTO `sys_tb_dict_group` VALUES (806646887082885120, 'CMS数据来源类别', 'cms_datasource_type', 1, '2021-02-03 14:06:45', '2021-02-03 14:06:45', 'admin', 0);
INSERT INTO `sys_tb_dict_group` VALUES (825360921223233536, '测试字典组', 'test_code', 1, '2021-03-27 05:29:38', '2021-03-27 13:24:07', 'admin', 0);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime(0) NOT NULL,
  `log_modified` datetime(0) NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
