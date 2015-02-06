package onePageGenerator;

import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URISyntaxException;

public class OnePageGenerator {
	String reportMessage;

	public static void main(String[] args) throws URISyntaxException {
		OnePageGenerator app = new OnePageGenerator();
		app.findFileLocation();
//		app.reportStatus();
	}

	private void reportStatus() {
		Frame f = new Frame();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.add(new Label(reportMessage));
		f.setSize(1500, 100);
		f.setVisible(true);
	}

	private void findFileLocation() {
		FileController fileControl = new FileController();
		fileControl.findAndMatchFiles();
		this.reportMessage = fileControl.getReportMessage();
	}
}
