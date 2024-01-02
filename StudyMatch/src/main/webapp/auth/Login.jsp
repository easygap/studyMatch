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
							<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
							<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<a href="javascript:void(0);" onclick="loginWithKakao();">
    <img src="../img/kakao_login.png">
</a>
<script>
      // 카카오 초기화
      Kakao.init('bb8b6ca34d06454a9c045ccd122b2902');
      Kakao.isInitialized();
      console.log(Kakao.isInitialized());
      
      // 카카오로그인 코드 확인
      function loginWithKakao() {
        Kakao.Auth.login({
          success: function (authObj) {
            console.log(authObj); //access토큰
            Kakao.Auth.setAccessToken(authObj.access_token); // 토큰 값 저장
            getInfo();
          },
          fail: function (err) {
            console.log(err);
          },
        });
      }

      // 액세스 토큰 발급 후 아래 함수를 호출시켜 사용자 정보 받기
      function getInfo() {
  Kakao.API.request({
    url: "/v2/user/me",
    success: function (res) {
      console.log(res);
      console.log("User Information:", res);
      sendUserInfoToServer(res);
    },
    fail: function (error) {
      alert("카카오 로그인 실패" + JSON.stringify(error));
    },
  });
}

// 서버로 사용자 정보를 보내는 함수 
function sendUserInfoToServer(userInfo) {
	console.log("Sending User Information to Server:", userInfo);
  $.ajax({
    type: 'POST',
    url: '../auth/KakaoLogin.do',
    contentType: 'application/json; charset=utf-8',
    data: JSON.stringify({
      id: userInfo.id,
      profile_nickname: userInfo.kakao_account.profile.nickname,
      name: userInfo.kakao_account.name,
      email: userInfo.kakao_account.email,
      phoneNumber: userInfo.kakao_account.phone_number,
      birthyear: userInfo.kakao_account.birthyear,
      birthday: userInfo.kakao_account.birthday
    }),
    success: function (response) { 
      console.log('서버 응답:', response);
      if (response.status === 'success') {
        alert('카카오 로그인 성공');
        window.location.href = 'http://localhost:8081/StudyMatch/board/Main.do';
      } else if (response.status === 'popup') {
    	  openPopup();
      } else {
        alert('카카오 로그인 실패: ' + response.message);
      }
    },
    error: function (error) {
      console.error('서버 요청 실패:', error);
      alert('서버 요청 실패');
    }
  });
}

function openPopup() {
	var url = "KakaoPopup.jsp";
	var name = "카카오 로그인 회원정보";
	var option = "width=500, height=500, left=500, location=no, top=100, scrollbars=no";
	window.open(url, name, option);
}
      // 로그아웃 기능
      function kakaoLogOut() {
        if (!Kakao.Auth.getAccessToken()) {
          alert("로그인을 먼저 하세요.");
          return;
        }
        Kakao.Auth.logout(function () {
          alert("로그아웃" + Kakao.Auth.getAccessToken());
        });
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