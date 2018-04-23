package co.edu.uniandes.testrunner.web.controller;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import co.edu.uniandes.testrunner.core.commandrunner.CommandRunner;
import co.edu.uniandes.testrunner.core.util.ApplicationConstants;
import co.edu.uniandes.testrunner.core.util.WildardReplaceUtil;
import co.edu.uniandes.testrunner.web.configuration.os.PathConfiguratorPropertyKeys;
import co.edu.uniandes.testrunner.web.transversal.WebConstants;

@ManagedBean
@ViewScoped
public class ReportesMB extends BaseMB {

	private String reporteLink;
	
	private Properties properties;

	@PostConstruct
	public void init() {
		properties = (Properties) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(ApplicationConstants.PATH_SESSION_KEY);
                reporteLink = " ";
	}

    public String getReporteLink() {
        return reporteLink;
    }

    public void setReporteLink(String reporteLink) {
        this.reporteLink = reporteLink;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
        
        
}

