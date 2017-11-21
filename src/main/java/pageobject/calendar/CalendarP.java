package pageobject.calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.Page;
import pageobject.modalForms.AddNewAppointmentMo;
import pageobject.modalForms.AddNewExtraShootMo;
import pageobject.modalForms.AddNewJobMo;

import java.time.Month;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Created by sombra-15 on 12.07.17.
 */

public class CalendarP extends Page {

    private Calendar c = Calendar.getInstance();
    private String beforeMonth = "";
    private String afterMonth = "";
    private String weekView = "Week view";
    private String monthView = "Month view";
    private String appointment = "Appointment";
    private String job = "Main shoot";
    private String extraShoot = "Extra Shoot";

    @FindBy(xpath = ".//*[@id='main-wrapper']//*[@ng-click='addNew()']")
    private WebElement addNew;

    @FindBy(xpath = ".//*[@id='addNewModal']//*[@title = 'job']")
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

    @FindBy(xpath = ".//*[contains(@class, 'calendar-cell') and contains(@class, 'pointer')]")
    private List<WebElement> allDays;

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

    public CalendarP(WebDriver webDriver) {
        super(webDriver);
        allDays.removeAll(daysNotFromCurrentMonth);
    }

    public AddNewJobMo createNewJob (){
        waitForElement(addNew, webDriver, 10);
        addNew.click();
        clickOnElement(addJob);
        return PageFactory.initElements(webDriver, AddNewJobMo.class);
    }

    public AddNewExtraShootMo createNewExtraShoot (String jobName) {
        waitForElement(addNew, webDriver, 10);
        addNew.click();
        clickOnElement(addExtrashoot);
        return PageFactory.initElements(webDriver, AddNewExtraShootMo.class);
    }

    public AddNewAppointmentMo createNewAppointment (){
        waitForElement(addNew, webDriver, 10);
        addNew.click();
        clickOnElement(addAppointment);
        return PageFactory.initElements(webDriver, AddNewAppointmentMo.class);
    }

    public boolean checkIfLinkToDashboardWorks(){
        waitForElement(dashboadLink,webDriver,3);
        clickOnElement(dashboadLink);
        return returnHeader()
                .whichSectionIsSelected()
                .equalsIgnoreCase("Dashboard");
    }

    private CalendarP goToNextCallendarPage(){
        waitForElement(next,webDriver,2);
        clickOnElement(next);
        return this;
    }

    private CalendarP goToPreviousCallendarPage(){
        waitForElement(previous,webDriver,2);
        clickOnElement(previous);
        return this;
    }

    private CalendarP goToTodayCallendarPage(){
        waitForElement(today,webDriver,2);
        clickOnElement(today);
        return this;
    }

    private CalendarP chooseWeekSection(){
        waitForElement(week,webDriver,2);
        clickOnElement(week);
        if( viewType
                .getText()
                .trim()
                .equalsIgnoreCase(weekView))
            System.out.println("Some problem occurs in switching between month/week");
        return this;
    }

    private CalendarP chooseMonthSection(){
        waitForElement(month,webDriver,2);
        clickOnElement(month);
        if( viewType
                .getText()
                .trim()
                .equalsIgnoreCase(monthView))
            System.out.println("Some problem occurs in switching between month/week");
        return this;
    }

dsf

    private boolean checkIfEventDisplayed (String typeOfEvent, String name, String day, String month, String eventTime){
        if(typeOfEvent.equalsIgnoreCase(job) && monthNavigation(day, month)){
            System.out.println("a job has been found");
        } else if(typeOfEvent.equalsIgnoreCase(appointment)){
            monthNavigation(day, month);
        }else if (typeOfEvent.equalsIgnoreCase(extraShoot)){
            monthNavigation(day, month);
        }
    }

    private boolean monthNavigation(String day, String month){
        if ( c.get(Calendar.MONTH) == getMonthNumber(month)){
            for (WebElement finder: allDays) {
                if (finder.getText().equalsIgnoreCase(day))
                    return true;
            }
        } else if(c.get(Calendar.MONTH) > getMonthNumber(month)){
            for( int i = c.get(Calendar.MONTH) - getMonthNumber(month); i <= 0 ; i--){
                waitForElement(next, webDriver, 3);
                clickOnElement(next);
            }
            for (WebElement finder: allDays) {
                if (finder.getText().equalsIgnoreCase(day))
                    return true;
            }
        } else if (c.get(Calendar.MONTH) < getMonthNumber(month)){
            for( int i = getMonthNumber(month) - c.get(Calendar.MONTH); i <= 0 ; i--){
                waitForElement(previous, webDriver, 3);
                clickOnElement(previous);
            }
            for (WebElement finder: allDays) {
                if (finder.getText().equalsIgnoreCase(day))
                    return true;
            }
        } else return false;
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
                            c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH));
        }else if (monthType == -1){
            return monthLabel
                    .getText()
                    .trim()
                    .equalsIgnoreCase(beforeMonth);
        }else {
            System.out.println("Wrong position");
            return false;
        }
    }

    public void checkNavigation(){
        goToNextCallendarPage().checkMonthLabel(1);
        goToTodayCallendarPage().checkMonthLabel(0);
        goToPreviousCallendarPage().checkMonthLabel(-1);

        chooseWeekSection()
                .goToNextCallendarPage()
                .goToTodayCallendarPage()
                .goToPreviousCallendarPage()
                .chooseMonthSection();
    }

    private void getMonthValues() {
        Map<String, Integer> displayNames = Calendar.getInstance().getDisplayNames(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        String currentName =  Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        Integer currentMonthNumber = displayNames.get(currentName);
        Integer beforeMonthNumber = currentMonthNumber != 0 ? currentMonthNumber - 1 : 12;
        Integer afterMonthNumber = currentMonthNumber != 12 ? currentMonthNumber + 1 : 0;

        for(Map.Entry<String, Integer> entry : displayNames.entrySet()){
            if (entry.getValue().equals(beforeMonthNumber)){
                beforeMonth = entry.getKey();
            }
            if (entry.getValue().equals(afterMonthNumber)){
                afterMonth = entry.getKey();
            }
        }
    }

    private int getMonthNumber (String monthName){
        return Month.valueOf(monthName.toUpperCase()).getValue();
    }
}
