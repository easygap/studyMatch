package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

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
			dataSource = (DataSource) context.lookup("dbcp_myoracle");
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
					+ " board_num, title, content, img, id, post_date, visit_count, like_count)" + " VALUES ( "
					+ " seq_board_num.NEXTVAL, ?, ?, ?, ?, ?, 0, 0)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getWriter());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시글 작성 중 예외 발생! ***");
		} finally {
			//close();
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
			psmt.setString(3, dto.getNum());
			psmt.setString(4, dto.getTitle());
			psmt.setString(5, dto.getContent());
			result = psmt.executeUpdate();
			System.out.println(dto.getNum() + "번 게시글 수정 성공");
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
			String query = "DELETE FROM board WHERE num=?";
			psmt = con.prepareStatement(query);
			psmt.setString(3, dto.getNum());
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

		try {
			con = dataSource.getConnection();
			String query = "SELECT B.*, M.id " + " FROM member M INNER JOIN board B " + " ON M.id = B.id "
					+ " WHERE num=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();

			if (rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setWriter(rs.getString(7));
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

	// 검색 조건에 맞는 게시글 목록
	public List<BoardDTO> selectList(Map<String, Object> map) {
		List<BoardDTO> bbs = new Vector<BoardDTO>(); // 게시물 목록 담을 변수

		String query = "SELECT * FROM board ";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " " + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += " ORDER BY num DESC ";

		try {
			stmt = con.createStatement(); // 쿼리문 생성
			rs = stmt.executeQuery(query); // 쿼리문 실행
			System.out.println("Query: " + query);
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getString("num")); // 게시물 번호
				dto.setTitle(rs.getString("title")); // 게시물 제목
				dto.setContent(rs.getString("content")); // 게시물 내용
				dto.setWriter(rs.getString("writer")); // 게시물 작성자
				dto.setVisitcount(rs.getString("visitcount")); // 게시물 조회수
				dto.setLikecount(rs.getString("likecount")); // 게시물 추천수
				dto.setCommcount(rs.getString("commcount")); // 게시물 댓글수
				dto.setPostdate(rs.getDate("postdate")); // 게시물 작성일

				bbs.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 게시물 검색 목록 불러오기 중 예외 발생! ***");
		}
		return bbs;

	}
	
//  검색 조건에 맞는 게시물 목록을 반환합니다(페이징 기능 지원).
	public List<BoardDTO> selectListPage(Map<String,Object> map) {
		List<BoardDTO> board = new Vector<BoardDTO>();
			// 쿼리문 준비
			String query = " "
						+ "SELECT * FROM ( "
						+ "		SELECT Tb.*, ROWNUM rNum FROM ( "
						+ "			SELECT * FROM mvcboard ";
			// 검색 조건이 있다면 WHERE절로 추가
			if (map.get("searchWord") != null)
			{
					query += " WHERE " + map.get("searchField")
							+ " LIKE '%" + map.get("searchWord") + "%' ";
			}
			
			query += "		ORDER BY idx DESC"
					+ "		) Tb "
					+ " ) "
					+ " WHERE rNum BETWEEN ? AND ?";	// 게시물 구간은 인파라미터로..
			try {
				psmt = con.prepareStatement(query);		// 동적 쿼리문 생성
				psmt.setString(1, map.get("start").toString());	// 인파라미터 설정
				psmt.setString(2, map.get("end").toString());
				rs = psmt.executeQuery();	// 쿼리문 실행
				
				// 반환된 게시물 목록을 List 컬렉션에 추가
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					
					dto.setNum(rs.getString(1));
					dto.setTitle(rs.getString(2));
					dto.setContent(rs.getString(3));
					dto.setWriter(rs.getString(4));
					dto.setVisitcount(rs.getString(5));
					dto.setLikecount(rs.getString(6));
					dto.setCommcount(rs.getString(7));
					dto.setPostdate(rs.getDate(8));
					
					board.add(dto);
				}
			}
			catch (Exception e) {
				System.out.println("게시물 조회 중 예외 발생");
				e.printStackTrace();
			}
			return board;	// 목록 반환
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
		}
	}
}