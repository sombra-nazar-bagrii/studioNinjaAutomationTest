package pageobject.calendar;

import ComfigurationClasses.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ComfigurationClasses.Page;
import pageobject.modalForms.AddNewAppointmentModal;
import pageobject.modalForms.AddNewExtraShootModal;
import pageobject.modalForms.AddNewJobModal;

import java.time.Month;
import java.time.MonthDay;
import java.util.*;


/**
 * Created by sombra-15 on 12.07.17.
 */
// TODO change name to *Page
public class CalendarPage extends Page {

    // TODO use java.time classes
    private Date date = new Date();
    // TODO make static final if not modifiable
    private String beforeMonth = "";
    private String afterMonth = "";
    private static final String weekView = "Week view";
    private static final String monthView = "Month view";

    // TODO move to method, because it's not threadsafe

    @FindBy(css = "[ng-click='addNew()']")
    private WebElement addNew;

    @FindBy(css = "[add='addJob({event:event})']")
    private WebElement addJob;

    @FindBy(xpath = ".//*[@id='addNewModal']//*[@title = 'extra shoot']")
    private WebElement addExtrashoot;

    @FindBy(xpath = ".//*[@id='addNewModal']//*[@title = 'appointment']")
    private WebElement addAppointment;

    @FindBy(xpath = "(.//*[starts-with(@ng-click,'toggleView')])[1]")
    private WebElement week;

    @FindBy(xpath = "(.//*[starts-with(@ng-click,'toggleView')])[2]")
    private WebElement month;

    @FindBy(xpath = ".//*[@id='main-wrapper']//*[contains(text(),'Today')]")
    private WebElement today;

    @FindBy(xpath = ".//*[@id='main-wrapper']//*[@title = 'Prev']")
    private WebElement previous;

    @FindBy(xpath = ".//*[@id='main-wrapper']//*[@title = 'Next']")
    private WebElement next;

    @FindBy(xpath = ".//*[(contains(@class, 'calendar-cell')) and not(contains(@class, 'no-curent-date'))]")
    private List<WebElement> daysFromCurrentMonth;

    @FindBy(xpath = ".//*[contains(@class, 'no-curent-date')]")
    private List <WebElement> daysNotFromCurrentMonth;

    @FindBy(xpath = ".//*[@id='main-wrapper']//ol//*[@href = '/']")
    private WebElement dashboadLink;

    @FindBy(xpath = ".//*[@id='main-wrapper']//ol/li[2]")
    private WebElement calendarLink;

    @FindBy(xpath = ".//*[@id='main-wrapper']//ol/li[3]")
    private WebElement viewType;

    @FindBy(xpath = "(.//*[@id='main-wrapper']//*[@ng-if = 'month'])[2]")
    private WebElement monthLabel;

    @FindBy(xpath = ".//*[@id = 'newJob']")
    private WebElement newJobModal;

    @FindBy(xpath = ".//*[contains(@id, 'calendar-table')]//*[contains(@class, 'pointer')]")
    private WebElement viewMore;

    @FindBy(xpath = ".//*[contains(@class, 'empty-td-body')]/..")
    private List<WebElement> weekList;

    @FindBy(xpath = ".//*[@id='addNewModal']")
    private WebElement addNewModalForm;

    public CalendarPage(WebDriver webDriver) {
        super(webDriver);
    }


    public AddNewJobModal createNewJobUsingButton (){
        addNew.click();
        sleepThread(10000);
        waitForElement(addJob, webDriver, 10);
        clickOnElement(addJob);
        return PageFactory.initElements(webDriver, AddNewJobModal.class);
    }

    public AddNewJobModal createNewJobUsingMonthSection(String month){
        waitForElement(addNew, webDriver, 10);
        monthNavigation(Utility.getRandDate(), month);
        clickOnElement(addJob);
        waitForElement(newJobModal, webDriver, 7);
        return PageFactory.initElements(webDriver, AddNewJobModal.class);
    }

    public AddNewJobModal createNewJobUsingWeekSection(){
        waitForElement(week, webDriver, 10);
        chooseWeekSection();
        clickOnElement(viewMore);
        clickOnElement(chooseJobTime(weekList));
        waitForElement(newJobModal, webDriver, 7);
        return PageFactory.initElements(webDriver, AddNewJobModal.class);
    }

    public AddNewExtraShootModal createNewExtraShootUsingButton (String jobName) {
        waitForElement(addNew, webDriver, 10);
        addNew.click();
        clickOnElement(addExtrashoot);
        return PageFactory.initElements(webDriver, AddNewExtraShootModal.class);
    }

