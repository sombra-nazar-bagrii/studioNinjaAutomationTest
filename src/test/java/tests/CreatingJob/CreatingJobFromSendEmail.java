package tests.CreatingJob;

import ComfigurationClasses.JobConfigurationFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.TestBase;

/**
 * Created by sombramac-8 on 12/28/17.
 */

public class CreatingJobFromSendEmail extends TestBase {

    @DataProvider
    public Object[][] getJobPositiveConfig(){
        return new Object[][]{
                {"JobConf1", "Test 4.1"},
                {"JobConf2", "Test 4.2"},
                {"JobConf3", "Test 4.3"},
                {"JobConf4", "Test 4.4"},
                {"JobConf5", "Test 4.5"},
                {"JobConf6", "Test 4.6"},};
    }

    @Test(dataProvider = "getJobPositiveConfig", priority = 1)
    public void jobsFromSendEmailPos(String configType, String jobName){
        startPage.goToClientsSection()
                .goToSendEmailMo()
                .addNewJob()
                .createNewJob(
                        JobConfigurationFactory.getConfiguration(configType),
                        jobName,
                        CLIENT_SEND_MODAL);
        Assert.assertTrue(startPage.checkMessageForCase(configType));
    }

    @DataProvider
    public Object[][] getJobNegativeConf(){
        return new Object[][]{
                {"JobConf8", ""},
                {"JobConf9", "Test 4.9"},
                {"JobConf10", "Test 4.10"},
                {"JobConf11", "Test 4.11"},
                {"JobConf12", "Test 4.12"},};
    }
    @Test(dataProvider = "getJobNegativeConf", priority = 2)
    public void jobsFromSendEmailNeg (String configType, String jobName){
        startPage.goToClientsSection()
                .goToSendEmailMo()
                .addNewJob()
                .createNewJob(
                        JobConfigurationFactory.getConfiguration(configType),
                        jobName,
                        CLIENT_SEND_MODAL);
        Assert.assertTrue(startPage.checkMessageForCase(configType));
    }
}
