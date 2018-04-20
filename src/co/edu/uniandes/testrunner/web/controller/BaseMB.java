package co.edu.uniandes.testrunner.web.controller;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import co.edu.uniandes.testrunner.core.util.ApplicationConstants;
import co.edu.uniandes.testrunner.web.configuration.os.PathConfigurator;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;

@ManagedBean
@SessionScoped
public class BaseMB {

	@PostConstruct
	public void initialize() {
		Properties p = PathConfigurator.getConfigurator().buildPathProperties();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(ApplicationConstants.PATH_SESSION_KEY, p);
	}

	protected void infoMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, WebConstants.GROWL_INFO, message));
	}

	protected void warningMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, WebConstants.GROWL_WARNING, message));
	}

	protected void errorMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, WebConstants.GROWL_ERROR, message));
	}

}
