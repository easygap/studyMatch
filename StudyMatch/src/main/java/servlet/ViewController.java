package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDAO;
import model.BoardDTO;

@WebServlet("/board/view.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시글 불러오기
		BoardDAO dao = new BoardDAO();
				
		String num = req.getParameter("num");
		
		String result = "N";
				
		// 삭제하기 & 수정하기 버튼 visible/invisible 처리
		String boardID = dao.checkSession(num);
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");
		System.out.println("현재 사용자 ID :" + sessionID + " / 게시판 ID : " + boardID +"입니다.");
		if(sessionID != null && sessionID.equals(boardID)) {
			result = "Y";
		}		
		
		dao.updateVisitCount(num);	// 조회수 1 증가
		BoardDTO dto = dao.selectView(num);
		dao.close();
		
		if(dto.getBoard_num().equals("1001")) {
			dto.setBoard_num("영어");
		}
		// 줄 바꿈 처리
		dto.setContent(dto.getContent().replace("\r\n", "<br/>"));
		
		// 게시물(dto) 저장 후 뷰로 포워드
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("../board/View.jsp?result="+result).forward(req, resp);
	}
}