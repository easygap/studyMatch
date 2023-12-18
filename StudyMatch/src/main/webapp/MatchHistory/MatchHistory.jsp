<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");

String img = request.getParameter("img");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Match History</title>
<script src="MatchHistoryScript.js"></script>
<link rel="stylesheet" href="../css/MatchHistorystyle.css">
</head>
<body id="matchbody">

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



	<form name="historyFrm" method="post" action="../match/MatchHistory.do"
		onsubmit="return validateForm(this);">
		<p class="matching" id="Matching">매 칭 현 황</p>

		<div class="MyStudyGruop-Back">
			<p class="matching" id="group">MY STUDY GROUP</p>
				<div class="image" align="center">
				<%
				if(!img.equals("null")){
				%>
				<img src="${pageContext.request.contextPath}/MyProfile/<%= img %>" name="profile" alt="Mem1" class="noMargin"> 
				<img src="${pageContext.request.contextPath}/MyProfile/<%= img %>" name="profile" alt="Mem2" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/<%= img %>" name="profile" alt="Mem3" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/<%= img %>" name="profile" alt="Mem4" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/logo2.jpg" name="profile" alt="Mem5" class="profile">
				<%} else { %>
				<img src="${pageContext.request.contextPath}/MyProfile/logo2.jpg" name="profile" alt="Mem1" class="noMargin"> 
				<img src="${pageContext.request.contextPath}/MyProfile/logo2.jpg" name="profile" alt="Mem2" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/logo2.jpg" name="profile" alt="Mem3" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/logo2.jpg" name="profile" alt="Mem4" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/logo2.jpg" name="profile" alt="Mem5" class="profile">
				<% } %>
				<p class="font">신짱구&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 한유리&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 김철수&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 이훈이</p>
				<input type="submit" name="mygroup" id="mygroup" value="  그 룹 게 시 판 이 동  " />
				</div>
		</div>

		<p></p>
		<div class="MatchHistory">
			<p class="matching" id="history">Match History</p>
			<p class="font">매칭 진행되고자 했던 날짜</p>
				<img src="Your Image1" alt="Mem1" name="profile" class="profile"> 
				<img src="Your Image2" alt="Mem2" name="profile" class="profile"> 
				<img src="Your Image3" alt="Mem3" name="profile" class="profile"> 
				<img src="Your Image4" alt="Mem4" name="profile" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/${imageName}" name="profile" alt="Mem5" class="profile">
				<p class="font">신짱구 한유리 김철수 이훈이</p>
			<p class="font">매칭 진행되고자 했던 날짜</p>
				<img src="Your Image1" alt="Mem1" name="profile" class="profile"> 
				<img src="Your Image2" alt="Mem2" name="profile" class="profile"> 
				<img src="Your Image3" alt="Mem3" name="profile" class="profile"> 
				<img src="Your Image4" alt="Mem4" name="profile" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/${imageName}"name="profile"  alt="Mem5" class="profile">
				<p class="font">신짱구 한유리 김철수 이훈이</p>
			<p class="font">매칭 진행되고자 했던 날짜</p>
				<img src="Your Image1" alt="Mem1" name="profile" class="profile"> 
				<img src="Your Image2" alt="Mem2" name="profile" class="profile"> 
				<img src="Your Image3" alt="Mem3" name="profile" class="profile"> 
				<img src="Your Image4" alt="Mem4" name="profile" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/${imageName}" name="profile" alt="Mem5" class="profile">
				<p class="font">신짱구 한유리 김철수 이훈이</p>
			<p class="font" id="plus">더 보 기..</p>
		</div>
	</form>
	</div>
	</div>
	</div>
	<!-- BootStrap javascript 사용 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
</html>