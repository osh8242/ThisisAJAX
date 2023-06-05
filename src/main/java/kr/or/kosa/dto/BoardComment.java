package kr.or.kosa.dto;

import lombok.Builder;
import lombok.Data;

/*
	String id = request.getParameter("id"); 
	String pwd = request.getParameter("pwd"); 
	String name = request.getParameter("mname"); 
	int age = Integer.parseInt(request.getParameter("age")); 
	String gender = request.getParameter("gender"); 
	String email = request.getParameter("email"); 
 */
@Data
@Builder
public class BoardComment {
	private String boardSeq;
	private String seq; 
	private String content;
}
