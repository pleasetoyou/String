package Lin.NoteBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Stock
 */
@WebServlet("/Stock")
public class Stock extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;
	Connection con1=null;
    Statement  stmt=null;
    Statement  stmt1=null;
    ResultSet   rs;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stock() {
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
		request.setCharacterEncoding("big5");
		response.setCharacterEncoding("big5");
		response.setContentType("text/html;big5");
		PrintWriter  out=response.getWriter();
		out.println("<html><head></head><body>");
		
		String booknum = request.getParameter("booknum");
		String num = request.getParameter("num");
		
		int target = Integer.parseInt(num);
		
		try {					
			Class.forName("com.mysql.jdbc.Driver");
	        con=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
	        stmt=con.createStatement();
	        rs=stmt.executeQuery("select  *  from  product where booknum = '"+booknum+"' ");
			
			while(rs.next())   
			{				
				String stock = rs.getString("stock");
				System.out.println("booknum:"+booknum);
				System.out.println("原本的stock:"+stock);
			    
				
				int number = Integer.parseInt(stock);
				System.out.println("增加數量:"+num);
				number  = number + target;
				//System.out.println(number);
				String good = Integer.toString(number);
				System.out.println("現在的stock:"+good);
					
				
				try {
					  Class.forName("com.mysql.jdbc.Driver");
		              con1=DriverManager.getConnection("jdbc:mysql://localhost/book","root","");
		              stmt1=con1.createStatement();
	            	  stmt1.executeUpdate("Update product set stock='"+good+"' where booknum='"+booknum+"'");
	        		  
	              }catch(SQLException se)
	    		  {    
	            	  out.println("操作失敗 <br/>");	    		       	    		       
	    		  }
		   }
			   response.sendRedirect("QueryProductData");
			   //out.println("<a href='QueryProductData'>購物網站</a> <br/>");
			   out.print("</body></html>");
			   out.close();
		}catch(Exception e)
	       {
			  out.print(e);
	       }
		   finally {
			   try {
			      
				  stmt.close();
	              con.close();
	              stmt1.close();
	              con1.close();
			   }catch(Exception e)
			   {
				   out.print(e);
			   }
		   }
	}

}
