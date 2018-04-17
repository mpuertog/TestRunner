package co.edu.uniandes.testrunner.web.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import co.edu.uniandes.testrunner.web.transversal.WebConstants;

@ManagedBean
public class BaseMB {

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
