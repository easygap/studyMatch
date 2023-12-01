<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">                                                                 
<title>Insert title here</title>
</head>
<body>
<h2>회원가입</h2>
	
	<form action="join_ok.jsp" method="post">
		아  이   디 :  <input type="text" name="id"><br/>
		비밀    번호 :  <input type="password" name="pw"><br/>
		비밀번호 확인 :  <input type="passworddu" name2="pwdu"><br/>
		이       름 :  <input type="text" name="name"><br/>
		별       명 :  <input type="text" name="name"><br/>
		생년    월일 :  <input type="text" name="name"><br/>
		전화    번호 :  <select name="phon1">		
			<option>010</option>
			<option>02</option>
			<option>031</option>
			<option>032</option>
            <option>033</option>
			<option>041</option>
			<option>042</option>
			<option>043</option>
			<option>044</option>			
            <option>051</option>
			<option>052</option>
			<option>053</option>
			<option>054</option>			
            <option>055</option>
			<option>061</option>
			<option>062</option>
			<option>063</option>			
	    	<option>064</option>					
		</select>
		- <input type="text" name="phone2" size="5">
		- <input type="text" name="phone3" size="5"><br/>
		주       소 :  <input type="text" name="name"><br/>
		이   메   일 :  <input type="text" name="name"><br/>
		직       업 :  <input type="text" name="name"><br/>
		
		<br/>
		<input type="radio" name="gender" value="m" checked>남자
		<input type="radio" name="gender" value="f">여자
		
		<input type="submit" value="가입">
		
	</form>
</body>
</html>