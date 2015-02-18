package onePageGenerator;

import java.io.ByteArrayInputStream;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;

public class PdfRenderer {

    public File createPdf(OnePageCV onePage) throws Exception {
//        String path = onePage.getHtml().getParent(); //--> waarom heb je path nodig? je kan alles relatief aan jar doen? <-dat was het al :P
        String path = PdfRenderer.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        File htmlFileLoc = new File(path + onePage.getName().replace(" ", "_") + ".html");
        File outputPdf = new File(path + onePage.getName().replace(" ", "_") + ".pdf");
        // Create a buffer to hold the cleaned up HTML
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        // Clean up the HTML to be well formed
        HtmlCleaner cleaner = new HtmlCleaner();
        CleanerProperties props = cleaner.getProperties();
        TagNode node = cleaner.clean(htmlFileLoc);

        // Instead of writing to System.out we now write to the ByteArray buffer
        new PrettyXmlSerializer(props).writeToStream(node, out);
        
//        System.out.println(out.toString());
        // Create the PDF-
        ITextRenderer renderer = new ITextRenderer();
        InputStream stream = new ByteArrayInputStream(out.toString().getBytes(StandardCharsets.UTF_8));

        DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
        fac.setNamespaceAware(false);
        fac.setValidating(false);
        fac.setFeature("http://xml.org/sax/features/namespaces", false);
        fac.setFeature("http://xml.org/sax/features/validation", false);
        fac.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        fac.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        DocumentBuilder builder = fac.newDocumentBuilder();

        Document doc = builder.parse(stream);

        String documentPath = "file:///" + htmlFileLoc.getAbsoluteFile().toString().replace("\\", "/");
//        System.out.println(documentPath);
        renderer.setDocument(doc, documentPath);

        ITextFontResolver fr = renderer.getFontResolver();
        File font1 = new File(path + "fonts/FuturaStd-Light.otf");
        File font2 = new File(path + "fonts/FuturaStd-Book.otf");
        File font3 = new File(path + "fonts/FuturaStd-Medium.otf");
        File font4 = new File(path + "fonts/FuturaStd-Bold.otf");
        fr.addFont(font1.getAbsolutePath(), true);
        fr.addFont(font2.getAbsolutePath(), true);
        fr.addFont(font3.getAbsolutePath(), true);
        fr.addFont(font4.getAbsolutePath(), true);

        renderer.layout();

//        OutputStream outputStream = new FileOutputStream(onePage.getName() + ".pdf"); //--> Gewoon opslaan op de root
        OutputStream outputStream = new FileOutputStream(outputPdf); //--> Gewoon opslaan op de root
        renderer.createPDF(outputStream);

        // Finishing up
        renderer.finishPDF();
        out.flush();
        out.close();
        return outputPdf;

    }
}
