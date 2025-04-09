package servletJDBC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewMed3Servlet")
public class ViewMed3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ViewMed3Servlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/first","root","root@123");
			PreparedStatement ps=con.prepareStatement("SELECT * FROM medicines");
			ResultSet rs=ps.executeQuery();
			pw.print("<table border='1' width='100%'");  
			pw.print("<tr><th>Medicine Id</th><th>Medicine Name</th><th>Medicine Description</th><th>Medicine Price</th><th>Place Order</th></tr>");
			while(rs.next()) {
				rs.getInt("medicine_id");
				rs.getString("medicine_name");
				rs.getString("description");
				rs.getInt("price");
				
				pw.print("<tr><td>"+rs.getInt("medicine_id")+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td><td><a href='PlaceOrder.jsp'>Place Order</a></td></tr>");
			}
			pw.print("</table>");
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}