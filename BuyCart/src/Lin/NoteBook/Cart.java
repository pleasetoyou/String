package Lin.NoteBook;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
    Statement  stmt=null;
    ResultSet   rs;
    
    List<Cart> list = new ArrayList();
    
    private String booknum;
    private String bookname;
    private String price;
    private Date createTime;
    public String getBooknum() {
		return booknum;
	}

	public void setBooknum(String booknum) {
		this.booknum = booknum;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=big5");
        request.setCharacterEncoding("big5");
        //String booknum = request.getParameter("booknum");
        
        PrintWriter  out=response.getWriter();
		//out.println("<html><head></head><body>");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/book?characterEncoding=utf-8","root","");
			stmt=con.createStatement();
			String booknum = request.getParameter("booknum");
			
			
			
			rs=stmt.executeQuery("select * from product WHERE booknum = '"+booknum+"' ");
			while(rs.next()) {
			String bookname = rs.getString("bookname");
			String price = rs.getString("price");
			
			Date createTime = new Date();
	        Cart message = new Cart();
	        
	        message.setBooknum(booknum);
	        message.setBookname(bookname);
	        message.setPrice(price);
			message.setCreateTime(createTime);
			
			list.add(message);
			
			HttpSession session = request.getSession();
	        session.setAttribute("messageList",list);
	        response.sendRedirect("InputCart.jsp");
	        
	        stmt.close();
	        con.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			out.print(e);
		}finally {
			//try {
				//stmt.close();
		        //con.close();
			 //catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			    out.print("編號輸入錯誤   <br/>");
				out.print("<a href='InputCart.jsp'>重回購物車</a> <br/>");
			//}
	}

  }
}
	
