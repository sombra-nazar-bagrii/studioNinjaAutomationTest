package pageobject.modalForms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pageobject.Page;
import pageobject.jobsOverview.SomeJobPage;

/**
 * Created by sombra-15 on 08.09.17.
 */

public class AddNewJobMo extends Page {

    private final String CLIENT_NAME = "Test";

    @FindBy(xpath = ".//*[@ng-model = 'clients[0]']")
    private WebElement chooseClientFirst;

    @FindBy(xpath = ".//*[@ng-model = 'clients[1]']")
    private WebElement chooseSecField;

    @FindBy(xpath = ".//*[@data-autotest-button='addNewClientFirst']")
    private WebElement addNewClientButton;

    @FindBy (xpath = ".//*[@data-autotest-button='addSecondaryClient']")
    private WebElement addNewSecondClientButton;

    @FindBy(xpath = ".//*[@data-autotest-button='addNewClientSecond']")
    private WebElement addSecondaryClient;

    @FindBy(id = "job-name")
    private WebElement jobName;

    @FindBy(xpath = ".//*[@ng-model = 'job.workflow']")
    private WebElement workflow;

    @FindBy(id = "job-type")
    private WebElement jobType;

    @FindBy(xpath = ".//*[@id = 'checkbox-squared-has-date']/following-sibling::label")
    private WebElement noDateCheckBox;

    @FindBy(xpath = "(.//*[@placeholder ='dd/mm/yyyy'])[1]")
    private WebElement dateFrom;

    @FindBy(xpath = "(.//*[@placeholder ='dd/mm/yyyy'])[2]")
    private WebElement dateTo;

    @FindBy(xpath = "(.//*[@aria-label = 'Today'])[1]")
    private WebElement todayButton;

    @FindBy(id = "checkbox-squared-all-job-day-event")
    private WebElement allDateCheckBox;

    @FindBy(xpath = "(.//*[starts-with(@id, 'timepicker')])[1]")
    private WebElement timeFrom;

    @FindBy(xpath = "(.//*[starts-with(@id, 'timepicker')])[2]")
    private WebElement timeTo;

    @FindBy(id = "Autocomplete2")
    private WebElement Location;

    @FindBy(xpath = "(.//*[starts-with(@id, 'taTextElement')])[1]")
    private WebElement jobNotes;

    @FindBy (xpath = ".//*[contains(text(), 'Save Job')]")
    private WebElement saveJob;


    public AddNewJobMo(WebDriver webDriver) {
        super(webDriver);
    }

    public SomeJobPage createJob(String numberOfClients, String createOrExisted, String typeOfJobDuration, String typeOfWorkflow, String jobnaMe) throws NoSuchMethodError {

    // Process of choosing client, we can choose one client or two clients also we check if 'choose client' field empty, because if we click
    // add new job from some client page it will be already configured first client

        if (createOrExisted.trim().equalsIgnoreCase("exist") && chooseClientFirst.getAttribute("class").endsWith("ng-empty")) {
            choosePrimaryClient();
            if (numberOfClients.trim().equalsIgnoreCase("two")){
                chooseSecCllient();
            }

    // Process of creating new client, we can create one client or two clients also we check if 'choose client' field empty, because if we click
    // add new job from some client page it will be already configured first client

        } else if (createOrExisted.trim().equalsIgnoreCase("new") && chooseClientFirst.getAttribute("class").endsWith("ng-empty")) {
            createNewClient().createNewClient();
            if (numberOfClients.trim().equalsIgnoreCase("two")){
                createNewSecondaryClient().createNewClient();
            }
        }

    // Process of choosing type of workflow, we have two position - created workflow OR default

        if (typeOfWorkflow.trim().equalsIgnoreCase("create")){
            selectCreatedWorkflow();
        }

    // Process configuration of day duration, we have three options - job with 'all day' duration, job planed on some time and job with out date
    //

        if (typeOfJobDuration.trim().equalsIgnoreCase("all day")){
            chooseTodayDay();
            clickOnElement(allDateCheckBox);
        } else if (typeOfJobDuration.trim().equalsIgnoreCase("time")){
            chooseTodayDay();
            clickOnElement(timeFrom);
        } else if (typeOfJobDuration.trim().equalsIgnoreCase("no date")){
            clickOnElement(noDateCheckBox);
        } else {throw new NoSuchMethodError();}

    // Random string will be set on field 'Location' & 'Notes'

        locationNotes();
        customClearAndSendValue(jobName, jobnaMe);

    // Save current Job

        clickOnElement(saveJob);
        try {Thread.sleep(1000);}
        catch (InterruptedException e) {e.printStackTrace();}

        return PageFactory.initElements(webDriver, SomeJobPage.class);
    }

    private AddNewClientMo createNewClient() {
        clickOnElement(addNewClientButton);
        return PageFactory.initElements(webDriver, AddNewClientMo.class);
    }

    private AddNewClientMo createNewSecondaryClient() {
        clickOnElement(addNewSecondClientButton);
        clickOnElement(addSecondaryClient);
        return PageFactory.initElements(webDriver, AddNewClientMo.class);
    }

    private void choosePrimaryClient(){
        customClearAndSendValue(chooseClientFirst, CLIENT_NAME);
        customSendKeys(chooseClientFirst, "1");
        webDriver.findElement(By.xpath(".//strong[contains(text(), '" + CLIENT_NAME + "1" + "')]/..")).click();
    }

    private void chooseSecCllient(){
        clickOnElement(addNewSecondClientButton);
        customClearAndSendValue(chooseSecField, CLIENT_NAME);
        customSendKeys(chooseSecField, "2");
        webDriver.findElement(By.xpath(".//strong[contains(text(), '" + CLIENT_NAME+ "2" + "')]/..")).click();
    }

    private void locationNotes(){
        customClearAndSendValue(Location, generateString());
        customClearAndSendValue(jobNotes, generateString());
    }

    private void chooseTodayDay(){
        clickOnElement(dateFrom);
        clickOnElement(todayButton);
    }

    private void selectCreatedWorkflow() {
        Select createdW = new Select(workflow);
        if (createdW.getOptions().size() == 1)
            System.out.println("Please create some workflow because now only default workflow available");
        else customSelectByIndex(workflow, 1);
    }
}
