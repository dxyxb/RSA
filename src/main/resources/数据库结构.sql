/*
  数据库名：hm_campus_activity
  适配：鸿蒙平台智慧教育校园活动报名与组织移动应用
  优化点：补充外键/索引/软删除/扩展字段，适配智能推荐/高并发场景
*/
CREATE DATABASE IF NOT EXISTS hm_campus_activity DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE hm_campus_activity;

-- 1. 用户表（核心）
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  account VARCHAR(50) NOT NULL COMMENT '账号（学号/工号）',
  password VARCHAR(100) NOT NULL COMMENT '加密密码',
  username VARCHAR(50) NOT NULL COMMENT '姓名',
  role TINYINT NOT NULL COMMENT '角色：1-学生 2-教师 3-管理员',
  college VARCHAR(50) COMMENT '学院',
  clazz VARCHAR(50) COMMENT '班级',
  grade VARCHAR(20) COMMENT '年级',
  phone VARCHAR(20) COMMENT '手机号',
  email VARCHAR(50) COMMENT '邮箱',
  avatar VARCHAR(255) COMMENT '头像地址',
  open_id VARCHAR(100) COMMENT '鸿蒙/校园统一认证ID',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  is_deleted TINYINT NOT NULL DEFAULT 0 COMMENT '软删除：0-未删 1-已删',
  PRIMARY KEY (id),
  UNIQUE KEY uk_account (account),
  INDEX idx_role (role),
  INDEX idx_college_grade (college, grade)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表';

