package pageobject.clients;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Factory.JobConfigurationFactory;
import pageobject.Page;
import pageobject.jobsOverview.SomeJobPage;
import pageobject.modalForms.AddNewClientMo;
import pageobject.modalForms.AddNewJobMo;

import java.util.List;

/**
 * Created by sombra-15 on 13.09.17.
 */

public class SomeClient extends Page {

    private final By pathToIcons = By.xpath(".//h2[contains(@class, 'jobs-cell-title')]/i");

    @FindBy(xpath = ".//*[@ng-click = 'addNewJob()']")
    private WebElement addNewJob;

    @FindBy(xpath = ".//*[@sn-text = 'Edit client profile']")
    private WebElement editClientProfile;

    @FindBy(xpath = ".//*[@sn-text = 'Edit notes']")
    private WebElement editClNotes;

    @FindBy(xpath = ".//*[starts-with(@href, 'jobs/view/')]")
    private List<WebElement> jobs;

    @FindBy(xpath = ".//*[starts-with(@href, 'invoices/')]")
    private List <WebElement> invoicesView;

    @FindBy(xpath = ".//*[starts-with(@ng-repeat, 'invoice in' )]//h4")
    private List <WebElement> invoiceIDlist;

    @FindBy(xpath = ".//*[@ng-show = 'invoice.paidOn']")
    private List <WebElement> invoicePaidLable;

    @FindBy(xpath = ".//*[starts-with(@ng-click, 'getJobMailInfo')]")
    private List <WebElement> mailView;

    @FindBy(xpath = ".//*[starts-with(@ng-repeat, 'mailItem in mails' )]//div[1]")
    private List <WebElement> mailSubjects;

    @FindBy(xpath = ".//*[@id = 'newJob']")
    private WebElement newJobModal;

    public SomeClient(WebDriver webDriver) {
        super(webDriver);
    }

    public AddNewJobMo addNewJob(){
        waitForElements(pathToIcons, webDriver, 5);
        sleepThread(1000);
        clickOnElement(addNewJob);
        waitForElement(newJobModal, webDriver, 20);
        return PageFactory.initElements(webDriver, AddNewJobMo.class);
    }

    public boolean checkIfJobCreated(String jobName){
        int count = 0;
        for (WebElement someJob: jobs) {
            if (someJob.findElement(By.xpath(".//b[@class = 'ng-binding']")).getText().trim().equalsIgnoreCase(jobName)){
                count = 1;
                break;
            }
        }
        return count == 1;
    }

    public boolean checkIfInvoiceCreated(String invoiceID) {
        int count = 0;
        for (WebElement someInvoice: invoiceIDlist) {
            if (someInvoice.getText().trim().equalsIgnoreCase(invoiceID)){
                count = 1;
                break;
            }
        }
        return count == 1;
    }

    public boolean checkIfInvoicePaid() {
        int count = 0;
        for (WebElement paidOrNot: invoicePaidLable) {
            if (!(paidOrNot.getAttribute("class").endsWith("ng-hide"))) {
                count = 1;
                break;
            }
        }
        return count ==1;
    }

    public boolean checkIfMailSent (String subject){
        int count = 0;
        for (WebElement someSubject: mailSubjects) {
            if (someSubject.getText().trim().equalsIgnoreCase(subject)){
                count =1;
                break;
            }
        }
        return count == 1;
    }

    public AddNewClientMo editClientInfo (){
        waitForElement(editClientProfile, webDriver, 10);
        clickOnElement(editClientProfile);
        return PageFactory.initElements(webDriver, AddNewClientMo.class);
    }

    public ClientNotes editClientNotes(){
        waitForElement(editClNotes, webDriver, 10);
        clickOnElement(editClNotes);
        return PageFactory.initElements(webDriver, ClientNotes.class);
    }

    public SomeJobPage goToSomeJob(String jName){
        if (jobs.size() == 0){
            System.out.println("This user haven't any job");
            addNewJob().createJob(
                    JobConfigurationFactory.getConfiguration("conf1"),
                    "Job from client page", "clientPage");
        }
        return PageFactory.initElements(webDriver, SomeJobPage.class);
    }

    private void script (){
        JavascriptExecutor jas = (JavascriptExecutor) webDriver;
        ((JavascriptExecutor) webDriver).executeScript("document.addEventListener(\"DOMContentLoaded\", function(event) { console.log(\"DOM fully loaded and parsed\");});");
    }
}

