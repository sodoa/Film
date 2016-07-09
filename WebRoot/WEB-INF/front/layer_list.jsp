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
                <div class="back"><a href="/center/my_center.html"><span class="icon-back"></span></a></div> 
                <div class="title">分销下级用户</div> 
                <div class="subMark"><p></p></div> 
            </header>
  	</div>
  	  
    <div class="scroll-content">
       <div class="m-block">
         <div class="o-user-grade">
         	<span class="second-ico">二级用户</span>
         </div>
         <div class="o-user-tit">
         	<c:forEach var="item" items="${list1}">
         		<P><span>${item.displayname}</span><span>${item.account}</span></P>
			</c:forEach>	
         </div>
       </div>
       
       <div class="m-block">
         <div class="o-user-grade">
         	<span class="third-ico">三级用户</span>
         </div>
         <div class="o-user-tit">
        	<c:forEach var="item" items="${list2}">
				<P><span>${item.displayname}</span><span>${item.account}</span></P>
			</c:forEach>					
         </div>
       </div>
    </div>
    
    <jsp:include page="footer.jsp"></jsp:include>
    
</div>

</body>
</html>


