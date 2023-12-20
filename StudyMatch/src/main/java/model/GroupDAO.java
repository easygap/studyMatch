package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import common.DBConnPool;

public class GroupDAO extends DBConnPool {

	private DataSource dataSource;
	private Connection con;
	private Statement stmt;
	private PreparedStatement psmt;
	private ResultSet rs;
	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();

	// 생성자, DB 연결
	public GroupDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/dbcp_myoracle");
			con = dataSource.getConnection();
			System.out.println("GroupDAO DB 연동 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** GroupDAO DB 연동 중 예외 발생 ***");
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
	public ArrayList<String> getMemberInterest(String id) {		
		int count = 0;
		String query1 = "SELECT COUNT(INTEREST1) + COUNT(INTEREST2) + COUNT(INTEREST3) AS interest FROM MEMBER WHERE id=?";

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
				if (count == 1)
					interest.add(rs.getString("interest1"));
				else if (count == 2) {
					interest.add(rs.getString("interest1"));
					interest.add(rs.getString("interest2"));
				} else if (count == 3) {
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

		String query = "SELECT address FROM member WHERE id=?";

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

	//그룹 id 세션
	public String GroupSession(String num) {
		String checkID = null;

		String query = "SELECT id1, id2, id3, id4, id5 FROM MATCHGROUP WHERE group_num";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();

			if (rs.next()) {
				checkID = rs.getString("id");
				System.out.println("Group DB : " + checkID);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 조회중 에러 발생 ***");
		}
		return checkID;
	}

	// 프로필 사진 가져오기
	public List<String> getProfile(String id) {
	    List<String> profiles = new ArrayList<>();
	    String query = "SELECT m.img " +
	            "FROM member m " +
	            "JOIN matchgroup mg ON m.id = mg.id1 OR m.id = mg.id2 OR m.id = mg.id3 OR m.id = mg.id4 OR m.id = mg.id5 " +
	            "WHERE mg.group_num IN " +
	            "    (SELECT group_num FROM matchgroup WHERE id1 = ? OR id2 = ? OR id3 = ? OR id4 = ? OR id5 = ?)";

	    try {
	        psmt = con.prepareStatement(query);
	        psmt.setString(1, id);
	        psmt.setString(2, id);
	        psmt.setString(3, id);
	        psmt.setString(4, id);
	        psmt.setString(5, id);
	        rs = psmt.executeQuery();
	        while (rs.next()) {
	            String profile = rs.getString("img");
	            profiles.add(profile);
	        }
            System.out.println("쿼리문에서 : " + profiles);
	    } catch (Exception e) {
	        System.out.println("DB 이미지 불러오기 실패");
	        e.printStackTrace();
	    }
	    return profiles;
	}
	
	// 그룹 멤버 이름 가져오기
	public List<String> getGroupName(String id){
		List<String> Names = new ArrayList<>();
		String query = " SELECT m.name "
				+ " FROM matchgroup mg "
				+ " JOIN member m ON "
				+ "    (mg.id1 = m.id OR mg.id2 = m.id OR mg.id3 = m.id OR mg.id4 = m.id OR mg.id5 = m.id) "
				+ " WHERE mg.id1 = ? OR mg.id2 = ? OR mg.id3 = ? OR mg.id4 = ? OR mg.id5 = ? ";
		try {
	        psmt = con.prepareStatement(query);
	        psmt.setString(1, id);
	        psmt.setString(2, id);
	        psmt.setString(3, id);
	        psmt.setString(4, id);
	        psmt.setString(5, id);
	        rs = psmt.executeQuery();
	        while (rs.next()) {
	            String name = rs.getString("name");
	            Names.add(name);
	        }
	        System.out.println("쿼리문에서 : " + Names + "\n");
	    } catch (Exception e) {
	        System.out.println("DB 이름 불러오기 실패");
	        e.printStackTrace();
	    }
	    return Names;
	}

	// 본인 관심사, 주소와 맞는 그룹의 Group_num 조회
	public Map<String, List<String>> getGroupData(String interest, String address, String id) {
		Map<String, List<String>> firstGroup = new HashMap<>();
		List<String> groupName = new ArrayList<>();
		List<String> groupImg = new ArrayList<>();
		List<String> groupNum = new ArrayList<>();
		String query = "SELECT m.NAME, m.IMG, Group_num "
				+ "FROM member m "
				+ "JOIN matchgroup mg ON m.id = mg.id1 OR m.id = mg.id2 OR m.id = mg.id3 OR m.id = mg.id4 OR m.id = mg.id5 "
				+ "WHERE mg.group_num IN ( "
				+ "    SELECT group_num "
				+ "    FROM matchgroup "
				+ "    WHERE import = ? AND ADDRESS LIKE ? "
				+ ") "
				+ "AND m.id != ? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, interest);
			psmt.setString(2, "%" + address + "%");
			/** 현재 사용자가 가입한 그룹은 보여주지 않기 */
			psmt.setString(3, id);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
	            groupName.add(rs.getString("name"));
	            groupImg.add(rs.getString("IMG"));
	            groupNum.add(rs.getString("group_num"));
	        }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		firstGroup.put("groupName", groupName);
		firstGroup.put("groupImg", groupImg);
		firstGroup.put("groupNum", groupNum);
		
		System.out.println("DAO에서 매칭 group의 이름 : " + groupName + ", 이미지 : " +groupImg);
		return firstGroup;
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
			System.out.println("매칭조회 자원 해제 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 매칭조회 자원 해제 중 예외 발생 ***");
		}
		dbConnPool.close();
	}
}
