<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>后台管理系统</title>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
  </head>
  <body>
  		<h1 align="center"><strong>后台管理系统</strong></h1>
  		<hr>
  		<div class="container">
  		<div class="col-lg-4"></div>
  		<div class="col-lg-4">
		<form id="loginForm" action="${ctx}/login" method="post" >
  			<div class="col-ms-4 form-group">
    			<label for="username" class="control-label">用户名</label>
    			<div>
    			</div>
    			<input type="text" class="form-control" id="username" name="username"placeholder="用户名">
  			</div>
  			<div class="form-group">
    			<label for="password" class="control-label">密码</label>
    			<input type="password" class="form-control" id="password" name="password" placeholder="密码">
  			</div>
  			<div class="form-group">
  				<input type="checkbox" id="rememberMe" name="rememberMe">&nbsp;下次自动登录
  				<button type="submit" class="btn btn-primary">登录</button>
  			</div>
		</form>
		</div>
  </div>
  </body>
</html>