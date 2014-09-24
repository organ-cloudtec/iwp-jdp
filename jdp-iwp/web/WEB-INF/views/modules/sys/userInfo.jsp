<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>后台管理系统</title>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
    <style type="text/css"> 
		.controls{ 
		     display:inline-block;
		} 
	</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="panel panel-default">
				<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/save" method="post" class="form-horizontal">
					<!-- Form Name -->
					<legend>User Info</legend>
					<!-- Text input-->
					<div class="control-group">
					  <label class="control-label" for="username">登陆名：</label>
					  <div class="controls">
					  	<form:input path="username" htmlEscape="false" cssClass="required input-xlarge" readonly="true"/>
					  </div>
					</div>
					
					<!-- Text input-->
					<div class="control-group">
					  <label class="control-label" for="textinput">用户名：</label>
					  <div class="controls">
						<form:input path="name" htmlEscape="false" cssClass="required input-xlarge" readonly="true"/>
					  </div>
					</div>
					
					<!-- Text input-->
					<div class="control-group">
					  <label class="control-label" for="textinput">单位信息：</label>
					  <div class="controls">
					  	<tags:treeselect url="${ctx}/sys/organ/treeData" id="organ" value="${user.organ.recid}" labelName="organ.name" labelValue="${user.organ.name}"
					  	 title="单位信息" name="organ.recid" cssClass="input-small required input-xlarge" ></tags:treeselect>
					  </div>
					</div>
					<!-- Text input-->
					<div class="control-group">
					  <label class="control-label" for="textinput">角色信息：</label>
					  <div class="controls">
					  	<form:checkboxes path="roleIdList" items="${user.roleList}" itemLabel="name" itemValue="recid" htmlEscape="false" class="required" disabled="true"/>
					  </div>
					</div>
					<!-- File Button --> 
					<div class="control-group">
					  <label class="control-label" for="filebutton">File Button</label>
					  <div class="controls">
					    <input id="filebutton" name="filebutton" class="input-file" type="file">
					  </div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>