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
            <div class="back"><a href="/center/my_center.html?from=${from}&opt=${opt}"><span class="icon-back"></span></a></div> 
            <div class="title">收货地址管理</div> 
            <div class="subMark"><p><a href="/center/address_add.html?from=${from}" style="padding: 1px;margin-left: 5px;font-size: 0.32rem;">新增</a></p></div> 
        </header>
    </div>
    
    <div class="scroll-content" style="">
        	<ul class="m-order-addresslist">
        		<c:forEach var="item" items="${list}" varStatus="s">
        			
		            	<li data="${item.deliveryId}" style="cursor: pointer;<c:if test='${item.isdefault==1}' > border:1px solid red; </c:if>"  onclick="go_check('/center/address_default.html?id=${item.deliveryId}&from=${from}&opt=${opt}')"   > 
		                     <div class="m-order-address">
		                     	${item.receiverName}&nbsp;&nbsp;${item.receiverPhone}
		                         <p>${item.address}</p>
		                         
		                         	<input id="chk${s.index}" type="checkbox" style="cursor: pointer;"  onclick="go_check('/center/address_default.html?id=${item.deliveryId}&from=${from}&opt=${opt}')"  <c:if test='${item.isdefault==1}'>checked='checked'</c:if> />
		                         	<label for="chk${s.index}" style="cursor: pointer;" ><c:choose> <c:when test="${opt==1}">选择收货地址</c:when> <c:otherwise> 选择默认地址 </c:otherwise> </c:choose></label>
		                     </div>
		                      
		                 	 <a href="/center/address_edit.html?id=${item.deliveryId}&from=${from}" style="margin-right: 20px;" class="m-order-address-edit"></a>
		                  	 <a href="javascript:void(0)" data="${item.deliveryId}" class="m-order-address-delete address_delete" ></a>
		         		 </li>
         		 </c:forEach>
            </ul>
    </div>
    
    <jsp:include page="footer.jsp"></jsp:include>
    
</div>
	
	<script type="text/javascript">
		
		$(function(){
			
			$(".address_delete").click(function(){
				var _this=$(this);
				dialog("提醒","您确认要删除该地址信息么？",function(){
				  	window.location.href="/center/address_delete.html?id="+_this.attr('data')+"&from=${from}";
				});
				
				return false;
			});
			
		});
		
		function go_check(url){
			window.location.href= url;
		}
	</script>
	


</body>
</html>


