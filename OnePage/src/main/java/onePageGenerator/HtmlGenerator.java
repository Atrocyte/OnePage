package onePageGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class HtmlGenerator {

	private File xslTemplate;

    public File createHtml(OnePageCV onePage) throws Exception {
        File generatedHtml = this.determineHtmlFileName(onePage.getXmlData());
        String xmlData = onePage.getXmlData().getAbsolutePath().toString();
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource(xslTemplate));

        transformer.transform(new StreamSource(xmlData), new StreamResult(
                new FileOutputStream(generatedHtml)));

        System.out.println("Generating Ordina OnePage CV of: "
                + onePage.getName());

        return generatedHtml;
    }

    private File determineHtmlFileName(File onePageXmlData) throws IOException {
    	String xmlName = onePageXmlData.getName().substring(0, onePageXmlData.getName().indexOf(".xml"));
    	String xmlPath = onePageXmlData.getAbsolutePath().substring(0, onePageXmlData.getAbsolutePath().indexOf(xmlName)) + "\\Resources\\";
//    	System.out.printf("filename %s and path %s %n", xmlName, xmlPath);
    	File htmlFile = new File(xmlPath + xmlName.replace(" ", "_") + ".html");
    	htmlFile.createNewFile();
    	return htmlFile;
    }

	public void setXslTemplate(File xslTemplate) {
		this.xslTemplate = xslTemplate;
	}

}
