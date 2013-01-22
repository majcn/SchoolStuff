import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import sun.misc.BASE64Encoder;
/**
 * Servlet implementation class dodaj
 */
@WebServlet(name="dodaj", urlPatterns={"/dodaj"})
public class dodaj extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String title = "Dodajanje te�ajev";
	
	protected String preveriGeslo(String g){
		//uporaba kodirne funkcije
		//če si pozabil kaj so kodirne funkcije
		//ponovno preveri zapiske za RK
		MessageDigest md = null;
		try
		{
		  md = MessageDigest.getInstance("SHA"); 
		}
		catch(NoSuchAlgorithmException e)
		{
		 e.getMessage();
		}
		
		try
		{
		  //objektu določimo polje števil, ki jih dobimo iz stringa
		  md.update(g.getBytes("UTF-8")); 
		}
		catch(UnsupportedEncodingException e)
		{
		  e.getMessage();
		}
		//izvedemo hash funkcijo
		byte raw[] = md.digest();
		//števila v polju prekodiramo v niz števil v 64-iškem zapisu
		String hash = (new BASE64Encoder()).encode(raw);
		return hash;
}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response, boolean novTecaj) throws ServletException, IOException {
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
			
			Connection con = setDatabase();
			Statement stmt = con.createStatement();
			if(request.getParameter("uime")!=null && request.getParameter("geslo")!=null)
			{
				ResultSet rs = stmt.executeQuery(String.format("SELECT GESLO FROM ADMIN WHERE IME = '%s'", request.getParameter("uime")));
				rs.first();
				String psw = preveriGeslo(request.getParameter("geslo"));
				if(request.getParameter("uime").equals("admin") && psw.equals(rs.getString("GESLO")))
					request.getSession();
			}
			
			if(request.getParameter("logout") != null)
			{
				if(request.getParameter("logout").equals("1"))
					request.getSession().invalidate();
				response.sendRedirect("dodaj");
			}
			else if(request.getSession(false)!=null)
			{
				if(request.getParameter("uporabnik")!=null)
			   	{
			   		Oseba o = new Oseba(request.getParameter("uporabnik"));
					request.getSession().setAttribute("ime", o);
				}
				
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM TECAJI");
				rs.next();
				
				if(novTecaj)
				{
					String dodajTecajSQL = "INSERT INTO TECAJI (";
					for(int i=2; i<rs.getMetaData().getColumnCount(); i++)
					{
						dodajTecajSQL += rs.getMetaData().getColumnLabel(i) + ", ";
					}
					dodajTecajSQL += rs.getMetaData().getColumnLabel(rs.getMetaData().getColumnCount()) + ") VALUES (";
					for(int i=2; i<rs.getMetaData().getColumnCount(); i++)
					{
						dodajTecajSQL += "'" + request.getParameter(rs.getMetaData().getColumnLabel(i)) + "', ";
					}
					dodajTecajSQL += "'" + request.getParameter(rs.getMetaData().getColumnLabel(rs.getMetaData().getColumnCount())) + "');";
					stmt.executeUpdate(dodajTecajSQL);
					out.println("<div>Dodajanje tecaja je bila uspesna</div>");
	
					rs = stmt.executeQuery("SELECT * FROM TECAJI");  //potrebno je na novo odpreti povezavo
					rs.next();
				}
				
				out.println("<h1>Dodajanje novega tecaja</h1>");
				out.println("<form method='POST'>");
				out.println("<table border='0'");
				for(int i=2; i<=rs.getMetaData().getColumnCount(); i++)
				{
					out.println("<tr>");
					out.format("<td>%s</td>\n", rs.getMetaData().getColumnLabel(i).replaceAll("_", " ")); 	//v bazi so ponavadi _ namesto " "
					out.format("<td><input type='text' name='%s'></td>\n", rs.getMetaData().getColumnLabel(i));
					out.println("</tr>");
				}
				out.println("</table>");
				out.println("<input type='submit' value='Dodaj tecaj'>");
				out.println("</form>");
			}
			else
			{
				out.println("<div id='login'><form method='POST'>");
				out.println("Uporabni�ko ime: <br/><br/><input type='text' name='uime'><br/> <br/>");
				out.println("Geslo: <br/><br/><input type='password' name='geslo'><br/> <br/>");
				out.println("<input type='submit' value='Prijavi'>");
				out.println("</form></div>");
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
		if(request.getParameter("uime")==null)
			processRequest(request, response, true);
		else
			processRequest(request, response, false);
	}
	
	class Oseba{
		String ime;
		Oseba(String i){
			this.ime=i;
		}
		String vrniIme(){
			return this.ime;
		}


	}
}