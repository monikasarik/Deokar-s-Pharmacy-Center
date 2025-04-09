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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String contact=request.getParameter("contact");
		String address=request.getParameter("address");
		String password =request.getParameter("password");
//		pw.println(username + password+address+email+contact);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/first","root","root@123");
			PreparedStatement ps=con.prepareStatement("INSERT INTO pharmacyuser(name,email,contact,address,password) VALUES(?,?,?,?,?)");
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, contact);
			ps.setString(4, address);
			ps.setString(5, password);
			int no=ps.executeUpdate();
			if(no>0) {
//				pw.println("Hello "+username);
				response.sendRedirect("Login.html");
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}

