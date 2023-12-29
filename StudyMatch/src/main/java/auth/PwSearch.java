package auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;

@WebServlet("/auth/PwSearch.do")
public class PwSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("PwSearch : doGet()");
		req.setCharacterEncoding("UTF-8");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		System.out.println("PwSearch : doPost()");
		req.setCharacterEncoding("UTF-8");

		// View에서 값 받아오기
		String pwId = req.getParameter("pwId");
		String pwPhone = req.getParameter("pwPhone");
		String pwBirth = req.getParameter("pwBirth");

		System.out.println(pwId);

		// DB 연결
		MemberDAO dao = new MemberDAO();
		String pwSearch = dao.pwSearch(pwId, pwPhone, pwBirth);
		dao.close();
		
		//pwSearch가 NOT NULL일 때만 아래 조건 문 실행
		if (pwSearch != null) {
			//dao에 값이 전달되어 DB랑 값이 맞아, pwSearch가 같을 때 아래 Parameter 값 전달
			if (pwSearch.equals(pwSearch)) {
				req.getRequestDispatcher("/auth/IdPwSearch.jsp?pwId=" + pwId).forward(req, resp);
				System.out.println("[ " + pwId + " ] 비밀번호 찾기 성공");
			}
			// 값이 다를 땐 아래 Parameter 값 전달
		} else {
			String nullPW = "Y";
			req.getRequestDispatcher("/auth/IdPwSearch.jsp?nullPW=" + nullPW).forward(req, resp);
			System.out.println("일치한 정보가 없습니다.");
		}

	}

}