    public AddNewAppointmentModal createNewAppointmentUsingButton (){
        waitForElement(addNew, webDriver, 10);
        addNew.click();
        clickOnElement(addAppointment);
        return PageFactory.initElements(webDriver, AddNewAppointmentModal.class);
    }

    public boolean checkIfLinkToDashboardWorks(){
        waitForElement(dashboadLink,webDriver,3);
        clickOnElement(dashboadLink);
        return returnHeader()
                .whichSectionIsSelected()
                .equalsIgnoreCase("DashboardPage");
    }

    private CalendarPage goToNextCalendarPage(){
        waitForElement(next,webDriver,2);
        clickOnElement(next);
        return this;
    }

    private CalendarPage goToPreviousCalendarPage(){
        waitForElement(previous,webDriver,2);
        clickOnElement(previous);
        return this;
    }

    private CalendarPage goToTodayCalendarPage(){
        waitForElement(today,webDriver,2);
        clickOnElement(today);
        return this;
    }

    private CalendarPage chooseWeekSection(){
        waitForElement(week,webDriver,2);
        clickOnElement(week);
        if( !(viewType
                .getText()
                .trim()
                .equalsIgnoreCase(weekView)))
            throw new InvalidElementStateException("Problem with switching to week section");
            // TODO more determinism - throw exception or remove this
        return this;
    }

    private CalendarPage chooseMonthSection(){
        waitForElement(month,webDriver,2);
        clickOnElement(month);
        if( !(viewType
                .getText()
                .trim()
                .equalsIgnoreCase(monthView)))
            throw new InvalidElementStateException("Problem with switching to month section");
        return this;
    }
 
    private void monthNavigation(String day, String month) {
        if (date.getMonth() == Utility.getMonthNumber(month)) {
            for (WebElement finder : daysFromCurrentMonth) {
                if (finder.findElement(By.xpath(".//td[contains(@class, 'td-head')]/div")).getText().equalsIgnoreCase(day)) {
                    clickOnElement(finder.findElement(By.xpath("./table")));
                }
            }
        } else if (date.getMonth() > Utility.getMonthNumber(month)) {
            for (int i = date.getMonth() - Utility.getMonthNumber(month) ; i > 0 ; i--) {
                goToPreviousCalendarPage();
            }
            for (WebElement finder : daysFromCurrentMonth) {
                if (finder.findElement(By.xpath(".//td[contains(@class, 'td-head')]/div")).getText().equalsIgnoreCase(day)) {
                    clickOnElement(finder.findElement(By.xpath("./table")));
                }
            }
        } else if (date.getMonth() < Utility.getMonthNumber(month)) {
            for (int i = (Utility.getMonthNumber(month) - date.getMonth()); i > 0; i--) {
                goToNextCalendarPage();
            }
            for (WebElement finder : daysFromCurrentMonth) {
                if (finder.findElement(By.xpath(".//td[contains(@class, 'td-head')]/div")).getText().equalsIgnoreCase(day)) {
                    clickOnElement(finder.findElement(By.xpath("./table")));
                }
            }
        }
    }

    /*
    -1 - previous month
    0 / current month
    1 - next month
     */

    private boolean checkMonthLabel(int monthType){
        getMonthValues();
        if (monthType == 1) {
            return monthLabel
                    .getText()
                    .trim()
                    .equalsIgnoreCase(afterMonth);
        } else if(monthType == 0){
            return monthLabel
                    .getText()
                    .trim()
                    .equalsIgnoreCase(
                            Month.of(date.getMonth() + 1).toString());
        }else if (monthType == -1){
            return monthLabel
                    .getText()
                    .trim()
                    .equalsIgnoreCase(beforeMonth);
        }else {
            throw new IndexOutOfBoundsException("monthType value incompatible with conditions");
        }
    }

    public void checkNavigation(){
        goToNextCalendarPage().checkMonthLabel(1);
        goToTodayCalendarPage().checkMonthLabel(0);
        goToPreviousCalendarPage().checkMonthLabel(-1);

        chooseWeekSection()
                .goToNextCalendarPage()
                .goToTodayCalendarPage()
                .goToPreviousCalendarPage()
                .chooseMonthSection();
    }

    private void getMonthValues() {
        Integer currentMonthNumber = date.getMonth() + 1;
        Integer beforeMonthNumber = currentMonthNumber != 0 ? currentMonthNumber - 1 : 12;
        Integer afterMonthNumber = currentMonthNumber != 12 ? currentMonthNumber + 1 : 0;
        beforeMonth = Month.of(beforeMonthNumber).toString();
        afterMonth = Month.of(afterMonthNumber).toString();
    }

    private WebElement chooseJobTime(List<WebElement> list){
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    // TODO extract this method and others to Utils class
}


