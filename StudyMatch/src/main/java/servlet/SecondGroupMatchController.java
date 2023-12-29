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

@WebServlet("/board/Match2.do")
public class SecondGroupMatchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		GroupDAO dao = new GroupDAO();

		/** 회원 ID 저장 */
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");

		String groupNum2 = req.getParameter("secondGroup");
		
		System.out.println(" groupNum2 groupNum2 groupNum2 groupNum2 : " + groupNum2);
		
		List<String> groupNameList;
		List<String> groupImgList;
		List<String> groupJobList;
		List<String> groupInterest1List;
		List<String> groupInterest2List;
		List<String> groupInterest3List;
		List<String> groupAgeList;
		
		/** 사용자가 2번 그룹의 '상 세 정 보'를 눌렀을 때 */
		if (groupNum2 != null) {	
			Map<String, List<String>> information = dao.groupInformation(groupNum2);

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
			
		} else {	/** 사용자가 2번 그룹의 '매 치 하 기' 를 눌렀을 때 */
			groupNum2 = req.getParameter("groupNum2");
			System.out.println("그룹 매치에서 groupNum2 : " + groupNum2);

			/** 그룹 인원이 몇명인지 구하는 쿼리문 */
			int total_Count = dao.countGroupMember(groupNum2);
			/** 5명 모두 찼으면 'makeGroup'의 값을 '초과'로 설정 */
			if (total_Count > 4) {
				req.setAttribute("makeGroup", "excess");
			}
			
			dao.groupJoin(groupNum2, sessionID, total_Count);
				
			req.getRequestDispatcher("../board/Main.do").forward(req, resp);
		}
		dao.close();
	}
}