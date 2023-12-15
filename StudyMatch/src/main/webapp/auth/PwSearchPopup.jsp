<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");

String id = request.getParameter("id");

String not = "N";
if(request.getParameter("not") != not){
	not = request.getParameter("not");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PW Change</title>
<link href="../css/Regist.css" rel="stylesheet"/>
<script>
	function validateForm(form) {
		if (form.id.value == "") {
			alert("아이디를 입력해 주세요.");
		} else if (form.pw.value == "") {
			alert("비밀번호를 입력해 주세요.");
		} else if (form.pwCheck.value == "") {
			alert("비밀번호를 한 번 더 입력해 주세요.");
			return false;
		}
	}
	
	function compareAndChange(){
		var pwChange = document.pwChangeFrm.pw.value;
		var pwCheck = document.pwChangeFrm.pwCheck.value;
		
		if(pwChange == pwCheck){
			alert("비밀번호 변경이 완료되었습니다. 로그인을 해주세요.");
			close();
		}else{
			alert("비밀번호가 동일하지 않습니다. 다시 작성해 주세요.");
		}
	}
</script>
</head>
<body>
	<form name="pwChangeFrm" method="post"
		action="../auth/PwSearchPopup.do"
		onsubmit="return validateForm(this);">
		<div align="center">
		<h2>비밀번호 변경하기</h2>
			<table>
				<tr>
					<td>ㆍ 아&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;디&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" name="ID" /></td>
					<td></td>
				</tr>
				<tr>
					<td>ㆍ 비 &nbsp;&nbsp;밀 &nbsp;&nbsp;&nbsp;번 &nbsp;&nbsp;&nbsp;호</td>
					<td><input type="password" name="pw"></td>
					<td></td>
				</tr>

				<tr>
					<td>ㆍ 비 밀 번 호 확 인 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><input type="password" name="pwCheck"></td>
					<td></td>
				</tr>
				
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			<input type="submit" name="Change" class="pwChange" onclick="compareAndChange()" value="변경하기" /> <input type="button" name="close" class="pwChange" value="닫기" onclick="window.close()" />
		</div>
	</form>
</body>
</html>