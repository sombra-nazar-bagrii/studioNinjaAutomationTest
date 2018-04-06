package pageobject.settings.settingMenu.contactForm;

import ComfigurationClasses.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by sombramac-8 on 4/5/18.
 */
public class HostedContactForm extends Page {

    @FindBy (xpath = ".//input[@name='FIRST_NAME']")
    private WebElement firstName;

    @FindBy (xpath = ".//input[@name='EMAIL']")
    private WebElement email;

    @FindBy (xpath = ".//input[@name='PHONE']")
    private WebElement phone;

    @FindBy (xpath = ".//select[starts-with(@ng-options, ' jobType.name')]")
    private WebElement jobType;

    @FindBy (xpath = ".//input[starts-with(@class, 'ws-date')]")
    private WebElement date;

    @FindBy (xpath = ".//*[@aria-label='Today']")
    private WebElement todayButton;

    @FindBy (xpath = ".//*[@name='MESSAGE']")
    private WebElement message;

    @FindBy (xpath = ".//*[@name='JOB_LOCATION']")
    private WebElement location;

    @FindBy (xpath = ".//*[@name='LAST_NAME']")
    private WebElement lastName;

    @FindBy (xpath = ".//*[@name='ENQUIRY_SOURCE']")
    private WebElement enquirySource;

    @FindBy (xpath = ".//*[@name='OTHER']")
    private WebElement other;

    public HostedContactForm(WebDriver webDriver) {
        super(webDriver);
    }
}