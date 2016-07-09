<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" uri="http://mos.xinfan.com/" %>

<jsp:include page="../fragment_top.jsp">
	<jsp:param name="menu_title" value="企业简介" />
</jsp:include>

<script type="text/javascript" src="/jslib/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
    selector: "textarea",
    language : 'zh_CN'
 });
</script>

<script type="text/javascript">

	function save_add(){
		if($("#updateForm").form("validate")){
			var paramters = $("#updateForm").serializeObject();
			paramters["content"] = getTinyMceContent();
			$.post("./save_update.jspx",paramters,function(json){
				if(json.result == 0){
					if(json.result == 0){
						showInfoMessage({"msg":"操作成功"});
						window.location.href= "/admin/info/list.jspx";
					}
					else{
						showInfoMessage({"msg":"操作失败"});
					}
				}
				
			},"json");
		}
	}

    function getTinyMceContent()
    {
        return  tinymce.get('content').getContent({format: 'raw'});
    }
</script>

<form id="updateForm">
	<input type="hidden" name="infoid" value="${bean.infoid}" />
		<table style="width:100%;" width="300" height="400" >
			<tr>
				<td>
					<textarea name="content" id="content" style="height:320px;">${bean.content}</textarea>
				</td>
			</tr>		
		</table>
		
		<div align="center" style="margin-top:5px;margin-bottom:5px;">
			<div class="buttons" style="align:center;">
					    <button  class="positive" type="button" onclick="save_add()">
					        	保存
					    </button>
			</div>	
		</div>
</form>


<jsp:include page="../fragment_bottom.jsp" />
