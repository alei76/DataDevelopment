package com.entity.credit;

import com.dao.hahaCal.EnumBeanFieldType;
import com.entity.haha.AnnotationGenSQL4Bean;
import com.sunxd.common.entity.IMysqlBean;

public class CreditUserExperienceStatBean implements IMysqlBean{
	
	private int click_count;  // 总点击次数
	private int click_usernum;// 总点击用户数
	
	
	@Override
	public String getSQL_insert() {
		// TODO Auto-generated method stub
		return null;
	}


	public int getClick_count() {
		return click_count;
	}

	@AnnotationGenSQL4Bean(sql1="select sum(times) from money.hotspot  where module=1.0 and function=1007",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setClick_count(int click_count) {
		this.click_count = click_count;
	}


	public int getClick_usernum() {
		return click_usernum;
	}

	@AnnotationGenSQL4Bean(sql1="select count(distinct member_id) from money.hotspot  where module=1.0 and function=1007",et=EnumBeanFieldType.INT, TbColumnName = "Time")
	public void setClick_usernum(int click_usernum) {
		this.click_usernum = click_usernum;
	}
	

}
