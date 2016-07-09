<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="item" items="${list}">
	<li>
		<div class="o-order-tit">
			<div class="NO">${item.chargeName}</div>
			<div class="num">分红金额：${item.income}&yen; &nbsp;</div>
		</div>
		<div class="o-order-con">
			<p class="cont">
				<span>消费金额：${item.charge}&yen;</span><span>分红比例：${item.rate}</span><span>分级：${item.level}级</span>
			</p>
			<p>下线用户：${item.downlineName}</p>
			<p>消费时间：<sp:dateformat time="${item.consumeDate}" /></p>
		</div>
	</li>
</c:forEach>
