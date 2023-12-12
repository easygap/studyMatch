<%@ page import="member.MemberDTO" %>
<%@ page import="member.MemberDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");


String idSearch = request.getParameter("id");

String nullID = "N";
if(request.getParameter("nullID") != null)
	nullID = request.getParameter("nullID");

String pwIdSearch = request.getParameter("pwId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID & PW Search</title>
<script>
	function validateForm(form){
		if(form.idName.value == ""){
			alert("이름을 입력해 주세요.");
			form.idName.focus();
		}else if(form.idPhone.value == ""){
			alert("연락처를 입력해 주세요.");
			form.idPhone.focus();
		}
	}
	
	function winopen(){
		if(document.pwSearchFrm.pwId.value == ""){
			alert("아이디를 입력해 주세요.");
			document.pwSearchFrm.pwId.focus();
		}else if(document.pwSearchFrm.pwPhone.value == ""){
			alert("연락처를 입력해 주세요.");
			document.pwSearchFrm.pwPhone.focus();
		}else if(document.pwSearchFrm.pwCertified.value != "pwCertified"){
			alert("인증을 먼저 진행해 주세요.");
		}
		else{
			window.open("../auth/PwSearchPopup.do?userid=" + document.pwSearchFrm.pwId.value, "" ,"width=500, height=300");
		}
	}
</script>
<style>
.parent {
	width: 90%;
    margin: 10px auto;
    display: flex;
    }
.id {
    flex:1;
    width:35%;
    margin: 0px 5%;
}
.pw{
    flex:1;
    margin: 0px 5%;
    width:35%;
}
</style>
</head>
<body>
<h2>아이디/비밀번호 찾기</h2>	<p style="font-size:14px;">가입한 아이디와 비밀번호를 잊으셨습니까?</p>
	<div class = "parent">
	<div class = "id">
	<form name="IdSearchFrm" method="post" action="../auth/IdSearch.do" onsubmit="return validateForm(this);">	
		<h3>아 이 디 찾 기</h3>
			<table>
				<tr>
					<td>ㆍ 이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" name="idName" value="<c:if test='${ not empty idSearch }'>${idSearch}</c:if>" /></td>
					<td></td>
				</tr>

				<tr>
					<td>ㆍ 연&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;락&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;처&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><input type="text" name="idPhone" placeholder="'-' 포함하여 입력해 주세요." />
					 	<input type="submit" name="idCertified" value="인증하기" /></td>
					<td></td>
				</tr>
				
				<tr>
						<td>ㆍ 아&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;디&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>
						<% if(idSearch != null){ 
						if(nullID.equals("N")) {%>
						<span style="color:blue; font:bold;">[  <%= idSearch %>  ]</span>
						<% } } else if(nullID.equals("Y")){ %>
						<span style="color:red; font:bold;">아이디 정보 없음 </span>
						<% } %>
						</td>
				</tr>
				<tr>
						<td></td>
						<td></td>
						<td></td>
				</tr>
				<tr>
						<td></td>
						<td></td>
						<td></td>
				</tr>
				
				<tr>
					<td></td>
					<td><button type="button" name="Login" onclick="location.href='Login.jsp';">로그인 하기</button></td>
					<td></td>
				</tr>
			</table>
	</form>
	</div>
	<div class="pw">
		<form name="pwSearchFrm" method="post" action="../auth/PwSearch.do" onsubmit="validateForm(this)" >
		<h3>비 밀 번 호 찾 기</h3>
		<table>
			<tr>
				<td>ㆍ 아&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;디&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input type="text" name="pwId"/></td>
				<td></td>
			</tr>
			<tr>
				<td>ㆍ 연&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;락&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;처&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td><input type="text" name="pwPhone" placeholder="'-' 포함하여 입력해 주세요." />
					<button type="submit" name="pwCertified">인증하기</button></td>
				<td></td>
			</tr>
			
			<tr>
				<td></td>
				<td>
						<% if(pwIdSearch != null){ %>
						<span style="color:blue; font:bold;">[  회원 정보가 확인 되었습니다. <br/> 비밀번호를 변경해 주세요.  ]</span>
						<% } else { %>
						<span style="color:red; font:bold;">[ 회원 정보 없음 ]</span>
						<% } %>
						</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td><button type="button" name="pwCertified" onclick="winopen()">비밀번호 변경하기</button></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
				
			<tr>
				<td></td>
				<td><button type="button" name="Login" onclick="location.href='Login.jsp';">로그인 하기</button></td>
				<td></td>
			</tr>
		</table>
		</form>
	</div>
	</div>
	
</body>
</html>