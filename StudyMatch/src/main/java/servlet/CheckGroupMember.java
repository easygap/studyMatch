package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GroupDAO;
import utils.JSFunction;

/**
 * Servlet implementation class CheckGroupMember
 */
@WebServlet("/board/CheckGroupMember.do")
public class CheckGroupMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		/** 현재 날짜 가져오기 */
        LocalDate currentDate = LocalDate.now();

        /** DateTimeFormatter를 사용하여 날짜를 "yyMMdd" 형식으로 포맷팅 */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String YearThatYY = currentDate.format(formatter);
		
        int count = 0;
		String groupID1 = req.getParameter("group1");
		if(!"".equals(groupID1))
			count++;
		String groupID2 = req.getParameter("group2");
		if(!"".equals(groupID2))
			count++;
		String groupID3 = req.getParameter("group3");
		if(!"".equals(groupID3))
			count++;
		String groupID4 = req.getParameter("group4");
		if(!"".equals(groupID4))
			count++;
		
		String address = req.getParameter("address");
		System.out.println("주소값은 : " + address + " Count 값은 : " + count);
		
		if( count == 0 )
			JSFunction .alertLocation(resp, "함께하실 그룹원을 선택해주세요.（；´д｀）ゞ", "../board/MakeGroup.jsp?address=" + address);
		else if(count == 1)
			JSFunction .alertLocation(resp, "본인 포함 3명부터 그룹 생성이 가능합니다.", "../board/MakeGroup.jsp?address=" + address);
		
		/** 흥미 값 num으로 변환*/
		String intrest = req.getParameter("interests");
		System.out.println("관심사 : " + intrest);
		if(intrest == null)
			JSFunction .alertLocation(resp, "관심사를 선택해주세요.（；´д｀）ゞ", "../board/MakeGroup.jsp?address=" + address);
		String interests = MemberInterest(intrest);
		
		String groupNum= interests + YearThatYY;
		
		System.out.println("새로운 그룹 num : " + groupNum);
		
		/** 회원 ID 저장 */
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");
		
		String[] strArray = { groupID1, groupID2, groupID3, groupID4 };
		
		String duplicationChk = "Y";
		
		/** 입력한 ID와 sessionID가 같다면 그룹 생성 불가능 */
		for (String str : strArray) {
            if (str.equals(sessionID)) 
            	duplicationChk = "N";	
		}
		
		if(duplicationChk.equals("N")) 
			JSFunction .alertLocation(resp, "본인 ID는 입력할 수 없습니다...", "../board/MakeGroup.jsp?address=" + address);
		

		/** 배열 null값 제거 */
		strArray = Arrays.stream(strArray)
				.distinct()
				.filter(s -> (s!=null && s.length() >0 ))
				.toArray(String[]::new);
		
		/** 데이터베이스 연결 */
		GroupDAO dao = new GroupDAO();
		
		/** 사용자가 입력한 ID가 존재하는지 확인 */
		int chkID = dao.checkId(strArray);
		
		/** ID가 존재한다면 */
		if(chkID == 1 && count > 1 && intrest != null && duplicationChk.equals("Y")) {
			dao.makeGroup(groupNum, sessionID, strArray, intrest, address);
			JSFunction .alertThenClose(resp, "그룹 생성에 성공하셨습니다.", "../board/MakeGroup.jsp");
		} else if(chkID == 0){
			
			JSFunction .alertBack(resp, "ID를 바르게 입력해주세요.（；´д｀）ゞ");
		}
		dao.close();
	}
	
	/** Group_num 생성을 위한 처리 - 현 로그인 계정의 관심사로 한자리수의 코드 부여 */
	private String MemberInterest(String input) {

        String interestCode = "";

        if ("영어".equals(input) || "일본어".equals(input) || "중국어".equals(input)) {
            interestCode = "1";
        } else if ("JAVA".equals(input) || "PYTHON".equals(input) || "C".equals(input) || "C++".equals(input)) {
            interestCode = "2";
        } else if ("디자이너".equals(input) || "퍼블리셔".equals(input) || "UI/UX".equals(input) || "JSP".equals(input)) {
            interestCode = "3";
        } else if ("엑셀/한글/워드".equals(input) || "회계".equals(input)) {
            interestCode = "4";
        } else if ("부동산".equals(input) || "투자/주식".equals(input)) {
            interestCode = "5";
        } else {
        	interestCode = "0";
        }
		return interestCode;
	}
}