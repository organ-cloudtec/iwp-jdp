package com.cloudtec.common.aspect.logUtil;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.cloudtec.modules.sys.entity.CloudLog;
import com.cloudtec.modules.sys.entity.User;
import com.cloudtec.modules.sys.utils.UserUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LogUtil {
	private static ObjectMapper om = new ObjectMapper();
	
	/**
	 * 根据模块名创建实体
	 */
	public static CloudLog createPopLog(String moduleName){
		CloudLog poplog = new CloudLog();
		poplog.setModuleName(moduleName);
		return poplog;
	}
	
	/**
	 * 根据类名、方法名判断模块
	 */
	public static String findModuleName(String className,String methodName){
		try {
			for(BusinessTarget target : LogMetaDataManager.getBusinessTargetList() ){
				if(target.getClassName().equals(className) && target.getMethodName().equals(methodName)){
					return target.getModuleName();
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return BusinessModuleEnum.others.getName();
	}
	/**
	 * 根据类名、方法名判断模块
	 */
	public static String findMethodDes(String className,String methodName){
		try {
			for(BusinessTarget target : LogMetaDataManager.getBusinessTargetList() ){
				if(target.getClassName().equals(className) && target.getMethodName().equals(methodName)){
					return target.getMethodDesc();
				}
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return null;
	}
	/**
	 * 获取客户ip
	 * @param request
	 * @return
	 */
	public static String getRemoteIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr(); 
		}
		//真实IP是第一个非内网IP，做安全限定用最后一个非内网IP(如果是用来限定安全后最后一个入口IP)
		return request.getHeader("x-forwarded-for").split(",")[0];
	}
	
	/**
	 * 获取request中的param,拼装成json
	 * @param request
	 * @return
	 */
	public static String getRequestParamsJson(HttpServletRequest request){
		if(null==request)
			return "";
		String requestJsonString = "";
		try {
			//获取request params
			HashMap<String, String> paramMap = new HashMap<String, String>();
			Enumeration<String> e = request.getParameterNames();
			while(e.hasMoreElements()){
				String paramname = e.nextElement();
				StringBuffer sb = new StringBuffer();
				String[] values = request.getParameterValues(paramname);
				for(String value : values){
					sb.append(value+",");
				}
				if(sb.length()>0){
					sb.deleteCharAt(sb.length()-1);
				}
				String paramvalue = sb.toString();
				paramMap.put(paramname, paramvalue);
			}
			requestJsonString = om.writeValueAsString(paramMap);
		} catch (Exception e2) {
			e2.printStackTrace(System.out);
		}
		return requestJsonString;
	}
	
	/**
	 * 获取request中的param,拼装成json
	 * @param request
	 * @return
	 */
	public static Map<String,String> getRequestParamsMap(HttpServletRequest request){
		HashMap<String, String> paramMap = new HashMap<String, String>();
		if(null==request)
			return paramMap;
		try {
			//获取request params
			Enumeration<String> e = request.getParameterNames();
			while(e.hasMoreElements()){
				String paramname = e.nextElement();
				StringBuffer sb = new StringBuffer();
				String[] values = request.getParameterValues(paramname);
				for(String value : values){
					sb.append(value+",");
				}
				if(sb.length()>0){
					sb.deleteCharAt(sb.length()-1);
				}
				String paramvalue = sb.toString();
				paramMap.put(paramname, paramvalue);
			}
		} catch (Exception e2) {
			e2.printStackTrace(System.out);
		}
		return paramMap;
	}
	
	/**
	 * 从request中获取帐号信息
	 * @param request
	 * @return
	 */
	public static User getOperator(HttpServletRequest request){
		return UserUtils.getUser();
	}
}
