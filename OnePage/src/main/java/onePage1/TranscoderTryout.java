package onePage1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.apache.avalon.framework.container.ContainerUtil;
import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.XMLAbstractTranscoder;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.fop.afp.modca.Document;
import org.apache.fop.svg.PDFTranscoder;

public class TranscoderTryout {
	public static void main(String[] args) throws TranscoderException, IOException {
		
	
	//Create transcoder
    Transcoder transcoder = new PDFTranscoder();
    
//    File svga = new File("D:\\Workspace\\OnePage\\src\\main\\resources\\text-text-03-b.svg");
    File svg = new File("D:\\Workspace\\OnePage\\src\\main\\resources\\optimised.svg");
    File pdf = new File("D:\\Workspace\\OnePage\\src\\main\\resources\\1POptimized.pdf");
    
    //Configure the transcoder
    try {
        DefaultConfigurationBuilder cfgBuilder = new DefaultConfigurationBuilder();
        Configuration cfg = cfgBuilder.buildFromFile(new File("D:\\Workspace\\OnePage\\src\\main\\resources\\pdf-renderer-cfg.xml"));
        ContainerUtil.configure(transcoder, cfg);
        System.out.println("starting defaultConfigBuilder");
    } catch (Exception e) {
        throw new TranscoderException(e);
    }
    
    final int dpi = 300;
    transcoder.addTranscodingHint(ImageTranscoder.KEY_PIXEL_UNIT_TO_MILLIMETER, 
            new Float((float)(25.4 / dpi))); 
    transcoder.addTranscodingHint(XMLAbstractTranscoder.KEY_XML_PARSER_VALIDATING, Boolean.FALSE);
    transcoder.addTranscodingHint(PDFTranscoder.KEY_STROKE_TEXT, Boolean.FALSE);

    
    //Setup input
    InputStream in = new java.io.FileInputStream(svg);
    try {
        TranscoderInput input = new TranscoderInput(in);
        input.setURI(svg.toURI().toString());
        System.out.println(input.getURI().toString());
        
        //Setup output
        System.out.println(pdf.toURI().toString());
        OutputStream out = new java.io.FileOutputStream(pdf);
        System.out.println(out.toString());
        
        //Buffer the OutputStream for better performance
        out = new java.io.BufferedOutputStream(out);
        System.out.println(out.toString());
        try {
            TranscoderOutput output = new TranscoderOutput(out);
            
            //Do the transformation
            transcoder.transcode(input, output);
        } finally {
            out.close();
        }
    } finally {
        in.close();
    }

}}
