<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>爆品电影</title>
</head>

<body style="font-family: 微软雅黑;background-image:url('${pageContext.request.contextPath}/resource/img/back.png'); ">
<div style="min-height:350px;max-width:650px;float:0 auto; MARGIN-RIGHT: auto; MARGIN-LEFT: auto;text-align: center; ">
  <div style="padding-top:100px;padding-bottom:30px; font-size: 24px">
  <p>爆品小业荷包小</p>
  <p>希望亲们赏包烟</p>
  <p>好来我为找影片</p>
  </div>
  
  <a href="${pageContext.request.contextPath}/movie/pay.jspx?fid=${fid}">
  	<img src="${pageContext.request.contextPath}/resource/img/shang.png">
  </a>
  
</div>
</body>
</html>