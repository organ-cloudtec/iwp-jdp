<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="输入框名称"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="输入框值"%>
<i id="${id}Icon" class="icon-${not empty value?value:' hide'}"></i>&nbsp;<label id="${id}IconLabel">${not empty value?value:'无'}</label>&nbsp;
<input id="${id}" name="${name}" type="hidden" value="${value}"/><a id="${id}Button" href="javascript:" class="btn">选择</a>&nbsp;&nbsp;
<script type="text/javascript">
	$("#${id}Button").click(function(){
		top.dialog({
			url: "${ctx}/tag/iconselect?value="+$("#${id}").val(),
			title: "选择图片",
			width: 700,
			hight: $(top.document).height()-180,
			ok:function(){
				var icon = this.iframeNode.contentWindow.icon.value;
				$("#${id}Icon").attr("class", "icon-"+icon);
                $("#${id}IconLabel").text(icon);
                $("#${id}").val(icon);
			},
			okValue: '确定',
			cancel:function(){
				 $("#${id}Icon").attr("class", "icon-hide");
	             $("#${id}IconLabel").text("无");
	             $("#${id}").val("");
			},
			cancelValue: '清除'
		}).showModal();
	});
</script>