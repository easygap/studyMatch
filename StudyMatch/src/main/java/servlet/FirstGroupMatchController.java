package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GroupDAO;
import model.GroupDTO;

@WebServlet("/board/Match1.do")
public class FirstGroupMatchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		GroupDAO dao = new GroupDAO();
		GroupDTO dto = new GroupDTO();

		/** 회원 ID 저장 */
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");

		String information1 = req.getParameter("information1");

		String newMatching1 = req.getParameter("match1");

		String groupNum1 = "";

		
		/** 사용자가 1번 그룹의 '상 세 정 보'를 눌렀을 때 */
		if (information1 != null) {
			groupNum1 = req.getParameter("groupNum1");
			System.out.println("그룹 매치에서 information1 : " + information1 + ", groupNum : " + groupNum1);
			
			dao.close();
			req.getRequestDispatcher("../board/MatchInformation.jsp").forward(req, resp);

		} else {	/** 사용자가 1번 그룹의 '매 치 하 기' 를 눌렀을 때 */
			groupNum1 = req.getParameter("groupNum1");
			System.out.println("그룹 매치에서 information1 : " + information1 + ", groupNum : " + groupNum1);
			
			/** 그룹 인원이 몇명인지 구하는 쿼리문 */
			int total_Count = dao.countGroupMember(groupNum1);
			/** 5명 모두 찼으면 메인페이지로 이동 */
			if (total_Count > 4) {
				dao.close();
				req.setAttribute("makeGroup", "excess");
			}
			
			dao.groupJoin(groupNum1, sessionID, total_Count);
			
			dao.close();
			req.getRequestDispatcher("../board/Main.do").forward(req, resp);
		}
	}
}