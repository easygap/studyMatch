package utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class JSFunction {
	
	// 예시(링크랑 같이 사용할 때)
	public static void alertLocation(HttpServletResponse resp, String msg, String url) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = ""
					+ "<script>"
					+ "		alert('" + msg + "');"
					+ "		location.href='" + url + "';"
					+ "</script>";
			writer.print(script);
		}catch(Exception e) {}
	}
	
	// 예시(알람창만 사용할 때)
	public static void alertBack(HttpServletResponse resp, String msg) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = ""
					+ "<script>"
					+ " 	alert('" + msg + "');"
					+ " 	history.back();"
					+ "</script>";
			writer.print(script);
		}catch(Exception e) {}
	}
	
	// 회원가입 성공 알람창
	public static void alertRegist(HttpServletResponse resp, String msg, String url) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = ""
					+ "<script>"
					+ " 	alert('" + msg + "');"
					+ "		location.href='" + url + "';"
					+ "</script>";
			writer.print(script);
		}catch(Exception e) {}
	}
	
	// 회원가입 실패 알람창
	public static void alertRegistFail(HttpServletResponse resp, String msg) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = ""
					+ "<script>"
					+ " 	alert('" + msg + "');"
					+ " 	history.back();"
					+ "</script>";
			writer.print(script);
		}catch(Exception e) {}
	}
	
	// 회원가입 빈곳일 때 가입신청하면 다시 백
		public static void alertRegistEmpty(HttpServletResponse resp) {
			try {
				resp.setContentType("text/html;charset=UTF-8");
				PrintWriter writer = resp.getWriter();
				String script = ""
						+ "<script>"
						+ " 	history.back();"
						+ "</script>";
				writer.print(script);
			}catch(Exception e) {}
		}

	// 로그인 알람창
	public static void alertLogin(HttpServletResponse resp, String msg, String url) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = ""
					+ "<script>"
					+ " 	alert('" + msg + "');"
					+ "		location.href='" + url + "';"
					+ "</script>";
			writer.print(script);
		}catch(Exception e) {}
	}
}
