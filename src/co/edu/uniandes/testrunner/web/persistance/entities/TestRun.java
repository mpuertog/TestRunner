package co.edu.uniandes.testrunner.web.persistance.entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the TEST_RUNS database table.
 * 
 */
@Entity
@Table(name = "TEST_RUNS")
@NamedQuery(name = "TestRun.findAll", query = "SELECT t FROM TestRun t")
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

	// bi-directional many-to-one association to TestDetail
	@OneToMany(mappedBy = "testRun")
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

}