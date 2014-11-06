<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Bootstrap 演示</title>
	<script type="text/javascript"> 
		$(document).ready(function() {
			$(".panel-heading a").click(function(){
				$('.accordion-toggle i').removeClass('icon-chevron-down');
				$('.accordion-toggle i').addClass('icon-chevron-right');
				if(!$($(this).attr('href')).hasClass('in')){
					$(this).children('i').removeClass('icon-chevron-right');
					$(this).children('i').addClass('icon-chevron-down');
				}
			});
			$(".panel-collapse a").click(function(){
				$("#menu li").removeClass("active");
				$("#menu li i").removeClass("icon-white");
				$(this).parent().addClass("active");
				$(this).children("i").addClass("icon-white");
			});
			$(".panel-collapse a:first i").click();
		});
	</script>
</head>
<body>
    <div class="accordion" id="menu">
	    <c:set var="menuList" value="${fns:getMenuList()}"/>
	    <c:set var="firstMenu" value="true"/>
	    <c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
	    	<!-- menu.parent.recid eq (not empty parentId?parentId:'TOP_MENU_ID') && -->
	    	<c:if test="${menu.parent.recid eq 'TOP_MENU_ID' && menu.isShow eq '1'}">
				<div class="panel panel-default">
				    <div class="panel-heading">
				    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu" href="#collapse${menu.recid}" title="${menu.remarks}">
				    	<i class="icon-chevron-${firstMenu?'down':'right'}"></i>
				    	&nbsp;${menu.name}</a>
				    </div>
				    <div id="collapse${menu.recid}" class="panel-collapse collapse ${firstMenu?'in':''}">
						<div class="panel-body">
							<ul class="nav nav-list">
							<c:forEach items="${menuList}" var="menuChild">
								<c:if test="${(menuChild.parent.recid eq menu.recid) && menuChild.isShow eq '1'}">
									<li>
									<!--  -->
									<!--  -->
									<!-- onclick='jump("${not empty menuChild.target?menuChild.target:'mainFrame'}","${ctx}${not empty menuChild.url?menuChild.url:'/404'}")' -->
									<a href="${ctx}${not empty menuChild.url?menuChild.url:'/404'}" target="${not empty menuChild.target?menuChild.target:'mainFrame'}">
									<i class="icon-${not empty menuChild.icon?menuChild.icon:'circle-arrow-down'}"></i>&nbsp;${menuChild.name}</a></li>
									<c:set var="firstMenu" value="false"/>
								</c:if>
							</c:forEach>
							</ul>
						</div>
				    </div>
				</div>
			</c:if>
		</c:forEach>
	</div>
</body>
</html>