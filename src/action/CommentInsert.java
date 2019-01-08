package action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.CommentVO;

/**
 * Servlet implementation class CommentInsert
 */
@WebServlet("/board/C_Insert")
public class CommentInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String msg = request.getParameter("msg");
		String userid = request.getParameter("userid");
		int bnum = Integer.parseInt(request.getParameter("BOARD_NUM"));
		CommentVO cb = new CommentVO();
		BoardDAO dao = BoardDAO.getInstance();
		cb.setBnum(bnum);
		cb.setMsg(msg);
		cb.setUserid(userid);
		dao.commentInsert(cb);
		response.sendRedirect("C_List?BOARD_NUM="+bnum);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
