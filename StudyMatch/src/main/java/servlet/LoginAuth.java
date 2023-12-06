package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/auth/Login.do")
public class LoginAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO dao;
	
	public LoginAuth() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dao = new MemberDAO();
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		try {
			Context iniCtx = new InitialContext();
			Context ctx = (Context) iniCtx.lookup("java:comp/env");
			DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/dbcp_myoracle");
			Connection con = dataSource.getConnection();
			System.out.println("로그인 - 커넥션 풀 연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 로그인 - 커넥션 풀 연결 중 예외 발생 ***");
		}

		// 인증 요청한 계정 객체에 저장
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		
		String url = "";

		// DB에서 인증 요청한 계정 찾기
		MemberDTO dto = dao.getMemberDTO(id, pass);

		String memberName = dto.getName();
		if (memberName != null) {
			req.setAttribute("autoMessage", memberName + " 회원님 반갑습니다! (´▽`ʃ♡ƪ)");
			url = "/layout/Main.jsp";
			System.out.println(date.format(now) + " [ " + id + " ] 로그인 성공");
		} else {
			req.setAttribute("autoMessage", "일치하는 회원 정보를 찾지 못했어요.（；´д｀）ゞ");
			url = "/auth/Login.jsp";
		}
		String script = "<script>alert('" + req.getAttribute("autoMessage") + "');</script>";
		req.setAttribute("alertScript", script);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}
}