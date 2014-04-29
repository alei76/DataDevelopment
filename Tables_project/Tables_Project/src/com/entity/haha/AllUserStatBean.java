package com.entity.haha;



import com.dao.hahaCal.EnumBeanFieldType;
import com.sunxd.common.entity.BeanFactory;

/**
*������MySQLToBean�����Զ�����
*��ע(���ݱ��comment�ֶ�)��haha�������û�ͳ�Ʊ�
*@author childlikeman@gmail.com,http://t.qq.com/lostpig
*@since 2014-04-03 17:22:57
*/

/*
CREATE TABLE `haha_all_user_stat` (
		  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
		  `Time` int(10) unsigned NOT NULL COMMENT 'ʱ��',
		  `LaunchCount` int(15) NOT NULL COMMENT '���շ����������û���',
		  `UninstallCount` int(10) NOT NULL COMMENT '�����û�ж������',
		  `ActiveCount` int(32) NOT NULL COMMENT '�����ܻ�Ծ��',
		  `UninstallRate` decimal(6,2) unsigned DEFAULT NULL COMMENT '������ж����',
		  `TotalVCU` int(32) NOT NULL COMMENT '��vcu����Ч�û�',
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='haha�������û�ͳ�Ʊ�'*/




public class AllUserStatBean implements IMysqlBean
{	
	private int Time;        //ʱ��
	private int LaunchCount;//���շ����������û���
	private int UninstallCount;//�����û�ж������
	private int ActiveCount;//�����ܻ�Ծ��
	private Double UninstallRate;////////?????????????????????????
	private int TotalVCU;//��vcu����Ч�û�
	
	public int getTime() {
		return Time;
	}

	@AnnotationGenSQL4Bean(sql1="select [?]",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setTime(int time) {
		Time = time;
	}


	@Override
	public String getSQL_insert() {
		// TODO Auto-generated method stub
		String insert_sql= "insert into haha_all_user_stat(Time,LaunchCount,UninstallCount,ActiveCount,UninstallRate,TotalVCU)" +
				" values(?,?,?,?,?,?)";
		return insert_sql;
	}
	
	
	public int getLaunchCount()
	{
		return this.LaunchCount;
	}
	
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid) from online[?] as a",et=EnumBeanFieldType.INT, TbColumnName = "LaunchCount") 
	// �Ѿ���֤��ע�⣬�����ʱ��Time�����ڣ���˲���Ҫ����online�����ʱ�䣬online�ı�������Ȼ������ʱ��
	public void setLaunchCount(int LaunchCount){
		this.LaunchCount=LaunchCount;
	}
	public int getUninstallCount(){
		return this.UninstallCount;
	}

	@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid) from uninstall[?] as a",et=EnumBeanFieldType.INT, TbColumnName = "UninstallCount")
	// �Ѿ���֤
	public void setUninstallCount(int UninstallCount){
		this.UninstallCount=UninstallCount;
	}
	public int getActiveCount(){
		return this.ActiveCount;
	}
	
	@AnnotationGenSQL4Bean(sql1="select count(distinct a.uid) from user_action[?] as a ",et=EnumBeanFieldType.INT, TbColumnName = "ActiveCount")
	// �Ѿ���֤
	public void setActiveCount(int ActiveCount){
		this.ActiveCount=ActiveCount;
	}
	public Double getUninstallRate(){
		return this.UninstallRate;
	}
	
	@AnnotationGenSQL4Bean(sql1="select (select count(distinct a.uid) from uninstall[?] as a)/100",et=EnumBeanFieldType.Double, TbColumnName = "UninstallRate")
	// ����������
	public void setUninstallRate(Double UninstallRate){
		this.UninstallRate=UninstallRate;
	}
	public int getTotalVCU(){
		return this.TotalVCU;
	}
	
	@AnnotationGenSQL4Bean(sql1="select count(b.uid) from (select  a.uid, sum(a.actionnum) as mycount from user_action[?] as a group by a.uid) b where b.mycount>2",et=EnumBeanFieldType.INT, TbColumnName = "TotalVCU")
	//����������������͵�һ�������vcu��ʲô�����أ�
	//                           select count(b.uid) from (select  a.uid, sum(a.actionnum) as mycount from user_action[?] as a group by a.uid) b where b.mycount>2
	public void setTotalVCU(int TotalVCU){
		this.TotalVCU=TotalVCU;
	}

	

}