import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="naloga", urlPatterns={"/naloga"})
public class naloga extends HttpServlet {
   

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Imena - AJAX plain/text</title>");
			out.println("<script src='vaja9.js'></script>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>Vpiši poštno številko in klikni potrdi</h3>");
			out.println("<input type='text' id='niz'/>");
			out.println("<input type='submit' value='Potrdi' onClick='isciPosto'/><br/><br/>");
			out.println("Poštna številka: <div id='stevilka'></div>");
			out.println("Naziv: <div id='naziv'></div>");
			out.println("</body>");
			out.println("</html>");
		} finally { 
			out.close();
		}
	} 


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		processRequest(request, response);
	} 


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		processRequest(request, response);
	}


	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
