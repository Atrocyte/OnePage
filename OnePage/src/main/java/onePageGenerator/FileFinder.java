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
		System.out.printf("Retrieved %d CV candidates: %s %n", counter,
				xmlCollection);
		return xmlCollection;
	}

	public File findPhoto(OnePageCV onePage) {
		// TODO To be implemented.
		return null;
	}

}
