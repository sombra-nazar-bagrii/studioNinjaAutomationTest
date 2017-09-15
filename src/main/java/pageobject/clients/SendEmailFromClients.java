package pageobject.clients;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pageobject.Page;

import java.util.List;

/**
 * Created by sombra-15 on 14.09.17.
 */
public class SendEmailFromClients extends Page {

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

    public SendEmailFromClients(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillAllModFields(){
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
}

