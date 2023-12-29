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
import utils.JSFunction;

@WebServlet("/auth/Mypage.do")
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 방법2. session에 담겨있는 loginUser(로그인한 회원정보) 객체에 있는 아이디 꺼내오기
		HttpSession session = request.getSession();
		
		// session에서 값을 꺼내오면 object 타입으로 가져와지므로 Member로 형변환해야한다.
		String sessionID = (String) session.getAttribute("user");
		
		if(sessionID == null) {
			JSFunction .alertBack(response, "로그인 후 이용할 수 있습니다.");
		} else {
		
		System.out.println(" sessionID : " +sessionID);
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.mypage(sessionID);
		
		request.setAttribute("id", dto.getId());
		request.setAttribute("name", dto.getName());
		request.setAttribute("birth", dto.getBirth());
		request.setAttribute("job", dto.getJob());
		request.setAttribute("nick", dto.getNick());
		request.setAttribute("phone", dto.getPhone());
		request.setAttribute("email", dto.getEmail());
		request.setAttribute("address", dto.getAddress());
		request.setAttribute("interest1", dto.getInterest1());
		request.setAttribute("interest2", dto.getInterest2());
		request.setAttribute("interest3", dto.getInterest3());
		request.setAttribute("image", dto.getImage());		
		 
		
		System.out.println(dto.getName() + " / " + dto.getJob());
		
		dao.close();
		request.getRequestDispatcher("/auth/MyPageView.jsp").forward(request, response);
		}
	}
}
