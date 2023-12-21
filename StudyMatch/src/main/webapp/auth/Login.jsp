<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script>
	function validateForm(form) {
		if (form.id.value == "") {
			alert("아이디를 입력해 주세요.");
			return false;
		} else if (form.pass.value == "") {
			alert("비밀번호를 입력해 주세요.");
		}
	}
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
				<!-- 카카오 스크립트 -->
				<a href="javascript:kakaoLogin();"><img
					src="../img/kakao_login.png"></a>
				<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
				<script>
				window.Kakao.init('bb8b6ca34d06454a9c045ccd122b2902');
				//카카오 로그인
				function kakaoLogin() {
					window.Kakao.Auth.login({
						scope: 'profile_nickname, account_email, name, birthyear, birthday, phone_number, shipping_address',
						success: function(authObj) {
							console.log(authObj);
							Kakao.Auth.setAccessToken(authObj.access_token);
							window.Kakao.API.request({
								url:'/v2/user/me',
								success: function(res) {
									const kakao_account = res.kakao_account;
				                    var nickname = kakao_account.profile.nickname;
				                    var email = kakao_account.email;
				                    var name = kakao_account.name;
				                    var birthyear = kakao_account.birthyear;
				                    var birthday = kakao_account.birthday;
				                    var birth = birthyear + birthday;
				                    var phone_number = kakao_account.phone_number;
				                    var shipping_address = kakao_account.shipping_address;
				                    
									alert('카카오 로그인 성공');
									window.Kakao.Auth.authorize({
									    redirectUri: 'http://localhost:8081/StudyMatch/board/MainPage.jsp',
									});
				                    console.log('로그인 계정:', email);
				                    console.log('이름:', name);
				                    console.log('닉네임: ', nickname);
				                    console.log('생년월일: ', birth);
				                    console.log('핸드폰:', phone_number);
				                    console.log('주소:', shipping_address);
								}
							});
						}
					});
				}
</script>
			</fieldset>
		</form>
	</div>
</body>
</html>