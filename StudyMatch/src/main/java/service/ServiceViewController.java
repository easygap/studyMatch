package service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDAO;
import model.BoardDTO;
import model.CommentDAO;
import model.CommentDTO;

@WebServlet("/service/ServiceView.do")
public class ServiceViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("안녕 나는 view라고 해");
		// 게시글 불러오기
		ServiceDAO dao = new ServiceDAO();
		ServiceDTO dto = new ServiceDTO();

		String num = req.getParameter("num");
		int inquiry_num = Integer.parseInt(num);
		System.out.println("inquiey_num: " + inquiry_num);
		String result = "N";

		// 삭제하기 & 수정하기 버튼 visible/invisible 처리
//		String adminId = admin이랑 비교해서 권한 있는 사람만 댓글 작성 및 수정
		String inquiryID = dao.checkSession(inquiry_num);
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");
		System.out.println("현재 사용자 ID :" + sessionID + " / 게시판 ID : " + inquiryID + "입니다.");
		if (sessionID != null && sessionID.equals(inquiryID)) {
			result = "Y";
		}
		
		// 댓글 삭제 & 수정
		ArrayList<ServiceDTO> commList = dao.getList(inquiry_num);
		ArrayList<Boolean> permissions = new ArrayList<>();

		for (ServiceDTO comment : commList) {
		    String answerId = comment.getAnswer_id();
		    if (answerId != null) {
		        boolean permission = (sessionID != null && answerId.equals(sessionID));
		        permissions.add(permission);
		    } else {
		        permissions.add(false);
		    }
		}

		dto = dao.selectView(inquiry_num);
		dao.close();
		// 줄 바꿈 처리
		dto.setContent(dto.getContent().replace("\r\n", "<br/>"));

		// 게시물(dto) 저장 후 뷰로 포워드
		req.setAttribute("dto", dto);
		req.setAttribute("permissions", permissions);

		req.getRequestDispatcher("../service/ServiceView.jsp?result=" + result + "&num=" + inquiry_num).forward(req, resp);
	}
}