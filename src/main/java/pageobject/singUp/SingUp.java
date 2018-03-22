package pageobject.singUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.session.StripAnyPlatform;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pageobject.Page;
import pageobject.dashboard.Dashboard;
import pageobject.singIn.SingIn;
import pageobject.temp_mail.TempMail;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by sombra-15 on 09.08.17.
 */
public class SingUp extends Page {

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

     private static String TEMP_MAIL = "https://www.tempmailaddress.com/";
     private static String CURRENCY = "EUR - Euro";
     private static String TIME_ZONE = "(+02:00) Europe - Kiev";
     private static String USED_COMPANY_NAME = "";
     private static String PASS = "qweqwe";

    private ArrayList <String> credentials = new ArrayList();

    public SingUp(WebDriver webDriver) {
        super(webDriver);
    }



    protected void enterInfoOfNewUser(){
        String newEmail = goToTempMailPage().setupTempEmail();
        webDriver.get(HOME_URL + "signup");
        customClearAndSendValue(companyName, generateString());
        customClearAndSendValue(firstName, generateString());
        customClearAndSendValue(emailAddress, newEmail);
        customClearAndSendValue(password, PASS);
        customClearAndSendValue(confirmPassword, PASS);
        /*
        checkAllSelectedOptions(currency);
        checkAllSelectedOptions(timezone);
        */
        customSelectByVisibleText(currency,CURRENCY);
        customSelectByVisibleText(timezone,TIME_ZONE);
        clickOnElement(termsAndPolicyCheckBox);
        clickOnElement(singUpButton);
        sleepThread(5000);
        credentials.add(PASS);
        credentials.add(newEmail);

      /*  Integer sum = credentials
                .stream()
                .filter(str -> str.contains("a"))
                .peek(System.out::println)
                .filter(s -> !Objects.equals(s, "Nazar"))
                .mapToInt(Integer::valueOf)
                .flatMap()
                .sum();

        MyInterface myInterface = something -> "hellow world";
      */
    }
    public Dashboard singUpNewUser(){
        enterInfoOfNewUser();
        goToTempMailPage()
                .confirmMail()
                .enterByExistingUser(
                        credentials.get(1),
                        credentials.get(0),
                        false);

        return PageFactory.initElements(webDriver, Dashboard.class);
    }

    /*

    public boolean openTermsModal(){
    }

    public boolean openPolicyModal(){
    }

    */

    public TempMail goToTempMailPage() {
        webDriver.get(TEMP_MAIL);
        return PageFactory.initElements(webDriver, TempMail.class);
    }

}
