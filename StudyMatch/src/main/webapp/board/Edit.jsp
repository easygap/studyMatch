<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
<!-- BootStrap css 사용 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<!-- css 가져오기 -->
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function validateForm(form) {
		if (form.name.value == "") {
			alert("작성자를 입력하세요.");
			form.name.focus();
			return false;
		}
		if (form.title.value == "") {
			alert("제목을 입력하세요.");
			form.title.focus();
			return false;
		}
		if (form.content.value == "") {
			alert("내용을 입력하세요.");
			form.content.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<!-- Jquery 사용 -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous"></script>
	<!-- BootStrap javascript 사용 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
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

				<h1 class="mt-4">파일 첨부형 게시판 - 수정하기(Edit)</h1>
				<form name="writeFrm" method="post" enctype="multipart/form-data"
					action="../mvcboard/edit.do" onsubmit="return validateForm(this);">
					<input type="hidden" name="idx" value="${ dto.idx }" /> <input
						type="hidden" name="prevOfile" value="${ dto.ofile }" /> <input
						type="hidden" name="prevSfile" value="${ dto.sfile }" />

					<table border="1" width="90%">
						<tr>
							<td>작성자</td>
							<td><input type="text" name="name" style="width: 150px;"
								value="${dto.name}" /></td>
						</tr>
						<tr>
							<td>제목</td>
							<td><input type="text" name="title" style="width: 90%;"
								value="${ dto.title }" /></td>
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea name="content"
									style="width: 90%; height: 100px;">${ dto.content }</textarea>
							</td>
						</tr>
						<tr>
							<td>첨부 파일</td>
							<td><input type="file" name="ofile" /></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<button type="submit">작성 완료</button>
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
</body>
<!-- 푸터 -->
		<jsp:include page="../layout/Footer.jsp"></jsp:include>
</html>