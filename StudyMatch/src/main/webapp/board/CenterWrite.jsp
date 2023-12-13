<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
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


            
    <table border="2">
<script language="javascript">
function OxfiDisplay(frm){
var Oxfi=frm.selectbank.selectedlndex;
switch(oxfi){
case 0:
	  ='선택시 과목이 표시 됩니다.';
oxfi;
case 1:
	  ='영어';
oxfi;
case 2:
	  ='일본어';
oxfi;
case 3:
	  ='중국어';
oxfi;
case 4:
	  ='디자이너';
oxfi;
case 5:
	  ='퍼블리셔';
oxfi;
case 6:
	  ='프로젝트';
oxfi;	  
case 7:
	  =엑셀/한글/워드';
oxfi;
case 8:
	  ='부동산';
oxfi;
case 9:
	  ='투자';
oxfi;
}
return true;	
}
</script>    
     </head>
  <form name = "form">
  <table border="1" width="500" bgcolor="#F5F6CE" align="center" cellspacing="0" cellpadding="3"
  bordercolor="#0000000" bordercolordark="#F5F6CE" bordercolorlight="#F5F6CE">  


					<tr>
                        <td><select class="form-control" name="searchField">
						
								<option value="engese">영어</option>
								<option value="japanese">일본어</option>
								<option value="chinese">중국어</option>
								<option value="designer">디자이너</option>
								<option value="Publishers">퍼블리셔</option>
								<option value="Project">프로젝트</option>
								<option value="Words">엑셀/한글/워드</option>
								<option value="Property">부동산</option>
								<option value="investment">투자</option>
								</select>
					
							<input type="text" placeholder="과정을 입력하세요!" style="width:350px;height:16px;font-size:9px;">
		
		</td>
		
			
							
					</tr>
					<td>		
					
									<textarea name = "ta2" rows = "20" cols = "65"
							wrap = "virtual">내용을 입력해 주세요.</textarea>		
							<div align="right">
						<button class="btn btn-primary">작성하기</button>			
							
					</tr>					
		
			</table>				
					
					
			<form>
			
			</div>
			</form>
			
			
			
				
		</div>
					


</body>

</html>