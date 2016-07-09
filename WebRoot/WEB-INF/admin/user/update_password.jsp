<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="http://mos.xinfan.com/" %>

<script type="text/javascript">
	function save_add(){
		if($("#updatePwdForm").form("validate")){
			var paramters = $("#updatePwdForm").serializeObject();
			$.post("./save_password.jspx",paramters,function(json){
				if(json.result == 0){
					if(json.result == 0){
						showInfoMessage({"msg":"操作成功"});
						closeWindow();
						$("#listGrid").datagrid("reload");
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
	<input type="hidden"  name="userid" value="${userid}"/>
		<table style="width:100%;" width="320">
			<tr>
				<th>密码：</th>
				<td><input type="password" name="password" id="txtPassword" size="32" class="easyui-validatebox" validType="maxLength[3]"  data-options="required:true"/></td>
			</tr>	
			<tr>
				<th>密码确认：</th>
				<td><input type="password" name="repassword" size="32" class="easyui-validatebox" validType="equalTo['#txtPassword']"  data-options="required:true"/></td>
			</tr>				
		</table>
		
		<div align="center" style="margin-top:5px;margin-bottom:5px;">
			<div class="buttons" style="align:center;">
					    <button  class="positive" type="button" onclick="save_add()">
					        	保存
					    </button>
					    <button  class="positive" type="button" onclick="closeWindow()">
					        	关闭
					    </button>				    
			</div>	
		</div>
</form>
