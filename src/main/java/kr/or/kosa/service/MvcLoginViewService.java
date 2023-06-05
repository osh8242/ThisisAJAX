package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;

public class MvcLoginViewService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = null;
		try {
			request.setAttribute("pagePath", "/WEB-INF/views/loginForm.jsp");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/main.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
