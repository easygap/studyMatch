package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardDTO;

@WebServlet("/board/view.do")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시글 불러오기
		BoardDAO dao = new BoardDAO();
		
		String num = req.getParameter("num");
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
		req.getRequestDispatcher("../board/View.jsp").forward(req, resp);
	}
}