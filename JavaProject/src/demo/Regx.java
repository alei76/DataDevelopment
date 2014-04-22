package demo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regx

{
	public static void main(String[] args)
	{
		String str="<input　type='text'　id='baijinshan'　name='baijinshan'　>";
		String toMat="insert into new_user_data_gswb(DateLine,TodayNewInstallNum,TodayNewUninstallNum,TodayNewActiveNum,NextDayActiveNum,7DayActiveNum,30DayActiveNum,30DayUninstallNum)" +
				" values(?,?,?,?,?,?,?,?)";
		int i1=toMat.indexOf("(", 1);
		int i2=toMat.indexOf(")", 1);
		String[] rst=toMat.substring(i1, i2).split(",");
		String regEx="(?<=id=')[\\S\\s]+(?='　name)";
		Pattern p=Pattern.compile(regEx);
		Matcher m=p.matcher(str);
		boolean result=m.find();
		System.out.println(m.group());
	}
}
	

