
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/theme/newest/css/order.css" />
<link type="text/css" rel="stylesheet" href="/jslib/uiadmin/lib/Validform/5.3.2/style.css" />
<script type="text/javascript" src="/jslib/uiadmin/lib/Validform/5.3.2/Validform.min.js"></script>

<style type="text/css">
.order-btn-logout button {
    display: block;
    height: 0.8rem;
    line-height: 0.8rem;
    background: #ff4f01;
    text-align: center;
    color: #fff;
    border-radius: .107rem;
    font-size: 0.32rem;
    width: 100%;
}
</style>

</head>
<body>
	<div class="g-doc">
		<div class="top-fxied">
			<header class="header">
				<div class="back">
					<a href="/center.html">
						<span class="icon-back"></span>
					</a>
				</div>
				<div class="title">领取红包</div>
				<div class="subMark">
					<p></p>
				</div>
			</header>
		</div>
		<div class="scroll-content">
			<div class="m-block-form" style="border: 0px;">
				<form class="form-horizontal m-order-address-form" id="regist_form" action="/center/transfer.html" method="post">
					<div class="scroll-content" style="padding: 10px; background-color: white;">
						<div class="form-group">
							<div class="col-xs-12">
								您账号有 &nbsp;<span style="color: red; font-size: 16px;">${wallet.balance}&yen;</span>&nbsp;可以领取（只能在微信当中领取）
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12">
								<div>请在下方输入您的提现金额，系统将以微信红包方式发送给您。</div>
								<div><input type="text" id="money" name="money" style="width: 60%; height: 38px; border: 1px solid red;" placeholder="红包金额" required maxlength="30">
								（元）</div>
							</div>
						</div>
						<c:if test="${fn:length(msg)>0}">
							<div id="login_error_msg_group" class="am-alert am-alert-secondary" data-am-alert>
								<p>${msg}</p>
							</div>
						</c:if>
						<div class="order-btn-logout"> 	<button class="am-btn am-btn-default am-radius" type="submit" id="submit_form">发送红包</button></div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			  validate();
			  
			  if(!isWeiXinBrowser()){
				  $("#submit_form").attr("disabled","disabled");
				 // $("#money").attr("disabled","disabled");
				  $("#money").val("红包只能在微信当中使用");
			  }
		});

		function validate() {
			
			$("#regist_form").Validform({
				ajaxPost : false,
				tiptype : 2,
				callback : function(data) {
				
				},
				beforeSubmit:function(curform){

					var balance = '${wallet.balance}';
					var inputBalance = $("#money").val();
					
					if(balance<inputBalance){
						alert("余额不足");
						return false;
					}
					
					if(inputBalance>200){
						alert("一次性最多200元");
						return false;
					}
				},
			});
			
		}
	</script>
</body>
</html>
