call CreditBasic(20140428,20140429)
call creditNewUser(20140423,20140424)
call creditTransaction(140430)
call creditThrid(20140428,20140429)
call creditExperience1(20140430)
call creditExperience14(20140430)
drop procedure CreditBasic 
drop procedure CreditNewUser 
drop procedure CreditTransaction
drop procedure creditThird
drop procedure creditExperience1
drop procedure creditExperience11
drop procedure creditExperience1213
drop procedure creditExperience14

, 

CREATE PROCEDURE `CreditBasic`(in todaydate INTEGER,tommorrow INTEGER)
BEGIN
set @inputDate:=todaydate;
set @toDate:=tommorrow;
set @sql4date:=concat("a.inputtime>unix_timestamp('",@inputDate,"') and a.inputtime<unix_timestamp('",@toDate,"')");
set @sql0:="select distinct b.qid from money.member as b";
set @sql1:=concat("select b.qid as qid,count(distinct a.member_id)as launch_count from lottery_data as a right join money.member as b on a.member_id=b.id where ",@sql4date);
set @sql2:=concat("select b.qid,count(distinct a.member_id)as active_count from (money.member_app_data as a left join money.member as b on a.member_id=b.id) where ",@sql4date," group by b.qid");
set @sql3:=concat("select b.qid,count(distinct a.member_id)as download_count from (money.member_app_data as a left join money.member as b on a.member_id=b.id) where a.usetype=4 and ",@sql4date," group by b.qid");
set @sql4:=concat("select b.qid,count(distinct a.member_id)as third_count from (money.member_app_data as a left join money.member as b on a.member_id=b.id) where a.usetype=2 and ",@sql4date," group by b.qid");
set @inter6:=concat("select ",@inputDate,",z.qid,a.launch_count,b.active_count,c.download_count,d.third_count from  (", @sql0 ,") as z left join  (", @sql1 ,") as a on z.qid=a.qid left join  (", @sql2 ,") as b on a.qid=b.qid left join  (", @sql3 ,") as c on b.qid=c.qid left join  (", @sql4 ,")  as d on c.qid=d.qid");
set @inter7:=concat("insert credit_basic(date,qid,launch_count,active_count,download_count,third_count)",@inter6);
prepare stmt from @inter7;
execute stmt;
END




create PROCEDURE `Creditfuture`(IN indate INTEGER)
BEGIN
set @todayDate:=indate;
set @today:=unix_timestamp(@todayDate);
set @prevMonth:=unix_timestamp(@todayDate)-86400*30;
set @prevWeek:=unix_timestamp(@todayDate)-86400*7;
set @prevDay:=unix_timestamp(@todayDate)-86400;
set @sqlday=concat("insert credit_future(date,qid,activetype,active_count) select from_unixtime(",@prevDay,",'%Y%m%d')as date,b.qid,10000,count(distinct a.member_id)as nextDay_active_count from (money.member_app_data as a right join money.member as b on a.member_id=b.id) where b.reg_date>@prevDay and b.reg_date<",@prevDay,"+86400 and a.inputtime>=",@today," and a.inputtime<",@today,"+86400 group by b.qid;");
set @sqlweek=concat("insert credit_future(date,qid,activetype,active_count) select from_unixtime(",@prevWeek,",'%Y%m%d')as date,b.qid,10001,count(distinct a.member_id)as nextDay_active_count from (money.member_app_data as a right join money.member as b on a.member_id=b.id) where b.reg_date>@prevDay and b.reg_date<",@prevDay,"+86400 and a.inputtime>=",@today," and a.inputtime<",@today,"+86400 group by b.qid;");
set @sqlmonth=concat("insert credit_future(date,qid,activetype,active_count) select from_unixtime(",@prevMonth,",'%Y%m%d')as date,b.qid,10002,count(distinct a.member_id)as nextDay_active_count from (money.member_app_data as a right join money.member as b on a.member_id=b.id) where b.reg_date>@prevDay and b.reg_date<",@prevDay,"+86400 and a.inputtime>=",@today," and a.inputtime<",@today,"+86400 group by b.qid;");
set @sql=concat(@sqlday,@sqlweek,@sqlmonth);
prepare stmt1 from @sqlday;
execute stmt1;
prepare stmt2 from @sqlweek;
execute stmt2;
prepare stmt3 from @sqlmonth;
execute stmt3;
END




