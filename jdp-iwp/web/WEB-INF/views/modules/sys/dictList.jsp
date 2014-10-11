<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">.table td i{margin:0 2px;}</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnImport").click(function(){
				$("#importBox").removeClass("hide");
			});
		});
		function updateSort(){
			var d = dialog({
			    content: '导入正在进行。。。。。。'
			});
			d.show();
			$("#listForm").submit();
		}
	</script>
</head>
<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/sys/dict/import" method="post" enctype="multipart/form-data"
			style="padding-left:20px;text-align:center;" class="form-search" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/sys/dict/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/dict/">枚举项列表</a></li>
		<shiro:hasPermission name="sys:dict:edit">
			<li><a href="${ctx}/sys/dict/form">枚举项添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="listForm" action="${ctx}/sys/dict/updateSort" method="post">
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
						<shiro:hasPermission name="sys:dict:edit">
							<input type="hidden" name="ids" value="${dict.recid}"/>
							<input name="sorts" value="${dict.sort}" style="width:50px;margin:0;padding:0;text-align:center;" />
						</shiro:hasPermission>
						<shiro:lacksPermission name="sys:dict:edit">
							${dict.sort}
						</shiro:lacksPermission>
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
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存排序"/>
				&nbsp;<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				&nbsp;<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
			</div>
		</shiro:hasPermission>
	</form:form>
</body>
</html>
