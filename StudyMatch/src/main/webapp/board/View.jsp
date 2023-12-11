<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>

<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />

</head>
<body>
	
	<!-- 코드 시작 -->
	<div class="d-flex" id="wrapper">
		<!-- 네비게이션 바 -->
		<jsp:include page="../layout/Main.jsp"></jsp:include>

		<!-- 페이지 컨텐츠 -->
		<div id="page-content-wrapper">
			<!-- 네비게이션 바 -->
			<jsp:include page="../layout/Navbar.jsp"></jsp:include>
			<div class="container-fluid">
				<br />
				<br />
				<br />
				
				<!-- View.jsp 코드 시작 -->
				
				<table class="table"
                style="text-align: center; border: 1px solid #dddddd">
					<colgroup>
						<col width="15%" />
						<col width="35%" />
						<col width="15%" />
						<col width="*" />
					</colgroup>

					<!-- 게시글 정보 -->
					<tr>
						<td>글 번호</td>
						<td>${ dto.board_num }</td>
						<td>작성자</td>
						<td>${ dto.id }</td>
					</tr>
					<tr>
						<td>작성일</td>
						<td>${ dto.post_date }</td>
						<td>조회수</td>
						<td>${ dto.visit_count }</td>
					</tr>
					<tr>
						<td>제목</td>
						<td colspan="3">${ dto.title }</td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="5" height="100">${ dto.content }</td>
					</tr>

					<!-- 하단 메뉴(버튼) -->
					<tr>
						<td colspan="4" align="center">
						<% if ((request.getParameter("result")).equals("N")) { %>
							<button type="button"
								onclick="location.href='../board/pass.do?mode=edit&idx=${ param.num }';">수정하기</button>
							<button type="button"
								onclick="location.href='../board/list.do?mode=delete&interest=${ param.interest }&num=${ param.num}';">삭제하기</button>
						<% } %>
							<button type="button"
								onclick="location.href='../board/list.do?interest=${ param.interest }';">목록 바로가기</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
<!-- 푸터 -->
		<jsp:include page="../layout/Footer.jsp"></jsp:include>
</html>