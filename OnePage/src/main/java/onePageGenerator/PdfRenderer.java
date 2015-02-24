package onePageGenerator;

import java.io.ByteArrayInputStream;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;

public class PdfRenderer {
	
	private List<File> fonts;

    public File createPdf(OnePageCV onePage) throws Exception {
        String path = onePage.getHtml().getParent() + "\\";
        File htmlFileLoc = new File(path +  onePage.getName().replace(" ", "_") + ".html");
        File outputPdf = new File(path + onePage.getName().replace(" ", "_") + ".pdf");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        HtmlCleaner cleaner = new HtmlCleaner();
        CleanerProperties props = cleaner.getProperties();
        TagNode node = cleaner.clean(htmlFileLoc);

        new PrettyXmlSerializer(props).writeToStream(node, out);
        
        ITextRenderer renderer = new ITextRenderer();
        
        //andere Charsets geven accenten en trema's niet goed weer
        InputStream stream = new ByteArrayInputStream(out.toString().getBytes(StandardCharsets.ISO_8859_1));
        

        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        fac.setNamespaceAware(false);
        fac.setValidating(false);
        fac.setFeature("http://xml.org/sax/features/namespaces", false);
        fac.setFeature("http://xml.org/sax/features/validation", false);
        fac.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        fac.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        DocumentBuilder builder = fac.newDocumentBuilder();

        Document doc = builder.parse(stream);

        String documentPath = htmlFileLoc.toURI().toURL().toString();
        
        renderer.setDocument(doc, documentPath);

        ITextFontResolver fr = renderer.getFontResolver();
        
        for (File font : fonts){
        	fr.addFont(font.getAbsolutePath(), true);
        }
        renderer.layout();

        OutputStream outputStream = new FileOutputStream(outputPdf);
        renderer.createPDF(outputStream);

        // Finishing up
        renderer.finishPDF();
        out.flush();
        out.close();
        return outputPdf;

    }

	public void setFonts(List<File> fonts) {
		this.fonts = fonts;
	}
}
