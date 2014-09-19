<%@page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@include file="/WEB-INF/views/include/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户管理</title>
	<style type="text/css">
		.sort{color:#0663A2;cursor:pointer;}
	</style>
</head>
<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/sys/organ/import" method="post" enctype="multipart/form-data"
			style="padding-left:20px;text-align:center;" class="form-search" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/sys/organ/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/organ/">单位列表</a></li>
		<li><a href="${ctx}/sys/organ/form">单位添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="organ" action="${ctx}/sys/organ/" method="post" class="breadcrumb form-search">
		<div>
			<label>单位代码：</label><form:input path="code" htmlEscape="false" maxlength="50" class="input-small"/>
		</div>
		<div style="margin-top:8px;">
			<label>单位名称：</label>
			<form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
			&nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
			&nbsp;<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
		</div>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>单位代码</th><th>单位名称</th><th>排序</th><th>等级</th><th>操作</th></tr></thead>
		<tbody>
		<c:forEach items="${organs.content}" var="organ">
			<tr>
				<td><a href="${ctx}/sys/organ/form?recid=${organ.recid}">${organ.code}</a></td>
				<td>${organ.name}</td>
				<td>${organ.sort}</td>
				<td>${organ.grade}</td>
				<td>
				<a href="${ctx}/sys/organ/delete?recid=${organ.recid}">删除</a>  
				<a href="${ctx}/sys/organ/form?recid=${organ.recid}">修改</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<tags:pagination page="${organs}" paginationSize="10"/>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#btnImport").click(function(){
			$("#importBox").removeClass("hide");
		});
	});
	</script>
</body>

</html>