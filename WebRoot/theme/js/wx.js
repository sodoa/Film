
$(function(){
	alert("-------------");
	configWX();
});


function GetClearUrlPath()
{
　　var url = document.location.toString();
    var relUrl = url;

　　if(relUrl.indexOf("?") != -1){
　　　　relUrl = relUrl.split("?")[0];
　　}

	if(relUrl.indexOf("#") != -1){
		relUrl = relUrl.split("?")[0];
	}
　　return relUrl;
}

function configWX() {
	if (wx != null) {
		$.ajax({
			type : "POST",
			url : "/film/share/sign.html?t="+new Date().getTime(),
			data : {
				"url" : GetClearUrlPath()
			},
			dataType : "json",
			success : function(data) {
				if (data.result == 0) {
					
					var wxsid = data["wxsid"];
					
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
						onShareListner(wxsid);
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
	var link = addUrlPara('wxsid',shareid);
	var imgUrl = 'http://'+window.location.host+":"+window.location.port+"/logo-64x64.png";
	
	return {
		title: '果然逗-关注健康高品质水果生活', // 分享标题
		imgUrl: imgUrl, // 分享图标
	    link: link, // 分享链接
	    type: 'link',
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
	var imgUrl = 'http://'+window.location.host+":"+window.location.port+"/logo-64x64.png";
	
	return {
	    link: link, // 分享链接
		title: '果然逗-关注健康高品质水果生活', // 分享标题
		imgUrl: imgUrl, // 分享图标
	    type: 'link', // 分享类型,music、video或link，不填默认为link
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
	var imgUrl = 'http://'+window.location.host+":"+window.location.port+"/logo-64x64.png";
	
	return {
		title: '果然逗-关注健康高品质水果生活', // 分享标题
		imgUrl: imgUrl, // 分享图标
	    link: link, // 分享链接
	    type: 'link',
	    success: function () { 
	    	alert('分享成功');
	    },
	    cancel: function () { 
	    	alert('分享失败');
	    }
	};
}