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

	 <button onclick="location.href='Center_often.jsp'">자주묻는질문</button >
	 <button onclick="location.href='Center.jsp'">1:1 문의</button >
	 <button onclick="location.href='CenterLeaguewik.jsp'">내 문의</button >


<!--<form name = "form">  -->  
<div align="center">

  <table border="1" width="500" bgcolor="#F5F6CE" align="center" cellspacing="0" cellpadding="3"
  bordercolor="#0000000" bordercolordark="#F5F6CE">  
  
					<tr>
                        <td>
                         <select id="changeTest" onchnage="selectBoxChange(this.value);">
								<option value="engese">영어</option>
								<option value="japanese">일본어</option>
								<option value="chinese">중국어</option>
								<option value="front_end">UI/UX</option>
								<option value="Project01">JSP</option>
								<option value="designer">디자이너</option>
								<option value="Publishers">퍼블리셔</option>
     							<option value="Words">엑셀/한글/워드</option>
								<option value="go_sjcu">회계</option>
								<option value="Property">부동산</option>
								<option value="investment">투자</option>
								</select>															
  					      <input type="textle" placeholder="과정을 입력하세요!" style="width:300px;height:30px;">
						</td>	
						
					</tr>
					
	
					<td>		
						<textarea class="form-control" rows="11" name="content"
							id="content" placeholder="내용을 입력해 주세요"></textarea>
					
							<div align="right">
			<script>		
					  funtion doSaveAs(){
						  document.execCommand("SaveAs")
					   }
		  </script>
		  <form>	  
					   <input type="button" 
							value="작성하기" onClick=doSaveAs()">
          </form>						
					</td>
					
		          </div>
		          </div>
		          
	</table>				
		<tr class="mybtn">
					<a href="../board/List.jsp">목록 바로가기</a>	
					</tr>		
			
	<!-- BootStrap javascript 사용 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>

</html>