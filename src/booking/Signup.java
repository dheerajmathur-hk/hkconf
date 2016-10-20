package booking;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.ResultSet;

public class Signup extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<body bgcolor=\"lightblue\">");
		out.println("<form action=\"/ConferenceRoomBooking/Signup\" method=\"POST\" >");
		out.println("<b><h1 align=center>SIGNUP</h1></b><br/>");
		out.println("<h4 align=center>Employee_Id<input type=\"text\" name=\"id\"/></h4>");
		out.println("<h4 align=center>Email_Id<input type=\"text\" name=\"email\"/></h4>");
		out.println("<h4 align=center>Password <input type=\"text\" name=\"password\"/></h4><br/>");
		out.println("<h5 align=center><input type=\"submit\" value=\"Send OTP\"/></h5> ");
		out.println("</form>");
		out.println("</body");
		out.println("</html>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		String email = (String) request.getParameter("email");
		String pass = (String) request.getParameter("password");
		String query;
		boolean login = false;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3305/booking", "root", "root");
			Statement stmt = (Statement) con.createStatement();

			query = "select * from booking where Email_id='" + email + "'";
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
		PrintWriter out = response.getWriter();
		if (login == true) {
			out.println("The given email id is already registered with us!");
		} else {

			final String username = "nipun.aggarwal@brightlifecare.com";
			final String password = "nipun1601";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username,
									password);
						}
					});

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(
						"nipun.aggarwal@brightlifecare.com"));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(email));
				message.setSubject("OTP SENT");
				Random random = new Random();
				int randomInteger = random.nextInt();
				System.out.println(randomInteger);
				message.setText("OTP: " + randomInteger);

				Transport.send(message);

				out.println("<html>");
				out.println("<body bgcolor=\"lightblue\">");
				out.println("<form action=\"/ConferenceRoomBooking/Add\" method=\"POST\" >");
				out.println("<b><h4 align=center>Please enter the password sent to "
						+ email + "</h4></b><br/>");
				out.println("<h4 align=center>OTP<input type=\"text\" name=\"otp\"/></h4>");
				out.println("<h5 align=center><input type=\"submit\" name=\"signup\" value=\"Signup\"/></h5> ");
				out.println("</form>");
				out.println("</body");
				out.println("</html>");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}

		}
	}
}