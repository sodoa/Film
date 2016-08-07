<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分享电影啦！在线影片，最新大片，在这里看电影真的很方便！朋友们，快点关注看电影！</title>
<meta name="keywords" content="电影,爆品电影" />
<meta name="description" content="我一直在这里看电影，最新大片，在线影片，真的很喜欢，朋友们快来看吧!扫描二维码就可以一起看了" />

<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/wx.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/theme/js/jweixin-1.0.0.js"></script>
</head>
<body>
<div class="scroll-content">
		<div style="height: 100%">
			<div style="padding: 10px;" class="desc">点击右上角，将本页面分享到朋友圈吧！</div>
			<div style="width:100%;">
				<img src="${pageContext.request.contextPath}/image.jspx?i=/temp/g/1469279534276/tb/1469279579537.jpg" width="49%" style="width: auto\9; width:49%;visibility: visible !important; height: auto !important;">
				<img id="example2" class="example-image" width="49%" height="90%" src="${pageContext.request.contextPath}/share/image.html?t=${random}">
			</div>
			<div style="padding: 10px;" class="desc">通过扫描上方的“我的二维码”，赶快让朋友们注册“爆品电影”，享受优质影片同时，轻松赚取免费电影时间吧！</div>
		</div>
</div>

	<script type="text/javascript">
	
		var title = '爆品电影真的真的很好看！朋友们，快点关注看电影！'	;
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