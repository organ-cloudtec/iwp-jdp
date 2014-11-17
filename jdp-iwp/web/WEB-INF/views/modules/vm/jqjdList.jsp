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
		<li class="active"><a href="${ctx}/vm/traffic/jqjd/">景区监控列表</a></li>
		<li><a href="#" >衔接景区监控</a></li>
	</ul>
	</br>
	<div id="content" class="row-fluid">
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img alt="100%x200" src="${ctxStatic}/images/vm/t1.jpg" data-holder-rendered="true" style="height: 200px; width: 100%; display: block;">
				<div class="caption">
            		<h4><a href="#" class="btn btn-primary" role="button">播放</a>1号  西涌海滨浴场 </h4>
          		</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img alt="100%x200" src="${ctxStatic}/images/vm/t2.jpg" data-holder-rendered="true" style="height: 200px; width: 100%; display: block;">
				<div class="caption">
            		<h4><a href="#" class="btn btn-primary" role="button">播放</a>2号  东涌海滨浴场</h4>
          		</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img alt="100%x200" src="${ctxStatic}/images/vm/t3.jpg" style="height: 200px; width: 100%; display: block;">
				<div class="caption">
            		<h4><a href="#" class="btn btn-primary" role="button">播放</a>3号 西涌海滨浴场</h4>
          		</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img alt="100%x200" src="${ctxStatic}/images/vm/t4.jpg"  data-holder-rendered="true" style="height: 200px; width: 100%; display: block;">
				<div class="caption">
            		<h4><a href="#" class="btn btn-primary" role="button">播放</a>4号  东涌海滨浴场</h4>
          		</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img alt="100%x200" src="${ctxStatic}/images/vm/t1.jpg" data-holder-rendered="true" style="height: 200px; width: 100%; display: block;">
				<div class="caption">
            		<h4><a href="#" class="btn btn-primary" role="button">播放</a>5号  南西公路</h4>
          		</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img alt="100%x200" src="${ctxStatic}/images/vm/t2.jpg" data-holder-rendered="true" style="height: 200px; width: 100%; display: block;">
				<div class="caption">
            		<h4><a href="#" class="btn btn-primary" role="button">播放</a>6号  南西公路</h4>
          		</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img alt="100%x200" src="${ctxStatic}/images/vm/t3.jpg" style="height: 200px; width: 100%; display: block;">
				<div class="caption">
            		<h4><a href="#" class="btn btn-primary" role="button">播放</a>7号  南西公路</h4>
          		</div>
			</div>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<img alt="100%x200" src="${ctxStatic}/images/vm/t4.jpg"  data-holder-rendered="true" style="height: 200px; width: 100%; display: block;">
				<div class="caption">
            		<h4><a href="#" class="btn btn-primary" role="button">播放</a>8号  南西公路</h4>
          		</div>
			</div>
		</div>
	</div>
</body>
</html>