<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
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
<!-- 코드 시작 -->
    <div class="d-flex" id="wrapper">
        <!-- 네비게이션 바 -->
        <jsp:include page="../layout/Main.jsp"></jsp:include>

        <!-- 페이지 컨텐츠 -->
        <div id="page-content-wrapper">
            <!-- 네비게이션 바 -->
            <jsp:include page="../layout/Navbar.jsp"></jsp:include>
            <div class="container-fluid">
                <br /> <br /> <br />

				<div class="loginbox">
					<h2>로 그 인</h2>
					<form name="LoginFrm" action="../auth/LoginAuth.do" method="post">

						<!--작성하지 않아도 문제는 없음-->
						<fieldset>
							<legend>로그인 구역</legend>
							<label for="loginid">아이디(E-mail)</label> 
							<input type="text" id="loginid" name="id" placeholder="아이디(E-mail)을 입력해 주세요">
							<label for="loginpw">비밀번호</label> 
							<input type="password" id="loginpw" name="pass" placeholder="비밀번호를 입력해 주세요">
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
							$.ajax({
							    type: 'POST',
							    url: '../auth/KakaoLogin.do',
							    contentType: 'application/json; charset=utf-8',
							    data: JSON.stringify({
							        access_token: authObj.access_token
							    }),
							    dataType: 'json',
							    success: function (response) {
							        console.log('서버 응답:', response);
							        alert('카카오 로그인 성공');
							    },
							    error: function (error) {
							        console.error('서버 요청 실패:', error);
							    }
							});
							window.Kakao.API.request({
				                url: '/v2/user/me',
				                success: function (res) {
				                    handleKakaoUserInfo(res.kakao_account);
				                }
				            });
						}
				    });
				function handleKakaoUserInfo(kakao_account) {
				    const nickname = kakao_account.profile.nickname;
				    const email = kakao_account.email;
				    const name = kakao_account.name;
				    const birthyear = kakao_account.birthyear;
				    const birthday = kakao_account.birthday;
				    const birth = birthyear + birthday;
				    const phone = kakao_account.phone_number;
				    const address = kakao_account.shipping_address;

				    console.log('로그인 계정:', email);
				    console.log('이름:', name);
				    console.log('닉네임: ', nickname);
				    console.log('생년월일: ', birth);
				    console.log('핸드폰:', phone);
				    console.log('주소:', address);
				}
				}
</script>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- BootStrap javascript 사용 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
<!-- 푸터 -->
<jsp:include page="../layout/Footer.jsp"></jsp:include>
</html>
