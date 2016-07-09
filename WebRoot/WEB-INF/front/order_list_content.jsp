<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="/jslib/endless/jquery.endless-scroll-1.3.js"></script>
<ul class="m-cartlist" id="page-comtain">
</ul>
<div id="page-next"></div>


<script type="text/javascript">
var li = '${li}';
		
$(function(){
	
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
	             url:"/center/order_list_data.html",
	             data:{'page':pageNo,'li':li},
	             dataType:"html",
	             success:function(data){
	            	 $('#page-comtain').append(data);
	             }
			});
	}
	
});



function toCancel(id){
	dialog('信息确认','确定 要删除订单嘛',function(){
		window.location.href="/center/order-del.html?id="+id+"&li="+li;
	});
}

function toPay(id){
	window.location.href="/center/order-info-"+id+".html?li="+li;
}

function toComment(id){
	window.location.href="/center/order-comment.html?id="+id+"&li="+li;		
}

function seeComment(id){
	window.location.href="/center/order-comment-view.html?id="+id+"&li="+li;		
}


</script>
