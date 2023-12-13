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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet()");


		MemberDAO dao = new MemberDAO();


		String id = request.getParameter("idCheck");
		String idChe = dao.idCheck(id);

		if (idChe.equals("Y")) {
			request.getRequestDispatcher("/auth/idCheck.jsp?idCheck=" + idChe + "&id=" + id).forward(request,
					response);
			System.out.println("사용 가능한 아이디입니다.");
		} else if (idChe.equals("N")) {
			request.getRequestDispatcher("/auth/idCheck.jsp?idCheck=" + idChe + "&id=" + id).forward(request,
					response);
			System.out.println("중복된 아이디입니다.");
		}
		dao.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
