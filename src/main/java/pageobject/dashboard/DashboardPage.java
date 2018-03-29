package pageobject.dashboard;

import ComfigurationClasses.JobConfigurationFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ComfigurationClasses.Page;
import pageobject.modalForms.AddNewAppointmentModal;
import pageobject.modalForms.AddNewExtraShootModal;
import pageobject.modalForms.AddNewJobModal;
import pageobject.modalForms.AddNewLeadModal;

import java.util.List;

/**
 * Created by sombra-15 on 12.07.17.
 */

public class DashboardPage extends Page {

    private final static String JOB_NAME = "Job from DashboardPage for checking upcoming section";

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

    @FindBy(xpath = ".//*[@id='addNewModal']//*[@title = 'lead']")
    private WebElement addLead;

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
    public DashboardPage(WebDriver webDriver) {
        super(webDriver);
    }

    public AddNewJobModal createNewJob (){
        addNew.click();
        clickOnElement(addJob);
        sleepThread(2000);
        return PageFactory.initElements(webDriver, AddNewJobModal.class);
    }
    public AddNewLeadModal createNewLead (){
        addNew.click();
        clickOnElement(addLead);
        sleepThread(2000);
        return PageFactory.initElements(webDriver, AddNewLeadModal.class);
    }

    public AddNewExtraShootModal createNewExtraShoot (String jobName) {
        waitForElement(addNew, webDriver, 10);
        addNew.click();
        clickOnElement(addExtrashoot);
        return PageFactory.initElements(webDriver, AddNewExtraShootModal.class);
    }

    public AddNewAppointmentModal createNewAppointment (){
        waitForElement(addNew, webDriver, 10);
        addNew.click();
        clickOnElement(addAppointment);
        return PageFactory.initElements(webDriver, AddNewAppointmentModal.class);
    }

    public boolean checkIfUpcomingSectionWorks(){
        return checkIfUpcomingSectionWorksJob();
    }

    private boolean checkIfUpcomingSectionWorksJob( ) {
        if (isElementDisplayed(noUpcomingLabel)) {
            createNewJob()
                    .createNewJob(
                            JobConfigurationFactory.getConfiguration("JobConf1"),
                            JOB_NAME, "DashboardPage");
            waitSomeSec(webDriver, 5);
            returnHeader()
                    .goToDashboard();
        }
        clickOnElement(upcomingShoots.get(0));
        waitSomeSec(webDriver, 5);
        // TODO return isElementDisplayed
        return isElementDisplayed(webDriver.findElement(By.xpath("(.//*[@id='main-wrapper']//h2)[1]")));
    }

    public boolean checkIfElementDisplayedInUpcomingSection(){
        // TODO invert
        if(JOB_NAME.equals(upcomingShoots.get(0).findElement(By.xpath("//span")).getText())){
            return true;
        }else {
            throw new NoSuchElementException("Job doesn't display at dashboard");
        }
    }

    public void checkAllStatesOfHeaderDropBoxes(){
        checkAllSelectedOptions(leadsSelect);
        checkAllSelectedOptions(acceptedSelect);
        checkAllSelectedOptions(scheduledSelect);
        checkAllSelectedOptions(totalInvoiced);
    }
}
