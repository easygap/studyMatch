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
	
	// 댓글 조회
	public ArrayList<CommentDTO> getList (String num) {
		String query = "SELECT C.*, M.nickname FROM COMMENTS C "
				+ "INNER JOIN BOARD B ON C.board_num = B.board_num "
				+ "INNER JOIN MEMBER M ON C.id = M.id "
				+ "WHERE B.board_num = ?";
		ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			rs = psmt.executeQuery();

			while (rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setInter_num(rs.getString(1));
				dto.setBoard_num(rs.getString(2));
				dto.setCommen_num(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setId(rs.getString("nickname"));
				dto.setCommen_date(rs.getDate(6));
				dto.setLike_count(rs.getString(7));
				
				list.add(dto);
				System.out.println(dto.getBoard_num() + "번 게시물 댓글 로드 성공~!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 댓글 로드 중 예외 발생! ***");
		}
		return list;
	}

	// 댓긇 쓰기
	public int insertComm(CommentDTO dto) {
		int result = 0;
		int likeCount = dto.getLike_count() != null ? Integer.parseInt(dto.getLike_count()) : 0;
		String query = "INSERT INTO comments (inter_num, board_num, commen_num, content, id, like_count) "
				+ "VALUES (?, ?, seq_comm_num.NEXTVAL, ?, ?, ?)";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getInter_num());
			psmt.setString(2, dto.getBoard_num());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getId());
			psmt.setInt(5, likeCount);
			result = psmt.executeUpdate();
			System.out.println(date.format(now) + " [ " + dto.getCommen_num() + " ] 댓긇 DB 업로드 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 댓글 업데이트 중 예외 발생! ***");
			result = 0;
		}
		return result;
	}

	// 수정하기
	public int updateComm(CommentDTO dto) {
		int result = 0;
		String query = "UPDATE comments SET " + "inter_num=?, board_num=?, commen_num=?, content=?";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getInter_num());
			psmt.setString(2, dto.getBoard_num());
			psmt.setString(3, dto.getCommen_num());
			psmt.setString(4, dto.getContent());
			result = psmt.executeUpdate();
			System.out.println(dto.getBoard_num() + " 번 게시글, " + dto.getCommen_num() + " 번 댓글 수정 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 댓글 수정 중 예외 발생 ***");
		}
		return result;
	}
	
	// 삭제하기
	public void deleteCommen() {
		CommentDTO dto = new CommentDTO();
		String query = "DELETE FROM comments WHERE inter_num=?, board_num=?, commen_num=?";
		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getInter_num());
			psmt.setString(2, dto.getBoard_num());
			psmt.setString(3, dto.getCommen_num());
			psmt.executeUpdate();
			System.out.println(dto.getBoard_num() + " 번 게시글 " + dto.getCommen_num() + " 번 댓글 삭제 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("*** 댓글 삭제 중 예외 발생  ***");
		}
	}
	
}
