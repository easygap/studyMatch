package servlet;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/user/kakao/callback")
public class KakaoLoginAuth extends HttpServlet implements Serializable {
    private static final long serialVersionUID = 1L;
    private KakaoService kakaoService;
    
    public KakaoLoginAuth(KakaoService kakaoService) {
    	this.kakaoService = kakaoService;
    }
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		// 코드 추출
		String code = req.getParameter("code");

		// KakaoService 인터페이스에서 토큰 요청
		String acceceToken = kakaoService.getToken(code);
		System.out.println("token : " + acceceToken);

		resp.sendRedirect(req.getContextPath() + "/");
		} catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("카카오 로그인 실패");
	    }
	}
}