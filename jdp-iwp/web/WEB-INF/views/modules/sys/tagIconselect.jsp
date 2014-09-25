<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x:hidden;overflow-y:auto;">
<head>
    <title>图标选择</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
    <style type="text/css">
		.the-icons {padding:25px 10px 15px;list-style:none;}
		.the-icons li {float:left;width:22%;line-height:25px;margin:2px 5px;cursor:pointer;}
		.the-icons i {margin:1px 5px;} .the-icons li:hover {background-color:#efefef;}
        .the-icons li.active {background-color:#0088CC;color:#ffffff;}
    </style>
    <script type="text/javascript">
   		 var dialog = top.dialog.get(window);
	    $(document).ready(function(){
	    	$("#icons li").click(function(){
	    		$("#icons li").removeClass("active");
	    		//$("#icons li i").removeClass("glyphicon glyphicon-white");
	    		$(this).addClass("active");
	    		//$(this).children("i").addClass("glyphicon glyphicon-white");
	    		$("#icon").val($(this).text());
	    	});
	    	$("#icons li").each(function(){
	    		if ($(this).text()=="${value}"){
	    			$(this).click();
	    		}
	    	});
	    	$("#icons li").dblclick(function(){
	    		dialog._trigger("ok");
	    	});
	    });
    </script>
</head>
<body>
<input type="hidden" id="icon" value="${value}" />
<ul class="the-icons clearfix" id="icons">
    <li><i class="glyphicon glyphicon-glass"></i>glass</li>
    <li><i class="glyphicon glyphicon-music"></i>music</li>
    <li><i class="glyphicon glyphicon-search"></i>search</li>
    <li><i class="glyphicon glyphicon-envelope"></i>envelope</li>
    <li><i class="glyphicon glyphicon-heart"></i>heart</li>
    <li><i class="glyphicon glyphicon-star"></i>star</li>
    <li><i class="glyphicon glyphicon-star-empty"></i>star-empty</li>
    <li><i class="glyphicon glyphicon-user"></i>user</li>
    <li><i class="glyphicon glyphicon-film"></i>film</li>
    <li><i class="glyphicon glyphicon-th-large"></i>th-large</li>
    <li><i class="glyphicon glyphicon-th"></i>th</li>
    <li><i class="glyphicon glyphicon-th-list"></i>th-list</li>
    <li><i class="glyphicon glyphicon-ok"></i>ok</li>
    <li><i class="glyphicon glyphicon-remove"></i>remove</li>
    <li><i class="glyphicon glyphicon-zoom-in"></i>zoom-in</li>
    <li><i class="glyphicon glyphicon-zoom-out"></i>zoom-out</li>
    <li><i class="glyphicon glyphicon-off"></i>off</li>
    <li><i class="glyphicon glyphicon-signal"></i>signal</li>
    <li><i class="glyphicon glyphicon-cog"></i>cog</li>
    <li><i class="glyphicon glyphicon-trash"></i>trash</li>
    <li><i class="glyphicon glyphicon-home"></i>home</li>
    <li><i class="glyphicon glyphicon-file"></i>file</li>
    <li><i class="glyphicon glyphicon-time"></i>time</li>
    <li><i class="glyphicon glyphicon-road"></i>road</li>
    <li><i class="glyphicon glyphicon-download-alt"></i>download-alt</li>
    <li><i class="glyphicon glyphicon-download"></i>download</li>
    <li><i class="glyphicon glyphicon-upload"></i>upload</li>
    <li><i class="glyphicon glyphicon-inbox"></i>inbox</li>

    <li><i class="glyphicon glyphicon-play-circle"></i>play-circle</li>
    <li><i class="glyphicon glyphicon-repeat"></i>repeat</li>
    <li><i class="glyphicon glyphicon-refresh"></i>refresh</li>
    <li><i class="glyphicon glyphicon-list-alt"></i>list-alt</li>
    <li><i class="glyphicon glyphicon-lock"></i>lock</li>
    <li><i class="glyphicon glyphicon-flag"></i>flag</li>
    <li><i class="glyphicon glyphicon-headphones"></i>headphones</li>
    <li><i class="glyphicon glyphicon-volume-off"></i>volume-off</li>
    <li><i class="glyphicon glyphicon-volume-down"></i>volume-down</li>
    <li><i class="glyphicon glyphicon-volume-up"></i>volume-up</li>
    <li><i class="glyphicon glyphicon-qrcode"></i>qrcode</li>
    <li><i class="glyphicon glyphicon-barcode"></i>barcode</li>
    <li><i class="glyphicon glyphicon-tag"></i>tag</li>
    <li><i class="glyphicon glyphicon-tags"></i>tags</li>
    <li><i class="glyphicon glyphicon-book"></i>book</li>
    <li><i class="glyphicon glyphicon-bookmark"></i>bookmark</li>
    <li><i class="glyphicon glyphicon-print"></i>print</li>
    <li><i class="glyphicon glyphicon-camera"></i>camera</li>
    <li><i class="glyphicon glyphicon-font"></i>font</li>
    <li><i class="glyphicon glyphicon-bold"></i>bold</li>
    <li><i class="glyphicon glyphicon-italic"></i>italic</li>
    <li><i class="glyphicon glyphicon-text-height"></i>text-height</li>
    <li><i class="glyphicon glyphicon-text-width"></i>text-width</li>
    <li><i class="glyphicon glyphicon-align-left"></i>align-left</li>
    <li><i class="glyphicon glyphicon-align-center"></i>align-center</li>
    <li><i class="glyphicon glyphicon-align-right"></i>align-right</li>
    <li><i class="glyphicon glyphicon-align-justify"></i>align-justify</li>
    <li><i class="glyphicon glyphicon-list"></i>list</li>

    <li><i class="glyphicon glyphicon-indent-left"></i>indent-left</li>
    <li><i class="glyphicon glyphicon-indent-right"></i>indent-right</li>
    <li><i class="glyphicon glyphicon-facetime-video"></i>facetime-video</li>
    <li><i class="glyphicon glyphicon-picture"></i>picture</li>
    <li><i class="glyphicon glyphicon-pencil"></i>pencil</li>
    <li><i class="glyphicon glyphicon-map-marker"></i>map-marker</li>
    <li><i class="glyphicon glyphicon-adjust"></i>adjust</li>
    <li><i class="glyphicon glyphicon-tint"></i>tint</li>
    <li><i class="glyphicon glyphicon-edit"></i>edit</li>
    <li><i class="glyphicon glyphicon-share"></i>share</li>
    <li><i class="glyphicon glyphicon-check"></i>check</li>
    <li><i class="glyphicon glyphicon-move"></i>move</li>
    <li><i class="glyphicon glyphicon-step-backward"></i>step-backward</li>
    <li><i class="glyphicon glyphicon-fast-backward"></i>fast-backward</li>
    <li><i class="glyphicon glyphicon-backward"></i>backward</li>
    <li><i class="glyphicon glyphicon-play"></i>play</li>
    <li><i class="glyphicon glyphicon-pause"></i>pause</li>
    <li><i class="glyphicon glyphicon-stop"></i>stop</li>
    <li><i class="glyphicon glyphicon-forward"></i>forward</li>
    <li><i class="glyphicon glyphicon-fast-forward"></i>fast-forward</li>
    <li><i class="glyphicon glyphicon-step-forward"></i>step-forward</li>
    <li><i class="glyphicon glyphicon-eject"></i>eject</li>
    <li><i class="glyphicon glyphicon-chevron-left"></i>chevron-left</li>
    <li><i class="glyphicon glyphicon-chevron-right"></i>chevron-right</li>
    <li><i class="glyphicon glyphicon-plus-sign"></i>plus-sign</li>
    <li><i class="glyphicon glyphicon-minus-sign"></i>minus-sign</li>
    <li><i class="glyphicon glyphicon-remove-sign"></i>remove-sign</li>
    <li><i class="glyphicon glyphicon-ok-sign"></i>ok-sign</li>

    <li><i class="glyphicon glyphicon-question-sign"></i>question-sign</li>
    <li><i class="glyphicon glyphicon-info-sign"></i>info-sign</li>
    <li><i class="glyphicon glyphicon-screenshot"></i>screenshot</li>
    <li><i class="glyphicon glyphicon-remove-circle"></i>remove-circle</li>
    <li><i class="glyphicon glyphicon-ok-circle"></i>ok-circle</li>
    <li><i class="glyphicon glyphicon-ban-circle"></i>ban-circle</li>
    <li><i class="glyphicon glyphicon-arrow-left"></i>arrow-left</li>
    <li><i class="glyphicon glyphicon-arrow-right"></i>arrow-right</li>
    <li><i class="glyphicon glyphicon-arrow-up"></i>arrow-up</li>
    <li><i class="glyphicon glyphicon-arrow-down"></i>arrow-down</li>
    <li><i class="glyphicon glyphicon-share-alt"></i>share-alt</li>
    <li><i class="glyphicon glyphicon-resize-full"></i>resize-full</li>
    <li><i class="glyphicon glyphicon-resize-small"></i>resize-small</li>
    <li><i class="glyphicon glyphicon-plus"></i>plus</li>
    <li><i class="glyphicon glyphicon-minus"></i>minus</li>
    <li><i class="glyphicon glyphicon-asterisk"></i>asterisk</li>
    <li><i class="glyphicon glyphicon-exclamation-sign"></i>exclamation-sign</li>
    <li><i class="glyphicon glyphicon-gift"></i>gift</li>
    <li><i class="glyphicon glyphicon-leaf"></i>leaf</li>
    <li><i class="glyphicon glyphicon-fire"></i>fire</li>
    <li><i class="glyphicon glyphicon-eye-open"></i>eye-open</li>
    <li><i class="glyphicon glyphicon-eye-close"></i>eye-close</li>
    <li><i class="glyphicon glyphicon-warning-sign"></i>warning-sign</li>
    <li><i class="glyphicon glyphicon-plane"></i>plane</li>
    <li><i class="glyphicon glyphicon-calendar"></i>calendar</li>
    <li><i class="glyphicon glyphicon-random"></i>random</li>
    <li><i class="glyphicon glyphicon-comment"></i>comment</li>
    <li><i class="glyphicon glyphicon-magnet"></i>magnet</li>

    <li><i class="glyphicon glyphicon-chevron-up"></i>chevron-up</li>
    <li><i class="glyphicon glyphicon-chevron-down"></i>chevron-down</li>
    <li><i class="glyphicon glyphicon-retweet"></i>retweet</li>
    <li><i class="glyphicon glyphicon-shopping-cart"></i>shopping-cart</li>
    <li><i class="glyphicon glyphicon-folder-close"></i>folder-close</li>
    <li><i class="glyphicon glyphicon-folder-open"></i>folder-open</li>
    <li><i class="glyphicon glyphicon-resize-vertical"></i>resize-vertical</li>
    <li><i class="glyphicon glyphicon-resize-horizontal"></i>resize-horizontal</li>
    <li><i class="glyphicon glyphicon-hdd"></i>hdd</li>
    <li><i class="glyphicon glyphicon-bullhorn"></i>bullhorn</li>
    <li><i class="glyphicon glyphicon-bell"></i>bell</li>
    <li><i class="glyphicon glyphicon-certificate"></i>certificate</li>
    <li><i class="glyphicon glyphicon-thumbs-up"></i>thumbs-up</li>
    <li><i class="glyphicon glyphicon-thumbs-down"></i>thumbs-down</li>
    <li><i class="glyphicon glyphicon-hand-right"></i>hand-right</li>
    <li><i class="glyphicon glyphicon-hand-left"></i>hand-left</li>
    <li><i class="glyphicon glyphicon-hand-up"></i>hand-up</li>
    <li><i class="glyphicon glyphicon-hand-down"></i>hand-down</li>
    <li><i class="glyphicon glyphicon-circle-arrow-right"></i>circle-arrow-right</li>
    <li><i class="glyphicon glyphicon-circle-arrow-left"></i>circle-arrow-left</li>
    <li><i class="glyphicon glyphicon-circle-arrow-up"></i>circle-arrow-up</li>
    <li><i class="glyphicon glyphicon-circle-arrow-down"></i>circle-arrow-down</li>
    <li><i class="glyphicon glyphicon-globe"></i>globe</li>
    <li><i class="glyphicon glyphicon-wrench"></i>wrench</li>
    <li><i class="glyphicon glyphicon-tasks"></i>tasks</li>
    <li><i class="glyphicon glyphicon-filter"></i>filter</li>
    <li><i class="glyphicon glyphicon-briefcase"></i>briefcase</li>
    <li><i class="glyphicon glyphicon-fullscreen"></i>fullscreen</li>
</ul>
</body>