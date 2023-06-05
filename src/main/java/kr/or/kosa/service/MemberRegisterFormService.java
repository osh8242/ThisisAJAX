package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.KoreaMemberDao;
import kr.or.kosa.dto.KoreaMember;

public class MemberRegisterFormService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		
		if(id!=null) {//수정일때
			KoreaMemberDao dao = new KoreaMemberDao();
			KoreaMember m =  dao.getKoreaMemberById(id);
			request.setAttribute("mode", "modify");
			request.setAttribute("member", m);
		}else { 
			request.setAttribute("mode", "register");
		}
		
		request.setAttribute("pagePath", "/WEB-INF/views/joinForm.jsp");
		
	  	ActionForward forward = new ActionForward();
	  	forward.setRedirect(false);
	  	forward.setPath("/main.jsp"); 
		
		return forward;
	}

}
