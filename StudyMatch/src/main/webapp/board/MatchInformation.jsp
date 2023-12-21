<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매칭 정보</title>
<link rel="stylesheet" href="../css/MainPage.css">
</head>
<body>
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

				<!-- import 끝 -->
				<!-- 본문 -->
				<div style="position: absolute; width: 1280px; height: 1300px">
					<div class="wrap"
						style="position: relative; width: 1280px; height: 100px;">
						<p class="matchfont" id="newmatch">매칭 정보</p>
						<div class="jumbotron" style="text-align: left;">
							<div id="NewMatch">
								<p class="lead">
									프로필
								</p>
								<!-- 그룹1 매칭 -->
								<div class="Match1" align="center">
								<p>이름</p>
								
								
								
								
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- BootStrap javascript 사용 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous">
	</script>
</body>
</html>