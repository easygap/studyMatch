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
		req.getRequestDispatcher("../board/Write.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.getWriter().append("Served at: ").append(req.getContextPath());
		String saveDirectory = req.getServletContext().getRealPath("/업로드");
	}

}
