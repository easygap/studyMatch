package servlet;

import java.io.IOException;

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

//	public MatchHistory() {
//	 컨트롤러 생성자 (처음 시작할 때 한번 실행). key-value로 한묶음
//		actionMap.put("image", "../MatchHistory/MatchHistory.jsp");
//	 여기서 넣어준 key값으로 아래 doGet메서드가 실행되었을 때 key값이 action에 저장되고, 그 action을 통해 value값을 조회해 해당 주소로 forward해주는거임
//	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// action parameter 불러오기
//		String action = req.getParameter("action");
		// 만약 action이 없거나 actionMap에 초기값이 없으면 action에 home을 넣음
//		if(action == null || !actionMap.containsKey(action)) action = "image";

		// actionMap의 action값이 home이면 home.jsp페이지로, image면 image.jsp페이지로 이동
//		req.getRequestDispatcher(actionMap.get(action)).forward(req, resp);
		
		System.out.println("---------- Match History -----------");
		System.out.println("매칭현황 : doGet()");
		
		GroupDAO dao = new GroupDAO();
		HttpSession session = req.getSession();
		String MatchID = (String) session.getAttribute("user");
		String img = null;

		if (MatchID != null) {
			img = dao.getProfile(MatchID);
			req.getRequestDispatcher("/MatchHistory/MatchHistory.jsp?img=" + img).forward(req, resp);
			
			System.out.println("match hitory DB연결 성공");
		} else {
			req.getRequestDispatcher("/MatchHistory/MatchHistory.jsp").forward(req, resp);
			
			System.out.println("match hitory DB연결 실패");
		}

		dao.close();
		System.out.println("Match Group : " + MatchID);
		System.out.println("---------- Match History -----------");
	}

//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		 image.jsp에서 post방식으로 받은 parameter(action, image, rating 총 세 가지 속성의 각 값)를
//		 doGet으로 처리
//		doGet(req, resp);
//		System.out.println("매칭현황 : doPost()");
//		System.out.println("---------- Match History -----------");
//	}
}
