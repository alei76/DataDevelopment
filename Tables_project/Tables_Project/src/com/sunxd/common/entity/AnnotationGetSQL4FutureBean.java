package com.sunxd.common.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.dao.hahaCal.EnumBeanFieldType;
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationGetSQL4FutureBean {

	String sql();

	EnumBeanFieldType et();

	

}
