<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String id = (String) request.getAttribute("id");

String name = (String) request.getAttribute("name");
String birth = (String) request.getAttribute("birth");
String job = (String) request.getAttribute("job");
String nick = (String) request.getAttribute("nick");
String phone = (String) request.getAttribute("phone");
String email = (String) request.getAttribute("email");
String address = (String) request.getAttribute("address");
String interest1 = (String) request.getAttribute("interest1");
String interest2 = (String) request.getAttribute("interest2");
String interest3 = (String) request.getAttribute("interest3");
String image = (String) request.getAttribute("image");

System.out.println(nick + " / " + phone + " / " + image);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/Regist.css" rel="stylesheet"/>   
<title>MyPage</title>
<script type="text/javascript">

// 체크박스 데이터베이스에 있는 값 먼저 선택되어 호출하기
window.onload=function() {
var Checked = document.getElementsByName("interest");
var Interest1 = "<%= interest1 %>";
var Interest2 = "<%= interest2 %>";
var Interest3 = "<%= interest3 %>";
    
    for (var i = 0; i < Checked.length; i++) {
        if (Checked[i].value == Interest1 || Checked[i].value == Interest2 || Checked[i].value == Interest3) {
            Checked[i].checked = true;
            // 이 부분에 원하는 로직을 추가하세요
            console.log(Checked[i].value + " is checked.");
        }
    }
}

function count_check(obj) {
	var chkBox = document.getElementsByName("interest");	// name값 interests 를 불러옴
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
		var Pw = MyPageFrm.pw.value;
		var pwCheck = MyPageFrm.pwcheck.value;
		
		if (Pw == "") {
			alert("비밀번호를 입력하세요.");
			MyPageFrm.pw.focus();
		} else if(Pw != pwCheck){
			alert("비밀번호가 동일하지 않습니다. 다시 확인해 주세요.");
			MyPageFrm.pwcheck.focus();
		}
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

					<form name="MyPageFrm" method="post" enctype="multipart/form-data" action="../auth/MypageView.do" onsubmit="return validateForm(this);">
						<div id="MypageDiv">
							<h2 id="MypageHead">마이페이지</h2>
								<div align="center" id="MyapgeTable">
							<table id="MypageTb">
								<tr>
									<td class="MypageTd">ㆍ 아 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;디</td>
									<td><input type="text" name="id" value="<%=id%>" class="MypageInput" disabled></td>
									<td class="MypageTd"></td>
								</tr>
	
								<tr>
									<td class="MypageTd">ㆍ 비 &nbsp;&nbsp;밀 &nbsp;&nbsp;&nbsp;번 &nbsp;&nbsp;&nbsp;호</td>
									<td><input type="password" name="pw" class="MypageInput" ></td>
									<td class="MypageTd"></td>
								</tr>
	
								<tr>
									<td class="MypageTd">ㆍ 비 밀 번 호 확 인 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td><input type="password" name="pwcheck" class="MypageInput" ></td>
									<td class="MypageTd"></td>
								</tr>
	
								<tr>
									<td class="MypageTd">ㆍ 이 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</td>
									<td><input type="text" name="name" value="<%=name%>" class="MypageInput"  disabled></td>
									<td class="MypageTd"></td>
								</tr>
	
								<tr>
									<td class="MypageTd">ㆍ 닉 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;네 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;임</td>
									<td><input type="text" name="nickName" value="<%=nick%>" class="MypageInput" > <input type="button" name="nickCheck" id="nick" onclick="NickCheck()" class="RegistButton" value="중 복 확 인"></td>
									<td class="MypageTd"></td>
								</tr>
	
								<tr>
									<td class="MypageTd">ㆍ 생 &nbsp;&nbsp;년 &nbsp;&nbsp;&nbsp;월 &nbsp;&nbsp;&nbsp;일</td>
									<td><input type="text" name="birth" value="<%=birth%>" class="MypageInput" disabled></td>
									<td class="MypageTd"></td>
								</tr>
	
								<tr>
									<td class="MypageTd">ㆍ 연 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;락 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;처</td>
									<td><input type="text" name="phone" value="<%=phone%>" class="MypageInput" ></td>
									<td class="MypageTd"></td>
								</tr>
	
								<tr>
									<td class="MypageTd">ㆍ 주 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소</td>
									<td><input type="text" name="address" value="<%=address%>" class="MypageInput" ></td>
									<td class="MypageTd"></td>
								</tr>
	
								<tr>
									<td class="MypageTd">ㆍ 이 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;메 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;일</td>
									<td><input type="text" name="Email" value="<%=email%>" class="MypageInput" ></td>
									<td class="MypageTd"></td>
								</tr>
	
								<tr>
									<td class="MypageTd">ㆍ 직 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;업</td>
									<td><input type="text" name="job" value="<%=job%>" class="MypageInput" ></td>
									<td class="MypageTd"></td>
								</tr>
	
								<tr>
									<td class="MypageTd">ㆍ 관 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;심 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;사</td>
									<td><input type="text" name="interestInfor" value="<%=interest1%> / <%=interest2%> / <%=interest3%>"  class="MypageInput" disabled></td>
									<td class="MypageTd"></td>
								</tr>
	
								<tr>
									<td class="MypageTd">(* 관심사 재선택 *) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									<td colspan="5">
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="JAVA"/> JAVA 
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="PYTHON"> PYTHON
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="C"> C 
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="C++"> C++ <br />
										 
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="영어"> 영어 
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="일본어"> 일본어 
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="중국어"> 중국어 <br />
	
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="UI/UX"> UI/UX 
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="JSP"> JSP 
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="디자이너"> 디자이너 
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="퍼블리셔"> 퍼블리셔 <br />
										 
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="엑셀/한글/워드"> 엑셀/한글/워드 
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="회계"> 회계 <br />
										 
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="부동산"> 부동산 
										<input type="checkbox" class="interest" onclick="count_check(this)" name="interest" value="투자/주식"> 투자/주식 <br /> <br /></td>
								</tr>
	
								<tr>
									<td class="MypageTd">ㆍ 프 &nbsp;로 &nbsp;필 &nbsp;사 &nbsp;진 &nbsp;</td>
									<td><input type="file" name="img" onchange="fileCheck(this)" class="MypageInput" accept="image/gif, image/jpeg, image/png">
									    <% if (image != null && !image.isEmpty()) { %>
									        <!-- 기존 이미지가 있는 경우 hidden input에 기존 이미지의 파일명을 설정 -->
									        <input type="hidden" name="oldImg" value="<%= image %>">
									    <% } %>
									</td>
									<td class="MypageTd"></td>
								</tr>
	
							</table>
							<br /> <input type="reset" class="myPage" name="reset" value="RESET"> <input type="submit" class="myPage" name="save" value="저장하기">
							<br/>
							</div>
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
<!-- 푸터 -->
<jsp:include page="../layout/Footer.jsp"></jsp:include>
</html>