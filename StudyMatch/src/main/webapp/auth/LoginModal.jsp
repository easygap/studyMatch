<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<ul class="nav">
 <li>
  <a data-toggle="modal" href="#loginModal">LOGIN</a>
  <div class="modal fade" id="loginModal" role="dialog">
   <div class="modal-dialog">
    <div class="modal-content">
     <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal">×</button>
      <h1 class="modal-title" align="center">로그인</h1>
     </div>
     <div class="modal-body">
      <th:block th:replace="~{/Login.jsp :: setLogin(~{this::login} )}"></th:block>
     </div>
    </div>
   </div>
  </div>
 </li>
</ul>
</body>
</html>