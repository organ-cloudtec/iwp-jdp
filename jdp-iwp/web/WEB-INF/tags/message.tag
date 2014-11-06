<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="content" type="java.lang.String" required="true" description="消息内容"%>
<%@ attribute name="type" type="java.lang.String" description="消息类型：info、succeed、warning、error、loading、face-sad、face-smaile、自己添加png图片到skins/icons目录"%>
<script type="text/javascript">dialog().close().remove();</script>
<c:if test="${not empty content}">
	<c:if test="${not empty type}">
		<c:set var="ctype" value="${type}"/>
	</c:if>
	<c:if test="${empty type}">
		<c:set var="ctype" value="${fn:indexOf(content,'失败') eq -1?'succeed':'error'}"/>
	</c:if>
	<script type="text/javascript">
		art.dialog({
			//改变位置
			 top: '50',
			//锁屏
			lock: true,
		    opacity: 0.87,	// 透明度
		    //内容
		    content: "${content}",
		    //消息类型，图片
		    icon: "${ctype}",
		    //定时关闭
		   // time: 2,
		    ok: function () {
		    	this.title('3秒后自动关闭').time(3);
		        return false;
		    },
		    cancelVal: '关闭',
		    cancel: true
		});
	</script>
</c:if>