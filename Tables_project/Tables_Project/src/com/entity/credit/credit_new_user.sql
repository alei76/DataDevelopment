CREATE temporary TABLE `credit_new_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `date` int(10) unsigned DEFAULT NULL COMMENT '时间',
  `qid` varchar(10) DEFAULT NULL COMMENT '渠道',
  `install_count` int(10) unsigned DEFAULT NULL COMMENT '安装数',
  `active_count` int(10) unsigned DEFAULT NULL COMMENT '活跃数',
  `download_count` int(10) unsigned DEFAULT NULL COMMENT '下载数',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币新用户数据表'






drop procedure CreditNewUser 
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
call creditNewUser(20140423,20140424)


select * from credit_new_user