<%@page import="kr.or.kosa.dao.CommentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.List" %>
<%@ page import="net.sf.json.JSONArray" %>

<%
	String comment = request.getParameter("content");
	String board_seq = request.getParameter("board_seq");
	//System.out.println(bbsSeq + " / " + comment);
	
%>









