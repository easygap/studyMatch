package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GroupDAO;
import model.GroupDTO;

@WebServlet("/board/Main.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		GroupDAO dao = new GroupDAO();
		GroupDTO dto = new GroupDTO();

		// DTO에 회원 정보 저장
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");

		String NickName = dao.getUserName(sessionID);

		dto.setId(sessionID);
		dto.setNickName(NickName);

		// request 영역에 DTO 담기
		req.setAttribute("dto", dto);

		if (sessionID != null) {

			// 현재 회원 ID의 interest 정보 가져오기
			ArrayList<String> interest = dao.getGroupData(sessionID);
			if (interest.size() != 1) {
				String interest1 = interest.get(0);
				String interest2 = interest.get(1);
				System.out.println("흥미 : " + interest1 + " 그리고 " + interest2);
				dto.setInterest1(interest1);
				dto.setInterest2(interest2);
			} else {
				String interest1 = interest.get(0);
				System.out.println("흥미 : " + interest1);
				dto.setInterest1(interest1);
			}

			// 현재 회원 ID의 주소 정보 가져오기
			String address = dao.getaddress(sessionID);
			dto.setAddress(address);
		}

		// jsp 페이지로 forward 이동
		req.getRequestDispatcher("../board/MainPage.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}