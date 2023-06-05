package kr.or.kosa.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;

public class MvcLogoutViewService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		ActionForward forward = null;
		try {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
