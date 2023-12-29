<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/Regist.css" rel="stylesheet"/>                                                                 
<title>Regist Page</title>
<script src="../js/upload.js"></script>
<script>
	
	function count_check(obj) {
		var chkBox = document.getElementsByName("interests");	// name값 interests 를 불러옴
		var totalChecked = 0;									// checked 변수에 초깃값을 0으로 설정
		
		for(var i = 0; i < chkBox.length; i++){					// 반복문으로 초깃값, 조건식, 증감식 설정
			if(chkBox[i].checked){								// 조건문으로 chkBox가 checked 됐을 경우
				totalChecked++;									// countChecked 1씩 증가
			}
		}
		if(totalChecked > 3){									// 조건문으로 totalChecked가 3개보다 클 경우
			alert("3개까지 체크할 수 있습니다.");						// alert를 띄움
			obj.checked = false;								// flase를 주어 alert를 띄운 뒤에 check가 되지 않도록 설정
			return false;
		}
	}

	function validateForm(form) {

		var Pw = form.pw.value;
		var pwCheck = form.pwcheck.value;
		
		if (form.id.value == "") {
			alert("아이디를 입력 후 중복확인을 해주세요.");
			RegistFrm.id.focus();
		}
		else if (form.pw.value == "") {
			alert("비밀번호를 입력하세요.");
			RegistFrm.pw.focus();
		} else if(Pw != pwCheck){
			alert("비밀번호가 동일하지 않습니다. 다시 확인해 주세요.");
			RegistFrm.pwcheck.focus();
		}
		else if (form.name.value == "") {
			alert("이름을 입력하세요.");
			RegistFrm.name.focus();
		}
		else if (form.birth.value == "") {
			alert("생일을 입력하세요.");
			RegistFrm.birth.focus();
		}
		else if (form.phone.value == "") {
			alert("휴대폰 번호를 입력하세요.");
			RegistFrm.phone.focus();
		}
		else if (form.address.value == "") {
			alert("주소를 입력하세요.");
			RegistFrm.address.focus();
		}
		else if (form.Email.value == "") {
			alert("이메일을 입력하세요.");
			RegistFrm.Email.focus();
		}
	}

	function winopen() {
			window.open("../auth/idCheckAuth.do", "", "width=500, height=300");
	}
</script>
</head>

<body id="registbody">
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
	<form name="RegistFrm" method="post" enctype="multipart/form-data" action="../auth/Regist.do" onsubmit="return validateForm(this);">
	<div align="center" id="registDiv">
	<h2 id="registHead">회 원 가 입</h2>
	<div id="table">
	<table id="registTb">
			<tr>
				<td class="registTd">ㆍ 아 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;디</td>
				<td><input type="text" name="id" class="registInput"> <input type="button" name="idCheck" id="idCheck" onclick="winopen()" class="RegistButton" value="중 복 확 인"></td>
				<td class="registTd"></td>
			</tr>
			
			<tr>
				<td class="registTd">ㆍ 비 &nbsp;&nbsp;&nbsp;밀 &nbsp;&nbsp;&nbsp;번 &nbsp;&nbsp;&nbsp;호</td>
				<td><input type="password" name="pw" class="registInput"></td>
				<td class="registTd"></td>
			</tr>
		
			<tr>
				<td class="registTd">ㆍ 비 밀 번 호 확&nbsp;&nbsp;인 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input type="password" name="pwcheck" class="registInput"></td>
				<td class="registTd"></td>
			</tr>
			
			<tr>
				<td class="registTd">ㆍ 이 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</td>
				<td><input type="text" name="name" class="registInput"></td>
				<td class="registTd"></td>
			</tr>
			
			<tr>
				<td class="registTd">ㆍ 닉 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;네 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;임</td>
				<td><input type="text" name="nickName" class="registInput"></td>
				<td class="registTd"></td>
			</tr>
			
			<tr>
				<td class="registTd">ㆍ 생 &nbsp;&nbsp;년 &nbsp;&nbsp;&nbsp;월 &nbsp;&nbsp;&nbsp;일</td>
				<td><input type="text" name="birth" class="registInput"></td>
				<td class="registTd"></td>
			</tr>
			 
			<tr>
				<td class="registTd">ㆍ 연 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;락 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;처</td>
				<td><input type="text" name="phone" class="registInput" placeholder="'-' 포함하여 작성해 주세요."></td>
				<td class="registTd"></td>
			</tr>
			
			<tr>
				<td class="registTd">ㆍ 주 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소</td>
				<td><input type="text" name="address" class="registInput" placeholder="'~동'까지 입력해 주세요."></td>
				<td class="registTd"></td>
			</tr>
			
			<tr>
				<td class="registTd">ㆍ 이 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;메 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;일</td>
				<td><input type="text" name="Email" class="registInput"></td>
				<td class="registTd"></td>
			</tr>
			
			<tr>
				<td class="registTd">ㆍ 직 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;업</td>
				<td><input type="text" name="job" class="registInput"></td>
				<td></td>
			</tr>

			<tr>
				<td class="registTd">ㆍ 관 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;심 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;사
				<br/>(최대 3개까지 선택해 주세요.)&nbsp;&nbsp;&nbsp;</td>
				<td colspan="5">
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="JAVA " /> JAVA
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="PYTHON"> PYTHON
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="C"> C
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="C++"> C++ <br/>
					
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="영어"> 영어
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="일본어"> 일본어
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="중국어"> 중국어 <br/>
			
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="UI/UX"> UI/UX
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="JSP"> JSP
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="디자이너"> 디자이너
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="퍼블리셔"> 퍼블리셔
					<br/>
					
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="엑셀/한글/워드"> 엑셀/한글/워드
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="회계"> 회계 <br/>
				
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="부동산"> 부동산
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="투자/주식"> 투자/주식 <br/>
					<br/>
				</td>
			</tr>

			<tr>
				<td class="registTd">ㆍ 프 &nbsp;로 &nbsp;필 &nbsp;사 &nbsp;진 &nbsp;</td>
				<td><input type="file" name="img" class="registInput" onchange="fileCheck(this)" accept="image/gif, image/jpeg, image/png"></td>
				<td class="registTd"></td>
			</tr>
		</table>
		</div>
	<br/>
	<button type="submit" name="signUp" id="RegistButton" >가입하기</button>
	</div>
</form>
</div>
</div>
</div>
 <!-- BootStrap javascript 사용 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
            crossorigin="anonymous"></script>
</body>
<jsp:include page="../layout/Footer.jsp"></jsp:include>
</html>