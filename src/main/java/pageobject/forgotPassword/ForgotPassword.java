package pageobject.forgotPassword;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject.Page;

/**
 * Created by sombra-15 on 09.08.17.
 */
public class ForgotPassword extends Page {

    @FindBy (id = "email")
    private WebElement email;

    @FindBy (xpath = "//*[contains(text(),'Submit')]")
    private WebElement submit;

    @FindBy (xpath = ".//*[@href='/login']")
    private WebElement back;

    public ForgotPassword(WebDriver webDriver) {super(webDriver);}


}
