package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * Servlet implementation class student
 */
@WebServlet("/student")
public class student extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public student() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student</title>");
            out.println("<script type='text/javascript' src='"+request.getContextPath()+"/ajax.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form method='POST'>");
            out.println("Vpisna številka: <input type='text' name='vpisna' id='vpisnaStevilka'><br/><br/>");
            out.println("<input type='submit' value='Potrdi POST'>");
            out.println("</form>");
            out.println("<input type='submit' value='Potrdi AJAX' onClick='getStudent()'/>");
            out.println("<br/><br/><div id='vpisna'></div>");
            out.println("<div id='ime_priimek'></div>");
            out.println("<div id='letnik'></div>");
            out.println("<div id='ulica'></div>");
            out.println("<div id='kraj'></div>");
            out.println("<div id='razdalja'></div>");
            out.println("<div id='cas'></div>");
            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception ex)
        {
            out.println(ex.toString());
        }
        finally
        {
            out.close();
        }
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student</title>");
            out.println("</head>");
            out.println("<body>");
            
            String url="http://localhost:8080/seminarska/viri/student/"+request.getParameter("vpisna");
            DefaultHttpClient klient=new DefaultHttpClient();
            HttpResponse odgovor=klient.execute(new HttpGet(url));
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(odgovor.getEntity().getContent());
            
            String vpisna = ((Element)doc.getElementsByTagName("vpisna").item(0)).getFirstChild().getNodeValue();
            String letnik = ((Element)doc.getElementsByTagName("letnik").item(0)).getFirstChild().getNodeValue();
            String ime = ((Element)doc.getElementsByTagName("ime").item(0)).getFirstChild().getNodeValue();
            String priimek = ((Element)doc.getElementsByTagName("priimek").item(0)).getFirstChild().getNodeValue();
            String ulica = ((Element)doc.getElementsByTagName("ulica").item(0)).getFirstChild().getNodeValue();
            String kraj = ((Element)doc.getElementsByTagName("kraj").item(0)).getFirstChild().getNodeValue();
            
            url = String.format("http://maps.googleapis.com/maps/api/distancematrix/xml?origins=%s,%s&destinations=Trzaska+cesta+25,Ljubljana&mode=walking&sensor=false", ulica.replaceAll(" ", "+"), kraj.replaceAll(" ", "+"));
            odgovor=klient.execute(new HttpGet(url));
            doc = db.parse(odgovor.getEntity().getContent());
            
            String distance = ((Element)doc.getElementsByTagName("text").item(1)).getFirstChild().getNodeValue();
            String duration = ((Element)doc.getElementsByTagName("text").item(0)).getFirstChild().getNodeValue();          
            
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>Vpisna številka:</td><td>"+vpisna+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Ime in priimek:</td><td>"+ime+" "+priimek+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Letnik:</td><td>"+letnik+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Ulica:</td><td>"+ulica+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Kraj:</td><td>"+kraj+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Oddaljenost od faksa:</td><td>"+distance+"</td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Èas hoje do faksa:</td><td>"+duration+"</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception ex)
        {
        	out.println(ex.toString());
        }
        finally
        {
            out.close();
        }
	}
}
