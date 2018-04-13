CREATE TABLE IF NOT EXISTS pt_user (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL UNIQUE COMMENT '用户名',
  `passwd` varchar(32) NOT NULL COMMENT '密码',
	`phone_number` varchar(20) DEFAULT NULL COMMENT '电话',
  `type` int(3) NOT NULL DEFAULT 1 COMMENT '用户类型（0:管理员 1：普通用户）',
	`online` int(3) NOT NULL DEFAULT 0 COMMENT '在线状态 0：离线 1：在线',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 -1：注销 0：禁用 1:可用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS pt_quotation(
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`number` varchar(32) NOT NULL COMMENT '单号',
	`customer_name` varchar(64) NOT NULL COMMENT '公司名称',
  `ctt_name` varchar(32) NOT NULL COMMENT '联系人姓名',
  `ctt_phone` varchar(32) NOT NULL COMMENT '联系人电话',
  `type` int(3) NOT NULL COMMENT '业务类型（1：铁路 2：海运）',
  `origin` varchar(64) NOT NULL COMMENT '起运地/起运港/发站代码',
	`dest` varchar(64) NOT NULL COMMENT '目的地/目的港/到站代码',
  `etad` bigint(11) DEFAULT NULL COMMENT '预计到达/出发时间',
  `truck_mode` varchar(64) NOT NULL COMMENT '车型',
	`goods_name` varchar(64) NOT NULL COMMENT '货物名称',
	`carton_type` varchar(10) NOT NULL COMMENT '箱型（20GP，40GP，40HQ）',
	`carton_count` int(3) NOT NULL DEFAULT 0 COMMENT '箱量',
	`weight` int(3) DEFAULT NULL COMMENT '重量',
	`volume` int(3) DEFAULT NULL COMMENT '体积',
  `cvalue` decimal(15,2) DEFAULT NULL COMMENT '货值',
  `transit_port` varchar(64) DEFAULT NULL COMMENT '过境口岸',
  `service_term` varchar(32) DEFAULT NULL COMMENT '服务条款',
  `csuser_id` int(11) DEFAULT NULL COMMENT '客服ID',
	`status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 1：待分拨 2:待处理 3：处理中 4:已报价 5：已成交',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table pt_quotation MODIFY `carton_type` varchar(10) NOT NULL COMMENT '箱型（20GP，40GP，40HQ）'; 
 
CREATE TABLE IF NOT EXISTS pt_biz_type (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(20) NOT NULL UNIQUE COMMENT '编码',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `special_flag` int(3) NOT NULL DEFAULT 0 COMMENT '是否特色业务 0：否 1:是',
	`status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 0：不可用 1:可用',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS pt_biz (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `biz_type_id` int(11) NOT NULL COMMENT '业务类型ID',
  `biz_intro` text NOT NULL COMMENT '业务介绍',
	`status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 0：不可用 1:可用',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE IF NOT EXISTS pt_company (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '公司名称',
  `logo` varchar(64) DEFAULT NULL COMMENT '公司logo地址',
  `intro` text NOT NULL COMMENT '公司简介',
  `philo` text DEFAULT NULL COMMENT '公司理念',
	`cooper` varchar(255) DEFAULT NULL COMMENT '合作方式',
  `home_pics` varchar(512) DEFAULT NULL COMMENT '首页轮播图（多个图逗号隔开）',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS pt_news (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(3) NOT NULL COMMENT '类型 1:普通 2:特殊',
  `title` varchar(32) NOT NULL COMMENT '标题',
	`content` text NOT NULL COMMENT '内容',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 0：新建 1:已发布 2：已删除',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE IF NOT EXISTS pt_quotation_oper_log (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `quotation_id` int(11) NOT NULL COMMENT '询盘单ID',
  `from_status`  int(3) NOT NULL DEFAULT 1 COMMENT '见pt_quotation.status',
	`to_status` int(3) NOT NULL DEFAULT 1 COMMENT '见pt_quotation.status',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS pt_customer_user (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
	`phone_number` varchar(20) DEFAULT NULL COMMENT '电话',
  `company` varchar(64) NOT NULL COMMENT '公司名称',
	`online` int(3) NOT NULL DEFAULT 0 COMMENT '在线状态 0：离线 1：在线',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '状态 -1：注销 0：禁用 1:可用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




  
 CREATE TABLE IF NOT EXISTS le_transit_line(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`number` varchar(32) NOT NULL COMMENT '编号',
  `trans_mode` tinyint(1) NOT NULL COMMENT '运输模式(1：班列 2：直发 3：中转 4：海铁联运)',
	`origin_port` bigint(11) DEFAULT NULL COMMENT '起始港',
  `origin` bigint(11) NOT NULL COMMENT '起始站',
  `transfer_station` bigint(11) DEFAULT NULL COMMENT '中转地',
	`customs_station` bigint(11)  NOT NULL COMMENT '报关站',
  `transit_port` bigint(11) NOT NULL COMMENT '过境口岸', 
	`dest` bigint(11) NOT NULL COMMENT '目的地',
	`depart_plan` varchar(64) DEFAULT NULL COMMENT '发车计划',
  `depart_time` bigint(11) DEFAULT NULL COMMENT '最早发车时间',
  `duration` int(11) DEFAULT NULL COMMENT '线路时长',
	`lcl` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否支持拼箱业务(0：不支持 1：支持)',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX IX_TRANSITLINE_ORIGINDEST(`origin`,`dest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='运输线路' ;
 

CREATE TABLE IF NOT EXISTS le_line_quotation(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`line_id` bigint(11) NOT NULL COMMENT '路线ID',
  `ctn_owner` tinyint(1) NOT NULL COMMENT '箱属性(1：租借 2：自有)', 
  `ctn_type` varchar(10) NOT NULL COMMENT '箱型（20GP，40GP，40HQ，拼箱）',
	`num_min` int(11) NOT NULL DEFAULT 1 COMMENT '最低数量',
	`num_max` int(11) NOT NULL DEFAULT 1 COMMENT '最高数量',
	`price` decimal(12,2) NOT NULL COMMENT '价格(运费+押运费等)',
	`unit` tinyint(1) NOT NULL DEFAULT 1 COMMENT '单位（1：RMB 2：USD）',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX IX_TLINEQUOTATION_LINECTN(`line_id`,`ctn_owner`,`ctn_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='线路报价' ;

CREATE TABLE IF NOT EXISTS le_quotation(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`number` varchar(32) NOT NULL COMMENT '编号', 
  `origin` bigint(11) NOT NULL COMMENT '起始站',
	`dest` bigint(11) NOT NULL COMMENT '目的站',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX IX_QUOTATION_ORIGINDEST(`origin`,`dest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='线路区间报价' ;

CREATE TABLE IF NOT EXISTS le_quotation_detail(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`quotation_id` bigint(11) NOT NULL COMMENT '报价ID', 
  `ctn_owner` tinyint(1) NOT NULL COMMENT '箱属性(1：租借 2：自有)', 
	`ctn_type` varchar(10) NOT NULL COMMENT '箱型（20GP，40GP，40HQ）',
  `price` decimal(12,2) NOT NULL COMMENT '价格',   
	`escort_fee` decimal(12,2) NOT NULL COMMENT '押运费',   
	`unit` tinyint(1) NOT NULL DEFAULT 1 COMMENT '单位（1：RMB 2：USD）',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX IX_QUOTATION_QUOCTN(`quotation_id`,`ctn_owner`,`ctn_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='报价明细' ;


CREATE TABLE IF NOT EXISTS le_station_container_price(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`station_id` bigint(11) NOT NULL COMMENT '车站ID', 
	`ctn_type` varchar(10) NOT NULL COMMENT '箱型（20GP，40GP，40HQ）',
  `price_buy` decimal(12,2) NOT NULL COMMENT '价格（买入）',
	`price_sale` decimal(12,2) NOT NULL COMMENT '价格(售出)',
	`unit` tinyint(1) NOT NULL DEFAULT 1 COMMENT '单位（1：RMB 2：USD）',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX IX_STATIONCTNRPRICE_STATIONCTN(`station_id`,`ctn_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='站点箱价' ;
 

CREATE TABLE IF NOT EXISTS le_transfer_station(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`station_id` bigint(11) NOT NULL COMMENT '车站ID',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间',
	`updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX IX_TRANSFERSTATION_STATION(`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='中转站' ;	


CREATE TABLE IF NOT EXISTS le_transit_port(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`station_id` bigint(11) NOT NULL COMMENT '车站ID',
  `station_outside` bigint(11) DEFAULT NULL COMMENT '境外车站ID',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间'
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX IX_TRANSITPORT_STATION(`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='过境口岸' ;	

CREATE TABLE IF NOT EXISTS le_customs_station(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`station_id` bigint(11) NOT NULL COMMENT '车站ID',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间'
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX IX_CUSTOMSSTATION_STATION(`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='报关站' ;	

CREATE TABLE IF NOT EXISTS le_sea_port(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`port_id` bigint(11) NOT NULL COMMENT '港口ID',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态 0：不可用 1:可用',
	`remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `creator_id` int(11) NOT NULL COMMENT '创建人',
  `create_time` bigint(11) NOT NULL COMMENT '创建时间'
  `updator_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` bigint(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX IX_SEAPORT_PORT(`port_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='海运港口' ;	