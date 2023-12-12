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
		
		System.out.println(pwId);
		
		MemberDAO dao = new MemberDAO();
		String result = dao.pwSearch(pwId, pwPhone);
		
		if(result.equals(result)) {
			System.out.println("[ " + pwId + " ] 비밀번호 찾기 성공");
		}else if(result.equals("") || result.equals(null)){
			System.out.println("일치한 정보가 없습니다.");
		}
		
		req.getRequestDispatcher("/auth/IdPwSearch.jsp?pwId=" + pwId).forward(req, resp);
		
	}

}
