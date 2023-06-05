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

			List<Board> boardlist = null;
			Connection conn = null;
			PreparedStatement pstmt = null;

			try {
				conn = ConnectionHelper.getConnection("oracle");
				String sql="select seq,title,content,regdate,hit from board";
				pstmt = conn.prepareStatement(sql);

				ResultSet rs = pstmt.executeQuery();

				boardlist = new ArrayList<Board>(); //POINT

				while(rs.next()) {
					Board b = Board.builder()
									.seq(rs.getInt("seq"))
									.title(rs.getString("title"))
									.content(rs.getString("content"))
									.regdate(rs.getString("regdate"))
									.hit(rs.getInt("hit"))
									.build();
					boardlist.add(b);

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

			return boardlist;
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

}
