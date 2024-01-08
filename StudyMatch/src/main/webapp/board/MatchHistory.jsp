<%@ page import="model.GroupDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%
    request.setCharacterEncoding("UTF-8");

	GroupDTO dto = (GroupDTO)request.getAttribute("dto");

	// 현재 내가 속한 그룹 정보
    Object Img = request.getAttribute("img");
    List<String> profileImages = (List<String>) Img;
    
    Object Name = request.getAttribute("name");
    List<String> Group_names = (List<String>) Name;
    
    // 이전에 내가 가입했다가 탈퇴한 그룹 정보
    Object previousImg = request.getAttribute("previousImg");
    List<String> PreviousImg = (List<String>) previousImg;
    
    Object previousName = request.getAttribute("previousNames");
    List<String> PreviousName = (List<String>) previousName;
    
    String Group_num = "";
    if(dto != null){
    	Group_num = dto.getGroup_Num();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Match History</title>
    <!-- Favicon-->
	<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
    <link rel="stylesheet" href="../css/MatchHistorystyle.css">
    <script src="../js/MatchHistoryScript.js"></script>
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
			<div id="matchHistory">
				<form name="historyFrm" method="post" action="../Match/MatchLeaving.do" onsubmit="return validateForm(this);">
					<p class="matching" id="Matching">매 칭 현 황</p>
					
						<!-- 현재 내 그룹 -->
						<div class="MyStudyGruop-Back">
							<p class="matching" id="group">MY STUDY GROUP</p>
							<div class="image" align="center">
								<!-- 프로필 사진 -->
								<% if (profileImages != null && !profileImages.isEmpty()) {
									for (int i = 0; i < profileImages.size(); i++) { 
										if (profileImages.get(i) != null && !profileImages.get(i).isEmpty()) { %>
										<img src="${pageContext.request.contextPath}/MyProfile/<%=profileImages.get(i)%>" name="profile" alt="Mem" class="profile">
								<% } else { %>
										<img src="${pageContext.request.contextPath}/MyProfile/default.png" name="profile" alt="Default" class="profile">
								<% } } } else { %>
								<p>그룹 정보의 프로필 이미지 값이 조회되지 않았습니다.</p>
								<% } %>

								<!-- 그룹원 이름 -->
								<% if( Group_names != null && !Group_names.isEmpty()) { %>
								<p id="name">
									<% for (int i = 0; i < Group_names.size(); i++) { %>
									<%=Group_names.get(i)%>
									<% if (i < Group_names.size() - 1) { %>
									<%-- 마지막 요소가 아닌 경우만 공백 추가 --%>
									<% } %>
									<% } %>
								</p>
								<input type="button" name="leaving" id="mygroup" value="  그 룹 탈 퇴 하 기  " onclick="Leaving()" />
								<% } else { %>
								<p>그룹 정보의 이름 값이 조회되지 않았습니다.</p>
								<% } %>

								<input type="text" style="display: none;" name="groupNum" value="<% if(Group_num != null && !Group_num.equals("")) { out.print(Group_num);  } %>" />
							</div>
						</div>

						<!-- 이전에 매칭될뻔한 그룹 -->
						<div class="MatchHistory">
						<p class="matching" id="history">Match History</p>
							<!-- 프로필 사진 -->
							<% if (PreviousImg != null && !PreviousImg.isEmpty()) {
									for (int i = 0; i < PreviousImg.size(); i++) { 
										if (PreviousImg.get(i) != null && !PreviousImg.get(i).isEmpty()) { %>
							<img src="${pageContext.request.contextPath}/MyProfile/<%=PreviousImg.get(i)%>" name="profile" alt="Mem" class="profile">
							<% } else { %>
							<img src="${pageContext.request.contextPath}/MyProfile/default.png" name="profile" alt="Default" class="profile">
							<% } } } else { %>
							<p>이전 그룹의 프로필 사진 값이 존재하지 않았습니다.</p>
							<% } %>

							<!-- 그룹원 이름 -->
							<% if( PreviousName != null && !PreviousName.isEmpty() ) { %>
								<p id="PreviousName"> 
								<% for(int i = 0; i < PreviousName.size(); i++) { %>
									<%=PreviousName.get(i)%> 
									<% if (i < PreviousName.size() - 1) { %> <%-- 마지막 요소가 아닌 경우만 공백 추가 --%><% } %> 
								<% } %></p>
								<% } else { %>
										<p>이전 그룹 정보의 이름 값이 조회되지 않았습니다.</p>
								<% } %>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- BootStrap javascript 사용 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
            crossorigin="anonymous"></script>
</body>
<!-- 푸터 -->
<jsp:include page="../layout/Footer.jsp"></jsp:include>
</html>
