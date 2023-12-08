package model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "/model/WriteController.do", urlPatterns="/uploads")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 최대 파일 크기 초과 시 임시저장
maxFileSize = 1024 * 1024 * 50, // 개별 최대 파일 크기
maxRequestSize = 1204 * 1204 * 50 * 5) // 전체 파일 크기
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.getWriter().append("Served at: ").append(req.getContextPath());
		String saveDirectory = req.getServletContext().getRealPath("/uploads");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		Part imgPart = req.getPart("img");
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.getWriter().append("Served at: ").append(req.getContextPath());
		req.getRequestDispatcher("../board/Write.jsp").forward(req, resp);
	}
}