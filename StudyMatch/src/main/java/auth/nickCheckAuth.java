package auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;

@WebServlet("/auth/nickCheckAuth.do")
public class nickCheckAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" NickCheck : doGet()");

		// DB 연결
		MemberDAO dao = new MemberDAO();
		String nick = request.getParameter("nickCheck");
		String nickChe = dao.nickCheck(nick);
		dao.close();

		// id가 DB에 같은 값이 있는지 없는지 확인 조건문
		// id가 DB에 저장되어 있는 값이 없다면 아래 Parameter 값 전달
		if (nickChe.equals("Y")) {
			request.getRequestDispatcher("/auth/nickCheck.jsp?nickCheck=" + nickChe + "&nick=" + nick).forward(request,
					response);
			System.out.println("사용 가능한 ' 닉네임 ' 입니다.");

			// id가 DB에 저장되어 있는 값이 있다면 아래 Parameter 값 전달
		} else {
			request.getRequestDispatcher("/auth/nickCheck.jsp?nickCheck=" + nickChe + "&nick=" + nick).forward(request,
					response);
			System.out.println("중복된 ' 닉네임 ' 입니다.");
		}
	}
}
