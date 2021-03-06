package Lin.NoteBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.util.*;
/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
    Statement stmt=null;
    ResultSet rs;
    
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
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=big5");
                request.setCharacterEncoding("big5");
        
        PrintWriter out=response.getWriter();
        
        try {
			Class.forName("com.mysql.jdbc.Driver");
			con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/book?characterEncoding=utf-8","root","");
			stmt=(Statement) con.createStatement();
			
			
			
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
			Scanner sc = new Scanner(System.in);
			System.out.println("????????????(Y/N):");
			String x = sc.next();
			if("Y".contentEquals(x)) {
			list.clear();
			}
			HttpSession session = request.getSession();
	                session.setAttribute("messageList",list);
	        //session.setMaxInactiveInterval(0);
	        
	        response.sendRedirect("Input.jsp");
	        
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
			        out.print("??????????????????   <br/>");
				out.print("<a href='Input.jsp'>??????????????????</a> <br/>");
			}

	}

	

	

	
 }

	
