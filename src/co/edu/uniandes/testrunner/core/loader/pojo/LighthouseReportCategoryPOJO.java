package co.edu.uniandes.testrunner.core.loader.pojo;

import co.edu.uniandes.testrunner.core.util.ApplicationConstants;

/**
 * {@link POJO} que almacena el nombre de las categorías calificadas en las
 * Progressive Web App (PWA) y su respectiva calificación según Google
 * Lighthouse
 * 
 * @author ms.puerto@uniandes.edu.co
 *
 */
public class LighthouseReportCategoryPOJO {

	private String categoryName;
	private Number categoryScore;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Number getCategoryScore() {
		return categoryScore;
	}

	public void setCategoryScore(Number categoryScore) {
		this.categoryScore = categoryScore;
	}

	@Override
	public String toString() {
		return this.categoryName + ApplicationConstants.COLON + this.categoryScore.toString();
	}

}
