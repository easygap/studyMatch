package servlet;

import java.io.IOException;

public interface KakaoService {
    String getToken(String code) throws IOException;
}