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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
/**
 * Servlet implementation class izvoz
 */
@WebServlet(name="izvoz", urlPatterns={"/izvoz"})
public class izvoz extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   
	protected void processRequest(HttpServletRequest request, HttpServletResponse response, boolean novTecaj) throws ServletException, IOException {
		response.setContentType("text/xml;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try
		{
			//ustvarimo nov dokument
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			//ustvarimo korenski element
			Element koren= doc.createElement("tecaji");
			//ga dodamo dokumentu
			doc.appendChild(koren);
			
			Connection con = setDatabase();
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM TECAJI");
			while(rs.next())
			{
				Element tecaj=doc.createElement("tecaj");
				for(int i=1; i<=rs.getMetaData().getColumnCount(); i++)
				{
					Element el = doc.createElement(rs.getMetaData().getColumnLabel(i));
					el.appendChild(doc.createTextNode(rs.getString(i)));
					tecaj.appendChild(el);
				}
				koren.appendChild(tecaj);
			}
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult streamResult = new StreamResult(out);
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, request.getContextPath() +"/xml.dtd");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, streamResult);
			

		//morebitne napake izpišemo
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
