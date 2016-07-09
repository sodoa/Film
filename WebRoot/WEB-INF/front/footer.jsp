<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 底部 -->
<div class="bottom-fxied">
	<footer>
         <a href="/" <c:if test="${menu_hit=='1'}"> class="current" </c:if> ><span class="home"></span>首页</a>
         <a href="/g-s.html?theme=1" <c:if test="${menu_hit=='2'}"> class="current" </c:if>  ><span class="discount"></span>折扣多</a>
         <a href="/rk.html" <c:if test="${menu_hit=='3'}"> class="current" </c:if> ><span class="list"></span>分销榜</a>
         <a href="/center/my_center.html" <c:if test="${menu_hit=='4'}"> class="current" </c:if> ><span class="my"></span>我的</a>
    </footer>
</div>
