<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<link href="/jslib/uiadmin/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />

<link href="/theme/css/myCenter.css" type="text/css" rel="stylesheet" />
<link href="/theme/css/order.css" type="text/css" rel="stylesheet" />

</head>
<body >

<div class="g-doc">
    <div class="top-fxied">
            <header class="header"> 
                <div class="back"><a href="/"><span class="icon-back"></span></a></div> 
                <div class="title">订单异常</div> 
                <div class="subMark"><p></p></div> 
            </header>
  </div>
    <div class="scroll-content">
		<div id="tipmsg" class="" style="border: 1px solid #eee;padding-top: 10px;padding-bottom: 10px;padding-left: 30px;text-align: center;">
				${html}		
		</div>
   </div>
    
</div>
	
</body>
</html>


