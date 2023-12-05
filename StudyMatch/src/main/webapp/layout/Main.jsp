<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Jquery 사용 -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Simple Sidebar - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="../css/styles.css" rel="stylesheet" />
</head>

            <!-- Sidebar-->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light">MatchMate</div>
                <div class="list-group list-group-flush">
                	<!-- 어학 카테고리 -->
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!" id="lan">어학 ▶</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="../board/List.jsp?category=1001" id="lan1" style="display:none;">• 영어</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="../board/List.jsp?category=1002" style="display:none;">• 일본어</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="../board/List.jsp?category=1002" id="lan3" style="display:none;">• 중국어</a>
                    
                    <!-- 개발 카테고리 -->
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!" id="dev">개발자 ▶</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="../board/List.jsp?category=2001" id="dev1" style="display:none;">• 프론트/백엔드</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="../board/List.jsp?category=2002" id="dev2" style="display:none;">• 프로젝트</a>
                    
                    <!-- UI/UX 카테고리 -->
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!" id="ui">UI/UX ▶</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="../board/List.jsp?category=4001" id="ui1" style="display:none;">• 디자이너</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="../board/List.jsp?category=4002" id="ui2" style="display:none;">• 퍼블리셔</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="../board/List.jsp?category=4003" id="ui3" style="display:none;">• 프로젝트</a>
                    
                    <!-- 사무 카테고리 -->
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!" id="aff">사무직 ▶</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="../board/List.jsp?category=3001" id="aff1" style="display:none;">• 엑셀/한글/워드</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="../board/List.jsp?category=3002" id="aff2" style="display:none;">• 회계</a>
                    
                    <!-- 금융 카테고리 -->
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!" id="fin">금융 ▶</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="../board/List.jsp?category=5001" id="fin1" style="display:none;">• 부동산</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="../board/List.jsp?category=5002" id="fin2" style="display:none;">• 투자</a>
                    
                    <!-- 자유게시판 -->
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="../board/List.jsp?category=6001">자유게시판</a>
                </div>
            </div>
            

        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="../js/scripts.js"></script>
</html>