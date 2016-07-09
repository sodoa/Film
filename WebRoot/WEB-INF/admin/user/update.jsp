<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="http://mos.xinfan.com/" %>

<script type="text/javascript">
	function save_add(){
		if($("#addForm").form("validate")){
			var paramters = $("#addForm").serializeObject();
			$.post("./save_update.jspx",paramters,function(json){
				if(json.result == 0){
					if(json.result == 0){
						showInfoMessage({"msg":"操作成功"});
						closeWindow();
						$("#listGrid").datagrid("reload");
					}
					else{
						showInfoMessage({"msg":json.message});
					}
				}
				
			},"json");
		}
	}
</script>

<form id="addForm">
		<table style="width:100%;align:right;" cellspacing="4" cellpadding="4" align="right" >
			<tr>
				<th>用户帐号：</th>
				<td><input type="hidden" name="userid" value="${bean.userid}"/>${bean.userid}</td>
			
				<th>用户姓名：</th>
				<td><input type="text" name="username" value="${bean.username}" style="width:200px;"  class="easyui-validatebox" data-options="required:true,validType:['maxLength[10]','CHS']" /></td>
			
			</tr>	
			<tr>
				<th>密&nbsp;&nbsp;&nbsp;码：</th>
				<td><input type="password" name="password" value="${bean.password}" style="width:200px;" class="easyui-validatebox" data-options="required:true,validType:'maxLength[32]'"/></td>			
				<th>手机号码：</th>
				<td><input type="text" name="telphone" value="${bean.telphone}" style="width:200px;" class="easyui-validatebox" data-options="required:true,validType:'maxLength[32]'"/></td>
			</tr>							
			<tr>
				<th>固定电话：</th>
				<td><input type="text" name="phone" value="${bean.phone}" style="width:200px;"  class="easyui-validatebox"  data-options="validType:'maxLength[32]'" /></td>	
				<th>邮&nbsp;&nbsp;&nbsp;箱：</th>
				<td><input type="text" name="email" value="${bean.email}" style="width:200px;"  class="easyui-validatebox" data-options="validType:'maxLength[64]'"  /></td>	
			</tr>
			<tr>
				<th>店面地址：</th>
				<td colspan="3"><input type="text" value="${bean.address}" style="width:500px;" name="address"  class="easyui-validatebox" data-options="validType:'maxLength[256]'"  /></td>			
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
