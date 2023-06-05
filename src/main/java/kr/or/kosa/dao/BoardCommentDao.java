package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.BoardComment;
import kr.or.kosa.utils.ConnectionHelper;

public class BoardCommentDao {

		//전체조회
		public List<BoardComment> getBoardCommnetList(String seq){
			
			List<BoardComment> commentlist = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = ConnectionHelper.getConnection("oracle");
				String sql="select board_seq,seq,content from board_comment where board_seq = ?order by seq desc ";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,seq);
				
				ResultSet rs = pstmt.executeQuery();
				
				commentlist = new ArrayList<BoardComment>(); //POINT
				
				while(rs.next()) {
					BoardComment bc = BoardComment.builder()
									.boardSeq(rs.getString("board_seq"))
									.seq(rs.getString("seq"))
									.content(rs.getString("content"))
									.build();
					commentlist.add(bc);
									
				}
				
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				
				//Pool에게 반환
				ConnectionHelper.close(conn);
					
			}catch (Exception e) {
				System.out.println(e.getMessage());
				e.getStackTrace();
				
			}finally {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);//반환
			}
			
			return commentlist;
		}
		
		
		//상세조회
		public Board getBoardCommnet(String seq){
			
			Board b = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = ConnectionHelper.getConnection("oracle");
				String sql="select seq,title,content,regdate,hit from board where seq=? ";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,seq);

				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					b = Board.builder()
									.seq(rs.getInt("seq"))
									.title(rs.getString("title"))
									.content(rs.getString("content"))
									.regdate(rs.getString("regdate"))
									.hit(rs.getInt("hit"))
									.build();
				}
				
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				
				//Pool에게 반환
				ConnectionHelper.close(conn);
					
			}catch (Exception e) {
				System.out.println(e.getMessage());
				e.getStackTrace();
				
			}finally {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);//반환
			}
			
			return b;
		}
		
		public int deleteComment(String board_seq, String seq){
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = ConnectionHelper.getConnection("oracle");
				String sql="delete from board_comment where board_seq=? and seq=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, board_seq);
				pstmt.setString(2, seq);
				int result = pstmt.executeUpdate();
				return result;
					
			}catch (Exception e) {
				System.out.println(e.getMessage());
				e.getStackTrace();
				
			}finally {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);//반환
			}
			
			return -1;
		}	
		
		public int insertComment(BoardComment comment){
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = ConnectionHelper.getConnection("oracle");
				String sql="insert into board_comment(board_seq, seq, content) values(?, commentseq.nextval, ?)";;
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, comment.getBoardSeq());
				pstmt.setString(2, comment.getContent());
				
				int result = pstmt.executeUpdate();
				return result;
					
			}catch (Exception e) {
				System.out.println(e.getMessage());
				e.getStackTrace();
				
			}finally {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);//반환
			}
			
			return -1;
		}
		
		public int updateComment(BoardComment comment){
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = ConnectionHelper.getConnection("oracle");
				String sql="update board_comment set content = ? where board_seq =? and seq= ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, comment.getContent());
				pstmt.setString(2, comment.getBoardSeq());
				pstmt.setString(3, comment.getSeq());
				
				int result = pstmt.executeUpdate();
				return result;
					
			}catch (Exception e) {
				System.out.println(e.getMessage());
				e.getStackTrace();
				
			}finally {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);//반환
			}
			
			return -1;
		}
				
}
