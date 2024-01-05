<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");


String idSearch = request.getParameter("id");

String nullID = "N";
if(request.getParameter("nullID") != null)
	nullID = request.getParameter("nullID");

String pwSearch = request.getParameter("pwId");

String nullPW = "N";
if(request.getParameter("nullPW") != null)
	nullPW = request.getParameter("nullPW");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID & PW Search</title>
<link href="../css/Regist.css" rel="stylesheet"/>
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
	
	
	function certifi() {
		if (document.pwSearchFrm.pwId.value == "") {
			alert("아이디를 입력해 주세요.");
			document.pwSearchFrm.pwId.focus();
		} else if (document.pwSearchFrm.pwPhone.value == "") {
			alert("연락처를 입력해 주세요.");
			document.pwSearchFrm.pwPhone.focus();
			return false;
		} else if(document.pwSearchFrm.pwBirth.value == ""){
			alert("생년월일을 입력해 주세요.");
			return false;
		}
	}
	
	function winopen() {
		var width = 500;
	    var height = 300;
	    var left = (screen.width - width) / 2;
	    var top = (screen.height - height) / 2;
		
		window.open("../auth/PwSearchPopup.jsp?id=" + "<%= pwSearch%>" , "", "width=" + width + ", height=" + height + ", left=" + left + ", top=" + top);
	}
</script>
</head>
<body id="idpwSerBody">
<!-- 코드 시작 -->
    <div class="d-flex" id="wrapper">
        <!-- 네비게이션 바 -->
        <jsp:include page="../layout/Main.jsp"></jsp:include>

        <!-- 페이지 컨텐츠 -->
        <div id="page-content-wrapper">
            <!-- 네비게이션 바 -->
            <jsp:include page="../layout/Navbar.jsp"></jsp:include>
            <div class="container-fluid">
                <br /> <br /> <br />
		<h2 align="center" id="idpwsear">아이디/비밀번호 찾기</h2>	<p style="font-size:14px;" align="center">가입한 아이디와 비밀번호를 잊으셨습니까?</p>
    <div id="parent">            
	
	<div id="idSearch">
	<form name="IdSearchFrm" method="post" action="../auth/IdSearch.do" onsubmit="return validateForm(this);">	
		<h3 align="center" id="idsearch">아 이 디 찾 기</h3>
			<table>
				<tr>
					<td class="idpwSearchTd">ㆍ 이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td class="idpwSearchTd"><input type="text" class="idpwSearchInput" name="idName" /></td>
					<td class="idpwSearchTd"></td>
				</tr>

				<tr>
					<td class="idpwSearchTd">ㆍ 연&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;락&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;처&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td class="idpwSearchTd"><input type="text" name="idPhone" class="idpwSearchInput"  placeholder="'-' 포함하여 입력해 주세요." />
					 	<input type="submit" class="IdPwSearch" name="idCertified" value="인증하기" /></td>
					<td class="idpwSearchTd"></td>
				</tr>
				
				<tr>
						<td class="idpwSearchTd">ㆍ 아&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;디&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td class="idpwSearchTd">
						<% if(idSearch != null){ 
						if(nullID.equals("N")) {%>
						<span style="color:blue; font:bold;">[  <%= idSearch %>  ]</span>
						<% } } else if(nullID.equals("Y")){ %>
						<span style="color:red; font:bold;">아이디 정보가 없습니다. </span>
						<% } %>
						</td>
				</tr>
				<tr>
						<td class="idpwSearchTd"></td>
						<td class="idpwSearchTd"></td>
						<td class="idpwSearchTd"></td>
				</tr>
				<tr>
						<td class="idpwSearchTd"></td>
						<td class="idpwSearchTd"></td>
						<td class="idpwSearchTd"></td>
				</tr>
				
				<tr>
					<td class="idpwSearchTd"></td>
					<td class="idpwSearchTd"><button type="button" class="login" name="Login" onclick="location.href='Login.jsp';">로 그 인 하 기</button></td>
					<td class="idpwSearchTd"></td>
				</tr>
			</table>
	</form>
	</div>
	<div id="pwSearch">
		<form name="pwSearchFrm" method="post" action="../auth/PwSearch.do" onsubmit="validateForm(this)" >
		<h3 align="center" id="pwsearch">비 밀 번 호 찾 기</h3>
		<table>
			<tr>
				<td class="idpwSearchTd">ㆍ 아&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;디&nbsp;&nbsp;&nbsp;</td>
				<td class="idpwSearchTd"><input type="text" name="pwId" class="idpwSearchInput"/></td>
				<td class="idpwSearchTd"></td>
			</tr>
			<tr>
				<td class="idpwSearchTd">ㆍ 연&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;락&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;처&nbsp;&nbsp;&nbsp;</td>
				<td class="idpwSearchTd"><input type="text" name="pwPhone" class="idpwSearchInput" placeholder="'-' 포함하여 입력해 주세요." /></td>
				<td class="idpwSearchTd"></td>
			</tr>
			
			<tr>
				<td class="idpwSearchTd">ㆍ 생 &nbsp;년 &nbsp;&nbsp;월 &nbsp;&nbsp;일</td>
				<td class="idpwSearchTd"><input type="text" name="pwBirth" class="idpwSearchInput" placeholder="ex)20000101" />
					<button type="submit" class="IdPwSearch" name="pwCertified" onclick="certifi()">인증하기</button></td>
				<td class="idpwSearchTd"></td>
			</tr>
			
			<tr>
				<td class="idpwSearchTd"></td>
				<td class="idpwSearchTd">
						<% if(pwSearch != null){ 
						if(nullPW.equals("N")){%>
						<span style="color:blue; font:bold;">[  회원 정보가 확인 되었습니다. <br/> 비밀번호를 변경해 주세요.  ]</span>
						<br/>
						<button type="button" class="PwChange" name="pwChange" onclick="winopen()">비밀번호 변경하기</button>
						<% } else if(nullPW.equals("Y")){ %>
						<span style="color:red; font:bold;">회원 정보가 없습니다. </span>
						<% }} %>
						</td>
				<td class="idpwSearchTd"></td>
				<td class="idpwSearchTd"></td>
			</tr>
			<tr>
				<td class="idpwSearchTd"></td>
				<td class="idpwSearchTd"></td>
				<td class="idpwSearchTd"></td>
			</tr>
			<tr>
				<td class="idpwSearchTd"></td>
				<td class="idpwSearchTd"></td>
				<td class="idpwSearchTd"></td>
			</tr>
				
			<tr>
				<td class="idpwSearchTd"></td>
				<td class="idpwSearchTd"><button type="button" class="login" name="Login" onclick="location.href='Login.jsp';">로 그 인 하 기</button></td>
				<td class="idpwSearchTd"></td>
			</tr>
		</table>
		</form>
	</div>
	</div>
	</div>
	</div>
	</div>
	 <!-- BootStrap javascript 사용 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
            crossorigin="anonymous"></script>
</body>
<jsp:include page="../layout/Footer.jsp"></jsp:include>
</html>