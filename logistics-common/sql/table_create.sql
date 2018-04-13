CREATE TABLE IF NOT EXISTS lm_user (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL UNIQUE COMMENT '用户名',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `passwd` varchar(32) NOT NULL COMMENT '密码',
  `head_img` varchar(100) DEFAULT NULL COMMENT '头像URL',
	`phone_number` varchar(20) DEFAULT NULL COMMENT '电话',
  `type` int(3) NOT NULL DEFAULT 1 COMMENT '用户类型（0:平台管理员 1：普通用户 2：其它用户）',
	`level` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '用户级别(1:默认级别 2:白金 3:钻石)',
  `company_id` int(11)) NOT NULL COMMENT '公司ID',
	`online` int(3) NOT NULL DEFAULT 0 COMMENT '在线状态 0：离线 1：在线',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 -1：注销 0：禁用 1:可用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_role (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL UNIQUE COMMENT '角色名称',
  `status` int(3) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_permission (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(32) NOT NULL UNIQUE COMMENT '权限编号',
  `name` varchar(32) NOT NULL COMMENT '权限名称',
  `pid` int(11) NOT NULL DEFAULT '-1' COMMENT '父ID',
  `type` int(3) NOT NULL COMMENT '权限类型（菜单、功能键等）',
	`seq` int(3) NOT NULL COMMENT '权限序号',
  `status` int(3) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_user_role (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_role_permission (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ????????????
CREATE TABLE IF NOT EXISTS lm_device (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(32) NOT NULL UNIQUE COMMENT '设备编号',
  `model` varchar(16) DEFAULT NULL COMMENT '设备型号',
	`card_number` varchar(32) NOT NULL COMMENT '设备卡号',
  `company_id` int(11) NOT NULL COMMENT '公司ID',
	`battery` int(3) DEFAULT 0 COMMENT '当前电量',
	`onff` int(3) DEFAULT 0 COMMENT '开关 0:离线 1：在线 2：休眠',
	`onff_time` bigint(11) DEFAULT NULL COMMENT '开关时间',
	`location` varchar(32) DEFAULT NULL COMMENT '当前位置（坐标）',
  `location_freq` int(3) DEFAULT 1440 COMMENT '定位频率（分钟 默认1天）',
  `status` int(3) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用 2:使用中 3：回收中 4:使用完成',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS lm_device_battery (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `device_id` int(11) NOT NULL COMMENT '设备ID',
  `order_id` int(11) NOT NULL COMMENT '运输单ID',
	`battery` int(11) NOT NULL COMMENT '电量',
	`time` bigint(11) NOT NULL COMMENT '电量时间',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 0：不可用 1:可用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_device_location (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `device_id` int(11) NOT NULL COMMENT '设备ID',
  `order_id` int(11) NOT NULL COMMENT '运输单ID',
	`longitude` decimal(12,8) NOT NULL COMMENT '经度',
  `latitude` decimal(12,8) NOT NULL COMMENT '纬度',
	`radius` int(11) DEFAULT 0 COMMENT '精度（半径）（米）',
	`inner_cn` int(3) NOT NULL DEFAULT 0 COMMENT '是否国内(0:否 1：是)',
  `time` bigint(11) NOT NULL COMMENT '坐标时间',
	`time_stay` int(11) NOT NULL DEFAULT 0 COMMENT '停留时间（秒）',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
	`source_type` int(3) NOT NULL DEFAULT 1 COMMENT '定位源类型（1：GPS 2：BTS 3：WIFI）',
	`source_id` int(11) NOT NULL DEFAULT -1 COMMENT '定位源ID',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 0：不可用 1:可用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 -- alter table lm_device_location add column inner_cn int(3) NOT NULL DEFAULT 0 COMMENT '是否国内(0:否 1：是)' after radius ;

CREATE TABLE IF NOT EXISTS lm_trans_order (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `device_number` varchar(32) NOT NULL COMMENT '设备编号',
  `carrier_number` varchar(32) DEFAULT NULL COMMENT '运输载体编号',
	`bill_number` varchar(32) DEFAULT NULL UNIQUE COMMENT '提单号',
  `origin` varchar(32) NOT NULL COMMENT '起始地',
	`destination` varchar(32) NOT NULL COMMENT '目的地',
	`goods_name` varchar(64) NOT NULL COMMENT '货物名称',
	`carton_type` varchar(10) NOT NULL COMMENT '箱型（20GP，40GP，40HQ）',
	`carton_owner` int(3) DEFAULT NULL COMMENT '集装箱归属(1:租借 2：自有)',
	`hs_code` varchar(32) DEFAULT NULL COMMENT 'HS编码',
	`vehicle_type` int(3) DEFAULT NULL COMMENT '车型',
  `transit_port` varchar(64) DEFAULT NULL COMMENT '过境口岸',
  `service_term` varchar(32) DEFAULT NULL COMMENT '服务条款',
  `customer` varchar(32) DEFAULT NULL COMMENT '服务客户',
	`location_freq_type` int(3) DEFAULT NULL COMMENT '定位频率类型（1：分钟 2：小时 3：天）',
  `location_freq` int(11) DEFAULT NULL COMMENT '定位频率',
  `trans_mode` int(3) DEFAULT NULL COMMENT '运输模式(0：火车 1：汽车 2：飞机 3：轮船）',
	`trans_status` int(3) DEFAULT NULL COMMENT '运输状态(-1 运输异常 1：运输中 2运输完成）',
  `start_time` bigint(11) DEFAULT NULL COMMENT '开始时间',
  `finish_time` bigint(11) DEFAULT NULL COMMENT '完成时间',
  `status` int(3) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_trans_order_oper (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '设备编号',
  `oper_status` int(3) NOT NULL COMMENT '操作状态（1：中转 2：清关 3：）',
	`oper_time` bigint(11) NOT NULL COMMENT '操作时间',
	`station` varchar(32) DEFAULT NULL COMMENT '站点代码',
  `status` int(3) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS lm_company (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(32) NOT NULL UNIQUE COMMENT '公司编号',
  `name` varchar(128) DEFAULT NULL COMMENT '公司名称',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 0：不可用 1:可用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS lm_city (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(32) NOT NULL UNIQUE COMMENT '城市编号',
  `name` varchar(255) NOT NULL COMMENT '城市名称',
  `name_en` varchar(255) DEFAULT NULL COMMENT '城市名称(英文)',
	`country_id` int(11) NOT NULL COMMENT '城市所在国家ID',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 0：不可用 1:可用',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_company_city (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NOT NULL COMMENT '公司ID',
  `city_id` int(11) NOT NULL COMMENT '城市ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_device_location_bts (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `imei` VARCHAR(15) NOT NULL COMMENT '设备IMEI',
  `mcc` varchar(10) NOT NULL COMMENT '移动用户所属国家代号',
  `mnc` varchar(10) NOT NULL COMMENT '移动网号码',
  `lac1` varchar(10) NOT NULL COMMENT '位置区码',
  `ci1` varchar(10) DEFAULT NULL COMMENT '移动基站',
  `rssi1` varchar(10) DEFAULT NULL COMMENT '信号强度',
  `lac2` varchar(10) DEFAULT NULL,
  `ci2` varchar(10) DEFAULT NULL,
  `rssi2` varchar(10) DEFAULT NULL,
  `lac3` varchar(10) DEFAULT NULL,
  `ci3` varchar(10) DEFAULT NULL,
  `rssi3` varchar(10) DEFAULT NULL,
  `lac4` varchar(10) DEFAULT NULL,
  `ci4` varchar(10) DEFAULT NULL,
  `rssi4` varchar(10) DEFAULT NULL,
  `lac5` varchar(10) DEFAULT NULL,
  `ci5` varchar(10) DEFAULT NULL,
  `rssi5` varchar(10) DEFAULT NULL,
  `time` BIGINT(11) NOT NULL COMMENT '时间',
	`status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 0：不可用 1:可用',
  `handle_flag` int(3) DEFAULT 0 COMMENT '是否已被解析：0否 1是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_device_location_gps (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imei` varchar(15) NOT NULL COMMENT '设备IMEI',
  `longitude` decimal(12,8) NOT NULL COMMENT '经度',
  `latitude` decimal(12,8) NOT NULL COMMENT '纬度',
	`location_flag` int(3) DEFAULT NULL COMMENT '定位标识',
	`speed` int(11) DEFAULT NULL COMMENT '速度 单位2节',
	`direction` int(11) DEFAULT NULL COMMENT '方向 单位10度',
	`factor` decimal(9,5) DEFAULT NULL COMMENT 'HDOP水平分量精度因子',
  `time` BIGINT(11) NOT NULL COMMENT '时间',
	`status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 0：不可用 1:可用',
  `handle_flag` int(3) DEFAULT 0 COMMENT '是否已被解析：0否 1是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE IF NOT EXISTS lm_device_change_status (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT, 
  `device_id` int(11) NOT NULL COMMENT '设备ID',
	`from_status` int(3) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用 2:使用中 3：回收中',
	`to_status` int(3) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用 2:使用中 3：回收中',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_device_cmd (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `imei` varchar(32) NOT NULL COMMENT '设备编号',
  `model` varchar(16) NOT NULL COMMENT '设备型号',
  `function` varchar(16) NOT NULL COMMENT '指令功能',
  `function_desc` varchar(16) DEFAULT NULL COMMENT '指令功能描述',
  `data` varchar(256) DEFAULT NULL COMMENT '指令数据',
  `save` int(3) NOT NULL DEFAULT 0 COMMENT '是否需要保存',
  `reply` int(3) NOT NULL DEFAULT 0 COMMENT '是否需要回复',
	`command` varchar(32) DEFAULT NULL COMMENT '指令下发内容', 
	`exec_mode` int(3) NOT NULL DEFAULT 1 COMMENT '执行模式 1：立即执行 2:定时执行',
  `exec_cron` varchar(32) DEFAULT NULL COMMENT '执行计划',
	`exec_status` int(3) NOT NULL DEFAULT 0 COMMENT '执行状态 0：未执行 1:执行中 2：成功成功 3：执行失败 4：执行过期 5：待执行', 
  `exec_count` int(3) NOT NULL DEFAULT 0 COMMENT '执行次数', 
	`exec_time` bigint(11) DEFAULT NULL COMMENT '执行时间',
  `status` int(3) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_device_location_semi (
	`id` int(11) unsigned NOT NULL AUTO_INCREMENT, 
  `imei` VARCHAR(15) NOT NULL COMMENT '设备IMEI',
	`longitude` decimal(12,8) NOT NULL COMMENT '经度',
  `latitude` decimal(12,8) NOT NULL COMMENT '纬度',
	`radius` int(11) DEFAULT 0 COMMENT '精度（半径）（米）',
	`distance` int(11) COMMENT '与前点距离(m)',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `inner_cn` int(3) NOT NULL DEFAULT 0 COMMENT '是否国内(0:否 1：是)',
  `time_begin` bigint(11) NOT NULL COMMENT '开始时间',
	`time_end` bigint(11) NOT NULL COMMENT '结束时间',
	`source_type` int(3) NOT NULL DEFAULT 1 COMMENT '定位源类型（1：GPS 2：BTS 3：WIFI）',
	`source_id` int(11) NOT NULL DEFAULT -1 COMMENT '定位源ID',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 0：不可用 1:可用',
  `handle_status` int(3) DEFAULT 0 COMMENT '处理状态：0未处理 1已完成',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
	PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

 
CREATE TABLE IF NOT EXISTS lm_station (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(32) NOT NULL UNIQUE COMMENT '车站编号',
  `name` varchar(255) NOT NULL COMMENT '车站名称',
  `name_en` varchar(255) DEFAULT NULL COMMENT '城市名称(英文)',
	`city` varchar(128) NOT NULL COMMENT '所在城市ID',
	`country` varchar(128) NOT NULL COMMENT '城市所在国家ID',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 0：不可用 1:可用',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_trans_order_statistics ( 
  `origin_city` int(11)NOT NULL COMMENT '起始地所在城市ID',
  `origin_country` int(11) NOT NULL COMMENT '起始地所在国家ID',
	`dest_city` int(11) NOT NULL COMMENT '起始地所在城市ID',
  `dest_country` int(11) NOT NULL COMMENT '起始地所在国家ID', 
	`goods_name` varchar(64) NOT NULL COMMENT '货物名称',
	`carton_type` varchar(10) NOT NULL COMMENT '箱型（20GP，40GP，40HQ）',
	`carton_owner` int(3) DEFAULT NULL COMMENT '集装箱归属(1:租借 2：自有)', 
	`vehicle_type` int(3) DEFAULT NULL COMMENT '车型',
  `transit_port` varchar(64) DEFAULT NULL COMMENT '过境口岸', 
  `trans_mode` int(3) DEFAULT NULL COMMENT '运输模式(0：火车 1：汽车 2：飞机 3：轮船）',
	`trans_status` int(3) DEFAULT NULL COMMENT '运输状态(-1 运输异常 1：运输中 2运输完成）',
  `start_day` int(11) DEFAULT NULL COMMENT '开始时间(日期）：20180323',
  `finish_day` int(11) DEFAULT NULL COMMENT '完成时间(日期）：20180323', 
  `company_id` int(11) NOT NULL COMMENT '公司ID',
	`order_id` int(11) NOT NULL COMMENT '订单ID',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_country (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(32) NOT NULL UNIQUE COMMENT '城市编号',
  `name` varchar(255) NOT NULL COMMENT '城市名称',
  `name_en` varchar(255) DEFAULT NULL COMMENT '城市名称(英文)', 
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 0：不可用 1:可用',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;