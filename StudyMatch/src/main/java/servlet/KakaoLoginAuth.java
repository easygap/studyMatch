package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/auth/KakaoLoginAuth.do")
public class KakaoLoginAuth extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
//	private static KakaoServiceIml kakaoService = new KakaoServiceIml();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		 MemberDAO dao = new MemberDAO();
		 MemberDTO dto = new MemberDTO();
		
		resp.setHeader("Access-Control-Allow-Origin", "*"); // 모든 도메인에서 접근 허용
		resp.setHeader("Access-Control-Allow-Methods", "POST"); // 허용할 HTTP 메서드 설정
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 허용할 헤더 설정

		BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
		StringBuilder jsonBuffer = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			jsonBuffer.append(line);
		}

		// JSON 데이터를 문자열로 변환
		String userData = jsonBuffer.toString();

		// 서버에서 전달된 JSON 데이터 출력
		System.out.println("로그인한 사용자: " + userData);

		if (!(userData.equals("null")) && !(userData.isEmpty())) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			PrintWriter out = resp.getWriter();
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("status", "success");
			out.print(jsonResponse.toString());
			out.flush();
		}

	    JSONParser parser = new JSONParser();
        HttpSession session = req.getSession();
        
		 try {
		        JSONObject userJson = (JSONObject) parser.parse(userData);

		        // JSON 객체에서 필드 값을 추출
		        String id = getStringOrDefault(userJson, "id", "");
		        String nick = getStringOrDefault(userJson, "profile_nickname", "");
		        String name = getStringOrDefault(userJson, "name", "");
		        String email = getStringOrDefault(userJson, "email", "");
		        String birthyear = getStringOrDefault(userJson, "birthyear", "");
		        String birthday = getStringOrDefault(userJson, "birthday", "");
		        String birth = birthyear + birthday;
		        String address = getStringOrDefault(userJson, "address", "");
		        String phoneNumber = getStringOrDefault(userJson, "phoneNumber", "");
		        
		        String phone = phoneNumber.replace("+82 ", "0");
		        
		        session.setAttribute("kakaoName", name);
		        session.setAttribute("kakaoNick", nick);
		        session.setAttribute("kakaoBirth", birth);
		        session.setAttribute("kakaoPhone", phone);
		        session.setAttribute("kakaoEmail", email);
		        
		        System.out.println("-----------카카오 로그인 사용자 정보------------");
		        System.out.println("id: " + id);
		        System.out.println("nick: " + nick);
		        System.out.println("name: " + name);
		        System.out.println("email: " + email);
		        System.out.println("birth: " + birth);
		        System.out.println("address: " + address);
		        System.out.println("phone: " + phone);
		        System.out.println("-------------------------------------------");
				 
				 dto.setId(id);
				 dto.setName(name);
				 dto.setNick(nick);
				 dto.setEmail(email);
				 dto.setBirth(birth);
				 dto.setAddress(address);
				 dto.setPhone(phone);
				 
				 dao.kakaoSign(dto);
				 
		    } catch (ParseException e) {
		        e.printStackTrace();
		        resp.getWriter().write("*** 전송받은 데이터 파싱 실패 ***");
		    }
	}
	
	private static String getStringOrDefault(JSONObject jsonObject, String key, String defaultValue) {
	    Object value = jsonObject.get(key);
	    return value != null ? value.toString() : defaultValue;
	}
}