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

System.out.println(nick + " / " + phone);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../css/Regist.css" rel="stylesheet"/>   
<title>MyPage</title>
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

					<form name="MyPageFrm" method="post" action="../auth/Mypage.do" onsubmit="return validateForm(this);">
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
									<td><input type="text" name="nickName" value="<%=nick%>" class="MypageInput" ></td>
									<td class="MypageTd"></td>
								</tr>
	
								<tr>
									<td class="MypageTd">ㆍ 생 &nbsp;&nbsp;년 &nbsp;&nbsp;&nbsp;월 &nbsp;&nbsp;&nbsp;일</td>
									<td><input type="text" name="birth" value="<%=birth%>" class="MypageInput" ></td>
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
										<input type="checkbox" class="interest" name="interest" value="JAVA " /> JAVA 
										<input type="checkbox" class="interest" name="interest" value="PYTHON"> PYTHON
										<input type="checkbox" class="interest" name="interest" value="C"> C 
										<input type="checkbox" class="interest" name="interest" value="C++"> C++ <br />
										 
										<input type="checkbox" class="interest" name="interest" value="영어"> 영어 
										<input type="checkbox" class="interest" name="interest" value="일본어"> 일본어 
										<input type="checkbox" class="interest" name="interest" value="중국어"> 중국어 <br />
	
										<input type="checkbox" class="interest" name="interest" value="UI/UX"> UI/UX 
										<input type="checkbox" class="interest" name="interest" value="JSP"> JSP 
										<input type="checkbox" class="interest" name="interest" value="디자이너"> 디자이너 
										<input type="checkbox" class="interest" name="interest" value="퍼블리셔"> 퍼블리셔 <br />
										 
										<input type="checkbox" class="interest" name="interest" value="엑셀/한글/워드"> 엑셀/한글/워드 
										<input type="checkbox" class="interest" name="interest" value="회계"> 회계 <br />
										 
										<input type="checkbox" class="interest" name="interest" value="부동산"> 부동산 
										<input type="checkbox" class="interest" name="interest" value="투자/주식"> 투자/주식 <br /> <br /></td>
								</tr>
	
								<tr>
									<td class="MypageTd">ㆍ 프 &nbsp;로 &nbsp;필 &nbsp;사 &nbsp;진 &nbsp;</td>
									<td><input type="file" name="img" onchange="fileCheck(this)" value="<%= image %>" class="MypageInput"  accept="image/gif, image/jpeg, image/png"></td>
									<td class="MypageTd"></td>
								</tr>
	
							</table>
							<br /> <input type="reset" class="myPage" name="reset" value="RESET"> <input type="submit" class="myPage" name="save" value="저장하기">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<!-- 푸터 -->
<jsp:include page="../layout/Footer.jsp"></jsp:include>
</html>