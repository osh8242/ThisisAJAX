package kr.or.kosa.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BoardCommentDao;
import kr.or.kosa.dao.CommentDao;
import kr.or.kosa.dto.BoardComment;

import net.sf.json.JSONArray;


@WebServlet("/refresh")
public class RefreshComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RefreshComment() {
        super();
       
    }
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
    	
    	String board_seq = request.getParameter("board_seq");
    	
    	BoardCommentDao commentdao = new BoardCommentDao();
		List<BoardComment> commentlist = null;
		commentlist = commentdao.getBoardCommnetList(board_seq);
		
		JSONArray jsonarray = JSONArray.fromObject(commentlist);
		
		out.print(jsonarray.toString());
	    out.flush();
	    out.close();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
