package onePageGenerator;

import com.lowagie.text.DocumentException;
import java.io.ByteArrayInputStream;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;

public class PdfRenderer {

    public static void main(String[] args) throws IOException, DocumentException, ParserConfigurationException, SAXException, URISyntaxException {

        // Create a buffer to hold the cleaned up HTML
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.out.println("Buffer Created...");

        // Clean up the HTML to be well formed
        HtmlCleaner cleaner = new HtmlCleaner();
        CleanerProperties props = cleaner.getProperties();
        TagNode node = cleaner.clean(PdfRenderer.class.getResourceAsStream("/src/index.html"));
        System.out.println("Cleaning HTML...");

        // Instead of writing to System.out we now write to the ByteArray buffer
        new PrettyXmlSerializer(props).writeToStream(node, out);
        System.out.println("HTML Cleaned, writing to buffer....");

        // Create the PDF
        ITextRenderer renderer = new ITextRenderer();
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

        renderer.setDocument(doc, "file:///C:/Users/suy20680/Dropbox/CV/1page/Clean_Minimal-flying-saucer/xhtml2pdf/src/src/");
        System.out.println("Setting up HTML Document...");

        ITextFontResolver fr = renderer.getFontResolver();
        fr.addFont("file:///C:/Users/suy20680/Dropbox/CV/1page/Clean_Minimal-flying-saucer/xhtml2pdf/src/src/fonts/FuturaStd-Light.otf", true);
        fr.addFont("file:///C:/Users/suy20680/Dropbox/CV/1page/Clean_Minimal-flying-saucer/xhtml2pdf/src/src/fonts/FuturaStd-Book.otf", true);
        fr.addFont("file:///C:/Users/suy20680/Dropbox/CV/1page/Clean_Minimal-flying-saucer/xhtml2pdf/src/src/fonts/FuturaStd-Medium.otf", true);
        fr.addFont("file:///C:/Users/suy20680/Dropbox/CV/1page/Clean_Minimal-flying-saucer/xhtml2pdf/src/src/fonts/FuturaStd-Bold.otf", true);
        System.out.println("Embedding Fonts...");
       
        renderer.layout();
        System.out.println("Rendering HTML Document...");

        OutputStream outputStream = new FileOutputStream("src/output/Clean Minimal CV.pdf");
        renderer.createPDF(outputStream);
        System.out.println("Writing to Output...");

        // Finishing up
        renderer.finishPDF();
        System.out.println("Finishing and Closing, thank you for flying with us!!");
        out.flush();
        out.close();

    }
}
