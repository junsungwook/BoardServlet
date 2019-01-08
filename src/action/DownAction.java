package action;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownAction
 */
@WebServlet("/board/down")
public class DownAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String BOARD_FILE = request.getParameter("BOARD_FILE");
		String savePath="boardUpload";
		ServletContext context = getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		String sFilePath = sDownloadPath+"\\"+BOARD_FILE;
		byte b[]=new byte[4096];
		System.out.println(sFilePath);
		FileInputStream in = new FileInputStream(sFilePath);
		String sMimeType = getServletContext().getMimeType(sFilePath);
		System.out.println("sMimeType>>>"+sMimeType);

		if(sMimeType == null)
			sMimeType = "application/octet-stream";

		response.setContentType(sMimeType);
		String agent = request.getHeader("User-Agent");
		boolean ieBrowser = (agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1);

		if(ieBrowser){
			BOARD_FILE = URLEncoder.encode(BOARD_FILE,"UTF-8".replaceAll("\\+", "%20"));
		}else{
			BOARD_FILE = new String(BOARD_FILE.getBytes("UTF-8"),"iso-8859-1");
		}

		response.setHeader("Content-Disposition", "attachment; BOARD_FILE="+BOARD_FILE);
		ServletOutputStream out2 = response.getOutputStream();
		int numRead;

		while((numRead = in.read(b, 0, b.length)) != -1){
			out2.write(b,0,numRead);
		}
		out2.flush();
		out2.close();
		in.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
