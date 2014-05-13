CREATE TABLE `credit_third` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `date` int(10) unsigned DEFAULT NULL COMMENT '时间',
  `software_name` varchar(10) DEFAULT NULL COMMENT '软件名称',
  `download_count` int(10) unsigned DEFAULT NULL COMMENT '下载次数', 
  `auto_launch_num` int(10) unsigned DEFAULT NULL COMMENT '安装后自动打开次数',
  `self_launch_count` int(10) unsigned DEFAULT NULL COMMENT '用户主动打开次数',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='赚金币三方应用数据表'




drop procedure creditThird
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
call creditThrid(20140428,20140429)