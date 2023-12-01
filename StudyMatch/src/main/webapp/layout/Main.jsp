<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar-->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light">MatchMate</div>
                <div class="list-group list-group-flush">
                	<!-- 어학 카테고리 -->
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!" id="lan">어학 ▶</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="#!" id="lan1">• 영어</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="#!" id="lan2">• 일본어</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="#!" id="lan3">• 중국어</a>
                    
                    <!-- 개발 카테고리 -->
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!" id="dev">개발자 ▶</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="#!" id="dev1">• 프론트/백엔드</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="#!" id="dev2">• 프로젝트</a>
                    
                    <!-- UI/UX 카테고리 -->
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!" id="ui">UI/UX ▶</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="#!" id="ui1">• 디자이너</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="#!" id="ui2">• 퍼블리셔</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="#!" id="ui3">• 프로젝트</a>
                    
                    <!-- 사무 카테고리 -->
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!" id="aff">사무직 ▶</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="#!" id="aff1">• 엑셀/한글/워드</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="#!" id="aff2">• 회계</a>
                    
                    <!-- 금융 카테고리 -->
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!" id="fin">금융 ▶</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="#!" id="fin1">• 부동산</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-2" href="#!" id="fin2">• 투자</a>
                    
                    <!-- 자유게시판 -->
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">자유게시판</a>
                </div>
            </div>
            <!-- Page content wrapper-->
            <div id="page-content-wrapper">
                <!-- Top navigation-->
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                    <div class="container-fluid">
                        <button class="btn btn-primary" id="sidebarToggle">게시판</button>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                                <li class="nav-item active"><a class="nav-link" href="#!">Home</a></li>
                                <li class="nav-item"><a class="nav-link" href="#!">Link</a></li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">계정</a>
                                    <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="#!">로그인</a>
                                        <a class="dropdown-item" href="#!">아이디 찾기</a>
                                        <a class="dropdown-item" href="#!">비밀번호 찾기</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#!">회원가입</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>          
            </div>
        </div>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="../js/scripts.js"></script>
    </body>
</html>