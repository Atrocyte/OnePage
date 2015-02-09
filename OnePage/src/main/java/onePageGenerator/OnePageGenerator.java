package onePageGenerator;

import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.lowagie.text.DocumentException;

public class OnePageGenerator {
	XmlToHtmlGenerator htmlGen = XmlToHtmlGenerator.getHtmlFactory();
	String reportMessage;

	public static void main(String[] args) {
		OnePageGenerator app = new OnePageGenerator();
		app.findFileLocation();
		app.createPdfFromHtml();
		// app.reportStatus();
	}

	private void createPdfFromHtml() {
		File htmlFile = htmlGen.getGeneratedHtmlFile();
//		System.out.println(htmlFile.getAbsolutePath().toString()); htmlFile is nog null...
		PdfRendererImpl pdfGen = new PdfRendererImpl();
		try {
			System.out.println("Heading to the PDF Generator... to be continued");
//			pdfGen.renderPdf(htmlFile);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private void reportStatus() {
		Frame f = new Frame();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.add(new Label(reportMessage));
		f.setSize(1500, 100);
		f.setVisible(true);
	}

	private void findFileLocation() {
		FileController fileControl = new FileController();
		fileControl.findAndMatchFiles();
		this.reportMessage = fileControl.getReportMessage();
	}
}
