package auth;

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
import utils.JSFunction;

@WebServlet("/auth/LoginAuth.do")
public class LoginAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dis = req.getRequestDispatcher("../auth/Login.jsp");
		dis.forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		// 인증 요청한 계정 객체에 저장
		HttpSession session = req.getSession();
		MemberDTO dto = null;
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		MemberDAO dao = new MemberDAO();

		dto = dao.getMemberDTO(id, pass);
		dao.close();

		if (dto != null) {
			session.setAttribute("user", dto.getId());
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			long creationTime = session.getCreationTime(); // 최초 요청 시간
			String creationTimeStr = dateFormat.format(new Date(creationTime));
			long lastTime = session.getLastAccessedTime(); // 마지막 요청 시간

			String lastTimeStr = dateFormat.format(new Date(lastTime));

			// 로그인 성공 알람창
			JSFunction.alertLogin(resp, dto.getNick() + " (" + dto.getId() + ") 회원님 반갑습니다! (´▽`ʃ♡ƪ)",
					"../board/Main.do");
			System.out.println("------------------------------");
			System.out.println(date.format(now) + " [ " + dto.getId() + " ] 로그인 성공 - session 저장 완료");
			System.out.println("세션 아이디: " + session.getId());
			System.out.println("세션 유지 시간: " + session.getMaxInactiveInterval());
			System.out.println("최초 요청 시간: " + creationTimeStr);
			System.out.println("마지막 요청 시간: " + lastTimeStr);
		} else {
			// 로그인 실패 알람창
			JSFunction.alertLogin(resp, "일치하는 회원 정보를 찾지 못했어요.（；´д｀）ゞ", "../auth/Login.jsp");
			System.out.println("로그인 실패 - 페이지 이동 안 함");
		}
	}
}