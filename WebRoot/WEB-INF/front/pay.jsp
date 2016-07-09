<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>

<%

	String path = request.getContextPath();
	
	String appId = request.getParameter("appid");
	String timeStamp = request.getParameter("timeStamp");
	String nonceStr = request.getParameter("nonceStr");
	String packageValue = request.getParameter("package");
	String paySign = request.getParameter("sign");

%>


	<jsp:include page="header_nav.jsp"></jsp:include>
	
	<section data-am-widget="accordion"
		class="am-accordion am-accordion-gapped"
		data-am-accordion='{ "multiple": true }'>
		<dl class="am-accordion-item am-active">
			<dt class="am-accordion-title">支付页面
			</dt>
			<dd class="am-accordion-content">
				<div>
				  <div class="am-form-group am-form-icon">
				    <i class="am-icon-calendar"></i>
				    <div  class="am-form-field" >您共计需要支付<b style="color: red;">${order.totalAmount}元</b></div>
				  </div>
				  
				  <div class="am-form-group am-form-icon">
				    <div class="am-form-field " >	
							<ul class="am-list am-list-static">
								<li>收件地址：${order.receiverAddress}</li>
								<li>收件人：${order.receiverName}</li>
								<li>联系电话：${order.receiverPhone}</li>
							</ul>
					</div>
				  </div>
				</div>
			</dd>
		</dl>
	</section>
	
	<div style="text-align:center;size:30px;"><input type="button" style="width:200px;height:80px;" value="微信支付" onclick="callpay()"></div>
	
	
	<jsp:include page="footer.jsp"></jsp:include>
	
	  <script type="text/javascript">
  	function callpay(){
		 WeixinJSBridge.invoke('getBrandWCPayRequest',{
  		 "appId" : "<%=appId%>","timeStamp" : "<%=timeStamp%>", "nonceStr" : "<%=nonceStr%>", "package" : "<%=packageValue%>","signType" : "MD5", "paySign" : "<%=paySign%>" 
   			},function(res){
				WeixinJSBridge.log(res.err_msg);
// 				alert(res.err_code + res.err_desc + res.err_msg);
	            if(res.err_msg == "get_brand_wcpay_request:ok"){  
	                alert("微信支付成功!");  
	            }else if(res.err_msg == "get_brand_wcpay_request:cancel"){  
	                alert("用户取消支付!");  
	            }else{  
	               alert("支付失败!");  
	            }  
			})
		}
  </script>

</body>
</html>


