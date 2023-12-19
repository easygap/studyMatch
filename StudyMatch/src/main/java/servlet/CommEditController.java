package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CommentDAO;
import model.CommentDTO;

@WebServlet("/board/CommEdit.do")
public class CommEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		CommentDAO dao = new CommentDAO();
		CommentDTO dto = new CommentDTO();

		String action = req.getParameter("action");
		String num = req.getParameter("num");
		String interest = req.getParameter("interest");
//		String content = req.getParameter("commContent");
		String commNum = req.getParameter("commNum");
		String id = req.getParameter("id");

		// session에서 id 받기
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");

		System.out.println("------------- 댓글 삭제 컨트롤러 ------------");
		System.out.println("게시물 번호 : " + num);
		System.out.println("View에서 요청 : " + commNum + " " + action);
		System.out.println("댓글 작성 아이디 : " + id);
		System.out.println("로그인 아이디 : " + sessionID);
		System.out.println("------------- 댓글 삭제 컨트롤러 ------------");

		if (sessionID != null && sessionID.equals(id) && "delete".equals(action)) {
			try {
				dao.deleteCommen(commNum);
				dao.close();
				resp.sendRedirect("../board/view.do?num=" + num + "&interest=" + interest);
				System.out.println("Controller 댓글 삭제 완");
			} catch (Exception e) {
				resp.sendRedirect("../board/view.do?num=" + num + "&interest=" + interest);
				e.printStackTrace();
				System.out.println("*** Controller 댓글 삭제 실패 ***");
			}
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		CommentDAO dao = new CommentDAO();
		CommentDTO dto = new CommentDTO();

		String action = req.getParameter("action");
		String num = req.getParameter("num");
		String interest = req.getParameter("interest");
		String content = req.getParameter("commContent");
		String commNum = req.getParameter("commNum");
		String id = req.getParameter("id");

		// session에서 id 받기
		ArrayList<String> commIds = dao.checkId(num);
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");

		System.out.println("------------- 댓글 수정 컨트롤러 ------------");
		System.out.println("게시물 번호 : " + num);
		System.out.println("View에서 요청 : " + commNum + " " + action);
		System.out.println("댓글 작성 아이디 : " + id);
		System.out.println("로그인 아이디 : " + sessionID);
		System.out.println("------------- 댓글 수정 컨트롤러 ------------");

		if (sessionID != null && sessionID.equals(id) && "edit".equals(action)) {
			System.out.println("댓글 수정 요청");
			dto.setCommen_num(commNum);
			dto.setContent(content);

			try {
				dao.updateComm(dto);
				dao.close();
				resp.sendRedirect("../board/view.do?num=" + num + "&interest=" + interest);
				System.out.println("Controller 댓글 수정 완");
			} catch (Exception e) {
				resp.sendRedirect("../board/view.do?num=" + num + "&interest=" + interest);
				e.printStackTrace();
				System.out.println("*** Controller 댓글 수정 실패 ***");
			}
		}
	}
}