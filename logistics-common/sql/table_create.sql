CREATE TABLE IF NOT EXISTS lm_user (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL UNIQUE COMMENT '�û���',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '�ǳ�',
  `passwd` varchar(32) NOT NULL COMMENT '����',
  `head_img` varchar(100) DEFAULT NULL COMMENT 'ͷ��URL',
	`phone_number` varchar(20) DEFAULT NULL COMMENT '�绰',
  `type` int(3) NOT NULL DEFAULT 1 COMMENT '�û����ͣ�0:ƽ̨����Ա 1����ͨ�û� 2�������û���',
	`level` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '�û�����(1:Ĭ�ϼ��� 2:�׽� 3:��ʯ)',
  `company_id` int(11)) NOT NULL COMMENT '��˾ID',
	`online` int(3) NOT NULL DEFAULT 0 COMMENT '����״̬ 0������ 1������',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ -1��ע�� 0������ 1:����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_role (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL UNIQUE COMMENT '��ɫ����',
  `status` int(3) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_permission (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(32) NOT NULL UNIQUE COMMENT 'Ȩ�ޱ��',
  `name` varchar(32) NOT NULL COMMENT 'Ȩ������',
  `pid` int(11) NOT NULL DEFAULT '-1' COMMENT '��ID',
  `type` int(3) NOT NULL COMMENT 'Ȩ�����ͣ��˵������ܼ��ȣ�',
	`seq` int(3) NOT NULL COMMENT 'Ȩ�����',
  `status` int(3) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_user_role (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '�û�ID',
  `role_id` int(11) NOT NULL COMMENT '��ɫID',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_role_permission (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '��ɫID',
  `permission_id` int(11) NOT NULL COMMENT 'Ȩ��ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- ????????????
CREATE TABLE IF NOT EXISTS lm_device (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(32) NOT NULL UNIQUE COMMENT '�豸���',
  `model` varchar(16) DEFAULT NULL COMMENT '�豸�ͺ�',
	`card_number` varchar(32) NOT NULL COMMENT '�豸����',
  `company_id` int(11) NOT NULL COMMENT '��˾ID',
	`battery` int(3) DEFAULT 0 COMMENT '��ǰ����',
	`onff` int(3) DEFAULT 0 COMMENT '���� 0:���� 1������ 2������',
	`onff_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
	`location` varchar(32) DEFAULT NULL COMMENT '��ǰλ�ã����꣩',
  `location_freq` int(3) DEFAULT 1440 COMMENT '��λƵ�ʣ����� Ĭ��1�죩',
  `status` int(3) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:���� 2:ʹ���� 3�������� 4:ʹ�����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS lm_device_battery (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `device_id` int(11) NOT NULL COMMENT '�豸ID',
  `order_id` int(11) NOT NULL COMMENT '���䵥ID',
	`battery` int(11) NOT NULL COMMENT '����',
	`time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ 0�������� 1:����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_device_location (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `device_id` int(11) NOT NULL COMMENT '�豸ID',
  `order_id` int(11) NOT NULL COMMENT '���䵥ID',
	`longitude` decimal(12,8) NOT NULL COMMENT '����',
  `latitude` decimal(12,8) NOT NULL COMMENT 'γ��',
	`radius` int(11) DEFAULT 0 COMMENT '���ȣ��뾶�����ף�',
	`inner_cn` int(3) NOT NULL DEFAULT 0 COMMENT '�Ƿ����(0:�� 1����)',
  `time` bigint(11) NOT NULL COMMENT '����ʱ��',
	`time_stay` int(11) NOT NULL DEFAULT 0 COMMENT 'ͣ��ʱ�䣨�룩',
  `address` varchar(255) DEFAULT NULL COMMENT '��ַ',
	`source_type` int(3) NOT NULL DEFAULT 1 COMMENT '��λԴ���ͣ�1��GPS 2��BTS 3��WIFI��',
	`source_id` int(11) NOT NULL DEFAULT -1 COMMENT '��λԴID',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ 0�������� 1:����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 -- alter table lm_device_location add column inner_cn int(3) NOT NULL DEFAULT 0 COMMENT '�Ƿ����(0:�� 1����)' after radius ;

CREATE TABLE IF NOT EXISTS lm_trans_order (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `device_number` varchar(32) NOT NULL COMMENT '�豸���',
  `carrier_number` varchar(32) DEFAULT NULL COMMENT '����������',
	`bill_number` varchar(32) DEFAULT NULL UNIQUE COMMENT '�ᵥ��',
  `origin` varchar(32) NOT NULL COMMENT '��ʼ��',
	`destination` varchar(32) NOT NULL COMMENT 'Ŀ�ĵ�',
	`goods_name` varchar(64) NOT NULL COMMENT '��������',
	`carton_type` varchar(10) NOT NULL COMMENT '���ͣ�20GP��40GP��40HQ��',
	`carton_owner` int(3) DEFAULT NULL COMMENT '��װ�����(1:��� 2������)',
	`hs_code` varchar(32) DEFAULT NULL COMMENT 'HS����',
	`vehicle_type` int(3) DEFAULT NULL COMMENT '����',
  `transit_port` varchar(64) DEFAULT NULL COMMENT '�����ڰ�',
  `service_term` varchar(32) DEFAULT NULL COMMENT '��������',
  `customer` varchar(32) DEFAULT NULL COMMENT '����ͻ�',
	`location_freq_type` int(3) DEFAULT NULL COMMENT '��λƵ�����ͣ�1������ 2��Сʱ 3���죩',
  `location_freq` int(11) DEFAULT NULL COMMENT '��λƵ��',
  `trans_mode` int(3) DEFAULT NULL COMMENT '����ģʽ(0���� 1������ 2���ɻ� 3���ִ���',
	`trans_status` int(3) DEFAULT NULL COMMENT '����״̬(-1 �����쳣 1�������� 2������ɣ�',
  `start_time` bigint(11) DEFAULT NULL COMMENT '��ʼʱ��',
  `finish_time` bigint(11) DEFAULT NULL COMMENT '���ʱ��',
  `status` int(3) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_trans_order_oper (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL COMMENT '�豸���',
  `oper_status` int(3) NOT NULL COMMENT '����״̬��1����ת 2����� 3����',
	`oper_time` bigint(11) NOT NULL COMMENT '����ʱ��',
	`station` varchar(32) DEFAULT NULL COMMENT 'վ�����',
  `status` int(3) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS lm_company (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(32) NOT NULL UNIQUE COMMENT '��˾���',
  `name` varchar(128) DEFAULT NULL COMMENT '��˾����',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ 0�������� 1:����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS lm_city (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(32) NOT NULL UNIQUE COMMENT '���б��',
  `name` varchar(255) NOT NULL COMMENT '��������',
  `name_en` varchar(255) DEFAULT NULL COMMENT '��������(Ӣ��)',
	`country_id` int(11) NOT NULL COMMENT '�������ڹ���ID',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ 0�������� 1:����',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_company_city (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NOT NULL COMMENT '��˾ID',
  `city_id` int(11) NOT NULL COMMENT '����ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_device_location_bts (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `imei` VARCHAR(15) NOT NULL COMMENT '�豸IMEI',
  `mcc` varchar(10) NOT NULL COMMENT '�ƶ��û��������Ҵ���',
  `mnc` varchar(10) NOT NULL COMMENT '�ƶ�������',
  `lac1` varchar(10) NOT NULL COMMENT 'λ������',
  `ci1` varchar(10) DEFAULT NULL COMMENT '�ƶ���վ',
  `rssi1` varchar(10) DEFAULT NULL COMMENT '�ź�ǿ��',
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
  `time` BIGINT(11) NOT NULL COMMENT 'ʱ��',
	`status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ 0�������� 1:����',
  `handle_flag` int(3) DEFAULT 0 COMMENT '�Ƿ��ѱ�������0�� 1��',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_device_location_gps (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imei` varchar(15) NOT NULL COMMENT '�豸IMEI',
  `longitude` decimal(12,8) NOT NULL COMMENT '����',
  `latitude` decimal(12,8) NOT NULL COMMENT 'γ��',
	`location_flag` int(3) DEFAULT NULL COMMENT '��λ��ʶ',
	`speed` int(11) DEFAULT NULL COMMENT '�ٶ� ��λ2��',
	`direction` int(11) DEFAULT NULL COMMENT '���� ��λ10��',
	`factor` decimal(9,5) DEFAULT NULL COMMENT 'HDOPˮƽ������������',
  `time` BIGINT(11) NOT NULL COMMENT 'ʱ��',
	`status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ 0�������� 1:����',
  `handle_flag` int(3) DEFAULT 0 COMMENT '�Ƿ��ѱ�������0�� 1��',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE IF NOT EXISTS lm_device_change_status (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT, 
  `device_id` int(11) NOT NULL COMMENT '�豸ID',
	`from_status` int(3) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:���� 2:ʹ���� 3��������',
	`to_status` int(3) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:���� 2:ʹ���� 3��������',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_device_cmd (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `imei` varchar(32) NOT NULL COMMENT '�豸���',
  `model` varchar(16) NOT NULL COMMENT '�豸�ͺ�',
  `function` varchar(16) NOT NULL COMMENT 'ָ���',
  `function_desc` varchar(16) DEFAULT NULL COMMENT 'ָ�������',
  `data` varchar(256) DEFAULT NULL COMMENT 'ָ������',
  `save` int(3) NOT NULL DEFAULT 0 COMMENT '�Ƿ���Ҫ����',
  `reply` int(3) NOT NULL DEFAULT 0 COMMENT '�Ƿ���Ҫ�ظ�',
	`command` varchar(32) DEFAULT NULL COMMENT 'ָ���·�����', 
	`exec_mode` int(3) NOT NULL DEFAULT 1 COMMENT 'ִ��ģʽ 1������ִ�� 2:��ʱִ��',
  `exec_cron` varchar(32) DEFAULT NULL COMMENT 'ִ�мƻ�',
	`exec_status` int(3) NOT NULL DEFAULT 0 COMMENT 'ִ��״̬ 0��δִ�� 1:ִ���� 2���ɹ��ɹ� 3��ִ��ʧ�� 4��ִ�й��� 5����ִ��', 
  `exec_count` int(3) NOT NULL DEFAULT 0 COMMENT 'ִ�д���', 
	`exec_time` bigint(11) DEFAULT NULL COMMENT 'ִ��ʱ��',
  `status` int(3) NOT NULL DEFAULT 0 COMMENT '״̬ 0�������� 1:����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_device_location_semi (
	`id` int(11) unsigned NOT NULL AUTO_INCREMENT, 
  `imei` VARCHAR(15) NOT NULL COMMENT '�豸IMEI',
	`longitude` decimal(12,8) NOT NULL COMMENT '����',
  `latitude` decimal(12,8) NOT NULL COMMENT 'γ��',
	`radius` int(11) DEFAULT 0 COMMENT '���ȣ��뾶�����ף�',
	`distance` int(11) COMMENT '��ǰ�����(m)',
  `address` varchar(255) DEFAULT NULL COMMENT '��ַ',
  `inner_cn` int(3) NOT NULL DEFAULT 0 COMMENT '�Ƿ����(0:�� 1����)',
  `time_begin` bigint(11) NOT NULL COMMENT '��ʼʱ��',
	`time_end` bigint(11) NOT NULL COMMENT '����ʱ��',
	`source_type` int(3) NOT NULL DEFAULT 1 COMMENT '��λԴ���ͣ�1��GPS 2��BTS 3��WIFI��',
	`source_id` int(11) NOT NULL DEFAULT -1 COMMENT '��λԴID',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ 0�������� 1:����',
  `handle_status` int(3) DEFAULT 0 COMMENT '����״̬��0δ���� 1�����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
	PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

 
CREATE TABLE IF NOT EXISTS lm_station (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(32) NOT NULL UNIQUE COMMENT '��վ���',
  `name` varchar(255) NOT NULL COMMENT '��վ����',
  `name_en` varchar(255) DEFAULT NULL COMMENT '��������(Ӣ��)',
	`city` varchar(128) NOT NULL COMMENT '���ڳ���ID',
	`country` varchar(128) NOT NULL COMMENT '�������ڹ���ID',
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ 0�������� 1:����',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_trans_order_statistics ( 
  `origin_city` int(11)NOT NULL COMMENT '��ʼ�����ڳ���ID',
  `origin_country` int(11) NOT NULL COMMENT '��ʼ�����ڹ���ID',
	`dest_city` int(11) NOT NULL COMMENT '��ʼ�����ڳ���ID',
  `dest_country` int(11) NOT NULL COMMENT '��ʼ�����ڹ���ID', 
	`goods_name` varchar(64) NOT NULL COMMENT '��������',
	`carton_type` varchar(10) NOT NULL COMMENT '���ͣ�20GP��40GP��40HQ��',
	`carton_owner` int(3) DEFAULT NULL COMMENT '��װ�����(1:��� 2������)', 
	`vehicle_type` int(3) DEFAULT NULL COMMENT '����',
  `transit_port` varchar(64) DEFAULT NULL COMMENT '�����ڰ�', 
  `trans_mode` int(3) DEFAULT NULL COMMENT '����ģʽ(0���� 1������ 2���ɻ� 3���ִ���',
	`trans_status` int(3) DEFAULT NULL COMMENT '����״̬(-1 �����쳣 1�������� 2������ɣ�',
  `start_day` int(11) DEFAULT NULL COMMENT '��ʼʱ��(���ڣ���20180323',
  `finish_day` int(11) DEFAULT NULL COMMENT '���ʱ��(���ڣ���20180323', 
  `company_id` int(11) NOT NULL COMMENT '��˾ID',
	`order_id` int(11) NOT NULL COMMENT '����ID',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS lm_country (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(32) NOT NULL UNIQUE COMMENT '���б��',
  `name` varchar(255) NOT NULL COMMENT '��������',
  `name_en` varchar(255) DEFAULT NULL COMMENT '��������(Ӣ��)', 
  `status` int(3) NOT NULL DEFAULT 1 COMMENT '״̬ 0�������� 1:����',
	`remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `creator_id` int(11) NOT NULL COMMENT '������',
  `create_time` bigint(11) NOT NULL COMMENT '����ʱ��',
  `updator_id` int(11) DEFAULT NULL COMMENT '������',
  `update_time` bigint(11) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;