<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>栏目管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
    	function updateSort() {
			loading('正在提交，请稍等...');
	    	$("#listForm").attr("action", "${ctx}/cms/category/updateSort");
	    	$("#listForm").submit();
    	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/cms/category/">栏目列表</a></li>
		<li class="active">
			<a href="${ctx}/cms/category/form">栏目
				<shiro:hasPermission name="cms:category:edit">${not empty category.recid?'修改':'添加'}
				</shiro:hasPermission>
				<shiro:lacksPermission name="cms:category:view">查看
				</shiro:lacksPermission>
			</a>
		</li>
	</ul>
	<tags:message content="${message}"/>
	<form:form id="inputForm" action="${ctx}/cms/category/save" method="post" modelAttribute="category">
		<form:hidden path="recid"/>
		<div class="control-group">
			<label class="control-label">上级栏目:</label>
                <tags:treeselect id="category.parent" name="parent.recid" value="${category.parent.recid}" labelName="parent.name" labelValue="${category.parent.name}"
					title="栏目" url="/sys/category/treeData?extId=${category.recid}"  cssClass="required" />
		</div>
		<div class="control-group">
			<label class="control-label">名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">别名:</label>
			<div class="controls">
				<form:input path="href" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">目标:</label>
			<div class="controls">
				<form:select path="target" items="${fns:getDictList('target')}" htmlEscape="false" itemLabel="label" itemValue="value"></form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">链接:</label>
			<div class="controls">
				<form:input path="href" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
			<div class="control-group">
			<label class="control-label">图标:</label>
			<div class="controls">
				<tags:iconselect id="image" name="image" value="${category.image}"></tags:iconselect>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="cms:category:edit"> 
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>