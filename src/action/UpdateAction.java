package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import vo.BoardVO;

/**
 * Servlet implementation class UpdateAction
 */
@WebServlet("/board/update")
public class UpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String BOARD_SUBJECT = request.getParameter("BOARD_SUBJECT");
		String BOARD_CONTENT = request.getParameter("BOARD_CONTENT");
		int BOARD_NUM = Integer.parseInt(request.getParameter("BOARD_NUM"));
		BoardVO bv = new BoardVO();
		bv.setBOARD_CONTENT(BOARD_CONTENT);
		bv.setBOARD_SUBJECT(BOARD_SUBJECT);
		bv.setBOARD_NUM(BOARD_NUM);
		request.setAttribute("bv", bv);
		RequestDispatcher dispatcher = request.getRequestDispatcher("updateForm.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String BOARD_SUBJECT = request.getParameter("BOARD_SUBJECT");
		String BOARD_CONTENT = request.getParameter("BOARD_CONTENT");
		String BOARD_PASS = request.getParameter("BOARD_PASS");
		int BOARD_NUM = Integer.parseInt(request.getParameter("BOARD_NUM"));
		BoardDAO dao  = BoardDAO.getInstance();
		boolean resp = dao.updateBoard(BOARD_NUM,BOARD_PASS,BOARD_SUBJECT,BOARD_CONTENT);
		if(resp==false){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
            out.println("<script>alert('비밀번호가 다릅니다'); history.go(-1);</script>");
            out.flush(); 
		}
		else{
			response.sendRedirect("boardList.bo");
		}
	}

}
