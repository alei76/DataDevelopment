package com.test;

import com.job.PromotionInstall_job;



public class PromotionInstallTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String dateTime = args[0];	//����ʱ��
//		String ip01 = args[1];		//43��
//		String user01 = args[2];	
//		String pwd01 = args[3];
//		String ip02 = args[4];		//127��
//		String user02 = args[5];
//		String pwd02 = args[6];
		String dateTime = "20140419";
		String ip01 = "112.124.97.43:3306";
		String user01 = "zhuyahuangweinia";
		String pwd01 = "vcr4mmdrG6SaCNHV";
		String ip02 ="211.149.193.127:3306";
		String user02 = "developer";
		String pwd02  = "DN3v74JB";
		
		//װ������
		PromotionInstall_job piJob = new PromotionInstall_job();
		piJob.PromotionInstall_work(dateTime, ip01, user01, pwd01, ip02, user02, pwd02);		//��װ��
		piJob.PromotionOnline_work(dateTime, ip01, user01, pwd01, ip02, user02, pwd02);		//����ʹ�ñ�
		//java -jar PromotionInstall_v1.jar '' 'localhost:3306' 'developer' 'DN3v74JB' '211.149.193.127:3306' 'developer' 'DN3v74JB'
		
	}
}
