package booking;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Welcome extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<body bgcolor=\"lightblue\">");
		out.println("<b><h1 align=center>WELCOME TO CONFERENCE ROOM BOOKING SYSTEM</h1></b><br/>");
		out.println("For Existing Customers");
		out.println("<tr><td><a href=http://localhost:8020/ConferenceRoomBooking/Signin>SIGNIN</a></td></tr>");
		out.println("</br>");
		out.println("</br>For New Customers");
		out.println("<tr><td><a href=http://localhost:8020/ConferenceRoomBooking/Signup>SIGNUP</a></td></tr>");
		out.println("</form>");
		out.println("</body");
		out.println("</html>");
		out.println("<form action=\"/ConferenceRoomBooking/Signup\" method=\"POST\" >");

	}
}