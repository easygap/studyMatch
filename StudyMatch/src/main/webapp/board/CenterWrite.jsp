<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
    <style>
        table, th, td {
            border: 1px solid #000000;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px 20px;
        }
    </style>

</head>
<body style="background-color:#F5F6CE">
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
<h2>고객센터</h2>
<input type='button' value='자주 묻는 질문'/>
<input type='button' value='1:1 문의'/>
<input type='button' value='내 문의'/>

<!--<form name = "form">  -->  

  <table border="1" width="100" bgcolor="#F5F6CE" align="center" cellspacing="0" cellpadding="3"
  bordercolor="#0000000" bordercolordark="#F5F6CE" bordercolorlight="#F5F6CE">  


					<tr>
                        <td>
                        <select name="language" >				
								<option value="engese">영어</option>
								<option value="japanese">일본어</option>
								<option value="chinese">중국어</option>
								<option value="front_end">프론트/백앤드</option>
								<option value="Project01">프로젝트</option>
								<option value="designer">디자이너</option>
								<option value="Publishers">퍼블리셔</option>
								<option value="Project02">프로젝트</option>
								<option value="Words">엑셀/한글/워드</option>
								<option value="go_sjcu">회계</option>
								<option value="Property">부동산</option>
								<option value="investment">투자</option>
								</select>															
							<input type="text" placeholder="과정을 입력하세요!" style="width:350px;height:30px;">
						</td>	
					</tr>
					<td>		
					
									<textarea name = "ta2" rows = "20" cols = "65"
							wrap = "virtual">내용을 입력하세요!</textarea>		
							<div align="right">
						<button class="btn btn-primary">작성하기</button>			
							
					</tr>					
		
			</table>				
					
					
			<form>
			
			</div>
	<!-- BootStrap javascript 사용 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>

</html>