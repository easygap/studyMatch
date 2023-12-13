package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import common.DBConnPool;

public class MemberDAO extends DBConnPool {
//	public MemberDAO(ServletContext application) {
//		super(application);
//	}

	private Connection con;
	private DataSource dataSource;
	private PreparedStatement psmt;
	private ResultSet rs;

	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();

	public MemberDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/dbcp_myoracle");
			System.out.println("DB 연동 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** DB 연동 중 예외 발생 ***");
		}
	}

	public MemberDTO getMemberDTO(String id, String pass) {
		MemberDTO dto = null;
		String query = "SELECT * FROM member WHERE id=? AND pwd=?";
		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();

			while (rs.next()) {
				dto = new MemberDTO();
				// 회원 정보를 DTO에 설정
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pwd"));
				dto.setBirth(rs.getString("birth"));
				dto.setJob(rs.getString("job"));
				dto.setNick(rs.getString("nickname"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setAddress(rs.getString("address"));
				dto.setInterest1(rs.getString("interest1"));
				dto.setInterest2(rs.getString("interest2"));
				dto.setInterest3(rs.getString("interest3"));
				dto.setImage(rs.getString("img"));

				System.out.println(date.format(now) + " [ " + id + " ] 정보 조회 성공!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("*** MemberDAO.getMemberDTO 회원 정보 조회 중 예외 발생 ***");
		}
		return dto;
	}

	// 로그인
	public boolean login(String id, String pass) {
		boolean result = false;
		String query = "SELECT nickname FROM member WHERE id=? AND pwd=?";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();

			if (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNick(rs.getString("nickname"));
				result = true;
				System.out.println(date.format(now) + " [ " + dto.getNick() + " ] 로그인 성공!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("*** 로그인 쿼리 실행 중 예외 발생 ***");
		}
		return result;
	}

	// 회원정보 조회
//	public ArrayList<MemberDTO> Inquiry(String uid, String upass) {
//		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
//		String query = "SELECT * FROM member WHERE id=? AND pwd=?";
//		try {
//			con = dataSource.getConnection();
//			psmt = con.prepareStatement(query);
//			rs = psmt.executeQuery();
//
//			psmt.setString(1, uid);
//			psmt.setString(6, upass);
//			while (rs.next()) { // DTO 객체에 회원 정보 저장
//				String id = rs.getString("id");
//				String pass = rs.getString("pwd");
//				String name = rs.getString(2);
//				String birth = rs.getString(3);
//				String job = rs.getString(4);
//				String nick = rs.getString(5);
//				String phone = rs.getString(7);
//				String email = rs.getString(8);
//				String address = rs.getString(9);
//				String interest1 = rs.getString(10);
//				String interest2 = rs.getString(11);
//				String interest3 = rs.getString(12);
//				String image = rs.getString(14);
//
//				MemberDTO dto = new MemberDTO();
//				list.add(dto);
//				System.out.println(date.format(now) + " [ " + uid + " ] 정보 조회 성공!");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("*** [ " + uid + " ] 회원정보 조회 쿼리문 실행 중 예외 발생! ***");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("*** [ " + uid + " ] 회원정보 조회 중 예외 발생! ***");
//		}
//		return list;
//	}

	// 회원가입
	public boolean signUp(MemberDTO dto) throws SQLException {
		con = dataSource.getConnection();
		boolean result = false;
		String query = "INSERT INTO member (id, name, birth, job, nickname, pwd, phone, email, address, interest1, interest2, interest3, img)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int signUpCount = 0;

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getBirth());
			psmt.setString(4, dto.getJob());
			psmt.setString(5, dto.getNick());
			psmt.setString(6, dto.getPass());
			psmt.setString(7, dto.getPhone());
			psmt.setString(8, dto.getEmail());
			psmt.setString(9, dto.getAddress());
			psmt.setString(10, dto.getInterest1());
			psmt.setString(11, dto.getInterest2());
			psmt.setString(12, dto.getInterest3());
			psmt.setString(13, dto.getImage());
			signUpCount = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 회원가입 중 예외 발생! ***");
		}

		if (signUpCount > 0) {
			System.out.println(date.format(now) + " [ " + dto.getId() + " ] 회원가입 성공!");
			result = true;
		} else {
			System.out.println("*** " + date.format(now) + " 회원가입 실패 ***");
			result = false;
		}
		return result;
	}
	
	// ID 중복체크
	public int idCheck(String id) {
		String query = "SELECT id FROM member WHERE id = ?";
		int result = 0;
		
		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = 0;
			}else {
				result = 1;
			}
			System.out.println("쿼리문 아이디 중복체크 결과 : " + result);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("*** 아이디 중복 검사 쿼리 실행 중 예외 발생 ***");
		}
		return result;
	}
	
	// 아이디 찾기
	public String idSearch(String name, String phone) {
		String query = "SELECT id FROM member WHERE name = ? AND phone = ?";
		String idSearch = null;
		
		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query);
			psmt.setString(1, name);
			psmt.setString(2, phone);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				idSearch = rs.getString("id");
				System.out.println(date.format(now) + " 쿼리문 : [ " + idSearch + " ] 아이디 찾기 성공!");
			}else {
				System.out.println(" 쿼리문 : 아이디 정보 없음");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("*** 아이디 찾기 쿼리문 실행 중 예외 발생 ***");
		}
		return idSearch;
	}
	
	// 비밀번호 찾기
	public String pwSearch(String id, String phone, String birth) {
		String query = "SELECT pwd FROM member WHERE id = ? AND phone = ? AND birth = ?";
		String pwSearch = null;
		
		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, phone);
			psmt.setString(3, birth);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				pwSearch = rs.getString("pwd");
				System.out.println(date.format(now) + " 쿼리문 : [ " + pwSearch + " ] 비밀번호 찾기 성공!");
			}else {
				System.out.println("쿼리문 : 회원 정보 없음");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("*** 비밀번호 찾기 쿼리문 실행 중 예외 발생 ***");
		}
		return pwSearch;
	}
	
	// 비밀번호 변경하기
	public void pwChange(String pw, String id) {
		String query = "UPDATE member SET pwd = ? WHERE id = ?";
		
		
		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query);
			psmt.setString(1, pw);
			psmt.setString(2, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("쿼리문 : 비밀번호 변경 성공!");
			}else {
				System.out.println("쿼리문 : 비밀번호 변경 실패...");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	// 자원 반납
	public void DBclose() {
		DBConnPool dbConnPool = new DBConnPool();
		try {
			if (con != null)
				con.close();
			if (psmt != null)
				psmt.close();
			if (rs != null)
				rs.close();
			System.out.println("DB 커넥션 풀 자원 반납");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** DB 커넥션 풀 자원 반납 중 예외 발생 ***");
		}	
		dbConnPool.close();
	}
}