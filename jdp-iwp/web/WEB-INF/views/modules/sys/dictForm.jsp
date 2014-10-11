<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#label").focus();
			$("#inputForm").validate({
				rules: {
					type: {
							required:true,
							remote:{                                          //验证用户名是否存在
					               type:"POST",
					               url:"${ctx}/sys/dict/checklabelandvalue",             //servlet
					               data:{
					            	   type:function(){return $("#type").val();},
					            	   label:function() {return $("#label").val();},
					            	   value:function() {return $("#value").val();},
					            	   recid:function() {return $("#recid").val();}
					               } 
					              } 
						},
					label: {
						required:true,
						remote:{                                          
			               type:"POST",
			               url:"${ctx}/sys/dict/checklabelandvalue",             
			               data:{
			            	   type:function(){return $("#type").val();},
			            	   label:function() {return $("#label").val();},
			            	   value:function() {return $("#value").val();},
			            	   recid:function() {return $("#recid").val();}
			               } 
			              } 
						},
					value: {
						required:true,
						remote:{                                          
			               type:"POST",
			               url:"${ctx}/sys/dict/checklabelandvalue",            
			               data:{
			            	   type:function(){return $("#type").val();},
			            	   label:function() {return $("#label").val();},
			            	   value:function() {return $("#value").val();},
			            	   recid:function() {return $("#recid").val();}
			               } 
			              } 
						}
				},
				messages: {
					type: {remote: "该类型标签名对应的数据值已存在"},
					label: {remote: "该类型标签名对应的数据值已存在"},
					value: {remote: "该类型标签名对应的数据值已存在"}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/dict/">枚举项列表</a></li>
		<li class="active"><a href="${ctx}/sys/dict/form?recid=${dict.recid}">枚举项<shiro:hasPermission name="sys:dict:edit">${not empty menu.recid?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:dict:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dict" action="${ctx}/sys/dict/save" method="post" class="form-horizontal">
		<form:hidden path="recid"/>
		<div class="control-group">
			<label class="control-label">标签名:</label>
			<div class="controls">
				<form:input path="label" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据值:</label>
			<div class="controls">
				<form:input path="value" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类型:</label>
			<div class="controls">
				<form:input path="type" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述:</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false"/>
			</div>
		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="50" class="required digits"/>
			</div>
		</div>
		<div class="form-actions">
			<!-- <shiro:hasPermission name="sys:menu:edit"> </shiro:hasPermission>-->
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>