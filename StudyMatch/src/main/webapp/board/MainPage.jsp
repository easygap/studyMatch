<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="model.GroupDTO"%>
<%@ page import="java.util.List"%>
<%
request.setCharacterEncoding("UTF-8");

Object groupNameList1 = request.getAttribute("nameGR1");
List<String> firstGroupName = (List<String>) groupNameList1;
/*
for(int i = 0; i < firstGroupName.size(); i++){
	System.out.println("First Group Name IN MainPage.jsp : " + firstGroupName.get(i));
}
*/
Object groupImgList1 = request.getAttribute("imgGR1");
List<String> firstGroupImg = (List<String>) groupImgList1;
/*
for(int i = 0; i < firstGroupImg.size(); i++){
	System.out.println("First Group IMG IN MainPage.jsp : " + firstGroupImg.get(i));
}
*/
Object groupNameList2 = request.getAttribute("nameGR2");
List<String> secondGroupName = (List<String>) groupNameList2;
/*
for(int i = 0; i < secondGroupName.size(); i++){
	System.out.println("Second Group Name IN MainPage.jsp : " + secondGroupName.get(i));
}
*/

Object groupImgList2 = request.getAttribute("imgGR2");
List<String> secondGroupImg = (List<String>) groupImgList2;
/*
for(int i = 0; i < secondGroupImg.size(); i++){
	System.out.println("Second Group IMG IN MainPage.jsp : " + secondGroupImg.get(i));
}
*/

String makeGroup = "";

Object make = request.getAttribute("makeGroup");
makeGroup = (String)make;

GroupDTO dto = (GroupDTO) request.getAttribute("dto");

String id = "";

String firstGroup = ""; 

String secondGroup = "";

if (dto != null) {
	id = dto.getId();
	firstGroup = dto.getFirstGroup();
	secondGroup = dto.getSecondGroup();
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
<link rel="stylesheet" href="../css/MainPage.css">
<script>	
	/** 그룹1 매치하기 버튼 눌렀을 때 */
	function matchCheck1() {
		if (confirm("그룹1에 가입하시겠습니까??") == true) {
			alert("'그룹1' 가입이 완료되었습니다.");
			document.group1.submit();
		} else {
			// 사용자가 취소를 선택한 경우 아무 동작 없음
		}
	}
	
	/** 그룹2 매치하기 버튼 눌렀을 때 */
	function matchCheck2() {
 		if (confirm("그룹2에 가입하시겠습니까??") == true) {    //확인
 			alert("'그룹2' 가입이 완료되었습니다.");
 			document.group2.submit();
 			
 		} else {   //취소
 			return false;
 		}
 	}

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
				<div style="position: absolute; width: 1280px; height: 1300px">
						<div class="wrap"
							style="position: relative; width: 1280px; height: 100px;">
							<div class="jumbotron" style="text-align: left;">
								<%
								if (!"".equals(id)) {
								%>
								<h1 class="display-4">${requestScope.dto.nickName}님,환영합니다!</h1>
								<%
								}
								%>
								<p class="lead">
									<%=nowTime%>
								</p>
							</div>
						</div>
						<!-- 매칭하기 -->
						<div id="NewMatch">
							<p class="matchfont" id="newmatch">NEW MATCH ! ! !</p>
							<!-- 그룹1 매칭 -->
							<form action="../board/Match1.do" name="group1" method="post">
							<div class="Match1" align="center">
								<% if("".equals(id)) { %>
								<p>로그인 후 이용할 수 있는 기능입니다.</p>
								<input type="button" value="로그인" class="Mainbutton" onClick="location.href='../auth/Login.jsp'"> <% } else { %>
									<% if( firstGroupImg != null && !firstGroupImg.isEmpty()) { 
									for(int i = 0; i < firstGroupName.size(); i++) {
										if(firstGroupImg.get(i) != null && !firstGroupImg.get(i).isEmpty()) { %>
											<img src="${pageContext.request.contextPath}/MyProfile/<%=firstGroupImg.get(i)%>" name="profile" alt="Mem" class="profile">
								<% 	} else { %>
											<img src="${pageContext.request.contextPath}/MyProfile/default.png" name="profile" alt="Default" class="profile">
								
								<% } } } else { %>
									<p>No images available</p>
								<% } %>
								<br />
								<% if( firstGroupName != null && !firstGroupName.isEmpty() ) { %>
								<p id="name">
								<% 
									for(int i = 0; i < firstGroupName.size(); i++) { %>
									<%=firstGroupName.get(i)%>
									<% if (i < firstGroupName.size() - 1) { %> <%-- 마지막 요소가 아닌 경우만 공백 추가 --%><% } %>
								<% } %></p>
									<p class="content1">설정하신 {${dto.getAddress()},
											${dto.getInterest1()} 프로젝트}로 1번 그룹에 매칭되었습니다.</p>
										<input type="submit" name="information1" class="Mainbutton"
											value="  상 세 보 기  " />&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" name="match1" class="Mainbutton"
											value="  매 치 하 기  " onclick="matchCheck1()" />
								<% } else { %>
										<p>매칭할 수 있는 그룹이 존재하지 않습니다.</p>
										<input type="submit" name="make" class="Mainbutton"
											value="  그 룹 생 성  " />
								<% } %>
							</div>
							<input type="text" style="display:none;" name="groupNum1" value="<% if(firstGroup != null && !firstGroup.equals("")) { out.print(firstGroup);  } %>" />
							</form>
							<div class="VS" align="center">
								<p class="matchfont" id="vs">VS</p>
							</div>
							<!-- 그룹2 매칭 -->
							<form action="../board/Match2.do" name="group2" method="post">
							<div class="Match2" align="center">
								<% if( secondGroupImg != null && !secondGroupImg.isEmpty() ) { 
									for(int i = 0; i < secondGroupName.size(); i++) {
										if(secondGroupImg.get(i) != null && !secondGroupImg.get(i).isEmpty()) { %>
											<img src="${pageContext.request.contextPath}/MyProfile/<%=secondGroupImg.get(i)%>" name="profile" alt="Mem" class="profile">
								<% 	} else { %>
											<img src="${pageContext.request.contextPath}/MyProfile/default.png" name="profile" alt="Default" class="profile">
								
								<% } } } else { %>
									<p>No images available</p>
								<% } %>
								<br />
								<% if( secondGroupName != null && !secondGroupName.isEmpty() ) { %>
								<p id="name">
								<% 
									for(int i = 0; i < secondGroupName.size(); i++) { %>
									<%=secondGroupName.get(i)%>
									<% if (i < secondGroupName.size() - 1) { %> <%-- 마지막 요소가 아닌 경우만 공백 추가 --%><% } %>
								<% } %></p>
									<p class="content1">설정하신 {${dto.getAddress()},
											${dto.getInterest2()} 프로젝트}로 2번 그룹에 매칭되었습니다.</p>
										<input type="submit" name="information2" class="Mainbutton"
											value="  상 세 보 기  " />&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" name="match2" class="Mainbutton"
											value="  매 치 하 기  " onclick="matchCheck2()" />
								<% } else { %>
										<p>매칭할 수 있는 그룹이 존재하지 않습니다.</p>
										<input type="submit" name="make" class="Mainbutton"
											value="  그 룹 생 성  " />
								<% } } %>
							</div>
							<input type="text" style="display:none;" name="groupNum2" value="<% if(secondGroup != null && !secondGroup.equals("")) { out.print(secondGroup); } %>" />
							</form>
						</div>
						
					<!-- 캘린더 -->
					<div id="Calendar" align="center">
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