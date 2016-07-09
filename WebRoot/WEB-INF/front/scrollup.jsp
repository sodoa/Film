<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="h" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link type="text/css" href="/jslib/scrollup/css/themes/image.css" rel="stylesheet" />
<script type="text/javascript" src="/jslib/scrollup/jquery.scrollUp.min.js"></script>
	
<script type="text/javascript">
		
		$(function(){
			
            $.scrollUp({
                animation: 'fade',
                activeOverlay: '#00FFFF',
                scrollImg: {
                    active: true,
                    type: 'background',
                    src: '/jslib/scrollup/img/top.png'
                }
            });
			
		});
		
	
	</script>