package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardCommentDao;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Board;
import kr.or.kosa.dto.BoardComment;

public class BoardDetailService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String seq = request.getParameter("seq");
		
		//게시판 상세정보 
		BoardDao dao = new BoardDao();
		Board b =  dao.getBoard(seq);
		request.setAttribute("board", b);

		//게시판 댓글리스트
		BoardCommentDao commentdao = new BoardCommentDao();
		List<BoardComment> commentlist =null;
		commentlist = commentdao.getBoardCommnetList(seq);
		request.setAttribute("commentlist", commentlist);
	
		request.setAttribute("pagePath", "/WEB-INF/views/boardDetail.jsp");
		
	  	ActionForward forward = new ActionForward();
	  	forward.setRedirect(false);
	  	forward.setPath("/main.jsp"); 
		
		return forward;
	}

}
