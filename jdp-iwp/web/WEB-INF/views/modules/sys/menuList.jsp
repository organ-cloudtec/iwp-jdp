<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@include file="/WEB-INF/views/include/head.jsp" %>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">.table td i{margin:0 2px;}</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/menu/">菜单列表</a></li>
		<li><a href="${ctx}/sys/menu/form">菜单添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="menu" action="${ctx}/sys/menu" method="post" class="breadcrumb form-search">
		<div>
			<label>菜单名称：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
		</div>
		<div style="margin-top:8px;">
			<label>简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label><form:input path="nameMng" htmlEscape="false" maxlength="50" class="input-small"/>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			&nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="导出" />
			&nbsp;<input id="btnImport" class="btn btn-primary" type="button" value="导入" />
		</div>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>简称</th>
				<th>排序</th>
				<th>可见</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${menus.content}" var="menu">
				<tr>
					<td><a href="${ctx}/sys/menu/form?recid=${menu.recid}">${menu.name}</a></td>
					<td>${menu.nameMng}</td>
					<td>${menu.sort}</td>
					<td>${menu.isShow eq '0'?'显示':'隐藏'}</td>
					<td>
						<a href="${ctx}/sys/menu/form?recid=${menu.recid}">添加</a>
						<a href="${ctx}/sys/menu/form?recid=${menu.recid}">修改</a>
						<a href="${ctx}/sys/menu/delete?recid=${menu.recid}">删除</a>  
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<tags:pagination paginationSize="10" page="${menus}"></tags:pagination>
</body>
</html>
