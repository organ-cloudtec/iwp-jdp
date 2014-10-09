<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">.table td i{margin:0 2px;}</style>
</head>
<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/dict/">枚举项列表</a></li>
		<shiro:hasPermission name="sys:dict:edit">
			<li><a href="${ctx}/sys/dict/form">枚举项添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="inputForm" method="post">
		<table id="treeTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<th style="text-align:center;">标签名</th>
				<th style="text-align:center;">数据值</th>
				<th style="text-align:center;">类型</th>
				<th style="text-align:center;">描述</th>
				<th style="text-align:center;">排序</th>
				<th style="text-align:center;">操作</th>
			</tr>
			<c:forEach items="${dicts}" var="dict">
				<tr>
					<td><a href="${ctx}/sys/dict/form?recid=${dict.recid}">${dict.label}</a></td>
					<td>${dict.value}</td>
					<td>${dict.type}</td>
					<td>${dict.description}</td>
					<td style="text-align:center;">
						${dict.sort}
					</td>
					<td>
						<shiro:hasPermission name="sys:dict:edit">
							<a href="${ctx}/sys/dict/form?recid=${dict.recid}">修改</a>
							<a href="${ctx}/sys/dict/delete?recid=${dict.recid}">删除</a>
						</shiro:hasPermission>
						<shiro:lacksPermission name="sys:dict:view">
							<a href="${ctx}/sys/dict/form?recid=${dict.recid}">查看</a>
						</shiro:lacksPermission>
					</td>
				</tr>
			</c:forEach>
		</table>
		<shiro:hasPermission name="sys:dict:edit">
			<div class="form-actions pagination-left">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="保存排序" onclick="updateSort();"/>
			</div>
		</shiro:hasPermission>
	</form:form>
</body>
</html>
