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
<body>
	<div class="g-doc">
		<div class="top-fxied">
			<header class="header">
				<div class="back">
					<a href="/center/order_list.html?li=${li}">
						<span class="icon-back"></span>
					</a>
				</div>
				<div class="title">评论商品</div>
				<div class="subMark">
					<p></p>
				</div>
			</header>
		</div>
	
		<form id="regist_form"  method="post" action="/center/save-order-comment.html">
		<input type="hidden" name="li" value="${li}"/>
		<input type="hidden" name="id" value="${id}"/>
		
		<div class="scroll-content" style="height: 140px;padding: 10px;padding-top:20px;background-color: white;">
			<textarea rows="6"  cols="" style="width: 100%;border: 1px solid #eee;" name="comment" placeholder="请输入评论，不超过200个字" required  maxlength="300"></textarea>
			<div class="order-btn-logout"> <a href="javascript:void(0)" onclick="regist_submit()">提交评论</a> </div>	
		</div>
			
		</form>
		
		<jsp:include page="footer.jsp"></jsp:include>
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
			tiptype : 2,
			callback : function(data) {
				if (data.result == 0) {
					layer.msg('评论成功');
					window.setTimeout(function(){
						window.location.href = "/center/order_list.html?li=${li}";
					},1000);
					
					return true;
				} else {
					layer.msg(data.message);
					return false;
				}
			},
			tiptype : function(msg, o, cssctl) {
				if(o.type==3){
					layer.msg(msg);
				}
			}
		});
		

	}
</script>

</body>
</html>


