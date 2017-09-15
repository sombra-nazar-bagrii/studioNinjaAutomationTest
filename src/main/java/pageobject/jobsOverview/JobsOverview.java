package pageobject.jobsOverview;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.Page;
import pageobject.modalForms.AddNewJobMo;

/**
 * Created by sombra-15 on 12.07.17.
 */
public class JobsOverview extends Page {

    @FindBy(xpath = ".//*[@data-autotest-button='addNewJob']")
    private WebElement addNewJobButton;

    @FindBy(xpath = ".//*[@id='fobs-search-input']")
    private WebElement searchField;

    @FindBy(xpath = ".//*[@ng-model='selectActiveJobs']")
    private WebElement selectActiveJob;
/*
    @FindBy(xpath = "")
    private WebElement

    @FindBy(xpath = "")
    private WebElement
*/
    public JobsOverview(WebDriver webDriver) {
        super(webDriver);
    }

    public AddNewJobMo addNewJob (){
        waitForElement(addNewJobButton,webDriver, 10);
        clickOnElement(addNewJobButton);
        return PageFactory.initElements(webDriver, AddNewJobMo.class);
    }
}
