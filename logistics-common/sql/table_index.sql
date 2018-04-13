
CREATE INDEX IX_DVCLOCSEMI_IMEI_STS_HSTS ON lm_device_location_semi(imei,status,handle_status);
CREATE INDEX IX_USER_USERNAME ON lm_user(user_name);
CREATE INDEX IX_USER_STATUS ON lm_user(status);
CREATE INDEX IX_USER_COMPANY ON lm_user(company_id);
CREATE INDEX IX_ROLE_STATUS ON lm_role(status);
CREATE INDEX IX_PERMISSION_TYPE ON lm_permission(type);
CREATE INDEX IX_PERMISSION_STATUS ON lm_permission(status);
CREATE INDEX IX_USERROLE_USERID ON lm_user_role(user_id);
CREATE INDEX IX_ROLEPERMISSION_ROLEID ON lm_role_permission(role_id);
CREATE INDEX IX_DEVICE_NUMBER ON lm_device(number);
CREATE INDEX IX_DEVICE_STATUS ON lm_device(status);
CREATE INDEX IX_DEVICEBATTERY_DVC_ORDER_STATUS ON lm_device_battery(device_id,order_id,status);
CREATE INDEX IX_DEVICELOCATION_DVC_ORDER_STATUS ON lm_device_location(device_id,order_id,status);
CREATE INDEX IX_TRANSORDER_DEVICE ON lm_trans_order(device_number);
CREATE INDEX IX_TRANSORDER_CARRIER ON lm_trans_order(carrier_number);
CREATE INDEX IX_TRANSORDER_BILL ON lm_trans_order(bill_number); 
CREATE INDEX IX_COMPANY_NUMBER ON lm_company(number); 
CREATE INDEX IX_COMPANY_STATUS ON lm_company(status);
CREATE INDEX IX_CITY_NUMBER ON lm_city(number); 
CREATE INDEX IX_CITY_STATUS ON lm_city(status);
CREATE INDEX IX_CITY_COUNTRY ON lm_city(country);
CREATE INDEX IX_COMPANYCITY_COMPANY ON lm_company_city(company_id);
CREATE INDEX IX_LOCATIONBTS_STATUS_TIME_FLAG ON lm_device_location_bts(status,time,handle_flag);
CREATE INDEX IX_LOCATIONBTS_IMEI ON lm_device_location_bts(imei);
CREATE INDEX IX_LOCATIONGPS_STATUS_TIME_FLAG ON lm_device_location_gps(status,time,handle_flag);
CREATE INDEX IX_LOCATIONGPS_IMEI ON lm_device_location_gps(imei);
CREATE INDEX IX_DEVICECMD_IMEI ON lm_device_cmd(imei);
CREATE INDEX IX_DEVICECMD_IMEI_CMD_EXESTS ON lm_device_cmd(imei,command,exec_status);
CREATE INDEX IX_DEVICECMD_EXESTS ON lm_device_cmd(exec_status);
CREATE INDEX IX_DEVICECHGSTATUS_DEVICE ON lm_device_change_status(device_id);
CREATE INDEX IX_TRANSORDEROPER_ORDER ON lm_trans_order_oper(order_id);
CREATE INDEX IX_STATION_NUMBER ON lm_station(number);