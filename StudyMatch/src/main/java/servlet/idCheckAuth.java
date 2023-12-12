package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;

@WebServlet("/auth/idCheckAuth.do")
public class idCheckAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");
		
		String id = request.getParameter("idCheck");
		
		MemberDAO dao = new MemberDAO();
		int result = dao.idCheck(id);
		
		if(result == 1) {
			System.out.println("사용 가능한 아이디입니다.");
		}else if(result == 0) {
			System.out.println("중복된 아이디입니다.");
		}else {
			System.out.println("에러발생!!!!!!!!!!!!");
		}
		dao.close();
		request.getRequestDispatcher("/auth/idCheck.jsp?idCheck=" + result).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		

	}

}
