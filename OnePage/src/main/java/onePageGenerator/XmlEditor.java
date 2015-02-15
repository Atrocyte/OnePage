package onePageGenerator;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XmlEditor {
	Document xmlDoc;
	String nodeName;
	String name;
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

	void openFile(File file) {
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			this.xmlDoc = dBuilder.parse(file);
			NodeList nList = xmlDoc.getElementsByTagName("Name");
			this.nodeName = nList.item(0).getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String compareDataWithFilename(File onePageXml) throws Exception {
		this.name = this.stripExtension(onePageXml.getName());
		if (nodeName.equals(name)) {
			return name;
		} else {
			throw new Exception(
					"Bestandsnaam en naam in het CV komen niet overeen.");
		}

	}

	private String stripExtension(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}

	public void injectPhoto(OnePageCV onePage) throws Exception{
		NodeList techNode = xmlDoc.getElementsByTagName("Tech1");
		techNode.item(0).setTextContent(onePage.getPhoto().getAbsolutePath());
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Source input = new DOMSource(xmlDoc);
		Result output = new StreamResult(onePage.getXmlData().getAbsolutePath());
		transformer.transform(input, output);
		System.out.println("Injecting photo path: " + techNode.item(0).getTextContent());
	}
}
