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

@WebServlet("/board/Match2.do")
public class SecondGroupMatchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		GroupDAO dao = new GroupDAO();
		GroupDTO dto = new GroupDTO();

		/** 회원 ID 저장 */
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");

		String information2 = req.getParameter("information2");
		String newMatching2 = req.getParameter("match2");
		String groupNum2 = "";
		
		/** 사용자가 2번 그룹의 '상 세 정 보'를 눌렀을 때 */
		if (information2 != null) {	
			groupNum2 = req.getParameter("groupNum2");
			System.out.println("그룹 매치에서 information2 : " + information2 + ", groupNum : " + groupNum2);
			
			dao.close();
			req.getRequestDispatcher("../board/MatchInformation.jsp").forward(req, resp);
			
		} else {	/** 사용자가 2번 그룹의 '매 치 하 기' 를 눌렀을 때 */
			groupNum2 = req.getParameter("groupNum2");
			System.out.println("그룹 매치에서 information1 : " + information2 + ", groupNum : " + groupNum2);

			/** 그룹 인원이 몇명인지 구하는 쿼리문 */
			int total_Count = dao.countGroupMember(groupNum2);
			/** 5명 모두 찼으면 'makeGroup'의 값을 '초과'로 설정 */
			if (total_Count > 4) {
				dao.close();
				req.setAttribute("makeGroup", "excess");
			}
			
			dao.groupJoin(groupNum2, sessionID, total_Count);
			
			dao.close();
			req.getRequestDispatcher("../board/MainPage.jsp").forward(req, resp);
		}
	}
}