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

String firstGroup = request.getParameter("firstGroup");

Object groupImgList = request.getAttribute("img");
List<String> GroupImg = (List<String>) groupImgList;

Object groupNameList = request.getAttribute("name");
List<String> GroupName = (List<String>) groupNameList;

Object groupAgeList = request.getAttribute("Age");
List<String> GroupAge = (List<String>) groupAgeList;


Object groupJobList = request.getAttribute("job");
List<String> GroupJob = (List<String>) groupJobList;

Object groupInterest1List = request.getAttribute("interest1");
List<String> GroupInterest1 = (List<String>) groupInterest1List;

Object groupInterest2List = request.getAttribute("interest2");
List<String> GroupInterest2 = (List<String>) groupInterest2List;

Object groupInterest3List = request.getAttribute("interest3");
List<String> GroupInterest3 = (List<String>) groupInterest3List;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매칭 정보</title>
<link rel="stylesheet" href="../css/MatchInfor.css">
</head>
<body id="inforBody">
	<form action="/board/Match1.do" name="group" method="post">
		<!-- import 끝 -->
		<!-- 본문 -->
		<div>
			<div class="wrap">
				<p class="matchfont" id="newmatch">매칭 정보</p>
				<div class="jumbotron">
					<div id="Match1Group"> 
						<!-- 그룹1 매칭 -->
						<div class="Match1">
							<div class="groupImg">
								<% if (GroupImg != null && !GroupImg.isEmpty()) {
									for (int i = 0; i < GroupImg.size(); i++) {
										if (GroupImg.get(i) != null && !GroupImg.get(i).isEmpty()) { %>
								<img src="${pageContext.request.contextPath}/MyProfile/<%=GroupImg.get(i)%>" name="profile" alt="Mem" class="profile">
								<% } else { %>
								<img src="${pageContext.request.contextPath}/MyProfile/default.png" name="profile" alt="Default" class="profile">
								<% } } } else { %>
								<p>No images available</p>
								<% } %>
							</div>
							<div class="groupInfo">
								<div id="info">
									<p class="Info">
									<% for (int i = 0; i < GroupName.size(); i++) { %>
										<% if (GroupName != null && !GroupName.isEmpty()) { %>
										<br/>
										성함 : <%=GroupName.get(i)%><br/> <br/>
										나이 : <%=GroupAge.get(i)%>대<br/> <br/>
										직업 : <%=GroupJob.get(i)%><br/> <br/>
										관심사 : <%=GroupInterest1.get(i)%> / <%=GroupInterest2.get(i)%> / <%=GroupInterest3.get(i)%><br/><br/><br/><br/><br/><br/><br/>
										<%-- 마지막 요소가 아닌 경우만 공백 추가 --%>
									</p>
									<% } else { %>
									<p class="Info">전달된 이름 정보가 없음.</p>
									<% } }%> 
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>