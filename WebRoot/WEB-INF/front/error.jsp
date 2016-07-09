<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<link href="/theme/newest/css/order.css" type="text/css" rel="stylesheet" />
<style type="text/css">

.detailsBlock11{width:100%;background-color:#fff}

</style>

</head>
<body>


<div class="g-doc">
    <div class="top-fxied">
            <header class="header"> 
                <div class="back"><a href="/"><span class="icon-back"></span></a></div> 
                <div class="title">错误信息</div> 
                <div class="subMark"><p></p></div> 
            </header>
  </div>
  	<div class="scroll-content"  style="background-color: white;padding: 20px;">
		
		<div id="tipmsg" class="" style="border: 1px solid #eee;padding-top: 10px;padding-bottom: 10px;padding-left: 30px;text-align: center;">
				${msg}
		</div>
		
		<div>  		 
		</div>
	</div>
	
	<jsp:include page="footer.jsp"></jsp:include>
</div>	

</body>
</html>


