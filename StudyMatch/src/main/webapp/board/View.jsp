<%@ page isELIgnored="false"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.CommentDAO"%>
<%@ page import="model.CommentDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();
String interest = request.getParameter("interest");
String num = request.getParameter("num");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>

<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />

<script>
	function removeCheck() {
		if (confirm("정말 삭제하시겠습니까??") == true) { //확인
			location.href = '../board/list.do?mode=delete&interest=${ param.interest }&num=${ param.num}';
		} else { //취소
			return false;
		}
	}
	// 댓글 수정 버튼
function setEditMode(content, commNum, commId) {
        // 댓글 내용을 textarea에 설정
        document.getElementById("commContent").value = content;

        // 수정할 댓글 번호를 hidden 필드에 설정
        document.getElementById("commNum").value = commNum;

        // "댓글입력" 버튼을 "수정하기"로 변경
        document.getElementById("commSubmitButton").value = "수정하기";

        // "수정하기" 버튼 클릭 시 commEdit.do로 전송
        document.getElementById("commentForm").action = "../board/CommEdit.do";

        // 추가: hidden 필드에 action과 id를 설정
        document.getElementById("commAction").value = "edit";
        document.getElementById("commId").value = commId;
    }

	</script>
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
				<br /> <br /> <br />

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
						<td colspan="5" height="100"><c:if
								test="${not empty dto.img}">
								<img src="../uploads/${dto.img}" width="800" height="550"
									alt="첨부 이미지">
							</c:if> <br />${ dto.content }</td>
					</tr>
					<!-- 하단 메뉴(버튼) -->
					<tr>
						<td colspan="4" align="center">
							<%
							if (request.getParameter("result") != null && request.getParameter("result").equals("Y")) {
							%><button type="button"
								onclick="location.href='../board/Edit.jsp?interest=${ param.interest }&num=${ param.num }&title=${ dto.title }&content=${ dto.content }';">수정하기</button>
							<button type="button" onclick="removeCheck();">삭제하기</button> <%	
 }
 %>
							<button type="button" onclick="location.href='../board/list.do?interest=${ param.interest }';">목록 바로가기</button>
						</td>
					</tr>
				</table>

				<form name="commentForm" id="commentForm" method="post" action="../board/CommWrite.do">
    <table class="table table-striped" style="text-align: center; border: 1px solid #dddddd; width: 100%;">
        <%-- 홀,짝 행 구분 --%>
        <thead>
            <tr>
                <th colspan="3" style="background-color: #eeeeeee; text-align: center;">댓글</th>
            </tr>
        </thead>
        <tbody>
            <% 
            CommentDAO dao = new CommentDAO(); 
            ArrayList<CommentDTO> list = dao.getList(num); 
            ArrayList<Boolean> permissions = (ArrayList<Boolean>) request.getAttribute("permissions"); 
            if (permissions != null) { 
            	for (int i = 0; i < list.size(); i++) { 
            	%>
                <tr>
                    <td style="text-align: left;"><%=list.get(i).getContent()%></td>
                    <td style="text-align: right;"><%=list.get(i).getNickname()%> <%=list.get(i).getCommen_date()%>
                        <%
                            boolean hasPermission = permissions.get(i);
                            if (hasPermission == true) {
                        %>
                            <a href="javascript:setEditMode('<%=list.get(i).getContent()%>', '<%=list.get(i).getCommen_num()%>', '<%=list.get(i).getId()%>');"
                                class="btn">수정</a>
                            <a href="../board/CommEdit.do?action=delete&commNum=<%=list.get(i).getCommen_num()%>&id=<%=list.get(i).getId()%>&num=<%=num%>&interest=<%=interest%>"
                                class="btn">삭제</a>
                    </td>
                </tr>
                <%
                    }
                }
            }
            %>
              <tr>
                <td style="text-align: left; width: 80%;">
                    <textarea type="text" class="form-control" placeholder="댓글을 입력하세요." id="commContent" name="commContent"
                        style="width: 100%;" maxlength="1024"></textarea>
                </td>
                <td style="text-align: center; vertical-align: middle;">
                    <input type="hidden" name="interest" value="<%=interest%>">
                    <input type="hidden" id="num" name="num" value="<%=num%>">
                    <input type="hidden" id="commNum" name="commNum" value="">
                    <input type="hidden" id="commAction" name="action" value="">
                    <input type="hidden" id="commId" name="id" value="">
                   <input type="submit" class="btn btn-sm" id="commSubmitButton" value="댓글입력">
                </td>
            </tr>
        </tbody>
    </table>
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
