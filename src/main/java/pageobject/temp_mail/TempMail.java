package pageobject.temp_mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.Page;
import pageobject.singUp.SingUp;


/**
 * Created by sombra-15 on 11.08.17.
 */

public class TempMail extends Page {

    @FindBy(xpath = ".//*[@id='mail']")
    private WebElement email;

    @FindBy(xpath = ".//*[@id='mails']")
    private WebElement mailsSection;

    @FindBy (xpath = ".//a[contains(text(), 'Studio Ninja')]")
    private WebElement mail;

    private String textEmail;

    public TempMail(WebDriver webDriver) {
        super(webDriver);
    }

    public void confirmMail(){
        WebDriverWait wait = new WebDriverWait(webDriver, 120);
        wait.until(ExpectedConditions.elementToBeClickable(mail));
        clickOnElement(mail);
        webDriver.findElement(By.xpath("//a[contains(text(), 'Not a robot')]")).click();
    }

    public SingUp returnNewEMailAndGoBackToSN(){
        SingUp singUp = PageFactory.initElements(webDriver, SingUp.class);
        singUp.setTempEmail(email.getAttribute("value"));
        return singUp;
    }


}
