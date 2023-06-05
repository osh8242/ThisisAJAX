package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.Board;
import kr.or.kosa.utils.ConnectionHelper;

public class BoardDao {
	//전체조회
	public List<Board> getBoardList(){
		
		List<Board> boardList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql="select seq, title, content, regdate, hit from board";
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			boardList = new ArrayList<Board>(); //POINT
			
			while(rs.next()) {
				Board b = Board.builder()
								.seq(rs.getInt("seq"))
								.title(rs.getString("title"))
								.content(rs.getString("content"))
								.regdate(rs.getString("regdate"))
								.hit(rs.getInt("age"))
								.build();				
				boardList.add(b);
								
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
		
		return boardList;
	}
}
