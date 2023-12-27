package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class testController
 */
@WebServlet("/myPage.me")
public class testController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public testController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        // session의 속성값에 loginUser가 null일 때
		if(session.getAttribute("loginUser") == null) {
            // alert메시지 띄우고, 요청을 redirect하기
			session.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			response.sendRedirect(request.getContextPath());
		} else {
            // null이 아닐 때 요청을 myPage로 보내고 request와 response를 forward하기
			request.getRequestDispatcher("views/member/myPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}