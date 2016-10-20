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

public class Validate extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String query;
		boolean login = false;
		PrintWriter out = response.getWriter();
		String id = (String) request.getParameter("id");
		String password = (String) request.getParameter("password");
		response.setContentType("text/html");

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3305/booking", "root", "root");
			Statement stmt = (Statement) con.createStatement();

			query = "select * FROM booking WHERE Employee_Id='" + id
					+ "' AND Password='" + password + "'";
			stmt.executeQuery(query);
			ResultSet rs = (ResultSet) stmt.getResultSet();
			login = rs.first();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.setContentType("text/html");

		if (login == false || id.equalsIgnoreCase("")
				|| password.equalsIgnoreCase("")) {
			out.println("login failed!");
		} else {
			String query1;
			response.setContentType("text/html");
			out.println("<h1 align=center>VIEW ROOM BOOKINGS</h1>");
			out.println("<html><body>");
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3305/booking", "root", "root");
				Statement stmt = (Statement) con.createStatement();

				query1 = "SELECT * FROM room";

				stmt.executeQuery(query1);
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
						if(booknow.equals("Available")){
							out.println("<tr><td>"+roomname+"</td><td>"+roomid+"</td><td><a href=http://localhost:8020/ConferenceRoomBooking/Book>BOOK_NOW</a></td></tr>");

						}else {
						out.println("<tr><td>"+roomname+"</td><td>"+roomid+"</td><td>ALREADY_BOOKED</td></tr>");
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
}
