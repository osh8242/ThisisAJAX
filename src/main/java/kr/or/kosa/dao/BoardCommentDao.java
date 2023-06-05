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
				String sql="select board_seq,seq,content from board_comment where board_seq = ?";
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
		public Board getBoard(String seq){
			
			Board b = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = ConnectionHelper.getConnection("oracle");
				String sql="select seq,title,content,regdate,hit from board where seq=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,seq);

				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					b = Board.builder()
									.seq(rs.getString("seq"))
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
				
}
