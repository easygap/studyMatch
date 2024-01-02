package auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDTO;
import model.BoardDAO;
import model.BoardDTO;

@WebServlet("/auth/MypageView.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		// 방법2. session에 담겨있는 loginUser(로그인한 회원정보) 객체에 있는 아이디 꺼내오기
		HttpSession session = req.getSession();
		// session에서 값을 꺼내오면 object 타입으로 가져와지므로 Member로 형변환해야한다.
		String sessionID = (String) session.getAttribute("user");
		
		MemberDAO dao = new MemberDAO();
		System.out.println("DAO 객체 생성 확인: " + (dao != null));
		MemberDTO dto = new MemberDTO();
		System.out.println("DTO 객체 생성 확인: " + (dto != null));
		
		String pw = req.getParameter("pw");
		String pwcheck = req.getParameter("pwcheck");
		
		String job = req.getParameter("job");
		String nick = req.getParameter("nickName");
		String phone = req.getParameter("phone");
		String email = req.getParameter("Email");
		String addres = req.getParameter("address");
		String[] interest = req.getParameterValues("interest");
		String[] interestInfo = req.getParameterValues("interestInfor");
		String image = req.getParameter("img");
		String result = "N";
		
		System.out.println(job + " / " + nick + " / " + sessionID);

		if (pw.equals(pwcheck)) {
			dto.setPass(pw);
			System.out.println("비밀번호 동일");
		} else {
			System.out.println("비밀번호 다름");
		}
		
		dto.setJob(job);
		dto.setNick(nick);
		dto.setEmail(email);
		dto.setAddress(addres);
		dto.setPhone(phone);
		if(req.getParameterValues("interest") != null) {
			if(interest.length == 1) {
				dto.setInterest1(interest[0]);
			}else if(interest.length == 2) {
				dto.setInterest1(interest[0]);
				dto.setInterest2(interest[1]);
			}else if(interest.length == 3) {
				dto.setInterest1(interest[0]);
				dto.setInterest2(interest[1]);
				dto.setInterest3(interest[2]);
			}
		}
		dto.setImage(image);
		
		dao.updateMypage(dto, sessionID);
		
		dao.close();
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/auth/MyPageView.jsp").forward(req, resp);
	}
}
