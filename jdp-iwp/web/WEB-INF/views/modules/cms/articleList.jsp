<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>栏目管理</title>
	<script type="text/javascript">
    	function updateWeight() {
			loading('正在提交，请稍等...');
	    	$("#listForm").attr("action", "${ctx}/cms/article/updateWeight");
	    	$("#listForm").submit();
    	}
	</script>
</head>
<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cms/article/">文章列表</a></li>
		<shiro:hasPermission name="cms:article:edit">
			<li><a href="${ctx}/cms/article/form">文章添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id ="searchForm"  action="${ctx}/article/list" modelAttribute="article" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${user.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${user.pageSize}"/>
		<div>
			<label>归属部门：</label>
			<tags:treeselect id="organ" name="organ.recid" value="${user.organ.recid}" labelName="organ.name" labelValue="${user.organ.name}" 
				title="部门" url="/sys/organ/treeData" cssClass="input-small" allowClear="true" checked="true"/>
			<label>登录名：</label><form:input path="username" htmlEscape="false" maxlength="50" class="input-small"/>
		</div>
		<div style="margin-top:8px;">
			<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label>
			<form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
			&nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			&nbsp;<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
		</div>
	</form:form>
	<div>
		<form id="listForm" method="post">
			<table id="treeTable" class="table table-striped table-bordered table-condensed">
				<tr>
					<th>文章分类</th>
					<th>文章图片</th>
					<th>文章标题</th>
					<th style="text-align:center;">权重</th>
					<th>关键字</th>
					<th>点击数</th>
					<th>推荐位</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${articles}" var="tpl">
					<tr id="${tpl.recid}">
						<td>${tpl.category.name}</td>
						<td><i class="icon-${not empty tpl.image?tpl.image:'hide'}"></i><a href="${ctx}/cms/article/form?recid=${tpl.recid}">${tpl.name}</a></td>
						<td>
							${tpl.title}
						</td>
						<td style="text-align:center;">
							<shiro:hasPermission name="cms:article:edit">
								<input type="hidden" name="ids" value="${tpl.recid}"/>
								<input name="sorts" type="text" value="${tpl.weight}" style="width:50px;margin:0;padding:0;text-align:center;">
							</shiro:hasPermission>
							<shiro:lacksPermission name="cms:article:edit">
							${tpl.weight}
							</shiro:lacksPermission>
						</td>
						<td>
							${tpl.keywords}
						</td>
						<td>
							${tpl.hits}
						</td>
						<td style="text-align:center;">
							${tpl.posid}
						</td>
						<td>
							<shiro:hasPermission name="cms:article:edit">
								<a href="${ctx}/cms/article/form?recid=${tpl.recid}">修改</a>
								<a href="${ctx}/cms/article/delete?recid=${tpl.recid}" >删除</a>
								<a href="${ctx}/cms/article/form?parent.recid=${tpl.recid}">添加下级栏目</a>
							</shiro:hasPermission>
							<shiro:lacksPermission name="cms:article:view">
								<a href="${ctx}/cms/article/form?recid=${tpl.recid}">查看</a>
							</shiro:lacksPermission>
							
						</td>
					</tr>
				</c:forEach>
			</table>
		</form>
		<tags:pagination page="${articles}" paginationSize="2" pageSize="${user.pageSize}"/>
	<shiro:hasPermission name="cms:article:edit">
		<div class="form-actions pagination-left">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保存排序" onclick="updateWeight();"/>
		</div>
	</shiro:hasPermission>
	</div>
</body>
</html>