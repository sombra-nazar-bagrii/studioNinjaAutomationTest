package pageobject.modalForms;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pageobject.JobConfiguration;
import pageobject.Page;
import pageobject.jobsOverview.SomeJobPage;

/**
 * Created by sombra-15 on 08.09.17.
 */

public class AddNewJobMo extends Page {

    // TODO static
    private final String CLIENT_NAME = "Client";

    @FindBy(css = "[placeholder='Start entering client name']")
    private WebElement chooseClientFirst;

    @FindBy(css = "[ng-model='job.secondaryContact']")
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

    @FindBy(id = "noDateJob")
    private WebElement noDateCheckBox;

    @FindBy(xpath = ".//input[contains(@ng-model, 'job.startDate')]/following-sibling::input")
    private WebElement dateFrom;

    @FindBy(xpath = ".//input[contains(@ng-model, 'job.startDate')]")
    private WebElement dateForCheck;

    @FindBy(xpath = ".//input[contains(@ng-model, 'job.finishDate')]/following-sibling::input")
    private WebElement dateTo;

    @FindBy(xpath = "(.//*[@aria-label = 'Today'])[1]")
    private WebElement todayButton;

    @FindBy(xpath = ".//*[@id = 'checkbox-squared-all-job-day-event']//following-sibling::label")
    private WebElement allDateCheckBox;

    @FindBy(xpath = ".//div[contains(@dom-time, 'job_start_time')]/input")
    private WebElement timeFrom;

    @FindBy(xpath = ".//div[contains(@dom-time, 'job_finish_time')]/input")
    private WebElement timeTo;

    @FindBy(xpath = ".//a[@data-action='decrementHour']")
    private WebElement arrowDown;

    @FindBy(xpath = ".//*[@id='AutocompleteJobModal']")
    private WebElement Location;

    @FindBy(xpath = "(.//*[starts-with(@id, 'taTextElement')])[1]")
    private WebElement jobNotes;

    @FindBy(xpath = "(.//*[starts-with(@id, 'taTextElement')])[3]")
    private WebElement jobNotesEmail;

    @FindBy (xpath = ".//*[contains(text(), 'Save Job')]")
    private WebElement saveJob;

    @FindBy (xpath = ".//*[@id = 'newJob']")
    private WebElement modalForm;

    @FindBy (xpath = ".//span[contains(@class, 'job-head__name')]")
    private WebElement jobNameFromPage;

    public AddNewJobMo(WebDriver webDriver) {
        super(webDriver);
    }

    public SomeJobPage createJob(JobConfiguration config, String jobnaMe, String fromWhere) throws NoSuchMethodError {

        // Process of choosing client, we can choose one client or two clients also we check if 'choose client' field empty, because if we click
        // add new job from some client page it will be already configured first client

        // TODO invert equals with constant
        // TODO split into several methods
        if(fromWhere.equals("clientPage") || fromWhere.equals("clientEmail")){
            if(!(isClientFieldWithValue())){
                System.out.println("field should be with client name and email info");
            }
            if (config.getCreateOrExisted().equals("exist")) {

                if (config.getNumberOfClients().equals("two")) {
                    chooseSecCllient();
                } else if (config.getNumberOfClients().equals("two the same")) {
                    chooseTheSameClient();
                }
            } else if (config.getCreateOrExisted().equals("new")) {
                createNewClient().createNewClient();
                waitForElement(modalForm, webDriver,10);
                if (config.getNumberOfClients().equals("two")) {
                    createNewSecondaryClient().createNewClient();
                    waitForElement(modalForm, webDriver,10);
                }
            }
        } else if(fromWhere.equals("Job") || fromWhere.equals("Dashboard") || fromWhere.equals("Calendar")|| fromWhere.equals("CalendarM")|| fromWhere.equals("CalendarW")){
            if (!(isClientFieldEmpty())){
                System.out.println("field should be empty field");
            }
            if (config.getCreateOrExisted().equals("exist")) {
                choosePrimaryClient();
                if (config.getNumberOfClients().equals("two")) {
                    chooseSecCllient();
                } else if (config.getNumberOfClients().equals("two the same")) {
                    chooseTheSameClient();
                }
            } else if (config.getCreateOrExisted().equals("new") && chooseClientFirst.getAttribute("class").endsWith("ng-empty")) {
                createNewClient().createNewClient();
                waitForElement(modalForm, webDriver,10);
                if (config.getNumberOfClients().equals("two")) {
                    createNewSecondaryClient().createNewClient();
                    waitForElement(modalForm, webDriver,10);
                }
            }
        }
            // Process of choosing type of workflow, we have two position - created workflow OR default

            if (config.getTypeOfWorkflow().equals("created")) {
                selectCreatedWorkflow();
            }

            // Process configuration of day duration
            //
        switch (fromWhere) {
            case "CalendarM":
                switch (config.getTypeOfJobDuration()) {
                    case "all day":
                        clickOnElement(allDateCheckBox);
                        break;
                    case "time":
                        clickOnElement(timeFrom);
                        break;
                    case "no date":
                        clickOnElement(noDateCheckBox);
                        break;
                    case "time before":
                        clickOnElement(timeFrom);
                        clickOnElement(timeTo);
                        clickOnElement(arrowDown);
                        clickOnElement(arrowDown);
                        break;
                    case "no all day box":
                        break;
                    default:
                        throw new NoSuchMethodError();
                }
                break;
            case "CalendarW":
                switch (config.getTypeOfJobDuration()) {
                    case "all day":
                        clickOnElement(allDateCheckBox);
                        break;
                    case "time":
                        break;
                    case "no date":
                        clickOnElement(noDateCheckBox);
                        break;
                    case "time before":
                        clickOnElement(timeTo);
                        clickOnElement(arrowDown);
                        clickOnElement(arrowDown);
                        break;
                    default:
                        throw new NoSuchMethodError();
                }
                break;
            default:
                switch (config.getTypeOfJobDuration()) {
                    case "all day":
                        chooseTodayDay();
                        sleepThread(500);
                        clickOnElement(allDateCheckBox);
                        break;
                    case "time":
                        chooseTodayDay();
                        clickOnElement(timeFrom);
                        break;
                    case "no date":
                        hardClick(webDriver,noDateCheckBox);
                        break;
                    case "null":

                        break;
                    case "time before":
                        chooseTodayDay();
                        clickOnElement(timeFrom);
                        clickOnElement(timeTo);
                        clickOnElement(arrowDown);
                        clickOnElement(arrowDown);
                        break;
                    case "no all day box":
                        chooseTodayDay();
                        break;
                    default:
                        throw new NoSuchMethodError();
                }
                break;
        }

            // Random string will be set on field 'Location' & 'Notes'

//            locationNotes();
        try {

            customSelectByIndex(jobType, getJobNum(jobnaMe));
        }catch (Exception e){
            customSelectByIndex(jobType, 1);
        }
            customClearAndSendValue(jobName, jobnaMe);

            // Save current Job

            clickOnElement(saveJob);
            sleepThread(5000);
            return PageFactory.initElements(webDriver, SomeJobPage.class);
        }

