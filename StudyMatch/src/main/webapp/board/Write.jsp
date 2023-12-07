<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<!-- BootStrap css 사용 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
<!-- css 가져오기 -->
<link href="../css/styles.css" rel="stylesheet" />
<style>
/* 업로드 스타일 */
.filebox label {
	display: inline-block;
	padding: .5em .75em;
	color: #f8f9fa;
	font-size: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #0d6efd;
	cursor: pointer;
	border: 1px solid #E5E5E5;
	border-radius: .40em;
}

.filebox input[type="file"] { /* 파일 필드 숨기기 */
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}

.mybtn {
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;

        margin-top: 10px;
    }
    .mybtn > input {
        margin-right: 10px;
    }

</style>
<!-- 자바 스크립트 -->
<script type="text/javascript">
	function validateForm(form) { // 필수 항목 입력 확인}
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
		if (form.pass.value == "") {
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

	<!-- 코드 시작 -->
	<div class="d-flex" id="wrapper">
		<!-- 네비게이션 바 -->
		<jsp:include page="../layout/Main.jsp"></jsp:include>

		<!-- 페이지 컨텐츠 -->
		<div id="page-content-wrapper">
			<!-- 네비게이션 바 -->
			<jsp:include page="../layout/Navbar.jsp"></jsp:include>
			<div class="container-fluid">
				<br />
				<br />
				<br />
				<form name="writeFrm" method="post" enctype="multipart/form-data"
					action="../mvcboard/write.do" onsubmit="return validateForm(this);">

					<div class="filebox">
						<label for="ofile">업로드</label> <input type="file" name="ofile"
							id="ofile">
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
						<input type="submit" class="btn btn-primary pull-right"
							value="글쓰기"> 
						<input type="reset"
							class="btn btn-primary pull-right" value="RESET"> 
						<input
							type="button" onclick="location.href='./List.jsp';"
							class="btn btn-primary pull-right" value="목록 바로가기">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<!-- 푸터 -->
<jsp:include page="../layout/Footer.jsp"></jsp:include>
</html>