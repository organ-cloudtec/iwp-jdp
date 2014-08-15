package com.cloudtec.common.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 *  导出导入注解，用以复用、简化导入导出处理
  * @ClassName: ExeclField
  * @Description: TODO
  * @author wangqi01
  * @date 2014-8-15 下午3:51:56
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExeclField {
	
	/**
	 * 导出字段名（默认调用当前字段的“get”方法，如指定导出字段为对象，请填写“对象名.对象属性”，例：“area.name”、“office.name”）
	 */
	String value() default "";

	/**
	 * 导出excel列表字段的列名
	 */
	String title() default "";
	/**
	 * 导出列排序号
	 */
	int sort() default 0;
	
	/**
	 * 导出字段对齐方式（0：自动；1：靠左；2：居中；3：靠右）
	 * 备注：Integer/Long类型设置居右对齐（align=3）
	 */
	int align() default 0;
	
	/**
	 * 标示字段是导出还是导入时使用（0：导出导入；1：仅导出；2：仅导入）
	 */
	int type() default 0;
	
	
}
