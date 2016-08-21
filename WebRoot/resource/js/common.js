
jQuery.ajaxSettings.traditional = true;


//js去除空格函数
//此处为string类添加三个成员
String.prototype.trim = function(){
    return trim(this);
};
String.prototype.ltrim = function(){
    return ltrim(this);
};
String.prototype.rtrim = function(){
    return rtrim(this);
};

//此处为独立函数
function ltrim(str){
  var i;
  for (i = 0; i < str.length; i++) {
      if (str.charAt(i) != " " && str.charAt(i) != " ") 
          break;
  }
  str = str.substring(i, str.length);
  return str;
}

function rtrim(str){
  var i;
  for (i = str.length - 1; i >= 0; i--) {
      if (str.charAt(i) != " " && str.charAt(i) != " ") 
          break;
  }
  str = str.substring(0, i + 1);
  return str;
}

function trim(str){
	  return ltrim(rtrim(str));
}


$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


Date.prototype.format = function(format) {
	/*
	 * format="yyyy-MM-dd hh:mm:ss";
	 */
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}; 

Date.prototype.formatLong = function(){
	return this.format('yyyy-MM-dd hh:mm:ss');
};

Date.prototype.formatShort = function(){
	return this.format('yyyy-MM-dd');
};

//获取页面上下文高度
function documentHeight(){
	return $(parent.document.body).height();
}
//面板垂直居中top
function panelTop(h){
	if(h==null || h==''){
		return 0;
	}else{
		return $(parent.window.document.body).height()/2-h/2;			
	}
}
//面板水平居中Left
function panelLeft(w){
	return $(parent.window.document.body).width()/2-w/2;
}

function dataGridHeight(){
	return $(parent.window.document.body).height()-40;
}

Date.prototype.format = function(format) {

	/*
	 * format="yyyy-MM-dd hh:mm:ss";
	 */
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	
	if(format.indexOf("NaN")>-1){
		return "";
	}
	return format;
}; 

Date.prototype.formatLong = function(){
	return this.format('yyyy-MM-dd hh:mm:ss');
};

Date.prototype.formatMiddle = function(){
	return this.format('yyyy-MM-dd hh:mm');
};

Date.prototype.formatShort = function(){
	return this.format('yyyy-MM-dd');
};

function showQueryWindow(id){
	if(id==null || id == ''){
		id = "queryWindow";
	}
	
	try{
		if($("#"+id)){
			$("#"+id).css({position:"absolute",left:20,top:40,zIndex:1000});
			$("#"+id).show();
			var cc = $(window.document.body);
			$("<div id=\"queryWindow_mask\" class=\"window-mask\"></div>").css({left:0,top:0,width:cc.width(),height:cc.height()}).appendTo(cc);
		}
	}catch(e){
		alert(e);
	}
}

function closeQueryWindow(id){
	if(id==null || id == ''){
		id = "queryWindow";
	}
	try{
		if($("#"+id)){
			$("#"+id).hide();
			$("#queryWindow_mask").remove();
		}
	}catch(e){
		alert(e);
	}
}

function showInfoMessage(option){
	$.messager.show({title: "操作提示",msg: option.msg,showType: 'slide' });
}

//弹出确认框
function showConfirmMessage(option){

	$.messager.confirm("操作确认", "您确定要执行此操作吗?", function (r) {
		option.callback(r);
	});
}

function showWindow(option) {
	var op = {href:option.href,"modal":true,"collapsible":false,"minimizable":false,"maximizable":false,left:2,top:2,width:option.width,height:option.height,title:option.title,"callback":option.callback};
	var win = $("#model_window").window(op);
	win.window("hcenter");
}

function closeWindow(){
	$("#model_window").window("close");
}

function showAlert(option){
	$.messager.alert("提示信息",option.msg);
}

function formatUserState(value){
	if(value == 1){
		return "<span style='color:green;font-weight: bold;'>正常</span>";
	}
	else if(value == 2){
		return "<span style='color:red;font-weight: bold;'>禁用</span>";
	}
	else if(value ==3 ){
		return "<span style='color:gray;font-weight: bold;'>已删除</span>";
	}
	else{
		return "未知";
	}
}

function formatIdentifyType(value){
	if(value == 1){
		return "<span style='color:green;font-weight: bold;'>门店</span>";
	}
	else if(value == 2){
		return "<span style='color:red;font-weight: bold;'>手机</span>";
	}
	else{
		return "未知";
	}
}

