<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://mos.xinfan.com/"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
   <jsp:include page="head.jsp"></jsp:include>
   <link href="${pageContext.request.contextPath}/jslib/uiadmin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
	<title>Admin Console</title>
</head>

<div class="header"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal" action="${pageContext.request.contextPath}/m_login.jspx" method="post">
    
         	 <c:if test="${not empty errormsg}">
         	  <div class="row cl">
         	  
         	  	<label class="form-label col-3"></label>
        				<div class="formControls col-8">
 						<div class="alert alert-danger">${errormsg}</div>
			      	</div>
			      </div>
         </c:if>
    
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-8">
          <input id="" name="username" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-8">
          <input id="" name="password" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <input name="" type="submit" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">Copyright</div>

	
	<script type="text/javascript">
		$("#login_btn").click(function(){
			$("#login_form").submit();
		});
	
	</script>
	
</body>

</html>
