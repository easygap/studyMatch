<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();

// 주소창에서 interest 값을 변수에 저장
String interest = request.getParameter("interest");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주묻는질문</title>

<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />

<style>
body, #wrapper, #page-content-wrapper, .container, .table, thead, tr, th, td, table{
background-color : #F4EAE0 !important;
}

</style>
</head>
<body>
	
	<!-- 코드 시작 -->
	<div class="d-flex" id="wrapper">
		<!-- 네비게이션 사이드 bar -->
		<jsp:include page="../layout/Main.jsp"></jsp:include>

		<!-- 페이지 컨텐츠 -->
		<div id="page-content-wrapper">
			<!-- 네비게이션 상단 bar -->
			<jsp:include page="../layout/Navbar.jsp"></jsp:include>
			<div class="container-fluid container">
				<br /> <br /> <br />
				<!-- 목록 테이블 -->
				<table class="table table-hover">
				<thead>
					<tr>
						<th width="10%" align="center">번호</th>
						<th width="*">제목</th>
						<th width="15%">작성자</th>
						<th width="15%">작성일</th>
						<th width="9%">조회수</th>
						<th width="9%">추천</th>
					</tr>
		
					<c:choose>
						<c:when test="${ empty boardLists }">
							<!-- 게시물이 없을 때 -->
							<tr>
								<td colspan="6" align="center">게시글이 존재하지 않습니다.•͈⌔•͈⑅</td>
							</tr>
						</c:when>
						<c:otherwise>
							<!-- 게시물이 있을 때 -->
							<c:forEach items="${ boardLists }" var="row" varStatus="loop">
								<tr>
									<td style="padding-left: 20px;">
										<!-- 번호 --> ${ map.totalCount - (((map.pageNum-1) * map.pageSize) + loop.index)}
									</td>
									<td>
										<!-- 제목(링크) --> <a href="../board/view.do?num=${ row.board_num }&interest=<%=interest%>">${ row.title }</a>

									</td>
									<td>${ row.id }</td>
									<!-- 작성자 -->
									<td>${ row.post_date }</td>
									<!-- 날짜 -->
									<td style="padding-left: 25px;">${ row.visit_count }</td>
									<!-- 조회수 -->
									<td style="padding-left: 25px;">${ row.like_count }</td>
									<!-- 추천 -->
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					</thead>
				</table>

				

				<!-- 검색 폼 -->
				<form method="get">
					<table class="table">
						<tr>
							<td align="center"><select class="selectpicker" style="height:27px" name="searchField">
									<option value="0">선택</option>
									<option value="bbsTitle">내용</option>
									<option value="userID">작성자</option>
									<option value="userday">작성일</option>
							<input type="text" name="searchWord" /> <input type="submit"
								value="검색하기" /></td>
						</tr>
					</table>
					<input type="text" style="display:none;" name="interest" value="<%=interest%>" />
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
<!-- 푸터 -->
<jsp:include page="../layout/Footer.jsp"></jsp:include>
</html>