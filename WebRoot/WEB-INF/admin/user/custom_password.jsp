<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="http://mos.xinfan.com/" %>

<jsp:include page="../fragment_top.jsp">
	<jsp:param name="menu_title" value="修改密码" />
</jsp:include>

<script type="text/javascript">
	function save_add(){
		if($("#updatePwdForm").form("validate")){
			var paramters = $("#updatePwdForm").serializeObject();
			$.post("./save_custom_password.jspx",paramters,function(json){
				if(json.result == 0){
					if(json.result == 0){
						showInfoMessage({"msg":"操作成功"});
					}
					else{
						showInfoMessage({"msg":"操作失败"});
					}
				}
				
			},"json");
		}
	}
</script>

<form id="updatePwdForm">
		<table style="width:100%;border:1px solid #eee;" width="320">
			<tr>
				<th>当前用户：</th>
				<td>${sessionMap.USERNAME}</td>
			</tr>
			<tr>
				<th>新密码：</th>
				<td><input type="password" name="password" id="txtPassword" size="32" class="easyui-validatebox" validType="maxLength[20]"  data-options="required:true"/></td>
			</tr>	
			<tr>
				<th>密码确认：</th>
				<td><input type="password" name="repassword" size="32" class="easyui-validatebox" validType="equalTo['#txtPassword']"  data-options="required:true"/></td>
			</tr>
			<tr>
				<td colspan="2">
						<div align="center" style="margin-top:5px;margin-bottom:5px;">
							<div class="buttons" style="align:center;">
									    <button  class="positive" type="button" onclick="save_add()">
									        	保存
									    </button>
							</div>	
						</div>					
				</td>
			</tr>				
		</table>
		

</form>

<jsp:include page="../fragment_bottom.jsp" />