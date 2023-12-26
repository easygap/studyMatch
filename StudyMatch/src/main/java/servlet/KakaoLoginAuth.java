package servlet;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/auth/KakaoLogin.do")
public class KakaoLoginAuth extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 코드 추출
			String code = req.getParameter("code");
			System.out.println("code: " + code);

//			KakaoService kakaoService = new KakaoServiceIml();
//			String accessToken = kakaoService.getToken(code);
			
			String accessToken = req.getParameter("access_token");
			System.out.println("token : " + accessToken);
			
			KakaoService kakaoService = new KakaoServiceIml();

//			HttpSession session = req.getSession();
//			session.setAttribute("accessToken", accessToken);

			// 이미 발급한 토큰을 사용하여 사용자 정보 가져오기
			ArrayList<Object> userInfo = kakaoService.getUserInfo(accessToken);
			String id = (String) userInfo.get(1);
			String email = (String) userInfo.get(1);
			String pass = (String) userInfo.get(2);
			String name = (String) userInfo.get(3);
			String nickname = (String) userInfo.get(4);
			String birth = (String) userInfo.get(5);
			String phone = (String) userInfo.get(6);
			String address = (String) userInfo.get(7);

			System.out.println("--------카카오 로그인 정보----------");
			System.out.println("id: " + id);
			System.out.println("name: " + name);
			System.out.println("nickname: " + nickname);
			System.out.println("email: " + email);
			System.out.println("birth: " + birth);
			System.out.println("phone: " + phone);
			System.out.println("address: " + address);
			System.out.println("--------------------------------");

			MemberDAO dao = new MemberDAO();
			MemberDTO dto = new MemberDTO();

			dto.setId(id);
			dto.setPass(pass);
			dto.setName(name);
			dto.setNick(nickname);
			dto.setBirth(birth);
			dto.setEmail(email);
			dto.setPhone(phone);
			dto.setAddress(address);
			dao.kakaoSign(dto);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 카카오 로그인 서블릿 예외 발생 ***");
		}
	}
}