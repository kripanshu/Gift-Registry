package wpl.spring.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Servlet implementation class TestDb
 */
@WebServlet("/TestDb")
public class TestDb extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			// setup connection variables
			String user = "mital";
			String pass = "mital";
			
			String jdbcUrl = "jdbc:mysql://localhost:3306/wplproject?useSSL=false";
			String driver = "com.mysql.jdbc.Driver";
			
			// get connection to database
			try {
				PrintWriter out = response.getWriter();
				
				out.println("Connecting to database: " + jdbcUrl);
				
				Class.forName(driver);
				
				Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
				
				out.println("SUCCESS!!!");
				
				myConn.close();
				
			}
			catch (Exception exc) {
				exc.printStackTrace();
				throw new ServletException(exc);
			}
		

	}

}
