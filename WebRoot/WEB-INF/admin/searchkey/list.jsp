<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %><!DOCTYPE html><html lang="en"><head>      <jsp:include page="../head.jsp"></jsp:include><script type="text/javascript" src="${pageContext.request.contextPath}/jslib/uiadmin/lib/My97DatePicker/WdatePicker.js"></script><script type="text/javascript" src="${pageContext.request.contextPath}/jslib/uiadmin/lib/datatables/1.10.0/jquery.dataTables.min.js"></script></head><body><nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>用户管理<span class="c-gray en">&gt;</span>搜索关键字列表 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav><div class="pd-20">	<div class="text-c"> 电影关键字：		<input type="text" class="input-text" style="width:250px" placeholder="输入电影关键字" id="word" name="word">		<button type="submit" class="btn btn-success radius" id="btn_search" name="" ><i class="Hui-iconfont">&#xe665;</i> 搜索</button>	</div>	<div class="mt-20">	<table class="table table-border table-bordered table-hover table-bg table-sort">		<thead>			<tr class="text-c">                 <th>关键字ID</th>                 <th>关键字</th>                 <th>搜索次数</th>                 <th>创建时间</th>                 <th>更新时间</th>                 <th>操作</th>             </tr>		</thead>		<tbody>	                                    			</tbody>	</table>	</div></div><script type="text/javascript">var datatable = null;$(function(){	datatable = $('.table-sort').dataTable({		"bStateSave": true,//状态保存         responsive: true,        "searching": false,        "processing": true,        "serverSide": true,        "ordering": false,        "bLengthChange": false,        "info":     false,        "paging":   true,        "ajax": {        	"url":"${pageContext.request.contextPath}/admin/searchkey/page.jspx",        	"type":"post",        	"data":function(d){        		getParamter(d);       		 }		},        "columnDefs": [                       {                           "render": function ( data, type, row ) {                        	   var array = new Array();                        	   array.push('<a title="删除" onclick="movie_delete(this,'+row[0]+')" href="javascript:;"  class="ml-5" style="text-decoration:none">删除</a>');                               return array.join("");                           },                           "targets": 5                       } ,                       {                       	   "render": function ( data, type, row ) {                   				if(data!=null){                   					return new Date(data).formatShort();                   				}                           },                           "targets": 4                       } ,                       {                           "render": function ( data, type, row ) {                        	   if(data!=null){                  					return new Date(data).formatShort();                  				}                           },                           "targets": 3                       }                 ]	});			$("#btn_search").click(function(){		reload();	});	});	function reload(){	 datatable.fnClearTable(0);       datatable.fnDraw(); //重新加载数据 }function getParamter(data){	var word = $("#word").val();	data['word']=word;}/*删除*/function movie_delete(obj,id){	layer.confirm('确认要删除吗？',function(index){	  var _data = {"searchkeyId": id};      $.ajax({type:"POST",             url:"${pageContext.request.contextPath}/admin/searchkey/delete.jspx?t="+new Date().getTime(),             data:_data,             dataType:"json",             success:function(data){            	if(data.result ==0){            		layer.msg('已删除!',{icon:1,time:1000});            		reload();            	}            	else{            		layer.msg(data.message,{icon:1,time:1000});            	}             }		});	});}</script></body></html>