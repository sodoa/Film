<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%><%@ taglib prefix="h" uri="http://mos.xinfan.com/"%><!DOCTYPE HTML><html><head>   <jsp:include page="head.jsp"></jsp:include><title>11Grand Admin Console</title></head><body>	<header class="Hui-header cl">		<a class="Hui-logo l" title="H-ui.admin v2.3" href="//admin/mindex.jspx">11Grand Admin Manage Console</a>		<a class="Hui-logo-m l" href="/admin/mindex.jspx#" title="Index">Index</a> <span			class="Hui-subtitle l">V1.0</span>		<ul class="Hui-userbar">			<li>超级管理员</li>			<li class="dropDown dropDown_hover"><a href="#"				class="dropDown_A">admin <i class="Hui-iconfont">&#xe6d5;</i></a>				<ul class="dropDown-menu radius box-shadow">					<li><a href="#">个人信息</a></li>					<li><a href="/" target="_blank">网站首页</a></li>					<li><a href="/m_logout.jspx">退出</a></li>				</ul></li>			<li id="Hui-msg"><a href="#" title="消息"><span					class="badge badge-danger">1</span><i class="Hui-iconfont"					style="font-size: 18px">&#xe68a;</i></a></li>			<li id="Hui-skin" class="dropDown right dropDown_hover"><a				href="javascript:;" title="换肤"><i class="Hui-iconfont"					style="font-size: 18px">&#xe62a;</i></a>				<ul class="dropDown-menu radius box-shadow">					<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>					<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>					<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>					<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>					<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>					<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>				</ul></li>		</ul>		<a aria-hidden="false" class="Hui-nav-toggle" href="#"></a>	</header>	<aside class="Hui-aside">		<input runat="server" id="divScrollValue" type="hidden" value="" />		<div class="menu_dropdown bk_2">			<dl id="menu-member">				<dt>					<i class="Hui-iconfont">&#xe616;</i>用户管理<i						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>				</dt>				<dd>					<ul>						<li><a _href="customer-list.jspx" href="javascript:void(0)">用户列表</a></li>						<li><a _href="customer-list.jspx" href="javascript:void(0)">收益列表</a></li>						<li><a _href="customer-list.jspx" href="javascript:void(0)">搜索关键字</a></li>					</ul>				</dd>			</dl>					<dl id="menu-product">				<dt>					<i class="Hui-iconfont">&#xe616;</i>电影管理<i						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>				</dt>				<dd>					<ul>						<li><a _href="film/list.jspx" href="javascript:void(0)">片源管理</a></li>						<li><a _href="producttype-list.jspx" href="javascript:void(0)">院线同步影片</a></li>						<li><a _href="goods-list.jspx" href="javascript:void(0)">网络事件影片</a></li>						<li><a _href="words-list.jspx" href="javascript:void(0)">激情伦理影片</a></li>						<li><a _href="words-list.jspx" href="javascript:void(0)">关键字影片</a></li>					</ul>				</dd>			</dl>						</div>	</aside>	<div class="dislpayArrow">		<a class="pngfix" href="javascript:void(0);"			onClick="displaynavbar(this)"></a>	</div>	<section class="Hui-article-box">		<div id="Hui-tabNav" class="Hui-tabNav">			<div class="Hui-tabNav-wp">				<ul id="min_title_list" class="acrossTab cl">					<li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em></li>				</ul>			</div>			<div class="Hui-tabNav-more btn-group">				<a id="js-tabNav-prev" class="btn radius btn-default size-S"					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a					id="js-tabNav-next" class="btn radius btn-default size-S"					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>			</div>		</div>		<div id="iframe_box" class="Hui-article">			<div class="show_iframe">				<div style="display: none" class="loading"></div>				<iframe scrolling="yes" frameborder="0" src="welcome.jspx"></iframe>			</div>		</div>	</section>		<script type="text/javascript">		/*资讯-添加*/		function article_add(title, url) {			var index = layer.open({				type : 2,				title : title,				content : url			});			layer.full(index);		}		/*图片-添加*/		function picture_add(title, url) {			var index = layer.open({				type : 2,				title : title,				content : url			});			layer.full(index);		}		/*产品-添加*/		function product_add(title, url) {			var index = layer.open({				type : 2,				title : title,				content : url			});			layer.full(index);		}		/*用户-添加*/		function member_add(title, url, w, h) {			layer_show(title, url, w, h);		}	</script></body></html>