package com.entity.haha;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.dao.hahaCal.EnumBeanFieldType;


@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationGenSQL4Bean {
	
	EnumBeanFieldType et();

	String sql1();

	String TbColumnName();

	
}