-- 2. 活动表（核心）
DROP TABLE IF EXISTS activity;
CREATE TABLE activity (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  title VARCHAR(100) NOT NULL COMMENT '活动标题',
  type VARCHAR(50) NOT NULL COMMENT '活动类型：学术讲座/志愿活动/文体活动等',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0-草稿 1-发布 2-报名中 3-报满 4-结束',
  creator_id BIGINT UNSIGNED NOT NULL COMMENT '创建人ID（关联sys_user.id）',
  start_time DATETIME NOT NULL COMMENT '活动开始时间',
  end_time DATETIME NOT NULL COMMENT '活动结束时间',
  sign_start_time DATETIME NOT NULL COMMENT '报名开始时间',
  sign_end_time DATETIME NOT NULL COMMENT '报名结束时间',
  max_people INT UNSIGNED NOT NULL COMMENT '最大报名人数',
  current_people INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '当前报名人数',
  location VARCHAR(100) COMMENT '活动地点',
  description TEXT COMMENT '活动详情',
  credit_type VARCHAR(50) COMMENT '学分/学时类型',
  credit_num DECIMAL(3,1) COMMENT '学分/学时数量',
  visible_scope JSON COMMENT '可见范围：{"college":"软件学院","grade":"2022级"}',
  cover_img VARCHAR(255) COMMENT '封面图片',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  is_deleted TINYINT NOT NULL DEFAULT 0 COMMENT '软删除：0-未删 1-已删',
  PRIMARY KEY (id),
  INDEX idx_creator_id (creator_id),
  INDEX idx_type_status_time (type, status, start_time), -- 复合索引：优化高频查询
  INDEX idx_sign_time (sign_start_time, sign_end_time),
  FOREIGN KEY (creator_id) REFERENCES sys_user(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动主表';

-- 3. 活动报名表
DROP TABLE IF EXISTS activity_apply;
CREATE TABLE activity_apply (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '报名ID',
  activity_id BIGINT UNSIGNED NOT NULL COMMENT '活动ID',
  user_id BIGINT UNSIGNED NOT NULL COMMENT '报名用户ID',
  apply_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '报名时间',
  review_status TINYINT NOT NULL DEFAULT 0 COMMENT '审核状态：0-待审核 1-通过 2-驳回',
  reviewer_id BIGINT UNSIGNED COMMENT '审核人ID',
  review_time DATETIME COMMENT '审核时间',
  sign_in_status TINYINT NOT NULL DEFAULT 0 COMMENT '签到状态：0-未签到 1-已签到',
  sign_in_time DATETIME COMMENT '签到时间',
  experience_level VARCHAR(20) COMMENT '经验等级：BEGINNER/INTERMEDIATE/ADVANCED/OTHER',
  remark VARCHAR(255) COMMENT '备注',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  is_deleted TINYINT NOT NULL DEFAULT 0 COMMENT '软删除：0-未删 1-已删',
  PRIMARY KEY (id),
  UNIQUE KEY uk_activity_user (activity_id, user_id), -- 避免重复报名
  INDEX idx_review_status (review_status),
  INDEX idx_sign_in_status (sign_in_status),
  FOREIGN KEY (activity_id) REFERENCES activity(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (user_id) REFERENCES sys_user(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (reviewer_id) REFERENCES sys_user(id) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动报名表';

-- 4. 活动日程表
DROP TABLE IF EXISTS activity_schedule;
CREATE TABLE activity_schedule (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日程ID',
  activity_id BIGINT UNSIGNED NOT NULL COMMENT '活动ID',
  title VARCHAR(100) NOT NULL COMMENT '日程标题',
  start_time DATETIME NOT NULL COMMENT '日程开始时间',
  end_time DATETIME NOT NULL COMMENT '日程结束时间',
  location VARCHAR(100) COMMENT '日程地点',
  description TEXT COMMENT '日程详情',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  is_deleted TINYINT NOT NULL DEFAULT 0 COMMENT '软删除：0-未删 1-已删',
  PRIMARY KEY (id),
  INDEX idx_activity_id (activity_id),
  FOREIGN KEY (activity_id) REFERENCES activity(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动日程表';

-- 5. 活动人员分工表
DROP TABLE IF EXISTS activity_staff;
CREATE TABLE activity_staff (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '分工ID',
  activity_id BIGINT UNSIGNED NOT NULL COMMENT '活动ID',
  user_id BIGINT UNSIGNED NOT NULL COMMENT '参与分工的用户ID',
  role VARCHAR(50) NOT NULL COMMENT '分工角色：主持人/志愿者/技术支持等',
  responsibility TEXT COMMENT '职责描述',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  is_deleted TINYINT NOT NULL DEFAULT 0 COMMENT '软删除：0-未删 1-已删',
  PRIMARY KEY (id),
  INDEX idx_activity_user (activity_id, user_id),
  FOREIGN KEY (activity_id) REFERENCES activity(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (user_id) REFERENCES sys_user(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动人员分工表';

-- 6. 活动物资表
DROP TABLE IF EXISTS activity_material;
CREATE TABLE activity_material (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '物资ID',
  activity_id BIGINT UNSIGNED NOT NULL COMMENT '活动ID',
  name VARCHAR(100) NOT NULL COMMENT '物资名称',
  quantity INT UNSIGNED NOT NULL COMMENT '物资数量',
  status TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0-未准备 1-已准备 2-已使用 3-已归还',
  supplier VARCHAR(100) COMMENT '供应商',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  is_deleted TINYINT NOT NULL DEFAULT 0 COMMENT '软删除：0-未删 1-已删',
  PRIMARY KEY (id),
  INDEX idx_activity_id (activity_id),
  INDEX idx_status (status),
  FOREIGN KEY (activity_id) REFERENCES activity(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动物资表';

-- 7. 活动反馈表
DROP TABLE IF EXISTS activity_feedback;
CREATE TABLE activity_feedback (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '反馈ID',
  activity_id BIGINT UNSIGNED NOT NULL COMMENT '活动ID',
  user_id BIGINT UNSIGNED NOT NULL COMMENT '反馈用户ID',
  apply_id BIGINT UNSIGNED COMMENT '报名ID（关联activity_apply.id）',
  score TINYINT COMMENT '评分（1-5分）',
  content TEXT COMMENT '反馈内容',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  is_deleted TINYINT NOT NULL DEFAULT 0 COMMENT '软删除：0-未删 1-已删',
  PRIMARY KEY (id),
  INDEX idx_activity_user (activity_id, user_id),
  FOREIGN KEY (activity_id) REFERENCES activity(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (user_id) REFERENCES sys_user(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (apply_id) REFERENCES activity_apply(id) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='活动反馈表';

-- 8. 用户兴趣标签表（智能推荐核心）
DROP TABLE IF EXISTS user_tag;
CREATE TABLE user_tag (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  user_id BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
  tag_name VARCHAR(50) NOT NULL COMMENT '标签名：学术讲座/志愿活动/文体活动等',
  weight DECIMAL(3,2) NOT NULL DEFAULT 0.00 COMMENT '标签权重（0-1）',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '权重更新时间',
  is_deleted TINYINT NOT NULL DEFAULT 0 COMMENT '软删除：0-未删 1-已删',
  PRIMARY KEY (id),
  UNIQUE KEY uk_user_tag (user_id, tag_name), -- 一个用户一个标签仅一条记录
  INDEX idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户兴趣标签表';

-- 9. 用户行为日志表（修复版：关键字更名+规范索引）
DROP TABLE IF EXISTS user_behavior_log;
CREATE TABLE user_behavior_log (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  user_id BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
  activity_id BIGINT UNSIGNED NOT NULL COMMENT '活动ID',
  action VARCHAR(20) NOT NULL COMMENT '行为类型：浏览/报名/参与/反馈/收藏',
  action_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '行为时间',
  ym VARCHAR(6) NOT NULL COMMENT '年月（分表用：202603）', -- 关键修复：将year_month改为ym，避免关键字冲突
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  is_deleted TINYINT NOT NULL DEFAULT 0 COMMENT '软删除：0-未删 1-已删',
  PRIMARY KEY (id),
  INDEX idx_user_action_time (user_id, action, action_time), -- 复合索引：优化画像查询
  INDEX idx_activity_id (activity_id),
  INDEX idx_ym (ym), -- 分表筛选索引
  FOREIGN KEY (user_id) REFERENCES sys_user(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (activity_id) REFERENCES activity(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户行为日志表';

-- 10. AI对话历史表
DROP TABLE IF EXISTS ai_chat_history;
CREATE TABLE ai_chat_history (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '对话ID',
  user_id BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
  user_msg TEXT NOT NULL COMMENT '用户消息',
  ai_msg TEXT NOT NULL COMMENT 'AI回复',
  chat_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '对话时间',
  expire_time DATETIME COMMENT '过期时间（自动清理）',
  is_deleted TINYINT NOT NULL DEFAULT 0 COMMENT '软删除：0-未删 1-已删',
  PRIMARY KEY (id),
  INDEX idx_user_id (user_id),
  INDEX idx_expire_time (expire_time), -- 清理过期数据索引
  FOREIGN KEY (user_id) REFERENCES sys_user(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI助手对话历史表';

-- 11. 消息通知表
DROP TABLE IF EXISTS notification;
CREATE TABLE notification (
  id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '通知ID',
  user_id BIGINT UNSIGNED NOT NULL COMMENT '接收用户ID',
  type VARCHAR(50) NOT NULL COMMENT '通知类型：报名审核/活动提醒/系统通知等',
  content TEXT NOT NULL COMMENT '通知内容',
  business_id BIGINT UNSIGNED COMMENT '关联业务ID（活动ID/报名ID）',
  is_read TINYINT NOT NULL DEFAULT 0 COMMENT '已读状态：0-未读 1-已读',
  read_time DATETIME COMMENT '已读时间',
  create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  is_deleted TINYINT NOT NULL DEFAULT 0 COMMENT '软删除：0-未删 1-已删',
  PRIMARY KEY (id),
  INDEX idx_user_is_read (user_id, is_read), -- 优化未读消息查询
  INDEX idx_business_id (business_id),
  FOREIGN KEY (user_id) REFERENCES sys_user(id) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息通知表';