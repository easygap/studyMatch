package service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/service/AnswerEdit.do")
public class AnswerEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		ServiceDAO dao = new ServiceDAO();
		ServiceDTO dto = new ServiceDTO();

		String action = req.getParameter("action");
		String num = req.getParameter("num");
		int inquiry_num = Integer.parseInt(num);
		String content = req.getParameter("answerContent");
		String id = req.getParameter("answerId");
		
		
		dto.setAnswer_content(content);
		dto.setAnswer_id(id);
		
		// session에서 id 받기
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");

		System.out.println("------------- 댓글 삭제 컨트롤러 ------------");
		System.out.println("게시물 번호 : " + inquiry_num);
		System.out.println("View에서 요청 : " + inquiry_num + " " + action);
		System.out.println("댓글 작성 아이디 : " + id);
		System.out.println("로그인 아이디 : " + sessionID);
		System.out.println("------------- 댓글 삭제 컨트롤러 ------------");

		if (sessionID != null && sessionID.equals(id) && "delete".equals(action)) {
			try {
				dao.deleteAnswer(inquiry_num);
				resp.sendRedirect("../service/ServiceView.do?num=" + inquiry_num);
				System.out.println("Controller 댓글 삭제 완");
			} catch (Exception e) {
				resp.sendRedirect("../service/ServiceView.do?num=" + inquiry_num);
				e.printStackTrace();
				System.out.println("*** Controller 댓글 삭제 실패 ***");
			}
		}
		dao.close();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		ServiceDAO dao = new ServiceDAO();
		ServiceDTO dto = new ServiceDTO();

		String action = req.getParameter("action");
		String num = req.getParameter("inquiry_num");
		int inquiry_num = Integer.parseInt(num);
		String content = req.getParameter("answerContent");
		String id = req.getParameter("answerId");

		// session에서 id 받기
		ArrayList<String> answerIds = dao.checkId(inquiry_num);
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");

		System.out.println("------------- 댓글 수정 컨트롤러 ------------");
		System.out.println("게시물 번호 : " + inquiry_num);
		System.out.println("View에서 요청 : " + action);
		System.out.println("댓글 작성 아이디 : " + id);
		System.out.println("댓글 내용 : " + content);
		System.out.println("로그인 아이디 : " + sessionID);
		System.out.println("------------- 댓글 수정 컨트롤러 ------------");

		if (sessionID != null && sessionID.equals(id) && "edit".equals(action)) {
			System.out.println("답변 수정 요청");
			dto.setAnswer_content(content);
			dto.setInquiry_num(inquiry_num);

			try {
				dao.updateAnswer(dto);
				resp.sendRedirect("../service/ServiceView.do?num=" + inquiry_num);
				System.out.println("Controller 댓글 수정 완");
			} catch (Exception e) {
				resp.sendRedirect("../service/ServiceView.do?num=" + inquiry_num);
				e.printStackTrace();
				System.out.println("*** Controller 댓글 수정 실패 ***");
			}
			dao.close();
		}
	}
}