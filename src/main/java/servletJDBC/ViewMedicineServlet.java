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

@WebServlet("/ViewMedicineServlet")
public class ViewMedicineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ViewMedicineServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		if(username=="" && password=="") {
			pw.println("<h3>Please enter Username ans Password</h3>");
			response.sendRedirect("Login.html");
		}else {
//			pw.println("login successfullyy");
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/first","root","root@123");
				PreparedStatement ps=con.prepareStatement("SELECT * FROM pharmacyuser WHERE name=? AND password=?");
				ps.setString(1, username);
				ps.setString(2, password);
				ResultSet rs=ps.executeQuery();
				if(rs.next()) {
//					pw.println("Login Successfully...");
					response.sendRedirect("ViewMedicines2Servlet");
					
				}else {
					response.sendRedirect("Login.html");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
