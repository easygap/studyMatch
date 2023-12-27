package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GroupDAO;
import model.GroupDTO;
import utils.JSFunction;

@WebServlet("/board/Match1.do")
public class FirstGroupMatchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		GroupDAO dao = new GroupDAO();

		/** 회원 ID 저장 */
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");

		String groupNum1 = req.getParameter("firstGroup");

		List<String> groupNameList;
		List<String> groupImgList;
		List<String> groupBirthList;
		List<String> groupJobList;
		List<String> groupInterest1List;
		List<String> groupInterest2List;
		List<String> groupInterest3List;
		List<String> groupAgeList;

		/** 사용자가 1번 그룹의 '상 세 정 보'를 눌렀을 때 */
		if (groupNum1 != null) {
			Map<String, List<String>> information = dao.groupInformation(groupNum1);

			groupImgList = information.get("groupImg");
			groupNameList = information.get("groupName");
			groupAgeList = information.get("groupAge");
			groupJobList = information.get("groupJob");
			groupInterest1List = information.get("groupinterest1");
			groupInterest2List = information.get("groupinterest2");
			groupInterest3List = information.get("groupinterest3");
			
			req.setAttribute("img", groupImgList);
			req.setAttribute("name", groupNameList);
			req.setAttribute("Age", groupAgeList);
			req.setAttribute("job", groupJobList);
			req.setAttribute("interest1", groupInterest1List);
			req.setAttribute("interest2", groupInterest2List);
			req.setAttribute("interest3", groupInterest3List);

			req.getRequestDispatcher("../board/MatchInformation.jsp").forward(req, resp);
		}
		else { /** 사용자가 1번 그룹의 '매 치 하 기' 를 눌렀을 때 */
			groupNum1 = req.getParameter("groupNum1");
			System.out.println("그룹 매치에서 groupNum : " + groupNum1);

			/** 그룹 인원이 몇명인지 구하는 쿼리문 */
			int total_Count = dao.countGroupMember(groupNum1);
			/** 5명 모두 찼으면 메인페이지로 이동 */
			if (total_Count > 4) {
				req.setAttribute("makeGroup", "excess");
			}

			dao.groupJoin(groupNum1, sessionID, total_Count);

			
			req.getRequestDispatcher("../board/Main.do").forward(req, resp);
		}
		dao.close();
	}
}