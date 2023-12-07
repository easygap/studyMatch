<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
long creationTime = session.getCreationTime(); // 최초 요청 시간
String creationTimeStr = dateFormat.format(new Date(creationTime));

long lastTime = session.getLastAccessedTime(); // 마지막 요청 시간
String lastTimeStr = dateFormat.format(new Date(lastTime));
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>