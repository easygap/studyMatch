<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%
    request.setCharacterEncoding("UTF-8");

    Object attribute = request.getAttribute("profileImages");
    List<String> profileImages = (List<String>) attribute;
    System.out.println("history.jsp : " + profileImages);
    
    Object GroupNames = request.getAttribute("getGroupName");
    List<String> Group_names = (List<String>) GroupNames;
    System.out.println("history.jsp : " + Group_names);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Match History</title>
    <link rel="stylesheet" href="../css/MatchHistorystyle.css">
    <script>
    document.addEventListener('DOMContentLoaded', function() {
        var groupNames = ${Group_names != null ? Group_names : 'null'}; // 서버에서 받은 그룹 이름 저장

        console.log("Group_names:", groupNames);

        // TODO: JavaScript로 groupNames를 이용하여 출력 로직 작성
        // 예시로 콘솔에 출력하는 로직을 작성하였습니다.
        if (groupNames !== null) {
            for (var i = 0; i < groupNames.length; i++) {
                console.log("Group Name " + (i + 1) + ": " + groupNames[i]);
            }
        } else {
            console.log("Group_names 값이 존재하지 않습니다.");
        }
    });
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

                <form name="historyFrm" method="post" action="../match/MatchHistory.do" onsubmit="return validateForm(this);">
                    <p class="matching" id="Matching">매 칭 현 황</p>

					<!-- 현재 내 그룹 -->
                    <div class="MyStudyGruop-Back">
                        <p class="matching" id="group">MY STUDY GROUP</p>
                        <div class="image" align="center">
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

							<% if (Group_names != null && Group_names.size() > 0) { %>
							    <p id="name">
							        <% for (int i = 0; i < Group_names.size(); i++) { %>
							            <%= Group_names.get(i) %>
							            <% if (i < Group_names.size() - 1) { %> <%-- 마지막 요소가 아닌 경우만 공백 추가 --%><% } %>
							        <% } %>
							    </p>
							<% } else { %>
							    <p>Group_names 값이 존재하지 않습니다.</p>
							<% } %>

                            <input type="submit" name="mygroup" id="mygroup" value="  그 룹 게 시 판 이 동  " />
                        </div>
                    </div>

					<!-- 이전에 매칭될뻔한 그룹 -->
                    <div class="MatchHistory">
                        <p class="matching" id="history">Match History</p>
                        <p class="font">매칭 진행되고자 했던 날짜</p>
                        <c:forEach var="img" items="${profileImages}">
                            <img src="${pageContext.request.contextPath}/MyProfile/${img}" alt="Member" name="profile" class="profile"> 
                        </c:forEach>
                        <p class="font">신짱구 한유리 김철수 이훈이</p>
                        <p class="font">매칭 진행되고자 했던 날짜</p>
                        <c:forEach var="img" items="${profileImages}">
                            <img src="${pageContext.request.contextPath}/MyProfile/${img}" alt="Member" name="profile" class="profile"> 
                        </c:forEach>
                        <p class="font">신짱구 한유리 김철수 이훈이</p>
                        <p class="font">매칭 진행되고자 했던 날짜</p>
                        <c:forEach var="img" items="${profileImages}">
                            <img src="${pageContext.request.contextPath}/MyProfile/${img}" alt="Member" name="profile" class="profile"> 
                        </c:forEach>
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
</html>
