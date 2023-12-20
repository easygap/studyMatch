<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
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

				<ul>
					<li onclick="kakaoLogin();"><a href="javascript:void(0)">
							<img src="../img/kakao_login.png" alt="카카오 로그인">
					</a></li>
				</ul>
				<!-- 카카오 스크립트 -->
				<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
				<script>
					Kakao.init('1679ead944a73eda9c0a535810405446');
					console.log(Kakao.isInitialized()); // sdk 초기화 여부 판단
					//카카오 로그인
					function kakaoLogin() {
						// 로그인 요청 - 회원가입
						Kakao.Auth.login({
							// 회원 정보 가져오기
							success : function(resp) {
								Kakao.API.request({
									url : '/v2/user/me',
									success : function(resp) { // 호출 실패
										console.log(resp);
									var nick = resp.nickname;
									scope : 'profile_nickname';
									var id = resp.id;
									scope : 'account_email';
									var birthyear = resp.birthyear;
									scope : 'birthyear';
									var birthday = resp.birthday;
									scope : 'birthday';
									var birth = birthyear + birthday;
									var phone = resp.phone;
									scope : 'phone_number';
									var address = resp.address;
									scope : 'shipping_address';
									var url = '../회원가입컨트롤러URL?nick=' + encodeURIComponent(nick) + '&id=' + encodeURIComponent(id)
									+ '&birth=' + encodeURIComponent(birth) + '&phone=' + encodeURIComponent(phone) + '&address=' + encodeURIComponent(address);
									alert(nick + "님 카카오 로그인하셨습니다. (●'◡'●)");
									location.href="../board/MainPage.jsp";
									},
									fail : function(error) {
										console.log(error)
										alert("로그인 정보 조회에 실패했습니다. 정보를 다시 확인해 주세요.);
										location.href="../auth/Login.jsp";
									},
								})
							},
							fail : function(error) {
								console.log(error)
								alert("로그인에 실패했습니다. 다시 로그인해 주세요.);
								location.href="../auth/Login.jsp";
							},
						})
					}
				</script>
			</fieldset>
		</form>
	</div>
</body>
</html>