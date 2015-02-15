package onePageGenerator;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class FileFinder {

	public File determineCurrentFolder() {
		File currentFolder = null;
		try {
			currentFolder = new File(OnePageCvCreator.class
					.getProtectionDomain().getCodeSource().getLocation()
					.toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return currentFolder;
	}

	public ArrayList<File> collectXmlFiles(File currentFolder) {
		File[] fList = currentFolder.listFiles();
		ArrayList<File> xmlCollection = new ArrayList<File>();
		int counter = 0;
		for (File file : fList) {
			if (file.isFile() && file.getName().endsWith("xml")) {
				xmlCollection.add(file);
				counter++;
			}
		}
		System.out.printf("Found %d One Page CV candidate(s): %s %n", counter,
				xmlCollection);
		return xmlCollection;
	}

	public File findPhoto(OnePageCV onePage) throws Exception {
		File photo;
		String xmlFile = onePage.getXmlData().getAbsolutePath();
		String strippedFile = xmlFile.substring(0, xmlFile.indexOf(".xml"));
		String jpgLocation = strippedFile + ".jpg";
		String pngLocation = strippedFile + ".png";
		
		File jpg = new File(jpgLocation);
		File png = new File(pngLocation);
		
		if(jpg.exists()){
			photo = jpg;
		} else if (png.exists()){
			photo = png;
		}
		else throw new Exception("Foto niet gevonden, lever deze aan als .jpg of .png");
		return photo;
	}
}
