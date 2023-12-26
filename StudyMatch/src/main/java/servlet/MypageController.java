package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/auth/Mypage.do")
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/auth/MyPageView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();

		// 방법2. session에 담겨있는 loginUser(로그인한 회원정보) 객체에 있는 아이디 꺼내오기
		HttpSession session = request.getSession();
		// session에서 값을 꺼내오면 object 타입으로 가져와지므로 Member로 형변환해야한다.
		String sessionID = (String) session.getAttribute("user");
		
		System.out.println(" sessionID : " +sessionID);
		
		dao.mypage(sessionID);
		
		dto.getId();
		dto.getName();
		dto.getPass();
		dto.getBirth();
		dto.getJob();
		dto.getNick();
		dto.getPhone();
		dto.getEmail();
		dto.getAddress();
		dto.getInterest1();
		dto.getInterest2();
		dto.getInterest3();
		dto.getImage();
		
		System.out.println(dto.getName() + " / " + dto.getJob());
		
		dao.close();
	}
}
