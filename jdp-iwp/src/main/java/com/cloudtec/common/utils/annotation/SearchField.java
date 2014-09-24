/**
 * Project Name:jdp-iwp 
 * File Name:SearchField.java 
 * Package Name:com.cloudtec.common.utils.annotation 
 * Date:2014-9-23下午3:15:48 
 * Copyright &copy; 2013-2014 <a href="http://www.svnchina.com/svn/iwp">iwp</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cloudtec.common.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import com.cloudtec.common.persistence.Operator;

/** 
 * 动态查询注解，简化，复用动态查询。
 * @ClassName: SearchField <br/> 
 * @Description: TODO <br/> 
 * @date: 2014-9-23 下午3:15:48 <br/> 
 * @author wangqi01 
 * @version  
 * @since JDK 1.6 
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SearchField {
	
	Operator operator() default Operator.EQ; 
}