    private AddNewClientMo createNewClient() {
        clickOnElement(addNewClientButton);
        sleepThread(2000);
        return PageFactory.initElements(webDriver, AddNewClientMo.class);
    }

    private AddNewClientMo createNewSecondaryClient() {
        clickOnElement(addNewSecondClientButton);
        clickOnElement(addSecondaryClient);
        sleepThread(2000);
        return PageFactory.initElements(webDriver, AddNewClientMo.class);
    }

    private void choosePrimaryClient(){
        customSendKeys(chooseClientFirst, CLIENT_NAME);
        sleepThread(1000);
        customSendKeys(chooseClientFirst, "1");
        webDriver.findElement(By.xpath(".//strong[contains(text(), '" + CLIENT_NAME + "1" + "')]/..")).click();
    }

    private void chooseTheSameClient(){
        clickOnElement(addNewSecondClientButton);
        customClearAndSendValue(chooseSecField, CLIENT_NAME);
        sleepThread(1000);
        customSendKeys(chooseSecField, "1");
        webDriver.findElement(By.xpath(".//strong[contains(text(), '" + CLIENT_NAME+ "1" + "')]/..")).click();
    }

    private boolean isClientFieldEmpty(){
          return chooseClientFirst.getAttribute("class").endsWith("ng-empty");
    }

    private boolean isClientFieldWithValue(){
        return chooseClientFirst.getAttribute("class").endsWith("user-success");
    }

    private void chooseSecCllient(){
        clickOnElement(addNewSecondClientButton);
        customClearAndSendValue(chooseSecField, CLIENT_NAME);
        sleepThread(1000);
        customSendKeys(chooseSecField, "2");
        webDriver.findElement(By.xpath(".//strong[contains(text(), '" + CLIENT_NAME+ "2" + "')]/..")).click();
    }

    private void locationNotes(){
        customClearAndSendValue(Location, generateString());
        try {
            customClearAndSendValue(jobNotes, generateString());
        }catch (ElementNotInteractableException e){
            customClearAndSendValue(jobNotesEmail, generateString());
        }
    }

    private void chooseTodayDay( ){
        clickOnElement(dateFrom);
        clickOnElement(todayButton);
    }

    private int getJobNum(String name){
        int number = Integer.parseInt(name.substring(name.length() -1));
        return number;
    }

    private boolean isDateEmpty(){
        return dateForCheck.getAttribute("class").contains("ng-empty");
    }

    private void selectCreatedWorkflow() {
        Select createdW = new Select(workflow);
        // TODO consider throwing RuntimeException
        if (createdW.getOptions().size() == 1)
            System.out.println("Please create some workflow because now only default workflow available");
        else customSelectByIndex(workflow, 1);
    }
}

