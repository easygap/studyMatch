package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CommentDAO;
import model.CommentDTO;


@WebServlet("/service/AnswerWrite.do")
public class AnswerWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ServiceDAO dao = new ServiceDAO();
		ServiceDTO dto = new ServiceDTO();
		
		HttpSession session = req.getSession();
        Object userObject = session.getAttribute("user");
        String userId = null;
        String num = req.getParameter("inquiry_num");
        int inquiry_num = Integer.parseInt(num);
        String content = req.getParameter("answerContent");
        System.out.println("답변 inquiry_num 값 : " + inquiry_num);
        System.out.println("답변 내용 : " + content);
        
        if (userObject != null) {
        	userId = userObject.toString();
            System.out.println("답변 작성 아이디 : " + userId);
        } else {
        	resp.sendRedirect("../service/ServiceView.jsp?num=" + inquiry_num);
            System.out.println("*** 답변 작성 아이디 생성 실패 ***");
        	return;
        }

        dto.setAnswer_id(userId);
        dto.setAnswer_content(content);
        dto.setInquiry_num(inquiry_num);
        
        try {
            int result = dao.insertAnswer(dto);

            if (result == 1) {
                req.getRequestDispatcher("../service/ServiceView.do?num=" + inquiry_num).forward(req, resp);
                System.out.println(inquiry_num + " 답변 작성 및 DB 업로드 완료!");
            } else {
                req.getRequestDispatcher("../service/ServiceView.do?num=" + inquiry_num).forward(req, resp);
                System.out.println("*** 답변 업로드 실패 ***");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("답변 입력 서블릿 실행 실패");
        }
		dao.close();
	}
}