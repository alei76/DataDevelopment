package com.entity.haha;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.dao.hahaCal.EnumBeanFieldType;
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationSQLCommon4Bean {

	String sql1();

	String[] reqArray={"NextDayActive","WeekActive","MonthActive"};
    String[] typeArray={"Count","Rate"};
    
	EnumBeanFieldType et();

}
