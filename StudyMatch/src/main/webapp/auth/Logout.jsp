<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String id = (String) session.getAttribute("id");
session.invalidate();
response.sendRedirect("../board/MainPage.jsp");
System.out.println(" [ "+ id + " ] 로그아웃 - session 무효화 완료");
%>