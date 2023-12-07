package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.BoardDAO;
import model.BoardDTO;
import utils.BoardPage;

@WebServlet("/ListController")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().append("Served at: ").append(req.getContextPath());

		BoardDAO dao = new BoardDAO();
		Map<String, Object> map = new HashMap<String, Object>();
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		if (searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}

		int totalCount = dao.selectCount(map); // 게시물 개수

		// 페이지 처리
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));

		// 현재 페이지
		int pageNum = 1; // 기본값
		String pageTemp = req.getParameter("pageNum");
		if (pageTemp != null && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp);

		// 목록에 출력할 게시물 범위
		int start = (pageNum - 1) * pageSize + 1;
		int end = pageNum * pageSize;
		map.put("start", start);
		map.put("end", end);
		
		System.out.println("검색 결과는 총" + totalCount + "개 입니다.");

		String interest = req.getParameter("interest");
		System.out.println("servlet에서 가져온 interest 값 :" + interest);
		List<BoardDTO> boardLists = dao.selectList(map, interest);
		dao.close();

		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../board/list.do");
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);

		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		req.getRequestDispatcher("../board/List.jsp").forward(req, resp);
		System.out.println("BoardLists Size: " + boardLists.size());
	}
}