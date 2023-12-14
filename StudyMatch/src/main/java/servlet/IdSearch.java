package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;

@WebServlet("/auth/IdSearch.do")
public class IdSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(" IDSearch : doGet()");
		req.setCharacterEncoding("UTF-8");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		System.out.println(" IDSearch : doPost()");
		req.setCharacterEncoding("UTF-8");

		String name = req.getParameter("idName");
		String phone = req.getParameter("idPhone");

		// DB 연결
		MemberDAO dao = new MemberDAO();
		String IdSearch = dao.idSearch(name, phone);
		dao.close();
		
		// IdSearch가 NOT NULL일 대 아래 조건 문 실행
		if (IdSearch != null) {
			// DB에 연결하는 이름과 연락처가 같을 때 아래 Parameter 값 전달
			if (IdSearch.equals(IdSearch)) {
				req.getRequestDispatcher("/auth/IdPwSearch.jsp?id=" + IdSearch).forward(req, resp);
				System.out.println("[ " + name + " ] 아이디 찾기 성공");
			}
			// DB에 연결하는 이름과 연락처가 다를 때 아래 Parameter 값 전달
		} else {
			String nullID = "Y";
			req.getRequestDispatcher("/auth/IdPwSearch.jsp?nullID=" + nullID).forward(req, resp);
			System.out.println("아이디 정보 없음");
		}
	}
}
