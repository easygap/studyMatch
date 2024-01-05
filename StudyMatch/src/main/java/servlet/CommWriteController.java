package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CommentDAO;
import model.CommentDTO;


@WebServlet("/board/CommWrite.do")
public class CommWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		CommentDAO dao = new CommentDAO();
		CommentDTO dto = new CommentDTO();
		
		HttpSession session = req.getSession();
        Object userObject = session.getAttribute("user");
        String userId = null;
        String internum = req.getParameter("interest");
        String boardnum = req.getParameter("num");
        String content = req.getParameter("commContent");
        System.out.println("댓글 interest 값 : " + internum + ", boardnum 값 : " + boardnum);
        System.out.println("댓글 내용 : " + content);
        
        if (userObject != null) {
        	userId = userObject.toString();
            System.out.println("댓글 작성 아이디 : " + userId);
        } else {
        	resp.sendRedirect("../board/View.jsp?num=" + boardnum + "&interest=" + internum);
            System.out.println("*** 댓글 작성 아이디 생성 실패 ***");
        	return;
        }
        
        dto.setInter_num(internum);
        dto.setBoard_num(boardnum);
        dto.setId(userId);
        dto.setContent(content);
        
        try {
            int result = dao.insertComm(dto);

            if (result == 1) {
                req.getRequestDispatcher("../board/view.do?num=" + boardnum + "&interest=" + internum).forward(req, resp);
                System.out.println(boardnum + " 댓글 작성 및 DB 업로드 완료!");
            } else {
                req.getRequestDispatcher("../board/view.do?num=" + boardnum + "&interest=" + internum).forward(req, resp);
                System.out.println("*** 댓글 업로드 실패 ***");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("댓글 입력 서블릿 실행 실패");
        }
		dao.close();
	}
}