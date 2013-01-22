import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import org.w3c.dom.*;

public class Vaja4 {
	public static void main(String[] args) {
		try{
		DocumentBuilderFactory dc = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dc.newDocumentBuilder();
		Document doc = db.newDocument();
		
		Element koren = doc.createElement("vpisani");
		doc.appendChild(koren);
		
		Element student = doc.createElement("student");
		
		Element ime = doc.createElement("ime");
		Element posta = doc.createElement("posta");
		Element ulica = doc.createElement("ulica");
		Element email = doc.createElement("email");
		Element teza = doc.createElement("teza");
		Element vpisna = doc.createElement("vpisna");
		
		ime.appendChild(doc.createTextNode("Janez Novak"));
		student.appendChild(ime);
		
		ulica.appendChild(doc.createTextNode("Trzaska 25"));
		student.appendChild(ulica);
		
		posta.appendChild(doc.createTextNode("1000"));
		student.appendChild(posta);
		
		email.appendChild(doc.createTextNode("janez.novak@student.uni-lj.si"));
		student.appendChild(email);
		
		email=doc.createElement("email");
		email.appendChild(doc.createTextNode("janez.novak@email.si"));
		student.appendChild(email);
		
		teza.appendChild(doc.createTextNode("50"));
		teza.setAttribute("enota", "kg");
		student.appendChild(teza);
		
		vpisna.appendChild(doc.createTextNode("63123123"));
		student.appendChild(vpisna);
		
		
		
		
		koren.appendChild(student);
		
		
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result =  new StreamResult(new File("rezultat.xml"));
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "vaja4.dtd");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //da je lepa oblika
		transformer.transform(source, result);
		
		}catch(Exception e){
			System.out.println("Napaka:"+e);
		}
		
	}
}
