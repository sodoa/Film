<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/theme/newest/css/order.css" />
<link rel="stylesheet" href="/jslib/lightbox/css/lightbox.css" media="screen" />
<script src="/jslib/lightbox/js/lightbox-2.6.min.js"></script>
</head>
<body>
<div class="g-doc">
	<div class="top-fxied">
		<header class="header">
			<div class="back">
				<a href="/center/my_center.html">
					<span class="icon-back"></span>
				</a>
			</div>
			<div class="title">设置</div>
			<div class="subMark">
				<p></p>
			</div>
		</header>
	</div>
	<div class="scroll-content">
		<!-- TAB -->
		<div class="m-cells">
			<div class="m-cell-item">
				<a href="/center/setpassword.html">修改密码</a>
			</div>
			<div class="m-cell-item">
				<a href="/center/about.html">关于我们</a>
			</div>
		</div>
		<div class="order-btn-logout"> <a href="javascript:void(0)" onclick="javascript:window.location.href='/logout.html'" >退出登录</a> </div>
	</div>
	
	

	<jsp:include page="footer.jsp"></jsp:include>
</div>
	
</body>
</html>


