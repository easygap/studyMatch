<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
</head>
<body>

<div align= "center">
	<form method="post" action="UpdateServlet" name="frm">
		<table align="center" width="600">
<div class="container-800">
         JSFunction .alertLogin(resp, dto.getNick() + " (" + dto.getId() + ") 님, 안녕하세요.</h1>
 
            <div class="w-75">
                <div>
                	<c:choose>
	                	<c:when test="${profile != null}">
	                		<img class="profile-img big" src="/attachment/download?attachmentNo=${profile.attachmentNo}">
	                    </c:when>
	                    <c:otherwise>
							<img class="profile-img big" src="/images/defaultProfile.png">
						</c:otherwise>
					</c:choose>
                </div>
                
                <div slign= "center" class="row">
                    <table class="table small">
                    	<h1>마이페이지</h1>
	      <form action="/memb/modify" method="post">
		        <table>
			   <tr>
				<tr>
                                <th class="w-30">아이디</th>
                                <td>${membersDto.memberID}변경 불가</td>

                            </tr>
			    <tr>
				<th>Password</th>
				<td><input type="text" name="pwd" value="${mvo.pwd }"></td>
			</tr>
                        <tbody class="left">
                            <tr>
                                <th>이름</th>
                                <td>${membersDto.memberLastName}${membersDto.memberFirstName}변경 불가</td>
	             
                            </tr>
                            <tr>
                                <th>닉네임</th>
                                <td><input type="text" name="nickName"> </td>
                            </tr>          
                            
                               <tr>
                                <th class="w-30">비밀번호</th>
                                <td><input type="text" name="Pass"> </td>>
                              </tr>
                               <tr>
                                <th class="w-30">비밀번호 확인</th>
                                <td><input type="text" name="Pass"> </td>>
                                </tr>
                                     <tr>
	                                <th class="w-30">생년월일</th>
	                                <td>${membersDto.memberbirth}변경 불가</td>
           	                        </td>
            	                        </tr>     		
	                                 		
	                             <tr>
                                <th>직업</th>
                                <td>${membersDto.memberjob}</td>
                                 </tr>  
                                 <tr>
	                                <th>전화번호</th>
		                                	<c:choose>
	                                		<c:when test="${membersDto.memberphone != null}">
	                                			${membersDto.memberphone}
	                                		</c:when>
                                        </c:choose>	     
	                            </tr>
                                 <tr>
                                  <th>이메일</th>
                                   <td>${membersDto.memberEmail}</td>
                                  </tr>
                                  <tr>
	                               <th>주소</th>
                                   <td>${membersDto.memberaddress}</td>
                            </tr>
                            <tr>
                                <th>관심사</th>
                                <td>${membersDto.memberLevel}
                                <select name="관심사">
                                <option value="none">===선택===</option>
                                <option value="interest">JAVA</option>
                                <option value="interest">PYTHON</option>
                                <option value="interest">C</option>
                                <option value="interest">C++</option>
                                <option value="interest">영어</option>
                                <option value="interest">일본어</option>
                                <option value="interest">중국어</option>
                                <option value="interest">UI/UX</option>
                                <option value="interest">JSP</option>
                                <option value="interest">디자이너</option>
                                <option value="interest">퍼블리셔</option>
                                <option value="interest">엑셀/한글/워드</option>
                                <option value="interest">회계</option>
                                <option value="interest">부동산</option>
                                <option value="interest">투자/주식</option>
                                </select>
                                </td>
                            </tr>
                           
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    </table>
 
 <div class="flex">
            <div class="w-25 me-40" style="position: relative; border-right: 0.5px solid rgb(169, 169, 169)">
                   <h3><a class="link" href="/members/changeInfo">개인정보 변경</a></h3>
                
                    <h3><a class="link" href="/members/changePW">비밀번호 변경</a></h3>
                    <a class="link warning" href="/members/leave" style="font-size: x-small;">회원 탈퇴</a>
                </div>
            </div>


</body>
</html>