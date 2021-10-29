package Lin.NoteBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.temporal.TemporalAccessor;
/**
 * Servlet implementation class Member
 */
@WebServlet("/Member")
public class Member extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Member() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		if(request.getSession().getAttribute("login") == null) {
			response.sendRedirect("Login.html");
		}
		
		out.println("<!Doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title> welcome </title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1> 歡迎光臨 </h1>");
		//out.println("<a href='InputMessage.jsp'>留言板</a> <br/>");
		out.println("<a href='read_Member'>會員資料</a> <br/>");
		out.println("<a href='Box.html'>網紅</a> <br/>");
		out.println("<a href='QueryProductData'>購物網站</a><br/>");
		out.println("<a href='Logout'>登出</a> <br/>");
		out.println("</body>");
		out.println(LocalDateTime.now());
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
