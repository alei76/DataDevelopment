package com.server.union;

import java.util.List;

import com.dao.union.PromotionAllDao;
import com.entity.union.Promotion_All;
import com.entity.union.Promotion_Install;

/**
 * 装机联盟总表
 * 逻辑业务层
 * @author ZhuYa
 *
 */
public class PromotionAllServer {
	PromotionAllDao pitDao = new PromotionAllDao();
	
	/**
	 * 返回装机联盟安装集合
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @return
	 */
	public Promotion_All getPromotionInstall(String db_ip,String user,String pwd,String db_name,String tab_name,String tab_name_01,String tab_name_07,String tab_name_30){
		Promotion_All objAll = new Promotion_All();
		//获取装机联盟1表 43库
		Promotion_All obj_01 = pitDao.getPromotionAll_01(db_ip, user, pwd, db_name, tab_name);
		//获取装机联盟2表 (45库 测试库)
		Promotion_All obj_02 = pitDao.getPromotionAll_02(db_ip, user, pwd, db_name, tab_name);
		//获取装机联盟3表 (45库 测试库)
		Promotion_All obj_03 = pitDao.getPromotionAll_03(db_ip, user, pwd, db_name, tab_name, tab_name_01, tab_name_07, tab_name_30);
		//获取装机联盟4表 (45库 测试库)
		Promotion_All obj_04 = pitDao.getPromotionAll_lostNum(db_ip, user, pwd, db_name, tab_name);
		//汇总
		objAll.setDateline(obj_01.getDateline());
		objAll.setFaFangNum(obj_01.getFaFangNum());
		objAll.setFaFangIntegrationNum(obj_01.getFaFangIntegrationNum());	
		objAll.setInstallationTodayNum(obj_01.getInstallationTodayNum());		
		objAll.setIntegrationNum(obj_01.getIntegrationNum());					
		objAll.setRegNum(obj_01.getRegNum());	
		objAll.setRegIntegrationNum(obj_01.getRegIntegrationNum());
		objAll.setUseNum(obj_01.getUseNum());
		objAll.setUseIntegrationNum(obj_01.getUseIntegrationNum());
		objAll.setDuihuanNum(obj_01.getDuihuanNum());
		objAll.setDhIntegrationNum(obj_01.getDhIntegrationNum());
		objAll.setTixianNum(obj_01.getTixianNum());
		objAll.setTxIntegrationNum(obj_01.getTxIntegrationNum());
		objAll.setIntAllIntegrationNum(obj_01.getIntAllIntegrationNum());
		objAll.setRegAllIntegrationNum(obj_01.getRegAllIntegrationNum());
		objAll.setInsAllIntegrationNum(obj_01.getInsAllIntegrationNum());
		objAll.setAteAllIntegrationNum(obj_01.getAteAllIntegrationNum());
		objAll.setActiveNum(obj_02.getActiveNum());		
		objAll.setSpreadNum(obj_02.getSpreadNum());
		objAll.setSpreadJiFenNum(obj_02.getSpreadJiFenNum());
		objAll.setConsumeNum(obj_02.getConsumeNum());
		objAll.setLandedNum(obj_02.getLandedNum());
		objAll.setDownloadNum(obj_02.getDownloadNum());
		objAll.setLostNum(obj_04.getLostNum());									// 流失用户
		objAll.setLoginNum(obj_03.getLoginNum());								// 当日注册用户数
		objAll.setRegInsActiveNum(obj_03.getRegInsActiveNum());		// 当日活跃用户数
		//分母不为0
		if(obj_03.getLoginNum()!=0){
			objAll.setActivepercentage(Math.round((obj_03.getRegInsActiveNum()*1.00/obj_03.getLoginNum())*100));	//当日活跃率 = 当日活跃用户数/当日注册用户数
		}else{
			objAll.setActivepercentage(0);
		}
		objAll.setNextRegInsActive(obj_03.getNextRegInsActive());			// 次日活跃用户数
		objAll.setNextActivePercentage(0);		// 次日活跃率
		objAll.setNext7DayRegInsActive(obj_03.getNext7DayRegInsActive());		// 7日活跃用户数 
		objAll.setNext7DayPercentage(0);		// 7日活跃率
		objAll.setNext30DayRegInsActive(obj_03.getNext30DayRegInsActive());	// 30日活跃用户数 
		objAll.setNext30DayPercentage(0);		// 30日活跃率
		return objAll;
	}
	
	
	/**
	 * 插入装机联盟 总表(45库 测试库)
	 * @param lists
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 */
	public void insertPromotionAll(Promotion_All obj,String db_ip,String user,String pwd,String db_name){
		if(obj != null){
			pitDao.insert_PromotionAll(obj, db_ip, user, pwd, db_name);
		}
	}
	
	/**
	 * 更新装机联盟 总表(45库 测试库)
	 * @param nextActive : 活跃率
	 * @param nextRegInsActive : 活跃数
	 * @param db_ip
	 * @param user
	 * @param pwd
	 * @param db_name
	 * @param tab_name
	 * @param tab_beforeDate : 次日时间
	 */
	public void updatePromotionAll(double nextActive,int nextRegInsActive,String db_ip,String user,String pwd,String db_name,String tab_beforeDate){
		pitDao.update_PromotionAll(nextActive,nextRegInsActive,db_ip, user, pwd, db_name, tab_beforeDate);
	}
	
}
