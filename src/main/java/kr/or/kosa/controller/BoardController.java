package kr.or.kosa.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.service.MemberAddService;
import kr.or.kosa.service.MemberDeleteService;
import kr.or.kosa.service.MemberListService;
import kr.or.kosa.service.MemberRegisterFormService;
import kr.or.kosa.service.MemberUpdateService;
import kr.or.kosa.service.MvcLoginService;
import kr.or.kosa.service.MvcLoginViewService;
import kr.or.kosa.service.MvcLogoutViewService;

@WebServlet("board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BoardController() {
		super();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");

		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlcommand = requestUri.substring(contextPath.length());

		System.out.println("requestUri : " + requestUri);
		System.out.println("contextPath : " + contextPath);
		System.out.println("urlcommand : " + urlcommand);

		Action action = null;
		ActionForward forward = null;

		if (urlcommand.equals("/register.do")) {
			// 회원가입
			action = new MemberAddService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/login.do")) {
			// 로그인 뷰
			action = new MvcLoginViewService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/loginok.do")) {
			// 로그인 로직
			action = new MvcLoginService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/logout.do")) {
			// 로그아웃 로직
			action = new MvcLogoutViewService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/memberlist.do")) {
			// 회원정보
			action = new MemberListService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/memberUpdate.do")) {
			// 회원수정
			action = new MemberUpdateService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/memberdelete.do")) {
			// 회원삭제
			action = new MemberDeleteService();
			forward = action.execute(request, response);

		} else if (urlcommand.equals("/memberRegister.do")) {
			// 회원가입, 수정 view
			action = new MemberRegisterFormService();
			forward = action.execute(request, response);
		}

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {

				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
