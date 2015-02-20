package onePageGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ResourceManager {
	private File targetFolder;
	private File currentFolder;

	public void unpackFiles(File currentFolder) throws IOException, URISyntaxException {
		this.currentFolder = currentFolder;
		this.createTargetFolder();
		this.unpackJar();
		this.attachFonts();
		
	}

	private void createTargetFolder() throws IOException {
		String parent = currentFolder.getAbsolutePath();
		String target = parent + "\\" + "Resources";
		this.targetFolder = new File(target);
		targetFolder.mkdir();
		
	}
	
	private void unpackJar() throws IOException {
		final File jarFile = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
		final String path = "resources/OnePageResources/";
		
		if(jarFile.isFile()) { 
		    final JarFile jar = new JarFile(jarFile);
		    final Enumeration<JarEntry> entries = jar.entries(); 
		    while(entries.hasMoreElements()) {
		        final String resource = entries.nextElement().getName();
		        if (resource.startsWith(path) && (resource.length() > path.length())) { 
		            InputStream in = getClass().getClassLoader().getResourceAsStream(resource);
		            String filename = resource.substring(path.length());
		    		File file = new File(targetFolder.getAbsolutePath() + "\\" + filename);
					boolean fileIsMade = file.createNewFile();
					if(fileIsMade){
						System.out.printf("attaching file %s %n", file);
						Files.copy(in, file.getAbsoluteFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
						
					}
		        }
		    }
		    jar.close();
		}
	}


	List<File> attachFonts() throws IOException {
		List<File> fonts = new ArrayList<File>();
		String[] fontTypes = {	"FuturaStdLight.otf", 
								"FuturaStdBook.otf", 
								"FuturaStdMedium.otf", 
								"FuturaStdBold.otf"};
		
		for (String font : fontTypes){
			File targetFile = new File(targetFolder.getAbsolutePath() + "\\" + font);
			fonts.add(targetFile);
		}
		return fonts;
	}

	File attachXslTemplate() throws FileNotFoundException {
		return new File(targetFolder.getAbsolutePath() + "\\Conversion.xsl");
	}
}
