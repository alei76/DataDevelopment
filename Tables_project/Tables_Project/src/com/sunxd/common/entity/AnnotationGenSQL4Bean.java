package com.sunxd.common.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.sunxd.common.EnumBeanFieldType;


@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationGenSQL4Bean {
	
	EnumBeanFieldType et();

	String sql1();

	String TbColumnName();

	
}


