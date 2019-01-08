package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDAO;
import vo.BoardVO;

/**
 * Servlet implementation class ReWrite
 */
@WebServlet("/board/boardRe")
public class ReWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReWrite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int BOARD_NUM = Integer.parseInt(request.getParameter("BOARD_NUM"));
		int BOARD_RE_REF = Integer.parseInt(request.getParameter("BOARD_RE_REF"));
		int BOARD_RE_SEQ = Integer.parseInt(request.getParameter("BOARD_RE_SEQ"));
		int BOARD_RE_LEV = Integer.parseInt(request.getParameter("BOARD_RE_LEV"));
		request.setAttribute("BOARD_NUM", BOARD_NUM);
		request.setAttribute("BOARD_RE_REF", BOARD_RE_REF);
		request.setAttribute("BOARD_RE_SEQ", BOARD_RE_SEQ);
		request.setAttribute("BOARD_RE_LEV", BOARD_RE_LEV);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ReplyForm.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String realFolder="";
		String saveFolder="/boardUpload";
		int fileSize=5*1024*1024;
		ServletContext context = request.getServletContext();
		realFolder=context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request,realFolder,fileSize,"UTF-8",new DefaultFileRenamePolicy());
		BoardVO bv = new BoardVO();
		bv.setBOARD_NUM(Integer.parseInt(multi.getParameter("BOARD_NUM")));
		bv.setBOARD_NAME(multi.getParameter("board_name"));
		bv.setBOARD_PASS(multi.getParameter("board_pass"));
		bv.setBOARD_SUBJECT(multi.getParameter("board_subject"));
		bv.setBOARD_CONTENT(multi.getParameter("board_content"));
		bv.setBOARD_RE_REF(Integer.parseInt(multi.getParameter("BOARD_RE_REF")));
		bv.setBOARD_RE_SEQ(Integer.parseInt(multi.getParameter("BOARD_RE_SEQ")));
		bv.setBOARD_RE_LEV(Integer.parseInt(multi.getParameter("BOARD_RE_LEV")));
		
		String filename = multi.getFilesystemName("board_file");
		String orgName = multi.getOriginalFileName("board_file");
		bv.setBOARD_FILE(orgName);
		BoardDAO dao = BoardDAO.getInstance();
		dao.boardReInsert(bv);
		response.sendRedirect("boardList.bo");
	}

}
