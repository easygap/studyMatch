package group;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CommentDTO;
import model.GroupBoardDAO;
import model.GroupBoardDTO;

@WebServlet("/board/GroupView.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("안녕 나는 view라고 해");
		// 게시글 불러오기
		GroupBoardDAO dao = new GroupBoardDAO();

		String num = req.getParameter("num");
		String GroupNum = req.getParameter("groupnum");

		String result = "N";

		// 삭제하기 & 수정하기 버튼 visible/invisible 처리
		String GroupboardID = dao.checkSession(num, GroupNum);
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");
		System.out.println("현재 사용자 ID :" + sessionID + " / 게시판 ID : " + GroupboardID + "입니다.");
		if (sessionID != null && sessionID.equals(GroupboardID)) {
			result = "Y";
		}
		
		// 댓글 삭제 & 수정
		ArrayList<CommentDTO> commList = dao.getList(num);
		ArrayList<Boolean> permissions = new ArrayList<>();

		for (CommentDTO comment : commList) {
		    String commId = comment.getId();
		    boolean permission = (sessionID != null && commId.equals(sessionID));
		    permissions.add(permission);
		}

		dao.updateVisitCount(num); // 조회수 1 증가
		GroupBoardDTO dto = dao.selectView(num);
		dao.close();
		// 줄 바꿈 처리
		dto.setContent(dto.getContent().replace("\r\n", "<br/>"));

		// 게시물(dto) 저장 후 뷰로 포워드
		req.setAttribute("dto", dto);
		req.setAttribute("permissions", permissions);

		req.getRequestDispatcher("../GroupBoard/View.jsp?result=" + result).forward(req, resp);
	}
}