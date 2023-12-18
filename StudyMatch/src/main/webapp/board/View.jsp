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
<!-- 댓글 내용 불러오기 -->
<script>
	function populateTextarea(commentContent) {
		document.getElementById("commContent").value = commentContent;
	}
</script>

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
							<button type="button"
								onclick="location.href='../board/list.do?interest=${ param.interest }';">목록
								바로가기</button>
						</td>
					</tr>
				</table>

				<form method="post" id="commFrm">
					<table class="table table-striped"
						style="text-align: center; border: 1px solid #dddddd">
						<%-- 홀,짝 행 구분 --%>
						<thead>
							<tr>
								<th colspan="3"
									style="background-color: #eeeeeee; text-align: center;">댓글</th>
							</tr>
						</thead>
						<tbody>
							<%
							CommentDAO dao = new CommentDAO();
							ArrayList<CommentDTO> list = dao.getList(num);

							for (int i = list.size() - 1; i >= 0; i--) {
								CommentDTO comment = list.get(i);
							%>
							<tr>
    <td style="text-align: left;"><%=comment.getContent()%></td>
    <td style="text-align: right;"><%=comment.getId()%> <%=comment.getCommen_date()%>
       <a href="javascript:void(0);" onclick="populateTextareaAndForm('<%=comment.getContent()%>', '<%=comment.getCommen_num()%>');" class="btn">수정</a>
        <a href="../board/CommEdit?action=delete&commNum=<%=comment.getCommen_num()%>&id=<%=comment.getId()%>&num=<%=num%>&interest=<%=interest%>" class="btn">삭제</a>    </td>

							<%
							}
							%>

							<%
							String buttonText = "댓글입력";
							CommentDTO dto = new CommentDTO();
							%>
							<tr>
								<td><textarea type="text" class="form-control"
										placeholder="댓글을 입력하세요." id="commContent" name="commContent"
										style="width: 100%;" maxlength="1024"></textarea></td>
								<td style="text-align: left;"></td>
							</tr>
						</tbody>
					</table>
					<input type="hidden" name="interest" value="<%=interest%>">
    <input type="hidden" id="num" name="num" value="<%=num%>">
    <input type="hidden" id="commNum" name="commNum" value="">
    <input type="button" class="btn" id="submitButton" value="<%= buttonText %>" onclick="submitFrm()">
    <input type="hidden" name="isModified" id="isModified" value="<%= dto.isModified() %>">
				</form>
<%
    if (dto.isModified()) {
%>
   <form method="post" id="modifiedCommFrm" name="modifiedCommFrm">
       <input type="hidden" name="interest" value="<%=interest%>">
        <input type="hidden" id="num" name="num" value="<%=num%>">
        <input type="hidden" name="commContent" id="modifiedCommContent" value="">
        <input type="hidden" name="commNum" id="modifiedCommNum" value="">
        <input type="button" class="btn" id="modifiedSubmitButton" value="수정하기" onclick="setModifiedFlag(); submitModifiedFrm()">
        <input type="hidden" name="isModified" id="isModified" value="true"> 
    
    </form>
<%
    }
%>
<!-- 초기 버튼 설정 -->
<script>
    function updateButton() {
        var buttonText = "댓글입력";
        document.getElementById("submitButton").value = buttonText;
    }

    // 수정 버튼을 눌렀을 때 호출하는 함수
function updateModifiedButton() {
    console.log("updateModifiedButton 실행");
    // isModified 값에 따라 버튼 텍스트를 동적으로 변경
    var isModified = document.getElementById("isModified").value === "true";
    var buttonText = isModified ? "수정하기" : "댓글입력";
    var modifiedSubmitButton = document.getElementById("modifiedSubmitButton");

    if (modifiedSubmitButton) {
        modifiedSubmitButton.value = buttonText;
    }
}

    // 수정 버튼의 클릭 이벤트 리스너 등록
document.addEventListener("DOMContentLoaded", function () {
    var modifiedButton = document.getElementById("modifiedSubmitButton");
    if (modifiedButton) {
        modifiedButton.addEventListener("click", function () {
            console.log("modifiedSubmitButton 실행");
            updateModifiedButton();
            submitModifiedFrm(); // 수정된 내용 서버로 전송
        });
    } else {
        console.error("modifiedSubmitButton를 찾을 수 없습니다.");
    }
});


// 페이지 로드 시 초기 버튼 설정
window.onload = function () {
    console.log("updateButton function called");
    console.log("updateModifiedButton function called");
    updateButton();
    setTimeout(function () {
        updateModifiedButton(); // setTimeout으로 지연 호출
    }, 0);
};


    function submitFrm() { // 새 댓글 작성
    	console.log("submitFrm function 실행");
        var commFrm = document.getElementById("commFrm");
        if (commFrm) {
            commFrm.action = "../board/CommWrite.do";
            commFrm.submit();
        }
    }

    function submitModifiedFrm() {
    	console.log("submitModifiedFrm 실행");
        var modifiedContent = document.getElementById("commContent").value;
        var commNum = document.getElementById("commNum").value;
        var modifiedCommFrm = document.getElementById("modifiedCommFrm");
        if (modifiedCommFrm) {
            modifiedCommFrm.action = "../board/CommEdit.do?action=edit&commNum=" + commNum + "&content=" + modifiedContent;
            modifiedCommFrm.submit();
        }
    }

    function setModifiedFlag() {
    	console.log("setModifiedFlag 실행");
        document.getElementById("isModified").value = "true";
    }

    function populateTextareaAndForm(commentContent, commNum) {
        var commContentElement = document.getElementById("commContent");
        var commNumElement = document.getElementById("commNum");
        var modifiedCommContentElement = document.getElementById("modifiedCommContent");
        var modifiedCommNumElement = document.getElementById("modifiedCommNum");
        var submitButton = document.getElementById("submitButton");
        var modifiedSubmitButton = document.getElementById("modifiedSubmitButton");

        if (commContentElement && commNumElement && modifiedCommContentElement && modifiedCommNumElement && submitButton && modifiedSubmitButton) {
            commContentElement.value = commentContent;
            commNumElement.value = commNum;
            modifiedCommContentElement.value = commentContent;
            modifiedCommNumElement.value = commNum;

            // "댓글입력" 버튼을 "수정하기"로 변경
            submitButton.value = "수정하기";
            modifiedSubmitButton.value = "수정하기";

            setModifiedFlag();
            updateModifiedButton();
        } else {
            console.error("One or more elements not found.");
        }
    }



</script>
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