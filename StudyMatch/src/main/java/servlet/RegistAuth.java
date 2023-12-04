package servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;

public class RegistAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet()");
		req.getRequestDispatcher("/auth/Regist.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println("doPost()");
		MemberDAO dao = new MemberDAO();

		String cp = req.getContextPath();
		String uri = req.getRequestURI();
		String url;

		if (uri.indexOf("Regist.do") != -1) {
			MemberDTO dto = new MemberDTO();
			dto.setId(req.getParameter("id"));
			dto.setPass(req.getParameter("pw"));
			dto.setName(req.getParameter("name"));
			dto.setNick(req.getParameter("nickname"));
			dto.setBirth(req.getParameter("birth"));
			dto.setPhone(req.getParameter("phone"));
			dto.setAddress(req.getParameter("address"));
			dto.setEmail(req.getParameter("Email"));
			dto.setJob(req.getParameter("job"));
			dto.setInterest1(req.getParameter("interest"));
			dto.setInterest2(req.getParameter("interest"));
			dto.setInterest3(req.getParameter("interest"));
			
			url = cp+"/Login.jsp";
			resp.sendRedirect(url);
		}
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init()");
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
		System.out.println("service()");
	}

}
