<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

//특정 세션 값 삭제
session.removeAttribute("user");

//모든 세션 값 삭제
session.invalidate();


response.sendRedirect("../board/MainPage.jsp");

%>