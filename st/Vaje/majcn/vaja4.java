import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class vaja4
{
	public static void main(String[] args)
	{
		try
		{
			//ustvarimo nov dokument
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			//ustvarimo korenski element
			Element koren= doc.createElement("vpisani");
			//ga dodamo dokumentu
			doc.appendChild(koren);

			//ustvarimo element avto
			Element student=doc.createElement("student");

			//ustvarimo podelemente elementa avto
			//in nastavimo pripadajoce vrednosti
			Element ime=doc.createElement("ime");
			ime.appendChild(doc.createTextNode("Janez Novak"));
			student.appendChild(ime);

			Element ulica=doc.createElement("ulica");
			ulica.appendChild(doc.createTextNode("Trzaska 25"));
			student.appendChild(ulica);

			Element posta=doc.createElement("posta");
			posta.appendChild(doc.createTextNode("1000"));
			student.appendChild(posta);

			Element email=doc.createElement("email");
			email.appendChild(doc.createTextNode("janez.novak@student.uni-lj.si"));
			student.appendChild(email);

			email=doc.createElement("email");
			email.appendChild(doc.createTextNode("janez.novak@email.si"));
			student.appendChild(email);

			Element teza=doc.createElement("teza");
			teza.appendChild(doc.createTextNode("50"));
			teza.setAttribute("enota", "kg");
			student.appendChild(teza);

			Element vpisna=doc.createElement("vpisna");
			vpisna.appendChild(doc.createTextNode("63123123"));
			student.appendChild(vpisna);

			koren.appendChild(student);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result =  new StreamResult(new File("rezultat.xml"));
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "vaja4.dtd");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);

		//morebitne napake izpišemo
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
