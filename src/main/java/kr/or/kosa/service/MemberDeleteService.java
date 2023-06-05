package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.KoreaMemberDao;

public class MemberDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	  
	  String id = request.getParameter("id");
 
	  KoreaMemberDao dao = new KoreaMemberDao();
  	  int result = dao.deleteMember(id);
  	  
  	  String msg="";
  	  if(result > 0) {
  		  msg="삭제 성공";
  	  }else {
  		  msg="삭제 실패";
  	  }
  	  
  	  request.setAttribute("msg", msg);
  	  request.setAttribute("pagePath", "/WEB-INF/views/memberlist.jsp");
  	
  	  ActionForward forward = new ActionForward();
  	  forward.setRedirect(false);
  	  forward.setPath("/memberlist.do"); 
  	  
  	  return forward;
	}

}
