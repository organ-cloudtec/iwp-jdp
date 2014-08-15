<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@include file="/WEB-INF/views/include/head.jsp" %>
<html>
<head>
	<title>角色管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
	
		<li class="active"><a href="${ctx}/sys/role/">角色列表</a></li>
		<li><a href="${ctx}/sys/role/form">角色添加</a></li>
	</ul>
</body>
</html>