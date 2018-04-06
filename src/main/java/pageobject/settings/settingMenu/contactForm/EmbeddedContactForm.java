package pageobject.settings.settingMenu.contactForm;

import ComfigurationClasses.ContactFormFactory;
import ComfigurationClasses.ContactFormField;
import ComfigurationClasses.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by sombramac-8 on 4/5/18.
 */
public class EmbeddedContactForm extends Page{

    private static final String INVALID_EMAIL_TYPE1 = "test";
    private static final String INVALID_EMAIL_TYPE2 = "test@test";

    @FindBy (xpath = ".//*[@id='submitButton']")
    private WebElement submitForm;

    @FindBy (xpath = ".//input[starts-with(@name, 'FIRST_NAME')]")
    private WebElement firstName;

    @FindBy (xpath = ".//input[starts-with(@name, 'EMAIL')]")
    private WebElement email;

    @FindBy (xpath = ".//input[starts-with(@name, 'PHONE')]")
    private WebElement phone;

    @FindBy (xpath = ".//select[starts-with(@name, 'JOB_TYPE')]")
    private WebElement jobType;

    @FindBy (xpath = ".//input[starts-with(@class, 'ws-date')]")
    private WebElement date;

    @FindBy (xpath = ".//*[@aria-label='Today']")
    private WebElement todayCalendarButton;

    @FindBy (xpath = ".//*[@aria-label='Clear']")
    private WebElement clearCalendarDate;

    @FindBy (xpath = ".//*[starts-with(@name, 'MESSAGE')]")
    private WebElement message;

    @FindBy (xpath = ".//input[starts-with(@name, 'JOB_LOCATION')]")
    private WebElement location;

    @FindBy (xpath = ".//input[starts-with(@name, 'LAST_NAME')]")
    private WebElement lastName;

    @FindBy (xpath = ".//input[starts-with(@name, 'ENQUIRY_SOURCE')]")
    private WebElement enqSource;

    @FindBy (xpath = ".//input[starts-with(@name, 'OTHER')]")
    private WebElement other;

    public EmbeddedContactForm(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkLabelsForCase( ){

    }

    private boolean checkRequiredLable(WebElement field){
        return isElementDisplayed(field.findElement(By.xpath("/following-sibling::div[1]")));
    }

    private void checkEmailValidation(){
        customClearAndSendValue(email,INVALID_EMAIL_TYPE1);
        clickOnElement(submitForm);
        if(!isElementDisplayed(email.findElement(By.xpath("/following-sibling::div[2]")))){
            throw new NoSuchElementException("Required message about invalid email type doesn't display");
        }
    }
}
