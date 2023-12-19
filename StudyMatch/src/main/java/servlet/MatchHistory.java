package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GroupDAO;

@WebServlet("/match/MatchHistory.do")
public class MatchHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GroupDAO dao = new GroupDAO();

		System.out.println("---------- Match History -----------");
		System.out.println("매칭현황 : doGet()");

		// Session ID 받아오기
		HttpSession session = req.getSession();
		String MatchID = (String) session.getAttribute("user");

		// DB 연결
		if (MatchID != null) {
			List<String> profileImages = dao.getProfile(MatchID);
			List<String> getGroupName = dao.getGroupName(MatchID);
			
			req.setAttribute("profileImages", profileImages);
			req.setAttribute("getGroupName", getGroupName);

			System.out.println("서블렛에서 : " + profileImages);
			System.out.println("서블렛에서 : " + getGroupName + "\n");
			System.out.println("match hitory DB연결 성공 ! ! !");
		} else {
			System.out.println("match hitory DB연결 실패 . . .");
		}
		dao.close();

		System.out.println("Match GroupID : " + MatchID);
		System.out.println("------------------------------------");
		
		req.getRequestDispatcher("/MatchHistory/MatchHistory.jsp").forward(req, resp);
	}
}
