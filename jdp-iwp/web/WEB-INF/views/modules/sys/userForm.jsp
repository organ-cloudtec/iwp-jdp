<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css"> 
		.controls{ 
		     display:inline-block;
		} 
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#username").focus();
			$("#inputForm").validate({
				rules: {
					username: {remote: "${ctx}/sys/user/checkUsername?oldusername=" + encodeURIComponent('${user.username}')},
				},
				messages: {
					username: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
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
		<li><a href="${ctx}/sys/user/">用户列表</a></li>
		<li class="active"><a href="${ctx}/sys/user/form?id=${user.recid}">用户<shiro:hasPermission name="sys:user:edit">${not empty user.recid?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sys:user:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
		<form:hidden path="recid"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" for="oldusername">登&nbsp;&nbsp;录&nbsp;名:</label>
			<div class="controls">
				<input id="oldusername" name="oldusername" type="hidden" value="${user.username}">
				<form:input path="username" htmlEscape="false" maxlength="50" class="required username controls"/>
			</div>
		</div>
		<div class="control-group" for="name">
			<label class="control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required controls"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="newpassword">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
			<div class="controls">
				<input id="newpassword" name="newpassword" type="password" value="" maxlength="50" minlength="3" class="${empty user.recid?'required':''} controls"/>
				<c:if test="${not empty user.recid}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="confirmNewPassword">确认密码:</label>
			<div class="controls">
				<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newpassword" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="roleIdList">用户角色:</label>
			<div class="controls">
				<form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="recid" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="remarks">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注:</label>
			<div class="controls">
				<form:textarea path="remarks" maxlength="200" htmlEscape="false" class="controls"/>
			</div>	
		</div>
		<div class="control-group">
			<label class="control-label" for= "organ">所属单位:</label>
			<div class="controls">
				<tags:treeselect id="organ" name="organ.recid" value="${user.organ.recid}" labelName="organ.name" labelValue="${user.organ.name}"
					title="公司" url="/sys/organ/treeData" cssClass="input-small required" allowClear="true"/>
			</div>
		</div>
		
		<div style="margin-top:8px;">
			<!-- <shiro:hasPermission name="sys:user:edit">  </shiro:hasPermission> -->
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>