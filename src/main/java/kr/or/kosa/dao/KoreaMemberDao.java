package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.KoreaMember;
import kr.or.kosa.utils.ConnectionHelper;

public class KoreaMemberDao {

		//로그인 - id 조회
		public boolean isKoreaMemberId(String id) {
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select id from koreaMember where id=?";
			Connection conn = null;
			try {
				  conn = ConnectionHelper.getConnection("oracle");
				  pstmt = conn.prepareStatement(sql);
				  pstmt.setString(1, id);
				  		
				  rs = pstmt.executeQuery();
				  if(rs.next()) {
					  return true;
				  }else {
					  return false;
				  }
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				//Pool에게 반환
				ConnectionHelper.close(conn);
				
			}
			return false;
		}
		
		//로그인 - id, password 조회
		public boolean isKoreaMemberIdPwd(String id, String pwd) {

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select id from koreaMember where id=? AND pwd=?";
			Connection conn = null;
			try {
				  conn = ConnectionHelper.getConnection("oracle");
				  pstmt = conn.prepareStatement(sql);
				  pstmt.setString(1, id);
				  pstmt.setString(2, pwd);
				  		
				  rs = pstmt.executeQuery();
				  if(rs.next()) {
					  return true;
				  }else {
					  //id, password 없음
					  return false;
				  }
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				//Pool에게 반환
				ConnectionHelper.close(conn);
			}
			return false;
		}
		
		public KoreaMember getKoreaMemberById(String id) {
			KoreaMember m = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select id,pwd,name,age,gender,email,ip from koreaMember where id=? ";
			Connection conn = null;
			try {
				  conn = ConnectionHelper.getConnection("oracle");
				  pstmt = conn.prepareStatement(sql);
				  pstmt.setString(1, id);
				  		
				  rs = pstmt.executeQuery();
				  if(rs.next()) {
					  //id, password 있음
					  m =  KoreaMember.builder()
							 .id(rs.getString("id"))
							.pwd(rs.getString("pwd"))
							.name(rs.getString("name"))
							.age(rs.getInt("age"))
							.gender(rs.getString("gender"))
							.email(rs.getString("email"))
							.build();
				  }
				  
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				//Pool에게 반환
				ConnectionHelper.close(conn);
			}
			return m;
		}

		//전체조회
		public List<KoreaMember> getMemberList(){
			
			List<KoreaMember> memberlist = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = ConnectionHelper.getConnection("oracle");
				String sql="select id,pwd,name,age,gender,email from koreaMember";
				pstmt = conn.prepareStatement(sql);
				
				ResultSet rs = pstmt.executeQuery();
				
				memberlist = new ArrayList<KoreaMember>(); //POINT
				
				while(rs.next()) {
					KoreaMember m = KoreaMember.builder()
									.id(rs.getString("id"))
									.pwd(rs.getString("pwd"))
									.name(rs.getString("name"))
									.age(rs.getInt("age"))
									.gender(rs.getString("gender"))
									.email(rs.getString("email"))
									.build();

					memberlist.add(m);
									
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
			
			return memberlist;
		}
		
		
		//상세조회
		public KoreaMember getMember(){
			
			KoreaMember m = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = ConnectionHelper.getConnection("oracle");
				String sql="select id,pwd,name,age,gender,email from koreaMember";
				pstmt = conn.prepareStatement(sql);
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					m = KoreaMember.builder()
									.id(rs.getString("id"))
									.pwd(rs.getString("pwd"))
									.name(rs.getString("name"))
									.age(rs.getInt("age"))
									.gender(rs.getString("gender"))
									.email(rs.getString("email"))
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
			
			return m;
		}
				
		
		//삽입
		public int insertMember(KoreaMember m) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			int resultrow = 0;

			try {
				conn = ConnectionHelper.getConnection("oracle");
				String sql;
				if(m.getAge() != 0) {
					sql="insert into koreaMember(id,pwd,name,gender,email,ip,age) values(?,?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(7,m.getAge());
				} else {
					sql="insert into koreaMember(id,pwd,name,gender,email,ip) values(?,?,?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
				}			
				
				pstmt.setString(1,m.getId());
				pstmt.setString(2,m.getPwd());
				pstmt.setString(3,m.getName());				
				pstmt.setString(4,m.getGender());
				pstmt.setString(5,m.getEmail());
				pstmt.setString(6,m.getIp());
				
				
				resultrow = pstmt.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);//반환
			}
					
			return resultrow;
		}
		
		
		//수정
		public int updateMember(KoreaMember m) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			int resultrow = 0;

			try {
				conn = ConnectionHelper.getConnection("oracle");
				String sql="update koreaMember set name=? , age=? , email=? where id=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,m.getName());
				pstmt.setInt(2,m.getAge());
				pstmt.setString(3,m.getEmail());
				pstmt.setString(4,m.getId());
				
				resultrow = pstmt.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);//반환
			}
					
			return resultrow;
			
		}
		
		//삭제
		public int deleteMember(String id) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			int resultrow = 0;

			try {
				conn = ConnectionHelper.getConnection("oracle");
				String sql="delete from koreaMember where id=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,id);
				
				resultrow = pstmt.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);//반환
			}
			return resultrow;
			
		}
		
		//검색
		public List<KoreaMember> searchByName(String name) {
			List<KoreaMember> memberlist = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = ConnectionHelper.getConnection("oracle");
				String sql="select id,pwd,name,age,gender,email,ip from koreaMember where name like ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, "%" + name + "%");
				
				ResultSet rs = pstmt.executeQuery();
				
				memberlist = new ArrayList<KoreaMember>(); //POINT
				
				while(rs.next()) {
					KoreaMember m = KoreaMember.builder()
									.id(rs.getString("id"))
									.pwd(rs.getString("pwd"))
									.name(rs.getString("name"))
									.age(rs.getInt("age"))
									.gender(rs.getString("gender"))
									.email(rs.getString("email"))
									.ip(rs.getString("ip"))
									.build();

					memberlist.add(m);
									
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
			
			return memberlist;
		}
		
}
