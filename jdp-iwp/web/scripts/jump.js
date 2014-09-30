function operator(){
	//给所有 .jump 的动态绑定 click事件，jq load处理
	$(document).on('click',".jump",function(){
		var url = $(this).attr('data-href');
		var target = $(this).attr('target');
		if(target == undefined)
			target="mainFrame";//默认展示的div id
		jump(target,url);
		return false;
	});
}
function jump(target,url){
	top.$('#'+target).load(url,function(responseText,textStatus,XMLHttpRequest){
		//responseText:请求返回的内容
		//textStatus:请求状态：success、error、notmodified、timeout这4种
		//XMLHttpRequest:XMLHttpRequest对象 
		if(textStatus == 'success')
			return false;
		this.innerHTML=responseText;
	});
	
}

