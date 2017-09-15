package tests;

import org.testng.annotations.Test;
import Factory.JobConfigurationFactory;

/**
 * Created by sombra-15 on 12.09.17.
 */
public class CreateJob extends TestBase{

    @Test(priority = 1, enabled = false)
    public void jobFromDashboard(){
        startPage.goToDashboard()
                .createNewJob()
                .createJob(
                        JobConfigurationFactory.getConfiguration("default"),
                        "Job from dashboard");
    }
/*
    @Test(priority = 1, enabled = false)
    public void jobFromDashboard(){
        startPage.goToDashboard()
                .createNewJob()
                .createJob(
                        JobConfigurationFactory.getConfiguration("config2"),
                        "Job from dashboard");
    }
*/
    @Test (priority = 2, enabled = false)
    public void jobFromJobsSection(){
        startPage.goToJobsOverviewSection()
                .addNewJob()
                .createJob(
                        JobConfigurationFactory.getConfiguration("conf2"),
                        "Job from jobs overview");
    }

    @Test (priority = 3, enabled = false)
    public void jobFromCalendar(){

    }

    @Test (enabled = true)
    public void jobFromClientsOverview(){

        startPage.goToClientsSection()
                .addNewClient()
                .createNewClient()
                .goToSomeClientPage()
                .addNewJob()
                .createJob(
                        JobConfigurationFactory.getConfiguration("conf3"),
                        "Job from client page");
    }

    @Test (priority = 3, enabled = false)
    public void jobFromClientEmail(){
        startPage.goToClientsSection();
    }
}
