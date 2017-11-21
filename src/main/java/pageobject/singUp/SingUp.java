package pageobject.singUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

     private static String TEMP_MAIL = "https://temp-mail.org/uk/";
     private static String LOGIN_PAGE = "https://snfrankfurt.servehttp.com/login";
     private static String CURRENCY = "EUR - Euro";
     private static String TIME_ZONE = "(+02:00) Europe - Kiev";

    ArrayList <String> credentials = new ArrayList();

    public SingUp(WebDriver webDriver) {
        super(webDriver);
    }

    protected void enterInfoOfNewUser(){
        String parentHandle = webDriver.getWindowHandle();       // take handle for return back to SN
        String pass = generateString();
        customClearAndSendValue(companyName, generateString());
        customClearAndSendValue(firstName, generateString());
        String newEmail = goToTempMailPage().returnNewEMailAndGoBackToSN().getTempEmail();
        webDriver.close();
        webDriver.switchTo().window(parentHandle);               // go back to SN
        customClearAndSendValue(emailAddress, newEmail);
        customClearAndSendValue(password, pass);
        customClearAndSendValue(confirmPassword, pass);
        customSelectByVisibleText(currency,CURRENCY);
        customSelectByVisibleText(timezone,TIME_ZONE);
        clickOnElement(termsAndPolicyCheckBox);
        clickOnElement(singUpButton);
        credentials.add(pass);
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
        //webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String pageHandle = webDriver.getWindowHandle();
        goToTempMailPage().confirmMail();
        webDriver.close();
        webDriver.switchTo().window(pageHandle);
        webDriver.get(LOGIN_PAGE);
        SingIn singIn = PageFactory.initElements(webDriver, SingIn.class);
        singIn.enterByExistingUser(credentials.get(1), credentials.get(0));
        return PageFactory.initElements(webDriver, Dashboard.class);
    }

    /*

    public boolean openTermsModal(){
    }

    public boolean openPolicyModal(){
    }

    */

    public TempMail goToTempMailPage() {
        openNewWindow(TEMP_MAIL);
        return PageFactory.initElements(webDriver, TempMail.class);
    }

}
