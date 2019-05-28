<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- cssのURLは相対パスで記述している -->
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/form.css">
<title>CreateUserConfirm画面</title>
</head>
<body>
<script type="text/javascript" src="./js/createUser.js"></script>
<jsp:include page="header.jsp"/>
<div id="contents">
	<h1>ユーザー情報入力確認画面</h1>
<!-- 	createUserConfirmActionで入力値チェック、ユーザー存在チェックを行い、エラーが起きなかった場合にこのjspに遷移してくる。 -->
<!-- 	この画面のあとはcreateUserCompleteActionを経由して -->
<!-- 	ユーザー情報入力完了画面(createUserComplete.jsp)へ遷移する、 -->
	<s:form id="createUserForm">
		<table class="T_box">
			<tr>
<!-- 			labelは、表示したいデータの横に表示するもの。　ex「姓 山田」 -->
				<th scope="row"><s:label value="姓"/></th>
<!-- 		以下のfamilyNameは、createUserConfirmActionのsession key:familyNameより抽出している -->
				<td><s:property value="familyName"/></td>
			</tr>
			<tr>
				<th scope="row"><s:label value="名"/></th>
				<td><s:property value="firstName"/></td>
			</tr>
			<tr>
				<th scope="row"><s:label value="姓ふりがな"/></th>
				<td><s:property value="familyNameKana"/></td>
			</tr>
			<tr>
				<th scope="row"><s:label value="名ふりがな"/></th>
				<td><s:property value="firstNameKana"/></td>
			</tr>
			<tr>
				<th scope="row"><s:label value="性別"/></th>
				<td><s:property value="sex"/></td>
			</tr>
			<tr>
				<th scope="row"><s:label value="メールアドレス"/></th>
				<td><s:property value="email"/></td>
			</tr>
			<tr>
				<th scope="row"><s:label value="ユーザーID"/></th>
				<td><s:property value="userId"/></td>
			</tr>
			<tr>
				<th scope="row"><s:label value="パスワード"/></th>
				<td><s:property value="password"/></td>
			</tr>
		</table>
		<div class="submit_btn_box">
<!-- 		onclick:javascriptのイベント？ -->
			<s:submit value="登録" class="btn" onclick="goCreateUserCompleteAction()"/>
		</div>
		<div class="submit_btn_box">
			<s:submit value="戻る" class="btn" onclick="goCreateUserAction()"/>
		</div>
<!-- 		hiddenフラグ→画面上には表示されない隠された部品を作成できる。 -->
<!-- 			name,value属性はinputタグと同様の意味をもつ。 -->
<!-- 			value属性が省略された場合は、nameに対応するActionクラスのプロパティが利用される -->
		<s:hidden id="backFlag" name="backFlag" value=""/>

	</s:form>

</div>
</body>
</html>