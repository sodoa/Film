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
				<div class="title">用户登录</div>
				<div class="subMark">
					<p></p>
				</div>
			</header>
		</div>
		<div class="scroll-content">
			<div class="m-block-form">
				<form class="form-horizontal m-order-address-form" id="login_form" action="login.html" method="post">
					<div class="form-group">
						<label for="name" class="col-xs-3">账号</label>
						<div class="col-xs-9">
							<input type="text" id="login_account" name="account" datatype="n10-15" errormsg="请输入手机号码" required maxlength="30" placeholder="请输入手机号码">
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-xs-3">密码</label>
						<div class="col-xs-9">
							<input type="password" id="login_password" name="password" datatype="*1-30" errormsg="请输入密码" placeholder="请输入密码" required maxlength="30">
						</div>
					</div>
					<div class="form-group">
						<div class="wenz" id="login_error_msg_group" style="display: none;" >
							<p></p>
						</div>
					</div>
					<div class="order-btn-logout">
						<a id="login_btn" href="javascript:void(0)" onclick="validate_submit()">登 录</a>
					</div>
					<div class="other-link"  style="line-height: 0.18rem; height: 0.38rem;">
						<a href="/forget.html" class="ft-right">忘记密码？</a>
					</div>
					<div class="other-link txt-center" style="height: 0.68rem">
						还没帐号?
						<a href="/regist.html?p=${p}" class="btn-register">注册</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var p = '${p}';
	</script>
	<script type="text/javascript" src="/theme/js/login.js"></script>
</body>
</html>
