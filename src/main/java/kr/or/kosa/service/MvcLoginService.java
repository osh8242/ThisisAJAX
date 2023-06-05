package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.KoreaMemberDao;

public class MvcLoginService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String msg = "";
		String url = "";
		
		KoreaMemberDao dao = new KoreaMemberDao();
		boolean idCheck = dao.isKoreaMemberId(id);
		
		if(idCheck) {
			boolean pwdCheck = dao.isKoreaMemberIdPwd(id,pwd);
			if(pwdCheck) {
				msg = "로그인 성공";
				// 세션 생성 및 속성 추가
		        HttpSession session = request.getSession(); // 기존 세션이 있으면 리턴, 없으면 생성
		        if (session != null) {
		            session.setAttribute("id", id);
		            url = "/WEB-INF/views/loginOk.jsp";
		        }
			} else {
				msg = "패스워드가 틀립니다.";
				url = "/WEB-INF/views/loginForm.jsp";
			}
		} else {
			msg = "아이디가 없습니다.";
			url = "/WEB-INF/views/loginForm.jsp";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("pagePath", url);
		
	  	ActionForward forward = new ActionForward();
	  	forward.setRedirect(false);
	  	forward.setPath("/main.jsp"); //동우가 만들었겠지
		
		return forward;
	}

}
