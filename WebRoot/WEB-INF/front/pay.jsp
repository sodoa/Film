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
<jsp:include page="header.jsp"></jsp:include>
</head>

<body >

<div class="g-doc">
    <div class="top-fxied">
            <header class="header"> 
                <div class="back"></div> 
                <div class="title">微信打赏</div> 
                <div class="subMark"><p></p></div> 
            </header>
  </div>
  	<div class="scroll-content"  style="background-color: white;padding: 20px;text-align: center;">
		
		  <p style="padding: 4px;">爆品小业荷包小</p>
		  <p style="padding: 4px;">希望亲们赏包烟</p>
		  <p style="padding: 4px;">好来我为找影片</p>
		  <p style="padding: 4px;">一包烟可抽个月</p>
		  
		  <br/>
		  <a style="border:1px solid #eee; padding: 10px;background-color: red;" href="${pageContext.request.contextPath}/movie/pay.jspx?fid=${fid}"><b>打赏一月</b></a>
	</div>
</div>

</body>
</html>