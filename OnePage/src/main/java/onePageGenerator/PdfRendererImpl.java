package onePageGenerator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

public class PdfRendererImpl {
	

    public File createPdf(OnePageCV onePage) throws Exception {
    	String path = onePage.getHtml().getParent();
    	System.out.println(path);

        // Create a buffer to hold the cleaned up HTML
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.out.println("Buffer Created...");

        // Clean up the HTML to be well formed
        HtmlCleaner cleaner = new HtmlCleaner();
        CleanerProperties props = cleaner.getProperties();
        System.out.println(onePage.getHtml().getPath());
        TagNode node = cleaner.clean(PdfRendererImpl.class.getResourceAsStream("/src/Oscar Ruiz Fernandez.html"));
        System.out.println("Cleaning HTML...");

        // Instead of writing to System.out we now write to the ByteArray buffer
        new PrettyXmlSerializer(props).writeToStream(node, out);
        System.out.println("HTML Cleaned, writing to buffer....");

        // Create the PDF
        ITextRenderer renderer = new ITextRenderer();
        File outputPdf = new File(path + "\\" + onePage.getName() + ".pdf");
        InputStream stream = new ByteArrayInputStream(out.toString().getBytes(StandardCharsets.UTF_8));
        System.out.println("Creating InputStrem from buffer...");

        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        fac.setNamespaceAware(false);
        fac.setValidating(false);
        fac.setFeature("http://xml.org/sax/features/namespaces", false);
        fac.setFeature("http://xml.org/sax/features/validation", false);
        fac.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        fac.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        DocumentBuilder builder = fac.newDocumentBuilder();
        System.out.println("Creating document builer to build HTML...");

        Document doc = builder.parse(stream);
        System.out.println("Parsing Stream through builder to HTML Document...");

        renderer.setDocument(doc, path + "\\" + onePage.getName() + ".html");
        System.out.println("Setting up HTML Document...");

        ITextFontResolver fr = renderer.getFontResolver();
        File font1 = new File(path + "/fonts/FuturaStd-Light.otf");
        File font2 = new File(path + "/fonts/FuturaStd-Book.otf");
        File font3 = new File(path + "/fonts/FuturaStd-Medium.otf");
        File font4 = new File(path + "/fonts/FuturaStd-Bold.otf");
        fr.addFont(font1.getAbsolutePath(), true);
        fr.addFont(font2.getAbsolutePath(), true);
        fr.addFont(font3.getAbsolutePath(), true);
        fr.addFont(font4.getAbsolutePath(), true);
        System.out.println("Embedding Fonts...");
       
        renderer.layout();
        System.out.println("Rendering HTML Document...");

        OutputStream outputStream = new FileOutputStream(path + "/" + onePage.getName() + ".pdf");
        renderer.createPDF(outputStream);
        System.out.println("Writing to Output...");

        // Finishing up
        renderer.finishPDF();
        System.out.println("Finishing and Closing, thank you for flying with us!!");
        out.flush();
        out.close();
		return outputPdf;

    }
}
