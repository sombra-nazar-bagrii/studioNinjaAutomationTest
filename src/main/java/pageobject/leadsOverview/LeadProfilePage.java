package pageobject.leadsOverview;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject.Page;

/**
 * Created by sombra-15 on 14.09.17.
 */
public class LeadProfilePage extends Page{

    @FindBy(xpath = "(.//*[@id='main-wrapper']//h2)[1]")
    private WebElement leadName;

    public LeadProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getLeadName(){
        return leadName.getText();
    }

}
