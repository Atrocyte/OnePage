package onePageGenerator;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class OnePageGenerator {
	FileFinder fileFinder = new FileFinder();
	XmlEditor xmlEditor = new XmlEditor();
	HtmlGenerator htmlGen = new HtmlGenerator();
	PdfRenderer pdfGen = new PdfRenderer();
	FileCleaner fileCleaner = new FileCleaner();
	ResourceManager resourceManager = new ResourceManager();
	File currentFolder;
	ArrayList<File> xmlCollection;

	void scanFolderForXml() throws Exception {
		System.out.println("Scanning for xml files...");
		this.currentFolder = fileFinder.determineCurrentFolder();
		this.xmlCollection = fileFinder.collectXmlFiles(currentFolder);
	}
	
	void unpackResources() throws IOException, URISyntaxException{
		resourceManager.unpackFiles(currentFolder);
		htmlGen.setXslTemplate(resourceManager.attachXslTemplate());
		pdfGen.setFonts(resourceManager.attachFonts());
	}

	void generateOnePageCV() throws Exception {
		for (File file : xmlCollection) {
			xmlEditor.openFile(file);
			String onePageName = xmlEditor.compareDataWithFilename(file);
			OnePageCV onePage = new OnePageCV(onePageName, file);
			onePage.setPhoto(fileFinder.findPhoto(onePage)); 
			xmlEditor.injectPhoto(onePage); 
			xmlEditor.setFuction(onePage);
			onePage.setHtml(htmlGen.createHtml(onePage));
			onePage.setPdf(pdfGen.createPdf(onePage));
			fileCleaner.clean(onePage);
		}

	}
}
