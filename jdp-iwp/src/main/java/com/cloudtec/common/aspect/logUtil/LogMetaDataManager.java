package com.cloudtec.common.aspect.logUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 业务模块和拦截目标管理类
 * @author wangqi01
 *
 */
public class LogMetaDataManager {
	private static List<BusinessModule> businessModuleList = null;
	private static List<BusinessTarget> businessTargetList = null;
	private static Map<String,String> excludePropertiesMap = null;
	private static List<String> excludeClassList = null;
	private static String excludeURIs = null;
	
	/**
	 * 获取不需要记录日志的Controllers
	 */
	public static String getExcludeURIs(){
		if(null==excludeURIs){
			loadCloudLogExcludes();
		}
		return excludeURIs;
	}
	
	/**
	 * 不需要记录到日志的实体属性
	 */	
	public static Map<String,String> getExcludePropertiesMap(){
		if(null==excludePropertiesMap){
			loadCloudLogExcludes();
		}
		return excludePropertiesMap;
	}
	
	/**
	 * 不需要记录到日志的类型
	 */	
	public static List<String> getExcludeClassList(){
		if(null==excludeClassList){
			loadCloudLogExcludes();
		}
		return excludeClassList;
	}
	
	/**
	 * 获取业务模块信息
	 * @return
	 */
	public static List<BusinessModule> getBusinessModuleList() {
		if(null!=businessModuleList)
			return businessModuleList;
		loadAllBusinessModule();
		if(null==businessTargetList) loadAllBusinessTarget();
		for(BusinessModule module: businessModuleList){
			for(BusinessTarget target : businessTargetList){
				if(target.getModuleName().equals(module.getModuleMame())){
					module.getBusinessTargetList().add(target);
				}
			}
		}		
		return businessModuleList;
	}


	/**
	 * 获取拦截目标信息
	 * @return
	 */
	public static List<BusinessTarget> getBusinessTargetList() {
		if(null==businessTargetList) loadAllBusinessTarget();
		return businessTargetList;
	}

