<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 3秒後に移動、HTML -->
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/form.css">
<title>CreateUserComplete画面</title>
<script type="text/javascript">
		window.onload=function(){
 		var form = document.getElementById('createUserForm');
 		setTimeout(function(){form.submit()},3000);
 	}
</script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div id="contents">
		<h1>ユーザー情報入力完了画面</h1>
	<div class="complete_box">
		ユーザー情報入力が完了しました。
	</div>
	</div>
<!-- 	s:formのid属性→createUser.js(javascript)のidを参照 -->
	<s:form id="createUserForm" action="LoginAction">
		<s:hidden name="userId" value="%{#session.userIdForCreateUser}"/>
		<s:hidden name="password" value="%{#session.password}"/>
	</s:form>
</body>
</html>