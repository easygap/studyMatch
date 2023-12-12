package servlet;

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
		System.out.println("doGet()");
		req.setCharacterEncoding("UTF-8");
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		System.out.println("doPost()");
		req.setCharacterEncoding("UTF-8");
		
		String pwId = req.getParameter("pwId");
		String pwPhone = req.getParameter("pwPhone");
		String pwBirth = req.getParameter("pwBirth");
		
		System.out.println(pwId);
		
		MemberDAO dao = new MemberDAO();
		String pwSearch = dao.pwSearch(pwId, pwPhone, pwBirth);
		
		if(pwSearch != null) {
			if(pwSearch.equals(pwSearch)) {
				req.getRequestDispatcher("/auth/IdPwSearch.jsp?pwId=" + pwId).forward(req, resp);
				System.out.println("[ " + pwId + " ] 비밀번호 찾기 성공");
			}
		}else{
			String nullPW = "Y";
			req.getRequestDispatcher("/auth/IdPwSearch.jsp?nullPW=" + nullPW).forward(req, resp);
			System.out.println("일치한 정보가 없습니다.");
		}
		
		dao.close();
		
	}

}