	/**
	 * 装载业务模块配置信息
	 * @param filepath
	 * @return
	 */
	private static synchronized void loadCloudLogExcludes() {
		excludePropertiesMap = new HashMap<String,String>();
		excludeClassList= new ArrayList<String>();
		try {
			InputStream is = LogMetaDataManager.class.getClassLoader().getResourceAsStream("config/logAspectConfig/cloudlog-excludes.xml");
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docBuilder.parse(is);
			
			//不需要记录日志的Controllers
			NodeList excludeUriNodeList = doc.getElementsByTagName("exclude-uris");
			excludeURIs = excludeUriNodeList.item(0).getFirstChild().getNodeValue();
			
			//不需要记录到日志的实体属性
			NodeList excludeEntityNodeList = doc.getElementsByTagName("exclude-entity");
			for(int i=0; i<excludeEntityNodeList.getLength(); i++){
				Element entityNode = (Element)excludeEntityNodeList.item(i);
				String className = entityNode.getElementsByTagName("class-name").item(0).getFirstChild().getNodeValue();
				String propertyNames = entityNode.getElementsByTagName("property-names").item(0).getFirstChild().getNodeValue();
				excludePropertiesMap.put(className, propertyNames); //属性可以,隔开写多个
			}
			
			//不需要记录到日志的类(如: 上传文件时的MultipartFile等)
			NodeList nodeList = doc.getElementsByTagName("class-name");
			for(int i=0; i<nodeList.getLength(); i++){
				String className = nodeList.item(i).getFirstChild().getNodeValue();
				excludeClassList.add(className);
			}
			
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 装载业务模块配置信息
	 * @param filepath
	 * @return
	 */
	private static synchronized void loadAllBusinessModule() {
		System.out.println("装载业务模块配置信息...");
		businessModuleList = new ArrayList<BusinessModule>();
		try {
			InputStream is = LogMetaDataManager.class.getClassLoader().getResourceAsStream("config/logAspectConfig/cloudlog-modules.xml");
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docBuilder.parse(is);
			NodeList moduleNodeList = doc.getElementsByTagName("business-module");
			for(int i=0; i<moduleNodeList.getLength(); i++){
				Element moduleNode = (Element)moduleNodeList.item(i);
				String moduleName = moduleNode.getElementsByTagName("module-name").item(0).getFirstChild().getNodeValue();
				String moduleDesc = moduleNode.getElementsByTagName("module-desc").item(0).getFirstChild().getNodeValue();
				BusinessModule businessModule = new BusinessModule();
				businessModule.setModuleMame(moduleName);
				businessModule.setModuleDesc(moduleDesc);
				businessModuleList.add(businessModule);
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 装载拦截目标配置信息
				<method-name>updateModule</method-name>
				<method-args>HttpServletRequest,HttpServletResponse,ShopModule</method-args>
				<method-desc>更新装修模块内容</method-desc>
				<module-names>decorate,imgspace</module-names>
	 * @param filepath
	 * @return
	 */
	private static synchronized void loadAllBusinessTarget() {
		System.out.println("开始装载拦截目标配置信息...");
		businessTargetList = new ArrayList<BusinessTarget>();
		try {
			InputStream is = LogMetaDataManager.class.getClassLoader().getResourceAsStream("config/logAspectConfig/cloudlog-targets.xml");
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docBuilder.parse(is);
			NodeList businessTargetNodeList = doc.getElementsByTagName("business-target");
			for(int i=0; i<businessTargetNodeList.getLength(); i++){
				Element businessTargetNode = (Element)businessTargetNodeList.item(i);
				String className = businessTargetNode.getElementsByTagName("class-name").item(0).getFirstChild().getNodeValue();
				NodeList methodNodeList = businessTargetNode.getElementsByTagName("method");
				for(int j=0; j<methodNodeList.getLength(); j++){
					BusinessTarget businessTarget = new BusinessTarget();
					//目标类
					businessTarget.setClassName(className); 
					//目标方法
					Element methodNode = (Element)methodNodeList.item(j);
					String methodName = methodNode.getElementsByTagName("method-name").item(0).getFirstChild().getNodeValue();
					String methodArgs = methodNode.getElementsByTagName("method-args").item(0).getFirstChild().getNodeValue();
					String methodDesc = methodNode.getElementsByTagName("method-desc").item(0).getFirstChild().getNodeValue();
					String moduleName = methodNode.getElementsByTagName("module-name").item(0).getFirstChild().getNodeValue();
					businessTarget.setMethodName(methodName);
					businessTarget.setMethodArgs(methodArgs);
					businessTarget.setMethodDesc(methodDesc);
					businessTarget.setModuleName(moduleName);
					businessTargetList.add(businessTarget);
				}
				
			}
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//业务模块信息(含要拦截的业务目标方法)
		List<BusinessModule> businessModuleList = LogMetaDataManager.getBusinessModuleList();
		for(BusinessModule module : businessModuleList){
			System.out.println( module.getModuleMame() + "\t" + module.getModuleDesc());
			for(BusinessTarget businessTarget : module.getBusinessTargetList()){
				System.out.println( businessTarget.getClassName() + "\t" + businessTarget.getMethodName() 
						 + "\t" + businessTarget.getMethodArgs()  + "\t" + businessTarget.getMethodDesc() 
						 + "\t" + businessTarget.getModuleName());
			}
		}
		
		//不需要记录日志的Controllers
		String excludeURIs = LogMetaDataManager.getExcludeURIs();
		System.out.println( excludeURIs );
		
		//不需要记录到日志的实体属性
		Map<String,String> excludePropertiesMap = LogMetaDataManager.getExcludePropertiesMap();
		for(String key : excludePropertiesMap.keySet()){
			System.out.println( key + "\t" + excludePropertiesMap.get(key));
		}
		
		//不需要记录到日志的类型
		List<String> excludeClassList = LogMetaDataManager.getExcludeClassList();
		for(String classname : excludeClassList){
			System.out.println(classname);
		}
	}
}
