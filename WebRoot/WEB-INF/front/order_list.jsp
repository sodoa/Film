<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/theme/newest/css/order.css" />
<link href="/jslib/uiadmin/lib/laypage/1.2/skin/laypage.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/jslib/uiadmin/lib/laypage/1.2/laypage.js"></script>
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
				<div class="title">订单管理</div>
				<div class="subMark">
					<p></p>
				</div>
			</header>
			<div class="m-tabs-nav">
				<a  <c:if test="${li=='1'}">class="current"</c:if> href="order_list.html?li=1"> 
					<span class="ico-all"></span>全部
				</a>
				<a <c:if test="${li=='2'}">class="current"</c:if> href="order_list.html?li=2"> 
					<span class="ico-bank"></span>待付款
				</a>
				<a <c:if test="${li=='3'}">class="current"</c:if> href="order_list.html?li=3"> 
					<span class="ico-bag"></span>待发货
				</a>
				<a <c:if test="${li=='4'}">class="current"</c:if> href="order_list.html?li=4">
					<span class="ico-bus"></span>待收货
				</a>
				<a <c:if test="${li=='5'}">class="current"</c:if> href="order_list.html?li=5"> 
					<span class="ico-comment"></span>已评论
				</a>
			</div>
		</div>
		<div class="order-scroll-content">
			<div class="order-manage">
				<jsp:include page="order_list_content.jsp"></jsp:include>
			</div>
		</div>
		<div style="height: 80px;"></div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
</body>
</html>
