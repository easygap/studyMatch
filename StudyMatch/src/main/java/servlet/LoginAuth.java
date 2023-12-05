package servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import member.MemberDAO;
import member.MemberDTO;

/**
 * Servlet implementation class LoginAuth
 */
@WebServlet("/LoginAuth")
public class LoginAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO dao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.getWriter().append("Served at: ").append(req.getContextPath());

		// 관리자 ID
		String admin_id = this.getInitParameter("admin_id");

		// 인증 요청한 계정
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");

		// DB에서 인증 요청한 계정 찾기
		MemberDTO dto = dao.getMemberDTO(id, pass);

		String memberNick = dto.getNickname();
		if (memberNick != null) {
			req.setAttribute("autoMessage", memberNick + " 회원님 반갑습니다! (´▽`ʃ♡ƪ)");
		} else {
			if (admin_id.equals(id))
				req.setAttribute("autoMessage", admin_id + " 님, 잘 부탁해요~! (｡･∀･)ﾉﾞ");
			else
				req.setAttribute("autoMessage", "일치하는 회원 정보를 찾지 못했어요. （；´д｀）ゞ");
		}
		String script = "<script>alert('" + req.getAttribute("autoMessage") + "');</script>";
		req.setAttribute("alertScript", script);
		req.getRequestDispatcher("/auth/Login.jsp").forward(req, resp);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
		try {
		Context iniCtx = new InitialContext();
		Context ctx = (Context) iniCtx.lookup("java:comp/env");
		DataSource dataSource = (DataSource) ctx.lookup("dbcp_myoracle");
		Connection con = dataSource.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 로그인 - 커넥션 풀 연결 중 예외 발생 ***");
		}

		dao = new MemberDAO();
	}

}