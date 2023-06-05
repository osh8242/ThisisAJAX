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
public class KoreaMember {
	private String id;
	private String pwd;
	private String name;
	private int age;
	private String gender;
	private String email;
	private String ip;
}
