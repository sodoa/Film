<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="item" items="${list}">
	<li style="margin-bottom: 5px;">
		<c:forEach var="form" items="${item.form}">
			<div class="m-tab-list">
				<div class="m-cartlist-img">
					<img src="${form.goods.thumbnailUrl}" />
				</div>
				<div class="m-cartlist-info">
					<h3>${form.goods.goodsName}</h3>
					<h4>${form.goods.goodsLname}</h4>
					<h5>
						&yen;${form.detail.finalPrice} <span class="m-cartlist-nums">x ${form.detail.quantity}</span>
					</h5>
				</div>
			</div>
		</c:forEach>
		
		<div class="m-tab-btn">
			<div class="info">
				共<span class="txtOrange">${item.order.totalQuantity}</span>件  合计：<span class="txtOrange">&yen;${item.order.totalAmount} (含邮)</span>
			</div>
			<div class="btn">
		<!-- UNPAY("未支付", 0), PAYED("已支付", 1), SHIPPED("已出货", 2), COMMENT("已评价", 3); -->
			<c:choose>
				<c:when test="${item.order.status == 0}">
					<div class="count_order">
						<a style="cursor: pointer;" class="" onclick="toPay(${item.order.orderId})">去结算</a>&nbsp;&nbsp;&nbsp;
						<a style="cursor: pointer;" class="h" onclick="toCancel(${item.order.orderId})">取消订单</a>
					</div>
				</c:when>
				<c:when test="${item.order.status == 1}">
					
				</c:when>
				<c:when test="${item.order.status == 2}">
					<div class="count_order">
						<a style="cursor: pointer;" class="h" onclick="toComment(${item.order.orderId})">去评论</a>
					</div>				
				</c:when>
				<c:when test="${item.order.status == 3}">
					<div class="count_order">
						<a style="cursor: pointer;" class="h" onclick="seeComment(${item.order.orderId})">查看评论</a>
					</div>					
				</c:when>
			</c:choose>
			</div>
		</div>
	</li>
</c:forEach>
