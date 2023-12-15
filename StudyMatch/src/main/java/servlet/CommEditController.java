package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CommentDAO;
import model.CommentDTO;

@WebServlet("/board/CommEdit.do")
public class CommEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		CommentDAO dao = new CommentDAO();
		CommentDTO dto = new CommentDTO();
		
		// session에서 id 받기
//		String id = dao.;

		String action = req.getParameter("action");
		String num = req.getParameter("num");
		String interest = req.getParameter("interest");
		String comm_num = dto.getCommen_num();
		String content = req.getParameter("content");
		System.out.println("Comments Edit Controller에서 게시글 번호 : " + num + ", interest값 : " + interest);
		System.out.println("Comments Edit Controller에서 댓글 번호 : " + comm_num);
		System.out.println("Comments Edit Controller에서 content값 : " + content);

		if ("delete".equals(action)) {
			dto.setInter_num(interest);
			dto.setBoard_num(num);
			dto.setCommen_num(comm_num);

			try {
				dao.deleteCommen();
				dao.close();
				req.getRequestDispatcher("../board/View.jsp?num=" + num + "&interest=" + interest);
				System.out.println("Controller 댓글 삭제 완");
			} catch (Exception e) {
				req.getRequestDispatcher("../board/View.jsp?num=" + num + "&interest=" + interest);
				e.printStackTrace();
				System.out.println("*** Controller 댓글 삭제 실패 ***");
			}
		} else if ("edit".equals(action)) {
			dto.setInter_num(interest);
			dto.setBoard_num(num);
			dto.setCommen_num(comm_num);
			dto.setContent(content);

			try {
				dao.updateComm(dto);
				dao.close();
				req.getRequestDispatcher("../board/View.jsp?num=" + num + "&interest=" + interest);
				System.out.println("Controller 댓글 수정 완");
			} catch (Exception e) {
				req.getRequestDispatcher("../board/View.jsp?num=" + num + "&interest=" + interest);
				e.printStackTrace();
				System.out.println("*** Controller 댓글 수정 실패 ***");
			}
		}
	}
}