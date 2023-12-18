package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

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
		
	// 현재 회원 ID의 interest 정보 가져오기
	public ArrayList<String> getMemberInterest(String id) {		
		int count = 0;
		String query1 ="SELECT COUNT(INTEREST1) + COUNT(INTEREST2) + COUNT(INTEREST3) AS interest FROM MEMBER WHERE id=?";
		
		try {
			psmt = con.prepareStatement(query1);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("interest");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<String> interest = new ArrayList<String>(count);

		String query2 = "SELECT INTEREST1, INTEREST2, INTEREST3 FROM member WHERE id=?";
	
		try {
			psmt = con.prepareStatement(query2);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				if(count == 1) 
					interest.add(rs.getString("interest1"));
				else if(count == 2) {
					interest.add(rs.getString("interest1"));
					interest.add(rs.getString("interest2"));
				} else if(count == 3) {
					interest.add(rs.getString("interest1"));
					interest.add(rs.getString("interest2"));
					interest.add(rs.getString("interest3"));
				} else {
					interest.add("error");
				}
			}
			Collections.shuffle(interest);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return interest;
		
	}
	
	// 현재 회원 ID의 주소 정보 가져오기
	public String getaddress(String id) {
		String address = null;
		
		String query="SELECT address FROM member WHERE id=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				address = rs.getString("address");
			}
			
			StringTokenizer st = new StringTokenizer(address);
			
			st.nextToken();
			
			address = st.nextToken();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}
	
	// 본인 관심사, 주소와 맞는 그룹의 Group_num 조회
	public String getGroupData1(String interest, String address) {
		String groupNum = null;
		String query = "SELECT group_num FROM MATCHGROUP WHERE IMPORT= ? AND ADDRESS LIKE ? order by DBMS_RANDOM.RANDOM";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, interest);
			psmt.setString(2, "%" + address + "%");
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				groupNum = rs.getString("group_num");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return groupNum;
	}
	
	// 본인 관심사, 주소와 맞는 그룹 중 매칭되지 않은 Group_num 조회
	public String getGroupData2(String interest, String address, String groupNum1) {
		String groupNum = null;
		String query = "SELECT group_num FROM MATCHGROUP WHERE group_num != ? AND IMPORT= ? AND ADDRESS LIKE ? order by DBMS_RANDOM.RANDOM";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, groupNum1);
			psmt.setString(2, interest);
			psmt.setString(3, "%" + address + "%");
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				groupNum = rs.getString("group_num");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return groupNum;
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
