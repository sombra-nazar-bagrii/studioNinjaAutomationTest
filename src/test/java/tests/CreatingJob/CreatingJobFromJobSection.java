package tests.CreatingJob;

import Factory.JobConfigurationFactory;
import Factory.Messages;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.TestBase;

/**
 * Created by sombramac-8 on 12/26/17.
 */
public class CreatingJobFromJobSection extends TestBase {

    private final String PLACE = "Job";
    //Positive test cases
    @DataProvider
    public Object[][] getJobPositiveConf(){
        return new Object[][]{
                {"JobConf1","Test 2.1"},
                {"JobConf2", "Test 2.2"},
                {"JobConf3","Test 2.3"},
                {"JobConf4", "Test 2.4"},
                {"JobConf5", "Test 2.5"},
                {"JobConf6", "Test 2.6"},};
    }

    @Test (dataProvider = "getJobPositiveConf", priority = 1)
    public void jobFromJobOverviewSectionPositive(String configType, String jobName) {
        startPage.goToJobsOverviewSection()
                .addNewJob()
                .createJob(JobConfigurationFactory.getConfiguration(configType)
                        , jobName
                        , PLACE);
        Assert.assertTrue(startPage.checkMessageForCase(configType));

    }

    // Negative test cases
    @DataProvider
    public Object[][] getJobNegativeConf(){
        return new Object[][]{
                {"JobConf8", ""},
                {"JobConf9","Test 2.9"},
                {"JobConf10", "Test 2.10"},
                {"JobConf11", "Test 2.11"},
                {"JobConf12", "Test 2.12"},};
    }
    @Test(dataProvider = "getJobNegativeConf", priority = 2)
    public void jobFromJobOverviewSectionNegative (String configType, String jobName){
        startPage.goToJobsOverviewSection()
                .addNewJob()
                .createJob(
                        JobConfigurationFactory.getConfiguration(configType)
                        , jobName
                        , PLACE);
        Assert.assertTrue(startPage.checkMessageForCase(configType));

    }
}
