<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
function validateForm(form){
	if(form.id.value == ""){
		alert("아이디를 입력해 주세요.");
		return false;
	}else if(form.pass.value == ""){
		alert("비밀번호를 입력해 주세요.");
	}
}
</script>
</head>
<!-- css 가져오기 -->
<link href="FormLogin.css" rel="stylesheet" type="text/css" />
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<body id="loginbody">
	<div class="loginbox">
		<h2>로 그 인</h2>
		<form name="LoginFrm" action="../auth/LoginAuth.do" method="post">
		
			<!--작성하지 않아도 문제는 없음-->
			<fieldset>
				<legend>로그인 구역</legend>
				<label for="loginid">아이디(E-mail)</label> <input type="text"
					id="loginid" name="id" placeholder="아이디(E-mail)을 입력해 주세요">
				<label for="loginpw">비밀번호</label> <input type="password"
					id="loginpw" name="pass" placeholder="비밀번호를 입력해 주세요">
				<ul>
					<li><a href="../auth/IdPwSearch.jsp">아이디/비밀번호찾기</a></li>
					<li><a href="../auth/Regist.jsp">회원가입</a></li>
				</ul>
				<!--데이터를 서버로 전송-->
				<button type="submit">로그인</button>
			</fieldset>
		</form>
	</div>
</body>
</html>