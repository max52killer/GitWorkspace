package edu.cc.sshe.framework.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)   //保留策略
@Target(ElementType.METHOD)  //指定注解的使用范围，当前指定只能使用的方法上
public @interface Permission {

	//String name() default "";
	//int age();
	String value();
}
