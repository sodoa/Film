
	
	$(function(){
		validate_login();
	});
	
	function validate_submit(){
		$("#login_form").submit();
	}
	
	function validate_login(){
		
		$("#login_form").Validform({
			ajaxPost:true,
			tiptype:2, 
			callback:function(data){
				if (data.result == 0) {
					layer.msg('登录成功');
            		window.location.href = "/my.html?p="+p;
            		return true;
				} else {
					layer.msg(data.message);
					return false;
				}
			},		
			tiptype:function(msg,o,cssctl){
				var objtip=$("#login_error_msg_group");
				cssctl(objtip,o.type);
				objtip.text(msg);
				objtip.fadeIn(2000,function(){
					objtip.hide();
				});
			}
		});
	}
	