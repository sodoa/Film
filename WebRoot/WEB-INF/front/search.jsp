<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<link href="/theme/css/shop.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/jslib/endless/jquery.endless-scroll-1.3.js"></script>
</head>
<body>


	<div class="header">
		<div class="top_left1">
			<a href="/"><img src="/theme/images/back.png" style="width: 12px"></a>
		</div>
		<div class="top_center1"><p style="display:inline;cursor: pointer;">商品查询结果</p></div>
		<div class="top_right1">

			<a href="javascript:void(0)" onclick="GoCenterUrl('/cart.html')"><img
				src="/theme/images/spc.png" style="width: 29px; height: 29px">
			</a>

		</div>
	</div>

	<div class="conter" style="z-index:1;position:absolute;top:0;width:100%">
		
		<div class="block">
	
			<ul id="page-comtain">
			</ul>
			
		</div>
		
		
				
		<c:if test="${page.totalCount == 0}">
			<div style="text-align: center;border: 1px solid #eee;background-color: #fff;padding: 5px;">
			没有查询到数据 
			</div>
		</c:if>
				
		
		
		<div id="page-next"></div>

	</div>
	
<script>

$(function(){
	////////////////////////////////////////////////////////////////////////////////
	
	var w = '${w}';
	
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
	             url:"/s-more.html",
	             data:{'page':pageNo,'w':w},
	             dataType:"json",
	             success:function(data){
					if(data.result == 0){
						parse(data.list);
						document.body.scrollTop = document.body.scrollHeight;
					}
	             }
			});
	}
	
	function parse(dataList){
		 
      var htmlArray = new Array();
      
      for(var i=0;i<dataList.length;i++){
      	
      	var item = dataList[i];
      
      	 htmlArray.push('<li> ');
      	 htmlArray.push('	<div class="block-image">');
      	 htmlArray.push('		<a href="goods-'+item.goodsId+'.html" ><img src="'+item.thumbnailUrl+'"  style="width: 100%;height:200px;"> </a> <span>出售：'+item.sellcount+'件</span>');
      	 htmlArray.push('	</div>');

      	 htmlArray.push('	<div class="block-titel">'+item.goodsLname+'</div>');
      	 htmlArray.push('	<div class="block-price">');
      	 htmlArray.push('		<p>&yen;'+item.finalPrices+'</p>');
      	 htmlArray.push('		<p>');
      	 htmlArray.push('			<s>&yen;'+item.orginPrices+'</s>');
      	 htmlArray.push('		</p>');
      	 htmlArray.push('		<p>'+item.discount+'折</p>');
      	 htmlArray.push('		<a ><img src="/theme/images/detalis.png" style="height: 18px"></a>');
      	 htmlArray.push('	</div>');
      	 htmlArray.push('</li>');
	      
      }
      
      $('#page-comtain').append(htmlArray.join(""));
	}
	

});

</script>

<jsp:include page="scrollup.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>


</body>
</html>


