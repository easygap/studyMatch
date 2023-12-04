package servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/auth/Regist.do")
public class RegistAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	protected void forword(HttpServletRequest req, HttpServletResponse resp, String url) throws ServletException, IOException {
//		RequestDispatcher rd = ;
//		rd.forward(req, resp);
//	}

//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("doGet()");
//		
//		req.getRequestDispatcher("/auth/Regist.jsp").forward(req, resp);
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("doPost()");
////		doGet(req, resp);
////		req.setCharacterEncoding("UTF-8");
////		resp.setContentType("text/html; charset=UTF-8");
//		
//		System.out.println("연결 모시기");
//		System.out.println("[" + req.getParameter("id") + "]");
//		
//		MemberDAO dao = new MemberDAO();
//
//		String cp = req.getContextPath();
//		String uri = req.getRequestURI();
//		String url;
//
//		if (uri.indexOf("Regist.do") != -1) {
//			MemberDTO dto = new MemberDTO();
//			dto.setId(req.getParameter("id"));
//			dto.setPass(req.getParameter("pw"));
//			dto.setName(req.getParameter("name"));
//			dto.setNick(req.getParameter("nickname"));
//			dto.setBirth(req.getParameter("birth"));
//			dto.setPhone(req.getParameter("phone"));
//			dto.setAddress(req.getParameter("address"));
//			dto.setEmail(req.getParameter("Email"));
//			dto.setJob(req.getParameter("job"));
//			dto.setInterest1(req.getParameter("interest"));
//			dto.setInterest2(req.getParameter("interest"));
//			dto.setInterest3(req.getParameter("interest"));
//			
//			dao.signUp(dto);
//			
//			url = cp+"/Login.jsp";
//			resp.sendRedirect(url);
//		}
//	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		
		System.out.println("doGet()");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
		
		System.out.println("doPost()");
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		System.out.println("회원가입 성공");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}