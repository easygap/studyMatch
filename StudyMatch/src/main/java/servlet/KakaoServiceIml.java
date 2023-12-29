package servlet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class KakaoServiceIml {
	
	public String getToken(String authorize_code) throws IOException {
		String access_token = null;

		try {
			// 카카오 API에 토큰 요청하기
            String tokenEndpoint = "https://kauth.kakao.com/oauth/token";
            String grantType = "authorization_code";
            String clientId = "f2c074f5065d2de22abbf8a880f7790c";
            String redirectUri = "http://localhost:8081/StudyMatch/board/Main.do";

            URL url = new URL(tokenEndpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // 설정
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            con.setDoOutput(true);
            
            // POST 데이터 설정
            String postData = "grant_type=" + URLEncoder.encode(grantType, "UTF-8") +
                    "&client_id=" + URLEncoder.encode(clientId, "UTF-8") +
                    "&redirect_uri=" + URLEncoder.encode(redirectUri, "UTF-8") +
                    "&code=" + URLEncoder.encode(authorize_code, "UTF-8");

            // 데이터 전송
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(postData);
                wr.flush();
            }

            // 응답 읽기
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                JSONParser parser = new JSONParser();
                JSONObject jsonParse = (JSONObject) parser.parse(response.toString());
                access_token = (String) jsonParse.get("access_token");
            }
            con.disconnect();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            System.out.println("*** 액세스 토큰 요청 중 예외 발생 ***");
        }
		System.out.println("액세스 토큰: " + access_token);
        return access_token;
    }

	public List<String> getUserInfo(String access_token) {
		List<String> userInfo = new ArrayList<>();
		
        try {
            // Kakao API에서 사용자 정보를 가져오기 위한 URL
            String userInfoEndpoint = "https://kapi.kakao.com/v2/user/me";

            URL url = new URL(userInfoEndpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // 설정
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + access_token);

            // 응답 읽기
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                
                // 사용자 정보 파싱
                JSONParser parser = new JSONParser();
                JSONObject jsonParse = (JSONObject) parser.parse(response.toString());
                
                // userInfo에 담기
                userInfo.add("name: " + jsonParse.get("kakao_account.name"));
                userInfo.add("birthyear: " + jsonParse.get("kakao_account.birthyear"));
                userInfo.add("birthday: " + jsonParse.get("kakao_account.birthday"));
                userInfo.add("phone: " + jsonParse.get("kakao_account.phone_number"));
                userInfo.add("address: " + jsonParse.get("kakao_account.shipping_address"));
                userInfo.add("nickname: " + jsonParse.get("properties.nickname"));
                userInfo.add("email: " + jsonParse.get("kakao_account.email"));
                
                System.out.println(response.toString());
            }
            con.disconnect();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            System.out.println("*** 사용자 정보 가져오기 중 예외 발생 ***");
        }
        
        return userInfo;
    }
}