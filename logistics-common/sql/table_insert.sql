insert into lm_user(user_name,nick_name,passwd,type,company_id,status,creator_id,create_time)
values('admin','管理员','e833c14c7f490a431519cb0919407a7c',0,-1,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_user(user_name,nick_name,passwd,type,company_id,status,creator_id,create_time)
values('sen','Sen','e833c14c7f490a431519cb0919407a7c',1,1,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_user(user_name,nick_name,passwd,type,company_id,status,creator_id,create_time)
values('ivan','玉龙','e833c14c7f490a431519cb0919407a7c',1,1,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_user(user_name,nick_name,passwd,type,company_id,status,creator_id,create_time)
values('charlie','查理','e833c14c7f490a431519cb0919407a7c',1,1,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_user(user_name,nick_name,passwd,type,company_id,status,creator_id,create_time)
values('lemon','小柠檬','e833c14c7f490a431519cb0919407a7c',1,4,1,-1,UNIX_TIMESTAMP()*1000);

insert into lm_device(number,model,card_number,location_freq,status,creator_id,create_time)
values('867282030485128','T19','1064731432728',24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,location_freq,status,creator_id,create_time)
values('867282030482943','T19','1064731432726',24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,location_freq,status,creator_id,create_time)
values('867282030482927','T19','1064731432727',24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,location_freq,status,creator_id,create_time)
values('867282030482968','T19','1064731432730',24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,location_freq,status,creator_id,create_time)
values('867282030482976','T19','1064731432729',24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,location_freq,status,creator_id,create_time)
values('868500027750579','T128','1064731432721',24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,location_freq,status,creator_id,create_time)
values('868500027749522','T128','1064731432722',24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,location_freq,status,creator_id,create_time)
values('868500027747807','T128','1064731432723',24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,location_freq,status,creator_id,create_time)
values('868500027750249','T128','1064731432724',24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,location_freq,status,creator_id,create_time)
values('868500027752625','T128','1064731432725',24*60,1,-1,UNIX_TIMESTAMP()*1000);


insert into lm_company(number,name,status,creator_id,create_time)
values('1001','小柠檬科技有限公司',1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_company(number,name,status,creator_id,create_time)
values('10003','大洋物流公司',1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_company(number,name,status,creator_id,create_time)
values('10004','测试公司',1,-1,UNIX_TIMESTAMP()*1000);

insert into lm_device(number,model,card_number,company_id,location_freq,status,creator_id,create_time)
values('1002','T19','11111',(select id from lm_company where number='1001'),24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,company_id,location_freq,status,creator_id,create_time)
values('1003','T19','11111',(select id from lm_company where number='1001'),24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,company_id,location_freq,status,creator_id,create_time)
values('1004','T128','11111',(select id from lm_company where number='1001'),24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,company_id,location_freq,status,creator_id,create_time)
values('1005','T128','11111',(select id from lm_company where number='1001'),24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,company_id,location_freq,status,creator_id,create_time)
values('1006','T19','11111',(select id from lm_company where number='1001'),24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,company_id,location_freq,status,creator_id,create_time)
values('867282030747097','T19','89852241603300320916',(select id from lm_company where number='10003'),24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,company_id,location_freq,status,creator_id,create_time)
values('867282030745901','T19','89852241603300320882',(select id from lm_company where number='10003'),24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,company_id,location_freq,status,creator_id,create_time)
values('867282030745802','T19','89852241603300321013',(select id from lm_company where number='10003'),24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,company_id,location_freq,status,creator_id,create_time)
values('867282030746594','T19','89852241603300320908',(select id from lm_company where number='10003'),24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,company_id,location_freq,status,creator_id,create_time)
values('867282030747105','T19','89852241603300320890',(select id from lm_company where number='10003'),24*60,1,-1,UNIX_TIMESTAMP()*1000);

insert into lm_device(number,model,card_number,company_id,location_freq,status,creator_id,create_time)
values('867282030746081','T19','867282030746081',(select id from lm_company where number='10003'),24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,company_id,location_freq,status,creator_id,create_time)
values('867282030747154','T19','867282030747154',(select id from lm_company where number='10003'),24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,company_id,location_freq,status,creator_id,create_time)
values('867282030747147','T19','867282030747147',(select id from lm_company where number='10003'),24*60,1,-1,UNIX_TIMESTAMP()*1000);
insert into lm_device(number,model,card_number,company_id,location_freq,status,creator_id,create_time)
values('867282030748343','T19','867282030748343',(select id from lm_company where number='10003'),24*60,1,-1,UNIX_TIMESTAMP()*1000); 

insert into lm_city(name,status,creator_id,create_time)values('TKM_#_A',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TKM_#_B',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TKM_#_D',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TKM_#_L',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TKM_#_M',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TKM_#_ASB',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TKM_#_NEB',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_B',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_C',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_J',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_N',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_O',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_T',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_Y',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_GB',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_KJ',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_MS',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_SU',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_TK',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_UG',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_KAN',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_KBA',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KGZ_#_KKO',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('UZB_#_AN',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('UZB_#_BU',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('UZB_#_FA',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('UZB_#_JI',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('UZB_#_NG',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('UZB_#_NW',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('UZB_#_QA',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('UZB_#_QR',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('UZB_#_SA',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('UZB_#_SI',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('UZB_#_SU',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('UZB_#_TK',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('UZB_#_TO',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('UZB_#_XO',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_DYU',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_ISF',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_KAN',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_KHO',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_KHU',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_KLB',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_KOF',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_KTJ',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_NUR',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_PJK',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_RGU',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_SBA',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_TBS',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_TSZ',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('TJK_#_UTJ',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_AKM',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_AKS',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_AKT',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_ALA',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_ARY',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_AST',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_ATY',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_AYK',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_BXH',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_DMB',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_DZH',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_EKB',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_KAP',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_KAR',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_KEN',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_KGT',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_KST',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_KUR',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_KZO',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_KZY',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_LEN',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_LKK',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_MAN',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_PAV',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_RUD',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_SAK',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_SAR',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_SEM',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_SEV',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_STE',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_TEK',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_TEM',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_TUR',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_VOS',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_ZAP',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_ZHA',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('KAZ_#_ZYR',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('QAT_#_DW',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('QAT_#_GW',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('QAT_#_JB',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('QAT_#_JM',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('QAT_#_KR',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('QAT_#_MS',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(name,status,creator_id,create_time)values('QAT_#_RN',1,-1,CURRENT_TIMESTAMP());
insert into lm_city(number,name,status,remark,creator_id,create_time) values('1_36_7','1_36_7',1,'赣州',-1,CURRENT_TIMESTAMP());
insert into lm_city(number,name,status,remark,creator_id,create_time) values('1_32_7','1_32_7',1,'连云港',-1,CURRENT_TIMESTAMP());
insert into lm_city(number,name,status,remark,creator_id,create_time) values('UZB_#_TO','UZB_#_TO',1,'塔什干',-1,CURRENT_TIMESTAMP());


insert into lm_company_city(company_id,city_id,creator_id,create_time)
select (select id from lm_company where name='大洋运输'),id,-1,CURRENT_TIMESTAMP() from lm_city where create_time !=0