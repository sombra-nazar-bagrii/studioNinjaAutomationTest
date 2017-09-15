package tests;

import org.testng.annotations.Test;
import pageobject.Page;

/**
 * Created by sombra-15 on 12.09.17.
 */
public class CreateJob extends TestBase{

    /*
     Name of methods -- createJobWith_
     numberOfClients         "with how many clients (client/clients)"_
     createdOrExisted        "client/s should be chooses from existed or create new(new/exist)"_
     typeOfJobDuration       "time of shoot (AllDay/Time/No Date)"_
     typeOfWorkflow          "with workflow (default/create)"
    */

    @Test(priority = 1, enabled = false)
    public void jobFromDashboard(){
        startPage.goToDashboard()
                .createNewJob()
                .createJob(
                        Page.numberOfClients,
                        Page.createOrExisted,
                        Page.typeOfJobDuration,
                        Page.typeOfWorkflow,
                        "Job from dashboard");
    }

    @Test (priority = 2, enabled = false)
    public void jobFromJobsSection(){
        startPage.goToJobsOverviewSection()
                .addNewJob()
                .createJob(
                        Page.numberOfClients,
                        Page.createOrExisted,
                        Page.typeOfJobDuration,
                        Page.typeOfWorkflow,
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
                        Page.numberOfClients,
                        Page.createOrExisted,
                        Page.typeOfJobDuration,
                        Page.typeOfWorkflow,
                        "Job from client page");
    }

    @Test (priority = 3, enabled = false)
    public void jobFromClientEmail(){
        startPage.goToClientsSection();
    }
}
