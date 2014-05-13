call creditTransaction(140430)

drop procedure CreditTransaction

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