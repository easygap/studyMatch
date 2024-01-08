<%@ page isELIgnored="false"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="service.ServiceDAO"%>
<%@ page import="service.ServiceDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
String cp = request.getContextPath();
String num = request.getParameter("num");
int inquiry_num = Integer.parseInt(num);
String sessionID = (String) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매치메이트 고객센터</title>

<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
<link href="../css/upload.css" rel="stylesheet" />

<script>
    function submitForm() {
        document.getElementById("editForm").submit();
    }
</script>
<script>
	function removeCheck() {
		if (confirm("정말 삭제하시겠습니까??") == true) { //확인
			location.href = '../service/ServiceList.do?mode=delete&num=${ dto.inquiry_num }';
		} else { //취소
			return false;
		}
	}
		// 댓글 수정 버튼
	function setEditMode(answerContent, answerId) {
        // 댓글 내용을 textarea에 설정
        document.getElementById("answerContent").value = answerContent;

        // "댓글입력" 버튼을 "수정하기"로 변경
        document.getElementById("answerSubmitButton").value = "수정하기";

        // "수정하기" 버튼 클릭 시 commEdit.do로 전송
        document.getElementById("answerForm").action = "../service/AnswerEdit.do";

        // 추가: hidden 필드에 action과 id를 설정
        document.getElementById("answerAction").value = "edit";
        document.getElementById("answerId").value = answerId;
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
						<td>${ dto.inquiry_num }</td>
						<td>작성자</td>
						<td>${ dto.id }</td>
					</tr>
					<tr>
						<td>문의유형</td>
						<td>${ dto.category_name } > ${ dto.detail_name }</td>
						<td>작성일</td>
						<td>${ dto.post_date }</td>
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
							%>
							<button type="button" class="ViewButton"
								onclick="location.href='../service/ServiceEdit.jsp?&num=${ dto.inquiry_num }&title=${ dto.title }&content=${ dto.content }';">수정하기</button>
							<button type="button" class="ViewButton" onclick="removeCheck();">삭제하기</button>
							<%
							}
							%>
							<button type="button" class="List" onclick="location.href='../service/ServiceList.do';">목록 바로가기</button>
						</td>
					</tr>
				</table>

				<form name="answerForm" id="answerForm" method="post" action="../service/AnswerWrite.do">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd; width: 100%;">
        <%-- 홀,짝 행 구분 --%>
        <thead>
            <tr>
                <th colspan="3" style="background-color: #eeeeeee; text-align: center;">댓글</th>
            </tr>
        </thead>
        <tbody>
            <%
            ServiceDAO dao = new ServiceDAO();
            ArrayList<ServiceDTO> list = dao.getList(inquiry_num);
            dao.close();
            ArrayList<Boolean> permissions = (ArrayList<Boolean>) request.getAttribute("permissions");
            if (permissions != null) {
            	for (int i = 0; i < list.size(); i++) {
            		String answerContent = list.get(i).getAnswer_content();
                    if (answerContent != null && !answerContent.trim().isEmpty()) { 
            	%>
                <tr>
                    <td style="text-align: left;"><%=list.get(i).getAnswer_content()%></td>
                    <td style="text-align: right;"><%=list.get(i).getAnswer_date()%> <%=list.get(i).getAnswer_id()%>
                        <%
                            boolean hasPermission = permissions.get(i);
                            if (hasPermission == true) {
                        %>
                            <a href="javascript:setEditMode('<%=list.get(i).getAnswer_content()%>', '<%=list.get(i).getAnswer_id()%>');"
                                class="btn">수정</a>
                            <a href="../service/AnswerEdit.do?action=delete&answerId=<%=list.get(i).getAnswer_id()%>&answerContent=<%=list.get(i).getAnswer_content()%>
                            &num=<%=list.get(i).getInquiry_num()%>"
                                class="btn">삭제</a>
                    </td>
                </tr>
                <%
                    }
                }
            }
            }
            
            if (sessionID.equals("admin")) {
            %>
              <tr>
                <td style="text-align: left; width: 80%;">
                    <textarea type="text" class="form-control" placeholder="댓글을 입력하세요." id="answerContent" name="answerContent"
                        style="width: 100%;" maxlength="1024"></textarea>
                </td>
                <td style="text-align: center; vertical-align: middle;">
                    <input type="hidden" id="inquiry_num" name="inquiry_num" value="${dto.inquiry_num}">
                    <input type="hidden" id="answerAction" name="action" value="">
                    <input type="hidden" id="answerId" name="answerId" value="">
                   <input type="submit" class="btn btn-sm" id="answerSubmitButton" value="댓글입력">
                </td>
            </tr>
            <% } %>
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
