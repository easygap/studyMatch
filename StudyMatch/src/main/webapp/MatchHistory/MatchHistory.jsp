<%@ page import="model.GroupDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%
    request.setCharacterEncoding("UTF-8");

	GroupDTO dto = (GroupDTO)request.getAttribute("dto");

    Object Img = request.getAttribute("img");
    List<String> profileImages = (List<String>) Img;
    
    Object Name = request.getAttribute("name");
    List<String> Group_names = (List<String>) Name;
    
    String Group_num = "";
    if(dto != null){
    	Group_num = dto.getGroup_Num();
    }

    System.out.println("history.jsp : " + profileImages + " / " + Group_names + " / " + Group_num);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Match History</title>
    <link rel="stylesheet" href="../css/MatchHistorystyle.css">
    <script>
    function validateForm(form) {
        return true;
    }
    
    function Leaving() {
		if (confirm("정말 그룹을 탈퇴 하시겠습니까?") == true) {
			alert("그룹 탈퇴가 완료되었습니다.");
			document.historyFrm.submit();
		} else {
			// 사용자가 취소를 선택한 경우 아무 동작 없음
		}
	}
    
    </script>
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
							<p>No images available</p>
							<% } %>

							<!-- 그룹원 이름 -->
							<p id="name">
								<% if (Group_names != null && Group_names.size() > 0) {
									for (int i = 0; i < Group_names.size(); i++) {
										Group_names.get(i); %>
									<% if (i < Group_names.size() - 1) { %> <%-- 마지막 요소가 아닌 경우만 공백 추가 --%>
							</p>
							<% } } } else { %>
							<p>Group_names 값이 존재하지 않습니다.</p>
							<% } %>

							<input type="text" style="display: none;" name="groupNum" value="<% if(Group_num != null && !Group_num.equals("")) { out.print(Group_num);  } %>" />
							<input type="button" name="leaving" id="mygroup" value="  그 룹 탈 퇴 하 기  " onclick="Leaving()" />

						</div>
					</div>

					<!-- 이전에 매칭될뻔한 그룹 -->
					<div class="MatchHistory">
						<p class="matching" id="history">Match History</p>
						<p class="font">매칭 진행되고자 했던 날짜</p>
						<c:forEach var="img" items="${profileImages}">
							<c:choose>
								<c:when test="${not empty img}">
									<img src="${pageContext.request.contextPath}/MyProfile/${img}" name="profile" alt="Member" class="profile">
								</c:when>
								<c:otherwise>
									<img src="${pageContext.request.contextPath}/MyProfile/default.png" name="profile" alt="Default" class="profile">
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${empty profileImages}">
							<p>No images available</p>
						</c:if>
						<p class="font">신짱구 한유리 김철수 이훈이</p>
						<p class="font">매칭 진행되고자 했던 날짜</p>
						<c:forEach var="img" items="${profileImages}">
							<c:choose>
								<c:when test="${not empty img}">
									<img src="${pageContext.request.contextPath}/MyProfile/${img}" name="profile" alt="Member" class="profile">
								</c:when>
								<c:otherwise>
									<img src="${pageContext.request.contextPath}/MyProfile/default.png" name="profile" alt="Default" class="profile">
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${empty profileImages}">
							<p>No images available</p>
						</c:if>
						<p class="font">신짱구 한유리 김철수 이훈이</p>
						<p class="font">매칭 진행되고자 했던 날짜</p>
						<c:forEach var="img" items="${profileImages}">
							<c:choose>
								<c:when test="${not empty img}">
									<img src="${pageContext.request.contextPath}/MyProfile/${img}" name="profile" alt="Member" class="profile">
								</c:when>
								<c:otherwise>
									<img src="${pageContext.request.contextPath}/MyProfile/default.png" name="profile" alt="Default" class="profile">
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${empty profileImages}">
							<p>No images available</p>
						</c:if>
						<p class="font" id="plus">더 보 기..</p>
					</div>
				</form>
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
