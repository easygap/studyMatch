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
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
<link href="../css/Regist.css" rel="stylesheet"/>
</head>
<body id="idCheckBody">
	
	
	<div align="center">
	<h2 id="idCheckHead">ID 중복 확인</h2>
	<br />
		<form name="checkIdFrm" action="../auth/idCheckAuth.do" method="get" onsubmit="return validateForm(this);">
			<table>
				<tr>
					<td class="idCheckTd">ㆍ아&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;디 &nbsp;&nbsp;&nbsp;&nbsp;</td>
					<%
					if(idCheck.equals("null")){
					%>
					<td class="idCheckTd"><input type="text" class="idCheckInput" name="idCheck" /></td>
					<%} else{ %>
					<td class="idCheckTd"><input type="text" class="idCheckInput" name="idCheck" value="<%= idCheck %>" /></td>
					<%} %>
					<td class="idCheckTd"><input type="submit" name="idChecked" class="IdCheck" value="중 복 확 인"></td>
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
			<br/> <br/>
			<input type="button" value="확인" class="IdCheck" onclick="window.close()" />
		</form>
	</div>
</body>
</html>