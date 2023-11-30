<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<!-- BootStrap css 사용 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<!-- css 가져오기 -->
<link href="Form.css" rel="stylesheet" type="text/css" />
<!-- 자바 스크립트 -->
<script type="text/javascript">
	function validateForm(form) { // 필수 항목 입력 확인}
		if(form.name.value == "") {
			alert("작성자를 입력하세요.");
			form.name.focus();
			return false;
		}
		if(form.title.value == ""){
			alert("제목을 입력하세요.");
			form.title.focus();
			return false;
		}
		if(form.content.value == ""){
			alert("내용을 입력하세요.");
			form.content.focus();
			return false;
		}
		if(form.pass.value == ""){
			alert("비밀번호를 입력하세요.");
			form.pass.focus();
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

<!-- 네비게이션 바 -->
<jsp:include page="../layout/Navbar.jsp"></jsp:include>	
<!-- 코드 시작 -->	
	<h2>파일 첨부형 게시판 - 글쓰기(write)</h2>
	<form name="writeFrm" method="post" enctype="multipart/form-data"
		action="../mvcboard/write.do" onsubmit="return validateForm(this);">
	<table border="1" width="90%">
		<tr>
			<td>작성자</td>
			<td>
				<input type="text" name="name" style="width:150px;" />
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>
				<input type="text" name="title" style="width:90%;" />
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="content" style="width:90%;height:100px;"></textarea>
			</td>
		</tr>
		<tr>
			<td>첨부 파일</td>
			<td>
				<input type="file" name="ofile" />
			</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" name="pass" style="width:100px;" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="submit">작성 완료</button>
				<button type="reset">RESET</button>
				<button type="button" onclick="location.href='../mvcboard/list.do';">
					목록 바로가기
				</button>
			</td>
		</tr>
	</table>
</form>
</body>
</html>