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
</script>
</head>
<body>
	<b>ID 중복 확인</b>
	<br />
	<div align="center">
		<form name="checkIdFrm" action="../auth/idCheckAuth.do" method="get" onsubmit="return validateForm(this);">
			<table>
				<tr>
					<td>ㆍ아&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;디 &nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" name="idCheck" value="<c:if test='${not empty id}'>${id}</c:if>" /></td>
					<td><input type="submit" name="idCheck" value="중복확인"></td>
				</tr>

			</table>
			<%
			if (result.equals("1")) {
			%>
			<span style="color: blue">해당 ID는 사용 가능합니다.</span> <br/>
			<%
			} else {
			%>
			<span style="color: red">해당 ID는 이미 사용 중입니다.</span><br/>
			<%
			}
			%>
			
			<input type="button" value="확인" onclick="window.close()" />
		</form>
	</div>
</body>
</html>