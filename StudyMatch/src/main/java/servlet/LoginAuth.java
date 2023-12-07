package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet(urlPatterns = "/auth/LoginAuth.do")
public class LoginAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RequestDispatcher dis = req.getRequestDispatcher("Login.jsp");
		dis.forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		// 인증 요청한 계정 객체에 저장
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.getMemberDTO(id, pass);
		if (dto != null) {
			req.setAttribute("user", dto);
			System.out.println("로그인 성공 - 계정 정보 req 저장 완료");
			resp.sendRedirect(req.getContextPath() + "/auth/Login.jsp");
		} else {
			System.out.println("로그인 실패 - 페이지 이동 안 함");
			resp.sendRedirect("Login.jsp");
		}
		dao.close();
	}
}