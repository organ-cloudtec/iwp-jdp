<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	    <title>后台管理系统</title>
   		<%@include file="/WEB-INF/views/include/head.jsp"%>
		<meta name="description" content="后台管理系统" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	</head>

	<body class="login-layout light-login">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf green"></i>
									<span class="red">后台</span>
									<span class="grey" id="id-text2">管理系统</span>
								</h1>
								<h4 class="blue" id="id-company-text">&copy; Company Name</h4>
							</div>
							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												请 输 入 登 陆 信 息
											</h4>
											<div class="space-6"></div>
											<form id="loginForm" action="${ctx}/a/login" method="post" >
												<label class="block clearfix">
													<span class="block input-icon input-icon-right">
														<input type="text" class="form-control" placeholder="Username" 
														name="username" />
														<i class="ace-icon fa fa-user"></i>
													</span>
												</label>

												<label class="block clearfix">
													<span class="block input-icon input-icon-right">
														<input type="password" class="form-control" placeholder="Password" 
														name="password"/>
														<i class="ace-icon fa fa-lock"></i>
													</span>
												</label>

												<div class="space"></div>

												<div class="clearfix">
													<label class="inline">
														<input type="checkbox" class="ace" name="rememberMe"/>
														<span class="lbl"> Remember Me</span>
													</label>

													<button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
														<i class="ace-icon fa fa-key"></i>
														<span class="bigger-110">登 陆</span>
													</button>
												</div>

												<div class="space-4"></div>
											</form>
											<div class="space-6"></div>
										</div><!-- /.widget-main -->
									</div>
								 </div><!-- /.login-box -->
							</div><!-- /.position-relative -->

							<div class="navbar-fixed-top align-right">
								<br />
								&nbsp;
								<a id="btn-login-dark" href="#">Dark</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-blur" href="#">Blur</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-light" href="#">Light</a>
								&nbsp; &nbsp; &nbsp;
							</div>
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			//you don't need this, just used for changing background
			jQuery(function($) {
			 $('#btn-login-dark').on('click', function(e) {
				$('body').attr('class', 'login-layout');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'blue');
				e.preventDefault();
			 });
			 $('#btn-login-light').on('click', function(e) {
				$('body').attr('class', 'login-layout light-login');
				$('#id-text2').attr('class', 'grey');
				$('#id-company-text').attr('class', 'blue');
				e.preventDefault();
			 });
			 $('#btn-login-blur').on('click', function(e) {
				$('body').attr('class', 'login-layout blur-login');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'light-blue');
				e.preventDefault();
			 });
			 
			});
		</script>
	</body>
</html>
