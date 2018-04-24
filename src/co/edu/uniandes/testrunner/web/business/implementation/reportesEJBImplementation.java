package co.edu.uniandes.testrunner.web.business.implementation;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import co.edu.uniandes.testrunner.web.business.ReportesEJB;
import co.edu.uniandes.testrunner.web.persistance.entities.TestDetail;
import co.edu.uniandes.testrunner.web.persistance.entities.TestRun;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

@Stateless
public class reportesEJBImplementation implements ReportesEJB, Serializable {

    private EntityManager entityManager;
    protected final static Logger logger = Logger.getRootLogger();

    /**
     *
     * @param framework
     * @return
     */
    @Override
    public List<TestRun> findByFramework(String framework) {
        List<TestRun> testList = new ArrayList<>();
        try {
            testList = entityManager.createNamedQuery("TestRun.findByFramework")
                    .setParameter("framework", framework)
                    .getResultList();
        } catch (Exception e) {
            logger.error(e);
        }
        return testList;
    }

    /**
     *
     * @param testRunList
     * @return
     */
    @Override
    public List<TestDetail> findByTestRun(List<TestRun> testRunList) {
        List<TestDetail> reportTestList = new ArrayList<>();
        testRunList.stream().forEach((i) -> {
            List<TestDetail> reportsByTestRun;
            try {
                reportsByTestRun = entityManager.createNamedQuery("TestDetail.findByTestRun")
                        .setParameter("testRun", i)
                        .getResultList();
                reportsByTestRun.stream().forEach((result) -> {
                    reportTestList.add(result);
                });
            } catch (Exception e) {
                logger.error(e);
            }
        });
        return reportTestList;
    }

}
