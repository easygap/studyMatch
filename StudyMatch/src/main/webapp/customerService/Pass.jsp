<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주 묻는 게시판</title>
<!-- BootStrap css 사용 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<!-- css 가져오기 -->
<link href="Form.css" rel="stylesheet" type="{ % static "/Form.css" % }" />
<script type="text/javascript">
	function validateForm(form) {
		if (form.pass.value == "") {
			alert("비밀번호를 입력하세요.");
			form.pass.focus();
			return false;
		}
		if(form.pass.value == "5"){
			alert("관리자님 반갑습니다.")
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
	<h2>자주 묻는 게시판 - 비밀번호 검증(Pass)</h2>
	<form name="writeFrm" method="post" action="../mvcboard/pass.do" onsubmit="return validateForm(this);">
		<input type="hidden" name="idx" value="${ param.idx }" />
		<input type="hidden" name="mode" value="${ param.mode }" />
		<table border="1" width="90%">
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="pass" style="width:100px;" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">검증하기</button>
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