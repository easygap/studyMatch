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
	function validateForm() {
		var submitButton = document.getElementById("form");
		
		if (pwChangeFrm.ID.value == "") {
			alert("아이디를 입력해 주세요.");
			return false;
		} else if (pwChangeFrm.pw.value == "") {
			alert("비밀번호를 입력해 주세요.");
			return false;
		} else if (pwChangeFrm.pwCheck.value == "") {
			alert("비밀번호를 한 번 더 입력해 주세요.");
			return false;
		} else if (pwChangeFrm.pw.value !== pwChangeFrm.pwCheck.value) {
	        alert("비밀번호가 일치하지 않습니다. 다시 확인 후 시도해 주세요.");
	        return false;
	    } else if(pwChangeFrm.ID.value !== '<%= id %>'){
	        alert("아이디가 이전 입력값과 동일하지 않습니다. 다시 확인 후 시도해 주세요.");
	        return false;
	    } else {
	    	submitButton.submit();
	    	return true;
	    }	
	}
</script>
</head>
<body id="pwPopBody">
	<form name="pwChangeFrm" method="post" id="form"
		action="../auth/PwSearchPopup.do"
		onsubmit="return validateForm(this);">
		<div align="center">
		<h2 id="pwPopHead">비밀번호 변경하기</h2>
			<table>
				<tr>
					<td class="pwSearchPopTd">ㆍ 아&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;디&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td class="pwSearchPopTd"><input type="text" name="ID" class="pwSearchPopInput" /></td>
					<td class="pwSearchPopTd"></td>
				</tr>
				<tr>
					<td class="pwSearchPopTd">ㆍ 비 &nbsp;&nbsp;밀 &nbsp;&nbsp;&nbsp;번 &nbsp;&nbsp;&nbsp;호</td>
					<td class="pwSearchPopTd"><input type="password" name="pw" class="pwSearchPopInput"></td>
					<td class="pwSearchPopTd"></td>
				</tr>

				<tr>
					<td class="pwSearchPopTd">ㆍ 비 밀 번 호 확 인 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td class="pwSearchPopTd"><input type="password" name="pwCheck" class="pwSearchPopInput"></td>
					<td class="pwSearchPopTd"></td>
				</tr>
				
				<tr>
					<td class="pwSearchPopTd"></td>
					<td class="pwSearchPopTd"></td>
					<td class="pwSearchPopTd"></td>
				</tr>
			</table>
			<input type="button" name="Change" class="pwChange" onclick="validateForm();" value="변경하기" /> <input type="button" name="close" class="pwChange" value="닫기" onclick="window.close()" />
		</div>
	</form>
</body>
</html>