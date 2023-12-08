package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/model/WriteController.do")
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.getWriter().append("Served at: ").append(req.getContextPath());
		System.out.println("글쓰기 컨트롤러 진입");
		req.getRequestDispatcher("../board/Write.jsp").forward(req, resp);
	}

}
