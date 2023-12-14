<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();
String interest = request.getParameter("interest");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />

<!-- 업로드 css -->
<link href="../css/upload.css" rel="stylesheet" />

<!-- 자바 스크립트 -->
<script src="../js/upload.js"></script>

<!-- 스마트에디터 -->
<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors,
		elPlaceHolder : "content",
		sSkinURI : "../se2/SmartEditor2Skin.html",
		fCreator : "createSEditor2"
	});

	//‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
	function submitContents(elClickedObj) {

		// 에디터의 내용이 textarea에 적용된다.
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

		// 에디터의 내용에 대한 값 검증은 이곳에서
		// document.getElementById("textAreaContent").value를 이용해서 처리한다.

		try {
			elClickedObj.form.submit();
		} catch (e) {
		}
	}

	// textArea에 이미지 첨부
	function pasteHTML(filepath) {
		var sHTML = '<img src="../uploads/'+filepath+'">';
		oEditors.getById["content"].exec("PASTE_HTML", [ sHTML ]);
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
				<br /> <br /> <br />
				<form name="writeFrm" method="post" enctype="multipart/form-data"
					action="../board/WriteController.do?<%=interest%>"
					onsubmit="return validateForm(this);">

					<div class="filebox">
						<label for="ofile">업로드</label> <input type="file" name="ofile"
							onchange="fileCheck(this)"
							accept="image/gif, image/jpeg, image/png" id="ofile">
					</div>

					<div class="mb-3">
						<label for="title">제목</label> <input type="text"
							class="form-control" name="title" id="title"
							placeholder="제목을 입력해 주세요">
					</div>

					<div class="mb-3">
						<label for="content">내용</label>
						<textarea class="form-control" rows="11" name="content"
							id="content" placeholder="내용을 입력해 주세요"></textarea>
					</div>

					<div class="mybtn">
						<input type="button"
							onclick="location.href='../board/list.do?interest=<%=interest%>';"
							class="btn btn-secondary pull-right" value="목록 바로가기"> <input
							type="submit" class="btn btn-primary pull-right" value="글쓰기">
						<input type="hidden" name="interest" value="<%=interest%>">
					</div>
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