<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>后台管理系统</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<%@ include file="/WEB-INF/views/include/dialog.jsp" %>
</head>
<body>
<div class="container-fluid">
	
	
 </div>
    <script type="text/javascript">
    $(function () {
    	$.jBox("iframe:http://www.baidu.com", {
    	    title: "百度一下",
    	    width: 800,
    	    height: 350,
    	    buttons: { '关闭': true }
    	});
    })
    </script>
</body>
</html>