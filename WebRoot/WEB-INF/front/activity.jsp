<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/theme/newest/css/order.css" />
</head>
<body>

<div class="g-doc">
    <div class="scroll-content" style="margin-top: 1px;">
    	<div class="banner"><img src="/theme/newest/images/banner.jpg" alt=""></div>
        <ul class="pic-art-list">
        
        	<c:forEach var="item" items="${list}">
				<li>
					<a href="/ac-${item.id}.html">
					<p><img src="${item.img}" width="200px" height="200px"></p>
                	<p class="p-tit">
                		<span class="txt">${item.title}</span>
                		<a href="/ac-${item.id}.html"><span class="btn">分享</span></a>
                	</p>
                	</a>
				</li>
			</c:forEach>
        
        </ul>
    </div>
    
    <jsp:include page="footer.jsp"></jsp:include>
    
</div>



</body>
</html>