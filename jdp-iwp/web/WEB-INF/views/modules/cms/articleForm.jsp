<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>栏目管理</title>
	<script type="text/javascript">
    	function updateSort() {
			loading('正在提交，请稍等...');
	    	$("#listForm").attr("action", "${ctx}/cms/category/updateSort");
	    	$("#listForm").submit();
    	}
	</script>
</head>
<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cms/category/">栏目列表</a></li>
		<shiro:hasPermission name="cms:category:edit">
			<li><a href="${ctx}/cms/category/form">栏目添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form id="listForm" method="post">
		<table id="treeTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<th>栏目名称</th>
				<th>栏目模型</th>
				<th style="text-align:center;">排序</th>
				<th>栏目链接</th>
				<th>展现方式</th>
				<th>是否允许评论</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${categorys}" var="tpl">
				<tr id="${tpl.recid}">
					<td><i class="icon-${not empty tpl.image?tpl.image:'hide'}"></i><a href="${ctx}/cms/category/form?recid=${tpl.recid}">${tpl.name}</a></td>
					<td>${fns:getDictLabel(tpl.module, 'cms_module', '公共模型')}</td>
					<td style="text-align:center;">
						<shiro:hasPermission name="cms:category:edit">
							<input type="hidden" name="ids" value="${tpl.recid}"/>
							<input name="sorts" type="text" value="${tpl.sort}" style="width:50px;margin:0;padding:0;text-align:center;">
						</shiro:hasPermission>
						<shiro:lacksPermission name="cms:category:edit">
						${tpl.sort}
						</shiro:lacksPermission>
					</td>
					<td>
						${tpl.href}
					</td>
					<td>
						${tpl.target}
					</td>
					<td style="text-align:center;">
						${fns:getDictLabel(tpl.allowComment, 'yes_no', '')}
					</td>
					<td>
						<a href="${pageContext.request.contextPath}${fns:getFrontPath()}/list-${tpl.recid}${fns:getUrlSuffix()}" target="_blank">访问</a>
						<shiro:hasPermission name="cms:category:edit">
							<a href="${ctx}/cms/category/form?recid=${tpl.recid}">修改</a>
							<a href="${ctx}/cms/category/delete?recid=${tpl.recid}" >删除</a>
							<a href="${ctx}/cms/category/form?parent.recid=${tpl.recid}">添加下级栏目</a>
						</shiro:hasPermission>
						<shiro:lacksPermission name="cms:category:view">
							<a href="${ctx}/cms/category/form?recid=${tpl.recid}">查看</a>
						</shiro:lacksPermission>
						
					</td>
				</tr>
			</c:forEach>
		</table>
		<shiro:hasPermission name="cms:category:edit">
			<div class="form-actions pagination-left">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="保存排序" onclick="updateSort();"/>
			</div>
		</shiro:hasPermission>
	</form>
</body>
</html>