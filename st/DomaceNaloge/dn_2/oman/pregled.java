/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sun.misc.BASE64Encoder;
import javax.xml.transform.Transformer;




/**
 *
 * @author Marko Oman
 */
@WebServlet(name="pregled", urlPatterns={"/pregled"})
public class pregled extends HttpServlet {
    HttpSession seja;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //------------------------LOGIN-----------------------------------------------
     protected void izpisiLogin(PrintWriter out){
            out.println("<div id='podatki'>");
            out.println("<form method='POST'>");
            out.println("<br>Uporabniško ime:<br/><input type='text' name='upoime'><br/>");
            out.println("Geslo:<br/><input type='password' name='geslo'><br/>");
            out.println("<br/><br/><input type='submit'>");
            out.println("</form>");
            out.println("</div>");

    }
     //------------------------MENU-----------------------------------------------
    protected void izpisiMenu(PrintWriter o){
        o.println("<a href='?page=pregled'>Pregled Tecajev</a><br></a><a href='?page=prijava'>Prijava<br>"
                + "<a href='?page=dodaj'>Dodaj Tecaj<br><a href='?page=odjavi'>Odjavi</a><br><a href='?page=izvoz' TARGET='_blank'>Izvoz</a>");
    }
    //------------------------DODAJ-----------------------------------------------
    protected void izpisiDodaj(PrintWriter out){
        out.println("<div id='Glavna'>");
        out.println("<h2>Dodajanje novega tecaja</h2>");
        out.println("<div id='Napis'>");
        out.println("<div id='Fonti'>Tema:<br></div>");
        out.println("<div id ='Fonti'>Organizator:<br></div>");
        out.println("<div id ='Fonti'>Izvajalec:<br></div>");
        out.println("<div id ='Fonti'>Prostor:<br></div>");
        out.println("<div id ='Fonti'>Število ur:<br></div>");
        out.println("<div id ='Fonti'>Termin:<br></div>");
        out.println("<div id ='Fonti'>Cena:<br></div>");
        out.println("</div>");
       
        
        out.println("<div id='Text'>");
        out.println("<form method='POST'>");
        out.println("<input type='text' name='naziv'><br>");
        out.println("<input type='text' name='organizator'><br>");
        out.println("<input type='text' name='izvajalec'><br>");
        out.println("<input type='text' name='prostor'><br>");
        out.println("<input type='text' name='stUr'><br>");
        out.println("<input type='text' name='termin'><br>");
        out.println("<input type='text' name='cena'><br>");
        out.println("<br/><input type='submit' value='Dodaj tečaj'>");
        out.println("</form>");
        out.println("</div></div>");
    }
    //------------------------PREGLED-----------------------------------------------
    protected void izpisiPregled(PrintWriter out, HttpServletRequest request){
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con= DriverManager.getConnection("jdbc:derby://localhost:1527/Tecaj", "admin", "geslo");
            Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ADMIN.TECAJI");
                //dokler imamo vrstice v rezultatu
                out.println("<div id='PregledTecajev'>");
                out.println("<H2>Pregled tečajev</H2>");
                out.println("Za prijavo in podrobnosti klnikni na naziv tecaj<p>");
                out.println("<table border=\"1\" width='500'>");
                out.println("<tr><td align='center'>Tema</td><td align='center'>Organizator</td></tr>");
                while(rs.next()){
                   String id=rs.getObject(1).toString();
                   String tema=rs.getObject(2).toString();
                   String organizator=rs.getObject(3).toString();

                   
                    out.println("<tr>");
                    out.println("<td align='center'><a href='pregled?id="+id+"'>"+tema+"</a></td>");
                    out.println("<td align='center'><p>"+organizator+"</p></td>");
                    out.println("</tr>");

                }
                out.println("</table>");
                out.println("</div>");


        }catch (Exception ex) {
            out.println(ex.toString());
        }
        finally {
            out.close();
        }
    }
    //------------------------PREGLED PODROBNOSTI-----------------------------------------------
    protected void izpisiPregledPodrobnosti(PrintWriter out, HttpServletRequest request, boolean uspesno){
        try{
            
        String id = request.getParameter("id");
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con= DriverManager.getConnection("jdbc:derby://localhost:1527/Tecaj", "admin", "geslo");
            Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM ADMIN.TECAJI WHERE ID_TECAJ="+id);
                while(rs.next()){
                      String tema = rs.getString("TEMA");
                      String organizator = rs.getString("ORGANIZATOR");
                      String izvajalec = rs.getString("IZVAJALEC");
                      String prostor = rs.getString("PROSTOR");
                      String stUr = rs.getString("STEVILO_UR");
                      String termin = rs.getString("TERMIN");
                      String cena = rs.getString("CENA");
                      out.println("<div id='PregledTecajev'>");
                      out.println("<H2>"+tema+"</H2>");
                      if(uspesno)
                          out.println("<p>Prijava na tecaj je bila uspesna</p>");
                      else
                          out.println("<p><br><p>");

                      out.println("<a href='pregled'>Nazaj na pregled</a><br><br>");
                      out.println("<table border = \"1\" width='400' align='center'>");
                      out.println("<tr><td align='center'><i>Organizator</i></td><td align='center'>"+organizator+"</td></tr>");
                      out.println("<tr><td align='center'><i>Izvajalec</i></td><td align='center'>"+izvajalec+"</td></tr>");
                      out.println("<tr><td align='center'><i>Prostor</i></td><td align='center'>"+prostor+"</td></tr>");
                      out.println("<tr><td align='center'><i>Število ur</i></td><td align='center'>"+stUr+"</td></tr>");
                      out.println("<tr><td align='center'><i>Termin</i></td><td align='center'>"+termin+"</td></tr>");
                      out.println("<tr><td align='center'><i>Cena</i></td><td align='center'>"+cena+"</td></tr>");
                      out.println("</table>");
                      out.println("</div>");
                  }
                      out.println("<div id='PregledTecajevPrijava'>");
                      out.println("<br><b>Obrazec za prijavo:</b><br><br>");

                      out.println("<div id ='Napis'>");
                      out.println("<div id ='Fonti'>Ime:</div><br>");
                      out.println("<div id ='Fonti'>Priimek:</div><br>");
                      out.println("<div id ='Fonti'>Email:</div><br>");
                      out.println("<form method='POST'>");
                      out.println("<br/><input type='submit' value='Prijavi se'>");
                      out.println("</div>");
                      out.println("<div id ='Text'>");
                      
                      out.println("<input type='text' name='ime'><br>");
                      out.println("<input type='text' name='priimek'><br>");
                      out.println("<input type='text' name='email'><br>");
                      
                      out.println("</form>");
                      out.println("</div>");
                      
                      out.println("</div>");
                       
            }catch (Exception ex) {
            out.println(ex.toString());
        }
        finally {
            out.close();
        }
    }
