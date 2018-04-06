package pageobject.settings;

import org.openqa.selenium.WebDriver;
import ComfigurationClasses.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.settings.settingMenu.*;
import pageobject.settings.settingMenu.contactForm.ContactFormPage;

/**
 * Created by sombra-15 on 12.07.17.
 */



public class SettingsGeneralPage extends Page {

    @FindBy(xpath = ".//*[@data-autotest-section='accountAndSubscription']")
    private WebElement accountAndSubscription;

    @FindBy(xpath = ".//*[@data-autotest-section='companyInformation']")
    private WebElement companyInformation;

    @FindBy(xpath = ".//*[@data-autotest-section='companyUsers']")
    private WebElement companyUsers;

    @FindBy(xpath = ".//*[@data-autotest-section='currencyAndTaxes']")
    private WebElement currencyAndTaxes;

    @FindBy(xpath = ".//*[@data-autotest-section='paymentMethods']")
    private WebElement paymentMethods;

    @FindBy(xpath = ".//*[@data-autotest-section='paymentSchedulesAndReminders']")
    private WebElement paymentSchedulesAndReminders;

    @FindBy(xpath = ".//*[@data-autotest-section='calendarSettings']")
    private WebElement calendarSettings;

    @FindBy(xpath = ".//*[@data-autotest-section='jobTypes']")
    private WebElement jobTypes;

    @FindBy(xpath = ".//*[@data-autotest-section='productsAndPackages']")
    private WebElement productsAndPackages;

    @FindBy(xpath = ".//*[@data-autotest-section='emailSettingsAndTemplates']")
    private WebElement emailSettingsAndTemplates;

    @FindBy(xpath = ".//*[@data-autotest-section='contractTemplates']")
    private WebElement contractTemplates;

    @FindBy(xpath = ".//*[@data-autotest-section='questionnareTemplates']")
    private WebElement questionnareTemplates;

    @FindBy(xpath = ".//*[@data-autotest-section='workflows']")
    private WebElement workflows;

    @FindBy(xpath = ".//*[@data-autotest-section='contractForm']")
    private WebElement contractForm;

    @FindBy(xpath = ".//*[@href='/settings/refer']")
    private WebElement referFriend;

    @FindBy(xpath = ".//*[@href='/settings/integrations']")
    private WebElement integrations;

    public SettingsGeneralPage(WebDriver webDriver) {
        super(webDriver);
    }

    public AccountAndSubscriptionPage goToAccountAndSubscriptionPage(){
        clickOnElement(accountAndSubscription);
        return PageFactory.initElements(webDriver, AccountAndSubscriptionPage.class);
    }

    public CompanyInformationPage goToCompanyInformationPage(){
        clickOnElement(companyInformation);
        return PageFactory.initElements(webDriver, CompanyInformationPage.class);
    }

    public CompanyUsersPage goToCompanyUsersPage(){
        clickOnElement(companyUsers);
        return PageFactory.initElements(webDriver, CompanyUsersPage.class);
    }

    public CurrencyAndTaxesPage goToCurrencyAndTaxesPage(){
        clickOnElement(currencyAndTaxes);
        return PageFactory.initElements(webDriver, CurrencyAndTaxesPage.class);
    }

    public PaymentMethodsPage goToPaymentMethodsPage(){
        clickOnElement(paymentMethods);
        return PageFactory.initElements(webDriver, PaymentMethodsPage.class);
    }

    public ContactFormPage goToContractFormPage(){
        clickOnElement(contractForm);
        return PageFactory.initElements(webDriver, ContactFormPage.class);
    }
/*
    public PaymentMethodsPage goToPaymentMethodsPage(){
        clickOnElement(paymentMethods);
        return PageFactory.initElements(webDriver, PaymentMethodsPage.class);
    }

    public PaymentMethodsPage goToPaymentMethodsPage(){
        clickOnElement(paymentMethods);
        return PageFactory.initElements(webDriver, PaymentMethodsPage.class);
    }

    public PaymentMethodsPage goToPaymentMethodsPage(){
        clickOnElement(paymentMethods);
        return PageFactory.initElements(webDriver, PaymentMethodsPage.class);
    }

    public PaymentMethodsPage goToPaymentMethodsPage(){
        clickOnElement(paymentMethods);
        return PageFactory.initElements(webDriver, PaymentMethodsPage.class);
    }

    public PaymentMethodsPage goToPaymentMethodsPage(){
        clickOnElement(paymentMethods);
        return PageFactory.initElements(webDriver, PaymentMethodsPage.class);
    }

    public PaymentMethodsPage goToPaymentMethodsPage(){
        clickOnElement(paymentMethods);
        return PageFactory.initElements(webDriver, PaymentMethodsPage.class);
    }

    public PaymentMethodsPage goToPaymentMethodsPage(){
        clickOnElement(paymentMethods);
        return PageFactory.initElements(webDriver, PaymentMethodsPage.class);
    }
   */
}
