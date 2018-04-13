CREATE TABLE IF NOT EXISTS pt_user (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL UNIQUE COMMENT '�û���',
  `passwd` varchar(32) NOT NULL COMMENT '����',
	`phone_number` varchar(20) DEFAULT NULL COMMENT '�绰',
  `type` int(3) NOT NULL DEFAULT 1 COMMENT '�û����ͣ�0:����Ա 1����ͨ�û���',
	`online` int(3) NOT NULL DEFAULT 0 COMMENT '����״̬ 0������ 1������',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ -1��ע�� 0������ 1:����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS pt_quotation(
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`number` varchar(32) NOT NULL COMMENT '����',
	`customer_name` varchar(64) NOT NULL COMMENT '��˾����',
  `ctt_name` varchar(32) NOT NULL COMMENT '��ϵ������',
  `ctt_phone` varchar(32) NOT NULL COMMENT '��ϵ�˵绰',
  `type` int(3) NOT NULL COMMENT 'ҵ�����ͣ�1����· 2�����ˣ�',
  `origin` varchar(64) NOT NULL COMMENT '���˵�/���˸�/��վ����',
	`dest` varchar(64) NOT NULL COMMENT 'Ŀ�ĵ�/Ŀ�ĸ�/��վ����',
  `etad` bigint(11) DEFAULT NULL COMMENT 'Ԥ�Ƶ���/����ʱ��',
  `truck_mode` varchar(64) NOT NULL COMMENT '����',
	`goods_name` varchar(64) NOT NULL COMMENT '��������',
	`carton_type` varchar(10) NOT NULL COMMENT '���ͣ�20GP��40GP��40HQ��',
	`carton_count` int(3) NOT NULL DEFAULT 0 COMMENT '����',
	`weight` int(3) DEFAULT NULL COMMENT '����',
	`volume` int(3) DEFAULT NULL COMMENT '���',
  `cvalue` decimal(15,2) DEFAULT NULL COMMENT '��ֵ',
  `transit_port` varchar(64) DEFAULT NULL COMMENT '�����ڰ�',
  `service_term` varchar(32) DEFAULT NULL COMMENT '��������',
  `csuser_id` int(11) DEFAULT NULL COMMENT '�ͷ�ID',
	`status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ 1�����ֲ� 2:������ 3�������� 4:�ѱ��� 5���ѳɽ�',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table pt_quotation MODIFY `carton_type` varchar(10) NOT NULL COMMENT '���ͣ�20GP��40GP��40HQ��'; 
 
CREATE TABLE IF NOT EXISTS pt_biz_type (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(20) NOT NULL UNIQUE COMMENT '����',
  `name` varchar(32) NOT NULL COMMENT '����',
  `special_flag` int(3) NOT NULL DEFAULT 0 COMMENT '�Ƿ���ɫҵ�� 0���� 1:��',
	`status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ 0�������� 1:����',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS pt_biz (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `biz_type_id` int(11) NOT NULL COMMENT 'ҵ������ID',
  `biz_intro` text NOT NULL COMMENT 'ҵ�����',
	`status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ 0�������� 1:����',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE IF NOT EXISTS pt_company (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '��˾����',
  `logo` varchar(64) DEFAULT NULL COMMENT '��˾logo��ַ',
  `intro` text NOT NULL COMMENT '��˾���',
  `philo` text DEFAULT NULL COMMENT '��˾����',
	`cooper` varchar(255) DEFAULT NULL COMMENT '������ʽ',
  `home_pics` varchar(512) DEFAULT NULL COMMENT '��ҳ�ֲ�ͼ�����ͼ���Ÿ�����',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS pt_news (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(3) NOT NULL COMMENT '���� 1:��ͨ 2:����',
  `title` varchar(32) NOT NULL COMMENT '����',
	`content` text NOT NULL COMMENT '����',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ 0���½� 1:�ѷ��� 2����ɾ��',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE IF NOT EXISTS pt_quotation_oper_log (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `quotation_id` int(11) NOT NULL COMMENT 'ѯ�̵�ID',
  `from_status`  int(3) NOT NULL DEFAULT 1 COMMENT '��pt_quotation.status',
	`to_status` int(3) NOT NULL DEFAULT 1 COMMENT '��pt_quotation.status',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS pt_customer_user (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL COMMENT '�û���',
	`phone_number` varchar(20) DEFAULT NULL COMMENT '�绰',
  `company` varchar(64) NOT NULL COMMENT '��˾����',
	`online` int(3) NOT NULL DEFAULT 0 COMMENT '����״̬ 0������ 1������',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ -1��ע�� 0������ 1:����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




  
 CREATE TABLE IF NOT EXISTS le_transit_line(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`number` varchar(32) NOT NULL COMMENT '���',
  `trans_mode` tinyint(1) NOT NULL COMMENT '����ģʽ(1������ 2��ֱ�� 3����ת 4����������)',
	`origin_port` bigint(11) DEFAULT NULL COMMENT '��ʼ��',
  `origin` bigint(11) NOT NULL COMMENT '��ʼվ',
  `transfer_station` bigint(11) DEFAULT NULL COMMENT '��ת��',
	`customs_station` bigint(11)  NOT NULL COMMENT '����վ',
  `transit_port` bigint(11) NOT NULL COMMENT '�����ڰ�', 
	`dest` bigint(11) NOT NULL COMMENT 'Ŀ�ĵ�',
	`depart_plan` varchar(64) DEFAULT NULL COMMENT '�����ƻ�',
  `depart_time` bigint(11) DEFAULT NULL COMMENT '���緢��ʱ��',
  `duration` int(11) DEFAULT NULL COMMENT '��·ʱ��',
	`lcl` tinyint(1) NOT NULL DEFAULT 0 COMMENT '�Ƿ�֧��ƴ��ҵ��(0����֧�� 1��֧��)',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:����',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  INDEX IX_TRANSITLINE_ORIGINDEST(`origin`,`dest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='������·' ;
 

CREATE TABLE IF NOT EXISTS le_line_quotation(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`line_id` bigint(11) NOT NULL COMMENT '·��ID',
  `ctn_owner` tinyint(1) NOT NULL COMMENT '������(1����� 2������)', 
  `ctn_type` varchar(10) NOT NULL COMMENT '���ͣ�20GP��40GP��40HQ��ƴ�䣩',
	`num_min` int(11) NOT NULL DEFAULT 1 COMMENT '�������',
	`num_max` int(11) NOT NULL DEFAULT 1 COMMENT '�������',
	`price` decimal(12,2) NOT NULL COMMENT '�۸�(�˷�+Ѻ�˷ѵ�)',
	`unit` tinyint(1) NOT NULL DEFAULT 1 COMMENT '��λ��1��RMB 2��USD��',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  INDEX IX_TLINEQUOTATION_LINECTN(`line_id`,`ctn_owner`,`ctn_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='��·����' ;

CREATE TABLE IF NOT EXISTS le_quotation(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`number` varchar(32) NOT NULL COMMENT '���', 
  `origin` bigint(11) NOT NULL COMMENT '��ʼվ',
	`dest` bigint(11) NOT NULL COMMENT 'Ŀ��վ',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:����',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  INDEX IX_QUOTATION_ORIGINDEST(`origin`,`dest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='��·���䱨��' ;

CREATE TABLE IF NOT EXISTS le_quotation_detail(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`quotation_id` bigint(11) NOT NULL COMMENT '����ID', 
  `ctn_owner` tinyint(1) NOT NULL COMMENT '������(1����� 2������)', 
	`ctn_type` varchar(10) NOT NULL COMMENT '���ͣ�20GP��40GP��40HQ��',
  `price` decimal(12,2) NOT NULL COMMENT '�۸�',   
	`escort_fee` decimal(12,2) NOT NULL COMMENT 'Ѻ�˷�',   
	`unit` tinyint(1) NOT NULL DEFAULT 1 COMMENT '��λ��1��RMB 2��USD��',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:����',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  INDEX IX_QUOTATION_QUOCTN(`quotation_id`,`ctn_owner`,`ctn_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='������ϸ' ;


CREATE TABLE IF NOT EXISTS le_station_container_price(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`station_id` bigint(11) NOT NULL COMMENT '��վID', 
	`ctn_type` varchar(10) NOT NULL COMMENT '���ͣ�20GP��40GP��40HQ��',
  `price_buy` decimal(12,2) NOT NULL COMMENT '�۸����룩',
	`price_sale` decimal(12,2) NOT NULL COMMENT '�۸�(�۳�)',
	`unit` tinyint(1) NOT NULL DEFAULT 1 COMMENT '��λ��1��RMB 2��USD��',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:����',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  INDEX IX_STATIONCTNRPRICE_STATIONCTN(`station_id`,`ctn_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='վ�����' ;
 

CREATE TABLE IF NOT EXISTS le_transfer_station(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`station_id` bigint(11) NOT NULL COMMENT '��վID',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:����',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
	`updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  INDEX IX_TRANSFERSTATION_STATION(`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='��תվ' ;	


CREATE TABLE IF NOT EXISTS le_transit_port(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`station_id` bigint(11) NOT NULL COMMENT '��վID',
  `station_outside` bigint(11) DEFAULT NULL COMMENT '���⳵վID',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:����',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��'
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  INDEX IX_TRANSITPORT_STATION(`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='�����ڰ�' ;	

CREATE TABLE IF NOT EXISTS le_customs_station(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`station_id` bigint(11) NOT NULL COMMENT '��վID',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:����',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��'
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  INDEX IX_CUSTOMSSTATION_STATION(`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='����վ' ;	

CREATE TABLE IF NOT EXISTS le_sea_port(
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
	`port_id` bigint(11) NOT NULL COMMENT '�ۿ�ID',
	`status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:����',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��'
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`),
  INDEX IX_SEAPORT_PORT(`port_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment='���˸ۿ�' ;	