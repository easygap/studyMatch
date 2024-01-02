package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

@WebServlet("/auth/KakaoLogin.do")
public class KakaoLoginAuth extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
//	private static KakaoServiceIml kakaoService = new KakaoServiceIml();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

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

		JSONParser parser = new JSONParser();
		HttpSession session = req.getSession();

		try {
			JSONObject userJson = (JSONObject) parser.parse(userData);

			// JSON 객체에서 필드 값을 추출
			String kakaoId = getStringOrDefault(userJson, "id", "");
			String kakaoNick = getStringOrDefault(userJson, "profile_nickname", "");
			String kakaoName = getStringOrDefault(userJson, "name", "");
			String kakaoEmail = getStringOrDefault(userJson, "email", "");
			String kakaoBirthyear = getStringOrDefault(userJson, "birthyear", "");
			String kakaoBirthday = getStringOrDefault(userJson, "birthday", "");
			String kakaoBirth = kakaoBirthyear + kakaoBirthday;
			String kakaoAddress = getStringOrDefault(userJson, "address", "");
			String kakaoPhoneNumber = getStringOrDefault(userJson, "phoneNumber", "");
			String kakaoPhone = kakaoPhoneNumber.replace("+82 ", "0");

			session.setAttribute("kakaoId", kakaoId);
			session.setAttribute("kakaoName", kakaoName);
			session.setAttribute("kakaoNick", kakaoNick);
			session.setAttribute("kakaoBirth", kakaoBirth);
			session.setAttribute("kakaoPhone", kakaoPhone);
			session.setAttribute("kakaoEmail", kakaoEmail);

			dto = dao.kakaoCheck(kakaoId);
			if (dto != null) {
//				RequestDispatcher loginAuth = req.getRequestDispatcher("../auth/LoginAuth.do");
//				loginAuth.forward(req, resp);
				resp.setContentType("application/json; charset=UTF-8");
				resp.setCharacterEncoding("UTF-8");

				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("status", "success");
				resp.getWriter().write(jsonResponse.toJSONString());
				
				session.setAttribute("user", dto.getId());
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				long creationTime = session.getCreationTime(); // 최초 요청 시간
				String creationTimeStr = dateFormat.format(new Date(creationTime));
				long lastTime = session.getLastAccessedTime(); // 마지막 요청 시간

				String lastTimeStr = dateFormat.format(new Date(lastTime));

				// 로그인 성공 알람창
				System.out.println("------------------------------");
				System.out.println(date.format(now) + " [ " + dto.getId() + " ] 로그인 성공 - session 저장 완료");
				System.out.println("세션 아이디: " + session.getId());
				System.out.println("세션 유지 시간: " + session.getMaxInactiveInterval());
				System.out.println("최초 요청 시간: " + creationTimeStr);
				System.out.println("마지막 요청 시간: " + lastTimeStr);
			} else {
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				PrintWriter out = resp.getWriter();
				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("status", "popup");
				out.print(jsonResponse.toString());
				out.flush();
			}
		} catch (ParseException e) {
			e.printStackTrace();
			resp.getWriter().write("*** 전송받은 데이터 파싱 실패 ***");
		}
	}

	private static String getStringOrDefault(JSONObject jsonObject, String key, String defaultValue) {
		Object value = jsonObject.get(key);
		return value != null ? value.toString() : defaultValue;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("kakao doGet");
		String id = req.getParameter("id");
		String nick = req.getParameter("nick");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String birth = req.getParameter("birth");
		String address = req.getParameter("address");
		String phone = req.getParameter("phone");
		String job = req.getParameter("job");
		String[] interests = req.getParameterValues("interests");
		String interest1 = interests[0];
		String interest2 = interests[1];
		String interest3 = interests[2];

		if (!(interest1.isEmpty()) || !(interest1.equals("null"))) {
			resp.setContentType("text/plain");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write("success");
			System.out.println("카카오 팝업에서 값 받아옴");
		}

		System.out.println("-----------카카오 로그인 사용자 정보------------");
		System.out.println("id: " + id);
		System.out.println("nick: " + nick);
		System.out.println("name: " + name);
		System.out.println("email: " + email);
		System.out.println("birth: " + birth);
		System.out.println("address: " + address);
		System.out.println("phone: " + phone);
		System.out.println("job: " + job);
		System.out.println("interest1: " + interest1);
		System.out.println("interest2: " + interest2);
		System.out.println("interest3: " + interest3);
		System.out.println("-------------------------------------------");

		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();

		dto.setId(id);
		dto.setName(name);
		dto.setNick(nick);
		dto.setEmail(email);
		dto.setBirth(birth);
		dto.setAddress(address);
		dto.setPhone(phone);
		dto.setJob(job);
		dto.setInterest1(interest1);
		dto.setInterest2(interest2);
		dto.setInterest3(interest3);

		dao.kakaoSign(dto);
		dao.close();
	}
}