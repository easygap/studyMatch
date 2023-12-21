package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GroupDAO;
import model.GroupDTO;

@WebServlet("/board/Match.do")
public class GroupMatchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		GroupDAO dao = new GroupDAO();
		GroupDTO dto = new GroupDTO();

		// DTO에 회원 정보 저장
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");

		String mode = "";

		String information1 = req.getParameter("information1");
		String information2 = req.getParameter("information2");
		String groupNum1 = "";
		String groupNum2 = "";

		if (information1 != null) {
			groupNum1 = req.getParameter("groupNum1");
			System.out.println("그룹 매치에서 information1 : " + information1 + ", groupNum : " + groupNum1 );
		} else if (information2 != null) {
			groupNum2 = req.getParameter("groupNum2");
			System.out.println("그룹 매치에서 information2 : " + information2 + ", groupNum : " + groupNum2 );
		}

		// jsp 페이지로 forward 이동
		req.getRequestDispatcher("../board/MainPage.jsp").forward(req, resp);
	}
}