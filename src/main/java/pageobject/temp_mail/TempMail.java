package pageobject.temp_mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.Page;
import pageobject.singIn.SingIn;
import pageobject.singUp.SingUp;


/**
 * Created by sombra-15 on 11.08.17.
 */

public class TempMail extends Page {

    @FindBy(xpath = ".//*[@href='/edit']")
    private WebElement editEmail;

    @FindBy(xpath = ".//*[@name='emailInput']")
    private WebElement newEmail;

    @FindBy(xpath = " .//*[@id='emailFormBtn']")
    private WebElement confirmNewEmail;

    @FindBy(xpath = ".//*[@id='email']")
    private WebElement email;

    @FindBy(xpath = "(.//*[@id='schranka']//*[contains(text(), 'Are you a real Ninja?')])[1]/..")
    private WebElement mailFromSN;

    @FindBy(xpath = ".//*[contains(@href, '" + HOME_URL + "')]")
    private WebElement confirmLink;

    @FindBy(xpath = "(.//*[contains(text(), '1 day')])[2]")
    private WebElement expirationTime;


    public TempMail(WebDriver webDriver) {
        super(webDriver);
    }

    public String setupTempEmail(){
        clickOnElement(editEmail);
        customSendKeys(newEmail, generateString());
        clickOnElement(confirmNewEmail);
        sleepThread(2000);
        clickOnElement(expirationTime);
        waitForElement(email, webDriver, 5);
        return email.getText();
    }

    public SingIn confirmMail(){
        WebDriverWait wait = new WebDriverWait(webDriver, 120);
        wait.until(ExpectedConditions.elementToBeClickable(mailFromSN));
        hardClick(webDriver, mailFromSN);
        System.out.println(confirmLink.getAttribute("href"));
        webDriver.get(confirmLink.getAttribute("href"));
        return PageFactory.initElements(webDriver, SingIn.class);
    }

    public SingUp returnNewEMailAndGoBackToSN(){
        SingUp singUp = PageFactory.initElements(webDriver, SingUp.class);
        singUp.setTempEmail(email.getAttribute("value"));
        return singUp;
    }


}
