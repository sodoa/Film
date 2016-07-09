<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html class="no-js">
<head>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<div class="g-doc">
		<div class="top-fxied">
			<header class="index-top-bar">
				<div class="s-input-select">
					<form method="get" class="searchForm">
						<div class="text-area">
							<input id="search_word" type="text" name="keyword" class="text" maxlength="18" id="keyword" value="搜索您想要的宝贝" >
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
		<div class="scroll-content" style="margin: 0.75rem auto 0.83rem">
			<!-- 内容区域 -->
			<div class="scrollview">
				<!-- 焦点图 -->
				<div class="m-slider">
					<div class="m-slider-con-wrap" id="JslideWrap" style="display: block;">
						<div class="m-slider-con">
							<a class="m-slider-item" href="javascript:void(0);">
								<img src="/theme/images/1.jpg" />
							</a>
							<a class="m-slider-item" href="javascript:void(0);">
								<img src="/theme/images/2.jpg" />
							</a>
						</div>
					</div>
					<div class="m-slider-nav" id="JslideNav"></div>
				</div>
				<!-- 菜单选择 -->
				<div class="m-block m-menu">
					<div class="m-labels">
						<a href="g-s.html?theme=1" class="ico-fruit">全部水果</a>
						<a href="g-s.html?theme=4" class="ico-girlfriend">送女友</a>
						<a href="g-s.html?theme=2" class="ico-parents">孝敬爸妈</a>
					</div>
					<div class="m-labels">
						<a href="g-s.html?theme=3" class="ico-player">游戏玩家</a>
						<a href="g-l.html?theme=20" class="ico-activity">火爆活动</a>
						<a href="ac.html" class="ico-money">分销赚钱</a>
					</div>
				</div>
			</div>
			<!-- 火爆单品 -->
			<div class="m-block">
				<div class="m-index-th">爆品</div>
				<div class="m-con">
					<ul class="list-pic">
						<c:forEach var="item" items="${burstList}">
							<li>
								<a href="/goods-${item.goodsId}.html">
									<img src="${item.thumbnailUrl}" onerror="imagerror(this)"  height="200" defimage="defimage"> <span>
										<p class="name" style="overflow: hidden; ">【${item.weight}${item.unit}】${item.goodsLname}&nbsp;</p></span>
								
				                    <p class="discount">${item.discount}折</p>
				                    <p class="price">火爆价<br/><span class="big">${item.finalPrices}</span>元</p>		
				                    </a>						
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<jsp:include page="scrollup.jsp"></jsp:include>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
	<script type="text/javascript" src="/theme/js/wap.js"></script>
		<script type="text/javascript">
		$(function(){
			
			$("#search_word").bind("focusin",function(){
				window.location.href= "/g-s.html?theme=1&show=1";
			});
			
		});
		
		var title = '<sp:config id="goal.title"></sp:config>|<sp:config id="goal.description"></sp:config>'	;
		var imgUrl = 'http://'+window.location.host+":"+window.location.port+"/theme/newest/logo/128x128.png";

		function getMenuShareTimeline(wxsid){
			
			var shareid = wxsid;
			var link = addUrlPara('wxsid',shareid);
			
			return {
			    title: title, // 分享标题
			    link: link, // 分享链接
			    imgUrl: imgUrl, // 分享图标
			    success: function () { 
			    	alert('分享成功');
			    },
			    cancel: function () { 
			    	alert('分享失败');
			    }
			};
		}

		function getMenuShareAppMessage(wxsid){
			
			var shareid = wxsid;
			var link = addUrlPara('wxsid',shareid);
			
			return {
			    title: title, // 分享标题
			    desc: title, // 分享描述
			    link: link, // 分享链接
			    imgUrl: imgUrl, // 分享图标
			    type: 'link', // 分享类型,music、video或link，不填默认为link
			    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			    success: function () { 
			    	alert('分享成功');
			    },
			    cancel: function () { 
			    	alert('分享失败');
			    }
			};
		}

		function getMenuShareQQ(wxsid){
			
			var shareid = wxsid;
			var link = addUrlPara('wxsid',shareid);
			
			return {
			    title: title, // 分享标题
			    desc: title, // 分享描述
			    link: link, // 分享链接
			    imgUrl: imgUrl, // 分享图标
			    success: function () { 
			    	alert('分享成功');
			    },
			    cancel: function () { 
			    	alert('分享失败');
			    }
			};
		}
		
	</script>
</body>
</html>