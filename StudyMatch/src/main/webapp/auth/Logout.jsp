<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

session.invalidate();
response.sendRedirect("../board/MainPage.jsp");
System.out.println(" [ "+ session.getId() + " ] 로그아웃 - session 무효화 완료");
%>