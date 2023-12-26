package servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class KakaoServiceIml implements KakaoService {

	@Override
	public String getToken(String code) throws IOException {
		try {
			String access_Token = "";

			// API가 서버에서 접근할 수 있도록 하는 URL
			final String requestURL = "https://kauth.kakao.com/oauth/token";

			// 토큰 요청할 URL
			URL url = new URL(requestURL);

			// HTTP 연결
			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
			urlCon.setRequestMethod("POST");
			urlCon.setDoOutput(true);

			// 서버로 요청 보내기
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlCon.getOutputStream()))) {
				StringBuilder sb = new StringBuilder();
				sb.append("grant_type=authorization_code");
				sb.append("client_id=f2c074f5065d2de22abbf8a880f7790c");
				sb.append("&redirect_uri=http://localhost:8081/user/kakao/callback");
				sb.append("&code=" + code);
				bw.write(sb.toString());
			}

			// 서버의 응답 데이터 가져오기
			try (BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()))) {
				String line = "";
				StringBuilder result = new StringBuilder();

				// result에서 토큰 포함된 응답 데이터 한 줄씩 저장
				while ((line = br.readLine()) != null) {
					result.append(line);
				}

				// 카카오에서 가져온 JSON 데이터 파싱
				JSONParser parser = new JSONParser();
				try {
					JSONObject jsonObject = (JSONObject) parser.parse(result.toString());
					access_Token = (String) jsonObject.get("access_token");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			return access_Token;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("카카오 토큰 요청 중 오류가 발생했습니다.", e);
		}
	}
}