<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<% String name = (String) session.getAttribute("kakaoName"); 
	String nick = (String) session.getAttribute("kakaoNick");
	String email = (String) session.getAttribute("kakaoEmail");
	String birth = (String) session.getAttribute("kakaoBirth");
	String phone = (String) session.getAttribute("kakaoPhone");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>카카오 로그인 정보 입력</title>
<script type="text/javascript">
</script>
<script>
function count_check(checkbox) {
	var checkboxes = document.querySelectorAll('.interest:checked');
	var submitButton = document.getElementById('submitButton');
	
	var checkedCount = 0;
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) { // 관심사 선택 수량 체크
            checkedCount++;
        }
    }
	if (checkboxes.length > 3) { // 관심사 3개 이상 선택 시
		alert('최대 3개의 관심사만 선택할 수 있습니다.');
        checkbox.checked = false;
	}
	
	var boxCheck = checkedCount > 0; // 체크박스가 1개 이상 체크하면 true 반환
    submitButton.disabled = !boxCheck;
}

</script>
</head>
<body>
<div id="popup-container"></div>
<div id="popup-content"></div>
<h2>카카오 로그인 정보</h2>
<p>
<form action="UpdateUserInfoServlet" method="post">
        <p>이름: <input type="text" name="newName" value="<%= (name != null) ? name : "" %>" required></p>
        <p>닉네임: <input type="text" name="newNick" value="<%= (nick != null) ? nick : "" %>" required></p>
        <p>이메일: <input type="text" name="newEmail" value="<%= (email != null) ? email : "" %>" required></p>
        <p>생년월일: <input type="text" name="newBirth" value="<%= (birth != null) ? birth : "" %>" required></p>
        <p>핸드폰: <input type="text" name="newPhone" value="<%= (phone != null) ? phone : "" %>" required></p>
        <p>주소: <input type="text" name="address" placeholder="(동/읍/면)까지만 입력" required></p>
        <p>관심사 (매칭을 위해 필수 선택 / 최대 3개)</p>
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
					<button id="submitButton" type="submit" disabled>입력완료</button>
					</form>
</body>
</html>