package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GroupDAO;
import model.GroupDTO;

@WebServlet("/board/Main.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		GroupDAO dao = new GroupDAO();
		GroupDTO dto = new GroupDTO();

		// DTO에 회원 정보 저장
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");

		String NickName = dao.getUserName(sessionID);

		dto.setId(sessionID);
		dto.setNickName(NickName);
		
		// 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();

        // DateTimeFormatter를 사용하여 날짜를 "yyyy-MM-dd" 형식으로 포맷팅
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String YearThatYY = currentDate.format(formatter);

        System.out.println("현재 날짜: " + YearThatYY);

		// request 영역에 DTO 담기
		req.setAttribute("dto", dto);

		if (sessionID != null) {
			String interest1 = null;
			String interest2 = null;
			
			// 현재 회원 ID의 주소 정보 가져오기
			String address = dao.getaddress(sessionID);
			dto.setAddress(address);
						
			// 현재 회원 ID의 interest 정보 가져오기
			ArrayList<String> interest = dao.getMemberInterest(sessionID);
			
			/** 현 로그인 계정의 관심사가 2개 이상일 경우 */
			if (interest.size() != 1) {
				interest1 = interest.get(0);
				interest2 = interest.get(1);
				System.out.println("흥미 : " + interest1 + " 그리고 " + interest2);
				dto.setInterest1(interest1);
				dto.setInterest2(interest2);
				
				System.out.println("관심사 : " + interest1 +", 주소 : " + address);
				
				// 첫번째 그룹 매치 - 관심사, 주소와 맞는 Group_num 추천
				String groupNum1 = dao.getGroupData1(interest1, address);
				
				// 두번째 그룹 매치 - 흥미가 2개 이상일 때 interest2 값으로도 그룹 매치
				String groupNum2 = dao.getGroupData2(interest2, address, groupNum1);
				
				
			} else {	
				/** 현 로그인 계정의 관심사가 한개일 경우 */
				interest1 = interest.get(0);
				System.out.println("흥미 : " + interest1);
				dto.setInterest1(interest1);
				
				System.out.println("관심사 : " + interest1 +", 주소 : " + address);
				
				// 첫번째 그룹 매치 - 관심사, 주소와 맞는 Group_num 추천
				String groupNum1 = dao.getGroupData1(interest1, address);
				
				// 두번째 그룹 매치 - 흥미가 1개일 때 interest1 값으로만 그룹 매치
				String groupNum2 = dao.getGroupData2(interest1, address, groupNum1);
			}

		}

		// jsp 페이지로 forward 이동
		req.getRequestDispatcher("../board/MainPage.jsp").forward(req, resp);
	}
	
	/** Group_num 생성을 위한 처리 - 현 로그인 계정의 관심사로 한자리수의 코드 부여 */
	String interest(String input) {

        String interestCode = "";

        if ("영어".equals(input) || "일본어".equals(input) || "중국어".equals(input)) {
            interestCode = "1";
        } else if ("프론트/백엔드".equals(input) || "프로젝트".equals(input)) {
            interestCode = "2";
        } else if ("디자이너".equals(input) || "퍼블리셔".equals(input) || "프로젝트".equals(input)) {
            interestCode = "3";
        } else if ("엑셀/한글/워드".equals(input) || "회계".equals(input)) {
            interestCode = "4";
        } else if ("부동산".equals(input) || "투자".equals(input)) {
            interestCode = "5";
        } else {
            // 다른 경우에 대한 처리
            interestCode = "0"; // 혹은 다른 값을 지정하거나 필요에 따라 처리하세요.
        }
		return interestCode;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}