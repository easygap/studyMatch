package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GroupDAO;
import model.GroupDTO;

@WebServlet("/match/MatchHistory.do")
public class MatchHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GroupDAO dao = new GroupDAO();
		GroupDTO dto = new GroupDTO();

		System.out.println("---------- Match History -----------");
		System.out.println("매칭현황 : doGet()");

		// Session ID 받아오기
		HttpSession session = req.getSession();
		String MatchID = (String) session.getAttribute("user");

		List<String> profileImages;
		List<String> getGroupName;
		List<String> groupNum;
		String Group_Num = "";

		// DB 연결
		if (MatchID != null) {
			Map<String, List<String>> NameImg = dao.getProfile(MatchID);

			profileImages = NameImg.get("Img");
			getGroupName = NameImg.get("Names");
			groupNum = NameImg.get("group_num");

			if (!groupNum.isEmpty()) { Group_Num = groupNum.get(0); }

			req.setAttribute("img", profileImages);
			req.setAttribute("name", getGroupName);
			req.setAttribute("dto", dto);

			System.out.println("서블렛에서 : " + profileImages + " / " + getGroupName + " / " + Group_Num + "\n");
			System.out.println("match hitory DB연결 성공 ! ! !");
		} else {
			System.out.println("match hitory DB연결 실패 . . .");
		}
		dto.setGroup_Num(Group_Num);
		dao.close();

		System.out.println("Match GroupID : " + MatchID);
		System.out.println("------------------------------------");

		req.getRequestDispatcher("/MatchHistory/MatchHistory.jsp").forward(req, resp);
	}
}
