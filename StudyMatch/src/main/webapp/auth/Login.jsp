<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
<!--
document.forms["LoginFrm"].addEventListener("submit", function(event) {
    event.preventDefault();
    var form = event.target;
    var formData = new FormData(form);
    
    fetch(form.action, {
        method: 'POST',
        body: formData
    })
    .then(resp => resp.text())
    .then(result => {
        if (result.trim() === 'success') {
            var dto = ${sessionScope.user}; // 세션에서 회원 정보 가져오기
            alert(dto.nick + " (" + dto.id + ") 회원님 반갑습니다! (´▽`ʃ♡ƪ)");
            window.location.href = "${pageContext.request.contextPath}/board/MainPage.jsp";
        } else {
            alert("일치하는 회원 정보를 찾지 못했어요.（；´д｀）ゞ");
            location.reload(); // 실패 시 페이지 새로고침
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
});
-->
</script>
</head>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
<!-- css 가져오기 -->
<link href="FormLogin.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<body>
	<div class="loginbox">
		<h2>로그인</h2>
		<form name="LoginFrm" action="../servlet/LoginAuth.do" method="post">
		
			<!--작성하지 않아도 문제는 없음-->
			<fieldset>
				<legend>로그인 구역</legend>
				<label for="loginid">아이디(E-mail)</label> <input type="text"
					id="loginid" name="id" placeholder="아이디(E-mail)을 입력해 주세요">
				<label for="loginpw">비밀번호</label> <input type="password"
					id="loginpw" name="pass" placeholder="비밀번호를 입력해 주세요">
				<ul>
					<li><a href="#">아이디/비밀번호찾기</a></li>
					<li><a href="#">회원가입</a></li>
				</ul>
				<!--데이터를 서버로 전송-->
				<button type="submit">로그인</button>
			</fieldset>
		</form>
	</div>
</body>
</html>