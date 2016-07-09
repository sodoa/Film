<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<link href="/jslib/uiadmin/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="/theme/newest/css/order.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/jslib/uiadmin/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="/jslib/uiadmin/lib/Validform/5.3.2/Validform.min.js"></script> 

</head>
<body >
	
	<form action="/center/take-order-update.html" id="order_form" method="post">
	
<div class="g-doc">
    <div class="top-fxied">
            <header class="header"> 
                <div class="back"><a href="/"><span class="icon-back"></span></a></div> 
                <div class="title">下订单</div> 
                <div class="subMark"><p></p></div> 
            </header>
  </div>
    <form action="/center/take-order.html" id="order_form" method="post">
    <div class="scroll-content">
    	<ul class="m-order-addresslist">       
                 <li>
					<input type="hidden" name="deliveryId" datatype="*" nullmsg="请编辑收货地址"  value="${address.deliveryId}" />
					<input type="hidden" name="orderId" value="${bean.order.orderId}"/>
					<c:if test="${empty address.deliveryId}">
						<div class="m-order-address">
						    <p style="cursor: pointer;" onclick="selectAddress()" >请编辑收货地址</span></p>
						</div>            
					</c:if>
					<c:if test="${not empty address.deliveryId}">
						<div class="m-order-address">
						    <p>${address.receiverName}<span>&nbsp;&nbsp;${address.receiverPhone}</span></p>
						    <p>${address.address}</p>
						</div>            
					</c:if>		         
					 <a href="javascript:void();"  onclick="selectAddress()" class="m-order-address-edit"></a>     
         		 </li>
            </ul>
            
        <div class="order-manage"> 
               <ul class="m-cartlist bgWhite">
               		<c:forEach items="${bean.form}" var="cartitem" varStatus="st">
		               		<li>
		                    	<div class="m-tab-list">
	                                   <div class="m-cartlist-img">
	                                   		<a href="/goods-${cartitem.goods.goodsId}.html"><img src="${cartitem.goods.thumbnailUrl}" style="width: 80px; height: 80px"></a>
										</div>
	                                   <div class="m-cartlist-info">
	                                       <h3>${cartitem.goods.goodsName}</h3>
	                                       <h4>${cartitem.goods.goodsLname}</h4>
	                                       <h5>&yen;${cartitem.detail.finalPrice} <span class="m-cartlist-nums">x ${cartitem.detail.quantity}</span></h5>
	                                   </div>
		                      	</div> 
		                    </li>
					</c:forEach>
               </ul>	 
         </div>
         
         <div class="order-manage"> 
         	<div style=" border: 0px solid #dedddd;background: #fff; padding: 0.04rem;font-size: 0.16rem;vertical-align: middle;margin-top: 0.1rem;padding-left:0.1rem;">
         		<span>邮费&nbsp;(满<sp:config id="goods.postage.money"></sp:config>元包邮)</span>
         		<span class="pay-bd">&nbsp;</span>
         		<span style="margin-right:10px;float:right;color: #ff4f01;    font-size: 0.2rem;">
	         		<h5>
		         			+&nbsp;${bean.order.postage}&yen;
	         		</h5>
         		</span>
         	</div>
         </div>
        
        <div class="m-trade-pay">
        
	        <div class="m-trade-pay" id="paytype_div">
	        	<c:if test="${wecat}">
		        	<div class="pay-cell">
		                <div class="pay-hd"><input type="radio" class="cartList-check" name="paytype"  <c:if test="${wecat}"> checked="checked" </c:if> value="1"></div>
		                <div class="pay-bd"><span class="wIcon"></span></div>
		                <div class="pay-ft">微信支付</div>
		            </div>
	            </c:if>
	            <div class="pay-cell">
	                <div class="pay-hd"><input type="radio" class="cartList-check" name="paytype" <c:if test="${!wecat}"> checked="checked" </c:if> value="2"></div>
	                <div class="pay-bd"><span class="zIcon"></span></div>
	                <div class="pay-ft">支付宝支付</div>
	            </div>
	        </div>
        </div>
 
       <div class="m-block mt20">
           <div class="m-cell-title">给卖家留言</div>
           <div class="m-cell-primary">
           	<div style="padding:0.1rem">
           		<textarea class="m-textarea"  datatype="*0-180"  errormsg="留言字数需要在100个以下" name="order_remark" rows="3" cols="30" style="width:99%;border: 1px solid #eee;border-radius: 0rem;"></textarea>
           </div>
           </div>
       </div> 
       
       	<c:if test="${CartInfoVo.hasGoods}">
		     <div class="bottom-fxied">
					<div class="m-btn"  onclick="sumibtForm()"> <a href="javascript:void(0)" class="orange-btn">确认支付（${CartInfoVo.totalAmount}元）</a> </div>
		 	</div>
	 	</c:if>
    </div>
    
    <div class="bottom-fxied">
		<c:if test="${bean.order.status==0}"><div style="cursor: pointer;" class="orange-btn"><p onclick="sumibtForm()">去结算(&yen;${bean.order.totalAmount})</p></div></c:if>
		<c:if test="${bean.order.status==3}"><div style="cursor: pointer;" class="orange-btn"><p onclick="toComment('${bean.order.orderId}')">去评论</p></div></c:if>
 	</div>
 	<div style="height: 20px;"></div>
    
</div>
</form>
	
	
<script type="text/javascript">

	var li = '${li}';
	
	function sumibtForm(){
		$("#order_form").submit();
	}
	
	
	function selectAddress(){
		window.location.href="/center/address_list.html?from=/center/order-info-${bean.order.orderId}.html";
	}
	
	function delOrder(id){
		dialog('信息确认','确定 要删除订单嘛',function(){
			window.location.href="/center/order-del.html?id="+id+"&li="+li;
		});
	}
	
	function toComment(id){
		window.location.href="/center/order-comment.html?id="+id+"&li="+li;		
	}
	
	$(function(){
		$("#order_form").Validform({
			tiptype:2,
			callback:function(form){
				
				form[0].submit();
				//var index = parent.layer.getFrameIndex(window.name);
				//parent.layer.close(index);
			},
			tiptype : function(msg, o, cssctl) {
				layer.msg(msg);
			}
		});
	});
	
</script>
	
</body>
</html>


