package onePageGenerator;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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

	public void injectPhoto(OnePageCV onePage) {
		NodeList techNode = xmlDoc.getElementsByTagName("Tech1");
		techNode.item(0).setTextContent(onePage.getPhoto().getAbsolutePath());
//		System.out.println(techNode.item(0).getTextContent());
	}
}