package co.edu.uniandes.testrunner.web.business;

import co.edu.uniandes.testrunner.web.persistance.entities.TestDetail;
import javax.ejb.Local;

import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;
import java.util.ArrayList;
import java.util.List;

@Local
public interface ReportesEJB {

    public List<TestRun> findByFramework(String framework);
    
    public List<TestDetail> findByTestRun(List<TestRun> testRunList);
    
}
