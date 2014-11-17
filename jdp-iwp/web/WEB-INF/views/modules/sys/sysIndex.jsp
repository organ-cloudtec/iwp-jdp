<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	<script type="text/javascript"> 
		$(document).ready(function() {
			jump('menuFrame','${ctx}/sys/menu/tree');
		/* 	operator();
			$("#menu a.menu").click(function(){
				$("#menu li.menu").removeClass("active");
				$(this).parent().addClass("active");
				if(!$("#openClose").hasClass("close")){
					$("#openClose").click();
				}
			}); 
		*/
		});
	</script>
</head>
<body>
	<div id="main">
		<!-- head -->
		 <div id="head" class="navbar navbar-default" role="navigation">
	      <div class="container">
	      	<!-- project name -->
	        <div class="navbar-header">
	        	 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
			      <span class="sr-only">Toggle navigation</span>
			    </button>
	          <a class="navbar-brand" href="#">${fns:getConfig('productName')}</a>
	        </div>
	        <!-- menu start -->
	        <div class="navbar-collapse collapse" id="top">
	          <ul class="nav navbar-nav navbar-right">
	          	 <li class="menu"><a class="icon-home" href="#" onblur="切换到首页"></a></li>
	          	 <!-- 消息通知 -->
	          	 <li class="dropdown">
	          	 	<a class="dropdown-toggle icon-bell" data-toggle="dropdown" href="#" title="通知"></a>
	          	 	<ul class="dropdown-menu">
	          	 	<!-- target="mainFrame" -->
				      <li><a href="${ctx}/sys/user/info" target ="mainFrame" ><i class="icon-user"></i>&nbsp; 个人信息</a></li>
				      <li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame" ><i class="icon-lock"></i>&nbsp;  修改密码</a></li>
				      <li><a href="${ctx}/logout" title="退出登录"><i class="icon-off"></i>&nbsp;  退出</a></li>
				    </ul>	
	          	 </li>
	          	 <!-- 用户信息 -->
		      	 <li class="dropdown light-blue">
				    <a class="dropdown-toggle icon-user" data-toggle="dropdown" href="#" title="个人信息">
				    	您好, <shiro:principal property="name"/></a>
				    <ul class="dropdown-menu">
				      <li><a href="${ctx}/sys/user/info" target="mainFrame" ><i class="icon-user"></i>&nbsp; 个人信息</a></li>
				      <li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame" ><i class="icon-lock"></i>&nbsp;  修改密码</a></li>
				      <li><a href="${ctx}/logout" title="退出登录"><i class="icon-off"></i>&nbsp;  退出</a></li>
				    </ul>
		  	 	 </li>
	   		  </ul>
	        </div><!-- menu end -->
	      </div>
	    </div>
	    <div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="menuFrame" class="sidebar col-md-2 nav" role="complementary">
				</div>
				<div id="mainDiv" class="col-md-10 main panel" role="main"> <!-- id="mainFrame" -->
					<div style="color: #000000">
						<iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;"
							scrolling="no" frameborder="no" width="100%" height="100%"></iframe>
					</div>
				</div>
			</div>
		</div>
		<!-- foot  -->
	 	<footer id="footer" class="footer container">
	      <p class="pull-right"><a href="#">返回顶部</a></p>
	      <p class="center">Copyright &copy; 2012-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} - Powered By <a href="http://www.svnchina.com/svn/iwp" target="_blank">iwp</a> ${fns:getConfig('version')}</p>
	    </footer>
	</div>
	<script type="text/javascript">
		function reinitIframe(){
			var iframe = document.getElementById("mainFrame");
			try{
				var bHeight = iframe.contentWindow.document.body.scrollHeight;
				var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
				var height = Math.max(bHeight, dHeight);
				iframe.height =  height;
			}catch (ex){}
		}
		window.setInterval("reinitIframe()", 200);
	</script>
</body>
</html>