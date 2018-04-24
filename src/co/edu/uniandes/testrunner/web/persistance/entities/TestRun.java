package co.edu.uniandes.testrunner.web.persistance.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the TEST_RUNS database table.
 * 
 */
@Entity
@Table(name = "TEST_RUNS")
@NamedQueries({ @NamedQuery(name = "TestRun.findAll", query = "SELECT t FROM TestRun t"),
		@NamedQuery(name = "TestRun.findByFramework", query = "SELECT t FROM TestRun t WHERE t.testFramework = :framework ") })

@XmlRootElement
public class TestRun implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "TEST_COMMAND")
	private String testCommand;

	@Temporal(TemporalType.DATE)
	@Column(name = "TEST_DATE")
	private Date testDate;

	@Column(name = "TEST_TYPE")
	private String testType;

	@Column(name = "TEST_FRAMEWORK")
	private String testFramework;

	@Transient
	private String downloadLink;

	// bi-directional many-to-one association to TestDetail
	@OneToMany(mappedBy = "testRun", cascade = CascadeType.ALL)
	private List<TestDetail> testDetails;

	public TestRun() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTestCommand() {
		return this.testCommand;
	}

	public void setTestCommand(String testCommand) {
		this.testCommand = testCommand;
	}

	public Date getTestDate() {
		return this.testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getTestType() {
		return this.testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public List<TestDetail> getTestDetails() {
		return this.testDetails;
	}

	public void setTestDetails(List<TestDetail> testDetails) {
		this.testDetails = testDetails;
	}

	public TestDetail addTestDetail(TestDetail testDetail) {
		getTestDetails().add(testDetail);
		testDetail.setTestRun(this);
		return testDetail;
	}

	public TestDetail removeTestDetail(TestDetail testDetail) {
		getTestDetails().remove(testDetail);
		testDetail.setTestRun(null);
		return testDetail;
	}

	public String getTestFramework() {
		return testFramework;
	}

	public void setTestFramework(String testFramework) {
		this.testFramework = testFramework;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

}