<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>${film.name}</title>
<script src="${pageContext.request.contextPath}/jslib/html5media.min.js"></script> 
</head>

<body style="font-family: 微软雅黑;">
<div style="min-height:350px;max-width:650px;float:0 auto; MARGIN-RIGHT: auto; MARGIN-LEFT: auto; ">
<h3 style="text-align: center;" >爆品电影，精挑细选 ，周周更新</h3>

  <video src="${film.url}" width="100%" height="256" controls preload></video> 

<p style="text-align: center;">${film.name}</p>
<section style="text-align: center;">
<section data-width="40%" style="padding-top: 2px; padding-right: 2px; padding-left: 2px; width: 192.79px; font-size: 14px; border-top-color: rgb(241, 210, 103); border-bottom-color: rgb(241, 210, 103); border-top-width: 3px; border-bottom-width: 1px; border-top-style: solid; border-bottom-style: solid; display: inline-block; max-width: 100%; box-sizing: border-box;"></section>
</section>
<p style="text-align: center;font-size: 12px;"><sp:dict_label_tag type="type" value="${film.type}"/></p>


<section class="" style="line-height: 25.6px; white-space: normal;text-align: center;max-width">
<section class="" style="padding: 3px; border: 3px solid rgb(255, 202, 0); max-width: 100%; box-sizing: border-box;">
<section class="" style="padding: 10px; border: 1px solid rgb(255, 202, 0); text-align: center; line-height: 1.4; font-family: inherit; font-size: 1.2em; text-decoration: inherit; max-width: 100%; box-sizing: border-box;font-size: 14px;font-weight:bold;">

<strong style="max-width: 100%; box-sizing: border-box !important;">导演：${film.director}</strong>

<p style="color: rgb(65, 61, 61); font-weight: inherit;  min-height: 1em; max-width: 100%; box-sizing: border-box !important;">
<span style="color: rgb(0, 0, 0); max-width: 100%; box-sizing: border-box !important;">
<strong style="max-width: 100%; box-sizing: border-box !important;">主<span style="max-width: 100%; box-sizing: border-box !important;">演：
	<span style="color: rgb(17, 17, 17); font-family: Helvetica, Arial, sans-serif; max-width: 100%; background-color: rgb(255, 255, 255);">
	<span style="line-height: 25.6px; font-family: 微软雅黑, sans-serif; max-width: 100%; box-sizing: border-box !important;">
	${film.actor}
	</span>
	</span>
	</span>
</strong>
</span></p>

<p style=" min-height: 1em; max-width: 100%; box-sizing: border-box !important;"><span style="color: rgb(255, 0, 0);">
<strong> <sp:dict_label_tag type="country" value="${film.country}"/> </strong>
</span></p>

<p style="color: rgb(65, 61, 61); font-weight: inherit;  min-height: 1em; max-width: 100%; box-sizing: border-box !important;"><span style="color: rgb(0, 0, 0); max-width: 100%; box-sizing: border-box !important;">
<strong style="max-width: 100%; box-sizing: border-box !important;"><sp:dateformat time="${film.publish}" format="yyyy-MM-dd"/> </strong>
</span></p>

</section>
</section>
</section>
<p style="line-height: 25.6px; white-space: normal; text-align: center;"><span style="color: rgb(255, 0, 0); max-width: 100%; box-sizing: border-box !important;">
<img src="${pageContext.request.contextPath}/image.jspx?i=${film.picture}" style="width: auto\9; width:100%;visibility: visible !important; height: auto !important;">
<br>
</span></p>



<section class="" data-id="23" style="white-space: normal; border: 0px currentcolor; color: rgb(62, 62, 62); line-height: 28.79px; font-family: 微软雅黑; font-size: 18px; max-width: 100%; box-sizing: border-box; background-color: rgb(255, 255, 255);"><section class="" style="margin-top: 10px; margin-bottom: 10px; padding: 15px 20px 15px 45px; outline: 0px; border: 0px currentcolor; line-height: 22.39px; font-size: 14px; vertical-align: baseline; max-width: 100%; box-sizing: border-box; background-image: none; background-color: rgb(241, 241, 241); background-position: 1% 5px; background-repeat: no-repeat;"><p><span style="color: rgb(17, 17, 17); line-height: 19.44px; font-family: Helvetica, Arial, sans-serif; font-size: 12px;"></span><span style="font-size: 12px;">&nbsp; &nbsp; </span>
<span style="color: rgb(17, 17, 17); font-family: Helvetica, Arial, sans-serif; font-size: 12px; line-height: 19.44px;">
${film.resume}
</span>
<span style="font-size: 12px;"></span><span style="color: rgb(17, 17, 17); line-height: 22.39px; letter-spacing: 0px;"></span></p></section></section>

<div style="font-size: 6px;text-align: center;">资源来源网络，如侵害您的权益，请联系微信号bpdy123456进行删除</div>
</div>
</body>
</html>