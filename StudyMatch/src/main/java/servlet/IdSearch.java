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
		System.out.println("doGet()");
		req.setCharacterEncoding("UTF-8");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		req.setCharacterEncoding("UTF-8");

		String name = req.getParameter("idName");
		String phone = req.getParameter("idPhone");

		System.out.println(name);

		MemberDAO dao = new MemberDAO();
		String IdSearch = dao.idSearch(name, phone);
		

		if (IdSearch != null) {
			if (IdSearch.equals(IdSearch)) {
				req.getRequestDispatcher("/auth/IdPwSearch.jsp?id=" + IdSearch).forward(req, resp);
				System.out.println("[ " + name + " ] 아이디 찾기 성공");
			}
		} else{
			String nullID = "Y";
			req.getRequestDispatcher("/auth/IdPwSearch.jsp?nullID=" + nullID).forward(req, resp);
			System.out.println("아이디 정보 없음");
		}

		
	}
}
