package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.KoreaMemberDao;
import kr.or.kosa.dto.KoreaMember;

public class MemberUpdateService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	  
	  String id = request.getParameter("id");
  	  String name = request.getParameter("name");
  	  int age = Integer.parseInt(request.getParameter("age"));
  	  String email = request.getParameter("email");
		
  	  KoreaMember m = KoreaMember.builder()
					.id(id)
					.name(name)
					.age(age)
					.email(email)
					.build();
	
  	  KoreaMemberDao dao = new KoreaMemberDao();
  	  int result = dao.updateMember(m);
  	  
  	  String msg="";
  	  if(result > 0) {
  		  msg="수정 성공";
  	  }else {
  		  msg="수정 실패";
  	  }
  	  
  	  request.setAttribute("msg", msg);
//  	  request.setAttribute("pagePath", "/WEB-INF/views/memberlist.jsp");
  	  
  	  ActionForward forward = new ActionForward();
  	  forward.setRedirect(false);
  	  forward.setPath("/memberlist.do"); //동우가 만들었겠지
  	  
  	  return forward;
	}

}
