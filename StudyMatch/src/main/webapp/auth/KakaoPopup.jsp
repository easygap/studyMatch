<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String name = (String) session.getAttribute("kakaoName");
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
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
function count_check(checkbox) {
	var checkboxes = document.querySelectorAll('.interest:checked');
	var submitButton = document.getElementById('submitButton');
	
	var checkCnt = 0;
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) { // 관심사 선택 수량 체크
            checkCnt++;
        }
    }
	if (checkCnt > 3) { // 관심사 3개 이상 선택 시
		alert('최대 3개의 관심사만 선택할 수 있습니다.');
        checkbox.checked = false;
	}
}

function getInterests() {
    var interests = [];
    var checkboxes = document.querySelectorAll('input[name="interests"]:checked');

    checkboxes.forEach(function(checkbox) {
        selectedInterests.push(checkbox.value);
    });
    return interests;
}
function validateForm() {
    // 텍스트 필드 검증
    var textFields = document.querySelectorAll('input[type="text"]');
    for (var i = 0; i < textFields.length; i++) {
        if (textFields[i].hasAttribute('required') && textFields[i].value.trim() === '') {
            alert('입력란을 모두 채워주세요.');
            return false;
        }
    }

    // 체크박스 검증
    var checkboxes = document.querySelectorAll('.interest:checked');
    if (checkboxes.length === 0 || checkboxes.length > 3) {
        alert('관심사를 1개 이상 선택해 주세요.');
        return false;
    }

    return true;
}
// 사용자 입력 정보 서버로 전송
function sendUserData() {
    if (!validateForm()) {
        return;
    }
	var id = '<%= (String) session.getAttribute("kakaoId") %>';
	var queryString = $('#kakaoData').serialize() + '&id=' + id;
  $.ajax({
	  type: 'GET',
	    url: '../auth/KakaoLogin.do?' + queryString,
        success: function(response) {
            if (response.status === 'success') {
                alert('가입이 완료되었습니다.');
                window.close();
                redirectToMainPage();
            } else {
                alert('가입 실패: ' + response.message);
            }
        },
        error: function() {
            alert('서버 오류가 발생했습니다.');
        }
    });
}
function redirectToMainPage() {
    window.opener.location.href = '../board/Main.do';
}
</script>
</head>
<body>
	<div id="popup-container"></div>
	<div id="popup-content"></div>
	<h2>카카오 로그인 정보</h2>
	<form id='kakaoData' action="../auth/KakaoLogin.do" method="GET">
		<p>이름: <input type="text" name="name" value="<%=(name != null) ? name : ""%>" required></p>
		<p>닉네임: <input type="text" name="nick" value="<%=(nick != null) ? nick : ""%>" required></p>
		<p>이메일: <input type="text" name="email" value="<%=(email != null) ? email : ""%>" required></p>
		<p>생년월일: <input type="text" name="birth" value="<%=(birth != null) ? birth : ""%>" required></p>
		<p>핸드폰: <input type="text" name="phone" value="<%=(phone != null) ? phone : ""%>" required></p>
		<p>직업: <input type="text" name="job"></p>
		<p>주소: <input type="text" name="address" placeholder="(동/읍/면)까지만 입력" required></p>
		<p>관심사 (매칭을 위해 필수 선택 / 최대 3개)</p>
		<input type="checkbox" class="interest" onclick="count_check(this)"
			name="interests" value="JAVA " /> JAVA <input type="checkbox"
			class="interest" onclick="count_check(this)" name="interests"
			value="PYTHON"> PYTHON <input type="checkbox"
			class="interest" onclick="count_check(this)" name="interests"
			value="C"> C <input type="checkbox" class="interest"
			onclick="count_check(this)" name="interests" value="C++"> C++
		<br /> <input type="checkbox" class="interest"
			onclick="count_check(this)" name="interests" value="영어"> 영어 <input
			type="checkbox" class="interest" onclick="count_check(this)"
			name="interests" value="일본어"> 일본어 <input type="checkbox"
			class="interest" onclick="count_check(this)" name="interests"
			value="중국어"> 중국어 <br /> <input type="checkbox"
			class="interest" onclick="count_check(this)" name="interests"
			value="UI/UX"> UI/UX <input type="checkbox" class="interest"
			onclick="count_check(this)" name="interests" value="JSP"> JSP
		<input type="checkbox" class="interest" onclick="count_check(this)"
			name="interests" value="디자이너"> 디자이너 <input type="checkbox"
			class="interest" onclick="count_check(this)" name="interests"
			value="퍼블리셔"> 퍼블리셔 <br /> <input type="checkbox"
			class="interest" onclick="count_check(this)" name="interests"
			value="엑셀/한글/워드"> 엑셀/한글/워드 <input type="checkbox"
			class="interest" onclick="count_check(this)" name="interests"
			value="회계"> 회계 <br /> <input type="checkbox" class="interest"
			onclick="count_check(this)" name="interests" value="부동산"> 부동산
		<input type="checkbox" class="interest" onclick="count_check(this)"
			name="interests" value="투자/주식"> 투자/주식 <br /> <br />
		<button id="submitButton" type="button" onclick="sendUserData()">입력완료</button>
	</form>
</body>
</html>