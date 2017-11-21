package pageobject.dashboard;

import Factory.JobConfigurationFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.Page;
import pageobject.jobsOverview.SomeJobPage;
import pageobject.modalForms.AddNewAppointmentMo;
import pageobject.modalForms.AddNewExtraShootMo;
import pageobject.modalForms.AddNewJobMo;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by sombra-15 on 12.07.17.
 */

public class Dashboard extends Page {

    private final static String JOB_NAME = "Job from Dashboard for checking upcoming section";

    @FindBy(xpath = ".//*[@ng-model='jobsLeadDateItem']")
    private WebElement leadsSelect;

    @FindBy(xpath = ".//*[@ng-model='jobsLeadDateItem']")
    private WebElement acceptedSelect;

    @FindBy(xpath = ".//*[@ng-model='jobsScheduledDateItem']")
    private WebElement scheduledSelect;

    @FindBy(xpath = ".//*[@ng-model='totalInvoicedDateItem']")
    private WebElement totalInvoiced;

    @FindBy(xpath = ".//*[@data-autotest-button='addNew']")
    private WebElement addNew;

    @FindBy(xpath = ".//*[@id='addNewModal']//*[@title = 'job']")
    private WebElement addJob;

    @FindBy(xpath = ".//*[@id='addNewModal']//*[@title = 'extra shoot']")
    private WebElement addExtrashoot;

    @FindBy(xpath = ".//*[@id='addNewModal']//*[@title = 'appointment']")
    private WebElement addAppointment;

    @FindBy(xpath = "(.//*[@id='table-first']//span)[2]")
    private WebElement noUpcomingLabel;

    @FindBy(xpath = "//*[contains(@class, 'dashboard-event-section')]")
    private List <WebElement> upcomingShoots;

/*
    @FindBy()
    private List <WebElement> jobWorkflowsAndTasks;

    @FindBy()
    private List <WebElement> overdueAndcomingPayments;
*/
    public Dashboard(WebDriver webDriver) {
        super(webDriver);
    }

    public AddNewJobMo createNewJob (){
        waitForElement(addNew, webDriver, 10);
        addNew.click();
        clickOnElement(addJob);
        return PageFactory.initElements(webDriver, AddNewJobMo.class);
    }

    public AddNewExtraShootMo createNewExtraShoot (String jobName) {
        waitForElement(addNew, webDriver, 10);
        addNew.click();
        clickOnElement(addExtrashoot);
        return PageFactory.initElements(webDriver, AddNewExtraShootMo.class);
    }

    public AddNewAppointmentMo createNewAppointment (){
        waitForElement(addNew, webDriver, 10);
        addNew.click();
        clickOnElement(addAppointment);
        return PageFactory.initElements(webDriver, AddNewAppointmentMo.class);
    }

    public boolean checkIfUpcomingSectionWorks(){
        return checkIfUpcomingSectionWorksJob();
    }

    private boolean checkIfUpcomingSectionWorksJob( ) {


        if (isElementDisplayed(noUpcomingLabel)) {
            createNewJob()
                    .createJob(
                            JobConfigurationFactory.getConfiguration("JobConf1"),
                            JOB_NAME);
            waitSomeSec(webDriver, 5);
            returnHeader()
                    .goToDashboard();
        }
        clickOnElement(upcomingShoots.get(0));
        waitSomeSec(webDriver, 5);
        if(isElementDisplayed(webDriver.findElement(By.xpath("(.//*[@id='main-wrapper']//h2)[1]")))){
            return true;
        } else return false;
    }

    public boolean checkIfElementDisplayedInUpcomingSection(){
        if(upcomingShoots.get(0).findElement(By.xpath("//span")).getText().equals(JOB_NAME)){
            System.out.println("Job displayed");
            return true;
        }else {
            System.out.println("Job doesn't display");
            return false;
        }
    }



/*
    public boolean checkIfUpcomingSectionWorksAppointmet(String appName){
    if (isElementDisplayed(noUpcomingLabel)){

        waitSomeSec(webDriver, 5);
        returnHeader()
                .goToDashboard();
    }

    public boolean checkIfUpcomingSectionWorksExtraShoot(String es){
    if (isElementDisplayed(noUpcomingLabel)){

        waitSomeSec(webDriver, 5);
        returnHeader()
                .goToDashboard();
    }

    public boolean checkIfJobWorkflowSectionWorks(){

    }

    public boolean checkIfPaymentSectionWorks(){

    }
*/
    public void checkAllStatesOfHeaderDropBoxes(){
        checkAllSelectedOptions(leadsSelect);
        checkAllSelectedOptions(acceptedSelect);
        checkAllSelectedOptions(scheduledSelect);
        checkAllSelectedOptions(totalInvoiced);
    }
}
