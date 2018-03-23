package pageobject.clients;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.Page;
import pageobject.modalForms.AddNewJobModal;

/**
 * Created by sombra-15 on 14.09.17.
 */
public class SendEmailFromClientsOverviewModal extends Page {

    @FindBy(xpath = ".//*[@id = 'sendContactEmailModal']//*[@data-autotest-button='close']")
    private WebElement closeBut;

    @FindBy(xpath = ".//*[starts-with(@ng-click , 'addNewJob')]")
    private WebElement addANewJob;

    @FindBy(xpath = ".//*[@ng-model='sendMessageData.emailTo']")
    private WebElement emailTo;

    @FindBy(xpath = ".//*[contains(text(), 'Add Cc')]")
    private WebElement addCC;

    @FindBy(id = "sendMessageData_emailCc")
    private WebElement cC;

    @FindBy(xpath = ".//*[@ng-model='mailData.choosenEmail']")
    private WebElement emailTemplate;

    @FindBy(xpath = ".//*[@id='sendContactEmailModal']//*[@ng-model = 'model']")
    private WebElement subject;

    @FindBy(xpath = "(.//*[starts-with(@id , 'taTextElement')])[2]")
    private WebElement messageField;

    @FindBy(xpath = ".//*[@id='sendContactEmailModal']//*[@ng-disabled='disableBtnOnSend']")
    private WebElement sendEmail;

    @FindBy(xpath = ".//*[@id = 'newJob']")
    private WebElement newJobModal;

    public SendEmailFromClientsOverviewModal(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillAllModalFields(){
         if (getElementOptionsSize(emailTemplate) >1){

         }
        if(modalsWithCC){
            clickOnElement(addCC);
            customClearAndSendValue(cC, generateString() + '@' +generateString()+ ".com");
        }

    }

    private int getElementOptionsSize(final WebElement webElement){
        return webElement.findElements(By.tagName("option")).size();
    }

    public AddNewJobModal addNewJob(){
        sleepThread(1000);
        clickOnElement(addANewJob);
        waitForElement(newJobModal, webDriver, 20);
        return PageFactory.initElements(webDriver, AddNewJobModal.class);
    }
}

