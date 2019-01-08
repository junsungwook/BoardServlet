package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.BoardDAO;
import vo.CommentVO;

/**
 * Servlet implementation class CommentList
 */
@WebServlet("/board/C_List")
public class CommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentList() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BoardDAO dao = BoardDAO.getInstance();
		int bnum = Integer.parseInt(request.getParameter("BOARD_NUM"));
		ArrayList<CommentVO> arr = dao.commentList(bnum);
		JSONArray jarr = new JSONArray();
		for(CommentVO cb : arr){
			JSONObject obj = new JSONObject();
			obj.put("bnum",cb.getBnum());
			obj.put("cnum",cb.getCnum());
			obj.put("msg",cb.getMsg());
			obj.put("userid",cb.getUserid());
			obj.put("regdate",cb.getRegdate());
			jarr.add(obj);
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jarr.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
