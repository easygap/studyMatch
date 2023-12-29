package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import common.DBConnPool;

public class CommentDAO extends DBConnPool {

	DataSource dataSource;
	Connection con;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;
	DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();

	public CommentDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/dbcp_myoracle");
			con = dataSource.getConnection();
			System.out.println("댓글 DB 연동 성공");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 댓글 DB 연동 중 예외 발생 ***");
		}
	}
	
//	public CommentDTO getCommentDTO (String id) {
//		CommentDTO dto = null;
//		String query = "SELECT * FROM comments WHERE id=?";
//		try {
//			psmt = con.prepareStatement(query);
//			psmt.setString(1, id);
//			rs = psmt.executeQuery();
//			
//			while (rs.next()) {
//				dto = new CommentDTO();
//				dto.setInter_num(rs.getString("inter_num"));
//				dto.setBoard_num(rs.getString("board_num"));
//				dto.setCommen_num(rs.getString("commen_num"));
//				dto.setContent(rs.getString("content"));
//				dto.setId(rs.getString("id"));
//				dto.setCommen_date(rs.getDate("commen_date"));
//				dto.setLike_count(rs.getString("like_count"));
//				System.out.println("dao.getCommenDTO 댓글 조회 성공");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("*** dao.getCommenDTO 댓글 조회 예외 발생 ***");
//		}
//		
//		return dto;
//	}

	/** 그룹 게시판 댓글 쓰기 */
	public int insertGroupComm(CommentDTO dto) {
		int result = 0;
		int likeCount = dto.getLike_count() != null ? Integer.parseInt(dto.getLike_count()) : 0;
		String query = "INSERT INTO comments (group_num, board_num, commen_num, content, id, like_count) "
				+ "VALUES (?, ?, seq_comm_num.NEXTVAL, ?, ?, ?)";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getGroup_num());
			psmt.setString(2, dto.getBoard_num());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getId());
			psmt.setInt(5, likeCount);
			result = psmt.executeUpdate();
			System.out.println(date.format(now) + " [ " + dto.getCommen_num() + " ] 댓글 DB 업로드 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 댓글 업데이트 중 예외 발생! ***");
			result = 0;
		}
		return result;
	}
	
	// 일반 게시글 댓글 쓰기
	public int insertComm(CommentDTO dto) {
		int result = 0;
		int likeCount = dto.getLike_count() != null ? Integer.parseInt(dto.getLike_count()) : 0;
		String query = "INSERT INTO comments (inter_num, board_num, commen_num, content, id, like_count) "
				+ "VALUES (?, ?, seq_comm_num.NEXTVAL, ?, ?, ?)";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getInter_num());
			psmt.setString(2, dto.getBoard_num());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getId());
			psmt.setInt(5, likeCount);
			result = psmt.executeUpdate();
			System.out.println(date.format(now) + " [ " + dto.getCommen_num() + " ] 댓글 DB 업로드 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 댓글 업데이트 중 예외 발생! ***");
			result = 0;
		}
		return result;
	}
	
	// 댓글 목록 아이디 조회
	public ArrayList<String> idCheck(String num) {
	    ArrayList<String> commIds = new ArrayList<>();
		String query = "SELECT id FROM comments WHERE board_num=? ORDER BY commen_date ASC";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			
			   while (rs.next()) {
				   String commId = rs.getString("id");
		           commIds.add(commId);
			   }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CommentDAO idCheck 댓글 작성자 조회 중 예외 발생");
		}
		return commIds;
	}
	
	// 수정&삭제용 댓글 작성자 조회
	public ArrayList<String> checkId (String num) {
		ArrayList<String> commIds = new ArrayList<>();
		String query = "SELECT id, commen_num, content FROM comments WHERE board_num=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();
			
			   while (rs.next()) {
		            String commId = rs.getString("id");
		            String commNum = rs.getString("commen_num");
		            String content = rs.getString("content");
		            commIds.add(commId);
		            commIds.add(commNum);
		            commIds.add(content);
			   }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("CommentDAO checkId 댓글 작성자, 번호 조회 중 예외 발생");
		}
		return commIds;
	}

	// 수정하기
	public int updateComm(CommentDTO dto) {
		int result = 0;
		String query = "UPDATE comments SET content=? WHERE commen_num=?";

		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getContent());
			psmt.setString(2, dto.getCommen_num());
			result = psmt.executeUpdate();
			System.out.println(dto.getBoard_num() + " 번 게시글, " + dto.getCommen_num() + " 번 댓글 수정 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 댓글 수정 중 예외 발생 ***");
		}
		return result;
	}
	
	// 삭제하기
	public void deleteCommen(String commNum) {
		String query = "DELETE FROM comments WHERE commen_num=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, commNum);
			psmt.executeUpdate();

			System.out.println("DAO " + commNum + "번 댓글 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 댓글 삭제 중 예외 발생  ***");
		}
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
			System.out.println("댓글 자원 해제 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 댓글 자원 해제 중 예외 발생 ***");
		}
		dbConnPool.close();
	}
}
