package servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class KakaoServiceIml implements KakaoService {
	private String accessToken;

//	@Override
//	public String getToken(String authorize_code) throws IOException {
//
//			String access_Token = "";
//			String refresh_Token = "";
//			String id_Token = "";
//			
//			// API가 서버에서 접근할 수 있도록 하는 URL
//			final String reqURL = "https://kauth.kakao.com/oauth/token";
//			
//			try {
//			// 토큰 요청할 URL
//			URL url = new URL(reqURL);
//
//			// HTTP 연결
//			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
//			urlCon.setRequestMethod("POST");
//			urlCon.setDoOutput(true);
//
//			// 서버로 요청 보내기
//			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlCon.getOutputStream()));
//				StringBuilder sb = new StringBuilder();
//				sb.append("grant_type=authorization_code");
//				sb.append("&client_id=f2c074f5065d2de22abbf8a880f7790c");
//				sb.append("&redirect_uri=http://localhost:8081/StudyMatch/board/MainPage.jsp");
//				sb.append("&code=" + authorize_code);
//				bw.write(sb.toString());
//				bw.flush();
//
//				int responseCode = urlCon.getResponseCode();
//				System.out.println("responseCode: " + responseCode);
//
//			// 서버의 응답 데이터 가져오기
//			BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
//			String line = "";
//			String result = "";
//
//				// result에서 토큰 포함된 응답 데이터 한 줄씩 저장
//				while ((line = br.readLine()) != null) {
//					result += line;
//				}
//				System.out.println("Kakao API 응답: " + result);
//
//				// 카카오에서 가져온 JSON 데이터 파싱
//				JSONParser parser = new JSONParser();
//				JSONObject jsonObject = (JSONObject) parser.parse(result.toString());
//				access_Token = (String) jsonObject.get("access_token");
//				refresh_Token = (String) jsonObject.get("refresh_token");
//				id_Token = (String) jsonObject.get("id_token");
//				System.out.println("access_Token: " + access_Token);
//				System.out.println("refresh_Token: " + refresh_Token);
//				System.out.println("id_Token: " + id_Token);
//				
//				br.close();
//				bw.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("카카오 API 토큰 발급 중 예외 발생");
//			}
//			
//			this.accessToken = access_Token;
//			return access_Token;
//	}

	public ArrayList<Object> getUserInfo(String access_Token) throws Exception {
		ArrayList<Object> list = new ArrayList<Object>();

        final String reqURL = "https://kapi.kakao.com/v2/user/me";

        URL url = new URL(reqURL);
        HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
        urlCon.setRequestMethod("GET");
        urlCon.setRequestProperty("Authorization", "Bearer " + access_Token);

		try (BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()))) {
			String line = "";
			StringBuilder result = new StringBuilder();

			while ((line = br.readLine()) != null) {
				result.append(line);
			}

			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(result.toString());

			JSONObject properties = (JSONObject) jsonObject.get("properties");
			JSONObject kakao_account = (JSONObject) jsonObject.get("kakao_account");
			System.out.println("-------- properties : " + properties);
			System.out.println("-------- kakao_account : " + kakao_account);
			System.out.println("---------------------------------");

			String id = (String) properties.get("email");
			String email = (String) properties.get("email");
			String pass = "1234";
			String name = (String) properties.get("name");
			String nickname = (String) properties.get("nickname");
			String birth = (String) properties.get("birth");
			String phone = (String) properties.get("phone");
			String address = (String) properties.get("address");

			list.add(id);
			list.add(email);
			list.add(pass);
			list.add(name);
			list.add(nickname);
			list.add(birth);
			list.add(phone);
			list.add(address);

			return list;

		}
	}
}