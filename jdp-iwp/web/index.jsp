<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>后台管理系统</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
<div class="container-fluid">

	<div class="row">
		<div class="col-md-12">
		<nav class="navbar navbar-default " role="navigation">
		  <div class="navbar-header">
		    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		      <span class="sr-only">Toggle navigation</span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		    </button>
		  </div>
		  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		    <ul class="nav navbar-nav">
		      <li><a href="${ctx}">首页</a></span></li>
		      <li><a href="${ctx}/sys/">系统管理</a></li>
		    </ul>
		    
		    <ul class="nav navbar-nav navbar-right">
		      <li class="dropdown">
		        <a href="#" class="dropdown-toggle" data-toggle="dropdown">您好，测试账号 <b class="caret"></b></a>
		        <ul class="dropdown-menu">
		          <li><a href="#">修改密码</a></li>
		          <li><a href="#">修改基本信息</a></li>
		          <li class="divider"></li>
		          <li><a href="#">屏幕锁定</a></li>
		          <li><a href="#">退出登录</a></li>
		        </ul>
		      </li>
		    </ul>
			</div>
	</nav>
		</div>
		<div class="col-md-6">
			<div class="panel panel-default">
  				<div class="panel-heading">待办事项</div>
  				<div class="panel-body">
  					<ul>
  						<li>待处理的邮件需要处理</li>
  						<li>待处理的公文需要处理</li>
  						<li>待处理的消息需要处理</li>
  					</ul>
  				</div>
			</div>
			<div class="panel panel-default">
  				<div class="panel-heading">这里是3D统计图展示示例</div>
  				<div id="div01" class="panel-body">
  				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="panel panel-default">
  				<div class="panel-heading">快捷方式</div>
  				<div class="panel-body">
  				</div>
			</div>
			<div class="panel panel-default">
  				<div class="panel-heading">这里是一般统计图展示示例</div>
  				<div id="div02" class="panel-body">
  				</div>
			</div>
		</div>
	</div>
 </div>
    <script type="text/javascript">
    $('.collapse').collapse();
    $(function () {
        // Set up the chart
        var chart = new Highcharts.Chart({
            chart: {
                renderTo: 'div01',
                type: 'column',
                margin: 75,
                options3d: {
                    enabled: true,
                    alpha: 15,
                    beta: 15,
                    depth: 50,
                    viewDistance: 25
                }
            },
            title: {
                text: 'Chart rotation demo'
            },
            plotOptions: {
                column: {
                    depth: 25
                }
            },
            series: [{
                data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
            }]
        });
        

        // Activate the sliders
        $('#R0').on('change', function(){
            chart.options.chart.options3d.alpha = this.value;
            showValues();
            chart.redraw(false);
        });
        $('#R1').on('change', function(){
            chart.options.chart.options3d.beta = this.value;
            showValues();
            chart.redraw(false);
        });

        function showValues() {
            $('#R0-value').html(chart.options.chart.options3d.alpha);
            $('#R1-value').html(chart.options.chart.options3d.beta);
        }
        showValues();
        
        $('#div02').highcharts({
            title: {
                text: 'Monthly Average Temperature',
                x: -20 //center
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                title: {
                    text: 'Temperature (°C)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: '°C'
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: 'Tokyo',
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
            }, {
                name: 'New York',
                data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
            }, {
                name: 'Berlin',
                data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
            }, {
                name: 'London',
                data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
            }]
        });
    });
        
    </script>
</body>
</html>