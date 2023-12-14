package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import common.DBConnPool;

public class GroupDAO extends DBConnPool {

	DataSource dataSource;
	Connection con;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;
	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();

	// 생성자, DB 연결
	public GroupDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/dbcp_myoracle");
			con = dataSource.getConnection();
			System.out.println("BoardDAO DB 연동 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** BoardDAO DB 연동 중 예외 발생 ***");
		}
	}
	
	// 사용자 정보 가져오기
	public String getUserName(String id) {
		String NickName = null;
		String query = "SELECT nickname FROM member WHERE id=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if (rs.next())
				NickName = rs.getString("nickname");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 조회중 에러 발생 ***");
		}
		return NickName;
	}
	
	// DB 연결 해제
	public void close() {
		DBConnPool dbConnPool = new DBConnPool();
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (con != null)
				con.close();
			if (stmt != null)
				stmt.close();
			System.out.println("게시판 자원 해제 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시판 자원 해제 중 예외 발생 ***");
		}
		dbConnPool.close();
	}
}
