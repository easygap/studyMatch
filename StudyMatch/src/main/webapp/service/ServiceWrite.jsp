<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();
String writer = (String) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매치메이트 1:1 문의</title>
<script>
function validateForm() {
	console.log("validateForm 함수 호출 확인");
	
	var submitButton = document.getElementById("form");
	
	if(inquiryFrm.categorySelect.value === "category"){
		alert("문의 유형을 선택해 주세요.");
		return false;
	} else if(inquiryFrm.title.value === ""){
		alert("문의 제목을 작성해 주세요.");
		inquiryFrm.title.focus();
		return false;
	} else if(inquiryFrm.content.value === ""){
		alert("문의 내용을 작성해 주세요.");
		inquiryFrm.content.focus();
		return false;
	} else {
		submitButton.submit();
		return true;
	}
}
</script>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
<!-- 업로드 css -->
<link href="../css/upload.css" rel="stylesheet" />

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
				<form name="inquiryFrm" method="post" id="form" enctype="multipart/form-data" action="../service/ServiceWrite.do">

					<div class="filebox">
						<label for="ofile">사진첨부</label> <input type="file" name="ofile"
							onchange="fileCheck(this)"
							accept="image/gif, image/jpeg, image/png" id="ofile">
					</div>
					<br/>
					<select id="categorySelect" name="categorySelect" onchange="updateDetail()">
						<option value="category">문의유형</option>
						<option value="게시판">게시판</option>
						<option value="회원/그룹">회원/그룹</option>
						<option value="기타문의">기타문의</option>
					</select>
					<select id="detailSelect" name="detailSelect"></select>
					<script>
						function updateDetail() {
							var categorySelect = document.getElementById("categorySelect");
							var detailSelect = document.getElementById("detailSelect");

							// 선택된 값 가져오기
							var selectedCategory = categorySelect.value;

							// 세부유형 옵션 초기화
							detailSelect.innerHTML = "";

							// 선택된 값에 따라 세부유형 옵션 추가
							switch (selectedCategory) {
							case "게시판":
								detailSelect.add(new Option("어학", "어학"));
								detailSelect.add(new Option("개발자", "개발자"));
								detailSelect.add(new Option("UIUX", "UIUX"));
								detailSelect.add(new Option("사무직", "사무직"));
								detailSelect.add(new Option("금융", "금융"));
								detailSelect.add(new Option("자유게시판", "자유게시판"));
								detailSelect.add(new Option("그룹게시판", "그룹게시판"));
								break;
							case "회원/그룹":
								detailSelect.add(new Option("회원정보", "회원정보"));
								detailSelect.add(new Option("그룹정보", "그룹정보"));
								break;
							case "기타문의":
								detailSelect.add(new Option("오류신고", "오류신고"));
								detailSelect.add(new Option("회원신고", "회원신고"));
								detailSelect.add(new Option("건의사항", "건의사항"));
								detailSelect.add(new Option("제휴문의", "제휴문의"));
								break;
							}
						}
					</script>
					
					<br/><br/>
					
					<div class="mb-3">
						<input type="text"
							class="form-control" name="title" id="title"
							placeholder="제목을 입력해 주세요">
					</div>

					<div class="mb-3">
						<textarea class="form-control" rows="11" name="content"
							id="content" placeholder="내용을 입력해 주세요"></textarea>
					</div>

					<div class="mb-3">
						<label for="pass">비밀번호</label> <input type="password"
							class="form-control" name="pass" id="pass" style="width: 200px;">
					</div>

					<div class="mybtn">
						<input type="reset" class="reset" value="RESET"> 
						<input type="button" class="write" onclick="validateForm();" value="문의하기">
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