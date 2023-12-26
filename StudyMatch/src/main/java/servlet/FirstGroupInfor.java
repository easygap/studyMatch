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

@WebServlet("/Match/FirstGroupInfor.do")
public class FirstGroupInfor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		GroupDAO dao = new GroupDAO();

		/** 회원 ID 저장 */
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");

		String information1 = req.getParameter("information1");

		String groupNum1 = req.getParameter("groupNum1");

		System.out.println("몰라 인마! 너가 알아서 좀 해 인마! 흥칫뿡 : " + information1 + " / " + groupNum1);

		List<String> groupNameList;
		List<String> groupImgList;
		List<String> groupBirthList;
		List<String> groupJobList;
		List<String> groupInterest1List;
		List<String> groupInterest2List;
		List<String> groupInterest3List;

		/** 사용자가 1번 그룹의 '상 세 정 보'를 눌렀을 때 */
		if (information1 != null) {
			if (groupNum1 != null) {
				Map<String, List<String>> information = dao.groupInformation(groupNum1);

				groupImgList = information.get("groupImg");
				groupNameList = information.get("groupName");
				groupBirthList = information.get("groupBirth");
				groupJobList = information.get("groupJob");
				groupInterest1List = information.get("groupinterest1");
				groupInterest2List = information.get("groupinterest2");
				groupInterest3List = information.get("groupinterest3");
				if (groupImgList != null && groupNameList != null && groupBirthList != null && groupJobList != null
						&& groupInterest1List != null && groupInterest2List != null && groupInterest3List != null) {
					req.setAttribute("img", groupImgList);
					req.setAttribute("name", groupNameList);
					req.setAttribute("birth", groupBirthList);
					req.setAttribute("job", groupJobList);
					req.setAttribute("interest1", groupInterest1List);
					req.setAttribute("interest2", groupInterest2List);
					req.setAttribute("interest3", groupInterest3List);

					System.out.println("그룹 매치에서 groupImgList : " + groupImgList + " / groupNameList : " + groupNameList
							+ " / groupBirthList : " + groupBirthList + " / groupJobList : " + groupJobList
							+ " / groupInterest1List : " + groupInterest1List + " / groupInterest2List : "
							+ groupInterest2List + " / groupInterest3List : " + groupInterest3List + ", groupNum : "
							+ groupNum1);
				}
			}
			dao.close();
			req.getRequestDispatcher("/board/MatchInformation.jsp").forward(req, resp);
//			JSFunction.alertMainPage(resp);
		}
	}
}
