package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.KoreaMemberDao;
import kr.or.kosa.dto.KoreaMember;

public class MemberListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	  
		String name = request.getParameter("searchName");
		KoreaMemberDao dao = new KoreaMemberDao();
		List<KoreaMember> memberList =null;
		
		if(name == null) {//전체검색
			memberList = dao.getMemberList();
		}else {	//조건검색(이름검색)
			memberList = dao.searchByName(name);
		}
		
		request.setAttribute("memberList", memberList);
		request.setAttribute("pagePath", "/WEB-INF/views/memberlist.jsp");
  
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/main.jsp"); 
  
		return forward;
	}

}
