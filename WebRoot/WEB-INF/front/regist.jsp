<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/theme/newest/css/order.css" />
<link type="text/css" rel="stylesheet" href="/jslib/uiadmin/lib/Validform/5.3.2/style.css" />
<script type="text/javascript" src="/jslib/uiadmin/lib/Validform/5.3.2/Validform.min.js"></script>

<style type="text/css">

.btn-register2{
	clear: both;
	margin-left: 0.42rem !important;;
    padding: 0.09rem 0.23rem !important;;
    border: 1px solid #ff4f01 !important;;
    border-radius: .107rem !important;;
    background-color: #fff !important;;
    color: #ff4f01 !important;;
    height:auto !important;;
    width: auto !important;;
    outline: black !important;;
}

</style>

</head>
<body>
	<div class="g-doc">
		<div class="top-fxied">
			<header class="header">
				<div class="back">
					<a href="/">
						<span class="icon-back"></span>
					</a>
				</div>
				<div class="title">注册</div>
				<div class="subMark">
					<p></p>
				</div>
			</header>
		</div>
		<div class="scroll-content">
			<div class="m-block-form">
				<form class="form-horizontal m-order-address-form" id="regist_form" action="regist.html" method="post">
					<input type="hidden" name="wx_id" value="${openid}"/>
					<div class="form-group">
						<label for="name" class="col-xs-3">账号</label>
						<div class="col-xs-9">
							<input type="text" id="regist_account" name="account" datatype="n10-15" errormsg="请输入手机号码" required maxlength="30" placeholder="请输入手机号码">
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-xs-3">验证码</label>
						<div class="col-xs-5">
							<input  id="verycode" name="verycode" datatype="n1-10" required  maxlength="30" type="text" id="name" placeholder="请输入验证码">
						</div>
						<div class="col-xs-4">
							<input type="button" href="javascript:void(0);"  id="get_very_code_btn"  class="btn-register2" style="padding: 0.12rem; font-size: 0.18rem" value="获取验证码" />
						</div>
					</div>
		              <div class="form-group">
		                <label for="name" class="col-xs-3">昵称</label>
		                <div class="col-xs-9">
		                  <input type="text" id="displayname" name="displayname"  datatype="*1-60" placeholder="请输入您的昵称" errormsg="长度在1到60位">
		                </div>
		              </div>					
					<div class="form-group">
						<label for="name" class="col-xs-3">密码</label>
						<div class="col-xs-9">
							<input type="password" id="login_password" name="password" datatype="*1-30" errormsg="长度在1到30位" placeholder="请输入密码" required maxlength="30">
						</div>
					</div>
					<div class="form-group">
						<div class="wenz" id="login_error_msg_group" style="display: none;">
							<p></p>
						</div>
					</div>
  					<div class="order-btn-logout"> <a href="javascript:void(0)" onclick="regist_submit()">注册</a> </div>
  					<div class="other-link" style="line-height: 0.18rem; height: 0.38rem;"><span class="ft-right">注册前请仔细阅读 <a href="/protocol.html" style="color:#666">果然逗服务协议</a></span></div>
              		<div class="other-link txt-center" style="height:0.68rem">已有账号？ <a href="/login.html" class="btn-register" >马上登录</a></div>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var p = '${p}';
	</script>
	<script type="text/javascript" src="/theme/js/regist.js"></script>
</body>
</html>
