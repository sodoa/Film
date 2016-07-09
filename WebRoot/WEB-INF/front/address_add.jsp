<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="/theme/newest/css/order.css" />
<script type="text/javascript" src="/jslib/address/GlobalProvinces_main.js"></script>
<script type="text/javascript" src="/jslib/address/GlobalProvinces_extend.js"></script>
<script type="text/javascript" src="/jslib/address/initLocation.js"></script>
<script src="/jslib/uiadmin/lib/Validform/5.3.2/Validform.js"></script>
<link href="/jslib/uiadmin/lib/Validform/5.3.2/style.css" type="text/css" rel="stylesheet" />

<script type="text/javascript">
	$(function() {
		initLocation({
			sheng_val : "湖南",
			shi_val : "长沙",
			xian_val : ""
		});
	});
</script>

<style type="text/css">

.address_select{
	width:100%;height:30px;border:1px solid #eee; height: 0.48rem;
}
</style>
</head>
<div class="g-doc">
    <div class="top-fxied">
        <header class="header"> 
            <div class="back"><a href="/center/address_list.html"><span class="icon-back"></span></a></div> 
            <div class="title">新增收货地址</div> 
            <div class="subMark"><p></p></div> 
        </header>
    </div>
    
    <div class="scroll-content">
    	<div class="m-block-form">
        	<form class="form-horizontal m-order-address-form" id="submit_form" action="address_save.html">
             <div class="form-group">
                <label for="name" class="col-xs-3">收货人</label>
                <div class="col-xs-9">
                 	<input name="receiverName" type="text" datatype="*1-20" errormsg="请输入收货人姓名" placeholder="请输入收货人姓名" value="${bean.receiverName}" />
                </div>
              </div>        	
             <div class="form-group">
                <label for="name" class="col-xs-3">手机号</label>
                <div class="col-xs-9">
                 	<input name="receiverPhone" type="text" datatype="*8-15" errormsg="请输入正确的联系方式" placeholder="请输入正确的联系方式" value="${bean.receiverPhone}" />
                </div>
              </div>
              <div class="form-group">
                <label for="name" class="col-xs-3">省份</label>
                <div class="col-xs-9">
                 	<select class="address_select"  id="sheng" name="province" datatype="*" errormsg="请选择省份" ></select>
                </div>
              </div>
              <div class="form-group">
                <label for="name" class="col-xs-3">市/县</label>
                <div class="col-xs-9">
                   <select id="shi" class="address_select" name="city" datatype="*" errormsg="请选择市/县" ></select>
                </div>
              </div>
              <div class="form-group">
                <label for="name" class="col-xs-3">区/乡</label>
                <div class="col-xs-9">
                 	<select id="xian" class="address_select" name="county" datatype="*"  errormsg="请选择区/乡"></select>
                </div>
              </div>
              <div class="form-group">
                <label for="name" class="col-xs-3">详细地址</label>
                <div class="col-xs-9">
                 	<input name="street" type="text" width="70%" datatype="*1-30" errormsg="请输入正确的详细地址" value="${bean.street}" required  placeholder="请输入正确的详细地址" />
                </div>
              </div>
              <div class="order-btn-logout"> <a href="javascript:void(0)" id="btn_complate">完成</a> </div>
            </form>
        </div>
    </div>
    
	<jsp:include page="footer.jsp"></jsp:include>
    
</div>
	

	<script type="text/javascript">
		
		var from = '${from}';
	
		$(function() {
			
			$("#submit_form").Validform({
				ajaxPost:true,
				tiptype:2, 
				callback:function(data){
					if (data.result == 0) {
						window.location.href = "/center/address_list.html";
					} else {
						layer.msg("添加失败：" + data.message);
					}
				},
				tiptype:function(msg,o,cssctl){
					if(o.type == 3){
						layer.msg(msg);
					}
				}
			});
	
			$(".address_select").click(function() {
				if (!$(this).hasClass("address_cur")) {
					$(".address_select").removeClass("address_cur");
					$(this).addClass("address_cur");
				}
			});

			$("#btn_complate").click(function() {
				$("#submit_form").submit();
			});

		});
		
	</script>


</body>
</html>


