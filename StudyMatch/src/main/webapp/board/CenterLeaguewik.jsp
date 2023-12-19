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
<title>내 문의함</title>

<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />

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
<h2>고객센터</h2>
	 <button onclick="location.href='Center_often.jsp'">자주묻는질문</button >
	 <button onclick="location.href='Center.jsp'">1:1 문의</button >
	 <button onclick="location.href='CenterLeaguewik.jsp'">내 문의</button >
          </div>
          <div align="center">

			
				<!-- 목록 테이블 -->
  <table border="1" width="800" bgcolor="#F5F6CE" align="center" cellspacing="0" cellpadding="3"
  bordercolor="#0000000" bordercolordark="#F5F6CE">  	
				<thead>
					<tr>
						<th width="10%" align="center">NO></th>
						<th width="50%">제목</th>
						<th width="15%">작성자</th>
						<th width="15%">작성일</th>
						<th width="9%">답변</th>
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
									<td style="padding-left: 25px;">${ row.like_count }</td>
									<!-- 답변 -->
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					</thead>
				</table>
           
            <!-- 게시글 추가로 들어오는 위치 -->
        </div>

        <!-- 페이지 블럭 -->
         <div align = center>
        <div class="container">
       <div class-"row"></div>
            <button class="before_move" onclick="before()">이전</button>
              
            <button class="next_move" onclick="next()">다음</button>
        </div>          
        </div>
        <div align = center>
        <div class="container">
		<div class="row">
			<form method="post" name="search" action="searchbbs.jsp">
				<table class="pull-right">
					<tr>
						<td><select class="form-control" name="searchField">
								<option value="0">선택</option>
								<option value="bbsTitle">제목</option>
								<option value="userID">작성자</option>
								<option value="userday">작성일</option>
						</select></td>
						<td><input type="text" class="form-control"
							 name="searchText" maxlength="100"></td>
						<td><button type="submit" class="btn btn-success">검색</button></td>
					</tr>
					
				</table>
			</form>	
		</div>	
		</div>
		</div>
		<!-- BootStrap javascript 사용 -->
<
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
    crossorigin="anonymous"></script>

</
</body>

</html>