<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
String id = (String) request.getParameter("idCheck");
Object result = request.getParameter("idCheck");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idCheck</title>
<script type="text/javascript">
	function result() {
		opener.document.RegistFrm.id.value = document.checkIdFrm.idCheck.value;
		opener.document.RegistFrm.id.readOnly = true;
		window.close();
	}
</script>
</head>
<body>
	<b>ID 중복 확인</b>
	<br />
	<div align="center">
		<form name="checkIdFrm" action="../auth/idCheckAuth.do" method="get"
			onsubmit="return validateForm(this);">
			<table>
				<tr>
					<td>ㆍ아&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;디</td>
					<td><input type="text" name="idCheck" /></td>
					<td><input type="submit" value="중복확인" /></td>
				</tr>
				
				<tr>
					<td></td>
					<td><input type="button" value="아이디 사용하기" onclick="idCheck();" /></td>
					<td></td>
			</table>
			<%
			if (result.equals("1")) {
			%>
			<span style="color: blue">해당 ID는 사용 가능합니다.</span>
			<%
			} else {
			%>
			<span style="color: red">해당 ID는 이미 사용 중입니다.</span>
			<%
			}
			%>
		</form>
	</div>
</body>
</html>