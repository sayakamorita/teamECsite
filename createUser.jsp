<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CreateUser画面</title>
<link rel="stylesheet" type="text/css" href="./css/style.css"/>
<link rel="stylesheet" href="./css/form.css">

</head>
<body>
<!-- 【includeアクション】指定したJSPページ（header.jsp)をcreateUser.jspにインクルードしている。 -->
<jsp:include page="header.jsp"/>
<div id="contents">
	<h1>ユーザー情報入力画面</h1>

<!-- 	formタグを記載する前に、それぞれのエラー動作確認を行う -->
<s:if test="familyNameErrorMessageList!=null && familyNameErrorMessageList.size()>0">
	<div class="error_box">
<!-- 【iterator タグ】value属性に指定されたオブジェクトに格納されたオブジェクトの個数だけ繰りかえしを行う。 -->
<!-- iteratorタグ内では、繰り返し中のオブジェクトのプロパティを参照可能。 -->
			<s:iterator value="familyNameErrorMessageList"><s:property/><br></s:iterator>
		</div>
</s:if>
<s:if test="firstNameErrorMessageList!=null && firstNameErrorMessageList.size()>0">
	<div class="error_box">
	<s:iterator value="firstNameErrorMessageList"><s:property /><br></s:iterator>
	</div>
</s:if>
<s:if test="familyNameKanaErrorMessageList!=null && familyNameKanaErrorMessageList.size()>0">
	<div class="error_box">
	<s:iterator value="familyNameKanaErrorMessageList"><s:property /><br></s:iterator>
	</div>
</s:if>
<s:if test="firstNameKanaErrorMessageList!=null && firstNameKanaErrorMessageList.size()>0">
	<div class="error_box">
	<s:iterator value="firstNameKanaErrorMessageList"><s:property /><br></s:iterator>
	</div>
</s:if>
<s:if test="emailErrorMessageList!=null && emailErrorMessageList.size()>0">
	<div class="error_box">
	<s:iterator value="emailErrorMessageList"><s:property /><br></s:iterator>
	</div>
</s:if>
<s:if test="userIdErrorMessageList!=null && userIdErrorMessageList.size()>0">
	<div class="error_box">
	<s:iterator value="userIdErrorMessageList"><s:property /><br></s:iterator>
	</div>
</s:if>
<s:if test="passwordErrorMessageList!=null && passwordErrorMessageList.size()>0">
	<div class="error_box">
	<s:iterator value="passwordErrorMessageList"><s:property /><br></s:iterator>
	</div>
</s:if>
<s:if test="isExistsUserErrorMessage!=null && !isExistsUserErrorMessage.isEmpty()">
	<div class="error_box">
	<s:iterator value="isExistsUserErrorMessage"><s:property /><br></s:iterator>
	</div>
</s:if>

<s:form action="CreateUserConfirmAction">
	<table class="T_box">
		<tr>
<!-- scope = "row"を用いると、見出しに対応するtd(テーブルデータ)を行方向（右方向）に入力することができる -->
<%-- 以下のsタグ内での【%{session.familyName】は【<s:property value="firstName"/>】と同様の意味を表す。 --%>
<!-- （sタグ内ではsタグが使えないため、上記のような記載方法になる。 -->
<!-- %{session.familyName}を用いると、 -->
<!-- ・ユーザー情報入力画面でエラーが発生した場合 -->
<!-- ・ユーザーがすでに存在する場合 -->
<!-- ・ユーザー情報入力確認画面で戻るボタンを押下した場合 -->
<!-- にsessionに保持された値をユーザー情報入力画面で表示することができる -->

			<th scope="row">姓</th>
			<td><s:textfield name="familyName" value="%{#session.familyName}" placeholder="姓" class="txt"/></td>
		</tr>
		<tr>
			<th scope="row">名</th>
			<!-- 	【textfieldタグ】name,value属性はinputタグと同様の意味をもつ。 -->
<!-- 	label属性に指定された文字列がフィールドの左側に表示される。 -->
			<td><s:textfield name="firstName" value="%{#session.firstName}" placeholder="名" class="txt"/></td>
		</tr>
		<tr>
			<th scope="row">姓ふりがな</th>
			<td><s:textfield name="familyNameKana" value="%{#session.familyNameKana}" placeholder="姓ふりがな" class="txt"/></td>
		</tr>
		<tr>
			<th scope="row">名ふりがな</th>
			<td><s:textfield name="firstNameKana" value="%{#session.firstNameKana}" placeholder="名ふりがな" class="txt"/></td>
		</tr>
		<tr>
			<th scope="row">性別</th>

<!-- 		sタグのlist属性で、ラジオボタンの選択肢を作ることができる。 -->
<!-- 		→createUserActionで作成されたsexListの2項目（男性/女性)を表示している。 -->
			<td><s:radio name="sex" list ="%{#session.sexList}" value="%{#session.sex}" placeholder="性別"/></td>
		</tr>
		<tr>
			<th scope="row">メールアドレス</th>
			<td><s:textfield name="email" value="%{#session.email}" placeholder="メールアドレス" class="txt"/></td>
		</tr>
		<tr>
			<th scope="row">ユーザーID</th>
			<td><s:textfield name="userId" value="%{#session.userIdForCreateUser}" placeholder="ユーザーID" class="txt"/></td>
		</tr>
		<tr>
			<th scope="row">パスワード</th>
<!-- 	パスワードは画面遷移の際常に未入力なので、value値にはsessionを記載せず空の状態にしておく -->
			<td><s:password name="password" value="" placeholder="パスワード" class="txt"/></td>
		</tr>
		</table>
<!-- 		確認ボタン押下→createUserConfirmActionで入力チェックとユーザーIDの存在チェックを行う -->
		<div class="submit_btn_box">
			<s:submit value="確認" class="btn"/>
		</div>
	</s:form>

</div>
</body>
</html>