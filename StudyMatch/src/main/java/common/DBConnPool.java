package common;
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

public class DBConnPool {
	private DataSource dataSource;
	private Connection con;
	private Statement stmt;
	private PreparedStatement psmt;
	private ResultSet rs;

	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	public DBConnPool() {
		try {
			// 커넥션 풀(DateSource) 얻기
			Context iniCtx = new InitialContext();
			Context ctx = (Context) iniCtx.lookup("java:comp/env");
			dataSource = (DataSource) ctx.lookup("dbcp_myoracle");

			// 커넥션 풀을 통해 연결 얻기
			con = dataSource.getConnection();

			System.out.println(date.format(now) + " DB 커넥션 풀 연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** DB 커넥션 풀 연결 실패 ***");
		}
	}
	// 연결 해제(자원 반납)
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (psmt != null)
				psmt.close();
			if (con != null)
				con.close();
			System.out.println("*** " + date.format(now) + " DB 커넥션 풀 자원 반납 ***");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** DB 커넥션 풀 자원 반납 실패 ***");
		}
	}
}