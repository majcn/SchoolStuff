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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@WebServlet(name="vaja7", urlPatterns={"/vaja7"})
public class vaja7 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			try {

				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Naloga 7</title>");
				out.println("<link rel='stylesheet' type='text/css' href='"+request.getContextPath() +"/style.css'>");
				out.println("</head>");
				out.println("<body>");
				out.println("<div id='podatki'>");
				out.println("<form method='POST'>");
				out.println("Ime:<br/><input type='text' name='id'><br/>");
				out.println("<br/><br/><input type='submit'>");
				out.println("</form>");
				out.println("</div>");
				out.println("</body>");
				out.println("</html>");
			}
			 catch (Exception ex) {
				out.println(ex.toString());
			}
			finally {
				out.close();
			}
	} 

	/** 
	 * Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			try {

				out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Naloga 7</title>");
				out.println("<link rel='stylesheet' type='text/css' href='"+request.getContextPath() +"/style.css'>");
				out.println("</head>");
				out.println("<body>");
				out.println("ID stranke: "+request.getParameter("id"));
				
				String url="http://localhost:8080/sqlrest/CUSTOMER/"+request.getParameter("id");
				DefaultHttpClient klient=new DefaultHttpClient();
				HttpGet getzahteva=new HttpGet(url);
				HttpResponse odgovor=klient.execute(getzahteva);
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();

				Document doc = db.parse(odgovor.getEntity().getContent());

				NodeList rezultat = doc.getElementsByTagName("FIRSTNAME");
				Element linkNode=(Element)rezultat.item(0);
				String ime=linkNode.getFirstChild().getNodeValue();

				rezultat = doc.getElementsByTagName("LASTNAME");
				linkNode=(Element)rezultat.item(0);
				String priimek=linkNode.getFirstChild().getNodeValue();
				
				rezultat = doc.getElementsByTagName("CITY");
				linkNode=(Element)rezultat.item(0);
				String mesto=linkNode.getFirstChild().getNodeValue();
				
				mesto = mesto.replace(" ","+");
				url="http://www.google.com/ig/api?weather="+mesto;
				
				getzahteva=new HttpGet(url);
				odgovor=klient.execute(getzahteva);

				InputSource temp=new InputSource(odgovor.getEntity().getContent()); temp.setEncoding("ISO-8859-1");
				doc = db.parse(temp);

				rezultat = doc.getElementsByTagName("icon");
				linkNode=(Element)rezultat.item(0);
				String icon = linkNode.getAttribute("data");
				
				rezultat = doc.getElementsByTagName("temp_c");
				linkNode=(Element)rezultat.item(0);
				String temp_c = linkNode.getAttribute("data");
				
				out.println("</br>");
				out.println("</br>");
				out.println(ime+" "+priimek);
				
				out.println("<br><br>");
				out.println("<img src='http://www.google.com"+icon+"'>"+temp_c+"°C");

				
				out.println("</body>");
				out.println("</html>");
			}
			 catch (Exception ex) {
				out.println(ex.toString());
			}
			finally {
				out.close();
			}
	}

	/** 
	 * Returns a short description of the servlet.
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
