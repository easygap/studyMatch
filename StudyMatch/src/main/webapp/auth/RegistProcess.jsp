<%@ page import="member.MemberDAO" %>
<%@ page import="member.MemberDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id = request.getParameter("id");
String pw = request.getParameter("pw");
String name = request.getParameter("name");
String nickName = request.getParameter("nickName");
String phone = request.getParameter("phone");
String birth = request.getParameter("birth");
String address = request.getParameter("address");
String Email = request.getParameter("Email");
String job = request.getParameter("job");

MemberDTO dto = new MemberDTO();
dto.setId(id);
dto.setPass(pw);
dto.setName(name);
dto.setNick(nickName);
dto.setPhone(phone);
dto.setBirth(birth);
dto.setAddress(address);
dto.setEmail(Email);
dto.setJob(job);

MemberDAO dao = new MemberDAO(application);
boolean iResult = dao.signUp(dto);
dao.close();

if(iResult == true){
	response.sendRedirect("Login.jsp");
}
%>