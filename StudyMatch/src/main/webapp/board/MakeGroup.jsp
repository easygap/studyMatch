<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("UTF-8");
String address = "";
if(request.getParameter("address") != null)
	address = request.getParameter("address").trim();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
<link href="../css/MakeGroup.css" rel="stylesheet"/>                                                                 
<title>Make Group</title>
<script>
	function validateForm(form) {

		var Pw = form.pw.value;
		var pwCheck = form.pwcheck.value;
		
		if (form.group1.value == "") {
			alert("그룹 생성을 위해서는 2명 이상의 그룹원이 필요합니다.\n(본인 포함 3명 이상)");
			RegistFrm.id.focus();
		}
		if (form.group2.value == "") {
			alert("그룹 생성을 위해서는 2명 이상의 그룹원이 필요합니다.\n(본인 포함 3명 이상)");
			RegistFrm.id.focus();
		}
	}
	
	function checkOnlyOne(element) {
		  
		  const checkboxes 
		      = document.getElementsByName("interests");
		  
		  checkboxes.forEach((cb) => {
		    cb.checked = false;
		  })
		  
		  element.checked = true;
		}

</script>
</head>

<body id="registbody">
	<form name="MakeGroupFrm" method="post" action="../board/CheckGroupMember.do" onsubmit="return validateForm(this);">
	<div align="center" id="makeDiv">
	<h2 id="makeHead">그 룹 생 성</h2>
	<div id="table">
	<table id="registTb">
			<tr>
				<td class="makeTd">ㆍ 그 &nbsp;&nbsp;&nbsp;룹 &nbsp;&nbsp;&nbsp;원 &nbsp;&nbsp;&nbsp;1</td>
				<td><input type="text" name="group1" class="makeInput"></td>
				<td class="makeTd"></td>
			</tr>
			
			<tr>
				<td class="makeTd">ㆍ 그 &nbsp;&nbsp;&nbsp;룹 &nbsp;&nbsp;&nbsp;원 &nbsp;&nbsp;&nbsp;2</td>
				<td><input type="text" name="group2" class="makeInput"></td>
				<td class="makeTd"></td>
			</tr>
		
			<tr>
				<td class="makeTd">ㆍ 그 &nbsp;&nbsp;&nbsp;룹 &nbsp;&nbsp;&nbsp;원 &nbsp;&nbsp;&nbsp;3</td>
				<td><input type="text" name="group3" class="makeInput"></td>
				<td class="makeTd"></td>
			</tr>
			
			<tr>
				<td class="makeTd">ㆍ 그 &nbsp;&nbsp;&nbsp;룹 &nbsp;&nbsp;&nbsp;원 &nbsp;&nbsp;&nbsp;4</td>
				<td><input type="text" name="group4" class="makeInput"></td>
				<td class="makeTd"></td>
			</tr>
			

			<tr>
				<td class="makeTd">ㆍ 관 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;심 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;사
				</td>
				<td colspan="5">
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="JAVA" /> JAVA
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="PYTHON"> PYTHON
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="C"> C
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="C++"> C++ <br/>
					
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="영어"> 영어
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="일본어"> 일본어
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="중국어"> 중국어 <br/>
			
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="UI/UX"> UI/UX
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="JSP"> JSP
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="디자이너"> 디자이너
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="퍼블리셔"> 퍼블리셔
					<br/>
					
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="엑셀/한글/워드"> 엑셀/한글/워드
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="회계"> 회계 <br/>
				
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="부동산"> 부동산
					<input type="checkbox" class="interest" name="interests" onclick='checkOnlyOne(this)' value="투자/주식"> 투자/주식 <br/>
					<br/>
				</td>
			</tr>
		</table>
		</div>
		<button type="submit" name="signUp" id="MakeButton" >생성하기</button>	
	</div>
	<input type="text" style="display:none;" name="address" value="<%=address%>" />
</form>
</body>
</html>