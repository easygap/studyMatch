<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>자주 묻는 질문 게시판 - (List)</title>
<meta charset="UTF-8">
<!-- BootStrap css 사용 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<!-- css 가져오기 -->
<link href="/q&aForm.css" rel="stylesheet" type="{ % static " /q&aForm.css" % }" />
<style>
a {
	text-decoration: none;
}
</style>
</head>
<body>
	<!-- Jquery 사용 -->
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
		crossorigin="anonymous"></script>
	<!-- BootStrap javascript 사용 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
	<!-- 네비게이션 바 -->
	<jsp:include page="../layout/Navbar.jsp"></jsp:include>

	<!-- 사이드 바 -->
	<jsp:include page="../layout/Sidebar.jsp"></jsp:include>

	<!-- 코드 시작 -->
	<h2>자주 묻는 질문 게시판 - (List)</h2>

	<!-- 목록 테이블 -->
	<table border="1" width="90%">
		<tr>
			<th width="10%">번호</th>
			<th width="*">제목</th>
			<th width="15%">작성자</th>
			<th width="10%">조회수</th>
			<th width="15%">작성일</th>
			<th width="8%">첨부</th>
		</tr>
		<c:choose>
			<c:when test="${ empty boardLists }">
				<!-- 게시물이 없을 때 -->
				<tr>
					<td colspan="6" align="center">등록된 게시물이 없습니다^^*</td>
				</tr>
			</c:when>
			<c:otherwise>
				<!-- 게시물이 있을 때 -->
				<c:forEach items="${ boardLists }" var="row" varStatus="loop">
					<tr align="center">
						<td>
							<!-- 번호 --> ${ map.totalCount - (((map.pageNum-1) * map.pageSize) + loop.index)}
						</td>
						<td align="left">
							<!-- 제목(링크 --> <a href="../mvcboard/view.do?idx=${ row.idx }">${ row.title }</a>
						</td>
						<td>${ row.name }</td>
						<!-- 작성자 -->
						<td>${ row.postdate }</td>
						<!-- 작성일 -->
						<td>${ row.visitcount }</td>
						<!-- 조회수 -->
						<td>${ row.likecount }</td>
						<!-- 추천수  -->						
						<td>
							<!-- 첨부 파일 --> <c:if test="${ not empty row.ofile }">
								<a
									href="../mvcboard/download.do?ofile=${ row.ofile }&sfile=${ row.sfile }&idx=${ row.idx }">[Down]</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>

	<!-- 하단 메뉴(바로가기, 글쓰기) -->
	<table border="1" width="90%">
		<tr align="center">
			<td>${ map.pagingImg }</td>
			<td width="100"><button type="button"
					onclick="location.href='../mvcboard/write.do';">글쓰기</button></td>
		</tr>
	</table>

	<!-- 검색 폼 -->
	<form method="get">
		<table border="1" width="90%">
			<tr>
				<td align="center"><select name="searchField">
						<option value="title">제목</option>
						<option value="content">내용</option>
				</select> <input type="text" name="searchWord" /> <input type="submit"
					value="검색하기" /></td>
			</tr>
		</table>
	</form>
</body>
</html>