package booking;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Signin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<body bgcolor=\"lightblue\">");
		out.println("<form action=\"/ConferenceRoomBooking/login/validate\" method=\"POST\" >");
		out.println("<b><h1 align=center>LOGIN</h1></b><br/>");
		out.println("<h4 align=center>Employee_id <input type=\"text\" name=\"id\"/></h4>");
		out.println("<h4 align=center>Password <input type=\"text\" name=\"password\"/></h4><br/>");
		out.println("<h5 align=center><input type=\"submit\" value=\"Signin\"/></h5> ");
		out.println("</form>");
		out.println("</body");
		out.println("</html>");

	}

}
