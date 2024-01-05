package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import common.DBConnPool;

public class ServiceDAO extends DBConnPool {
	DataSource dataSource;
	Connection con;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;
	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();

	public ServiceDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/dbcp_myoracle");
			con = dataSource.getConnection();
			System.out.println("ServiceDAO DB 연동 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** ServiceDAO DB 연동 중 예외 발생 ***");
		}
	}

	// 문의글 작성 DB 업로드
	public int insertService(ServiceDTO dto) {
		int result = 0;
		String query = "INSERT INTO inquiry_board (inquiry_num, category_name, detail_name, writer, title, content, img, pass) "
				+ "VALUES (seq_inquiry_num.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getCategory_name());
			psmt.setString(2, dto.getDetail_name());
			psmt.setString(3, dto.getId());
			psmt.setString(4, dto.getTitle());
			psmt.setString(5, dto.getContent());
			psmt.setString(6, dto.getImg());
			psmt.setString(7, dto.getPass());
			result = psmt.executeUpdate();
			System.out.println("serviceDAO 문의글 DB 업로드 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** ServiceDAO 문의글 insert 중 예외 발생 ***");
		}
		return result;
	}

	// 게시글 보기
	public ServiceDTO selectView(int inquiry_num) {
		ServiceDTO dto = new ServiceDTO();
		String query = "SELECT * FROM inquiry_board WHERE inquiry_num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, inquiry_num);
			rs = psmt.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getString("writer"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setImg(rs.getString("img"));
				dto.setPost_date(rs.getDate("post_date"));
				dto.setCategory_name(rs.getString("category_name"));
				dto.setDetail_name(rs.getString("detail_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 로드 중 예외 발생! ***");
		}
		return dto;
	}

	// 아이디 조회
	public String checkSession(int inquiry_num) {
		String checkID = null;
		String query = "SELECT writer FROM inquiry_board WHERE inquiry_num=?";

		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, inquiry_num);
			rs = psmt.executeQuery();

			if (rs.next())
				checkID = rs.getString("writer");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** ServiceDAO checkSession 에러 발생 ***");
		}
		return checkID;
	}

	// 답변 조회
	public ArrayList<ServiceDTO> getList(int inquiry_num) {
		String query = "SELECT answer_content, answer_date FROM inquiry_board "
				+ "WHERE inquiry_num = ? ORDER BY answer_date DESC";
		ArrayList<ServiceDTO> list = new ArrayList<ServiceDTO>();
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, inquiry_num);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ServiceDTO dto = new ServiceDTO();
				dto.setAnswer_content(rs.getString(1));
				dto.setAnswer_date(rs.getDate(2));

				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** ServiceDAO getList 댓글 로드 중 예외 발생! ***");
		}
		return list;
	}

	// 게시글 목록
	public List<ServiceDTO> selectList(Map<String, Object> map) {
		List<ServiceDTO> bbs = new Vector<ServiceDTO>(); // 게시물 목록 담을 변수
		String query = "SELECT * FROM inquiry_board";
		if (map.get("searchWord") != null) {
			query += " AND " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += "ORDER BY inquiry_num DESC";

		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			while (rs.next()) {
				ServiceDTO dto = new ServiceDTO();
				dto.setInquiry_num(rs.getInt("inquiry_num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setId(rs.getString("writer"));
				dto.setPost_date(rs.getDate("post_date"));
				dto.setAnswer_status(rs.getString("answer_status"));
				bbs.add(dto);
				System.out.println("게시글 목록 로드 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 목록 불러오기 중 예외 발생! ***");
		}
		return bbs;
	}

	// 게시물 수
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0; // 게시물 수를 담을 변수
		String query = "SELECT COUNT(*) FROM inquiry_board";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
			System.out.println("게시판 게시글 수 로드 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 카운트 중 예외 발생! ***");
		}
		return totalCount;
	}

	// 게시물 삭제
	public String deletePost(int inquiry_num) {
		String filename = null;

		String query1 = "SELECT img FROM board WHERE inquiry_num=?";

		try {
			psmt = con.prepareStatement(query1);
			psmt.setInt(1, inquiry_num);
			rs = psmt.executeQuery();

			if (rs.next()) {
				filename = rs.getString("img");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 첨부파일 삭제 중 예외 발생 ***");
		}

		String query2 = "DELETE FROM inquiry_board WHERE inquiry_num=?";

		try {
			psmt = con.prepareStatement(query2);
			psmt.setInt(1, inquiry_num);
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 삭제 중 예외 발생 ***");
		}
		return filename;
	}

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
			System.out.println("ServiceDAO 자원 해제 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** ServiceDAO 자원 해제 중 예외 발생 ***");
		}
		dbConnPool.close();
	}
}
