/*数据表结构*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for live
-- ----------------------------
DROP TABLE IF EXISTS `live`;
CREATE TABLE `live`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `push_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ban` tinyint(4) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000,
  `client_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `stream_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uuid`(`uuid`) USING BTREE,
  UNIQUE INDEX `push_code`(`push_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
