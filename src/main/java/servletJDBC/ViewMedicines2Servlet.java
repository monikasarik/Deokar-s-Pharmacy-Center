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
@WebServlet("/ViewMedicines2Servlet")
public class ViewMedicines2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ViewMedicines2Servlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.println("<a href='AddMedicines.html'>Add new Medicine</a><br><br>");
		pw.println("<a href='ViewMed3Servlet'>View Medicine List</a>");
		String MedName=request.getParameter("Medname");
		String des=request.getParameter("desc");
		String price=request.getParameter("price");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/first","root","root@123");
			PreparedStatement ps=con.prepareStatement("INSERT INTO medicines (medicine_name,description,price) VALUES(?,?,?)");
			ps.setString(1, MedName);
			ps.setString(2, des);
			ps.setString(3, price);
			int no=ps.executeUpdate();
			if(no>0) {
				pw.println("Added  successfully...");
			}
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/first","root","root");
				PreparedStatement ps1=con1.prepareStatement("SELECT * FROM medicines");
				ResultSet rs=ps1.executeQuery();
				pw.print("<table border='1' width='100%'");  
				pw.print("<tr><th>Medicine Id</th><th>Medicine Name</th><th>Medicine Description</th><th>Medicine Price</th><th>Place Order</th><th>Delete</th></tr>");
				
				
				while(rs.next()) {
					rs.getInt(1);
					rs.getString(2);
					rs.getString(3);
					rs.getInt(4);
					pw.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td></tr>");
				}
		     
		     pw.print("</table>");
				
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
