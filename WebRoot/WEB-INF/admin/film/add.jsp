<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html><html lang="en"><head>   <jsp:include page="../head.jsp"></jsp:include><link href="${pageContext.request.contextPath}/jslib/uiadmin/lib/icheck/icheck.css" rel="stylesheet" type="text/css" /><link href="${pageContext.request.contextPath}/jslib/uiadmin/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" /><link href="${pageContext.request.contextPath}/jslib/uiadmin/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" /><script type="text/javascript" src="${pageContext.request.contextPath}/jslib/uiadmin/lib/My97DatePicker/WdatePicker.js"></script> <script type="text/javascript" src="${pageContext.request.contextPath}/jslib/uiadmin/lib/icheck/jquery.icheck.min.js"></script> <script type="text/javascript" src="${pageContext.request.contextPath}/jslib/uiadmin/lib/Validform/5.3.2/Validform.min.js"></script> <script type="text/javascript" src="${pageContext.request.contextPath}/jslib/uiadmin/lib/webuploader/0.1.5/webuploader.min.js"></script> <script type="text/javascript" src="${pageContext.request.contextPath}/jslib/uiadmin/lib/ueditor/1.4.3/ueditor.config.js"></script><script type="text/javascript" src="${pageContext.request.contextPath}/jslib/uiadmin/lib/ueditor/1.4.3/ueditor.all.min.js"> </script><script type="text/javascript" src="${pageContext.request.contextPath}/jslib/uiadmin/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script></head><body><div class="pd-20">	<form action="${pageContext.request.contextPath}/admin/film/add-save.jspx" method="post" class="form form-horizontal" id="form-submit">		<input type="hidden" name="time" value="${time}"  id="time"/>		<div class="row cl">			<label class="form-label col-2"><span class="c-red">*</span>电影名称：</label>			<div class="formControls col-4">				<input type="text" class="input-text" value="" datatype="*2-100" placeholder="" id="name" name="name">			</div>			<label class="form-label col-2">发行时间：</label>			<div class="formControls col-4">				<input type="text" onfocus="WdatePicker()" id="publish" name="publish" class="input-text Wdate" >			</div>		</div>		<div class="row cl">			<label class="form-label col-2"><span class="c-red">*</span>类型：</label>			<div class="formControls col-4"> <span class="select-box">				<select name="type" class="select" datatype="*1-100">						<option value="">请选择</option>						<sp:dict_option_tag type="type" />				</select>				</span> 			</div>			<label class="form-label col-2"><span class="c-red">*</span>国家：</label>			<div class="formControls col-4"> <span class="select-box">				<select name="country" class="select" datatype="*1-100">						<option value="">请选择</option>						<sp:dict_option_tag type="country" />				</select>				</span> 			</div>		</div>		<div class="row cl">			<label class="form-label col-2"><span class="c-red">*</span>电影地址：</label>			<div class="formControls col-4">				<input type="text" class="input-text" datatype="*1-500" ignore="ignore" placeholder="" id="url" name="url">			</div>			<label class="form-label col-2"><span class="c-red">*</span>视频格式：</label>			<div class="formControls col-4"> <span class="select-box">				<select name="format" class="select" datatype="*1-100">						<option value="">请选择</option>						<sp:dict_option_tag type="format" />				</select>				</span> 			</div>		</div>		<div class="row cl">			<label class="form-label col-2">导演：</label>			<div class="formControls col-4">				<input type="text" name="director" id="director" placeholder="" value="" class="input-text">			</div>			<label class="form-label col-2">主演：</label>			<div class="formControls col-4"> 				<input type="text" name="actor" id="actor" placeholder="" value="" class="input-text">			</div>		</div>		<div class="row cl">			<label class="form-label col-2">缩略图：</label>			<div class="formControls col-10">				<div class="uploader-thum-container">					<div id="uploader-demo" class="wu-example">					    <div id="fileList" class="uploader-list">					    </div>					    <div id="filePicker" class="webuploader-container"><div class="webuploader-pick">选择图片</div></div>					 </div>					</div>				</div>		</div>		<%-- <div class="row cl">			<label class="form-label col-2"><span class="c-red">*</span>播放器类型：</label>			<div class="formControls col-4"> <span class="select-box">				<select name="player" class="select" datatype="*1-100">						<option value="">请选择</option>						<sp:dict_option_tag type="type" />				</select>				</span> 			</div>			<label class="form-label col-2">播放器地址：</label>			<div class="formControls col-10">				<input type="text" name="playerUrl" id="playerUrl" placeholder="" value="" class="input-text">			</div>		</div> --%>		<div class="row cl">			<label class="form-label col-2">电影简述：</label>			<div class="formControls col-10">				<textarea style="display: none;" id="resume" name="resume"></textarea>			</div>			<div class="formControls col-10"> 				<script id="editor" type="text/plain" style="width:100%;height:400px;"></script> 			</div>		</div>				<div class="row cl">			<div class="col-10 col-offset-2">				<button class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存</button>				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>			</div>		</div>	</form></div><script>$(function(){	$("#form-submit").Validform({		tiptype:3,		callback:function(form){						$("#resume").val(UE.getEditor('editor').getContent());						form[0].submit();		}	});		var ue = UE.getEditor('editor');		   });jQuery(function() {    var $ = jQuery,        $list = $('#fileList'),        // 优化retina, 在retina下这个值是2        ratio = window.devicePixelRatio || 1,        // 缩略图大小        thumbnailWidth = 100 * ratio,        thumbnailHeight = 100 * ratio,        // Web Uploader实例        uploader;    // 初始化Web Uploader    uploader = WebUploader.create({        // 自动上传。        auto: true,        // swf文件路径        swf: '${pageContext.request.contextPath}/jslib/uiadmin/lib/webuploader/0.1.5/Uploader.swf',        // 文件接收服务端。        server: '${pageContext.request.contextPath}/admin/images/upload_thumd.jspx?time=${time}',        // 选择文件的按钮。可选。        // 内部根据当前运行是创建，可能是input元素，也可能是flash.        pick: '#filePicker',        // 只允许选择文件，可选。        accept: {            title: 'Images',            extensions: 'gif,jpg,jpeg,bmp,png',            mimeTypes: 'image/*'        }    });    // 当有文件添加进来的时候    uploader.on( 'fileQueued', function( file ) {        var $li = $(                '<div id="' + file.id + '" class="file-item thumbnail">' +                    '<img>' +                    '<div class="info">' + file.name + '</div>' +                '</div>'                ),            $img = $li.find('img');        $list.append( $li );        // 创建缩略图        uploader.makeThumb( file, function( error, src ) {            if ( error ) {                $img.replaceWith('<span>不能预览</span>');                return;            }            $img.attr( 'src', src );        }, thumbnailWidth, thumbnailHeight );    });    // 文件上传过程中创建进度条实时显示。    uploader.on( 'uploadProgress', function( file, percentage ) {        var $li = $( '#'+file.id ),            $percent = $li.find('.progress span');        // 避免重复创建        if ( !$percent.length ) {            $percent = $('<p class="progress"><span></span></p>')                    .appendTo( $li )                    .find('span');        }        $percent.css( 'width', percentage * 100 + '%' );    });    // 文件上传成功，给item添加成功class, 用样式标记上传成功。    uploader.on( 'uploadSuccess', function( file ) {        $( '#'+file.id ).addClass('upload-state-done');    });    // 文件上传失败，现实上传出错。    uploader.on( 'uploadError', function( file ) {        var $li = $( '#'+file.id ),            $error = $li.find('div.error');        // 避免重复创建        if ( !$error.length ) {            $error = $('<div class="error"></div>').appendTo( $li );        }        $error.text('上传失败');    });    // 完成上传完了，成功或者失败，先删除进度条。    uploader.on( 'uploadComplete', function( file ) {        $( '#'+file.id ).find('.progress').remove();    });});</script></body></html>