package pageobject.header;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ComfigurationClasses.Page;
import pageobject.clients.ClientsOverviewPage;
import pageobject.dashboard.DashboardPage;
import pageobject.jobsOverview.JobsOverviewPage;
import pageobject.settings.SettingsGeneralPage;
import pageobject.calendar.CalendarPage;
import pageobject.payments.PaymentsOverviewPage;
import pageobject.singIn.SingInPage;

/**
 * Created by sombra17 on 13.12.16.
 */
public class Header extends Page {

    @FindBy(xpath="//a[@data-autotest-section='dashboardComputer']")//+
    private WebElement dashboard;

    @FindBy(xpath = "//a[@data-autotest-section='clientsComputer']")//+
    private  WebElement client;

    @FindBy(xpath = "//a[@data-autotest-section='jobsComputer']")//+
    private  WebElement jobsOverview;

    @FindBy(xpath = "//a[@data-autotest-section='settingsComputer']")//+
    private  WebElement settings;

    @FindBy(xpath = "//a[@data-autotest-section='calendarComputer']")//+
    private  WebElement calendar;

    @FindBy(xpath = "//a[@data-autotest-section='paymentsComputer']")//+
    private  WebElement payments;

    @FindBy(xpath = "//*[@class='selected-menu']//span[2]")
    private WebElement selectedSection;

    @FindBy(xpath = "")
    private WebElement dropDown;

    @FindBy(xpath = "//a[@data-link='logout']")
    private WebElement logoutLink;

    @FindBy(xpath = "")
    private WebElement accountAndSub;

    @FindBy(xpath = "")
    private WebElement help;

    public Header(WebDriver webDriver) {
        super(webDriver);
    }

    public SettingsGeneralPage goToSettingsSection(){
        waitForElement(settings,webDriver,10);
        clickOnElement(settings);
        return PageFactory.initElements(webDriver,SettingsGeneralPage.class);
    }

    public ClientsOverviewPage goToClientsSection(){
        waitForElement(client,webDriver,10);
        clickOnElement(client);
        return PageFactory.initElements(webDriver,ClientsOverviewPage.class);
    }

    public JobsOverviewPage goToJobsOverviewSection(){
        waitForElement(jobsOverview,webDriver,10);
        clickOnElement(jobsOverview);
        return PageFactory.initElements(webDriver,JobsOverviewPage.class);
    }

    public DashboardPage goToDashboard(){
        waitForElement(dashboard,webDriver,10);
        clickOnElement(dashboard);
        return PageFactory.initElements(webDriver,DashboardPage.class);
    }

    public CalendarPage goToCalendar(){
        waitForElement(calendar,webDriver,10);
        clickOnElement(calendar);
        return PageFactory.initElements(webDriver,CalendarPage.class);
    }

    public PaymentsOverviewPage goToPayments(){
        waitForElement(payments,webDriver,10);
        clickOnElement(payments);
        return PageFactory.initElements(webDriver,PaymentsOverviewPage.class);
    }

    public SingInPage logOut(){
        waitForElement(logoutLink, webDriver, 10);
        clickOnElement(logoutLink);
        return PageFactory.initElements(webDriver, SingInPage.class);
    }

    public String whichSectionIsSelected(){
        return selectedSection.getText();
    }

}
