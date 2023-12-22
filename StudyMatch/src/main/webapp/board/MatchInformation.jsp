<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashMap"%>
<%
request.setCharacterEncoding("UTF-8");

Object groupImgList = request.getAttribute("img");
List<String> GroupImg = (List<String>) groupImgList;

Object groupNameList = request.getAttribute("name");
List<String> GroupName = (List<String>) groupNameList;

Object groupBirthList = request.getAttribute("birth");
List<String> GroupBirth = (List<String>) groupBirthList;

Object groupJobList = request.getAttribute("job");
List<String> GroupJob = (List<String>) groupJobList;

Object groupInterest1List = request.getAttribute("interest1");
List<String> GroupInterest1 = (List<String>) groupInterest1List;

Object groupInterest2List = request.getAttribute("interest2");
List<String> GroupInterest2 = (List<String>) groupInterest2List;

Object groupInterest3List = request.getAttribute("interest3");
List<String> GroupInterest3 = (List<String>) groupInterest3List;

System.out.println(" JSP에서 GroupImg : " + GroupImg
		+ " / GroupName : " + GroupName + " / GroupBirth : " + GroupBirth
		+ " / GroupJob : " + GroupJob + " / GroupInterest1 : " + GroupInterest1
		+ " / GroupInterest2 : " + GroupInterest2 + " / GroupInterest3 : " + GroupInterest3);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매칭 정보</title>
<link rel="stylesheet" href="../css/MainPage.css">
</head>
<body>
	<!-- import 끝 -->
	<!-- 본문 -->
	<div style="position: absolute; width: 1280px; height: 1300px">
		<div class="wrap"
			style="position: relative; width: 1280px; height: 100px;">
			<p class="matchfont" id="newmatch">매칭 정보</p>
			<div class="jumbotron" style="text-align: left;">
				<div id="NewMatch">
					<p class="lead">프로필</p>
					<!-- 그룹1 매칭 -->
					<div class="Match1" align="center">
						<p>이름</p>
						<% if( GroupImg != null && !GroupImg.isEmpty()) { 
									for(int i = 0; i < GroupImg.size(); i++) {
										if(GroupImg.get(i) != null && !GroupImg.get(i).isEmpty()) { %>
						<img src="${pageContext.request.contextPath}/MyProfile/<%=GroupImg.get(i)%>" name="profile" alt="Mem" class="profile">
						<% 	} else { %>
						<img src="${pageContext.request.contextPath}/MyProfile/default.png" name="profile" alt="Default" class="profile">

						<% } } } else { %>
						<p>No images available</p>
						<% } %>
						<br />
						<% if( GroupName != null && !GroupName.isEmpty() ) { %>
						<p id="name">
							<% 
									for(int i = 0; i < GroupName.size(); i++) { %>
							<%=GroupName.get(i)%>
							<% if (i < GroupName.size() - 1) { %>
							<%-- 마지막 요소가 아닌 경우만 공백 추가 --%>
							<% } } }%>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>