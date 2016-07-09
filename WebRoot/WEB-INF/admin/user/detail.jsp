<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="http://mos.xinfan.com/" %>

<div class="easyui-tabs" >
    <div title="详细信息" style="padding:20px;">
		<table style="width:100%;align:left;" cellspacing="4" cellpadding="4" align="left" >
			<tr>
				<th>用户帐号：</th>
				<td align="left">${bean.userid}</td>
			
				<th>用户姓名：</th>
				<td>${bean.username}</td>
			</tr>	
			<tr>
				<th>密&nbsp;&nbsp;&nbsp;码：</th>
				<td>******</td>			
				<th>手机号码：</th>
				<td>${bean.telphone}</td>
			</tr>							
			<tr>
				<th>固定电话：</th>
				<td>${bean.phone}</td>	
				<th>邮&nbsp;&nbsp;&nbsp;箱：</th>
				<td>${bean.email}</td>	
			</tr>
			<tr>
				<th>帐号状态：</th>
				<td>
					<c:choose>
						<c:when test="${bean.state==1}">
							 <span style='color:green;font-weight: bold;'>正常</span>
						</c:when>
						<c:when test="${bean.state==2}">
							<span style='color:red;font-weight: bold;'>禁用</span>
						</c:when>
						<c:when test="${bean.state==3}">
							<span style='color:gray;font-weight: bold;'>已删除</span>
						</c:when>		
						<c:otherwise>
							<span>未知</span>
						</c:otherwise>				
					</c:choose>
				</td>	
				<th>权限角色：</th>
				<td>${bean.roleid}</td>	
			</tr>			
			<tr>
				<th>创建人：</th>
				<td>${bean.createuser}</td>	
				<th>创建时间：</th>
				<td><h:dateformat time="${bean.createtime}"/></td>	
			</tr>	
			<tr>
				<th>更新人：</th>
				<td>${bean.updateuser}</td>	
				<th>更新时间：</th>
				<td><h:dateformat time="${bean.updatetime}"/></td>	
			</tr>					
			<tr>
				<th>店面地址：</th>
				<td colspan="3">${bean.address}</td>			
			</tr>	
		</table>
    </div>
    <div title="登录历史">
    	<div id="loginListGrid"></div>
      	<script type="text/javascript">
			$("#loginListGrid").datagrid({
				 url:'/admin/loginext/data.jspx'
				 ,height:300
				,pagination:	true
				,fitColumns:	true
				,queryParams:	{"userid":"${bean.userid}"}
				,singleSelect:	true
				,striped:		true
				,autoRowHeight:	true
				,pageSize :7		
				,columns:[[  
					{field:'EXTID',title:'流水号',width:180,align:'center'}
					,{field:'USERID',title:'登录名',width:160,align:'center'}
					,{field:'LOGINIP',title:'登录ip',width:120,align:'center'}
					,{field:'LOGINTIME',title:'登录时间',width:180,align:'center'}
					,{field:'LOGOUTTIME',title:'退出时间',width:180,align:'center'}
				]]
			});      	
      	</script>
    </div>
    <div title="经办历史">
    	<div id="identifyListGrid"></div>
		<script type="text/javascript">
			$("#identifyListGrid").datagrid({
				 url:'identifylist.jspx'
				,pagination:	true
				,fitColumns:	true
				,singleSelect:	true
				,striped:		true
				,autoRowHeight:	true
				,pageSize :10	
				,columns:[[  
					{field:'IDENTIFYID',title:'流水号',width:160,align:'center'}
					,{field:'CREATETIME',title:'消费时间',width:180,align:'center'}
					,{field:'RELNAME',title:'姓名',width:100,align:'center'}
					,{field:'IDCARD',title:'身份证',width:160,align:'center'}
					,{field:'PRODUCTID',title:'产品ID',width:120,align:'center'}
					,{field:'PRODUCTCODE',title:'产品条码',width:120,align:'center'}
					,{field:'STATE',title:'消费状态',width:80,align:'center',formatter:formatIdentifyState}
					,{field:'IDENTIFYTYPE',title:'登记方式',width:80,align:'center',formatter:formatIdentifyType}
				]]
			});		
		</script>
    </div>    
</div>
