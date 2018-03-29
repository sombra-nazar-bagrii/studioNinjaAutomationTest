package pageobject.singUp;

import ComfigurationClasses.ClientConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ComfigurationClasses.Page;
import pageobject.dashboard.DashboardPage;
import pageobject.temp_mail.TempMailPage;
import java.util.ArrayList;

/**
 * Created by sombra-15 on 09.08.17.
 */
public class SingUpPage extends Page {

     @FindBy(xpath = ".//*[@id='companyName']")
     private WebElement companyName;

     @FindBy(xpath = ".//*[@id='first_name']")
     private WebElement firstName;

     @FindBy(xpath = ".//*[@id='email']")
     private WebElement emailAddress;

     @FindBy(xpath = ".//*[@id='password']")
     private WebElement password;

     @FindBy(xpath = ".//*[@id='password_confirm']")
     private WebElement confirmPassword;

     @FindBy(xpath = ".//*[@data-autotest-select='currency']")
     private WebElement currency;
     //Select currency = new Select(currencyForSellect);

     @FindBy(xpath = ".//*[@data-autotest-select='timezone']")
     private WebElement timezone;

     @FindBy(xpath = ".//*[@data-autotest-checkbox='checkbox']")
     private WebElement termsAndPolicyCheckBox;

     @FindBy(xpath = ".//a[contains(text(), 'terms')]")
     private WebElement termsLink;

     @FindBy(xpath = ".//a[contains(text(), 'privacy policy')]")
     private WebElement policyLink;

     @FindBy(xpath = ".//*[@data-autotest-button='signUp']")
     private WebElement singUpButton;

    @FindBy(xpath = ".//*[@data-autotest-modal = 'helloModal']")
    private WebElement helloModal;

     private static final String TEMP_MAIL = "https://www.tempmailaddress.com/";
     private static final String CURRENCY = "EUR - Euro";
     private static final String TIME_ZONE = "(+02:00) Europe - Kiev";
     private static final String USED_COMPANY_NAME = "Sombra";
     private static final String PASS = "qweqwe";

    private ArrayList <String> credentials = new ArrayList();

    public SingUpPage(WebDriver webDriver) {
        super(webDriver);
    }

    private void configurationClientInformation(){
        String newEmail = goToTempMailPage().setupTempEmail();
        webDriver.get(HOME_URL + "signup");
        newClientInfo();
        customClearAndSendValue(emailAddress, newEmail);
        clickOnElement(singUpButton);
        waitWhileTrue(helloModal.getAttribute("style").endsWith("flex;"), webDriver, 10);
        credentials.add(PASS);
        credentials.add(newEmail);
    }

    private void caseWithShotrPassword(){
        newClientInfo();
        customClearAndSendValue(emailAddress, ClientConfiguration.getFirstName() + "@" + ClientConfiguration.getLastName() + ".com");

    }

    public DashboardPage singUpNewUser(){
        configurationClientInformation();
        goToTempMailPage()
                .confirmMail()
                .enterByExistingUser(
                        credentials.get(1),
                        credentials.get(0),
                        false);
        return PageFactory.initElements(webDriver, DashboardPage.class);
    }

    private void newClientInfo(){
        customClearAndSendValue(companyName, generateString());
        customClearAndSendValue(firstName, generateString());
        customClearAndSendValue(password, PASS);
        customClearAndSendValue(confirmPassword, PASS);
        customSelectByVisibleText(currency,CURRENCY);
        customSelectByVisibleText(timezone,TIME_ZONE);
        clickOnElement(termsAndPolicyCheckBox);
    }

    private TempMailPage goToTempMailPage() {
        webDriver.get(TEMP_MAIL);
        return PageFactory.initElements(webDriver, TempMailPage.class);
    }

}
