package board;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp2.PStmtKey;

import common.JDBCUtil;

public class BoardDAO {
	// 필드
	private Connection conn = null;
	private PreparedStatement psmt=null;
	private ResultSet rs = null;
		
	//게시글 목록
	public ArrayList<Board> getBoardList(int page){
		ArrayList<Board> boardList = new ArrayList<>();	
		int pageSize = 10;
		conn=JDBCUtil.getConnection();
		String sql = "SELECT * "
				+ "FROM (SELECT ROWNUM rn, board.* "
				+ "            FROM(SELECT * FROM t_board ORDER BY bnum DESC) board) "
				+ "WHERE rn >= ? AND RN <= ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, (page-1)*pageSize + 1); // 시작행
			psmt.setInt(2, pageSize*page); // 페이지당 총 행수
			rs = psmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setBnum(rs.getInt("bnum"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("regdate"));
				board.setModifyDate(rs.getTimestamp("modifydate"));
				board.setHit(rs.getInt("hit"));
				board.setMemberId(rs.getString("memberid"));
				
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, psmt, rs);
		}
		
		return boardList;
		
	}
	
	//게시물 쓰기
	public void addBoard(Board board) {
		conn=JDBCUtil.getConnection();
		String sql = "INSERT INTO t_board(bnum,title,content,memberid,fileupload)"
				+ "VALUES (b_seq.NEXTVAL,?,?,?,?)";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContent());
			psmt.setString(3, board.getMemberId());
			psmt.setString(4, board.getFileUpload());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, psmt);
		}
	}
	
	//게시글 총 개수
	public int getBoardCount() {
		int total = 0;
		conn = JDBCUtil.getConnection();
		String sql = "SELECT COUNT(*) AS total FROM t_board";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next())
				total = rs.getInt("total");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, psmt, rs);
		}
		return total;
	}
	
	public Board getBoard(int bnum) {
		Board board = new Board();
		conn=JDBCUtil.getConnection();
		String sql = "SELECT * FROM t_board WHERE bnum = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bnum); // 글 번호 바인딩
			rs = psmt.executeQuery();
			if(rs.next()) {
				board.setBnum(rs.getInt("bnum"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("regdate"));
				board.setModifyDate(rs.getTimestamp("modifydate"));
				board.setHit(rs.getInt("hit"));
				board.setMemberId(rs.getString("memberid"));
				
				//조회수 증가
				int hit = rs.getInt("hit") + 1;
				sql = "UPDATE t_board SET hit = ? WHERE bnum = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, hit);
				psmt.setInt(2, bnum);
				psmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn, psmt);
		}
		
		return board;
	}
	
	public void deleteBoard(int bnum) {
		conn=JDBCUtil.getConnection();
		String sql = "DELETE FROM t_board WHERE bnum = ?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, bnum);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, psmt);
		}
	}
	
	public void updateBoard(Board board) {
		conn=JDBCUtil.getConnection();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String sql = "UPDATE t_board SET title=?,content=?,"
				+ "modifydate=? WHERE bnum=?";
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContent());
			psmt.setTimestamp(3, now);
			psmt.setInt(4, board.getBnum());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, psmt);
		}
	}
	
}
