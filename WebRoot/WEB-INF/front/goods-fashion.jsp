<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<link href="/theme/css/shop.css" type="text/css" rel="stylesheet" />
<link href="/theme/css/details.css" type="text/css" rel="stylesheet" />
<link type="text/css" href="/jslib/swipe/swipe.css" rel="stylesheet" />
<script type="text/javascript" src="/jslib/swipe/jquery.touchSwipe.min.js"></script>

<style type="text/css">

.shopcart{
	clear:both;
	width:20%;
	height:40px;
	min-width:240px;
	background-color:#fff;
	border:1px solid #eee;
	position:fixed;
	bottom:10px;

	max-width: 340px;
} 
.shopcart .cart{
	width:30%;
	height:20px;
	line-height:20px;
	text-align:center;
	margin-top:5px;
	background-color:#FFF;
	margin-left:5%;
	float:right;
	border-radius:5px;
	
}
.shopcart button{
	width:55%;
	margin-left:5%;
	height:30px;
	float:right;
	margin-top:5px;
	background-color:#ff2d49;
	border:0;
	font-size:14px;
	color:#FFF;
	border-radius:5px;
}

</style>

<script type="text/javascript">
    $(document).ready(
        function() {
            var nowpage = 0;
            var maxpage = '${fn:length(gImages)}';
            //给最大的盒子增加事件监听
            $(".container").swipe(
                {
                    swipe:function(event, direction, distance, duration, fingerCount) {
                         if(direction == "up"){
                            nowpage = nowpage + 1;
                         }else if(direction == "down"){
                            nowpage = nowpage - 1;
                         }

                         if(nowpage > (maxpage-1)){
                            nowpage = (maxpage-1);
                         }

                         if(nowpage < 0){
                            nowpage = 0;
                         }

                        $(".container").animate({"top":nowpage * -100 + "%"},400);

                        $(".page").eq(nowpage).addClass("cur").siblings().removeClass("cur");
                    }
                }
            );
        }
    );
    
	
	$(function(){
		
		var current_goods_id = '${goods.goodsId}';
		
		$("#put_goods_in_cart").click(function(){
			
			checkLogin(function(){
				$.ajax({type:"POST",
		             url:"/center/put-goods-cart.html",
		             data:{"goodsId" : current_goods_id},
		             dataType:"json",
		             success:function(data){
		            	if(data.result ==0){
		            		alert('添加成功');
		            	}
		            	else{
		            		alert('添加失败');
		            	}
		             }
				});
				
			},function(){
				window.location.href = "/login.html?p="+GetUrlRelativePath();
			});
			
		});
	});	
		
		
</script>
</head>
<body>
	
	<div class="detailsBlock1">
		<div class="container">
			<c:forEach var="item" items="${gImages}" varStatus="s">
			  	<div class="page">
					 <img alt="" src="${item.imageUrl}" style="width: 100%;cursor: pointer;" height="100%">
				</div>
			</c:forEach>
		</div>
		
		<img class="xiangxiatishi" src="/resource/images/prompt.png">
	</div>
	
	
	
	<div class="shopcart">
		<a href="/cart.html"><div class="cart" style="cursor: pointer;"><img src="assets/i/detail/cart.PNG"></div></a>
		<button style="cursor: pointer;    font-family: 'Microsoft YaHei';" id="put_goods_in_cart">加入购物车</button>
	</div>

</body>
</html>