//1正常 2申诉 3申诉成功 4申诉失败
function formatIdentifyState(value){
	if(value == 1){
		return "<span style='color:green;font-weight: bold;'>正常</span>";
	}
	else if(value == 2){
		return "<span style='color:red;font-weight: bold;'>申诉中</span>";
	}
	else if(value == 3){
		return "<span style='color:red;font-weight: bold;'>申诉成功</span>";
	}
	else if(value == 4){
		return "<span style='color:red;font-weight: bold;'>申诉失败</span>";
	}	
	else{
		return "未知";
	}
}

//1申诉成功 2申诉失败 3未处理
function formatAppealResult(value){
	if(value == 1){
		return "<span style='color:green;font-weight: bold;'>申诉成功</span>";
	}
	else if(value == 2){
		return "<span style='color:red;font-weight: bold;'>申诉失败</span>";
	}
	else if(value == 3){
		return "<span style='color:red;font-weight: bold;'>未处理</span>";
	}
	else{
		return "未知";
	}
}

function formatProductState(value){
	if(value == 0){
		return "<span style='color:green;font-weight: bold;'>未消费</span>";
	}
	else if(value == 1){
		return "<span style='color:red;font-weight: bold;'>已消费</span>";
	}
	else{
		return "未知";
	}
}

function formatSex(value){
	if(value == 1){
		return "男";
	}
	else if(value == 2){
		return "女";
	}
	else{
		return "未知";
	}
}

function formatRoleType(value){
	if(value == '1'){
		return "<span style='color:green;font-weight: bold;'>管理员</span>";
	}
	else if(value == '2'){
		return "<span style='color:red;font-weight: bold;'>经办员</span>";
	}
	else{
		return "其它";
	}
}

function formatFunType(value){
	if(value == '1'){
		return "<span style='color:green;font-weight: bold;'>菜单</span>";
	}
	else if(value == '2'){
		return "<span style='color:green;font-weight: bold;'>按钮</span>";
	}
	else if(value == '3'){
		return "<span style='color:green;font-weight: bold;'>链接</span>";
	}
	else{
		return "其它";
	}
}

function checkLogin(sucessCallback,errorCallback){
	
	$.ajax({type:"POST",
        url:"/userstate.html",
        dataType:"json",
        success:function(data){
	       	if(data.result ==0){
	       		if(sucessCallback!=null){
	       			sucessCallback();
	       		}
	       	}
	       	else{
	       		if(errorCallback!=null){
	       			errorCallback();
	       		}
	       	}
        },
        error:function(){
       		if(errorCallback!=null){
       			errorCallback();
       		}
        }
	});
}

function GetUrlRelativePath()
{
	var url = document.location.toString();
	var arrUrl = url.split("//");
	var start = arrUrl[1].indexOf("/");
	var relUrl = arrUrl[1].substring(start);
	if(relUrl.indexOf("?") != -1){
		relUrl = relUrl.split("?")[0];
	}
	return relUrl;
}




function GoCenterUrl(url){
	checkLogin(function(){
		window.location.href= url;
	},function(){
		window.location.href= "/login.html?p="+GetUrlRelativePath();
	});
}

function imagerror(obj){
	obj.src = "/theme/newest/images/no-image-large.png";
}

function addUrlPara(name, value) {  
    var currentUrl = window.location.href.split('#')[0];  
    if (/\?/g.test(currentUrl)) {  
        if (/name=[-\w]{4,25}/g.test(currentUrl)) {  
            currentUrl = currentUrl.replace(/name=[-\w]{4,25}/g, name + "=" + value);  
        } else {  
            currentUrl += "&" + name + "=" + value;  
        }  
    } else {  
        currentUrl += "?" + name + "=" + value;  
    }  
    
    if (window.location.href.split('#')[1]) {  
       return currentUrl + '#' + window.location.href.split('#')[1];  
    } else {  
       return currentUrl;  
    }  
}  

function isWeiXinBrowser(){
        var ua = navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == "micromessenger") {
        	return true;
        }
	return false;
}

/**
window.alert = function(msg){
	layer.msg(msg,{
		  time: 2000
		});
}
**/

//对话框
//单键对话框
function dialog(titel,conter,callback){
	
	layer.confirm(conter, function(){
			callback();
		}, function(){
	});
	
}
//双键对话框
function dialog1(titel,conter){
	var obj="<div class=\"dialog\"><p>"+titel+"</p>"+
	         "<p>"+conter+"</p>"+
	         "<p id=\"cancel\">确定</p>"
			 "</div>";
	$("body").append(obj);  
	
	$("#cancel").on("click",function(){		
		$(".dialog").remove();
	});
}
//返回上一页
function return_prepage(){  
	window.history.back();
}  

function close_window(){
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭   
}

