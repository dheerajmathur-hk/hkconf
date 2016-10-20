package booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.ResultSet;

public class Book extends HttpServlet {

	private String message;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		response.setContentType("text/html");
		out.println("<html>");
		out.println("<body bgcolor=\"lightblue\">");
		out.println("<form action=\"/ConferenceRoomBooking/book\" method=\"POST\" >");
		out.println("<b><h1 align=center>BOOK ROOM</h1></b><br/>");
		out.print("<h4 align=center>Room_Name <input type=\"text\" name=\"name\"/></h4>");
		out.print("<h4 align=center>Room_id <input type=\"text\" name=\"id\"/></h4>");
		out.println("<h5 align=center><input type=\"submit\" value=\"Book\"/></h5> ");
		out.println("</form>");
		out.println("</body");
		out.println("</html>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String query;
		String name = (String) request.getParameter("name");
		String id = (String) request.getParameter("id");
		// String booknow = (String) request.getParameter("");

		response.setContentType("text/html");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3305/booking", "root", "root");
			Statement stmt = (Statement) con.createStatement();

			query = "UPDATE room set Book_Now='ALREADY_BOOKED' WHERE Room_id='"
					+ id + "'";

			stmt.execute(query);
			ResultSet rs = (ResultSet) stmt.getResultSet();
			out.println("<html>");
			out.println("<body bgcolor=\"lightblue\">");
			out.println("<form action=\"/ConferenceRoomBooking/rooms\" method=\"GET\" >");
			out.println("<h1 align=center>Thanks for Booking the room.Your Room has been booked successfully</h1>");
			out.println("<h5 align=center><input type=\"submit\" value=\"SEE BOOKINGS\"/></h5> ");
			out.println("</html>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}