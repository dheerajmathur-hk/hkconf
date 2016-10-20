package booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.ResultSet;

public class Add extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String query;
		PrintWriter out = response.getWriter();
		String id = (String) request.getParameter("id");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		out.println(id);
		out.println(email);
		out.println(password);
		response.setContentType("text/html");

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3305/booking", "root", "root");
			Statement stmt = (Statement) con.createStatement();

			query = "insert into booking values ('" + id + "','" + password
					+ "','"+email+"')";
			stmt.execute(query);
			ResultSet rs = (ResultSet) stmt.getResultSet();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("<html>");
		out.println("<body bgcolor=\"lightblue\">");
		out.println("You have been successfully signed up");
		out.println("");
		out.println("<a href=http://localhost:8020/ConferenceRoomBooking/Rooms>VIEW ROOM BOOKINGS</a><br/><br/>");
		out.println("</form>");
		out.println("</body");
		out.println("</html>");
	}
}
