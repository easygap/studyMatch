<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네비게이션 바</title>
<!-- BootStrap css 사용 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<style>
.navbar {
	position: fixed;
	width: 100%;
	z-index: 5;
}

.navbar-nav {
	font-size: 15px;
}

#navbody, .navbar, .collapse {
	background-color: #F4EAE0 !important;
}

#sidebarToggle {
	background: #5174BE;
	opacity: 0.7;
	border-radius: 10px;
	color: #ffffff;
	font-weight: bold;
	font-size: 18px;
	width: 90px; height: 34px;
	border: none;
}
</style>
</head>
<body id="navbody">
	
	<!-- 네비게이션 바 -->
	
		<!-- Top navigation-->
		<nav
			class="navbar navbar-expand-lg navbar-light bg-light border-bottom">

			<div class="container-fluid">
				<button id="sidebarToggle">게 시 판</button>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ms-auto mt-2 mt-lg-0">
						<li class="nav-item active"><a class="nav-link" href="../board/Main.do">Home</a></li>
						<li class="nav-item active"><a class="nav-link" href="../map/StudyMap.do">StudyMap</a></li>
						<li class="nav-item"><a class="nav-link" href="../service/ServiceList.do">Service</a></li>
						<li class="nav-item"><a class="nav-link" href="../auth/Mypage.do">MyPage</a></li>
						<li class="nav-item"><a class="nav-link" href="../match/MatchHistory.do">Match History</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
							role="button" data-bs-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">계정</a>
							<div class="dropdown-menu dropdown-menu-end"
								aria-labelledby="navbarDropdown">
								
								<!-- session에 따른 조건문 -->
								<% 	
									System.out.println("세션 정보 : " + session.getAttribute("user"));
									if(session.getAttribute("user") == null) {
								%>
								<a class="dropdown-item" href="../auth/Login.jsp?#pop1">로그인</a><a
									class="dropdown-item" href="../auth/IdPwSearch.jsp">아이디 찾기</a> <a
									class="dropdown-item" href="../auth/IdPwSearch.jsp">비밀번호 찾기</a>
									<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="../auth/Regist.jsp">회원가입</a>
								<%
									} else {
								%>
								<a class="dropdown-item" href="../auth/Logout.jsp">로그아웃</a>
								<%
									}
								%>
								
							</div></li>
					</ul>
				</div>
			</div>
		</nav>
<!-- Jquery 사용 -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
	<!-- BootStrap javascript 사용 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>

</html>