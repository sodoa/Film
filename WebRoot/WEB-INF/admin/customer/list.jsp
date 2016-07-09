<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html><html lang="en"><head>      <jsp:include page="../head.jsp"></jsp:include><script type="text/javascript" src="/jslib/uiadmin/lib/My97DatePicker/WdatePicker.js"></script><script type="text/javascript" src="/jslib/uiadmin/lib/datatables/1.10.0/jquery.dataTables.min.js"></script></head><body><nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>用户管理<span class="c-gray en">&gt;</span>用户列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav><div class="pd-20">	<div class="text-c"> 日期范围：		<input type="text" onfocus="WdatePicker()" id="datemin" class="input-text Wdate" style="width:120px;">		-		<input type="text" onfocus="WdatePicker()" id="datemax" class="input-text Wdate" style="width:120px;">		<input type="text" class="input-text" style="width:250px" placeholder="输入会员电话号码" id="user_phone" name="">		<button type="submit" class="btn btn-success radius" id="btn_search" name="" ><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>	</div>	<div class="mt-20">	<table class="table table-border table-bordered table-hover table-bg table-sort">		<thead>			<tr class="text-c">                                            <th>用户ID</th>                                            <th>用户名称</th>                                            <th>帐号</th>                                            <th>微信号</th>                                            <th>注册时间</th>                                            <th>到期时间</th>                                            <th>注册类型</th>                                            <th>状态</th>                                            <th>操作</th>                                        </tr>		</thead>		<tbody>	                                    			</tbody>	</table>	</div></div><script type="text/javascript">var datatable = null;$(function(){	datatable = $('.table-sort').dataTable({		"bStateSave": true,//状态保存        responsive: true,        "searching": false,        "processing": true,        "serverSide": true,        "ordering": false,        "bLengthChange": false,        "info":     false,        "paging":   true,        "ajax": {        	"url":"/admin/customer-list-page.jspx",        	"type":"post",        	"data":function(d){        		getParamter(d);       		 }		},        "columnDefs": [                       {                           "render": function ( data, type, row ) {                        	   var array = new Array();                        	   if(row[7] == 1){                        		  array.push('<a style="text-decoration:none" onClick="member_stop(this,'+row[0]+')" href="javascript:;" title="禁用"><i class="Hui-iconfont">&#xe6e1;</i></a>');                        	   }                        	   else{                        		   array.push('<a style="text-decoration:none" onClick="member_start(this,'+row[0]+')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');                        	   }                        	                           	   array.push('<a title="删除" href="javascript:;" onclick="member_del(this,'+row[0]+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>');                        	   array.push('<a style="text-decoration:none" class="ml-5" onClick=change_password('+row[0]+') href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a>');                               return array.join("");                           },                           "targets": 8                       },                       {                            "render": function ( data, type, row ) {                        	                           	   if(data == 1){                        		   return '<span class="td-status"><span class="label label-success radius">已启用</span></span>';                        	   }                        	   else{                        		   return '<span class="td-status"><span class="label label-defaunt radius">已停用</span></span>';                        	   }                           },                           "targets":7                   	                       	                          },                       {                            "render": function ( data, type, row ) {                        	                           	   if(data == 1){                        		   return '微信用户';                        	   }                        	   else if(data == 2){                        		   return '网站用户';                        	   }                           },                           "targets": 6                	                       	                          },                       {                            "render": function ( data, type, row ) {                  				if(data!=null){                  					return new Date(data).formatShort();                  				}                           },                           "targets": 5                	                       	                          },                       {                            "render": function ( data, type, row ) {                  				if(data!=null){                  					return new Date(data).formatShort();                  				}                           },                           "targets": 4                	                       	                          }                   ]	});			$("#btn_search").click(function(){		reload();	});	});	function reload(){	 datatable.fnClearTable(0);       datatable.fnDraw(); //重新加载数据 }function getParamter(data){	var startdate = $("#datemin").val();	var enddate = $("#datemax").val();	var user_phone = $("#user_phone").val();	data['startdate']=startdate;	data['enddate']=enddate;	data['user_phone'] = user_phone;}/*用户-添加*/function member_add(title,url,w,h){	layer_show(title,url,w,h);}/*用户-查看*/function member_show(id){	layer_show('用户查看',"customer-info.jspx?id="+id,600,450);}/*用户-停用*/function member_stop(obj,id){	layer.confirm('确认要停用吗？',function(index){		var _data = {"id": id,"state":0};	      $.ajax({type:"POST",	             url:"customer-state.jspx?t="+new Date().getTime(),	             data:_data,	             dataType:"json",	             success:function(data){	            	if(data.result ==0){	            		layer.msg('操作成功!',{icon:1,time:1000});	            		reload();	            	}	            	else{	            		layer.msg(data.message,{icon:1,time:1000});	            	}	             }			});	});}/*用户-启用*/function member_start(id){	layer.confirm('确认要启用吗？',function(id,state){		  var _data = {"id": id,"state":1};	      $.ajax({type:"POST",	             url:"customer-state.jspx?t="+new Date().getTime(),	             data:_data,	             dataType:"json",	             success:function(data){	            	if(data.result ==0){	            		layer.msg('操作成功!',{icon:1,time:1000});	            		reload();	            	}	            	else{	            		layer.msg(data.message,{icon:1,time:1000});	            	}	             }			});	});}/*用户-编辑*/function member_edit(title,url,id,w,h){	layer_show(title,url,w,h);}/*密码-修改*/function change_password(id){	layer_show('修改密码','customer-password.jspx?id='+id,600,270);	}/*用户-删除*/function member_del(obj,id){	layer.confirm('确认要删除吗？',function(index){	  var _data = {"id": id};      $.ajax({type:"POST",             url:"customer-delete.jspx?t="+new Date().getTime(),             data:_data,             dataType:"json",             success:function(data){            	if(data.result ==0){            		layer.msg('已删除!',{icon:1,time:1000});            		reload();            	}            	else{            		layer.msg(data.message,{icon:1,time:1000});            	}             }		});	});}</script></body></html>