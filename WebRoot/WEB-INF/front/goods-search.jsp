<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/theme/newest/css/search.css" />
<script type="text/javascript" src="/jslib/endless/jquery.endless-scroll-1.3.js"></script>
<link type="text/css" rel="stylesheet" href="/theme/newest/css/order.css" />

<style type="text/css">
.pic-product-list .d-img{margin:0rem 0rem;} 
.pic-product-list li{
	width:100%;
	float:none;
}
.pic-product-list .d-tit {
	text-align: left;
}
.pic-product-list .price{
	margin: 0px;
}
</style>
</head>
<body>

<div class="g-doc" style="overflow-y: scroll;">
	<div class="category" style="display: none;z-index: 9999;position: fixed;height: 100%;width: 100%;background-color: white;max-width: 640px;">
	    <div class="top-fxied">
	            <header class="header"> 
	                <div class="back"><a href="javascript:void(0);" onclick="panel_close_search()"><span class="icon-back"></span></a></div> 
	                <div class="m-seach">
	                    <form method="get" class="searchForm">
	                    <div class="text-area"><input  name="panel_search_word" id="panel_search_word" value="${w}"  type="text"  class="text" maxlength="18"  value="搜索您想要的宝贝" >
	                    </div>
	                    <input type="button" class="btn-sub"  onclick="panel_search_form()" >
	                    </form>
	
	                </div> 
	            </header>
	 	</div>
	    <div class="scroll-content">
	    	<div class="m-search-tit">热门关键字</div>
	        <ul class="m-search-con">
	        	<sp:search_words_list_tag>
					<c:forEach var="item" items="${list}">
						<li><a onclick="panel_pickup_words('${item.words}')" style="padding:5px;padding-right:10px;padding-top:5px;padding-bottom:5px;border:1px solid #eee;margin:5px;cursor: pointer;">${item.words}</a></li>
					</c:forEach>
				</sp:search_words_list_tag>
	        </ul>
	    </div>
	</div>
</div>

<div class="g-doc">
		<div class="top-fxied">
			<header class="index-top-bar">
				<div class="s-input-select">
					<form method="get" class="searchForm">
						<div class="text-area">
							<input type="text" name="keyword" class="text" maxlength="18" id="search_word"  value="${w}"  placeholder="搜索您想要的宝贝">
						</div>
						<input type="button" class="btn-sub">
					</form>
				</div>
				<a href="/cart.html" class="top-bar-cart">
					<div class="icons-cart">
						<i class="cart-sales-nums"><sp:cart_num_tag></sp:cart_num_tag></i>
					</div>
				</a>
			</header>
		</div>
		
		<div class="scroll-content">
			<div class="block">
				 <ul class="pic-product-list" id="page-comtain">
				</ul>
			</div>
			<div id="page-next"></div>
		</div>
	<jsp:include page="scrollup.jsp"></jsp:include>
	<jsp:include page="footer.jsp"></jsp:include>	
	
</div>	

<script>

function panel_close_search(){
	$(".category").hide();
}

function panel_pickup_words(keywords){
	$("#panel_search_word").val(keywords);
	panel_search_form();
}

function panel_search_form(){
	var w = $("#panel_search_word").val();
	//if(w.length>0){
	var encode_w = encodeURIComponent(w);
	window.location.href="g-s.html?w="+encode_w+"&theme="+theme;
	//}
}

var theme = "${theme}";
var show = "${show}";

$(function(){

	var ch=$(".category").height();
	var hh=$(".header").height();
	
	if(show =="1"){
		$(".category").show();
	}
	//分类框动画

	$("#search_word").bind("focusin",function(){
		$(".category").show();
	});
	
	$("#panel_search_word").keydown(function(e){
		var theEvent = window.event || e; 
		var code = theEvent.keyCode || theEvent.which; 
		if (code == 13) { 
			var word = $(this).val();
			if(word == null || word.length ==0 || word.trim().length == 0){
				return;
			}
			panel_search_form();
			//window.location.href = "/s.html?w="+encodeURI(word);
		} 
	});
	
	//选择类别
	
	////////////////////////////////////////////////////////////////////////////////
	
	var cur = '${w}';
	var totalPage = '${page.totalPage}';
	var page = 1;
	
	$('#page-comtain').endlessScroll({
	    fireOnce: true,
	    fireDelay: false,
	    callback: function(){
	    	if(page<=totalPage){
	    		ajaxPageContent(page++);
	    	}
	    }
	});
	
	function ajaxPageContent(pageNo){

	      $.ajax({type:"POST",
	             url:"/g-s-more.html",
	             data:{'page':pageNo,'w':cur,'theme':theme},
	             dataType:"json",
	             success:function(data){
					if(data.result == 0){
						parse(data.list);
						//document.body.scrollTop = document.body.scrollHeight;
					}
	             }
			});
	}
	
	function parse(dataList){
		 
      var htmlArray = new Array();
      
      for(var i=0;i<dataList.length;i++){
      	
      	var item = dataList[i];
      	
      	 htmlArray.push('<li>');
      	 htmlArray.push('<div class="d-img"> <a href="goods-'+item.goodsId+'.html"><img onerror="imagerror(this)"   src="'+item.thumbnailUrl+'" style="width: 100%;height:200px;"> </a></div> ');
      	 htmlArray.push('<div class="d-tit">【'+item.weight+item.unit+'】'+item.goodsLname+'</div> ');
      	 htmlArray.push('<div class="d-subMark"> ');
      	 htmlArray.push('	<p class="price">&yen;'+item.finalPrices+'</p> ');
      	 htmlArray.push('    <p class="change"><del>&yen;'+item.orginPrices+'</del>&nbsp;&nbsp;&nbsp;<span class="discount">'+item.discount+'折</span></p> ');
      	 htmlArray.push('    <p class="btn"><a href="javascript:void(0);" onclick="put_goods_to_order('+item.goodsId+');"></a></p> ');
      	 htmlArray.push('</div> ');
      	 htmlArray.push('</li> ');
      }
      
      $('#page-comtain').append(htmlArray.join(""));
	}

});


function put_goods_to_order(current_goods_id){

	$.ajax({type:"POST",
         url:"/put-goods-cart.html",
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
}

</script>

</body>
</html>


