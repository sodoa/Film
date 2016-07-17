<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html class="no-js">
<head>
<link href="${pageContext.request.contextPath}/theme/newest/css/order.css" type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/jslib/loading/css/msgbox.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/loading/js/msgbox.js"></script>

</head>
<body >
<div class="g-doc">
    <div class="top-fxied">
            <header class="header"> 
                <div class="back"><a href="/"><span class="icon-back"></span></a></div> 
                <div class="title">微信支付</div> 
                <div class="subMark"><p></p></div> 
            </header>
  </div>
  	<div class="scroll-content"  style="background-color: white;padding: 20px;">
		
		<div id="tipmsg" class="" style="border: 1px solid #eee;padding-top: 10px;padding-bottom: 10px;padding-left: 30px;text-align: center;">
				正在支付当中...
		</div>
		
		<div>  		 
		</div>
		<div id="fakeloader"></div>
		<div style="margin-top: 20px;margin-bottom:20px;text-align: center;">
			<a href="./see.html?fid=${fid}" style="padding: 5px;border: 1px solid #eee;">去看电影</a>
		</div>
		
	  <script type="text/javascript">
	  
	  $(function(){
		  
		  ZENG.msgbox.show("正在初始安全支付...", 6);
		  
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
// 				alert(res.err_code + res.err_desc + res.err_msg);
	            if(res.err_msg == "get_brand_wcpay_request:ok"){  
	                $("#tipmsg").html("微信支付成功!");
	            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
	                $("#tipmsg").html("微信支付取消，请在订单中心继续支付!");
	            }else{  
	            	$("#tipmsg").html("支付失败!" + res.err_msg);
	            } 
	            
	            ZENG.msgbox._hide();
			});
	}
  	
  	// "appId" : "${appId}","timeStamp" : "${timeStamp}", "nonceStr" : "${nonceStr}", "package" : "${packageValue}","signType" : "MD5", "paySign" : "${sign}"
  	
  </script>
	</div>
</div>	
	
</body>
</html>


