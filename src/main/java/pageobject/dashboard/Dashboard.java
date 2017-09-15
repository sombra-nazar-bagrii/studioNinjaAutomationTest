package pageobject.dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.Page;
import pageobject.modalForms.AddNewAppointmentMo;
import pageobject.modalForms.AddNewExtraShootMo;
import pageobject.modalForms.AddNewJobMo;

import java.util.List;

/**
 * Created by sombra-15 on 12.07.17.
 */

public class Dashboard extends Page {

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


}
