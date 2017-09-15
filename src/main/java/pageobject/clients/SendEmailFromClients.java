package pageobject.clients;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject.Page;

/**
 * Created by sombra-15 on 14.09.17.
 */
public class SendEmailFromClients extends Page {

    @FindBy(xpath = ".//*[starts-with(@ng-click , 'addNewJob')]")
    private WebElement addANewJob;

    @FindBy(xpath = ".//*[@ng-model='sendMessageData.emailTo']")
    private WebElement emailTo;

    @FindBy(xpath = ".//*[contains(text(), 'Add Cc')]")
    private WebElement addCC;

    @FindBy(xpath = ".//*[@ng-model='mailData.choosenEmail']")
    private WebElement emailTemplate;

    @FindBy(xpath = "")
    private WebElement

    @FindBy(xpath = "")
    private WebElement

    @FindBy(xpath = "")
    private WebElement

    @FindBy(xpath = "")
    private WebElement

    @FindBy(xpath = "")
    private WebElement

    @FindBy(xpath = "")
    private WebElement

    @FindBy(xpath = "")
    private WebElement

    public SendEmailFromClients(WebDriver webDriver) {
        super(webDriver);
    }
}
