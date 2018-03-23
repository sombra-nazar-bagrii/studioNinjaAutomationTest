package tests.CreatingJob;

import Factory.Messages;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Factory.JobConfigurationFactory;
import tests.TestBase;

/**
 * Created by sombra-15 on 12.09.17.
 */
public class CreatingJobFromDashboard extends TestBase {

    // Positive test cases
    @DataProvider
    public Object[][] getJobPositiveConf(){
        return new Object[][]{
                {"JobConf1", "Test 1.1"},
                {"JobConf2", "Test 1.2"},
                {"JobConf3", "Test 1.3"},
                {"JobConf4", "Test 1.4"},
                {"JobConf5", "Test 1.5"},
                {"JobConf6", "Test 1.6"},};
    }

    @Test(dataProvider = "getJobPositiveConf", enabled = true)
    public void jobFromDashboard(String configType, String jobName){
        startPage.goToDashboard()
                .createNewJob()
                .createNewJob(
                        JobConfigurationFactory.getConfiguration(configType),
                        jobName,
                        DASHBOARD_PAGE);

        Assert.assertTrue(startPage.checkMessageForCase(configType));
    }

    // Negative test cases
    @DataProvider
    public Object[][] getJobNegativeConf(){
        return new Object[][]{
                {"JobConf7", "Test 1.7"},
                {"JobConf8", ""},
                {"JobConf9", "Test 1.9"},
                {"JobConf10", "Test 1.10"},
                {"JobConf11", "Test 1.11"},
                {"JobConf12", "Test 1.12"},};
    }
    @Test(dataProvider = "getJobNegativeConf", enabled = true)
    public void jobFromDashboardNegative (String configType, String jobName){
        startPage.goToDashboard()
                .createNewJob()
                .createNewJob(
                        JobConfigurationFactory.getConfiguration(configType),
                        jobName,
                        DASHBOARD_PAGE);

        Assert.assertTrue(startPage.checkMessageForCase(configType));
    }
}
