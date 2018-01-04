package tests.CreatingJob;

import Factory.JobConfigurationFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.TestBase;

import java.util.Random;

/**
 * Created by sombramac-8 on 12/28/17.
 */
public class CreatingJobFromCalendar extends TestBase {

    private final String PLACE1 = "CalendarM";
    private final String PLACE2 = "CalendarW";
    private final String PLACE3 = "Calendar";
    private final String month = "April";

    @DataProvider
    public Object[][] getJobPositiveConfig(){
        return new Object[][]{
                {"JobConf1", "Test 1."},
                {"JobConf2", "Test 2."},
                {"JobConf3", "Test 3."},
                {"JobConf4", "Test 4."},
                {"JobConf5", "Test 5."},
                {"JobConf6", "Test 6."},};
    }

    @Test(dataProvider = "getJobPositiveConfig", priority = 1)
    public void jobsFromCalendarPagePos(String configType, String jobName){
        startPage.goToCalendar()
                .createNewJobUsingButton()
                .createJob(
                        JobConfigurationFactory.getConfiguration(configType),
                        jobName + '4',
                        PLACE3);
        Assert.assertTrue(startPage.checkMessageForCase(configType));
    }

    @Test(dataProvider = "getJobPositiveConfig", priority = 3)
    public void jobsFromCalendarPageMonthPos(String configType, String jobName){
        startPage.goToCalendar()
                .createNewJobMonth(month)
                .createJob(
                        JobConfigurationFactory.getConfiguration(configType),
                        jobName + '5',
                        PLACE1);
        Assert.assertTrue(startPage.checkMessageForCase(configType));
    }

    @Test(dataProvider = "getJobPositiveConfig", priority = 5)
    public void jobsFromCalendarPageWeekPos(String configType, String jobName){
        startPage.goToCalendar()
                .createNewJobWeek()
                .createJob(
                        JobConfigurationFactory.getConfiguration(configType),
                        jobName + '6',
                        PLACE2);
        Assert.assertTrue(startPage.checkMessageForCase(configType));
    }

    @DataProvider
    public Object[][] getJobNegativeConf(){
        return new Object[][]{
                {"JobConf7", "Test 7."},
                {"JobConf8", ""},
                {"JobConf9", "Test 9."},
                {"JobConf10", "Test 10."},
                {"JobConf11", "Test 11."},
                {"JobConf12", "Test 12."},};
    }
    @Test(dataProvider = "getJobNegativeConf", priority = 2)
    public void jobsFromCalendarPageNeg (String configType, String jobName){
        startPage.goToCalendar()
                .createNewJobUsingButton()
                .createJob(
                        JobConfigurationFactory.getConfiguration(configType),
                        jobName + '4',
                        PLACE3);
        Assert.assertTrue(startPage.checkMessageForCase(configType));
    }

    @DataProvider
    public Object[][] getJobNegativeConfM(){
        return new Object[][]{
                {"JobConf7", "Test 7."},
                {"JobConf8", ""},
                {"JobConf10", "Test 10."},
                {"JobConf11", "Test 11."},
                {"JobConf12", "Test 12."},};
    }

    @Test(dataProvider = "getJobNegativeConfM", priority = 4)
    public void jobsFromCalendarPageMonthNeg (String configType, String jobName){
        startPage.goToCalendar()
                .createNewJobMonth(month)
                .createJob(
                        JobConfigurationFactory.getConfiguration(configType),
                        jobName + '5',
                        PLACE1);
        Assert.assertTrue(startPage.checkMessageForCase(configType));
    }

    @DataProvider
    public Object[][] getJobNegativeConfW(){
        return new Object[][]{
                {"JobConf7", "Test 7."},
                {"JobConf8", ""},
                {"JobConf11", "Test 11."},
                {"JobConf12", "Test 12."},};
    }

    @Test(dataProvider = "getJobNegativeConfW", priority = 6)
    public void jobsFromCalendarPageWeekNeg (String configType, String jobName){
        startPage.goToCalendar()
                .createNewJobWeek()
                .createJob(
                        JobConfigurationFactory.getConfiguration(configType),
                        jobName + '6',
                        PLACE2);
        Assert.assertTrue(startPage.checkMessageForCase(configType));
    }
}
