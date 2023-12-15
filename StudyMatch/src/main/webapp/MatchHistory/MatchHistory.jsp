<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="MatchViewPort"
	content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="MatchHistorystyle.css">
<title>Match History</title>
<script src="MatchHistoryScript.js"></script>
</head>
<body>
	<form name="historyFrm" method="post" action="../match/MatchHistory.do"
		onsubmit="return validateForm(this);">
		<h1>매 칭 현 황</h1>

		<div class="MyStudyGruop-Back">
			<h2>MY STUDY GROUP</h2>
			<div class="image" align="center">
				<img src="${pageContext.request.contextPath}/MyProfile/20231214_173544822.jpg" alt="Mem1" class="noMargin"> 
				<img src="${pageContext.request.contextPath}/MyProfile/20231214_173624370.jpg" alt="Mem2" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/20231214_173817900.jpg" alt="Mem3" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/20231214_173656184.jpg" alt="Mem4" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/logo2.jpg" alt="Mem5" class="profile">
				<p class="font">신짱구 한유리 김철수 이훈이</p>
			</div>
		</div>

		<p></p>
		<div class="MatchHistory">
			<h2>Match History</h2>
			<h4>매칭 진행되고자 했던 날짜</h4>
				<img src="Your Image1" alt="Mem1" class="profile"> 
				<img src="Your Image2" alt="Mem2" class="profile"> 
				<img src="Your Image3" alt="Mem3" class="profile"> 
				<img src="Your Image4" alt="Mem4" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/${imageName}" alt="Mem5" class="profile">
				<p class="font">신짱구 한유리 김철수 이훈이</p>
			<h4>매칭 진행되고자 했던 날짜</h4>
				<img src="Your Image1" alt="Mem1" class="profile"> 
				<img src="Your Image2" alt="Mem2" class="profile"> 
				<img src="Your Image3" alt="Mem3" class="profile"> 
				<img src="Your Image4" alt="Mem4" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/${imageName}" alt="Mem5" class="profile">
				<p class="font">신짱구 한유리 김철수 이훈이</p>
			<h4>매칭 진행되고자 했던 날짜</h4>
				<img src="Your Image1" alt="Mem1" class="profile"> 
				<img src="Your Image2" alt="Mem2" class="profile"> 
				<img src="Your Image3" alt="Mem3" class="profile"> 
				<img src="Your Image4" alt="Mem4" class="profile"> 
				<img src="${pageContext.request.contextPath}/MyProfile/${imageName}" alt="Mem5" class="profile">
				<p class="font">신짱구 한유리 김철수 이훈이</p>
			<h4>더 보 기..</h4>
		</div>
	</form>
</body>
</html>