package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testUpdateController
 */
@WebServlet("/update.me")
public class testUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public testUpdateController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interestArr = request.getParameterValues("interest");
		
		String interest = "";
		if(interestArr != null) {
			interest = String.join(", ", interestArr);
		}
		Member m = new Member(userId, userName, phone, email, address, interest);
		
		Member updateMem = new MemberService().updateMember(m);
		
		if(updateMem == null) {  // 회원정보 수정 실패
			request.setAttribute("errorMsg", "회원정보수정에 실패하였습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		} else {  // 회원정보 수정 성공
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", updateMem);// 동일 키값으로 값 대입 시 덮어쓰기됨
			session.setAttribute("alertMsg", "회원정보가 수정되었습니다.");
			response.sendRedirect(request.getContextPath()+"/myPage.me");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}