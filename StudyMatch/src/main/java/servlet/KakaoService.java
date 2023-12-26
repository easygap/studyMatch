package servlet;

import java.io.IOException;
import java.util.ArrayList;

public interface KakaoService {
//    String getToken(String code) throws IOException;
    ArrayList<Object> getUserInfo(String access_token) throws Exception;
}