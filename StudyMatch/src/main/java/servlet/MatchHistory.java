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
		
		List<String> PreviousImg;
		List<String> PreviousNames;

		// DB 연결
		if (MatchID != null) {
			Map<String, List<String>> NameImg = dao.getProfile(MatchID);
			Map<String, List<String>> Previous = dao.PreviousList(MatchID);

			// 현재 내가 속한 그룹 정보
			profileImages = NameImg.get("Img");
			getGroupName = NameImg.get("Names");
			groupNum = NameImg.get("group_num");
			
			// 이전에 내가 가입했다가 탈퇴한 그룹 정보
			PreviousNames = Previous.get("PreviousNames");
			PreviousImg = Previous.get("PreviousImg");

			if (!groupNum.isEmpty()) { Group_Num = groupNum.get(0); }

			// 현재 내가 속한 그룹 정보
			req.setAttribute("img", profileImages);
			req.setAttribute("name", getGroupName);
			
			// 이전에 내가 가입했다가 탈퇴한 그룹 정보
			req.setAttribute("previousImg", PreviousImg);
			req.setAttribute("previousNames", PreviousNames);
			
			req.setAttribute("dto", dto);
			System.out.println("match hitory DB연결 성공 ! ! !");
		} else {
			System.out.println("match hitory DB연결 실패 . . .");
		}
		dto.setGroup_Num(Group_Num);
		dao.close();

		System.out.println("Match GroupID : " + MatchID);
		System.out.println("------------------------------------");

		req.getRequestDispatcher("/board/MatchHistory.jsp").forward(req, resp);
	}
}
