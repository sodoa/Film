<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<script type="text/javascript" src="/jslib/endless/jquery.endless-scroll-1.3.js"></script>
<script type="text/javascript" src="/jslib/timecountdown/timeCountDown.js"></script>
</head>
<body>

<div class="g-doc">
<div class="top-fxied">
<header class="header"> 
                <div class="back"><a href="/"><span class="icon-back"></span></a></div> 
                <div class="title">火爆活动</div> 
                <div class="subMark"><p></p></div> 
            </header>
</div>

<div class="scroll-content">

<!-- 火爆单品 -->
<div class="m-block">
    <div class="m-con">
       <ul class="list-pic" id="page-comtain">

       </ul> 
       <div id="page-next"></div>
    </div>
</div>


</div>

<jsp:include page="scrollup.jsp"></jsp:include>
<jsp:include page="footer.jsp"></jsp:include>

</div>
	
	
<script>

function panel_search_form(){
	var w = $("#panel_search_word").val();
	//if(w.length>0){
	var encode_w = encodeURIComponent(w);
	window.location.href="g-l.html?w="+encode_w+"&theme="+theme;
	//}
}

var theme = "${theme}";

$(function(){

	var ch=$(".category").height();
	var hh=$(".header").height();

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
	             url:"/g-l-more.html",
	             data:{'page':pageNo,'w':cur,'theme':theme},
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
      
      for(var i=0;i<dataList.length;i++){
    	var htmlArray = new Array();
      	var item = dataList[i];
      	
      	var clockid = item.goodsId;
      	
      	
    	htmlArray.push('<li> ');
    	htmlArray.push('   <a href="/gl-'+item.goodsId+'.html"><img style="width:100%;height:35%;" height="35%" width="100%" src="'+item.thumbnailUrl+'" alt=""  /><span>【'+item.weight+item.unit+'】'+item.goodsLname+'</span></a> ');
    	htmlArray.push('   <div class="toolbar"> ');
    	htmlArray.push('   	<div class="time" id="'+clockid+'">仅剩<em class="day countime">04</em> 天<em class="hour countime">01</em>  时<em class="mini countime">05</em> 分<em class="sec countime">01</em> 秒</div> ');
    	htmlArray.push('       <div class="count">库存：<em >'+item.totalAmount+'</em>&nbsp;&nbsp;</div> ');
    	htmlArray.push('   </div> ');
    	htmlArray.push('</li> ');
      	
		var d = new Date(parseFloat(item.timeLimit));
		
		$('#page-comtain').append(htmlArray.join(""));
		//alert($('#'+clockid+"  .day").html());
		var obj = {
				 day: $('#'+clockid+"  .day").get(0),
			     sec: $('#'+clockid+"  .sec").get(0),
			     mini: $('#'+clockid+" .mini").get(0),
			     hour: $('#'+clockid+" .hour").get(0)
			};
		 
		fnTimeCountDown(d, obj);
      }
     
	}

});

</script>




</body>
</html>


