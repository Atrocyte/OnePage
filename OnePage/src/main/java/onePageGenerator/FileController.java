package onePageGenerator;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class FileController {
	File fileLocation;
	Path fileFolder;
	private String reportMessage;

	public void findAndMatchFiles() {
		this.findCurrentFileLocation();
		FileMatcher fileMatcher = new FileMatcher();
		fileMatcher.scanForXML(fileLocation);
		fileMatcher.openXML();
//		fileMatcher.matchXmlData();
		this.setReportMessage(fileMatcher.getReportMessage());
	}

	public String getFilePath() {
		return getReportMessage();
	}

	private void findCurrentFileLocation() {
		try {
			this.fileLocation = new File(OnePageGenerator.class
					.getProtectionDomain().getCodeSource().getLocation()
					.toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public String getReportMessage() {
		return reportMessage;
	}

	public void setReportMessage(String reportMessage) {
		this.reportMessage = reportMessage;
	}
}
