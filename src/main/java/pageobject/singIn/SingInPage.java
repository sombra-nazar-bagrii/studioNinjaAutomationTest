package pageobject.singIn;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.Page;
import pageobject.forgotPassword.ForgotPasswordPage;
import pageobject.singUp.SingUpPage;
import static java.lang.System.*;

/**
 * Created by sombra-15 on 14.07.17.
 */

public class SingInPage extends Page {

  //  public static final Logger LOGGER = LoggerFactory.getLogger(Page.class);

    @FindBy(xpath = ".//*[@data-autotest-field='email']")
    private WebElement email;

    @FindBy(xpath = ".//*[@data-autotest-field='password']")
    private WebElement password;

    @FindBy(xpath = ".//*[@data-autotest-button='signIn']")
    private WebElement singInButton;

    @FindBy(xpath = ".//*[@class = 'forgot-password']")
    private WebElement forgotPassword;

    @FindBy(xpath = "//*[@id = 'checkbox-squared-stay-signed-in']/following-sibling::label")
    private WebElement staySignedIn;

    @FindBy(xpath = ".//*[@data-autotest-link='signUp']")
    private WebElement singUpButton;

    private static String TITLE = "Houston, we have a problem!";
    private static String MESSAGE = "Incorrect email or password.";

    public SingInPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void enterByExistingUser(String mail, String pass, boolean stayIn){
        customClearAndSendValue(email, mail);
        customClearAndSendValue(password, pass);
        if(stayIn)
        {
            clickOnElement(staySignedIn);
        }
        clickOnElement(staySignedIn);
         try {
            singInButton.click();
            Thread.sleep(4000);
        } catch (Exception e){
             throw new InvalidArgumentException("Invalid user details");
        }
    }

    public boolean checkIfErrorMessageCorrect(String mail, String pass){
        customClearAndSendValue(email, mail + 'q');
        customClearAndSendValue(password, pass + 'q');
        singInButton.click();
        if (isToasterValid(getTosterTitle(), getTosterMessage(), ERROR_TYPE)) {
            out.println("Error message valid!");
            return true;
        }
        else{
            out.println("Error message invalid!");
            return false;
        }
    }

    public SingUpPage openSingUpPage (){
        waitForElement(singUpButton, webDriver,5);
        clickOnElement(singUpButton);
        return PageFactory.initElements(webDriver, SingUpPage.class);
    }

    public ForgotPasswordPage goToForgotPasswordPage(){
        waitForElement(forgotPassword, webDriver, 5);
        clickOnElement(forgotPassword);
        return PageFactory.initElements(webDriver, ForgotPasswordPage.class);
    }

}