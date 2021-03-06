package pageobject.jobsOverview;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ComfigurationClasses.Page;

/**
 * Created by sombra-15 on 14.09.17.
 */
public class JobProfilePage extends Page{

    @FindBy(xpath = "(.//*[@id='main-wrapper']//h2)[1]")
    private WebElement jobName;

    public JobProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getJobName(){
        return jobName.getText();
    }

}
