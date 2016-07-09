<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/theme/newest/css/order.css" />
</head>
<body>
	<div class="g-doc">
		<div class="top-fxied">
			<header class="header">
				<div class="back">
					<a href="/center/order_list.html?li=${li}">
						<span class="icon-back"></span>
					</a>
				</div>
				<div class="title">评论商品</div>
				<div class="subMark">
					<p></p>
				</div>
			</header>
		</div>
		<div class="scroll-content" style="height: 140px;padding-left: 30px;padding-top:30px;background-color: white;">
				${bean.context}
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
	</div>

</body>
</html>


