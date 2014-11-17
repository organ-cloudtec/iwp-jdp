<%@page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<title>视频监控管理</title>
	<%@include file="/WEB-INF/views/include/head.jsp"%>
	<style type="text/css">
		.sort{color:#0663A2;cursor:pointer;}
		.controls{ 
		     display:inline-block;
		}
	</style>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			if(s != null && s != "")
				$("#pageSize").val(s);
			$("#searchForm").submit();
    		return false;
   		}
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<ul class="nav nav-tabs"> 
		<li class="active"><a href="${ctx}/vm/traffic/tip/">交通诱导列表</a></li>
		<li><a href="#" >添加交通诱导</a></li>
	</ul>
	</br>
	<div id="content" class="row-fluid">
		<div class="col-sm-6 col-md-6">
			<div class="thumbnail">
				<img alt="100%x200" src="${ctxStatic}/images/vm/vt1.jpg" data-holder-rendered="true" style="height: 200px; width: 100%; display: block;">
				<div class="caption">
            		<h4>1号交通诱导屏  位于坪西路 </h4>
            		<p><a href="#" class="btn btn-primary" role="button">诱导信息设置</a></p>
          		</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-6">
			<div class="thumbnail">
				<img alt="100%x200" src="${ctxStatic}/images/vm/vt2.jpg" data-holder-rendered="true" style="height: 200px; width: 100%; display: block;">
				<div class="caption">
            		<h4>2号交通诱导屏  位于新大路</h4>
            		<p><a href="#" class="btn btn-primary" role="button">诱导信息设置</a></p>
          		</div>
			</div>
		</div>
	</div>
</body>
</html>