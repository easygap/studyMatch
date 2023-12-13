<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");

String idCheck = request.getParameter("id");
String IDcheck = request.getParameter("idCheck");
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
					<%
					if(idCheck.equals("null")){
					%>
					<td><input type="text" name="idCheck" /></td>
					<%} else{ %>
					<td><input type="text" name="idCheck" value="<%= idCheck %>" /></td>
					<%} %>
					<td><input type="submit" name="idChecked" value="중복확인"></td>
				</tr>

			</table>
			<%
			if(!idCheck.equals("null")){
			if (IDcheck.equals("Y")) {
			%>
			<span style="color: blue">해당 ID는 사용 가능합니다.</span> <br/>
			<%
			} else if(IDcheck.equals("N")){
			%>
			<span style="color: red">해당 ID는 이미 사용 중입니다.</span><br/>
			<%
			}}
			%>
			
			<input type="button" value="확인" onclick="window.close()" />
		</form>
	</div>
</body>
</html>