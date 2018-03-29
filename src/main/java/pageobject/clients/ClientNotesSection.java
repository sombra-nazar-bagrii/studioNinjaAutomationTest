package pageobject.clients;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ComfigurationClasses.Page;

/**
 * Created by sombra-15 on 14.09.17.
 */

public class ClientNotesSection extends Page{
    private static String text = "Nazar ";

    @FindBy (xpath = "(.//*[starts-with(@id,'taTextElement')])[3]")
    private WebElement notesField;

    @FindBy (xpath = "(.//*[@name='bold'])[3]")
    private WebElement boldFormat;

    @FindBy (xpath = "(.//*[@name='italics'])[3]")
    private WebElement italicFormat;

    @FindBy (xpath = "(.//*[@name='underline'])[3]")
    private WebElement underlineFormat;

    @FindBy (xpath = "(.//*[@name='link'])[3]")
    private WebElement link;

    @FindBy (xpath = "(.//*[@name='ul'])[3]")
    private WebElement pointListType;

    @FindBy (xpath = "(.//*[@name='ol'])[3]")
    private WebElement numbersListType;

    @FindBy (xpath = "(.//*[@name='justifyLeft'])[3]")
    private WebElement justifyLeft;

    @FindBy (xpath = "(.//*[@name='justifyCenter'])[3]")
    private WebElement justifyCenter;

    @FindBy (xpath = "(.//*[@name='justifyRight'])[3]")
    private WebElement justifyRight;

    @FindBy (xpath = "(.//*[@name='justifyFull'])[3]")
    private WebElement justifyFull;

    @FindBy (xpath = ".//*[@id='updateModal']//*[@data-dismiss='modal']")
    private WebElement closeB;

    @FindBy (xpath = ".//*[@ng-click='saveNotes()']")
    private WebElement saveChanges;

    public ClientNotesSection(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkAllNotesSettings(){
        clickOnElement(notesField);
        clickOnElement(boldFormat);
        customSendKeys(notesField, text);
        clickOnElement(italicFormat);
        customSendKeys(notesField, text);
        clickOnElement(pointListType);
        clickOnElement(underlineFormat);
        customSendKeys(notesField, text + '\n');
        clickOnElement(underlineFormat);
        customSendKeys(notesField, text);
        clickOnElement(numbersListType);
        clickOnElement(italicFormat);
        customSendKeys(notesField, text);
        clickOnElement(boldFormat);
        customSendKeys(notesField, text);
        clickOnElement(justifyCenter);
        clickOnElement(justifyFull);
        clickOnElement(justifyLeft);
        clickOnElement(justifyRight);
        clickOnElement(saveChanges);

    }
}
