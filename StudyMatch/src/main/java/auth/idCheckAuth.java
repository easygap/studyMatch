package auth;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" IDCheck : doGet()");

		// DB 연결
		MemberDAO dao = new MemberDAO();
		String id = request.getParameter("idCheck");
		String idChe = dao.idCheck(id);
		dao.close();
		
		// id가 DB에 같은 값이 있는지 없는지 확인 조건문
		// id가 DB에 저장되어 있는 값이 없다면 아래 Parameter 값 전달
		if (idChe.equals("Y")) {
			request.getRequestDispatcher("/auth/idCheck.jsp?idCheck=" + idChe + "&id=" + id).forward(request,
					response);
			System.out.println("사용 가능한 아이디입니다.");
			
		// id가 DB에 저장되어 있는 값이 있다면 아래 Parameter 값 전달
		} else if (idChe.equals("N")) {
			request.getRequestDispatcher("/auth/idCheck.jsp?idCheck=" + idChe + "&id=" + id).forward(request,
					response);
			System.out.println("중복된 아이디입니다.");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		System.out.println(" IDCheck : doPost()");
	}
}
