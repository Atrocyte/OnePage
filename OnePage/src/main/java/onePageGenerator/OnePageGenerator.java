package onePageGenerator;

import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;

public class OnePageGenerator {
	FileFinder fileFinder = new FileFinder(); // TODO inject?
	XmlEditor xmlEditor = new XmlEditor();
	HtmlGenerator htmlGen = new HtmlGenerator();
	PdfRendererImpl pdfGen = new PdfRendererImpl();
	File currentFolder;
	ArrayList<File> xmlCollection;

	void scanFolderForXml() {
		System.out.println("Scanning for xml files...");
		this.currentFolder = fileFinder.determineCurrentFolder();
		this.xmlCollection = fileFinder.collectXmlFiles(currentFolder);
	}

	void generateOnePageCV() throws Exception {
		for (File file : xmlCollection) {
			xmlEditor.openFile(file);
			String onePageName = xmlEditor.compareDataWithFilename(file);
			OnePageCV onePage = new OnePageCV(onePageName, file);
			onePage.setPhoto(fileFinder.findPhoto(onePage)); 
			xmlEditor.injectPhoto(onePage); 
			onePage.setHtml(htmlGen.createHtml(onePage));
//			Desktop.getDesktop().open(onePage.getHtml()); //opens html directly for debugging
//			onePage.setPdf(pdfGen.createPdf(onePage)); //TODO not yet working correctly
			
		}
	}
}
