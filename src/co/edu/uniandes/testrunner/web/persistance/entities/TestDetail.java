package co.edu.uniandes.testrunner.web.persistance.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TEST_DETAIL database table.
 * 
 */
@Entity
@Table(name="TEST_DETAIL")
@NamedQuery(name="TestDetail.findAll", query="SELECT t FROM TestDetail t")
public class TestDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="FILE_CONTENT")
	private String fileContent;

	@Column(name="FILE_NAME")
	private String fileName;

	//bi-directional many-to-one association to TestRun
	@ManyToOne
	@JoinColumn(name="TEST_ID")
	private TestRun testRun;

	public TestDetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileContent() {
		return this.fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public TestRun getTestRun() {
		return this.testRun;
	}

	public void setTestRun(TestRun testRun) {
		this.testRun = testRun;
	}

}