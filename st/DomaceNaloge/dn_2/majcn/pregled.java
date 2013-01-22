import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class pregled
 */
@WebServlet(name="pregled", urlPatterns={"/pregled"})
public class pregled extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String title = "Pregled teèajev";
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response, boolean novaPrijava) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try
		{
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>"+title+"</title>");
			out.println("</head>");
			out.println("<body>");
			
			if(request.getParameter("id")==null)
			{
				Connection con = setDatabase();
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT ID_TECAJ, TEMA, ORGANIZATOR FROM TECAJI");
				
				out.println("<h1>Pregled teèajev</h1>");
				out.println("<div>Za prijavo in podrobnosti klikni na naziv teèaj</div>");
				
				
				out.println("<table border='1'");
				while(rs.next())
				{
					String id = rs.getString("ID_TECAJ");
					String tema = rs.getString("TEMA");
					String organizator = rs.getString("ORGANIZATOR");
					out.println("<tr>");
					out.format("<td><a href='?id=%s'>%s</a></td>\n", id, tema);
					out.format("<td>%s</td>\n", organizator);
					out.println("</tr>");
				}
				out.println("</table>");
			}
			else
			{
				String id = request.getParameter("id");
				Connection con = setDatabase();
				Statement stmt = con.createStatement();
				
				if(novaPrijava)
				{
					String ime=request.getParameter("ime");
					String priimek=request.getParameter("priimek");
					String email=request.getParameter("email");
					stmt.executeUpdate(String.format("INSERT INTO PRIJAVE (ID_TECAJ, IME, PRIIMEK, EMAIL) VALUES ('%s', '%s', '%s', '%s')", id, ime, priimek, email));
				}
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM TECAJI WHERE ID_TECAJ = "+id);
				
				rs.next();
				out.format("<h1>%s</h1>\n", rs.getString("TEMA"));
				if(novaPrijava)
					out.println("<div>Prijava na teèaj je bila uspešna</div>");
				out.println("<div><a href='pregled'>Nazaj na pregled</a></div>");
				
				out.println("<table border='1'");
				for(int i=3; i<=rs.getMetaData().getColumnCount(); i++)
				{
					out.println("<tr>");
					out.format("<td>%s</td>\n", rs.getMetaData().getColumnLabel(i).replaceAll("_", " ")); 	//v bazi so ponavadi _ namesto " "
					out.format("<td>%s</td>\n", rs.getString(i));
					out.println("</tr>");
				}
				out.println("</table>");
				
				out.println("<br/><br/>");
				
				out.println("<form method='POST'>");
				out.println("Obrazec za prijavo:<br/>");
				out.println("<table border='0'>");
				out.println("<tr>");
				out.println("<td>Ime:</td>");
				out.println("<td><input type='text' name='ime'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Priimek:</td>");
				out.println("<td><input type='text' name='priimek'></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>E-Mail:</td>");
				out.println("<td><input type='text' name='email'></td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("<input type='submit' value='Prijavi se'>");
				out.println("</form>");
				
				
				
			}
			
			out.println("</body>");
			out.println("</html>");
		}
		catch (Exception ex) {
			out.println(ex.toString());
		}
		finally
		{
			out.close();
		}
		
	}
	
	private Connection setDatabase() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/STvaje", "STvaje", "STvaje");
		return con;
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response, false);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response, true);
	}

}
