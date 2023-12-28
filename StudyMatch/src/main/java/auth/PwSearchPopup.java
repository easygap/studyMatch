package auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;

@WebServlet("/auth/PwSearchPopup.do")
public class PwSearchPopup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("PwSearchPopup : doGet()");
		req.setCharacterEncoding("UTF-8");

		// View에서 값 받아오기
		String id = req.getParameter("ID");
		String pwChan = req.getParameter("pw");
		String pwCheck = req.getParameter("pwCheck");

		MemberDAO dao = new MemberDAO();

		// DB 연결
		if (id != null) {
			// 비밀번호가 동일할 경우에만 변경 가능.
			if (pwChan.equals(pwCheck)) {
				dao.pwChange(pwChan, id);
				System.out.println("[ " + id + " ] 비밀번호 변경 성공!!!!!");
			} else {
				System.out.println("[ " + id + " ] 비밀번호 변경 실패,,,,");
			}
		}
		dao.close();
		req.getRequestDispatcher("/auth/PwSearchPopup.jsp?id=" + id).forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		System.out.println("PwSearchPopup : doPost()");
	}
}
