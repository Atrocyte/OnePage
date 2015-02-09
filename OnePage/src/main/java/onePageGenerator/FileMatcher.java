package onePageGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FileMatcher {
	String reportMessage;
	File folder;
	Document xmlDoc;
	Node xmlNode;
	List<File> retrievedXml = new ArrayList<File>();

	void scanForXML(File jarFile) {
		this.folder = jarFile.toPath().getParent().toFile();
		File[] fList = folder.listFiles();
		int counter = 0;
		for (File file : fList) {
			if (file.isFile() && file.getName().endsWith("xml")) {
				retrievedXml.add(file);
				counter++;
			}
		}
		System.out.printf("Retrieved %d CV candidates: %s %n", counter,
				retrievedXml);
	}

	void openXML() {
		XmlToHtmlGenerator htmlGen = XmlToHtmlGenerator.getHtmlFactory();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		try {
			dBuilder = dbFactory.newDocumentBuilder();

			for (File file : retrievedXml) {
				String fileName = this
						.stripExtension(file.getName().toString());
				this.xmlDoc = dBuilder.parse(file);
				NodeList nList = xmlDoc.getElementsByTagName("Name");
				this.xmlNode = nList.item(0);
				if (xmlNode.getTextContent().equals(fileName)) {
					this.reportMessage = "Match found, generating OnePage CV for "
							+ fileName; // alleen message over laatste persoon.
					htmlGen.createHTML(file);
				} else {
					this.reportMessage = "Unable to match "
							+ fileName
							+ " with XML content. Does the name inside the document match the filename?";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String stripExtension(String str) {
		int pos = str.lastIndexOf(".");
		if (pos == -1) {
			return str;
		}
		return str.substring(0, pos);
	}

	public String getReportMessage() {
		return reportMessage;
	}

}
