package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/auth/LoginAuth.do")
public class LoginAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher dis = req.getRequestDispatcher("../auth/Login.jsp");
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
			HttpSession session = req.getSession();
			session.setAttribute("user", dto);
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			long creationTime = session.getCreationTime(); // 최초 요청 시간
			String creationTimeStr = dateFormat.format(new Date(creationTime));

			long lastTime = session.getLastAccessedTime(); // 마지막 요청 시간
			String lastTimeStr = dateFormat.format(new Date(lastTime));			
			resp.getWriter().write("success");
			resp.sendRedirect(req.getContextPath() + "/board/MainPage.jsp");
			
			System.out.println("------------------------------");
			System.out.println(date.format(now) + " [ " + id + " ] 로그인 성공 - session 저장 완료");
			System.out.println("세션 아이디: " + session.getId());
			System.out.println("세션 유지 시간: " + session.getMaxInactiveInterval());
			System.out.println("최초 요청 시간: " + creationTimeStr);
			System.out.println("마지막 요청 시간: " + lastTimeStr);
			System.out.println("------------------------------");
		} else {
			resp.getWriter().write("fail");
			resp.sendRedirect("../auth/Login.jsp");
			System.out.println("로그인 실패 - 페이지 이동 안 함");
		}
		dao.close();
	}
}