package co.edu.uniandes.testrunner.core.loader.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link POJO} que contiene los datos relevantes del reporte Lighthouse para
 * Progressive Web Apps (PWA)
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class LighthousePOJO extends POJO {

	private String userAgent;
	private String lighthouseVersion;
	private String initialUrl;
	private String serviceWorker;
	private String worksOffline;
	private List<LighthouseReportCategoryPOJO> categories;

	public LighthousePOJO() {
		this.categories = new ArrayList<LighthouseReportCategoryPOJO>();
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getLighthouseVersion() {
		return lighthouseVersion;
	}

	public void setLighthouseVersion(String lighthouseVersion) {
		this.lighthouseVersion = lighthouseVersion;
	}

	public String getInitialUrl() {
		return initialUrl;
	}

	public void setInitialUrl(String initialUrl) {
		this.initialUrl = initialUrl;
	}

	public String getServiceWorker() {
		return serviceWorker;
	}

	public void setServiceWorker(String serviceWorker) {
		this.serviceWorker = serviceWorker;
	}

	public String getWorksOffline() {
		return worksOffline;
	}

	public void setWorksOffline(String worksOffline) {
		this.worksOffline = worksOffline;
	}

	public List<LighthouseReportCategoryPOJO> getCategories() {
		return categories;
	}

	public void setCategories(List<LighthouseReportCategoryPOJO> categories) {
		this.categories = categories;
	}

}
