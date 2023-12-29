<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");

String nickCheck = request.getParameter("nick");
String NICKcheck = request.getParameter("nickCheck");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NickName Check</title>
<link href="../css/Regist.css" rel="stylesheet"/>
</head>
<body id="CheckBody">
	
	
	<div align="center">
	<h2>닉네임 중복 확인</h2>
	<br />
		<form name="checkNickFrm" action="../auth/nickCheckAuth.do" method="get" onsubmit="return validateForm(this);">
			<table>
				<tr>
					<td>ㆍ 닉&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 네&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 임&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<%
					if(nickCheck.equals("null")){
					%>
					<td><input type="text" name="nickCheck" /></td>
					<%} else{ %>
					<td><input type="text" name="nickCheck" value="<%= nickCheck %>" /></td>
					<%} %>
					<td><input type="submit" name="nickChecked" class="NickCheck" value="중 복 확 인"></td>
				</tr>

			</table>
			<%
			if(!nickCheck.equals("null")){
			if (NICKcheck.equals("Y")) {
			%>
			<span style="color: blue">해당 닉네임은 사용 가능합니다.</span> <br/>
			<%
			} else if(NICKcheck.equals("N")){
			%>
			<span style="color: red">해당 닉네임은 이미 사용 중입니다.</span><br/>
			<%
			}}
			%>
			<br/> <br/>
			<input type="button" value="확인" class="NickCheck" onclick="window.close()" />
		</form>
	</div>
</body>
</html>