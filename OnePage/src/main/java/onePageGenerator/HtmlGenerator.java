package onePageGenerator;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class HtmlGenerator {

	private File xslTemplate;
	private String fileFolder;

	public File createHtml(OnePageCV onePage) throws Exception {
		xslTemplate = this.locateXslFile(onePage.getXmlData());
		File generatedHtml = this.determineHtmlFileName(onePage.getXmlData());
		String xmlData = onePage.getXmlData().getAbsolutePath().toString();
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer(new StreamSource(
				xslTemplate));

		transformer.transform(new StreamSource(xmlData), new StreamResult(
				new FileOutputStream(generatedHtml)));

		System.out.println("Generating Ordina OnePage CV of: "
				+ onePage.getName());

		return generatedHtml;
	}

	private File locateXslFile(File onePageXmlData) {
		// TODO deze moet anders, gaat nu uit van externe file in dezelfde
		// folder als de jar.
		String fileFolder = onePageXmlData.getAbsolutePath().toString();
		return new File(fileFolder.substring(0,
				fileFolder.indexOf(onePageXmlData.getName()))
				+ "\\Style1P.xsl");
	}

	private File determineHtmlFileName(File onePageXmlData) {
		String xmlFile = onePageXmlData.getAbsolutePath().toString();
		return new File(xmlFile.substring(0, xmlFile.indexOf(".xml")) + ".html");
	}

}
