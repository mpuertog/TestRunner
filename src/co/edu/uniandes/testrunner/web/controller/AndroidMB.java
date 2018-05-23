package co.edu.uniandes.testrunner.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

import co.edu.uniandes.testrunner.core.commandrunner.CommandRunner;
import co.edu.uniandes.testrunner.core.util.ApplicationConstants;
import co.edu.uniandes.testrunner.core.util.FilesConstants;
import co.edu.uniandes.testrunner.web.business.AndroidEJB;
import co.edu.uniandes.testrunner.web.configuration.os.PathConfiguratorPropertyKeys;
import co.edu.uniandes.testrunner.web.persistance.entities.AndroidEmulator;
import co.edu.uniandes.testrunner.web.transversal.FileUtil;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;

@ManagedBean
@ViewScoped
public class AndroidMB extends BaseMB {

	private AndroidEmulator selectedEmulator;
	private List<AndroidEmulator> androidEmulatorList;
	private int selectedEmulatorID;
	private UploadedFile file;
	private UploadedFile image;
	private String gherkinCode;
	private Properties properties;

	@EJB
	private AndroidEJB androidEJB;

	@PostConstruct
	public void init() {
		properties = (Properties) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(ApplicationConstants.PATH_SESSION_KEY);
		androidEmulatorList = androidEJB.listAllEmulators();
	}

	public void handleEmulatorChange(ValueChangeEvent event) {
		selectedEmulatorID = Integer.parseInt(event.getNewValue().toString());
		selectedEmulator = androidEJB.findAndroidEmulatorByID(selectedEmulatorID);
	}

	public void runAndroidE2ETest() {
		if (!isAndroidFormComplete()) {
			warningMessage(WebConstants.FIELDS_INCOMPLETE);
			return;
		}
		String encodedGherkinCode = FileUtil.getBase64FromString(gherkinCode);
		String encodedAPK = FileUtil.getBase64FromString(new String(file.getContents()));
		String userPath = properties.get(PathConfiguratorPropertyKeys.USER_PROFILE).toString().trim();
		String ruta = userPath + FilesConstants.CALABASH_PATH;
		CommandRunner.getRunner().runCommand(String.format(FilesConstants.CALABASH_DELETE_APK, ruta));
		byte[] contenido;
		try {
			contenido = IOUtils.toByteArray(file.getInputstream());
			FileOutputStream fos = new FileOutputStream(ruta + file.getFileName());
			fos.write(contenido);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		androidEJB.runAndroidE2ETest(file.getFileName(), encodedGherkinCode, encodedAPK, selectedEmulator.getCommand());
		infoMessage(WebConstants.CYPRESS_RUNNING + file.getFileName());
	}

	private boolean isAndroidFormComplete() {
		if (file == null || file.getSize() <= 0)
			return false;

		if (selectedEmulator == null)
			return false;

		if (gherkinCode == null || gherkinCode.equals(""))
			return false;

		return true;
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

	public String getGherkinCode() {
		return gherkinCode;
	}

	public void setGherkinCode(String gherkinCode) {
		this.gherkinCode = gherkinCode;
	}

}
