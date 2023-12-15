<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="model.GroupDTO" %>
<%
	GroupDTO dto = (GroupDTO)request.getAttribute("dto");

	String id = "";

	if(dto != null){
		id = dto.getId();
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
	
	Calendar now = Calendar.getInstance();
	String nowTime = sdf.format(now.getTime());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>달력</title>
<style>
td {
	width: 50px;
	height: 50px;
}

.Calendar {
	text-align: center;
	margin: 0 auto;
}

.Calendar>thead>tr:first-child>td {
	font-weight: bold;
}

.Calendar>thead>tr:last-child>td {
	background-color: gray;
	color: white;
}

.pastDay {
	background-color: lightgray;
}

.today {
	background-color: #FFCA64;
	cursor: pointer;
}

.futureDay {
	background-color: #FFFFFF;
	cursor: pointer;
}

.futureDay.choiceDay, .today.choiceDay {
	background-color: #3E85EF;
	color: #fff;
	cursor: pointer;
}
</style>

<script>
	window.onload = function() {
		buildCalendar();
	} // 웹 페이지가 로드되면 buildCalendar 실행

	let nowMonth = new Date(); // 현재 달을 페이지를 로드한 날의 달로 초기화
	let today = new Date(); // 페이지를 로드한 날짜를 저장
	today.setHours(0, 0, 0, 0); // 비교 편의를 위해 today의 시간을 초기화

	// 달력 생성 : 해당 달에 맞춰 테이블을 만들고, 날짜를 채워 넣는다.
	function buildCalendar() {

		let firstDate = new Date(nowMonth.getFullYear(), nowMonth.getMonth(), 1); // 이번달 1일
		let lastDate = new Date(nowMonth.getFullYear(),
				nowMonth.getMonth() + 1, 0); // 이번달 마지막날

		let tbody_Calendar = document.querySelector(".Calendar > tbody");
		document.getElementById("calYear").innerText = nowMonth.getFullYear(); // 연도 숫자 갱신
		document.getElementById("calMonth").innerText = leftPad(nowMonth
				.getMonth() + 1); // 월 숫자 갱신

		while (tbody_Calendar.rows.length > 0) { // 이전 출력결과가 남아있는 경우 초기화
			tbody_Calendar.deleteRow(tbody_Calendar.rows.length - 1);
		}

		let nowRow = tbody_Calendar.insertRow(); // 첫번째 행 추가           

		for (let j = 0; j < firstDate.getDay(); j++) { // 이번달 1일의 요일만큼
			let nowColumn = nowRow.insertCell(); // 열 추가
		}

		for (let nowDay = firstDate; nowDay <= lastDate; nowDay.setDate(nowDay
				.getDate() + 1)) { // day는 날짜를 저장하는 변수, 이번달 마지막날까지 증가시키며 반복  

			let nowColumn = nowRow.insertCell(); // 새 열을 추가하고
			nowColumn.innerText = leftPad(nowDay.getDate()); // 추가한 열에 날짜 입력

			if (nowDay.getDay() == 0) { // 일요일인 경우 글자색 빨강으로
				nowColumn.style.color = "#DC143C";
			}
			if (nowDay.getDay() == 6) { // 토요일인 경우 글자색 파랑으로 하고
				nowColumn.style.color = "#0000CD";
				nowRow = tbody_Calendar.insertRow(); // 새로운 행 추가
			}

			if (nowDay < today) { // 지난날인 경우
				nowColumn.className = "pastDay";
			} else if (nowDay.getFullYear() == today.getFullYear()
					&& nowDay.getMonth() == today.getMonth()
					&& nowDay.getDate() == today.getDate()) { // 오늘인 경우           
				nowColumn.className = "today";
				nowColumn.onclick = function() {
					choiceDate(this);
				}
			} else { // 미래인 경우
				nowColumn.className = "futureDay";
				nowColumn.onclick = function() {
					choiceDate(this);
				}
			}
		}
	}

	// 날짜 선택
	function choiceDate(nowColumn) {
		if (document.getElementsByClassName("choiceDay")[0]) { // 기존에 선택한 날짜가 있으면
			document.getElementsByClassName("choiceDay")[0].classList
					.remove("choiceDay"); // 해당 날짜의 "choiceDay" class 제거
		}
		nowColumn.classList.add("choiceDay"); // 선택된 날짜에 "choiceDay" class 추가
	}

	// 이전달 버튼 클릭
	function prevCalendar() {
		nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() - 1,
				nowMonth.getDate()); // 현재 달을 1 감소
		buildCalendar(); // 달력 다시 생성
	}
	// 다음달 버튼 클릭
	function nextCalendar() {
		nowMonth = new Date(nowMonth.getFullYear(), nowMonth.getMonth() + 1,
				nowMonth.getDate()); // 현재 달을 1 증가
		buildCalendar(); // 달력 다시 생성
	}

	// input값이 한자리 숫자인 경우 앞에 '0' 붙혀주는 함수
	function leftPad(value) {
		if (value < 10) {
			value = "0" + value;
			return value;
		}
		return value;
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

				<!-- import 끝 -->
				<!-- 본문 -->
				<div style="position: absolute; width:1280px; height:1300px">
				<form action="../board/" method="post">
				<div class="wrap" style="position: relative; width: 1280px; height: 100px;">
					<div class="jumbotron" style="text-align: left;">
						<% if(id != "") { %>
						<h1 class="display-4">${requestScope.dto.nickName}님, 환영합니다!</h1>
						<% } %>
						<p class="lead">
							<%=nowTime%>
						</p>
					</div>	
				</div>
				
					<div class="jumbotron" style="position: relative; width: 1280px; height: 150px;" >		
						<div style="position: relative; left: -250px; top: 0.1px; margin-left: 5px; text-align: center;">
						<div style="width: 70px; height: 70px; border-radius: 70%; overflow: hidden; background: #BDBDBD; display: inline-block;"></div>
						<div style="width: 70px; height: 70px; border-radius: 70%; overflow: hidden; background: #BDBDBD; display: inline-block;"></div>
						<div style="width: 70px; height: 70px; border-radius: 70%; overflow: hidden; background: #BDBDBD; display: inline-block;"></div>
						</div>
						<div style="position: relative; left: 100px; top: 0.1px; margin-left: 5px; text-align: center; width:400px; height: 30px;">
						<div style="position: relative; left: 100px; top: -5px; text-align: center; width:100px; height: 15px;">
						<p style="width:50px; height: 10px; display: inline-block;">신짱구</p>
						<p style="width:20px; height: 10px; display: inline-block;">남</p>
						</div>
						<div style="position: relative; left: 150px; top: -20px; text-align: center; width:100px; height: 15px;">
						<p style="width:50px; height: 10px; display: inline-block;">한유리</p>
						<p style="width:20px; height: 10px; display: inline-block;" >여</p>
						</div>
						<div style="position: relative; left: 200px; top: -35px; text-align: center; width:100px; height: 15px;">
						<p style="width:50px; height: 10px; display: inline-block;">김철수</p>
						<p style="width:20px; height: 10px; display: inline-block;">남</p>
						</div>
						</div>
						<div style="position: relative; width: 30px; height: 10px;" align="center">
						<p>VS</p>
						<div style="position: relative; left: 250px; top: -118px; margin-left: 5px; text-align: center; width:400px; height: 100px;">
						<div style="width: 70px; height: 70px; border-radius: 70%; overflow: hidden; background: #BDBDBD; display: inline-block;"></div>
						<div style="width: 70px; height: 70px; border-radius: 70%; overflow: hidden; background: #BDBDBD; display: inline-block;"></div>
						<div style="width: 70px; height: 70px; border-radius: 70%; overflow: hidden; background: #BDBDBD; display: inline-block;"></div>
						</div>
						<div style="position: relative; left: 100px; top: 0.1px; margin-left: 5px; text-align: center; width:400px; height: 30px;">
						<div style="position: relative; left: 100px; top: -5px; text-align: center; width:100px; height: 15px;">
						<p style="width:50px; height: 10px; display: inline-block;">신짱구</p>
						<p style="width:20px; height: 10px; display: inline-block;">남</p>
						</div>
						<div style="position: relative; left: 150px; top: -20px; text-align: center; width:100px; height: 15px;">
						<p style="width:50px; height: 10px; display: inline-block;">한유리</p>
						<p style="width:20px; height: 10px; display: inline-block;" >여</p>
						</div>
						<div style="position: relative; left: 200px; top: -35px; text-align: center; width:100px; height: 15px;">
						<p style="width:50px; height: 10px; display: inline-block;">김철수</p>
						<p style="width:20px; height: 10px; display: inline-block;">남</p>
						</div>
						</div>
						<div class="lead" style="position: relative; left: -250px; top: -50px; width: 300px;">
							<input type="submit" class="btn" value="상세보기">
							<input type="submit" class="btn" value="매치하기">
						</div>
						<div class="lead" style="position: relative; left: 250px; top: -90px; width: 300px;">	
							<input type="submit" class="btn" value="상세보기">
							<input type="submit" class="btn" value="매치하기">
						</div>
					</div>
				</div>
				</form>
					<!-- 캘린더 -->
					<div style="position: relative; top: 150px" align="center">
					<table class="Calendar">
						<thead>
							<tr>
								<td onClick="prevCalendar();" style="cursor: pointer;">&#60;</td>
								<td colspan="5"><span id="calYear"></span>년 <span
									id="calMonth"></span>월</td>
								<td onClick="nextCalendar();" style="cursor: pointer;">&#62;</td>
							</tr>
							<tr>
								<td>일</td>
								<td>월</td>
								<td>화</td>
								<td>수</td>
								<td>목</td>
								<td>금</td>
								<td>토</td>
							</tr>
						</thead>

						<tbody>
						</tbody>
					</table>
					</div>
				</div>
				</div>
			</div>
		</div>
		<!-- BootStrap javascript 사용 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>
</body>

</html>