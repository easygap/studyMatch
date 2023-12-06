package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/LoginAuth")
public class LoginAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO dao;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.getWriter().append("Served at: ").append(req.getContextPath());
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		// 인증 요청한 계정 객체에 저장
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");

		// DB에서 인증 요청한 계정 찾기
		MemberDTO dto = dao.getMemberDTO(id, pass);
		String memberNick = dto.getNick();

		if (id.isEmpty() || pass.isEmpty()) {
			req.setAttribute("autoMessage", "아이디 혹은 비밀번호를 입력해 주세요.");
			return;
		} else if (memberNick != null) {
			req.setAttribute("autoMessage", memberNick + " 회원님 반갑습니다! (´▽`ʃ♡ƪ)");
			System.out.println(date.format(now) + " [ " + id + " ] 로그인 성공");
		}
		
		String script = "<script>alert('" + req.getAttribute("autoMessage") + "');</script>";
		req.setAttribute("alertScript", script);
		req.getRequestDispatcher("/auth/Login.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}