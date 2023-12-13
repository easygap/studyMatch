<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>1:1 문의</title>
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

<div align="center">
    <table border="1">
        <thead>
            <tr>
                <th>제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목</th>
               
                <th><input type = "text" title = "title" size = "40"><br/></th>
                
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>문의사항</td>
                <td>
                    <form class="check">
                        <input type="checkbox" devel="box1"> 개발자
                        <input type="checkbox" back-end="box2"> 백엔드
                        <input type="checkbox" with="box3"> 탈퇴
                    </form>
                </td>
            </tr>
            <tr>
               <td>이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름</td>
                 <th><input type = "text" name = "name" size = "35"><br/></th>
            </tr>
            <tr>
                <td>연&nbsp;&nbsp;락&nbsp;&nbsp;처</td>
                <td name="telname" action="telpon" method="post">
                <input type='tel' name='phone1' size=7 maxlength=6 /> -
                <input type='tel' name='phone2' size=7 maxlength=6 /> -
                <input type='tel' name='phone3' size=7 maxlength=6 />
                 </td>

            </tr>
            <tr>
                <td>이&nbsp;&nbsp;메&nbsp;&nbsp;일</td>
                <th><input type = "text" email-one = "email-one" size = "35"><br/></th>
            </tr>
            <tr>
                <td>내&nbsp;&nbsp;&nbsp;&nbsp;용</td>
                <td><textarea mess="message" rows="5" cols="40"></textarea></td>
            </tr>
            <tr>
            
                <td colspan="0"></td>
                <td>
                <div align="center">
                    <input type='button' value='초기화'/>
                    <input type='button' value='문의하기'/>
                     </div> 
                </td>
            </tr>
        </tbody>
    </table>
</div>

</body>
</html>