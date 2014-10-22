<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>栏目管理</title>
	<style type="text/css">
		.controls{ 
		     display:inline-block;
		} 
	</style>
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
                <tags:treeselect id="category" name="parent.recid" value="${category.parent.recid}" labelName="parent.name" labelValue="${category.parent.name}"
					title="栏目" url="/sys/category/treeData?extId=${category.recid}"  cssClass="required" />
		</div>
		<div class="control-group">
			<label class="control-label">栏目名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">栏目模型:</label>
			<div class="controls">
				<form:input path="module" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">目&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标:</label>
			<div class="controls">
				<form:input path="target"  htmlEscape="false" maxlength="20"/>
				<span class="help-inline">栏目超链接打开的目标窗口，新窗口打开，请填写：“_blank”</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">链&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;接:</label>
			<div class="controls">
				<form:input path="href" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">栏目排序:</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="50"/>
				<span class="help-inline">栏目排列顺序</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述:</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">栏目图标:</label>
			<div class="controls">
				<tags:iconselect id="image" name="image" value="${category.image}"></tags:iconselect>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">栏目关键字:</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="50"/>
				<span class="help-inline">栏目关键字，便于搜索。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否允许评论:</label>
			<div class="controls">
				<form:radiobuttons path="allowComment" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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