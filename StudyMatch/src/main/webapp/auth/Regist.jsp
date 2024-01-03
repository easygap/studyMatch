<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/Regist.css" rel="stylesheet"/>                                                                 
<title>Regist Page</title>
<script src="../js/upload.js"></script>
<script src="
https://cdn.jsdelivr.net/npm/verbal-expressions@1.0.2/dist/verbalexpressions.min.js
"></script>
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

	function validateForm() {
		
		var submitButton = document.getElementById("form");
		
		
		/** verbal 정규식 */
		
		/** 핸드폰 번호 정규식 */
		var phoneCheck = VerEx()
            .startOfLine()
			.range('0', '9')
			.repeatPrevious(3)
			.maybe('-')
			.range('0', '9')
			.repeatPrevious(4)
			.maybe('-')
			.range('0', '9')
			.repeatPrevious(4)
			.endOfLine()

		/** 생일 정규식 */
		var birthCheck = VerEx()
            .startOfLine()
			.range('1', '2')
			.repeatPrevious(1)
			.range('0', '9')
			.repeatPrevious(3)
			.range('0', '1')
			.repeatPrevious(1)
			.range('0', '9')
			.repeatPrevious(1)
			.range('0', '3')
			.repeatPrevious(1)
			.range('0', '9')
			.repeatPrevious(1)
			.endOfLine()
			
			
		/** 정규 표현식 */
		
		/** 아이디 정규식 */
		var idCheck = /^[a-zA-Z0-9]+$/;
			
		/** 주소 정규식 */
		var addressCheck = /^(경기도|서울특별시|부산광역시|대구광역시|인천광역시|광주광역시|대전광역시|울산광역시|세종특별자치시|제주특별자치도) [가-힣\s]+(시|군|구)[가-힣\s]*$/;
		
		// 한글 이름 (2글자 이상, 5글자 이하)
		var nameCheck = /^[가-힣a-zA-Z]+$/;
		    		
		// 정규식 체크를 위한 변수 <-- 폼 값을 받아온다.
		var phone = form.phone.value;
		var birth = form.birth.value;
		var address = form.address.value;
		var id = form.id.value;
		var name = form.name.value;
		var email = form.Email.value;
		
		var Pw = form.pw.value;
		var pwCheck = form.pwcheck.value;
		
		/** 체크된 interest 박스가 있는지 검사 */
		var interest = document.getElementsByName("interests");
		
		var chk = "N";
		
		 for (var i = 0; i < interest.length; i++) {
		        if (interest[i].checked) 
		        	chk = "Y";
		    }
		
		if (id == "") {
			alert("아이디를 입력 후 중복확인을 해주세요.");
			RegistFrm.id.focus();
		}
		else if (Pw == "") {
			alert("비밀번호를 입력하세요.");
			RegistFrm.pw.focus();
		} else if(Pw != pwCheck){
			alert("비밀번호가 동일하지 않습니다. 다시 확인해 주세요.");
			RegistFrm.pwcheck.focus();
		}
		else if (name == "") {
			alert("이름을 입력하세요.");
			RegistFrm.name.focus();
		}
		else if (birth == "") {
			alert("생일을 입력하세요.");
			RegistFrm.birth.focus();
		}
		else if (phone == "") {
			alert("휴대폰 번호를 입력하세요.");
			RegistFrm.phone.focus();
		}
		else if (address == "") {
			alert("주소를 입력하세요.");
			RegistFrm.address.focus();
		}
		else if (email == "") {
			alert("이메일을 입력하세요.");
			RegistFrm.Email.focus();
		}
		else if(idCheck.test(id) === false){
			alert("아이디는 한글, 특수문자 입력이 불가합니다.");
		}
		else if(nameCheck.test(name) === false){
			alert("올바른 이름을 입력하세요.");
		}
//		else if((koreanNameCheck.test(name).equals("false")) && (englishNameCheck.test(name).equals("false"))){
//			alert("올바른 이름을 입력하세요.");
//		}
		else if (phoneCheck.test(phone) !== true){
			alert("올바른 휴대폰 번호를 입력하세요.");
		}
		else if (birthCheck.test(birth) !== true){
			alert("생년월일은 YYYYMMDD 형식으로 입력하세요.");
		}
		else if(addressCheck.test(address) !== true){
			alert("올바른 주소를 입력하세요.");
		}
		else if (chk === 'N'){
			alert("관심사를 1개 이상 선택해 주세요.");
		}
		 else {
			submitButton.submit();
		}
	}

	function winopen() {
		var width = 500;
	    var height = 250;
	    var left = (screen.width - width) / 2;
	    var top = (screen.height - height) / 2;

	    window.open("../auth/idCheckAuth.do", "", "width=" + width + ", height=" + height + ", left=" + left + ", top=" + top);
	}
	
	function NickCheck(){
		var width = 500;
	    var height = 250;
	    var left = (screen.width - width) / 2;
	    var top = (screen.height - height) / 2;

	    window.open("../auth/nickCheckAuth.do", "", "width=" + width + ", height=" + height + ", left=" + left + ", top=" + top);
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
	<form id="form" name="RegistFrm" method="post" enctype="multipart/form-data" action="../auth/Regist.do" >
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
				<td><input type="text" name="nickName" class="registInput"> <input type="button" name="nickCheck" id="nick" onclick="NickCheck()" class="RegistButton" value="중 복 확 인"></td>
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
				<td><input type="email" name="Email" class="registInput"></td>
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
					<input type="checkbox" class="interest" onclick="count_check(this)" name="interests" value="JAVA" /> JAVA
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
	<button type="button" name="signUp" id="RegistButton" onClick="validateForm();" >가입하기</button>
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