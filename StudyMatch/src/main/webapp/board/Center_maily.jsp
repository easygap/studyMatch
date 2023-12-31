<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>내 문의글</title>
    <!-- Favicon-->
	<link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
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

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Document</title>
    <style>

        ul{
            padding: 0;
            margin: 0;
            display:inline-block;
        }
 
        .board_row {

            width: 1000px;
            border-bottom:solid 1px gray; 

        }

        /* 게시글 목록 공통 개별 요소 속성 */
        li{
            list-style:none;
            border-bottom:solid 1px gray; 
            float:left; 
            text-align:center;
            /* padding: 0;
            margin: 0; */
        }

        /* 제목, 작성자 row의 style 설정 */
        .title_row > li {
            height: 50px;
            line-height: 50px;
            background:rgb(233, 233, 233);
            font-weight: bold;
            border-top:solid 2px rgb(0, 0, 0);
            border-right:solid 1px gray;
        }

        /* 게시글 목록 데이터 개별 요소 속성 */
        .data_row > li{
            height: 40px;
            line-height: 40px;
            font-weight: lighter;
            border-right:solid 1px gray;
            
        }

        /* 요소들의 너비 설정 */
        .w70 {width:70px; }
        .w500 {width:500px; }
        .w120 {width:120px; }
        .w100 {width:100px; }


        /* 게시판 목록 박스 */
        .notice_board{

            display: flex;
            justify-content: center;
            align-content: center;
            align-items: center;
            /* 구성 요소 세로로 배치 */
            flex-direction: column;

        }


        .pagination{
            display: flex;
            width: 400px;
            justify-content: space-between;
        }

        /* 전체 중앙 정렬 */
        .container{
            display: flex;
            flex-direction: column;
            align-items: center;
        }
    </style>
    <script>
                console.log("remove");
                for(let i=front_block;i<=total_block && i< front_block+block_num ;i++){
                 console.log("add element");
                 let button = document.createElement("button");
                 button.textContent = i;
                 button.addEventListener("click",function(event){
                 post_data_print(i)});
                 block_box.appendChild(button);

                }
               
            }


            }

            function next(){
                block_print(current_block+block_num)
                console.log("다음");

            }
            // 화면 로드 시 실행되는 이벤트
            window.onload = function(){

                // 게시글 데이터 출력하기
                post_data_print(1)

               // 블럭 출력하기
               block_print(1)
            }
        </script>

    </head>
    <body>
    <div class="container">
    <table>
    <tr>
    <td>
    <table>
    <tr>
    <div class="card-write">
    <div class="myinfo">
 
	<form action="/bbs/replyForm.bbs" method="post"> 
		<div align="center">
		    <input type="hidden" name="pageNum" value="${pageNum}">
		    <input type="hidden" name="depth" value="${article.depth}">
		    <input type="hidden" name="pos" value="${article.pos}">
		    <input type="hidden" name="groupId" value="${article.groupId}">
		    
			<table border="2" width="500">
				<tr>
					<th>제&nbsp; &nbsp; &nbsp;목	
				<input type="text"
							class="form-control" name="title" id="title"
							placeholder="제목을 입력해 주세요">	
				</th>				
				</div>							
				
	    	<tr> 
	    	<td colspan='2'>
	   		<textarea name="title" id="utitle" rows="10" cols="65" placeholder="내용쓰기" maxlength="100" required></textarea>
      	     </td>
      	     <tr>
          </tr>
 	

				<tr>
				<td colspan='2'>
										
			<img src="../img/댓글.png" alt="댓글" />
		    <textarea name="title" id="utitle" rows="5" cols="65" placeholder="댓글달기" maxlength="100" required></textarea>
          <div align="right"> 
	    <input type='button' value='작성하기'/>
	    </div>
        <div align="center"> 
			<button class="before_move" onclick="before()">이전글</button>          
			<button type="button" onclick="location.href='../board/list.do?interest=${ param.interest }';">목록 바로가기</button>		
            <button class="next_move" onclick="next()">다음글</button>      
         </div>    
                       

	</form>
		<!-- BootStrap javascript 사용 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>
</html>