CREATE PROCEDURE `CreditNewUser`(in todaydate INTEGER,tommorrow INTEGER)
BEGIN
set @inputDate:=todaydate;
set @toDate:=tommorrow;
set @sql0:="select distinct b.qid from money.member as b";
set @sql1=concat("select distinct a.qid,count(distinct id)as install_count from money.member as a where a.reg_date>unix_timestamp('",@inputDate,"') and a.reg_date<unix_timestamp('",@toDate,"')  group by a.qid");
set @sql2=concat("select b.qid,count(distinct a.member_id)as active_count from (money.member_app_data as a right join money.member as b on a.member_id=b.id) where b.reg_date>unix_timestamp('",@inputDate,"') and b.reg_date<unix_timestamp('",@toDate,"') group by b.qid");
set @sql3=concat("select b.qid,count(distinct a.member_id)as download_count from (money.member_app_data as a right join money.member as b on a.member_id=b.id) where a.usetype=4 and b.reg_date>unix_timestamp('",@inputDate,"') and b.reg_date<unix_timestamp('",@toDate,"') group by b.qid");
set @inter:=concat("select ",@inputDate,",z.qid as qid,a.install_count,b.active_count,c.download_count"," from  (", @sql0 ,")as z left join  (", @sql1 ,")as a on z.qid=a.qid left join  (", @sql2 ,")as b on a.qid=b.qid left join  (", @sql3 ,")as c on b.qid=c.qid");
set @inter1:=concat("insert credit_new_user(date,qid,install_count,active_count,download_count) ",@inter);
prepare stmt from @inter1;
execute stmt;
END










CREATE PROCEDURE `CreditTransaction`(in todaydate INTEGER)
BEGIN
set @inputDate:=todaydate;    
set @sql=concat(
"insert credit_transaction(date,raffle_count,raffle_user_num,issue_user_num,credit_issue,ali_point,ali_usernum,exchange_cell_usernum,exchange_cell_point_count,scheme1,scheme2,scheme3,scheme4,scheme5)
select a.date,a.raffle_count,a.raffle_user_num,b.issue_user_num,b.credit_issue,c.ali_point_count,c.aliuser_num,c.cell_user_num,c.cell_point_count,h.scheme1,h.scheme2,h.scheme3,h.scheme4,h.scheme5 from
(
(select FROM_UNIXTIME(inputtime,'%y%m%d') as date,count(a.member_id)as raffle_count,count(distinct a.member_id)as raffle_user_num from money.lottery_data as a where a.type=1 group by FROM_UNIXTIME(inputtime,'%y%m%d'))as a
left join
(select FROM_UNIXTIME(inputtime,'%y%m%d') as date,count(distinct a.member_id)as issue_user_num,sum(a.point) as credit_issue from money.member_point_data as a where a.type=1 and a.way=1  group by FROM_UNIXTIME(inputtime,'%y%m%d'))as b on a.date=b.date
left join
(select FROM_UNIXTIME(a.dateline,'%y%m%d')as date,sum(case b.type when 2 then b.cost else 0 end)as ali_point_count,count(distinct(case b.type when 2 then a.mid else 0 end))as aliuser_num,sum(case b.type when 1 then b.cost else 0 end)as cell_point_count,count(distinct (case b.type when 1 then a.mid else 0 end))as cell_user_num from money.exchange_order as a inner join money.exchange_item as b on a.item_id=b.id)as c on b.date=c.date
)left join
(select   FROM_UNIXTIME(a.inputtime,'%y%m%d')as date,
          sum(case a.difftype when 1 then b.point else 0 end) as scheme1,
          sum(case a.difftype when 2 then b.point else 0 end) as scheme2,
          sum(case a.difftype when 3 then b.point else 0 end) as scheme3,
          sum(case a.difftype when 4 then b.point else 0 end) as scheme4,
          sum(case a.difftype when 5 then b.point else 0 end) as scheme5
          from money.lottery_data as a inner join money.member_point_data as b on a.trade_id=b.trade_id group by FROM_UNIXTIME(a.inputtime,'%y%m%d'))as h on a.date=h.date where a.date=",@inputdate);
          
prepare stmt from @sql;
execute stmt;
END     






CREATE PROCEDURE `CreditThird`(in todaydate INTEGER,tommorrow INTEGER)
BEGIN
set @inputDate:=todaydate;
set @toDate:=tommorrow;
set @inter6:=concat("insert credit_third(date,software_name,download_count,auto_launch_num,self_launch_count)
select ",@inputDate,",a.app_name,sum(case a.usetype when 2 then 1 else 0 end) as download_count,
        sum(case a.usetype when 3 then 1 else 0 end) as autoLaunch,
        sum(case a.usetype when 4 then 1 else 0 end) as selfLaunch from money.member_app_data as a where a.inputtime>unix_timestamp('",@inputDate,"') and a.inputtime<unix_timestamp('",@toDate,"')group by a.app_name
 ");
prepare stmt from @inter6; 
execute stmt;
END















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



