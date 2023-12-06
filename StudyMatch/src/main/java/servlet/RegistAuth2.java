package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.DBConnPool;
import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/auth/Regist.do")
public class RegistAuth2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
		System.out.println("doPost()");
		String[] interest = req.getParameterValues("interest");
		
		for(String s : interest) {
			System.out.println("[ " + s + " ]");
		}
		
		MemberDAO dao = new MemberDAO();

		String uri = req.getRequestURI();
		
		String saveDirectory;
			
			try {
				MemberDTO dto = new MemberDTO();
				
				if (uri.indexOf("Regist.do") != -1) {
					dto.setId(req.getParameter("id"));
					dto.setPass(req.getParameter("pw"));
					dto.setName(req.getParameter("name"));
					dto.setNick(req.getParameter("nickname"));
					dto.setBirth(req.getParameter("birth"));
					dto.setPhone(req.getParameter("phone"));
					dto.setAddress(req.getParameter("address"));
					dto.setEmail(req.getParameter("Email"));
					dto.setJob(req.getParameter("job"));
					dto.setInterest1(interest[0]);
					dto.setInterest2(interest[1]);
					dto.setInterest3(interest[2]);
					dto.setImage(req.getParameter("img"));
					
					dao.signUp(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doGet()");

		req.getRequestDispatcher("/auth/Regist.jsp").forward(req, resp);
	}



}