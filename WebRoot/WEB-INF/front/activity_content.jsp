<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html class="no-js">
<head>
<title>${bean.title}</title>
<meta name="keywords" content="${bean.keywords}" />
<meta name="description" content="${bean.summary}" />

<jsp:include page="header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/theme/newest/css/art.css" />
<style type="text/css">
.ac-content-wrap {
	clear: both;
}

.ac-content-wrap img {
	width: 100%;
}
</style>
</head>
<body>

<div class="g-doc">
    <div class="top-fxied">
            <header class="header"> 
                <div class="back"><a href="/ac.html"><span class="icon-back"></span></a></div> 
                <div class="title">分销软文</div> 
                <div class="subMark"><p></p></div> 
            </header>
            
  </div>
    
    <div class="scroll-content">
    	<div class="article">
       	  <div class="hd">
       	  		<h1>【<sp:dict_label_tag type="article_classify" value="${bean.classify}"></sp:dict_label_tag>】${bean.title}</h1>
       	  		<span class="sTime"><sp:dateformat time="${bean.releasedate}" format="yyyy-MM-dd" /></span></div>
            <div class="desc">点击右上角将文章分享给朋友圈，可以你带来分销收入;
            	<c:if test="${login==0}">
					您还没有登录，分销前请先登录，否则分销无法绑定您的收益帐号，点击链接：&nbsp;&nbsp;
					<a style="background-color: red;padding:3px;" href="javascript:void(0);" id="put_goods_in_cart">登录</a>
				</c:if>
            </div>
            <div class="ac-content-wrap" style="margin: 0px;padding: 0px;">
            	${bean.content}
            </div>
             <div class="ac-content-wrap" style="margin: 0px;padding: 0px;">
             	<c:if test="${not empty wxsid}">
	             	<div style="padding: 10px;" class="desc">长按下方二维码成为果然逗分销成员！ ${current_wxsid}</div>
	             	<img alt="" src="/distri-image2.html?t=${random}" width="100%">
             	</c:if>
             </div>
        </div>
    </div>
    
    <jsp:include page="footer.jsp"></jsp:include>
    
</div>

<script type="text/javascript">
$(function(){
	$("#put_goods_in_cart").click(function(){
		checkLogin(function(){
		},function(){
			window.location.href = "/login.html?p="+GetUrlRelativePath();
		});
	});
});	


var title = '${bean.title}||${bean.summary}';
var imgUrl = 'http://'+window.location.host+":"+window.location.port+"${bean.img}";

<c:if test="${not empty wxsid}">

var current_wxsid = '${wxsid}';
function getMenuShareTimeline(wxsid){
	
	var shareid = current_wxsid;
	var link = addUrlPara('wxsid',shareid);
	
	return {
	    title: title, // 分享标题
	    link: link, // 分享链接
	    imgUrl: imgUrl, // 分享图标
	    success: function () { 
	    	alert('分享成功');
	    	updateShareCnt();
	    },
	    cancel: function () { 
	    	alert('分享失败');
	    }
	};
}

function getMenuShareAppMessage(wxsid){
	
	var shareid = current_wxsid;
	var link = addUrlPara('wxsid',shareid);
	
	return {
	    title: title, // 分享标题
	    desc: title, // 分享描述
	    link: link, // 分享链接
	    imgUrl: imgUrl, // 分享图标
	    type: 'link', // 分享类型,music、video或link，不填默认为link
	    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
	    success: function () { 
	    	alert('分享成功');
	    	updateShareCnt();
	    },
	    cancel: function () { 
	    	alert('分享失败');
	    }
	};
	
}

function getMenuShareQQ(wxsid){
	
	var shareid = current_wxsid;
	var link = addUrlPara('wxsid',shareid);
	
	return {
	    title: title, // 分享标题
	    desc: title, // 分享描述
	    link: link, // 分享链接
	    imgUrl: imgUrl, // 分享图标
	    success: function () { 
	    	alert('分享成功');
	    	updateShareCnt();
	    },
	    cancel: function () { 
	    	alert('分享失败');
	    }
	};
}

</c:if>

function updateShareCnt(){
	
	var id = '${bean.id}';
     $.ajax({type:"POST",
             url:"accreate.html?t="+new Date().getTime(),
             data:{"id":id},
             dataType:"json"
	 });
}

</script>

</body>
</html>