package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GroupDAO;

@WebServlet("/Match/MatchLeaving.do")
public class MatchLeaving extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/MatchHistory/MatchHistory.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GroupDAO dao = new GroupDAO();

		/** 회원 ID 저장 */
		HttpSession session = req.getSession();
		String MatchID = (String) session.getAttribute("user");

		String GroupNum = req.getParameter("groupNum");

		if (MatchID != null) {
			dao.Leaving(MatchID, GroupNum);
			if (GroupNum == null) {
				System.out.println("탈퇴 성공!!!");
			}
		} else {
			System.out.println("탈퇴 실패...");
		}
		dao.close();
		doGet(req, resp);
	}
}
