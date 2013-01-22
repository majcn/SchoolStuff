/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * REST Web Service
 *
 * @author LRA
 */

@Path("vaja8")
public class vaja8 {
    @Context
    private UriInfo context;

    /** Creates a new instance of vaja8 */
    public vaja8() {
    }


    @GET
    @Produces("application/xml")
    public String vrniVse() throws Exception {
         //povezava z bazo
         Statement stmt=poveziBazo();    

         //izvedemo query
         ResultSet rs = stmt.executeQuery("SELECT STEVILKA FROM POSTE");

         //ustvarimo prazen dokument
         Document doc=vrniPrazenDokument();

         //ustvarimo koren
         Element koren= doc.createElement("poste");
         doc.appendChild(koren);


         Element stevilka;

         //v zanki ustvarjamo elemente <posta>
         while(rs.next()){
             stevilka=doc.createElement("posta");
             stevilka.appendChild(doc.createTextNode(rs.getString("STEVILKA")));
             koren.appendChild(stevilka);
         }

         //transformiramo v String
         String rezultat=transformiraj(doc);

         //vrnemo XML v String obliki
         return rezultat;
    }

    @GET
    @Path("{stevilka}")
    @Produces("application/xml")
    public String vrniIme( @PathParam("stevilka") int postnaStevilka) throws Exception{

         //povežemo se z bazo
         Statement stmt=poveziBazo();    

         //izvedemo query
         ResultSet rs = stmt.executeQuery("SELECT STEVILKA,NAZIV FROM POSTE WHERE STEVILKA="+postnaStevilka);

         //ustvarimo prazen dokument

         Document doc=vrniPrazenDokument();
         

         //ustvarimo koren
         Element koren= doc.createElement("posta");
         doc.appendChild(koren);


         Element stevilka;

         //v zanki ustvarjamo elemente <posta>
         while(rs.next()){
             stevilka=doc.createElement("stevilka");
             stevilka.appendChild(doc.createTextNode(rs.getString("STEVILKA")));
             koren.appendChild(stevilka);
             
             stevilka=doc.createElement("naziv");
             stevilka.appendChild(doc.createTextNode(rs.getString("NAZIV")));
             koren.appendChild(stevilka);
         }

         //transformiramo v String
         String rezultat=transformiraj(doc);

         //vrnemo XML v String obliki
         return rezultat;
    }
    
    @POST
    public void dodajPosto(String content) throws Exception {
        Statement stmt=poveziBazo();

        Document doc= razcleniBody(content);


        NodeList nazivNode = doc.getElementsByTagName("naziv");
        NodeList stevilkaNode = doc.getElementsByTagName("stevilka");

        Element naziv=(Element)nazivNode.item(0);
        Element stevilka=(Element)stevilkaNode.item(0);

        String nazivVrednost=naziv.getFirstChild().getNodeValue();
        String stevilkaVrednost=stevilka.getFirstChild().getNodeValue();
        stmt.executeUpdate("INSERT INTO POSTE (STEVILKA, NAZIV)"
                + " VALUES ("+stevilkaVrednost+", '"+nazivVrednost+"')");
        
    }
    
    @PUT
    public void spremeniPosto(String content, @QueryParam("ime") String ime) throws Exception {
    	 Statement stmt=poveziBazo();

         Document doc= razcleniBody(content);
         
         NodeList stevilkaNode = doc.getElementsByTagName("stevilka");
         
         Element stevilka=(Element)stevilkaNode.item(0);
         String stevilkaVrednost=stevilka.getFirstChild().getNodeValue();
         
         stmt.executeUpdate("UPDATE POSTE SET STEVILKA = "+stevilkaVrednost+" WHERE NAZIV = '"+ime+"'");
    }
    
    @DELETE
    @Path("{stevilka}")
    public void izbrisiPosto(@PathParam("stevilka") int postnaStevilka) throws Exception
    {
    	Statement stmt=poveziBazo();
    	stmt.executeUpdate("DELETE FROM POSTE WHERE STEVILKA = "+postnaStevilka);
    }




    private String transformiraj(Document doc) throws TransformerConfigurationException, TransformerException{
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
        return writer.toString();
    }
    private Statement poveziBazo() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
         Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/STvaje", "STvaje", "STvaje");
         return  con.createStatement();
    }
    private Document vrniPrazenDokument() throws ParserConfigurationException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         DocumentBuilder db = dbf.newDocumentBuilder();
         return db.newDocument();
    }
    private Document razcleniBody(String content) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        //iz objekta tipa String naredimo objekt Document
        InputSource is=new InputSource(new StringReader(content));
        return db.parse(is);
    }


}
