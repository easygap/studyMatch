<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>자주 묻는 질문</title>
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

<h2>고객센터</h2>
<input type='button' value='자주 묻는 질문'/>
<input type='button' value='1:1 문의'/>
<input type='button' value='내 문의'/>
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
        /* 
            게시판 목록 행 단위 
            목록 요소 자체를 가운데 정렬하기
            ul 기본 가로 크기는 100%이므로, 가로 크기를 지정해줘야 가운데로 정렬할 수 있다.
            가운데 정렬
            번호, 제목, 작성자, 작성일, 조회, 좋아요 와 데이터 정렬
        */
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

        /* 번호, 제목, 작성자, 작성일, 답변 row의 style 설정 */
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

 
            //    총 게시글 수
            let totalPage = 500;
            //  한 페이지 당 출력되는 게시글 갯수
            let page_num = 10;
            //   한번에 출력될 수 있는 최대 블록 수
            // ex ) [1][2][3][4][5] -> 블록
            let block_num = 10;
            // 블록의 총 수를 계산한다.
            let total_block = totalPage%20 == 0 ? totalPage/20 : totalPage/20+1 ;
            // 현재 블록 위치를 알려준다
            let current_block = 1;
            /*
            게시글 데이터를 담고 있는 객체 배열
            번호 : data[게시글 번호].notice_num
            제목 : data[게시글 번호].title
            작성자 : data[게시글 번호].writer
            작성일 : data[게시글 번호].date_created
            답 변 : data[게시글 번호].like_ans
            */
            let data = new Array();

            //    게시글 데이터를 담고 있는 객체를 500개 추가한다.
            for(let i=1;i<=totalPage;i++){
                data[i] = {
                    notice_num : i,
                    title:"제목"+i,
                    writer:"작성자"+i,
                    date_created:"2022-10-07",
                    like_ans : i
                }
            }
            // 게시글 데이터 출력하기
            // 매개변수 : 선택 블럭 
           function post_data_print(block){

                // 초기화
                // 게시글 title 제외하고 모두 제거
                let post_list = document.querySelectorAll(".data_row");
                post_list.forEach(function(item){
                    item.remove();
                })

                // 게시글 출력 공간
                let notice_board = document.querySelector(".notice_board");
                // 출력 첫 페이지 번호
                let start = totalPage-page_num*(block-1);
                for(let i=start;i>=1&&i>start-page_num;i--){

                    // data[i].notice_num data[i].title data[i].writer data[i].date_created data[i].Lookkup_num data[i].like

                    let post = document.createElement("ul");
                    post.className = "board_row";
                    post.className = "data_row";

                    let classname = ["w70","w500","w120","w100","w100"]

                    let post_data = [data[i].notice_num, data[i].title, data[i].writer, data[i].date_created, data[i].like_ans];
                    
                    //게시글 생성
                    for(let j=0;j<classname.length;j++){
                        let li = document.createElement("li");
                        li.className = classname[j];
                        li.textContent = post_data[j];
                        post.appendChild(li);
                    }

                    // 게시글 추가
                    notice_board.appendChild(post);

                }

                }
                
            // 블럭 출력하기
            // 매개변수 : 가장 앞에 오는 블럭
            function block_print(front_block){
             current_block = front_block;

                // 이전으로 갈 블럭이 없으면
                if(front_block <= 1 ){
                    document.querySelector(".before_move").style.visibility = "hidden";
                }
                else{
                    document.querySelector(".before_move").style.visibility = "visible";
                }

                // 다음으로 갈 블럭이 없으면
                if(front_block+block_num >= total_block){

                    document.querySelector(".next_move").style.visibility = "hidden";
                }
                else{
                    document.querySelector(".next_move").style.visibility = "visible";
                }

                // 블럭을 추가할 공간
                let block_box = document.querySelector(".block");
                // 기존 블럭 모두 삭제
                block_box.replaceChildren();

                console.log("remove");


                //front_block부터 total_block 또는 block_num까지 생성 및 추가
                for(let i=front_block;i<=total_block && i< front_block+block_num ;i++){
                    console.log("add element");

                    // 버튼을 생성한다.
                    let button = document.createElement("button");
                    button.textContent = i;
                    // 버튼을 클릭하면 게시글이 변경되는 이벤트 추가
                    button.addEventListener("click",function(event){
                        post_data_print(i)});
                    // 블럭에 추가한다.
                    block_box.appendChild(button);

                }
               
            }

            function before(){
                block_print(current_block-block_num)
                console.log("이전");
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
  
     <p style='letter-spacing:1em'>No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;제목&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;작성자&nbsp;작성일&nbsp;조회수&nbsp;&nbsp;추천</p>
      <div class="notice_board">
            <!-- title 열 -->
            <ul class="board_row title_row ">
               
            </ul>
            <!-- 게시글 추가로 들어오는 위치 -->
        </div>

        <!-- 페이지 블럭 -->
        <div class="pagination">
            <button class="before_move" onclick="before()">이전</button>
            <div class="block">
                <!-- 블럭 추가로 들어오는 위치 -->
            </div>
            <button class="next_move" onclick="next()">다음</button>
                  
        </div>
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

</body>
</html>