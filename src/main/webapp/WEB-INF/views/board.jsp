<%@page import="kr.or.kosa.dao.CommentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.List" %>
<%@ page import="net.sf.json.JSONArray" %>

<%
	String comment = request.getParameter("content");
	String board_seq = request.getParameter("board_seq");
	//System.out.println(bbsSeq + " / " + comment);
	
	CommentDao dao = dao.getCommentsBySeq(board_seq);

	//덧글등록
	dao.addComment(comment);
	List<CommentVO> commentlist = dao.getCommentList(bbsSeq);
	
	//JSON 데이터
	JSONArray jsonarray = JSONArray.fromObject(commentlist);
	//JSON 날짜 형식 (DATE 형식 안되요 ....)
%>
<%=jsonarray %>









