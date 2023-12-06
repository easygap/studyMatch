package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import common.DBConnPool;

public class BoardDAO extends DBConnPool {

	DataSource dataSource;
	Connection con;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;
	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	public BoardDAO() {
		try {

	         Context context = new InitialContext();
	         dataSource = (DataSource) context.lookup("java:comp/env/dbcp_myoracle");
	         con = dataSource.getConnection();
	         System.out.println("DB 연동 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** DB 연동 중 예외 발생 ***");
		}
	}
	// 글쓰기
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			String query = "INSERT INTO board ( "
					+ " inter_num, board_num, title, content, img, id, post_date, visit_count, like_count)" + " VALUES ( "
					+ " ?, seq_board_num.NEXTVAL, ?, ?, ?, ?, ?, 0, 0)";
			psmt = con.prepareStatement(query);
			psmt.setString(3, dto.getTitle());
			psmt.setString(4, dto.getContent());
			psmt.setString(6, dto.getId());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시글 작성 중 예외 발생! ***");
		}
		return result;
	}
	// 수정하기
	public int updateEdit(BoardDTO dto) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			String query = "UPDATE board SET " + " title=?, content=? " + " WHERE board_num=?";

			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getBoard_num());
			result = psmt.executeUpdate();
			System.out.println(dto.getBoard_num() + "번 게시글 수정 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 수정 중 예외 발생 ***");
		}
		return result;
	}
	// 게시물 삭제
	public int deletePost(BoardDTO dto) {
		int result = 0;

		try {
			con = dataSource.getConnection();
			String query = "DELETE FROM board WHERE board_num=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getBoard_num());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 삭제 중 예외 발생 ***");
		}
		return result;
	}
	
	// 선택한 게시물 보기
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
		String query = "SELECT B.*, M.id " + " FROM member M INNER JOIN board B " + " ON M.id = B.id "
				+ " WHERE board_num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			con = dataSource.getConnection();
			rs = psmt.executeQuery();

			if (rs.next()) {
				dto.setBoard_num(rs.getString("num"));
				dto.setId(rs.getString(1));
				System.out.println("게시물 로드 성공~!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 로드 중 예외 발생! ***");
		}
		return dto;
	}
	
	// 검색 조건에 맞는 게시글 수
	public int selectCount(Map<String, Object> map) { // 게시글 검색
		int totalCount = 0; // 게시물 수를 담을 변수
		String query = "SELECT COUNT(*) FROM board";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1); // 첫 번째 컬럼 값
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 검색 카운트 중 예외 발생! ***");
		}
		return totalCount; // 게시물 개수를 서블릿으로 반환
	}
	// 게시글 목록
	public List<BoardDTO> selectList(Map<String, Object> map, String interest) {
		List<BoardDTO> bbs = new Vector<BoardDTO>(); // 게시물 목록 담을 변수
		String query = "SELECT * FROM board ";
		if (map.get("searchWord") != null) {
			query += " AND " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += "WHERE inter_num=? ORDER BY board_num DESC ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, interest);
			rs = psmt.executeQuery(); // 쿼리문 실행
			System.out.println("Query: " + query);
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_num(rs.getString("board_num")); // 게시물 번호
				dto.setTitle(rs.getString("title")); // 게시물 제목
				dto.setContent(rs.getString("content")); // 게시물 내용
				dto.setId(rs.getString("Id")); // 게시물 작성자
				dto.setVisit_count(rs.getString("visit_count")); // 게시물 조회수
				dto.setLike_count(rs.getString("like_count")); // 게시물 추천수
//				dto.setCommen_count(rs.getString("commcount")); // 게시물 댓글수
				dto.setPost_date(rs.getDate("post_date")); // 게시물 작성일
				bbs.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 검색 목록 불러오기 중 예외 발생! ***");
		}
		return bbs;
	}

	public void close() {
		try {
		if (rs != null) rs.close();
		if (psmt != null) psmt.close();
		if (con != null) con.close();
		if (stmt != null) stmt.close();
		System.out.println("게시판 자원 해제 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시판 자원 해제 중 예외 발생 ***");
		} finally {
			close();
		}
	}
}