<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage</title>
<script type="text/javascript">

</script>
</head>
<body>

<h2>마이페이지</h2>
<form name="MyPageFrm" method="post" action="../auth/MyPage.do" onsubmit="return validateForm(this);">
<div align="center">
		<table>
				<tr>
					<td>ㆍ 아 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;디</td>
					<td><input type="text" name="id" disabled></td>
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
					<td><input type="text" name="name" disabled></td>
					<td></td>
				</tr>
				
				<tr>
					<td>ㆍ 닉 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;네 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;임</td>
					<td><input type="text" name="nickName"></td>
					<td></td>
				</tr>
	
				<tr>
					<td>ㆍ 생 &nbsp;&nbsp;년 &nbsp;&nbsp;&nbsp;월 &nbsp;&nbsp;&nbsp;일</td>
					<td><input type="text" name="birth" disabled></td>
					<td></td>
				</tr>
	
				<tr>
					<td>ㆍ 연 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;락 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;처</td>
					<td><input type="text" name="phone"></td>
					<td></td>
				</tr>
	
				<tr>
					<td>ㆍ 주 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소</td>
					<td><input type="text" name="address"></td>
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
					<br/>(*  관심사 재선택  *) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td colspan="5">
						<input type="checkbox" class="interest" name="interest" value="JAVA " /> JAVA
						<input type="checkbox" class="interest" name="interest" value="PYTHON"> PYTHON
						<input type="checkbox" class="interest" name="interest" value="C"> C
						<input type="checkbox" class="interest" name="interest" value="C++"> C++ <br/>
						
						<input type="checkbox" class="interest" name="interest" value="영어"> 영어
						<input type="checkbox" class="interest" name="interest" value="일본어"> 일본어
						<input type="checkbox" class="interest" name="interest" value="중국어"> 중국어 <br/>
				
						<input type="checkbox" class="interest" name="interest" value="UI/UX"> UI/UX
						<input type="checkbox" class="interest" name="interest" value="JSP"> JSP
						<input type="checkbox" class="interest" name="interest" value="디자이너"> 디자이너
						<input type="checkbox" class="interest" name="interest" value="퍼블리셔"> 퍼블리셔
						<br/>
						
						<input type="checkbox" class="interest" name="interest" value="엑셀/한글/워드"> 엑셀/한글/워드
						<input type="checkbox" class="interest" name="interest" value="회계"> 회계 <br/>
					
						<input type="checkbox" class="interest" name="interest" value="부동산"> 부동산
						<input type="checkbox" class="interest" name="interest" value="투자/주식"> 투자/주식 <br/>
						<br/>
					</td>
				</tr>

				<tr>
					<td>ㆍ 프 &nbsp;로 &nbsp;필 &nbsp;사 &nbsp;진 &nbsp;</td>
					<td><input type="file" name="img" onchange="fileCheck(this)"
						accept="image/gif, image/jpeg, image/png"></td>
					<td></td>
				</tr>

			</table>
			<br/>
			<input type="reset" name="reset" value="RESET"> 
			<input type="submit" name="save" value="저장하기">
		</div>
	</form>
</body>
</html>