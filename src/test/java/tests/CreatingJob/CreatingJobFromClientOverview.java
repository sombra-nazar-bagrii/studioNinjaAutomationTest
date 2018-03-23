package tests.CreatingJob;


import Factory.JobConfigurationFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.TestBase;

/**
 * Created by sombramac-8 on 12/26/17.
 */
public class CreatingJobFromClientOverview extends TestBase {

    @DataProvider
    public Object[][] getJobPositiveConfig(){
        return new Object[][]{
                {"JobConf1", "Test 3.1"},
                {"JobConf2", "Test 3.2"},
                {"JobConf3", "Test 3.3"},
                {"JobConf4", "Test 3.4"},
                {"JobConf5", "Test 3.5"},
                {"JobConf6", "Test 3.6"},};
    }

    @Test (dataProvider = "getJobPositiveConfig")
    public void jobsFromClientPagePos(String configType, String jobName){
        startPage.goToClientsSection()
                .goToSomeClientPage()
                .addNewJob()
                .createNewJob(
                        JobConfigurationFactory.getConfiguration(configType),
                        jobName,
                        CLIENT_OVERVIEW_PAGE);
        Assert.assertTrue(startPage.checkMessageForCase(configType));
    }

    @DataProvider
    public Object[][] getJobNegativeConf(){
        return new Object[][]{
                {"JobConf7", "Test 3.7"},
                {"JobConf8", ""},
                {"JobConf9", "Test 3.9"},
                {"JobConf10", "Test 3.10"},
                {"JobConf11", "Test 3.11"},
                {"JobConf12", "Test 3.12"},};
    }
    @Test(dataProvider = "getJobNegativeConf", enabled = true)
    public void jobsFromClientPageNeg (String configType, String jobName){
        startPage.goToClientsSection()
                .goToSomeClientPage()
                .addNewJob()
                .createNewJob(
                        JobConfigurationFactory.getConfiguration(configType),
                        jobName,
                        CLIENT_OVERVIEW_PAGE);
        Assert.assertTrue(startPage.checkMessageForCase(configType));
    }
}
