package onePageGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;

import net.coobird.thumbnailator.Thumbnails;

public class FileFinder {

    public File determineCurrentFolder()  {
        URL url = OnePageCvCreator.class.getProtectionDomain().getCodeSource().getLocation();
        File temp = new File(url.getPath());
        File temp2 = new File(temp.getAbsolutePath());
        File currentFolder = new File(temp2.getParent().replace("%20", " "));
        System.out.println(currentFolder.getAbsolutePath().toString());
        return currentFolder;
    }

    public ArrayList<File> collectXmlFiles(File currentFolder) throws FileNotFoundException {
        File[] fList = currentFolder.listFiles();
        ArrayList<File> xmlCollection = new ArrayList<File>();
        int counter = 0;
        if (fList == null){
        	throw new FileNotFoundException("No xml found in folder: " + currentFolder.getAbsolutePath());
        }
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

        if (jpg.exists()) {
            photo = jpg;
        } else if (png.exists()) {
            photo = png;
        } else {
            throw new Exception("Foto niet gevonden, lever deze aan als .jpg of .png");
        }
        return photoOfCorrectSize(photo);
    }

	private File photoOfCorrectSize(File photo) throws IOException {
		System.out.printf("Resizing photo of %s%n", photo.getName());
		if(photo.getName().endsWith("png")){
			Thumbnails.of(photo).size(300, 300).outputFormat("png").toFile(photo);
		} else {
		Thumbnails.of(photo).size(300, 300).outputFormat("jpg").toFile(photo);
		} 
		return photo;
	}
}
