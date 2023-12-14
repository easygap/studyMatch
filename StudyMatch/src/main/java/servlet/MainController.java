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

@WebServlet("/board/Main.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		GroupDAO dao = new GroupDAO();
		GroupDTO dto = new GroupDTO();
		
		// DTO에 회원 정보 저장
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");
				
		String NickName = dao.getUserName(sessionID);
		
		dto.setId(sessionID);
		dto.setNickName(NickName);
		
		// request 영역에 DTO 담기
		req.setAttribute("dto", dto);
		
		// jsp 페이지로 forward 이동
		req.getRequestDispatcher("../board/MainPage.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}