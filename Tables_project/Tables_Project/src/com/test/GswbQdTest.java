package com.test;


import com.job.Gswb_qd_job;
/**
 * �������뷨����ͳ�����
 * @author ZhuYa
 *
 */
public class GswbQdTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String dateTime = "";//args[0];	//�ж��ǲ��ǵ�ǰʱ��
		String ip01 = "112.124.97.43:3306";//args[1];		//43��
//		String user01 = args[2];	//�û���
//		String pwd01 = args[3];		//����
		String user01 = "zhuyahuangweinia";
		String pwd01 = "vcr4mmdrG6SaCNHV";
		//java -jar Tables_Project_qd_v1.0.jar '' 'localhost:3306' 'developer' 'DN3v74JB'
		//�������뷨����
		Gswb_qd_job qdJob = new Gswb_qd_job();
		qdJob.gswb_qd_work(dateTime,ip01,user01,pwd01);
	}
}