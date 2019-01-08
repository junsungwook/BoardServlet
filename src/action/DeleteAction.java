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

/**
 * Servlet implementation class DeleteAction
 */
@WebServlet("/board/delete")
public class DeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int BOARD_NUM = Integer.parseInt(request.getParameter("BOARD_NUM"));
		request.setAttribute("BOARD_NUM", BOARD_NUM);
		RequestDispatcher dispatcher = request.getRequestDispatcher("deleteForm.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int BOARD_NUM = Integer.parseInt(request.getParameter("BOARD_NUM"));
		String BOARD_PASS = request.getParameter("BOARD_PASS");
		BoardDAO dao  = BoardDAO.getInstance();
		boolean resp = dao.delBoard(BOARD_NUM,BOARD_PASS);
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
