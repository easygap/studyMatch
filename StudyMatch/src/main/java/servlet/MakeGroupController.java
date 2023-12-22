package servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GroupDAO;
import model.GroupDTO;

@WebServlet("/board/makeGroup.do")
public class MakeGroupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		GroupDAO dao = new GroupDAO();
		GroupDTO dto = new GroupDTO();

		/** 회원 ID 저장 */
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");
		System.out.println("makeGroup에서 유저 정보 : " + sessionID);
		
		dao.close();
		req.getRequestDispatcher("../board/Main.do").forward(req, resp);
	}
	
	/** Group_num 생성을 위한 처리 - 현 로그인 계정의 관심사로 한자리수의 코드 부여 */
	private String MemberInterest(String input) {

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
	
	/** group_num 생성을 위한 interest1 혹은 interest2 값을 난수로 준다. */
	private String getRandomGroupNum(String groupNum1, String groupNum2) {
        Random random = new Random();
        int randomNumber = random.nextInt(2); // 0 또는 1 생성

        // 랜덤으로 선택된 번호에 따라 groupNum1 또는 groupNum2 반환
        return (randomNumber == 0) ? groupNum1 : groupNum2;
	}
}