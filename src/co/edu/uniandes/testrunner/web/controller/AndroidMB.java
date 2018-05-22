package co.edu.uniandes.testrunner.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class AndroidMB extends BaseMB {

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload() {
		if (file != null) {
			infoMessage(file.getFileName() + " cargado exitosamente");
			System.out.println("Size="+file.getSize());
		}
	}

}
