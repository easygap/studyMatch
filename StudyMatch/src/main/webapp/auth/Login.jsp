<%@ page import ="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h1> 로그인 </h1>

		<form action="loginProcess.jsp" method= "post">
		<table>
			<tr>
				<td>
					<label for= "loginID"> 아이디 </label>
				</td>
				<td>
					<input type= "text" name="loginID" id="loginID">
				</td>
			</tr>
			<tr>
				<td>
					<label for= "loginPW"> 비밀번호 </label>
				</td>
				<td>
					<input type= "password" name="loginPW" id="loginPW">
				</td>
			</tr>	
		</table>
			<button type="submit"> 로그인 </button>	
		</form>

		${not empty alertScript ? alertScript : ''}
</body>
</html>