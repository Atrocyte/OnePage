package resources;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;

import java.io.*;

public class PdfRendererTest {

    public static void main(String[] args) throws IOException {

        // Clean up the HTML to be well formed
        HtmlCleaner cleaner = new HtmlCleaner();
        CleanerProperties props = cleaner.getProperties();
        TagNode node = cleaner.clean(PdfRenderer.class.getResourceAsStream("/src/clean_minimal.html"));
        new PrettyXmlSerializer(props).writeToStream(node, System.out);

    }

}
