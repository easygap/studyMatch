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
<title>Regist Page</title>
<script>
function validateForm(form){
	if(form.id.value == ""){
		alert("아이디를 입력하세요.");
		RegistFrm.id.focus();
	} else if(form.idCheck.value != "idCheck"){
		alert("아이디 중복확인을 해주세요.")
		return false;
	} else if(form.pw.value == ""){
		alert("비밀번호를 입력하세요.");
		RegistFrm.pw.focus();
	} else if(form.pwdu.value == ""){
		alert("비밀번호와 맞는지 확인해 주세요.");
		RegistFrm.pwdu.focus();
	} else if(form.name.value == ""){
		alert("이름을 입력하세요.");
		RegistFrm.name.focus();
	} else if(form.birth.value == ""){
		alert("생일을 입력하세요.");
		RegistFrm.birth.focus();
	} else if(form.phone.value == ""){
		alert("휴대폰 번호를 입력하세요.");
		RegistFrm.phone.focus();
	} else if(form.address.value == ""){
		alert("주소를 입력하세요.");
		RegistFrm.address.focus();
	} else if(form.Email.value == ""){
		alert("이메일을 입력하세요.");
		RegistFrm.Email.focus();
	} else if(form.job.value == ""){
		alert("직업을 입력하세요.");
		RegistFrm.job.focus();
	}
}

function winopen(){
	if(document.RegistFrm.id.value == ""){
		alert("아이디를 먼저 입력해 주세요.");
		document.RegistFrm.id.focus();
	}else{
		window.open("../auth/idCheckAuth.do?userid=" + document.RegistFrm.id.value,"","width=500, height=300");
	}
}
</script>
</head>
<body>
<h2>회원가입</h2>
	
	<form name="RegistFrm" method="post" enctype="multipart/form-data" action="../auth/Regist.do" onsubmit="return validateForm(this);">
	<div align="center">
	<table>
			<tr>
				<td>ㆍ 아 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;디</td>
				<td><input type="text" name="id"> <input type="button" name="idCheck" onclick="winopen()" value="중복확인"></td>
				<td><input type="hidden" name="idDuplication" value="idUncheck" /></td>
				<td></td>
			</tr>
			
			<tr>
				<td>ㆍ 비 &nbsp;&nbsp;밀 &nbsp;&nbsp;&nbsp;번 &nbsp;&nbsp;&nbsp;호</td>
				<td><input type="password" name="pw"></td>
				<td></td>
			</tr>
		
			<tr>
				<td>ㆍ 비 밀 번 호 확 인 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input type="password" name="pwcheck"></td>
				<td></td>
			</tr>
			
			<tr>
				<td>ㆍ 이 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</td>
				<td><input type="text" name="name"></td>
				<td></td>
			</tr>
			
			<tr>
				<td>ㆍ 닉 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;네 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;임</td>
				<td><input type="text" name="nickName"></td>
				<td></td>
			</tr>
			
			<tr>
				<td>ㆍ 생 &nbsp;&nbsp;년 &nbsp;&nbsp;&nbsp;월 &nbsp;&nbsp;&nbsp;일</td>
				<td><input type="text" name="birth"></td>
				<td></td>
			</tr>
			 
			<tr>
				<td>ㆍ 연 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;락 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;처</td>
				<td><input type="text" name="phone" placeholder="'-' 포함하여 작성해 주세요."></td>
				<td></td>
			</tr>
			
			<tr>
				<td>ㆍ 주 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소</td>
				<td><input type="text" name="address" placeholder="'~동'까지 입력해 주세요."></td>
				<td></td>
			</tr>
			
			<tr>
				<td>ㆍ 이 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;메 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;일</td>
				<td><input type="text" name="Email"></td>
				<td></td>
			</tr>
			
			<tr>
				<td>ㆍ 직 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;업</td>
				<td><input type="text" name="job"></td>
				<td></td>
			</tr>

			<tr>
				<td>ㆍ 관 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;심 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;사
				<br/>(최대 3개까지 선택해 주세요.)&nbsp;&nbsp;&nbsp;</td>
				<td colspan="5">
					<input type="checkbox" class="interest" name="interests" value="JAVA " /> JAVA
					<input type="checkbox" class="interest" name="interests" value="PYTHON"> PYTHON
					<input type="checkbox" class="interest" name="interests" value="C"> C
					<input type="checkbox" class="interest" name="interests" value="C++"> C++ <br/>
					
					<input type="checkbox" class="interest" name="interests" value="영어"> 영어
					<input type="checkbox" class="interest" name="interests" value="일본어"> 일본어
					<input type="checkbox" class="interest" name="interests" value="중국어"> 중국어 <br/>
			
					<input type="checkbox" class="interest" name="interests" value="UI/UX"> UI/UX
					<input type="checkbox" class="interest" name="interests" value="JSP"> JSP
					<input type="checkbox" class="interest" name="interests" value="디자이너"> 디자이너
					<input type="checkbox" class="interest" name="interests" value="퍼블리셔"> 퍼블리셔
					<br/>
					
					<input type="checkbox" class="interest" name="interests" value="엑셀/한글/워드"> 엑셀/한글/워드
					<input type="checkbox" class="interest" name="interests" value="회계"> 회계 <br/>
				
					<input type="checkbox" class="interest" name="interests" value="부동산"> 부동산
					<input type="checkbox" class="interest" name="interests" value="투자/주식"> 투자/주식 <br/>
					<br/>
				</td>
			</tr>

			<tr>
				<td>ㆍ 프 &nbsp;로 &nbsp;필 &nbsp;사 &nbsp;진 &nbsp;</td>
				<td><input type="file" name="img"></td>
				<td></td>
			</tr>
		</table>
	<br/>
	<button type="submit">가입하기</button> 
	<button type="button" onclick="location.href='Login.jsp';">로그인하기</button>
	</div>
</form>
</body>
</html>