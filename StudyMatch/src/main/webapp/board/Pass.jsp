<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>

<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />

<script type="text/javascript">
	function validateForm(form) {
		if (form.pass.value == "") {
			alert("비밀번호를 입력하세요.");
			form.pass.focus();
			return false;
		}
	}
</script>
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
				<br /> <br />
				<h1 class="mt-4">비밀번호 검증</h1>
				<form name="writeFrm" method="post" action="../mvcboard/pass.do"
					onsubmit="return validateForm(this);">
					<input type="hidden" name="idx" value="${ param.idx }" /> <input
						type="hidden" name="mode" value="${ param.mode }" />
					<table border="1" width="90%">
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="pass" style="width: 100px;" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<button type="submit">검증하기</button>
								<button type="reset">RESET</button>
								<button type="button"
									onclick="location.href='../mvcboard/list.do';">목록 바로가기
								</button>
							</td>
						</tr>
					</table>
				</form>
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