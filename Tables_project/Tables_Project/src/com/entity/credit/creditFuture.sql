#10000表示次日
#10001表示7日
#10002表示30日

PROCEDURE money.Creditfuture(IN indate INTEGER(10),prevDate INTEGER(10))
BEGIN
set @todayDate=20140429
set @today:=unix_timestamp(@todayDate)
set @prevMonth:=unix_timestamp(@todayDate)-86400*30;
set @prevWeek:=unix_timestamp(@todayDate)-86400*7;
set @prevDay:=unix_timestamp(@todayDate)-86400;
set @sqlday=concat("select from_unixtime(",@prevDay,",'%Y%m%d')as date,b.qid,10000,count(distinct a.member_id)as nextDay_active_count from (money.member_app_data as a right join money.member as b on a.member_id=b.id) where b.reg_date>@prevDay and b.reg_date<",@prevDay,"+86400 and a.inputtime>=",@today," and a.inputtime<",@today,"+86400 group by b.qid;");
set @sqlweek=concat("select from_unixtime(",@prevWeek,",'%Y%m%d')as date,b.qid,10000,count(distinct a.member_id)as nextDay_active_count from (money.member_app_data as a right join money.member as b on a.member_id=b.id) where b.reg_date>@prevDay and b.reg_date<",@prevDay,"+86400 and a.inputtime>=",@today," and a.inputtime<",@today,"+86400 group by b.qid;");
set @sqlmonth=concat("select from_unixtime(",@prevMonth,",'%Y%m%d')as date,b.qid,10000,count(distinct a.member_id)as nextDay_active_count from (money.member_app_data as a right join money.member as b on a.member_id=b.id) where b.reg_date>@prevDay and b.reg_date<",@prevDay,"+86400 and a.inputtime>=",@today," and a.inputtime<",@today,"+86400 group by b.qid;");
set @sql=concat(@sqlday,@sqlweek,@sqlmonth);
prepare stmt from @sql
execute stmt
END
