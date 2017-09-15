package pageobject.header;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.Page;
import pageobject.clients.Clients;
import pageobject.dashboard.Dashboard;
import pageobject.jobsOverview.JobsOverview;
import pageobject.settings.Settings;
import pageobject.calendar.CalendarP;
import pageobject.payments.Payments;
import pageobject.singIn.SingIn;

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

    @FindBy(xpath = "//a[@data-link='logout']")
    private WebElement logoutLink;

    public Header(WebDriver webDriver) {
        super(webDriver);
    }

    public Settings goToSettingsSection(){
        waitForElement(settings,webDriver,10);
        clickOnElement(settings);
        return PageFactory.initElements(webDriver,Settings.class);
    }

    public Clients goToClientsSection(){
        waitForElement(client,webDriver,10);
        clickOnElement(client);
        return PageFactory.initElements(webDriver,Clients.class);
    }

    public JobsOverview goToJobsOverviewSection(){
        waitForElement(jobsOverview,webDriver,10);
        clickOnElement(jobsOverview);
        return PageFactory.initElements(webDriver,JobsOverview.class);
    }

    public Dashboard goToDashboard(){
        waitForElement(dashboard,webDriver,10);
        clickOnElement(dashboard);
        return PageFactory.initElements(webDriver,Dashboard.class);
    }

    public CalendarP goToCalendar(){
        waitForElement(calendar,webDriver,10);
        clickOnElement(calendar);
        return PageFactory.initElements(webDriver,CalendarP.class);
    }

    public Payments goToPayments(){
        waitForElement(payments,webDriver,10);
        clickOnElement(payments);
        return PageFactory.initElements(webDriver,Payments.class);
    }

    public SingIn logOut(){
        waitForElement(logoutLink, webDriver, 10);
        clickOnElement(logoutLink);
        return PageFactory.initElements(webDriver, SingIn.class);
    }

}
