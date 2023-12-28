package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GroupDAO;

@WebServlet("/map/StudyMap.do")
public class KakaoMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String sessionID = (String) session.getAttribute("user");
		GroupDAO dao = new GroupDAO();
		String address = dao.getaddress(sessionID);
		
		req.setAttribute("address", address);
		
		dao.close();
		
		req.getRequestDispatcher("../board/KakaoMap.jsp").forward(req, resp);
	}

}
