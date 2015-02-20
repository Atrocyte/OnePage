package onePageGenerator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileCleaner {

	public void clean(OnePageCV onePage) throws IOException {
		this.renamePdf(onePage);
		this.deleteGeneratedHtml(onePage);
	}

	private void renamePdf(OnePageCV onePage) throws IOException {
		File oldPdf = onePage.getPdf();
		File newPdfName = new File(onePage.getXmlData().getParent() + "\\" + onePage.getName() + " - " + onePage.getFunction() + ".pdf");
		this.removePreviousPdf(newPdfName);
		boolean isPdfRenamed = oldPdf.renameTo(newPdfName);
		if (isPdfRenamed){
//			System.out.println("Pdf renamed, deleting old file");
			Files.deleteIfExists(oldPdf.getAbsoluteFile().toPath());
			}
		System.out.printf("Generated Ordina CV: %s", newPdfName.getName());
		}

	private void removePreviousPdf(File newPdfName) throws IOException {
		if(newPdfName.exists()){
			Files.delete(newPdfName.toPath());
		} else return;
		
	}

	private void deleteGeneratedHtml(OnePageCV onePage) throws IOException {
		boolean fileDeleted = Files.deleteIfExists(onePage.getHtml().toPath());
//		if (fileDeleted){System.out.println("HTML clutter removed.");}
		
	}
}
