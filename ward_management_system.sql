/*
 Navicat Premium Dump SQL

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : ward_management_system

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 23/09/2024 18:44:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'c3284d0f94606de1fd2af172aba15bf3');

-- ----------------------------
-- Table structure for bed
-- ----------------------------
DROP TABLE IF EXISTS `bed`;
CREATE TABLE `bed`  (
  `bid` int NOT NULL AUTO_INCREMENT,
  `bnumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `wnumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`bid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bed
-- ----------------------------
INSERT INTO `bed` VALUES (1, '101-1', NULL, NULL, '101');
INSERT INTO `bed` VALUES (5, '302-1', NULL, NULL, '302');
INSERT INTO `bed` VALUES (6, '302-2', '10021', '1001', '302');
INSERT INTO `bed` VALUES (7, '302-3', '10014', '1006', '302');
INSERT INTO `bed` VALUES (11, '204-1', NULL, NULL, '204');
INSERT INTO `bed` VALUES (12, '204-2', '10002', '1013', '204');
INSERT INTO `bed` VALUES (13, '204-3', '10018', '1013', '204');
INSERT INTO `bed` VALUES (14, '301-1', '10004', '1006', '301');
INSERT INTO `bed` VALUES (15, '301-2', '10020', '1009', '301');
INSERT INTO `bed` VALUES (16, '301-3', '10013', '1006', '301');
INSERT INTO `bed` VALUES (18, '103-2', NULL, NULL, '103');
INSERT INTO `bed` VALUES (19, '103-3', '10008', '1002', '103');
INSERT INTO `bed` VALUES (23, '101-2', '10009', '1001', '101');
INSERT INTO `bed` VALUES (27, '103-1', NULL, NULL, '103');
INSERT INTO `bed` VALUES (28, '101-3', '10024', '1013', '101');
INSERT INTO `bed` VALUES (29, '102-1', '10010', '1002', '102');
INSERT INTO `bed` VALUES (30, '102-2', NULL, NULL, '102');
INSERT INTO `bed` VALUES (31, '102-3', NULL, NULL, '102');
INSERT INTO `bed` VALUES (35, '405-1', '10005', '1010', '405');
INSERT INTO `bed` VALUES (36, '405-2', '10025', '1004', '405');
INSERT INTO `bed` VALUES (37, '405-3', '10015', '1010', '405');
INSERT INTO `bed` VALUES (38, '601-1', '10006', '1016', '601');
INSERT INTO `bed` VALUES (39, '601-2', '10019', '1016', '601');
INSERT INTO `bed` VALUES (40, '601-3', NULL, NULL, '601');
INSERT INTO `bed` VALUES (41, '801-1', '10007', '1020', '801');
INSERT INTO `bed` VALUES (42, '801-2', '10011', '1020', '801');
INSERT INTO `bed` VALUES (43, '801-3', '10017', '1020', '801');
INSERT INTO `bed` VALUES (44, '701-1', '10022', '1007', '701');
INSERT INTO `bed` VALUES (45, '701-2', NULL, NULL, '701');
INSERT INTO `bed` VALUES (46, '701-3', NULL, NULL, '701');
INSERT INTO `bed` VALUES (47, '202-1', '10001', '1013', '202');
INSERT INTO `bed` VALUES (48, '202-2', '10023', '1013', '202');
INSERT INTO `bed` VALUES (49, '202-3', '12313', '1013', '202');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `did` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '科室名称',
  `dlocation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '科室位置',
  PRIMARY KEY (`did`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '内科', '一楼');
INSERT INTO `department` VALUES (2, '外科', '二楼');
INSERT INTO `department` VALUES (3, '儿科', '三楼');
INSERT INTO `department` VALUES (4, '口腔科', '四楼');
INSERT INTO `department` VALUES (5, '妇科', '五楼');
INSERT INTO `department` VALUES (6, '眼科', '六楼');
INSERT INTO `department` VALUES (7, '皮肤科', '七楼');
INSERT INTO `department` VALUES (8, '骨科', '八楼');

-- ----------------------------
-- Table structure for duty
-- ----------------------------
DROP TABLE IF EXISTS `duty`;
CREATE TABLE `duty`  (
  `duty_id` bigint NOT NULL AUTO_INCREMENT,
  `duty_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `duty_work_time` time NULL DEFAULT NULL,
  `duty_closing_time` time NULL DEFAULT NULL,
  `duty_task_progress` int NULL DEFAULT NULL,
  `duty_details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`duty_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of duty
-- ----------------------------
INSERT INTO `duty` VALUES (1, '周一', '20:30:00', '22:00:00', 32, '123');
INSERT INTO `duty` VALUES (2, '周二', '18:00:00', '21:00:00', 45, 'qweqrqwr');
INSERT INTO `duty` VALUES (3, '周三', '18:00:00', '22:00:00', 93, 'qweqrqwr');
INSERT INTO `duty` VALUES (4, '周四', '18:00:00', '22:00:00', 34, 'qweqrqwr');
INSERT INTO `duty` VALUES (5, '周五', '18:00:00', '22:00:00', 23, 'safdafa');
INSERT INTO `duty` VALUES (6, '周六', '14:00:00', '21:00:00', 76, 'safdafa');
INSERT INTO `duty` VALUES (7, '周日', '19:00:00', '21:00:00', 23, 'safdafa');

-- ----------------------------
-- Table structure for duty_nurse
-- ----------------------------
DROP TABLE IF EXISTS `duty_nurse`;
CREATE TABLE `duty_nurse`  (
  `duty_id` int NULL DEFAULT NULL,
  `nid` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of duty_nurse
-- ----------------------------
INSERT INTO `duty_nurse` VALUES (4, 4);
INSERT INTO `duty_nurse` VALUES (4, 5);
INSERT INTO `duty_nurse` VALUES (4, 18);
INSERT INTO `duty_nurse` VALUES (4, 19);
INSERT INTO `duty_nurse` VALUES (4, 20);
INSERT INTO `duty_nurse` VALUES (4, 21);
INSERT INTO `duty_nurse` VALUES (4, 22);
INSERT INTO `duty_nurse` VALUES (5, 15);
INSERT INTO `duty_nurse` VALUES (5, 16);
INSERT INTO `duty_nurse` VALUES (5, 18);
INSERT INTO `duty_nurse` VALUES (5, 19);
INSERT INTO `duty_nurse` VALUES (5, 20);
INSERT INTO `duty_nurse` VALUES (6, 14);
INSERT INTO `duty_nurse` VALUES (6, 15);
INSERT INTO `duty_nurse` VALUES (6, 16);
INSERT INTO `duty_nurse` VALUES (6, 17);
INSERT INTO `duty_nurse` VALUES (6, 19);
INSERT INTO `duty_nurse` VALUES (6, 21);
INSERT INTO `duty_nurse` VALUES (3, 14);
INSERT INTO `duty_nurse` VALUES (3, 24);
INSERT INTO `duty_nurse` VALUES (3, 26);
INSERT INTO `duty_nurse` VALUES (3, 27);
INSERT INTO `duty_nurse` VALUES (7, 23);
INSERT INTO `duty_nurse` VALUES (7, 24);
INSERT INTO `duty_nurse` VALUES (7, 25);
INSERT INTO `duty_nurse` VALUES (7, 27);
INSERT INTO `duty_nurse` VALUES (2, 4);
INSERT INTO `duty_nurse` VALUES (2, 5);
INSERT INTO `duty_nurse` VALUES (1, 1);
INSERT INTO `duty_nurse` VALUES (1, 2);
INSERT INTO `duty_nurse` VALUES (1, 3);
INSERT INTO `duty_nurse` VALUES (1, 14);
INSERT INTO `duty_nurse` VALUES (1, 17);

-- ----------------------------
-- Table structure for nurse
-- ----------------------------
DROP TABLE IF EXISTS `nurse`;
CREATE TABLE `nurse`  (
  `nid` bigint NOT NULL AUTO_INCREMENT,
  `nno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `did` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`nid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of nurse
-- ----------------------------
INSERT INTO `nurse` VALUES (1, '1001', '张二', '1');
INSERT INTO `nurse` VALUES (2, '1002', '李四', '1');
INSERT INTO `nurse` VALUES (3, '1003', '王五', '8');
INSERT INTO `nurse` VALUES (4, '1004', '赵六', '3');
INSERT INTO `nurse` VALUES (5, '1005', '刘七', '5');
INSERT INTO `nurse` VALUES (6, '1006', '刘德华', '3');
INSERT INTO `nurse` VALUES (14, '1007', '球球', '7');
INSERT INTO `nurse` VALUES (15, '1008', '六六', '4');
INSERT INTO `nurse` VALUES (16, '1008', '李四8', '2');
INSERT INTO `nurse` VALUES (17, '1009', '王五9', '3');
INSERT INTO `nurse` VALUES (18, '1010', '赵六10', '4');
INSERT INTO `nurse` VALUES (19, '1011', '刘七11', '5');
INSERT INTO `nurse` VALUES (20, '1012', '张三12', '1');
INSERT INTO `nurse` VALUES (21, '1013', '李四13', '2');
INSERT INTO `nurse` VALUES (22, '1014', '王五14', '3');
INSERT INTO `nurse` VALUES (23, '1015', '赵六15', '4');
INSERT INTO `nurse` VALUES (24, '1016', '刘七16', '6');
INSERT INTO `nurse` VALUES (25, '1017', '张三17', '5');
INSERT INTO `nurse` VALUES (26, '1018', '李四18', '2');
INSERT INTO `nurse` VALUES (27, '1019', '王五19', '7');
INSERT INTO `nurse` VALUES (28, '1020', '赵六20', '8');
INSERT INTO `nurse` VALUES (29, '1021', '刘七21', '5');

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `pid` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pno` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '患者编号',
  `pavatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `page` int NULL DEFAULT NULL COMMENT '年龄',
  `pgender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `padmissiondate` date NOT NULL COMMENT '入院时间',
  `pleavedate` date NULL DEFAULT NULL COMMENT '出院时间',
  `pstatus` int NULL DEFAULT NULL COMMENT '出院状态（1-住院中，2-已出院）',
  `ptelephone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `did` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属科室',
  `nno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `wnumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bnumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES (1, '10001', '1.jpg', '张三', 22, '男', '2024-07-25', '2024-08-25', 1, '1112222', '2', '1013', '202', '202-1');
INSERT INTO `patient` VALUES (2, '10002', '1.jpg', '张三', 22, '男', '2024-07-30', '2024-08-30', 2, '1112222', '2', '1013', '204', '204-2');
INSERT INTO `patient` VALUES (3, '10003', '1.jpg', '张三', 22, '女', '2024-07-30', '2024-08-30', 2, '1112222', '2', '1008', '204', '204-3');
INSERT INTO `patient` VALUES (5, '10004', '5c2c0eda04c64fcdbd1c3ddbe15597ae_3.jpg', 'tom', 23, '男', '2024-08-20', '2024-09-07', 1, '121314', '3', '1006', '301', '301-1');
INSERT INTO `patient` VALUES (6, '10005', '1.jpg', '王五', 12, '女', '2024-08-25', '2024-09-15', 1, '1424314', '4', '1010', '405', '405-1');
INSERT INTO `patient` VALUES (7, '10006', '1.jpg', 'pony', 21, '男', '2024-06-07', '2024-07-13', 1, '1242152', '6', '1016', '601', '601-1');
INSERT INTO `patient` VALUES (8, '10007', '1.jpg', '张四', 45, '男', '2024-08-04', '2024-09-18', 1, '2143151', '8', '1020', '801', '801-1');
INSERT INTO `patient` VALUES (9, '10008', 'f598ebf5f50a48d2b2e1772c40829b23_2.jpg', '李白', 22, '女', '2024-08-20', '2024-09-01', 1, '2122', '1', '1002', '103', '103-3');
INSERT INTO `patient` VALUES (27, '10009', 'a7bbf882545347b09422e504f06a737e_3.jpg', '杜甫', 22, '男', '2024-08-31', '2024-09-08', 1, '2131413', '1', '1001', '101', '101-2');
INSERT INTO `patient` VALUES (33, '10010', '1.jpg', '丽丽', 23, '女', '2024-08-23', '2024-09-06', 2, '213124', '1', '1002', '102', '102-1');
INSERT INTO `patient` VALUES (34, '10011', '1.jpg', '天天', 33, '男', '2024-08-26', '2024-09-10', 1, '21431551', '8', '1020', '801', '801-2');
INSERT INTO `patient` VALUES (35, '10013', '091b36e98e5e4fb3be8213c10892563a_3.jpg', '111', 11, '男', '2024-08-25', '2024-09-09', 1, '132413541', '3', '1006', '301', '301-3');
INSERT INTO `patient` VALUES (36, '10014', '8912f2aeaa054d688c7d9b9555a1a418_2.jpg', '泡泡', 14, '男', '2024-09-02', '2024-09-25', 1, '2134315414', '3', '1006', '302', '302-3');
INSERT INTO `patient` VALUES (37, '10015', '035b0470b2af420a8f0d3055d330b7e0_2.jpg', 'JJ', 23, '男', '2024-08-27', '2024-09-19', 1, '324153253', '4', '1010', '405', '405-3');
INSERT INTO `patient` VALUES (38, '10016', 'acaaee5832e5416c829b92775483bf88_3.jpg', 'jerry', 22, '女', '2024-08-26', '2024-09-02', 2, '2312312414', '7', '1007', '701', '701-2');
INSERT INTO `patient` VALUES (39, '10017', '249507f675b04739a552099762780b00_1.jpg', 'alice', 12, '女', '2024-08-25', '2024-09-24', 1, '24143141', '8', '1020', '801', '801-3');
INSERT INTO `patient` VALUES (40, '10018', 'd7df1af221d04893aa43685ea7a4c41d_2.jpg', 'ace', 44, '男', '2024-08-28', '2024-09-17', 1, '2134314', '2', '1013', '204', '204-3');
INSERT INTO `patient` VALUES (41, '10019', '44180f7f6ef54766a6bd158a611a5c0e_2.jpg', 'TT', 23, '男', '2024-08-26', '2024-09-17', 1, '123131231', '6', '1016', '601', '601-2');
INSERT INTO `patient` VALUES (42, '10020', 'e3bd748b59f740a8a515f1ba0b7d6325_1.jpg', 'DD', 12, '男', '2024-08-27', '2024-09-11', 1, '23123131', '3', '1009', '301', '301-2');
INSERT INTO `patient` VALUES (43, '10023', '1.jpg', '测试', 22, '男', '2024-07-24', '2024-08-24', 1, '1112222', '2', '1013', '202', '202-2');
INSERT INTO `patient` VALUES (45, '10024', '1.jpg', '测试2', 22, '男', '2024-07-24', '2024-08-24', 2, '1112222', '1', '1013', '101', '101-3');
INSERT INTO `patient` VALUES (46, '10021', 'e254fabce7da4457bf952710b21b3f4a_3.jpg', '11', 22, '女', '2021-06-03', '2021-07-03', 2, '213', '3', '1001', '302', '302-2');
INSERT INTO `patient` VALUES (47, '10022', '1.jpg', '11', 11, '男', '2024-08-23', '2024-09-02', 1, '1211', '7', '1007', '701', '701-1');
INSERT INTO `patient` VALUES (48, '10025', '1.jpg', '11', 23, '女', '2021-06-03', '2021-07-03', 1, '213', '4', '1004', '405', '405-2');

-- ----------------------------
-- Table structure for ward
-- ----------------------------
DROP TABLE IF EXISTS `ward`;
CREATE TABLE `ward`  (
  `wid` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `wnumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '病房号',
  `did` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`wid`) USING BTREE,
  UNIQUE INDEX `ward_number`(`wnumber` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ward
-- ----------------------------
INSERT INTO `ward` VALUES (2, '101', '1');
INSERT INTO `ward` VALUES (9, '302', '3');
INSERT INTO `ward` VALUES (11, '204', '2');
INSERT INTO `ward` VALUES (12, '301', '3');
INSERT INTO `ward` VALUES (13, '103', '1');
INSERT INTO `ward` VALUES (15, '102', '1');
INSERT INTO `ward` VALUES (17, '405', '4');
INSERT INTO `ward` VALUES (18, '601', '6');
INSERT INTO `ward` VALUES (19, '801', '8');
INSERT INTO `ward` VALUES (20, '701', '7');
INSERT INTO `ward` VALUES (21, '202', '2');

SET FOREIGN_KEY_CHECKS = 1;
