<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html class="no-js">
<head>

<jsp:include page="header.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/theme/newest/css/order.css" type="text/css" rel="stylesheet" />
</head>
<body >
<div class="g-doc">
    <div class="top-fxied">
            <header class="header"> 
                <div class="back"></div> 
                <div class="title">微信打赏</div> 
                <div class="subMark"><p></p></div> 
            </header>
  </div>
  	<div class="scroll-content"  style="background-color: white;padding: 20px;">
		<div id="tipmsg"  class="" style="border: 0px solid #eee;padding-top: 30px;padding-bottom: 30px;text-align: center;">
				正在支付当中...
		</div>
		<div style="margin-top: 20px;margin-bottom:20px;text-align: center;" id="toPlay" >
			<a href="${pageContext.request.contextPath}/movie/see.jspx?fid=${fid}" style="padding: 10px;padding-left: 30px;padding-right:30px;border: 1px solid #eee;background-color: green;color: #fff;">去看电影</a>
		</div>
		
	  <script type="text/javascript">
	  
	  $(function(){
		  
		  if (typeof WeixinJSBridge == "undefined"){
			   if( document.addEventListener ){
			       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
			   }else if (document.attachEvent){
			       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
			       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
			   }
			}else{
			   onBridgeReady();
			}
		  
	  });
	  
  	function onBridgeReady(){
		 WeixinJSBridge.invoke('getBrandWCPayRequest',{
  		 "appId" : "${appId}","timeStamp" : "${timeStamp}", "nonceStr" : "${nonceStr}", "package" : "${packageValue}","signType" : "MD5", "paySign" : "${sign}" 
   			},function(res){
				WeixinJSBridge.log(res.err_msg);
				//alert(res.err_code + res.err_desc + res.err_msg);
	            if(res.err_msg == "get_brand_wcpay_request:ok"){  
	                $("#tipmsg").html("微信支付成功!");
	                $("#toPlay").show();
	            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
	                $("#tipmsg").html("微信支付取消!");
	            }else{  
	            	$("#tipmsg").html("支付失败!" + res.err_code + "||"+res.err_desc +"||"+ res.err_msg);
	            } 
			});
	}
  	
  </script>
	</div>
</div>	
	
</body>
</html>


