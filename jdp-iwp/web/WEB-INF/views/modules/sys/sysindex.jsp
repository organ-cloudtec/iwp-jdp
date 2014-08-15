<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>后台管理系统</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
<div id="main">
	<!-- head -->
	 <div id="head" class="navbar navbar-inverse" role="navigation">
      <div class="container">
      	<!-- project name -->
        <div class="navbar-header">
        	 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-01">
		      <span class="sr-only">Toggle navigation</span>
		    </button>
          <a class="navbar-brand" href="#">${fns:getConfig('productName')}</a>
        </div>
        <!-- menu start -->
        <div class="navbar-collapse collapse" id="menu">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#" >首页</a></li>
            <li>
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">系统管理</a>
            	<ul class="dropdown-menu">
            		  <li><a href="${ctx}/sys/user/" target="mainFrame">用户管理</a></li>
			          <li><a href="${ctx}/sys/menu/" target="mainFrame">资源管理</a></li>
			          <li><a href="${ctx}/sys/role/" target="mainFrame">角色管理</a></li>
            	</ul>
            </li>
            <li><a href="#">CMS管理</a></li>
            <li><a href="#">帮助</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
	      	 <li class="dropdown">
			    <a class="dropdown-toggle" data-toggle="dropdown" href="#" title="个人信息">您好, <shiro:principal property="name"/></a>
			    <ul class="dropdown-menu">
			      <li><a href="${ctx}/sys/user/info" target="mainFrame"><i class="icon-user"></i>&nbsp; 个人信息</a></li>
			      <li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame"><i class="icon-lock"></i>&nbsp;  修改密码</a></li>
			    </ul>
	  	 	 </li>
	  	 	<li><a href="${ctx}/logout" title="退出登录">退出</a></li>
	  	 	<li>&nbsp;</li>
   		  </ul>
   		  <!-- 
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form> -->
        </div><!-- menu end -->
      </div>
    </div>
	<!-- main body -->
	<div id="mainBody" class="container">
		<div id="content" class="row" >
			<div id="left" class="col-md-2 sidebar panel panel-default"><!--  -->
				<iframe id="menuFrame" name="menuFrame" src="${ctx}/sys/menu/tree" style="overflow:visible;"
					scrolling="no" frameborder="no" width="100%" height="650"></iframe>
			</div>
			<div id="openClose" class="close">&nbsp;</div>
			<div id="right" class="col-md-10 main panel"><!--  -->
				<iframe id="mainFrame" name="mainFrame" src="${ctx}/sys/user" style="overflow:visible;"
					scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
			</div>
		</div>
	</div><!-- main body end -->
	<!-- foot  -->
 	<footer id="footer" class="footer container">
      <p class="pull-right"><a href="#">返回顶部</a></p>
      <p class="center">Copyright &copy; 2012-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} - Powered By <a href="http://www.svnchina.com/svn/iwp" target="_blank">iwp</a> ${fns:getConfig('version')}</p>
    </footer>
</div>
</body>
</html>