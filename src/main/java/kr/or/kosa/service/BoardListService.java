package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardDao;
import kr.or.kosa.dto.Board;

public class BoardListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	  
		BoardDao dao = new BoardDao();
		List<Board> boardList =null;
		
		boardList = dao.getBoardList();
		
		request.setAttribute("memberList", boardList);
		request.setAttribute("pagePath", "/WEB-INF/views/board.jsp");
  
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/main.jsp"); 
  
		return forward;
	}

}
