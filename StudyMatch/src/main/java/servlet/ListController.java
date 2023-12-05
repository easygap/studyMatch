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
<<<<<<< HEAD

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

=======
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// DAO 생성
>>>>>>> branch 'master' of https://github.com/easygap/studyMatch.git
		BoardDAO dao = new BoardDAO();
<<<<<<< HEAD
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
=======
		
		// 뷰에 전달할 매개변수 저장용 맵 생성
		Map<String, Object> map = new HashMap<String, Object>();
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		if (searchWord != null) {
			// 쿼리 스트링으로 전달받은 매개변수 중 검색어가 있다면 map에 저장
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		int totalCount = dao.selectCount(map); // 게시물 개수
		
		/* 페이지 처리 시작 */
		ServletContext application = getServletContext();
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
		// 현재 페이지 확인
		int pageNum = 1; // 기본값
		String pageTemp = req.getParameter("pageNum");
		if (pageTemp != null && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp);	// 요청받은 페이지로 수정
		
		// 목록에 출력할 게시물 범위
		int start = (pageNum - 1) * pageSize + 1;	// 첫 게시물 번호
		int end = pageNum * pageSize;		// 마지막 게시물 번호
		map.put("start", start);
		map.put("end", end);
		/* 페이지 처리 종료 */
		
		List<BoardDTO> boardLists = dao.selectList(map);
		// 게시물 목록 받기
		dao.close();	// DB 연결 닫기
		
		// 뷰에 전달할 매개변수 추가
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../board/List.do");	// 바로가기 영역 HTML 문자열
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		// 전달할 데이터를 request 영역에 저장 후 List.jsp로 포워드
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		req.getRequestDispatcher("/List.jsp").forward(req, resp);
		System.out.println("BoardLists Size: " + boardLists.size());
>>>>>>> branch 'master' of https://github.com/easygap/studyMatch.git
	}

}
