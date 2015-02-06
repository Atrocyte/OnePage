package xml2HtmlCss;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.*;

public class Xml2Html {
	public static void main(String[] args) {
		try {

			TransformerFactory tFactory = TransformerFactory.newInstance();
			File xslPath = new File(
					"D:\\Workspace\\OnePage\\src\\main\\resources\\Style1P.xsl");
			File xmlPath = new File(
					"D:\\Workspace\\OnePage\\src\\main\\resources\\0204OnePage.xml");
			// File htmlPath = new File(
			// "D:\\Workspace\\OnePage\\src\\main\\resources\\Generated1P.html");
			File htmlPath = new File(
					"D:\\Workspace\\OnePage\\src\\main\\resources\\HTML\\HTML_Clean_Minimal_One_Page_CV_v1.0\\Generated1P.html");

			Transformer transformer = tFactory
					.newTransformer(new javax.xml.transform.stream.StreamSource(xslPath));
			
			transformer.transform(new javax.xml.transform.stream.StreamSource(xmlPath), new javax.xml.transform.stream.StreamResult(
					new FileOutputStream(htmlPath)));
			
			System.out.printf("Generating Ordina OnePage CV of: " + nameOfCurrentEmployee(xmlPath));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String nameOfCurrentEmployee(File xmlPath)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlPath);
		 
		NodeList nList = doc.getElementsByTagName("Name");
		
		Node node = nList.item(0);
		return node.getTextContent();
	}
}