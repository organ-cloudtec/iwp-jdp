<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>角色管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/role/">角色列表</a></li>
		<li><a href="${ctx}/sys/role/form">角色添加</a></li>
	</ul>
	<div>
		<table id="treeTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th style="text-align:center;">名称</th>
					<th style="text-align:center;">别名</th>
					<th style="text-align:center;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${roles}" var="role">
					<tr data-tt-id="${role.recid}">
						<td><a href="${ctx}/sys/role/form?recid=${role.recid}">${role.name}</a></td>
						<td>${role.nameMng}</td>
						<td>
							<a href="${ctx}/sys/role/form?recid=${role.recid}">修改权限</a>
							<a href="${ctx}/sys/role/delete?recid=${role.recid}">删除</a>  
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>