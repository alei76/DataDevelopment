package com.sunxd.common.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.dao.hahaCal.EnumBeanFieldType;
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationUpdateSQL4FutureBean {

String name();

String sql();

EnumBeanFieldType et();

String metaSql() default "select count(distinct b.uid)from install[?] as a inner join user_action[?] as b on a.UID=b.uid"; 
}
