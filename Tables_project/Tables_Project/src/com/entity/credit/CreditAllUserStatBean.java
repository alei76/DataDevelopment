package com.entity.credit;


import com.dao.hahaCal.EnumBeanFieldType;
import com.entity.haha.AnnotationGenSQL4Bean;
import com.sunxd.common.entity.BeanFactory;
import com.sunxd.common.entity.IMysqlBean;

/*�ñ�ĸ����ֶ�����ͨ��һ��sql�������ã�����ÿ���ֶβ�ѯ���õĽ�����������ܲ�һ������˻�����һ��sql�����ƴ�ӱȽϷ���*/
/**select * from tb1,tb2 where tb1.f1=tb2.f2, ��ֱ�Ӱ����ű����ѿ�����Ȼ����ѡ�����������Щ����inner join��Ч����һ����*/
public class CreditAllUserStatBean implements IMysqlBean
{
	/**��̬���ص�factory��map�� */
    static 
    {
	  BeanFactory.addFactory("credit.CreditAllUserStatBean", new Factory());
	}
    /**ע�������Լ��Ĺ�����map��*/
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
	/**���¿�ʼJAVABEAN*/
	private int date;
	private String vendor;//����
	private int online_count;//���ռ�⵽�������û���
	private int download_Count;//����������app������һ��������Ӧ�õ��û���
	private int active_count;//�������ٴ򿪹�һ��׬��ҵ��û�
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
	
	/**�������ʺ���һ��select���㶨����Ϊ������������ݣ��Ҹ�����֮���й���*/
	
	/**rst�Ƕ���:����ǣ������ʱ��������ڵ�ת�������Ҫѡ����ڽ����С�����������*/
	@AnnotationGenSQL4Bean(sql1="select distinct b.qid from money.lottery_data as a inner join money.member as b on a.member_id=b.id where a.inputtime>unix_timestamp('20140424') and a.inputtime<unix_timestamp('20140425')",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setVendor(String vendor){
		this.vendor=vendor;
	}
	public int getOnline_count(){
		return this.online_count;
	}
	/**rst�Ƕ��У�ÿ��qid��Ӧһ�����*/
	@AnnotationGenSQL4Bean(sql1="select b.qid,count(distinct a.member_id)  from lottery_data as a inner join money.member as b on a.member_id=b.id where a.inputtime>unix_timestamp('20140423') and a.inputtime<unix_timestamp('20140424')",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setOnline_count(int online_count){
		this.online_count=online_count;
	}
	public int getDownload_Count(){
		return this.download_Count;
	}
	
	//�������������޷���������ֶ�ƴ��һ��
	/**���������û�����member_app_data��usetype=4��ʾ����*/
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.member_id) from (money.member_app_data as a left join money.lottery_data as b on a.member_id=b.member_id) where a.usetype=4 and b.inputtime>unix_timestamp('20140423') and b.inputtime<unix_timestamp('20140424') group by b.qid",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setDownload_Count(int download_Count){
		this.download_Count=download_Count;
	}
	public int getActive_count(){
		return this.active_count;
	}
	/**�������ٴ򿪹�һ��׬��ҵ��û�:��member_app_data����κ�һ��useType���㣬���ֱ��count,���մ��û���Ϊ�����ֵ��Ӽ���*/
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.member_id) from (money.member_app_data as a left join money.member as b on a.member_id=b.id)group by b.qid",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setActive_count(int active_count){
		this.active_count=active_count;
	}
	public String getLaunch_count(){
		return this.launch_count;
	}
	/**���մ��û���:member_app_data��usetypeΪ2���ǲ����û�*/
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.member_id) from (money.member_app_data as a left join money.member as b on a.member_id=b.id) where a.usetype=2 group by b.qid",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setLaunch_count(String launch_count){
		this.launch_count=launch_count;
	}
}

