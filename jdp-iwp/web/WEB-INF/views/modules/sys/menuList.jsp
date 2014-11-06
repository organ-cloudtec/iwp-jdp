<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">.table td i{margin:0 2px;}</style>
	<script type="text/javascript">
		function updateSort(){
			var d = dialog({
			    title: '系统提示',
			    content: '确认要保存修改菜单的排序？',
			    okValue: '确定',
			    ok: function () {
					$("#inputForm").submit();
			    },
			    cancelValue: '取消',
			    cancel: function () {}
			});
			d.show();
		};
		$(document).ready(function(){
			
		});
	</script>
</head>
<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/menu/">菜单列表</a></li>
		<shiro:hasPermission name="sys:menu:edit">
			<li><a href="${ctx}/sys/menu/form">菜单添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="inputForm" method="post" action="${ctx }/sys/menu/updateSort">
		<table id="treeTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<th style="text-align:center;">名称</th>
				<th style="text-align:center;">别名</th>
				<th style="text-align:center;">链接</th>
				<th style="text-align:center;">排序</th>
				<th style="text-align:center;">可见</th>
				<th style="text-align:center;">权限</th>
				<th style="text-align:center;">操作</th>
			</tr>
			<c:forEach items="${menus}" var="menu">
				<tr>
					<td style="${not empty menu.permissionFlag?'text-align:center':''}"><i class="icon-${not empty menu.icon?menu.icon:'hide'}"></i><a href="${ctx}/sys/menu/form?recid=${menu.recid}">${menu.name}</a></td>
					<td>${menu.nameMng}</td>
					<td>${menu.url}</td>
					<td style="text-align:center;">
						<shiro:hasPermission name="sys:menu:edit">
							<input type="hidden" name="ids" value="${menu.recid}"/>
							<input name="sorts" type="text" value="${menu.sort}" style="width:50px;margin:0;padding:0;text-align:center;">
						</shiro:hasPermission><shiro:lacksPermission name="sys:menu:edit">
							${menu.sort}
						</shiro:lacksPermission>
					</td>
					<td>${menu.isShow eq '0'?'隐藏':'显示'}</td>
					<td>${menu.permissionFlag}</td>
					<td>
						<shiro:hasPermission name="sys:menu:edit">
							<a href="${ctx}/sys/menu/form?parent.recid=${menu.recid}">添加下级菜单</a>
							<a href="${ctx}/sys/menu/form?recid=${menu.recid}">修改</a>
							<a href="${ctx}/sys/menu/delete?recid=${menu.recid}">删除</a>
						</shiro:hasPermission>
						<shiro:lacksPermission name="sys:menu:view">
							<a href="${ctx}/sys/menu/form?recid=${menu.recid}">查看</a>
						</shiro:lacksPermission>
					</td>
				</tr>
			</c:forEach>
		</table>
		<shiro:hasPermission name="sys:menu:edit">
			<div class="form-actions pagination-left">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="保存排序" onclick="updateSort();"/>
			</div>
		</shiro:hasPermission>
	</form:form>
</body>
</html>