//------------------------KODIRANJE-----------------------------------------------
    protected String KodirajGeslo(String g){
           MessageDigest md = null;
            try            {
              md = MessageDigest.getInstance("SHA");
            }catch(NoSuchAlgorithmException e){
             e.getMessage();
            }

            try{
              md.update(g.getBytes("UTF-8"));
            }
            catch(UnsupportedEncodingException e){
              e.getMessage();
            }
            
            byte raw[] = md.digest();
            String hash = (new BASE64Encoder()).encode(raw);
            return hash;
    }
    //------------------------XML-----------------------------------------------
    protected void XML(HttpServletResponse response){
        try{
		DocumentBuilderFactory dc = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dc.newDocumentBuilder();
		Document doc =  db.newDocument();
                response.setContentType("text/xml;charset=UTF-8");
                
                Element koren = doc.createElement("Tecaji");
                doc.appendChild(koren);
       
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection con= DriverManager.getConnection("jdbc:derby://localhost:1527/Tecaj", "admin", "geslo");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM ADMIN.TECAJI");
               
                while(rs.next()){
                   Element tecaj = doc.createElement("tecaj");

                   Element id = doc.createElement("id");
                   id.appendChild(doc.createTextNode(rs.getObject("ID_TECAJ").toString()));
                   tecaj.appendChild(id);
                   
                   Element tema=doc.createElement("tema");
                   tema.appendChild(doc.createTextNode(rs.getObject("TEMA").toString()));
                   tecaj.appendChild(tema);

                   Element organizator=doc.createElement("organizator");
                   organizator.appendChild(doc.createTextNode(rs.getObject("ORGANIZATOR").toString()));
                   tecaj.appendChild(organizator);

                   Element izvajalec=doc.createElement("izvajalec");
                   izvajalec.appendChild(doc.createTextNode(rs.getObject("IZVAJALEC").toString()));
                   tecaj.appendChild(izvajalec);

                   Element prostor=doc.createElement("prostor");
                   prostor.appendChild(doc.createTextNode(rs.getObject("PROSTOR").toString()));
                   tecaj.appendChild(prostor);

                   Element stUr=doc.createElement("stUr");
                   stUr.appendChild(doc.createTextNode(rs.getObject("STEVILO_UR").toString()));
                   tecaj.appendChild(stUr);

                   Element termin=doc.createElement("termin");
                   termin.appendChild(doc.createTextNode(rs.getObject("TERMIN").toString()));
                   tecaj.appendChild(termin);

                   Element cena=doc.createElement("cena");
                   cena.appendChild(doc.createTextNode(rs.getObject("CENA").toString()));
                   tecaj.appendChild(cena);
                   
                   koren.appendChild(tecaj);
                }
                
                PrintWriter out = response.getWriter();

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                DOMSource source = new DOMSource(doc);
                StreamResult result =  new StreamResult(out);
              
                String rez="dtdfile.dtd";
                String pot=getServletContext().getRealPath(rez);

                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, pot);
                transformer.transform(source, result);                

		}catch(Exception e){
			System.out.println("Napaka:"+e);
		}
    }

   
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
        PrintWriter out = response.getWriter();
        try {
            //Ce je page=izvoz ne pise nic html ampak odpre novo okno in izpise xml
            ////---------------------------------IZVOZ--------------------------------------------------------////
            if(request.getParameter("page")!=null && request.getParameter("page").equals("izvoz")){
                  
                  XML(response);
                  
            }else{
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
            out.println("<title>Pregled Tecajev</title>");
            out.println("<link rel='stylesheet' type='text/css' href='"+request.getContextPath() +"/style.css'>");
            out.println("</head>");
            out.println("<body>");
            
            izpisiMenu(out);

                if(request.getParameter("page")!=null){
                    ////---------------------------------PRIJAVA--------------------------------------------------------////
                     if(request.getParameter("page").equals("prijava")){
                       if(request.getSession(false)==null){
                             izpisiLogin(out);
                       }else{
                             out.println("<br>Ste ze prijavljeni:");
                             out.println("<a href='?page=odjavi'>Odjava</a><br>");
                       }

                     }
                     ////---------------------------------ODJAVI--------------------------------------------------------////
                      if(request.getParameter("page").equals("odjavi")){
                            izpisiLogin(out);
                            seja = request.getSession();
                            seja.invalidate();

                      }
                     ////---------------------------------PREGLEDI--------------------------------------------------------////
                    
                     if(request.getParameter("page").equals("pregled")){
                        izpisiPregled(out,request);
                        
                     }
                    
                     ////---------------------------------DODAJ--------------------------------------------------------////
                      if(request.getParameter("page").equals("dodaj")){
                            if(request.getSession(false)!=null){
                                izpisiDodaj(out);
                            }else{
                                out.println("<br><br>Najprej se morate prijaviti. <a href='pregled?page=prijava'>Prijava</a>");
                                
                            }
                      }
                     

                }else{
                    if(request.getParameter("id")!=null && request.getParameter("id")!=null){
                        izpisiPregledPodrobnosti(out, request, false);
                    }else
                        izpisiPregled(out,request);

                }

            }
        }  catch (Exception ex) {
            out.println(ex.toString());
        }
        finally {
            out.close();
        }
         out.println("</body>");
         out.println("</html>");
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
        processRequest(request, response);
        PrintWriter out = response.getWriter();
            try {
           
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Vpis</title>");
            out.println("<link rel='stylesheet' type='text/css' href='"+request.getContextPath() +"/style.css'>");
            out.println("</head>");
            out.println("<body>");
            izpisiMenu(out);

            ////DODAJANJE NOVEGA TECAJA
            if(request.getParameter("page")!=null && request.getParameter("page").equals("dodaj") && request.getSession(false)!=null){
                          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                          Connection con= DriverManager.getConnection("jdbc:derby://localhost:1527/Tecaj", "admin", "geslo");
                          Statement stmt = con.createStatement();

                          
                          String tema = request.getParameter("naziv");
                          String organizator = request.getParameter("organizator");
                          String izvajalec = request.getParameter("izvajalec");
                          String prostor = request.getParameter("prostor");
                          String stUr = request.getParameter("stUr");
                          String termin = request.getParameter("termin");
                          String cena = request.getParameter("cena");
                        
                          

                         stmt.executeUpdate("INSERT INTO ADMIN.TECAJI"
                         + " (TEMA,ORGANIZATOR,IZVAJALEC,PROSTOR,STEVILO_UR,TERMIN,CENA)"
                         + "VALUES ('"+tema+"','"+organizator+"','"+izvajalec+"','"+prostor+"',"+stUr+",'"+termin+"',"+cena+")");

                          System.out.println("DODANO");
                           ResultSet rs = stmt.executeQuery("SELECT * FROM ADMIN.TECAJI");
                           out.println("<br><br>Tečaj je uspešno dodan "+"<a href='pregled?page=dodaj'>Nazaj</a>");
                     
            }

            //////////////////////////////////////
             if(request.getParameter("page")==null && request.getParameter("id")!=null){
                //vpis v bazo
                 if(request.getParameter("ime")!=null && request.getParameter("ime")!="" &&
                        request.getParameter("priimek")!=null && request.getParameter("priimek")!=""
                        && request.getParameter("email")!=null && request.getParameter("email")!=""){
                         

                          Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                          Connection con= DriverManager.getConnection("jdbc:derby://localhost:1527/Tecaj", "admin", "geslo");
                          Statement stmt = con.createStatement();

                          String ime=request.getParameter("ime");
                          String priimek=request.getParameter("priimek");
                          String email=request.getParameter("email");
                          int id = Integer.parseInt(request.getParameter("id"));
                          
                         stmt.executeUpdate("INSERT INTO ADMIN.PRIJAVE"
        + " (ID_TECAJ,IME,PRIIMEK,EMAIL)"
        + " VALUES ("+id+", '"+ime+"', '"+priimek+"', '"+email+"')");
                          
                           ResultSet rs = stmt.executeQuery("SELECT * FROM ADMIN.PRIJAVE");
                           izpisiPregledPodrobnosti(out, request, true);
                 }else
                     izpisiPregledPodrobnosti(out,request,false);
                 
            }else{

            ////NA STRANI PRIJAVA///
            if(request.getParameter("page")!=null && (request.getParameter("page").equals("prijava") || request.getParameter("page").equals("odjavi"))){
           if(request.getParameter("upoime")!=null && request.getParameter("geslo")!=null){

           
                     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                      Connection con= DriverManager.getConnection("jdbc:derby://localhost:1527/Tecaj", "admin", "geslo");
                      Statement stmt = con.createStatement();
                      ResultSet rs = stmt.executeQuery("SELECT * FROM UPORABNIKI");
                      String upo = request.getParameter("upoime");
                      //kodirano geslo
                      String ges = KodirajGeslo(request.getParameter("geslo"));
                      boolean uspesno = false;
                while(rs.next()){
                      String upoime = rs.getString("UPOIME");
                      String geslo = rs.getString("GESLO");
                      
                      if(upo.equals(upoime) && ges.equals(geslo)){
                           uspesno = true;
                           break;
                      }
               }

               if(!uspesno)
                   out.println("<br>Napacno uporabnisko ime ali geslo!<br> <a href='?page=prijava'>Poizkusi ponovno</a>");
               else{
                          out.println("<br><br>Uspesno ste se vpisali. ");
                           out.println("<a href='pregled'>Domov</a>");
                           request.getSession();
               }
                     
                }
                    

                }
              }

        }
         catch (Exception ex) {
            out.println(ex.toString());
        }
        finally {
            out.close();
        }
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>



   
}
