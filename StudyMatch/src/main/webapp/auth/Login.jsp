<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<meta charset="UTF-8">

<!-- css 가져오기 -->
<link href="FormLogin.css" rel="stylesheet" type="text/css" />

<th:block th:fragment="setLogin(login)">
<body>
	<div class="loginbox">
		<h2>로그인</h2>
		<form th:action="../auth/Login.do" th:method="POST">

			<!--작성하지 않아도 문제는 없음-->
			<fieldset>
				<legend>로그인 구역</legend>
				<label for="loginid">아이디(E-mail)</label> 
				<input type="text" id="loginid" name="id" placeholder="아이디(E-mail)을 입력해 주세요"> 
				<label for="loginpw">비밀번호</label> 
				<input type="password" id="loginpw" name="pass" placeholder="비밀번호를 입력해 주세요">
				<ul>
					<li><a href="#">아이디/비밀번호찾기</a></li>
					<li><a href="#">회원가입</a></li>
				</ul>
				<!--데이터를 서버로 전송-->
				<button type="submit">로그인</button>
			</fieldset>
		</form>
	</div>
</body>
</th:block>