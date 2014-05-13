
drop table credit_basic

create temporary table credit_basic(`id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `date` int(10) unsigned DEFAULT NULL COMMENT '时间',
  `qid` varchar(10) DEFAULT NULL COMMENT '渠道',
  `launch_count` int(10) unsigned DEFAULT NULL COMMENT '服务启动量',
  `active_count` int(10) unsigned DEFAULT NULL COMMENT '活跃数',
  `download_count` int(10) unsigned DEFAULT NULL COMMENT '下载数',
  `third_count` int(10) unsigned DEFAULT NULL COMMENT '打开第三方应用',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币基础数据表'

drop procedure CreditBasic 
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
call CreditBasic(20140428,20140429)




select * from credit_basic
