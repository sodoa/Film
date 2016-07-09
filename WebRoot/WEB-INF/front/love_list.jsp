<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/theme/newest/css/order.css" />

</head>
<body>

<div class="g-doc">
    <div class="top-fxied">
            <header class="header"> 
                <div class="back"><a href="/center/my_center.html"><span class="icon-back"></span></a></div> 
                <div class="title">我的收藏</div>
                <div class="subMark"><p></p></div>
            </header>
            
  </div>
    
    <div class="scroll-content">
        <ul class="pic-product-list">
       		<c:forEach var="item" items="${list}">
	        	<li>
	            	<div class="d-img"><img src="${item.thumbnail_url}"></div>
	                <div class="d-tit">${item.goods_lname}</div>
	                <div class="d-subMark">
	                	<p class="price">&yen;${item.final_prices}</p>
	                    <p class="change"><del>&yen;${item.orgin_prices}111</del><br/><span class="discount">${item.discount}折</span></p>
	                    <p class="btn close" onclick="del(this,'${item.love_id}')"><span class="close-btn"></span></p>
	                </div>
	            </li>
            </c:forEach>
        </ul>
    </div>
    
    <jsp:include page="footer.jsp"></jsp:include>
    
</div>

<script type="text/javascript">
function del(obj,id){
	
	dialog('删除提示','确认要删除吗？',function(){
		  var _data = {"id": id};
	      $.ajax({type:"POST",
	             url:"love-delete.html?t="+new Date().getTime(),
	             data:_data,
	             dataType:"json",
	             success:function(data){
	            	if(data.result ==0){
	            		location.replace(location.href);
	            	}
	            	else{
	            		alert(data.message);
	            	}
	             }
			});
	});

}
</script>
</body>
</html>


