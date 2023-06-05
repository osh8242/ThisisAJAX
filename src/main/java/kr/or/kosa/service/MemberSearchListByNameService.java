package kr.or.kosa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.KoreaMemberDao;
import kr.or.kosa.dto.KoreaMember;

public class MemberSearchListByNameService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String name = request.getParameter("name");
		
		KoreaMemberDao dao = new KoreaMemberDao();
		List<KoreaMember> memberListByName = dao.searchByName(name);
		request.setAttribute("memberList", memberListByName);
  
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/redirect.jsp"); //동우가 만들었겠지
  
		return forward;
	}

}
