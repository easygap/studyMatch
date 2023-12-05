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

/**
 * Servlet implementation class ListController2
 */
@WebServlet("/ListController")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		BoardDAO dao = new BoardDAO();
		Map<String, Object> map = new HashMap<String, Object>();
	    String searchField = request.getParameter("searchField");
	    String searchWord = request.getParameter("searchWord");
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
	    String pageTemp = request.getParameter("pageNum");
	    if (pageTemp != null && !pageTemp.equals(""))
	        pageNum = Integer.parseInt(pageTemp);
	    
	    // 목록에 출력할 게시물 범위
	    int start = (pageNum - 1) * pageSize + 1;
	    int end = pageNum * pageSize;
	    map.put("start", start);
	    map.put("end", end);
	    
	    List<BoardDTO> boardLists = dao.selectList(map);
	    dao.close();
	    
	    String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../board/list.jsp");
	    map.put("pagingImg", pagingImg);
	    map.put("totalCount", totalCount);
	    map.put("pageSize", pageSize);
	    map.put("pageNum", pageNum);
	    
	    request.setAttribute("boardLists", boardLists);
	    request.setAttribute("map", map);
	    request.getRequestDispatcher("/Board/List.jsp").forward(request, response);
	    System.out.println("BoardLists Size: " + boardLists.size());
	}
}