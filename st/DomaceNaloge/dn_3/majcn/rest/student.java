package rest;

import java.io.*;
import java.sql.*;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Path("student")
public class student {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;
    
    public student() {
        // TODO Auto-generated constructor stub
    }
    
    @GET
    @Produces("application/xml")
    public String getStudent(@QueryParam("letnik") String letnik) throws Exception
    {
    	 Statement stmt=poveziBazo();
    	 
    	 ResultSet rs = null;
    	 if(letnik==null)
    		 rs = stmt.executeQuery("SELECT VPISNA, LETNIK FROM STUDENT");
    	 else
    		 rs = stmt.executeQuery("SELECT VPISNA, LETNIK FROM STUDENT WHERE LETNIK="+letnik);
    	 
    	 Document doc=vrniPrazenDokument();
    	 Element koren= doc.createElement("vpisani");
         doc.appendChild(koren);
         
         Element stevilka;
         while(rs.next())
         {
             stevilka=doc.createElement("student");
 			 stevilka.setAttribute("letnik", rs.getString("LETNIK"));
             stevilka.appendChild(doc.createTextNode(rs.getString("VPISNA")));
             koren.appendChild(stevilka);
         }
    	 
         return transformiraj(doc);
    }
    
    @GET
    @Path("{vpisna}")
    @Produces("application/xml")
    public String getStudent( @PathParam("vpisna") int vpisnaStevilka) throws Exception
    {
    	Statement stmt=poveziBazo();
    	ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT WHERE VPISNA="+vpisnaStevilka);
    	rs.first();
    	 
    	Document doc=vrniPrazenDokument();
    	Element koren= doc.createElement("student");
        doc.appendChild(koren);
         
        Element element;
        
        element=doc.createElement("vpisna");
        element.appendChild(doc.createTextNode(rs.getString("vpisna")));
        koren.appendChild(element);
        
        element=doc.createElement("letnik");
        element.appendChild(doc.createTextNode(rs.getString("letnik")));
        koren.appendChild(element);
        
        element=doc.createElement("ime");
        element.appendChild(doc.createTextNode(rs.getString("ime")));
        koren.appendChild(element);
        
        element=doc.createElement("priimek");
        element.appendChild(doc.createTextNode(rs.getString("priimek")));
        koren.appendChild(element);
        
        element=doc.createElement("ulica");
        element.appendChild(doc.createTextNode(rs.getString("ulica")));
        koren.appendChild(element);
        
        element=doc.createElement("kraj");
        element.appendChild(doc.createTextNode(rs.getString("kraj")));
        koren.appendChild(element);
    	
    	return transformiraj(doc);
    }
    
    @DELETE
    @Path("{vpisna}")
    public void deleteStudent(@PathParam("vpisna") int vpisnaStevilka) throws Exception
    {
    	Statement stmt=poveziBazo();
    	stmt.executeUpdate("DELETE FROM STUDENT WHERE VPISNA = "+vpisnaStevilka);
    }
    
    @POST
    public void addStudent(String content) throws Exception
    {
   	 	Statement stmt=poveziBazo();

        Document doc= razcleniBody(content);
        
        String vpisna = ((Element)doc.getElementsByTagName("vpisna").item(0)).getFirstChild().getNodeValue();
        String letnik = ((Element)doc.getElementsByTagName("letnik").item(0)).getFirstChild().getNodeValue();
        String ime = ((Element)doc.getElementsByTagName("ime").item(0)).getFirstChild().getNodeValue();
        String priimek = ((Element)doc.getElementsByTagName("priimek").item(0)).getFirstChild().getNodeValue();
        String ulica = ((Element)doc.getElementsByTagName("ulica").item(0)).getFirstChild().getNodeValue();
        String kraj = ((Element)doc.getElementsByTagName("kraj").item(0)).getFirstChild().getNodeValue();
        
        stmt.executeUpdate(String.format("INSERT INTO STUDENT (vpisna, letnik, ime, priimek, ulica, kraj) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", vpisna, letnik, ime, priimek, ulica, kraj));
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