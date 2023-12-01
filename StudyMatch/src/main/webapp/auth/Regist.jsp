<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">                                                                 
<title>Insert title here</title>
<script>
function validateForm(form){
	if(form.id.value == ""){
		alert("아이디를 입력하세요.");
		return false;
	}
	if(form.pw.value == ""){
		alert("비밀번호를 입력하세요.");
		return false;
	}
	if(form.pwdu.value == ""){
		alert("비밀번호와 맞는지 확인해 주세요.");
		return false;
	}
	if(form.name.value == ""){
		alert("이름을 입력하세요.");
		return false;
	}
	if(form.birth.value == ""){
		alert("생일을 입력하세요.");
		return false;
	}
	if(form.phone.value == ""){
		alert("휴대폰 번호를 입력하세요.");
		return false;
		}
	if(form.address.value == ""){
		alert("주소를 입력하세요.");
		return false;
	}
	if(form.Email.value == ""){
		alert("이메일을 입력하세요.");
		return false;
	}
	if(form.job.value == ""){
		alert("직업을 입력하세요.");
		return false;
	}
}
</script>
</head>
<body>
<h2>회원가입</h2>
	
	<form name="ResistFrm" method="post" action="RegistProcess.jsp" onsubmit="return validateForm(this);">
		아  이   디 :  <input type="text" name="id"><br/>
		비밀    번호 :  <input type="password" name="pw"><br/>
		비밀번호 확인 :  <input type="password" name="pwdu"><br/>
		이       름 :  <input type="text" name="name"><br/>
		닉   네   임 :  <input type="text" name="nickname"><br/>
		생년    월일 :  <input type="text" name="birth"><br/> 
		휴 대 폰 번호 :  <input type="text" name="phone" size="13"><br/>
		주       소 :  <input type="text" name="address"><br/>
		이   메   일 :  <input type="text" name="Email"><br/>
		직       업 :  <input type="text" name="job"><br/>
		
		<br/>
		<input type="radio" name="gender" value="m" checked>남자
		<input type="radio" name="gender" value="f">여자
		<br/>
		
		<button type="submit">가입하기</button> 
		<button type="button" onclick="location.href='Login.jsp';">로그인하기</button>
	</form>
</body>
</html>