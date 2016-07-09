<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="am-avg-sm-2 am-thumbnails">

	<sp:top_goods_list_tag>
		<c:forEach items="${goods_list}" var="item">
			<li>
				<a href="/goods-${item.goods_id}.html" target="_self"><img class="am-thumbnail" src="${item.thumbnail_url}" /></a>
				<p>
					${item.goods_name}|${item.final_prices}/<b>${item.unit}</b>
				</p></li>
		</c:forEach>
	</sp:top_goods_list_tag>
</ul>