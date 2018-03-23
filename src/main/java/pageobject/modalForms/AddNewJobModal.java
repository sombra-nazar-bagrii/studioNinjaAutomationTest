package pageobject.modalForms;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pageobject.JobConfiguration;
import pageobject.Page;
import pageobject.jobsOverview.JobProfilePage;

/**
 * Created by sombra-15 on 08.09.17.
 */

public class AddNewJobModal extends Page {

    // TODO static
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
    private WebElement jobNameLocator;

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

    public AddNewJobModal(WebDriver webDriver) {
        super(webDriver);
    }

    public JobProfilePage createNewJob(JobConfiguration configuration, String jobName, String fromWhere){
        chooseClientConfiguration(configuration, fromWhere);
        jobDetailsSection(configuration, jobName);
        mainShootSection(configuration, fromWhere);
        //locationNotes();
        clickOnElement(saveJob);
        sleepThread(5000);
        return PageFactory.initElements(webDriver, JobProfilePage.class);
    }

    private void chooseClientConfiguration(JobConfiguration configuration, String fromWhere){

            if (configuration.getCreateOrExisted().equals("exist")) {
                if((!CLIENT_OVERVIEW_PAGE.equals(fromWhere)) || (!CLIENT_SEND_MODAL.equals(fromWhere))) {
                    choosePrimaryClient();
                }
                if (configuration.getNumberOfClients().equals("two")) {
                    chooseSecCllient();
                } else if (configuration.getNumberOfClients().equals("two the same")) {
                    chooseTheSameClient();
                }
            } else if (configuration.getCreateOrExisted().equals("new")) {
                goToAddNewClientModal().createNewClient();
                waitForElement(modalForm, webDriver,10);
                if (configuration.getNumberOfClients().equals("two")) {
                    goToNewSecondaryClientModal().createNewClient();
                    waitForElement(modalForm, webDriver,10);
                }
            }
    }

    private void jobDetailsSection(JobConfiguration configuration, String jobName){
        // Job name
        customClearAndSendValue(jobNameLocator, jobName);

        // Job type
        try {
            customSelectByIndex(jobType, getJobNumber(jobName));
        }catch (IndexOutOfBoundsException e){
            customSelectByIndex(jobType, 1);
        }

        // Workflow
        if (configuration.getTypeOfWorkflow().equals("created")) {
            selectCreatedWorkflow();
        }
    }

    private void mainShootSection(JobConfiguration configuration, String fromWhere){
        if(CALENDAR_MONTH_SECTION.equals(fromWhere)){
            //creating job from month page in calendar
            switch (configuration.getTypeOfJobDuration()) {
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
                    selectTimeDuration();
                    break;
                case "no all day box":
                    break;
                default:
                    throw new InvalidElementStateException("You can't create event with this configuration from " + CALENDAR_MONTH_SECTION);
            }
        } else if(CALENDAR_WEEK_SECTION.equals(fromWhere)){
            //creating job from week page in calendar
            switch (configuration.getTypeOfJobDuration()) {
                case "all day":
                    clickOnElement(allDateCheckBox);
                    break;
                case "time":
                    break;
                case "no date":
                    clickOnElement(noDateCheckBox);
                    break;
                case "time before":
                    selectTimeDuration();
                    break;
                default:
                    throw new NoSuchMethodError("You can't create event with this configuration from " + CALENDAR_WEEK_SECTION);
            }
        } else {
            // creating job from other places (Dashboard, Jobs Overview, Client Profile page, Modal form send email to client)
            switch (configuration.getTypeOfJobDuration()) {
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
                    selectTimeDuration();
                    break;
                case "no all day box":
                    chooseTodayDay();
                    break;
                default:
                    throw new NoSuchMethodError("You can't create event with this configuration from " + fromWhere);
            }
        }
    }

    private void selectTimeDuration(){
            clickOnElement(timeFrom);
            clickOnElement(timeTo);
            clickOnElement(arrowDown);
            clickOnElement(arrowDown);
    }

    private AddNewClientModal goToAddNewClientModal() {
        clickOnElement(addNewClientButton);
        sleepThread(2000);
        return PageFactory.initElements(webDriver, AddNewClientModal.class);
    }

    private AddNewClientModal goToNewSecondaryClientModal() {
        clickOnElement(addNewSecondClientButton);
        clickOnElement(addSecondaryClient);
        sleepThread(2000);
        return PageFactory.initElements(webDriver, AddNewClientModal.class);
    }

    private void choosePrimaryClient(){
        enterClientData(chooseClientFirst, 1);
    }

    private void chooseTheSameClient(){
        clickOnElement(addNewSecondClientButton);
        enterClientData(chooseSecField, 1);
    }

    private void chooseSecCllient(){
        clickOnElement(addNewSecondClientButton);
        enterClientData(chooseSecField, 2);
    }

    private void enterClientData(WebElement field, Integer clientNumber){
        customClearAndSendValue(field, CLIENT_NAME);
        sleepThread(1000);
        customSendKeys(field, clientNumber.toString());
        webDriver.findElement(By.xpath(".//strong[contains(text(), '" + CLIENT_NAME + clientNumber.toString() + "')]/..")).click();
    }

    private boolean isClientAlreadyAssigned(){
        return chooseClientFirst.getAttribute("class").endsWith("user-success");
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

    private int getJobNumber(String name){
        return Integer.parseInt(name.substring(name.length() -1));
    }

    private boolean isDateEmpty(){
        return dateForCheck.getAttribute("class").contains("ng-empty");
    }

    private void selectCreatedWorkflow() {
        Select createdWorkflow = new Select(workflow);
        // TODO consider throwing RuntimeException
        if (createdWorkflow.getOptions().size() == 1)
            throw new NoSuchElementException("Created workflow doesn't display in drop-down menu");
        customSelectByIndex(workflow, 1);
    }
}

