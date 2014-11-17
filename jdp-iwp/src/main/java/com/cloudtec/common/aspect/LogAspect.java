/**
 * Project Name:jdp-iwp 
 * File Name:LogAspect.java 
 * Package Name:com.cloudtec.common.aspect 
 * Date:2014-11-10上午10:01:41 
 * Copyright &copy; 2013-2014 <a href="http://www.svnchina.com/svn/iwp">iwp</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.cloudtec.common.aspect;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cloudtec.common.aspect.logUtil.LogMetaDataManager;
import com.cloudtec.common.aspect.logUtil.LogUtil;
import com.cloudtec.modules.sys.entity.CloudLog;
import com.cloudtec.modules.sys.entity.User;

/** 
 * @ClassName: LogAspect <br/> 
 * @Description: TODO <br/> 
 * @date: 2014-11-10 上午10:01:41 <br/> 
 * @author wangqi01 
 * @version  
 * @since JDK 1.6 
 */
@Aspect
public class LogAspect {
	//不需要记录日志的Controllers
	private static String excludeURIs = null;
	//不需要记录到日志的实体属性
	private static Map<String,String> excludePropertiesMap = null;
	//不需要记录到日志的类型
	private static List<String> excludeClassList = null;
	
	//日志切面,织入点
	@Pointcut("execution(* com.cloudtec.modules..controllers.*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void pointcut1() {
	}
	@Around("pointcut1()")
	public Object controllerStart(ProceedingJoinPoint joinPoint) throws Throwable{
		if(excludeURIs==null || excludePropertiesMap==null || excludeClassList==null){
			excludeURIs = LogMetaDataManager.getExcludeURIs();
			excludePropertiesMap = LogMetaDataManager.getExcludePropertiesMap();
			excludeClassList = LogMetaDataManager.getExcludeClassList();
		}
		long begin = System.currentTimeMillis();
		//System.out.println("----------- Around 切面开始 ------------" + System.currentTimeMillis());
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		//获取类名、方法名
		Signature signature=joinPoint.getSignature();
		String signatureStr = signature.toString();
		String className = signature.getDeclaringTypeName();//joinPoint.getTarget().getClass().getName()
		String methodName = signature.getName();
		
		//模块名
		String moduleName = LogUtil.findModuleName(className, methodName);
		
		CloudLog cloudLog = LogUtil.createPopLog(moduleName);//返回的实体中已经设置过模块名
		cloudLog.setStartAt(new Date());
		cloudLog.setClassName(className);
		cloudLog.setMethodName(methodName);
		
		//获取参数类型、参数值
		String argsType = signatureStr.substring(signatureStr.indexOf("(")+1, signatureStr.indexOf(")"));
		Object[] args = joinPoint.getArgs();
		for(int i=0; i<args.length; i++){
			if(args[i] instanceof HttpServletRequest){
				//获取request params
				//String requestJsonString = LogUtil.getRequestParamsJson(request);
				//args[i]=requestJsonString;
				Map<String, String> paramMap = LogUtil.getRequestParamsMap(request);
				args[i]=paramMap;
			}
			if(args[i] instanceof HttpServletResponse){
				args[i]="response";
			}
		}		
		cloudLog.setArgsType(argsType);
		cloudLog.setArgsValues(args);
		
		cloudLog.setClientIp(LogUtil.getRemoteIP(request) );

		//操作帐号信息
		User operator = LogUtil.getOperator(request);
		cloudLog.setUser(operator);
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
			cloudLog.setResultStatus(1); //成功-1,失败-0
			//不需要记录到日志的实体属性(将对应属性设置为null,需要在原业务处理完后进行)
			for(int i=0; i<args.length; i++){
				if( null!=args[i] && excludePropertiesMap.containsKey( args[i].getClass().getName() ) ){
					String[] propertyNames = excludePropertiesMap.get(args[i].getClass().getName()).split(",");
					for(String propertyName : propertyNames){
						BeanUtils.setProperty(args[i], propertyName, null);
					}
				}
			}
			//不需要记录到日志的类型(排除掉这些类型的对象)
			for(int i=0; i<args.length; i++){
				if( null!=args[i] && excludeClassList.contains(args[i].getClass().getName()) ){
					args[i]=null;
				}
			}
			cloudLog.setArgsValues(args);
		} catch (Throwable ex) {
			cloudLog.setResultStatus(0); //成功-1,失败-0
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			ex.printStackTrace(ps);
			String exception = new String( baos.toByteArray() );
			cloudLog.setException(exception );
			//错误信息打印到控制台
			System.out.println(exception);
			/**
			 * 错误信息比较多 时处理【待实现】
			 */
		}
		long opEnd = System.currentTimeMillis();
		cloudLog.setCostTime(opEnd-begin); //运行耗时
		
		//有的Controller不进行拦截
		String requestURI = request.getRequestURI();
		requestURI = requestURI.substring(requestURI.lastIndexOf("/")+1);
		if( excludeURIs.indexOf(requestURI)==-1 ){
			//保存日志【待实现】
			
		}
		//System.out.println("----------- Around 切面结束 ------------" + System.currentTimeMillis());
		return obj;
	}	
}
