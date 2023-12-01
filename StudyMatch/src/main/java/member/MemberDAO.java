package member;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class MemberDAO extends JDBConnect {
	public MemberDAO(ServletContext application) {
		super(application);
	}

	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();

	public MemberDTO Inquiry (String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member WHERE id=? AND pwd=?";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(6, upass);
			rs = psmt.executeQuery();

			if (rs.next()) { // DTO 객체에 회원 정보 저장
				dto.setId(rs.getString("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(2));
				dto.setBirth(rs.getString(3));
				dto.setJob(rs.getString(4));
				dto.setNick(rs.getString(5));
				dto.setPhone(rs.getString(7));
				dto.setEmail(rs.getString(8));
				dto.setAddress(rs.getString(9));
				dto.setInterest1(rs.getString(10));
				dto.setInterest2(rs.getString(11));
				dto.setInterest3(rs.getString(12));
				dto.setImage(rs.getString(14));
				System.out.println(date.format(now) + " [ " + uid + " ] 정보 조회 성공!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("*** 정보 조회 쿼리문 실행 중 예외 발생! ***");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** [ " + uid + " ] 정보 조회 중 예외 발생! ***");
		}
		
		return dto;
	}

	public boolean signUp(MemberDTO dto) {
		boolean result = false;
		String query = "INSERT INTO member (id, name, birth, job, nick, pwd, phone, email, address, interest1, interest2, interest3, img)"
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
	
	public boolean login (String id, String pass) {
		boolean result = false;
		String query = "SELECT * FROM member WHERE id=? AND pwd=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				result = true;
				System.out.println(date.format(now) + " [ " + id + " ] 로그인 성공!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("*** 로그인 쿼리 실행 중 예외 발생 ***");
		}
		return result;
	}
}
