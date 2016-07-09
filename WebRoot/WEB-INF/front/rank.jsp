<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
	
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
                <div class="back"><a href="/"><span class="icon-back"></span></a></div> 
                <div class="title">分销榜</div> 
                <div class="subMark"><p></p></div> 
            </header>
            <div class="m-tabs-nav m-tabs-charts">
                <a href="rk.html?l=1" <c:if test="${l=='1'}">class="current"</c:if> >按周计算</a>
                <a href="rk.html?l=2" <c:if test="${l=='2'}">class="current"</c:if> >按月份排名</a>
                <a href="rk.html?l=3" <c:if test="${l=='3'}">class="current"</c:if> >按年排名</a>
            </div>
  	</div>
    
    <div class="order-scroll-content">
       <div class="m-block">
      	 <ul class="m-chartsList">
	     	<c:forEach items="${list}" var="item" varStatus="s" >
				<li>
				  	<div class="o-chart-tit">
					  	<div class="NO">&nbsp;&nbsp;NO.${s.index+1 }&nbsp;【${item.displayname}/<sp:phone_hidden_part_tag value="${item.account}"></sp:phone_hidden_part_tag>】</div>
					  	<div class="num">收入${item.totalIncome} 元</div>
				  	</div>
	                <div class="o-progress">
	                	<div class="progress-bar">
	                        <div class="progress-inner-bar orange-progress" style="width:<fmt:formatNumber type='number' value='${(item.totalIncome/list[0].totalIncome)*100}' maxFractionDigits="2"></fmt:formatNumber>%"></div>
	                    </div>
	                </div>
				 </li>
			</c:forEach>
         </ul>
       </div>
    </div>
    
    <jsp:include page="footer.jsp"></jsp:include>
    <jsp:include page="scrollup.jsp"></jsp:include>
</div>

</body>
</html>