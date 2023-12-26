package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testUpdatePwdController
 */
@WebServlet("/updatePwd.me")
public class testUpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public testUpdatePwdController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		Member updateMem = new MemberService().updatePwdMember(userId, userPwd, updatePwd);
		
		HttpSession session = request.getSession();
		
		if(updateMem == null) {
			session.setAttribute("alertMsg", "비밀번호 변경에 실패하였습니다.");
		} else {
			session.setAttribute("loginUser", updateMem);
			session.setAttribute("alertMsg", "비밀번호가 변경되었습니다.");
		}
		response.sendRedirect(request.getContextPath()+"/myPage.me");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}