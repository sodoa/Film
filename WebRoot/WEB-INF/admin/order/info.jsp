<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../head.jsp"></jsp:include>
</head>

<body>
		<div class="responsive">
			<div class="row cl">
				<div class="col-lg-12" style="padding: 5px;margin: 5px;">
					<c:if test="${order.status ==1}">
						<button id="process_order_btn" type="button" class="btn btn-outline btn-primary">处理定单</button>
					</c:if>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>订单状态：
					<b style="color:red;font-size: 14px;">
					<c:choose>
						<c:when test="${order.status == 0}">
							未支付
						</c:when>
						<c:when test="${order.status == 1}">
							已支付
						</c:when>
						<c:when test="${order.status == 2}">
							已出货
						</c:when>
						<c:when test="${order.status == 3}">
							已评论
						</c:when>																		
					</c:choose>
					</b>
					
					</span>
					<input type="hidden" id="process_order_id" value="${order.orderId}" />
				</div>
			</div>
			<div class="row cl">
				<div class="col-lg-6">
					<div class="panel panel-default" style="margin: 5px;">
						<div class="panel-header">定单信息</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive" >
								<table class="table table-striped table-bordered table-hover " >
									<tbody>
										<tr>
											<td>定单号：</td>
											<td>${order.orderId}</td>
											<td>共计金额（元）：</td>
											<td><b style="font-size: 20px; color: red;">${order.totalAmount}</b></td>
										</tr>
										<tr>
											<td>定单日期：</td>
											<td><sp:dateformat time="${order.orderDate}" /></td>
											<td>定单状态：</td>
											<td>
												<sp:dict_label_tag type="order_state" value="${order.status}"></sp:dict_label_tag>
											</td>
										</tr>
										<tr>
											<td>支付方式：</td>
											<td>
												<sp:dict_label_tag type="payment_mode" value="${order.paymentMode}"></sp:dict_label_tag>
											</td>
											<td>是否短信确认</td>
											<td>
												<sp:dict_label_tag type="shared_state" value="${order.shared}"></sp:dict_label_tag>
											</td>
										</tr>
										<tr>
											<td>邮费：</td>
											<td>
												${order.postage}
											</td>
											<td></td>
											<td>
												
											</td>
										</tr>										
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /.panel-body -->
					</div>

					<div class="panel panel-default" style="margin: 5px;">
						<div class="panel-header">送货信息</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover">
									<tbody>
										<tr>
											<td>送货地址：</td>
											<td>${order.receiverAddress}</td>
										</tr>
										<tr>
											<td>收货人：</td>
											<td>${order.receiverName}</td>
										</tr>
										<tr>
											<td>联系电话</td>
											<td>${order.receiverPhone}</td>
										</tr>
										<tr>
											<td>送货说明</td>
											<td><b style="font-size: 18px; color: green;">${order.mark}</b></td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-6 -->
				<div class="col-lg-6">
					<div class="panel panel-default" style="margin: 5px;">
						<div class="panel-header">客户信息</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table">
									<tbody>
										<tr>
											<td>显示名称：</td>
											<td>${customer.displayname}</td>
										</tr>
										<tr>
											<td>帐号：</td>
											<td>${customer.account}</td>
										</tr>
										<tr>
											<td>系统ID：</td>
											<td>${customer.customerId}</td>
										</tr>
										<tr>
											<td>注册时间：</td>
											<td><sp:dateformat time="${customer.regdate}" /></td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-6 -->
			</div>

			<div class="row cl">
				<div class="col-lg-12">
					<div class="panel panel-default" style="margin: 5px;">
						<div class="panel-header">定单详单</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>#</th>
											<th>商品名称</th>
											<th>数量（份）</th>
											<th>单价（元）</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${orderDetailList}" varStatus="s">
											<tr>
												<td>${s.index+1}</td>
												<td>${item.goodsName}</td>
												<td>${item.quantity}</td>
												<td>${item.finalPrice}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-6 -->
			</div>
		</div>	
		

<script type="text/javascript">

$(function(){
	
	init_process_order_btn();
});


function init_process_order_btn(){
	
	$("#process_order_btn").click(function(){
		
		layer.confirm('确认进行出货处理嘛？',function(index){
			
			var id = $("#process_order_id").val();
			
			$.ajax({type:"GET",
	            	 url:"/admin/process-order-"+id+".jspx",
	            	 dataType:"json",
	             	 success:function(data){
	            		if(data.result ==0){
	            			layer.msg(data.message,{icon:1,time:1000});
	            			window.location.reload();
	            		}
	            		else{
	            			layer.msg(data.message,{icon:1,time:1000});
	            		}
	             }
			});
		});

	});
}

</script>		
			
</body>
</html>