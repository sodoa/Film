<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<body > 
	<div class="g-doc">
		<div class="top-fxied">
			<header class="header">
				<div class="back">
					<a href="/center/setting.html">
						<span class="icon-back"></span>
					</a>
				</div>
				<div class="title">修改密码</div>
				<div class="subMark">
					<p></p>
				</div>
			</header>
		</div>
		<div class="scroll-content">
			<div class="m-block-form">
				<form class="form-horizontal m-order-address-form" id="regist_form" action="/center/setpassword.html" method="post">
					<div class="form-group">
						<label for="name" class="col-xs-3">旧密码</label>
						<div class="col-xs-9">
							<input type="password" id="orgi_password" name="orgi_password" datatype="*1-30" errormsg="请输入旧密码，1到30位之间" placeholder="请输入旧密码" required maxlength="30">
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-xs-3">新密码</label>
						<div class="col-xs-9">
							<input type="password" id="password" name="password" datatype="*1-30" errormsg="请输入新密码，1到30位之间" placeholder="请输入新密码" required maxlength="30">
						</div>
					</div>
					<div class="form-group">
						<div class="wenz" id="login_error_msg_group" style="display: none;">
							<p></p>
						</div>
					</div>
  					<div class="order-btn-logout"> <a href="javascript:void(0)" onclick="regist_submit()">重置密码</a> </div>
				</form>
			</div>
		</div>
	</div>

	
<script type="text/javascript">
	$(function(){
		  validate();
	});
	
	function regist_submit() {
		$("#regist_form").submit();
	}

	function validate() {
		
		$("#regist_form").Validform({
			ajaxPost : true,
			tiptype : 4,
			callback : function(data) {
				if (data.result == 0) {
					layer.msg('修改成功');
					window.location.href = "/login.html";
					return true;
				} else {
					layer.msg(data.message);
					return false;
				}
			},
			tiptype : function(msg, o, cssctl) {
				if(o.type == 3){
					layer.msg(msg);
				}
			}
		});
	}
</script>

</body>
</html>


