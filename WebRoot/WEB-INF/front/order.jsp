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
<body>

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
					<input type="hidden" name="deliveryId"  datatype="*" nullmsg="请编辑收货地址" value="${address.deliveryId}" />
					<c:if test="${empty address.deliveryId}">
						<div class="m-order-address">
						    <p style="cursor: pointer;" onclick="selectAddress()" >请编辑收货地址</span></p>
						</div>            
					</c:if>
					<c:if test="${not empty address.deliveryId}">
						<div class="m-order-address">
						    <p>${address.receiverName}<span>${address.receiverPhone }</span></p>
						    <p>${address.address}</p>
						</div>            
					</c:if>					
					 <a href="javascript:void();"  onclick="selectAddress()" class="m-order-address-edit"></a>     
         		 </li>
            </ul>
            
        <div class="order-manage"> 
               <ul class="m-cartlist bgWhite">
               		<c:if test="${CartInfoVo.hasGoods}">
						<c:forEach items="${CartInfoVo.carts}" var="cartitem" varStatus="st">
		               		<li>
		                    	<div class="m-tab-list">
	                                   <div class="m-cartlist-img"><img src="${CartInfoVo.goods[st.index].thumbnailUrl}" style="width: 80px; height: 80px"></div>
	                                   <div class="m-cartlist-info">
	                                       <h3>${CartInfoVo.goods[st.index].goodsName}</h3>
	                                       <h4>${CartInfoVo.goods[st.index].goodsLname}</h4>
	                                       <h5>&yen;${CartInfoVo.goods[st.index].finalPrices} <span class="m-cartlist-nums">x ${CartInfoVo.carts[st.index].quantity}</span></h5>
	                                   </div>
		                      </div> 
		                    </li>
						</c:forEach>
					</c:if>	
               </ul>	 
         </div>
         <c:if test="${CartInfoVo.hasGoods}">
	         <div class="order-manage"> 
	         	<div style=" border: 0px solid #dedddd;background: #fff; padding: 0.04rem;font-size: 0.16rem;vertical-align: middle;margin-top: 0.1rem;padding-left:0.1rem;">
	         		<span>邮费&nbsp;(满<sp:config id="goods.postage.money"></sp:config>元包邮)</span>
	         		<span class="pay-bd">&nbsp;</span>
	         		<span style="margin-right:10px;float:right;color: #ff4f01;    font-size: 0.2rem;">
		         		<h5>
			         		<c:choose>
			         			<c:when test="${CartInfoVo.hasPostage}">
			         				+&nbsp;<sp:config id="goods.postage"></sp:config>&yen;
			         			</c:when>
				         		<c:otherwise>
				         			+&nbsp;0 &yen;
				         		</c:otherwise>
			         		</c:choose>
		         		</h5>
	         		</span>
	         	</div>
	         </div>
         </c:if>
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
           <textarea class="m-textarea"  datatype="*0-180" errormsg="留言字数需要在100个以下" name="order_remark" rows="3" cols="30" style="width:99%;border: 1px solid #eee;"></textarea>
           </div>
           </div>
       </div> 
       
       	<c:if test="${CartInfoVo.hasGoods}">
		     <div class="bottom-fxied">
					<div class="m-btn"  onclick="sumibtForm()"> <a href="javascript:void(0)" class="orange-btn">确认支付（${CartInfoVo.totalAmount}元）</a> </div>
		 	</div>
	 	</c:if>
       
    </div>
    </form>
    
    <div style="height: 60px;"></div>
    
</div>

	
<script type="text/javascript">
			
	$(function(){
		//alert(typeof WeixinJSBridge);
		
		//if (!isWeiXinBrowser()){
			//$("#paytype_div>div:last").click();
			//$("#paytype_div>div:first").remove();
		//}
		
	});
	
	
	function sumibtForm(){
		$("#order_form").submit();
	}
	
	
	function selectAddress(){
		window.location.href="/center/address_list.html?from=/center/order.html&opt=1";
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
				if(o.type == 3){
					layer.msg(msg);
				}
			}
		});
	});	
	
</script>
	
</body>
</html>


