package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.Board_comment;
import kr.or.kosa.utils.ConnectionHelper;

public class CommentDao {
	
	public List<Board_comment> getCommentsBySeq(String board_seq){
		
		List<Board_comment> commentList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql="select board_seq, seq, content from board_comment where board_seq=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board_seq);
			ResultSet rs = pstmt.executeQuery();			
			
			commentList = new ArrayList<Board_comment>(); //POINT
			
			while(rs.next()) {
				Board_comment c = Board_comment.builder()
								.board_seq(rs.getInt("board_seq"))
								.content(rs.getString("content"))
								.seq(rs.getInt("seq"))
								.build();				
				commentList.add(c);
								
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
		
		return commentList;
	}
	
	
	
	
}
