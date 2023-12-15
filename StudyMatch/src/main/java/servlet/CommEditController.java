package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CommentDAO;
import model.CommentDTO;

@WebServlet("/board/CommEdit")
public class CommEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		CommentDAO dao = new CommentDAO();
		CommentDTO dto = new CommentDTO();
		
		
		String action = req.getParameter("action");
		String num = req.getParameter("num");
		System.out.println("게시물 번호 : " + num);
		String interest = req.getParameter("interest");
		String content = req.getParameter("commContent");
		System.out.println("댓글 내용 : " + content);
		String commNum = req.getParameter("commNum");
		
		// session에서 id 받기
		String id = dao.idCheck(num, commNum);
		HttpSession session = req.getSession();
		String sessionId = (String) session.getAttribute("user");

		System.out.println("------------- 댓글 수정 컨트롤러 ------------");
		System.out.println("View에서 요청 : " + commNum + " " + action);
		System.out.println("댓글 작성 아이디 : " + id);
		System.out.println("댓글 내용 : " + content);
		System.out.println("로그인 아이디 : " + sessionId);
		System.out.println("------------- 댓글 수정 컨트롤러 ------------");
		
		if (sessionId != null && sessionId.equals(id)) {
			if ("delete".equals(action)) {
				try {
					dao.deleteCommen(commNum);
					dao.close();
					resp.sendRedirect("../board/View.jsp?num=" + num + "&interest=" + interest);
					System.out.println("Controller 댓글 삭제 완");
				} catch (Exception e) {
					resp.sendRedirect("../board/View.jsp?num=" + num + "&interest=" + interest);
					e.printStackTrace();
					System.out.println("*** Controller 댓글 삭제 실패 ***");
				}
			} else if ("edit".equals(action)) {
				dto.setCommen_num(commNum);
				dto.setContent(content);
				
				try {
					dao.updateComm(dto);
					dao.close();
					req.getRequestDispatcher("../board/View.jsp?num=" + num + "&interest=" + interest).forward(req, resp);
					System.out.println("Controller 댓글 수정 완");
				} catch (Exception e) {
					req.getRequestDispatcher("../board/View.jsp?num=" + num + "&interest=" + interest).forward(req, resp);
					e.printStackTrace();
					System.out.println("*** Controller 댓글 수정 실패 ***");
				}
			}
		}

	}
}