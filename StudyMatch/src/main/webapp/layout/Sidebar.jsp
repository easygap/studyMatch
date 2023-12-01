<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- BootStrap css 사용 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

<!-- Jquery 사용 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<style>
a {
	text-decoration: none;
	color: #000;
	font-size: 14px;
}
</style>
<script>
$(document).ready(function(){
	  
	  $('#lan').click(function(){
	    $('.nav-lan1').slideToggle();
	  })
	  
	  $('#dev').click(function(){
	    $('.nav-lan2').slideToggle();
	  })
	  
	  $('#ui').click(function(){
	    $('.nav-lan3').slideToggle();
	  })
	  
	  $('#aff').click(function(){
	    $('.nav-lan4').slideToggle();
	  })
	  
	  $('#fin').click(function(){
	    $('.nav-lan5').slideToggle();
	  })
})
</script>
</head>
<body>
<!-- BootStrap javascript 사용 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-sm bg-light navbar-light"
            style="width: 170px; float: right;">
  <ul class="nav flex-column">
    <li class="nav-item active">
      <a class="nav-link" href="#" id="lan">어학 스터디</a>
      <a class="nav-lan1" href="#">￮ 영어<br/></a>
      <a class="nav-lan1" href="#">￮ 일본어<br/></a>
      <a class="nav-lan1" href="#">￮ 중국어<br/></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#" id="dev">개발자 스터디</a>
      <a class="nav-lan2" href="#">￮ 프론트/백엔드<br/></a>
      <a class="nav-lan2" href="#">￮ 프로젝트<br/></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#" id="ui">UI/UX 스터디</a>
      <a class="nav-lan3" href="#">￮ 디자이너<br/></a>
      <a class="nav-lan3" href="#">￮ 퍼블리셔<br/></a>
      <a class="nav-lan3" href="#">￮ 프로젝트<br/></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#" id="aff">사무직 스터디</a>
      <a class="nav-lan4" href="#">￮ 엑셀/한글/워드<br/></a>
      <a class="nav-lan4" href="#">￮ 회계<br/></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#"id="fin">금융 스터디</a>
      <a class="nav-lan5" href="#">￮ 부동산<br/></a>
      <a class="nav-lan5" href="#">￮ 투자<br/></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">자유게시판</a>
    </li>
  </ul>
</nav>
</body>
</html>