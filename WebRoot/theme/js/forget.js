$(function() {
	validate();
	reg_vercode_event();
});

function regist_submit() {
	$("#regist_form").submit();
}

function reg_vercode_event() {

	$("#get_very_code_btn").click(function() {

		var $btn = $(this);

		var account = $("#regist_account").val();
		if (account == null || account.length < 11) {
			$('#login_error_msg_group').show();
			$("#login_error_msg_group  p").text("请输入手机号码");
			return false;
		}

		var _data = {
			"account" : account
		};

		codetimeout();

		$.ajax({
			type : "POST",
			url : "/get_forget_very_code.html?t=" + new Date().getTime(),
			data : _data,
			dataType : "json",
			success : function(data) {
				if (data.result == 0) {
					//$("#verycode").val(data.code);
				} else {
					$('#login_error_msg_group').show();
					$("#login_error_msg_group  p").text(data.message);
				}
			}
		});

	});
}

var timeObject = null;
var timeset = 60;
var time_less = 60;

function codetimeout() {

	$("#get_very_code_btn").val("重新获取(" + timeset + "s)");
	$("#get_very_code_btn").attr({
		"disabled" : "disabled"
	});

	time_less = timeset;
	timeObject = window.setInterval('repeatCodetimeout()', 1000);
}

function repeatCodetimeout() {
	time_less--;
	$("#get_very_code_btn").val("重新获取(" + time_less + "s)");

	if (time_less <= 0) {

		if (timeObject != null) {
			clearInterval(timeObject);
			$("#get_very_code_btn").val("获取验证码");
			$("#get_very_code_btn").removeAttr("disabled");
		}
	}
}

function validate() {
	$("#regist_form").Validform({
		ajaxPost : true,
		tiptype : 2,
		callback : function(data) {
			if (data.result == 0) {
				layer.msg('修改成功');
				window.location.href = "/login.html";
				return true;
			} else {
				layer.msg(data.message);
				return false;
			}
		},
		tiptype : function(msg, o, cssctl) {
			if(o.type == 3){
				var objtip = $("#login_error_msg_group");
				cssctl(objtip, o.type);
				objtip.text(msg);
				objtip.fadeIn(2000, function() {
					objtip.hide();
				});
			}
		}
	});
}