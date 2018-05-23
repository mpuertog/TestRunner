package co.edu.uniandes.testrunner.web.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.model.UploadedFile;

import co.edu.uniandes.testrunner.web.business.AndroidEJB;
import co.edu.uniandes.testrunner.web.persistance.entities.AndroidEmulator;

@ManagedBean
@ViewScoped
public class AndroidMB extends BaseMB {

	private AndroidEmulator selectedEmulator;
	private List<AndroidEmulator> androidEmulatorList;
	private int selectedEmulatorID;
	private UploadedFile file;
	private UploadedFile image;

	@EJB
	private AndroidEJB androidEJB;

	@PostConstruct
	public void init() {
		androidEmulatorList = androidEJB.listAllEmulators();
	}

	public void upload() {
		if (file != null) {
			infoMessage(file.getFileName() + " cargado exitosamente");
			System.out.println("Size=" + file.getSize());
		}
	}

	public void handleEmulatorChange(ValueChangeEvent event) {
		selectedEmulatorID = Integer.parseInt(event.getNewValue().toString());
		selectedEmulator = androidEJB.findAndroidEmulatorByID(selectedEmulatorID);
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}

	public AndroidEmulator getSelectedEmulator() {
		return selectedEmulator;
	}

	public void setSelectedEmulator(AndroidEmulator selectedEmulator) {
		this.selectedEmulator = selectedEmulator;
	}

	public List<AndroidEmulator> getAndroidEmulatorList() {
		return androidEmulatorList;
	}

	public void setAndroidEmulatorList(List<AndroidEmulator> androidEmulatorList) {
		this.androidEmulatorList = androidEmulatorList;
	}

	public int getSelectedEmulatorID() {
		return selectedEmulatorID;
	}

	public void setSelectedEmulatorID(int selectedEmulatorID) {
		this.selectedEmulatorID = selectedEmulatorID;
	}

}
