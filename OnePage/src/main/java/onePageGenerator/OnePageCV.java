package onePageGenerator;

import java.io.File;

public class OnePageCV {

    private String name;
    private String function;
    private File xmlData;
    private File photo;
    private File html;
    private File pdf;

    public OnePageCV(String onePageName, File file) {
        this.name = onePageName;
        this.xmlData = file;
    }

    public OnePageCV(String onePageName) {
        this.name = onePageName;
    }

    public String getName() {
        return name;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public File getPhoto() {
        return this.photo;
    }

    public File getXmlData() {
        return xmlData;
    }

    public void setHtml(File createdHtml) {
        this.html = createdHtml;
    }

    public File getHtml() {
        return this.html;
    }

    public void setPdf(File createdPdf) {
        this.pdf = createdPdf;
    }

    public File getPdf() {
        return this.pdf;
    }

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

}
