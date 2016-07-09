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
		<div class="scroll-content" style="margin-top: 1px;">
			<!-- 个人信息-->
			<div class="m-user">
				<div class="userInfo" style="padding: 0.1rem">
					<p class="name">${customer.displayname}</p>
					<p class="num" style="padding: 0.1rem 0.1rem;">
						帐号金额：${wallet.balance}&yen;&nbsp;&nbsp;&nbsp;
						<a href="./tranfer.html" style="font-size: 14px; color: orange;">领取红包</a>
					</p>
					<p class="num" style="padding: 0.1rem 0.1rem;">
						累计收益：${wallet.distrBalance}&yen;&nbsp;&nbsp;&nbsp;
						<a href="/distri-tip.html?id=${customerId}" style="font-size: 14px; color: orange;">分销说明</a>
					</p>
				</div>
				<div class="userPic" style="margin-right: 0px; padding: 0.05rem">
					<p class="img">
						<a class="example-image-link" href="/center/distri-image.html" data-lightbox="example-1">
							我的二维码 <img id="example2" class="example-image" width="90%" height="90%" src="/center/distri-image.html?t=${random}">
						</a>
					</p>
				</div>
			</div>
			<!-- 导航栏-->
			<div class="m-tabs-nav">
				<a href="order_list.html?li=2" <c:if test="${li==2}">class="current"</c:if>>
					<span class="ico-bank"><i class="cart-sales-nums">${unPayCount}</i></span>待付款
				</a>
				<a href="order_list.html?li=3" <c:if test="${li==3}">class="current"</c:if>>
					<span class="ico-bag"></span>待发货
				</a>
				<a href="order_list.html?li=4" <c:if test="${li==4}">class="current"</c:if>>
					<span class="ico-bus"></span>待收货
				</a>
				<a href="order_list.html?li=5" <c:if test="${li==5}">class="current"</c:if>>
					<span class="ico-comment"></span>已评论
				</a>
			</div>
			<!-- TAB -->
			<div class="m-cells">
				<div class="m-cell-item">
					<a href="/center/order_list.html" style="">订单列表 </a>
				</div>
				<div class="m-cell-item">
					<a href="/center/distri.html">我的收益</a>
				</div>
				<div class="m-cell-item">
					<a href="/center/layer_list.html">下级分销</a>
				</div>
				<div class="m-cell-item">
					<a href="/center/love.html">我的收藏</a>
				</div>
				<div class="m-cell-item">
					<a href="/center/address_list.html">地址管理</a>
				</div>
				<div class="m-cell-item">
					<a href="tel:<sp:config id="service.telphone"></sp:config>">
						客服电话(
						<sp:config id="service.telphone"></sp:config>
						)
					</a>
				</div>
				<div class="m-cell-item">
					<a href="/center/setting.html">设置</a>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
