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
		int visitCount = dto.getVisit_count() != null ? Integer.parseInt(dto.getVisit_count()) : 0;
		int likeCount = dto.getLike_count() != null ? Integer.parseInt(dto.getLike_count()) : 0;
		String query = "INSERT INTO board ( "
				+ " inter_num, board_num, title, content, img, id, visit_count, like_count)" + " VALUES ( "
				+ " ?, seq_board_num.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getInter_num());
//			psmt.setString(2, dto.getBoard_num());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getImg());
			psmt.setString(5, dto.getId());
			psmt.setInt(6, visitCount);
			psmt.setInt(7, likeCount);

			result = psmt.executeUpdate();
			System.out.println(date.format(now) + " [ " + dto.getId() + " ] 게시글 DB 업로드 완료");
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
			String query = "UPDATE board SET " + " title=?, content=? img=? " + " WHERE board_num=?";

			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getImg());
			psmt.setString(4, dto.getBoard_num());
			result = psmt.executeUpdate();
			System.out.println(dto.getBoard_num() + "번 게시글 수정 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 수정 중 예외 발생 ***");
		}
		return result;
	}

	// 게시물 삭제, 수정의 visible / invisible 처리를 위한 유저 정보 전달
	public String checkSession(String num) {
		String checkID = null;
		
		String query = "SELECT id FROM board WHERE board_num = ?";
		
		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			
			if (rs.next()) 
				checkID = rs.getString("id");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 조회중 에러 발생 ***");
		}
		return checkID;
	}
	
	
	// 게시물 삭제
	public String deletePost(String num) {
		String filename = null;
		
		String query1 = "SELECT img FROM board WHERE board_num=?";
		
		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query1);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				filename = rs.getString("img");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 첨부파일 삭제 중 예외 발생 ***");
		}
		
		String query2 = "DELETE FROM board WHERE board_num=?";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query2);
			psmt.setString(1, num);
			psmt.executeUpdate();
			System.out.println(num + "번 게시글 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 삭제 중 예외 발생 ***");
		}
		
		return filename;
	}


	// 선택한 게시물 보기
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
		String query = "SELECT B.*, M.nickname " + " FROM member M INNER JOIN board B " + " ON M.id = B.id "
				+ " WHERE board_num=?";
		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();

			if (rs.next()) {
				dto.setGroud_num(rs.getString("GROUD_NUM"));
				dto.setInter_num(rs.getString("INTER_NUM"));
				dto.setBoard_num(rs.getString("BOARD_NUM"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setImg(rs.getString("IMG"));
				dto.setId(rs.getString("nickname"));
				dto.setPost_date(rs.getDate("POST_DATE"));
				dto.setVisit_count(rs.getString("VISIT_COUNT"));
				dto.setLike_count(rs.getString("LIKE_COUNT"));
				System.out.println(dto.getBoard_num() + "번 게시물 로드 성공~!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 로드 중 예외 발생! ***");
		}
		return dto;
	}
	
	// 게시물 조회수 +1
	public void updateVisitCount(String num) {
		String query = "UPDATE board SET visit_count = visit_count + 1 where board_num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeQuery();
		}
		catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
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
			System.out.println("게시판 게시글 수 로드 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 카운트 중 예외 발생! ***");
		}
		return totalCount; // 게시물 개수를 서블릿으로 반환
	}

	// 게시글 목록
	public List<BoardDTO> selectList(Map<String, Object> map, String interest) {
		List<BoardDTO> bbs = new Vector<BoardDTO>(); // 게시물 목록 담을 변수
		String query = "SELECT B.*, M.nickname FROM member M INNER JOIN board B ON M.id = B.id WHERE inter_num=? ";
		if (map.get("searchWord") != null) {
			query += " AND " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += "ORDER BY board_num DESC";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query);
			psmt.setString(1, interest);
			rs = psmt.executeQuery(); // 쿼리문 실행
			System.out.println("Query: " + query);
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_num(rs.getString("board_num")); // 게시물 번호
				dto.setTitle(rs.getString("title")); // 게시물 제목
				dto.setContent(rs.getString("content")); // 게시물 내용
				dto.setId(rs.getString("nickname")); // 게시물 작성자
				dto.setVisit_count(rs.getString("visit_count")); // 게시물 조회수
				dto.setLike_count(rs.getString("like_count")); // 게시물 추천수
//				dto.setCommen_count(rs.getString("commcount")); // 게시물 댓글수
				dto.setPost_date(rs.getDate("post_date")); // 게시물 작성일
				bbs.add(dto);
				System.out.println("게시글 목록 로드 성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 목록 불러오기 중 예외 발생! ***");
		}
		return bbs;
	}

	public void close() {
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
	}
}