<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分享电影啦！在线影片，最新大片，在这里看电影真的很方便！朋友们，快点关注看电影！</title>
<meta name="keywords" content="电影,爆品电影" />
<meta name="description" content="我一直在这里看电影，最新大片，在线影片，真的很喜欢，朋友们快来看吧!扫描二维码就可以一起看了" />

<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/wx.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/jweixin-1.0.0.js"></script>
<style type="text/css">
.desc {
    background: #d5d5d5;
    border-radius: 0.107rem;
    padding: 0.1rem;
    margin: 0.15rem 0;
    color: #000;
    font-size:42px;
}
</style>
</head>
<body>

<c:if test="${from==1}">
<div class="scroll-content">
	<div style="height: 100%">
		<div style="padding: 10px;" class="desc">点击右上角，将本页面分享到朋友圈吧！</div>
		<div style="width:100%;">
			<div>点击右上角分享，如有好友加入可获得免费看电影一个月时间</div>
			<c:forEach var="item" items="${list}">
				<img src="${pageContext.request.contextPath}/image.jspx?i=imageurl" width="99%" style="visibility: visible !important; height: auto !important;">
			</c:forEach>
		</div>
	</div>
	<div id="tt"></div>
</div>

	<script type="text/javascript">
		var title = '${share.title}'	;
		var imgUrl = 'http://'+window.location.host+":"+window.location.port+"/${pageContext.request.contextPath}/image.jspx?i=${share.headimg}";
		
		if (wx != null) {
			$.ajax({
				type : "POST",
				url : "/film/share/sign.html?t="+new Date().getTime() + "&filmid=" +'${share.filmid}'+"&shareid=${share.shareid}",
				data : {
					"url" : GetClearUrlPath()
				},
				dataType : "json",
				success : function(data) {
					if (data.result == 0) {
						
						var refid = data["refid"];
						
						//alert(data["signature"]);

						wx.config({
							debug : false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
							appId : data["appId"], // 必填，公众号的唯一标识
							timestamp : data["timestamp"], // 必填，生成签名的时间戳
							nonceStr : data["nonceStr"], // 必填，生成签名的随机串
							signature : data["signature"],// 必填，签名，见附录1
							jsApiList : [ 'onMenuShareTimeline',
									'onMenuShareAppMessage', 'onMenuShareQQ',
									'onMenuShareQZone', 'onMenuShareWeibo' ]
						// 必填，需要使用的JS接口列表，所有JS接口列表见附录2
						});

						wx.ready(function() {
							console.log("wx config success");
							onShareListner(refid);
						});

						wx.error(function(res) {
							console.log("wx config error :" + res);
						});

					} else {
						console.log("wx config error " + data.message);
					}
				}
			});
		}
		
		function onShareListner(wxsid){

			if(wx !=null){
					wx.onMenuShareTimeline(getMenuShareTimeline(wxsid));
					wx.onMenuShareAppMessage(getMenuShareAppMessage(wxsid));
					wx.onMenuShareQQ(getMenuShareQQ(wxsid));
			}
		}
		
		
		function getMenuShareTimeline(wxsid){
			
			var shareid = wxsid;
			var link = addUrlPara('refid',shareid);
			
			return {
			    title: title, // 分享标题
			    link: link, // 分享链接
			    imgUrl: imgUrl, // 分享图标
			    success: function () { 
			    	updateCount(shareid);
			    	alert('分享成功');
			    },
			    cancel: function () { 
			    	alert('分享失败');
			    }
			};
		}
	
		function getMenuShareAppMessage(wxsid){
			
			var shareid = wxsid;
			var link = addUrlPara('refid',shareid);
			
			return {
			    title: title, // 分享标题
			    desc: title, // 分享描述
			    link: link, // 分享链接
			    imgUrl: imgUrl, // 分享图标
			    type: 'link', // 分享类型,music、video或link，不填默认为link
			    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			    success: function () { 
			    	updateCount(shareid);
			    	alert('分享成功');
			    },
			    cancel: function () { 
			    	alert('分享失败');
			    }
			};
		}
	
		function getMenuShareQQ(wxsid){
			
			var shareid = wxsid;
			var link = addUrlPara('refid',shareid);
			
			return {
			    title: title, // 分享标题
			    desc: title, // 分享描述
			    link: link, // 分享链接
			    imgUrl: imgUrl, // 分享图标
			    success: function () { 
			    	updateCount(shareid);
			    	alert('分享成功');
			    },
			    cancel: function () { 
			    	alert('分享失败');
			    }
			};
		}
		
		function updateCount(refid){
			$.ajax({
				type : "POST",
				url : "./sharecount.jspx?t="+new Date().getTime() + "&refid=" +refid,
				dataType : "json"
			});
		}
	</script>
</c:if>

<c:if test="${from==2}">
<div class="scroll-content">
	<div style="height: 100%">
		<div style="width:100%;">
			<div>长按二维码关注微信公众号后进行观看</div>
			<img id="example2" class="example-image" width="100%" height="90%" src="${pageContext.request.contextPath}/share/image.html?t=${random}&refid=${refid}">
			<div style="padding: 10px;border: 1px solid #eee;">
				${share.description}
			</div>
			<c:forEach var="item" items="${list}">
				<img src="${pageContext.request.contextPath}/image.jspx?i=imageurl" width="99%" style="visibility: visible !important; height: auto !important;">
			</c:forEach>
		</div>
	</div>
	<div id="tt"></div>
</div>

</c:if>
 
</body>
</html>