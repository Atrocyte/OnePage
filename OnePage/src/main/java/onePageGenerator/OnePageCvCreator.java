package onePageGenerator;

public class OnePageCvCreator {
	
	public static void main(String[] args) throws Exception {
		OnePageGenerator onePageGenerator = new OnePageGenerator();
		onePageGenerator.scanFolderForXml();
		onePageGenerator.unpackResources();
		onePageGenerator.generateOnePageCV();
//		onePageGenerator.cleanupResourceFolder();
	}
}
	