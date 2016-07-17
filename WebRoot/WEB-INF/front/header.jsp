<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title><sp:config id="goal.title"></sp:config>|<sp:config id="goal.description"></sp:config></title>
<meta name="keywords" content="<sp:config id="goal.keywords"></sp:config>" />
<meta name="description" content="<sp:config id="goal.description"></sp:config>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=yes" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/theme/newest/logo/128x128.png" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/theme/newest/css/reset.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/theme/newest/css/index.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/flexible.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/uiadmin/lib/lazyload/lazyload.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/wx.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/jweixin-1.0.0.js"></script>
<script src="${pageContext.request.contextPath}/jslib/uiadmin/lib/layer/1.9.3/layer.js"></script>
<link href="${pageContext.request.contextPath}/jslib/uiadmin/lib/layer/1.9.3/skin/layer.css" type="text/css" rel="stylesheet" />

<script type="text/javascript">
	$(document).ajaxStart(function() {
		layer.load();
	}).ajaxComplete(function() {
		 layer.closeAll('loading');
	}).ajaxError(function(){
		 layer.closeAll('loading');
	}); 
	
	$(function() {
	    $("img").lazyload({ 
	   		   placeholder : "images/loading.gif",
	           effect: "fadeIn"
	     });  
	});
</script>

