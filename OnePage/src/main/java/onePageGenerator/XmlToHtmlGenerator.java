package onePageGenerator;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XmlToHtmlGenerator {
	private static XmlToHtmlGenerator instance = null;
	private String fileLoc;
	private File generatedHtml;
	private File xslTemplate;

	public static XmlToHtmlGenerator getHtmlFactory() {
		if (instance == null) {
			return new XmlToHtmlGenerator();
		} else return instance;
	}

	public void createHTML(File fileToTransform) {
		this.fileLoc = fileToTransform.getAbsolutePath().toString();
		this.xslTemplate = this.locateXslFile(fileToTransform);
		this.generatedHtml = this.determineHtmlFileName(fileToTransform);
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer(new StreamSource(
					xslTemplate));

			transformer.transform(new StreamSource(fileLoc), new StreamResult(
					new FileOutputStream(generatedHtml)));

			System.out.println("Generating Ordina OnePage CV of: " + fileLoc);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File getGeneratedHtmlFile() {
		return generatedHtml;
	}

	private File locateXslFile(File fileToTransform) {
		return new File(fileLoc.substring(0,
				fileLoc.indexOf(fileToTransform.getName()))
				+ "\\Style1P.xsl");
	}

	private File determineHtmlFileName(File fileToTransform) {
		return new File(fileLoc.substring(0, fileLoc.indexOf(".xml")) + ".html");
	}

}