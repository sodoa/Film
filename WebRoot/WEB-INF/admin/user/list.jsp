<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="http://mos.xinfan.com/" %>

<jsp:include page="../fragment_top.jsp">
	<jsp:param name="menu_title" value="帐号管理" />
</jsp:include>

<script type="text/javascript">

	$(function(){
		
		$("#listGrid").datagrid({
			 url:'data.jspx'
			,pagination:	true
			,rownumbers:	true
			,fitColumns:	true
			,queryParams:	getQueryParamters()
			,singleSelect:	true
			,striped:		true
			,autoRowHeight:	true
			,pageSize :20			
			,columns:[[  
			    {field:'*',width:65,align:'center',checkbox:true}  
				,{field:'USERID',title:'用户ID',width:180,align:'center'}
				,{field:'USERNAME',title:'用户姓名',width:160,align:'center'}
				,{field:'TELPHONE',title:'手机号码',width:120,align:'center'}
				,{field:'PHONE',title:'固定电话',width:120,align:'center'}
				,{field:'CREATEUSER',title:'创建人',width:180,align:'center'}
				,{field:'CREATETIME',title:'创建时间',width:180,align:'center'}
				,{field:'STATE',title:'帐号状态',width:180,align:'center',formatter:formatUserState}
				,{field:'LASTTIME',title:'上次登录时间',width:180,align:'center'}
				,{field:'LASTIP',title:'上次登录IP',width:180,align:'center'}
			]]
		});
	});
	
	function showQuery(){
		showQueryWindow();
	}
	
	function submitQuery(){
		var paramters = getQueryParamters();
		$('#listGrid').datagrid('load',paramters);
		closeQueryWindow();
	}
	
	function getQueryParamters(){
		var paramters = $("#queryForm").serializeObject();
		return paramters;
	}
	
	function showUpdatePasswordWindow(){
		var row = $('#listGrid').datagrid('getSelected'); 
		if(row){
			showWindow({width:360,height:160,title:"修改",href:"./update_password.jspx?userid="+row["USERID"]});
		}else{
			showAlert({msg:"请选择要修改的数据行。"});
		}
	}
	
	function showAddWindow(){
		showWindow({width:680,height:300,title:"增加用户",href:"./add.jspx"});
	}
	
	function updateState(state){
		
		var row = $('#listGrid').datagrid('getSelected'); 
		if(row){
			showConfirmMessage({callback:function(r){
				if(r){
					var param = {"userid":row["USERID"],"state":state};
					$.post("update_state.jspx",param,function(json){
						if(json.result == 0){
							showInfoMessage({msg:"操作成功"});
							$('#listGrid').datagrid("reload");
						}
						else{
							showInfoMessage({msg:json.message});
						}
					},"json");
				}
			}});
		}else{
			showAlert({msg:"请选择要修改的数据行。"});
		}
	}
	
	function delUser(){
		var row = $('#listGrid').datagrid('getSelected'); 
		if(row){
			showConfirmMessage({callback:function(r){
				if(r){
					var param = {"userid":row["USERID"]};
					$.post("deluser.jspx",param,function(json){
						if(json.result == 0){
							showInfoMessage({msg:"操作成功"});
							$('#listGrid').datagrid("reload");
						}
						else{
							showInfoMessage({msg:"操作失败"});
						}
					},"json");
				}
			}});
		}else{
			showAlert({msg:"请选择要删除的数据行。"});
		}		
	}
	
	function showUpdateWindow(){
		var row = $('#listGrid').datagrid('getSelected'); 
		if(row){
			showWindow({width:680,height:300,title:"修改帐号信息",href:"./update.jspx?userid="+row["USERID"]});
		}else{
			showAlert({msg:"请选择要修改的数据行。"});
		}
	}
	
	function showDetailWindow(){
		var row = $('#listGrid').datagrid('getSelected'); 
		if(row){
			showWindow({width:680,height:380,title:"详细信息",href:"./detail.jspx?userid="+row["USERID"]});
		}else{
			showAlert({msg:"请选择要查看的数据行。"});
		}
	}
	
	//

</script>

<div class="toolbars">
	<a class="btn blue" id="" style="cursor: pointer;" onclick="showQuery()" ><i></i><span><i></i><span></span>查询</span></a>
	
	<h:auth funid="F03.01.01">
	<a class="btn blue" id="" style="cursor: pointer;" onclick="showAddWindow()" ><i></i><span><i></i><span></span>新增</span></a>
	 </h:auth>  
	 
	<h:auth funid="F03.01.02">
	<a class="btn blue" id="" style="cursor: pointer;" onclick="showUpdateWindow()" ><i></i><span><i></i><span></span>修改</span></a>
	</h:auth>
	
	 <h:auth funid="F03.01.03">
	<a class="btn blue" id="" style="cursor: pointer;" onclick="delUser()" ><i></i><span><i></i><span></span>删除</span></a>
	</h:auth>
	
	<h:auth funid="F03.01.04"> 
	<a class="btn blue" id="" style="cursor: pointer;" onclick="updateState(1)" ><i></i><span><i></i><span></span>启用</span></a>
	</h:auth>
	
	<h:auth funid="F03.01.05">
	<a class="btn blue" id="" style="cursor: pointer;" onclick="updateState(2)" ><i></i><span><i></i><span></span>禁用</span></a>
	</h:auth>
	
	<h:auth funid="F03.01.06">
	<a class="btn blue" id="" style="cursor: pointer;" onclick="showUpdatePasswordWindow()" ><i></i><span><i></i><span></span>修改密码</span></a>
	</h:auth>
	
	<h:auth funid="F03.01.07">
	<a class="btn blue" id="" style="cursor: pointer;" onclick="showDetailWindow()" ><i></i><span><i></i><span></span>详细</span></a>
	</h:auth>
	
	<div style="clear:both;"></div>
</div>

<div style="clear:both;padding-top:10px;"></div>

<div id="queryWindow" style="display:none;" class="query_window">
	<form id="queryForm">
		<table style="width:100%;align:center;" >
			<tr>
				<th>字典分类：</th>
				<td><input type="text" name="field" size="32"/></td>
				<th>字典名称：</th>
				<td><input type="text" name="dname" size="32" /></td>
			</tr>
		</table>
		
		<div align="center" style="margin-top:5px;margin-bottom:5px;">
			<div class="buttons" style="align:center;">
					    <button  class="positive" type="button" onclick="submitQuery()">
					        	查询
					    </button>
					    <button  class="positive" type="button" onclick="closeQueryWindow()">
					        	关闭
					    </button>				    
			</div>	
		</div>
	</form>
</div>

<div id="listGrid">
</div>


<jsp:include page="../fragment_bottom.jsp" />