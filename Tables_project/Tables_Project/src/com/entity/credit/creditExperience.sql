drop table credit_experience1
CREATE temporary TABLE `credit_experience1` (
id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
date int(10) unsigned not NULL,
1001_total_click   int(10) unsigned Default NULL,
1001_total_usr_num  int(10) unsigned Default NULL,
1001_ratio  int(10) unsigned Default NULL,
1002_total_click  int(10) unsigned Default NULL,
1002_total_usr_num  int(10) unsigned Default NULL,
1002_ratio  int(10) unsigned Default NULL,
1003_total_click  int(10) unsigned Default NULL,
1003_total_usr_num  int(10) unsigned Default NULL,
1003_ratio  int(10) unsigned Default NULL,
1004_total_click  int(10) unsigned Default NULL,
1004_total_usr_num  int(10) unsigned Default NULL,
1004_ratio  int(10) unsigned Default NULL,
1005_total_click  int(10) unsigned Default NULL,
1005_total_usr_num  int(10) unsigned Default NULL,
1005_ratio  int(10) unsigned Default NULL,
1006_total_click  int(10) unsigned Default NULL,
1006_total_usr_num  int(10) unsigned Default NULL,
1006_ratio  int(10) unsigned Default NULL,
1007_total_click  int(10) unsigned Default NULL,
1007_total_usr_num  int(10) unsigned Default NULL,
1007_ratio  int(10) unsigned Default NULL,
 PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币用户体验数据表'


select * from credit_experience1

call creditExperience1(20140430)

delete from credit_experience1 







drop procedure creditExperience1
CREATE PROCEDURE `CreditExperience1`(in todaydate INTEGER)
BEGIN
set @inputDate:=todaydate;
set @inter6:=concat("
insert credit_experience1(date,1001_total_click,1001_total_usr_num,1001_ratio,1002_total_click,1002_total_usr_num,1002_ratio,1003_total_click,1003_total_usr_num,1003_ratio,1004_total_click,1004_total_usr_num,1004_ratio,1005_total_click,1005_total_usr_num,1005_ratio,1006_total_click,1006_total_usr_num,1006_ratio,1007_total_click,1007_total_usr_num,1007_ratio)
select ",@inputDate,",
sum(case function when 1001 then use_count else 0 end) AS 1001_total_click,
sum(case function when 1001 then user_num else 0 end)as 1001_total_usr_num,
(sum(case function when 1001 then use_count else 0 end)/sum(case function when 1001 then user_num else 0 end) )as 1001_ratio,
sum(case function when 1002 then use_count else 0 end) AS 1002_total_click,
sum(case function when 1002 then user_num else 0 end)as 1002_total_usr_num,
(sum(case function when 1002 then use_count else 0 end)/sum(case function when 1002 then user_num else 0 end) )as 1002_ratio,
sum(case function when 1003 then use_count else 0 end) AS 1003_total_click,
sum(case function when 1003 then user_num else 0 end)as 1003_total_usr_num,
(sum(case function when 1003 then use_count else 0 end)/sum(case function when 1003 then user_num else 0 end) )as 1003_ratio,
sum(case function when 1004 then use_count else 0 end) AS 1004_total_click,
sum(case function when 1004 then user_num else 0 end)as 1004_total_usr_num,
(sum(case function when 1004 then use_count else 0 end)/sum(case function when 1004 then user_num else 0 end) )as 1004_ratio,
sum(case function when 1005 then use_count else 0 end) AS 1005_total_click,
sum(case function when 1005 then user_num else 0 end)as 1005_total_usr_num,
(sum(case function when 1005 then use_count else 0 end)/sum(case function when 1005 then user_num else 0 end) )as 1005_ratio,
sum(case function when 1006 then use_count else 0 end) AS 1006_total_click,
sum(case function when 1006 then user_num else 0 end)as 1006_total_usr_num,
(sum(case function when 1006 then use_count else 0 end)/sum(case function when 1006 then user_num else 0 end) )as 1006_ratio,
sum(case function when 1007 then use_count else 0 end) AS 1007_total_click,
sum(case function when 1007 then user_num else 0 end)as 1007_total_usr_num,
(sum(case function when 1007 then use_count else 0 end)/sum(case function when 1007 then user_num else 0 end) )as 1007_ratio
    from(
    select sum(a.times)as use_count,count(distinct member_id) as user_num,module,function,date 
    from (select times,member_id ,module,function,DATE_FORMAT(stampdate, '%y%m%d')as date from money.hotspot)as a group by a.date,a.module,a.function)as a where a.date=",@inputdate);
prepare stmt from @inter6;
execute stmt;
END




call creditExperience1(20140430)


######################################################


drop table credit_experience11
CREATE temporary TABLE `credit_experience11` (
id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
date int(10) unsigned NOT NULL,
11001_total_click  int(10) unsigned default NULL,
11001_total_usr_num  int(10) unsigned default NULL,
11001_ratio  int(10) unsigned default NULL,
11002_total_click  int(10) unsigned default NULL,
11002_total_usr_num  int(10) unsigned default NULL,
11002_ratio  int(10) unsigned default NULL,
11003_total_click  int(10) unsigned default NULL,
11003_total_usr_num  int(10) unsigned default NULL,
11003_ratio  int(10) unsigned default NULL,
11004_total_click  int(10) unsigned default NULL,
11004_total_usr_num  int(10) unsigned default NULL,
11004_ratio  int(10) unsigned default NULL,
11005_total_click  int(10) unsigned default NULL,
11005_total_usr_num  int(10) unsigned default NULL,
11005_ratio  int(10) unsigned default NULL,
11006_total_click  int(10) unsigned default NULL,
11006_total_usr_num  int(10) unsigned default NULL,
11006_ratio  int(10) unsigned default NULL,
 PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币用户体验数据表'




drop procedure creditExperience11
CREATE PROCEDURE `CreditExperience11`(in todaydate INTEGER)
BEGIN
set @inputDate:=todaydate;
set @inter6:=concat("
insert credit_experience11(date,11001_total_click,11001_total_usr_num,11001_ratio,11002_total_click,11002_total_usr_num,11002_ratio,11003_total_click,11003_total_usr_num,11003_ratio,11004_total_click,11004_total_usr_num,11004_ratio,11005_total_click,11005_total_usr_num,11005_ratio,11006_total_click,11006_total_usr_num,11006_ratio)
select ",@inputDate,",
sum(case function when 11001 then use_count else 0 end) AS 11001_total_click,
sum(case function when 11001 then user_num else 0 end)as 11001_total_usr_num,
(sum(case function when 11001 then use_count else 0 end)/sum(case function when 11001 then user_num else 0 end) )as 11001_ratio,
sum(case function when 11002 then use_count else 0 end) AS 11002_total_click,
sum(case function when 11002 then user_num else 0 end)as 11002_total_usr_num,
(sum(case function when 11002 then use_count else 0 end)/sum(case function when 11002 then user_num else 0 end) )as 11002_ratio,
sum(case function when 11003 then use_count else 0 end) AS 11003_total_click,
sum(case function when 11003 then user_num else 0 end)as 11003_total_usr_num,
(sum(case function when 11003 then use_count else 0 end)/sum(case function when 11003 then user_num else 0 end) )as 11003_ratio,
sum(case function when 11004 then use_count else 0 end) AS 11004_total_click,
sum(case function when 11004 then user_num else 0 end)as 11004_total_usr_num,
(sum(case function when 11004 then use_count else 0 end)/sum(case function when 11004 then user_num else 0 end) )as 11004_ratio,
sum(case function when 11005 then use_count else 0 end) AS 11005_total_click,
sum(case function when 11005 then user_num else 0 end)as 11005_total_usr_num,
(sum(case function when 11005 then use_count else 0 end)/sum(case function when 11005 then user_num else 0 end) )as 11005_ratio,
sum(case function when 11006 then use_count else 0 end) AS 11006_total_click,
sum(case function when 11006 then user_num else 0 end)as 11006_total_usr_num,
(sum(case function when 11006 then use_count else 0 end)/sum(case function when 11006 then user_num else 0 end) )as 11006_ratio

    from(
    select sum(a.times)as use_count,count(distinct member_id) as user_num,module,function,date 
    from (select times,member_id ,module,function,DATE_FORMAT(stampdate, '%y%m%d')as date from money.hotspot)as a group by a.date,a.module,a.function)as a where a.date=",@inputdate);
prepare stmt from @inter6;
execute stmt;
END

select * from credit_experience11
call creditExperience11(20140423)



######################################################
drop table credit_experience1213
CREATE temporary TABLE `credit_experience1213` (
id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
date int(10) unsigned Not NULL,
12001_total_click  int(10) unsigned  default NULL,
12001_total_usr_num  int(10) unsigned default  NULL,
12001_ratio  int(10) unsigned default NULL,
12002_total_click  int(10) unsigned default NULL,
12002_total_usr_num  int(10) unsigned default NULL,
12002_ratio  int(10) unsigned default NULL,
12003_total_click  int(10) unsigned default NULL,
12003_total_usr_num  int(10) unsigned default NULL,
12003_ratio  int(10) unsigned default NULL,
12004_total_click  int(10) unsigned default NULL,
12004_total_usr_num  int(10) unsigned default NULL,
12004_ratio  int(10) unsigned default NULL,
13001_total_click  int(10) unsigned default NULL,
13001_total_usr_num  int(10) unsigned default NULL,
13001_ratio  int(10) unsigned default NULL,
13002_total_click  int(10) unsigned default NULL,
13002_total_usr_num  int(10) unsigned default NULL,
13002_ratio  int(10) unsigned default NULL,
 PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币用户体验数据表'



drop procedure creditExperience1213
CREATE PROCEDURE `CreditExperience1213`(in todaydate INTEGER)
BEGIN
set @inputDate:=todaydate;
set @inter6:=concat("
insert credit_experience1213(date,12001_total_click,12001_total_usr_num,12001_ratio,12002_total_click,12002_total_usr_num,12002_ratio,12003_total_click,12003_total_usr_num,12003_ratio,12004_total_click,12004_total_usr_num,12004_ratio,13001_total_click,13001_total_usr_num,13001_ratio,13002_total_click,13002_total_usr_num,13002_ratio)
select ",@inputDate,",
sum(case function when 12001 then use_count else 0 end) AS 12001_total_click,
sum(case function when 12001 then user_num else 0 end)as 12001_total_usr_num,
(sum(case function when 12001 then use_count else 0 end)/sum(case function when 12001 then user_num else 0 end) )as 12001_ratio,
sum(case function when 12002 then use_count else 0 end) AS 12002_total_click,
sum(case function when 12002 then user_num else 0 end)as 12002_total_usr_num,
(sum(case function when 12002 then use_count else 0 end)/sum(case function when 12002 then user_num else 0 end) )as 12002_ratio,
sum(case function when 12003 then use_count else 0 end) AS 12003_total_click,
sum(case function when 12003 then user_num else 0 end)as 12003_total_usr_num,
(sum(case function when 12003 then use_count else 0 end)/sum(case function when 12003 then user_num else 0 end) )as 12003_ratio,
sum(case function when 12004 then use_count else 0 end) AS 12004_total_click,
sum(case function when 12004 then user_num else 0 end)as 12004_total_usr_num,
(sum(case function when 12004 then use_count else 0 end)/sum(case function when 12004 then user_num else 0 end) )as 12004_ratio,
sum(case function when 13001 then use_count else 0 end) AS 13001_total_click,
sum(case function when 13001 then user_num else 0 end)as 13001_total_usr_num,
(sum(case function when 13001 then use_count else 0 end)/sum(case function when 13001 then user_num else 0 end) )as 13001_ratio,
sum(case function when 13002 then use_count else 0 end) AS 13002_total_click,
sum(case function when 13002 then user_num else 0 end)as 13002_total_usr_num,
(sum(case function when 13002 then use_count else 0 end)/sum(case function when 13002 then user_num else 0 end) )as 13002_ratio

    from(
    select sum(a.times)as use_count,count(distinct member_id) as user_num,module,function,date 
    from (select times,member_id ,module,function,DATE_FORMAT(stampdate, '%y%m%d')as date from money.hotspot)as a group by a.date,a.module,a.function)as a where a.date=",@inputdate);
prepare stmt from @inter6;
execute stmt;
END


call creditExperience1213(20140427)

select * from credit_experience1213



##################################################################################################

drop table credit_experience14
CREATE temporary TABLE `credit_experience14` (
id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
date int(10) unsigned Not null,
14001_total_click  int(10) unsigned ,
14001_total_usr_num  int(10) unsigned ,
14001_ratio  int(10) unsigned ,
14002_total_click  int(10) unsigned ,
14002_total_usr_num  int(10) unsigned ,
14002_ratio  int(10) unsigned ,
14003_total_click  int(10) unsigned ,
14003_total_usr_num  int(10) unsigned ,
14003_ratio  int(10) unsigned ,
14004_total_click  int(10) unsigned ,
14004_total_usr_num  int(10) unsigned ,
14004_ratio  int(10) unsigned ,
140041_total_click  int(10) unsigned ,
140041_total_usr_num  int(10) unsigned ,
140041_ratio  int(10) unsigned ,
140042_total_click  int(10) unsigned ,
140042_total_usr_num  int(10) unsigned ,
140042_ratio  int(10) unsigned ,
140043_total_click  int(10) unsigned ,
140043_total_usr_num  int(10) unsigned ,
140043_ratio  int(10) unsigned ,
14005_total_click  int(10) unsigned ,
14005_total_usr_num  int(10) unsigned ,
14005_ratio  int(10) unsigned ,
14006_total_click  int(10) unsigned ,
14006_total_usr_num  int(10) unsigned ,
14006_ratio  int(10) unsigned ,
14007_total_click  int(10) unsigned ,
14007_total_usr_num  int(10) unsigned ,
14007_ratio  int(10) unsigned ,
14008_total_click  int(10) unsigned ,
14008_total_usr_num  int(10) unsigned ,
14008_ratio  int(10) unsigned ,
14009_total_click  int(10) unsigned ,
14009_total_usr_num  int(10) unsigned ,
14009_ratio  int(10) unsigned ,
PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币用户体验数据表'



drop procedure creditExperience14
CREATE PROCEDURE `CreditExperience14`(in todaydate INTEGER)
BEGIN
set @inputDate:=todaydate;
set @inter6:=concat("
insert credit_experience14(date,14001_total_click,14001_total_usr_num,14001_ratio,14002_total_click,14002_total_usr_num,14002_ratio,14003_total_click,14003_total_usr_num,14003_ratio,14004_total_click,14004_total_usr_num,14004_ratio,140041_total_click,140041_total_usr_num,140041_ratio,140042_total_click,140042_total_usr_num,140042_ratio,140043_total_click,140043_total_usr_num,140043_ratio,14005_total_click,14005_total_usr_num,14005_ratio,14006_total_click,14006_total_usr_num,14006_ratio,14007_total_click,14007_total_usr_num,14007_ratio,14008_total_click,14008_total_usr_num,14008_ratio,14009_total_click,14009_total_usr_num,14009_ratio)
select ",@inputDate,",
sum(case function when 14001 then use_count else 0 end) as 14001_total_click,
sum(case function when 14001 then user_num else 0 end)as 14001_total_usr_num,
(sum(case function when 14001 then use_count else 0 end)/sum(case function when 14001 then user_num else 0 end) )as 14001_ratio,
sum(case function when 14002 then use_count else 0 end) AS 14002_total_click,
sum(case function when 14002 then user_num else 0 end)as 14002_total_usr_num,
(sum(case function when 14002 then use_count else 0 end)/sum(case function when 14002 then user_num else 0 end) )as 14002_ratio,
sum(case function when 14003 then use_count else 0 end) AS 14003_total_click,
sum(case function when 14003 then user_num else 0 end)as 14003_total_usr_num,
(sum(case function when 14003 then use_count else 0 end)/sum(case function when 14003 then user_num else 0 end) )as 14003_ratio,
sum(case function when 14004 then use_count else 0 end) AS 14004_total_click,
sum(case function when 14004 then user_num else 0 end)as 14004_total_usr_num,
(sum(case function when 14004 then use_count else 0 end)/sum(case function when 14004 then user_num else 0 end) )as 14004_ratio,
sum(case function when 140041 then use_count else 0 end) AS 140041_total_click,
sum(case function when 140041 then user_num else 0 end)as 140041_total_usr_num,
(sum(case function when 140041 then use_count else 0 end)/sum(case function when 140041 then user_num else 0 end) )as 140041_ratio,
sum(case function when 140042 then use_count else 0 end) AS 140042_total_click,
sum(case function when 140042 then user_num else 0 end)as 140042_total_usr_num,
(sum(case function when 140042 then use_count else 0 end)/sum(case function when 140042 then user_num else 0 end) )as 140042_ratio,
sum(case function when 140043 then use_count else 0 end) AS 140043_total_click,
sum(case function when 140043 then user_num else 0 end)as 140043_total_usr_num,
(sum(case function when 140043 then use_count else 0 end)/sum(case function when 140043 then user_num else 0 end) )as 140043_ratio,
sum(case function when 14005 then use_count else 0 end) AS 14005_total_click,
sum(case function when 14005 then user_num else 0 end)as 14005_total_usr_num,
(sum(case function when 14005 then use_count else 0 end)/sum(case function when 14005 then user_num else 0 end) )as 14005_ratio,
sum(case function when 14006 then use_count else 0 end) AS 14006_total_click,
sum(case function when 14006 then user_num else 0 end)as 14006_total_usr_num,
(sum(case function when 14006 then use_count else 0 end)/sum(case function when 14006 then user_num else 0 end) )as 14006_ratio,
sum(case function when 14007 then use_count else 0 end) AS 14007_total_click,
sum(case function when 14007 then user_num else 0 end)as 14007_total_usr_num,
(sum(case function when 14007 then use_count else 0 end)/sum(case function when 14007 then user_num else 0 end) )as 14007_ratio,
sum(case function when 14008 then use_count else 0 end) AS 14008_total_click,
sum(case function when 14008 then user_num else 0 end)as 14008_total_usr_num,
(sum(case function when 14008 then use_count else 0 end)/sum(case function when 14008 then user_num else 0 end) )as 14008_ratio,
sum(case function when 14009 then use_count else 0 end) AS 14009_total_click,
sum(case function when 14009 then user_num else 0 end)as 14009_total_usr_num,
(sum(case function when 14009 then use_count else 0 end)/sum(case function when 14009 then user_num else 0 end) )as 14009_ratio

    from(
    select sum(a.times)as use_count,count(distinct member_id) as user_num,module,function,date 
    from (select times,member_id ,module,function,DATE_FORMAT(stampdate, '%y%m%d')as date from money.hotspot)as a group by a.date,a.module,a.function)as a where a.date=",@inputdate);
prepare stmt from @inter6;
execute stmt;
END

call creditExperience14(20140430)

select * from credit_experience14

###################################################################################
insert credit_experience1(1001_total_click,1001_total_usr_num,1001_ratio,1002_total_click,1002_total_usr_num,1002_ratio,1003_total_click,1003_total_usr_num,1003_ratio,1004_total_click,1004_total_usr_num,1004_ratio,1005_total_click,1005_total_usr_num,1005_ratio,1006_total_click,1006_total_usr_num,1006_ratio,1007_total_click,1007_total_usr_num,1007_ratio)
insert credit_experience11(11001_total_click,11001_total_usr_num,11001_ratio,11002_total_click,11002_total_usr_num,11002_ratio,11003_total_click,11003_total_usr_num,11003_ratio,11004_total_click,11004_total_usr_num,11004_ratio,11005_total_click,11005_total_usr_num,11005_ratio,11006_total_click,11006_total_usr_num,11006_ratio)
insert credit_experience1213(12001_total_click,12001_total_usr_num,12001_ratio,12002_total_click,12002_total_usr_num,12002_ratio,12003_total_click,12003_total_usr_num,12003_ratio,12004_total_click,12004_total_usr_num,12004_ratio,13001_total_click,13001_total_usr_num,13001_ratio,13002_total_click,13002_total_usr_num,13002_ratio)
insert credit_experience14(14001_total_click,14001_total_usr_num,14001_ratio,14002_total_click,14002_total_usr_num,14002_ratio,14003_total_click,14003_total_usr_num,14003_ratio,14004_total_click,14004_total_usr_num,14004_ratio,140041_total_click,140041_total_usr_num,140041_ratio,140042_total_click,140042_total_usr_num,140042_ratio,140043_total_click,140043_total_usr_num,140043_ratio,14005_total_click,14005_total_usr_num,14005_ratio,14006_total_click,14006_total_usr_num,14006_ratio,14007_total_click,14007_total_usr_num,14007_ratio,14008_total_click,14008_total_usr_num,14008_ratio,14009_total_click,14009_total_usr_num,14009_ratio)


