package booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Rooms extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String query;
		response.setContentType("text/html");
		out.println("<h1 align=center>VIEW ROOM BOOKINGS</h1>");
		out.println("<html><body>");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3305/booking", "root", "root");
			Statement stmt = (Statement) con.createStatement(); 
			query = "SELECT * FROM room";
			stmt.executeQuery(query);
			ResultSet rs = (ResultSet) stmt.getResultSet();
			out.println("<html>");
			out.println("<body bgcolor=\"lightblue\">");
			out.println("<table align=center border=1 width=50% height=25%>");
			out.println("<tr><th>Room_Name</th><th>Room_id</th><th>Book_Now</th><tr>");

			while (rs.next()) {

				try {

					String roomname = rs.getString("Room_Name");
					String roomid = rs.getString("Room_id");
					String booknow = rs.getString("Book_Now");
					if (booknow.equals("Available")) {
						out.println("<tr><td>"+roomname+"</td><td>"+roomid+"</td><td><a href=http://localhost:8080/ConferenceRoomBooking/book>BOOK_NOW</a></td></tr>");
					} else {
						out.println("<tr><td>" + roomname + "</td><td>"
								+ roomid + "</td><td>ALREADY_BOOKED</td></tr>");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
