package com.entity.credit;


import com.dao.hahaCal.EnumBeanFieldType;
import com.entity.haha.AnnotationGenSQL4Bean;
import com.sunxd.common.entity.BeanFactory;
import com.sunxd.common.entity.IMysqlBean;

/*该表的各个字段难以通过一个sql语句来获得，但是每个字段查询后获得的结果的条数可能不一样，因此还是用一个sql语句来拼接比较方便*/
/**select * from tb1,tb2 where tb1.f1=tb2.f2, 则直接把两张表做笛卡尔积然后再选择有意义的那些，和inner join的效果是一样的*/
public class CreditAllUserStatBean implements IMysqlBean
{
	/**动态加载到factory的map里 */
    static 
    {
	  BeanFactory.addFactory("credit.CreditAllUserStatBean", new Factory());
	}
    /**注册属于自己的工厂到map里*/
	private static class Factory extends BeanFactory 
	{
	    protected IMysqlBean create() 
	    { 
	      return new CreditAllUserStatBean(); 
	    }
	}
	@Override
	public String getSQL_insert() {
		// TODO Auto-generated method stub
		String sql="select distinct b.qid,count(distinct a.member_id) from money.lottery_data as a inner join money.member as b on a.member_id=b.id where a.inputtime>unix_timestamp('20140424') and a.inputtime<unix_timestamp('20140425')";
		return sql;
	}
	/**以下开始JAVABEAN*/
	private int date;
	private String vendor;//渠道
	private int online_count;//当日检测到的在线用户数
	private int download_Count;//当日至少在app里下载一个第三方应用的用户数
	private int active_count;//当日至少打开过一次赚金币的用户
	private String launch_count;
	////////////////////////////////////////////////////////////////////////////
	public int getDate() {
		return date;
	}
	@AnnotationGenSQL4Bean(sql1="select [?]",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setDate(int date) {
		this.date = date;
	}
	public String getVendor(){
		return this.vendor;
	}
	
	/**该问题适合用一个select语句搞定，因为会产生多行数据，且各个行之间有关联*/
	
	/**rst是多行:这里牵扯到了时间戳到日期的转化，因此要选择大于今天和小于明天的日期*/
	@AnnotationGenSQL4Bean(sql1="select distinct b.qid from money.lottery_data as a inner join money.member as b on a.member_id=b.id where a.inputtime>unix_timestamp('20140424') and a.inputtime<unix_timestamp('20140425')",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setVendor(String vendor){
		this.vendor=vendor;
	}
	public int getOnline_count(){
		return this.online_count;
	}
	/**rst是多行：每个qid对应一个结果*/
	@AnnotationGenSQL4Bean(sql1="select b.qid,count(distinct a.member_id)  from lottery_data as a inner join money.member as b on a.member_id=b.id where a.inputtime>unix_timestamp('20140423') and a.inputtime<unix_timestamp('20140424')",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setOnline_count(int online_count){
		this.online_count=online_count;
	}
	public int getDownload_Count(){
		return this.download_Count;
	}
	
	//下面的这个动作无法和上面的字段拼在一起
	/**当日下载用户量：member_app_data里usetype=4表示下载*/
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.member_id) from (money.member_app_data as a left join money.lottery_data as b on a.member_id=b.member_id) where a.usetype=4 and b.inputtime>unix_timestamp('20140423') and b.inputtime<unix_timestamp('20140424') group by b.qid",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setDownload_Count(int download_Count){
		this.download_Count=download_Count;
	}
	public int getActive_count(){
		return this.active_count;
	}
	/**当日至少打开过一次赚金币的用户:在member_app_data里的任何一种useType都算，因此直接count,当日打开用户数为该数字的子集合*/
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.member_id) from (money.member_app_data as a left join money.member as b on a.member_id=b.id)group by b.qid",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setActive_count(int active_count){
		this.active_count=active_count;
	}
	public String getLaunch_count(){
		return this.launch_count;
	}
	/**当日打开用户数:member_app_data里usetype为2的那部分用户*/
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.member_id) from (money.member_app_data as a left join money.member as b on a.member_id=b.id) where a.usetype=2 group by b.qid",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setLaunch_count(String launch_count){
		this.launch_count=launch_count;
	}
}

