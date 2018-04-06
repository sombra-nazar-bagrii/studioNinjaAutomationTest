package pageobject.modalForms;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ComfigurationClasses.ClientConfiguration;
import ComfigurationClasses.Page;
import pageobject.clients.ClientsOverviewPage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by sombra-15 on 09.09.17.
 */
public class AddNewClientModal extends Page {
    private final String isClientCompany = "yes";

    @FindBy(xpath = ".//*[@ng-model = 'companyNameChecked']/following-sibling::label")
    private WebElement clientIsACompanyBox;

    @FindBy(xpath = ".//*[@ng-model = 'client.firstName']")
    private WebElement firstName;

    @FindBy(xpath = ".//*[@ng-model = 'client.lastName']")
    private WebElement lastName;

    @FindBy(xpath = ".//*[@ng-model = 'client.phone']")
    private WebElement phone;

    @FindBy(xpath = ".//*[@ng-model = 'client.email']")
    private WebElement email;

    @FindBy(xpath = ".//*[@ng-model = 'client.address']")
    private WebElement street;

    @FindBy(xpath = ".//*[@ng-model = 'client.town']")
    private WebElement town;

    @FindBy(xpath = ".//*[@ng-model = 'client.postcode']")
    private WebElement postcode;

    @FindBy(xpath = ".//*[@ng-model = 'client.state']")
    private WebElement state;

    @FindBy(xpath = ".//*[@ng-model = 'client.country']")
    private WebElement country;

    @FindBy(css = "[ng-model='html']")
    private WebElement notesFromJob;

    @FindBy(xpath = "(.//*[@ng-model='client.companyName'])[1]")
    private WebElement company;

    @FindBy(xpath = ".//*[contains(text(), 'Save client profile')]")
    private WebElement saveClient;


    public AddNewClientModal(WebDriver webDriver) {
        super(webDriver);
    }

    Random rand = new Random();

    public ClientsOverviewPage createNewClient() {
        List<WebElement> list = Arrays.asList(firstName, lastName, phone, street, town, postcode, state, country);
        if (isClientCompany.trim().equalsIgnoreCase("Yes") && !clientIsACompanyBox.isSelected()) {
            clickOnElement(clientIsACompanyBox);
            customClearAndSendValue(company, ClientConfiguration.getFirstName());
        }
        List<String> values=ClientConfiguration.getAllValues();
        setAllValuesForAllElements(
                list,
                values);
        //to prevent issue on multiple modals
        this.country.sendKeys(Keys.TAB);
        this.notesFromJob = webDriver.switchTo().activeElement();
        this.notesFromJob.sendKeys(values.get(8));
        customClearAndSendValue(
                email,
                ClientConfiguration.getFirstName().replaceAll(" ","") + rand.nextInt(99) +"@" + ClientConfiguration.getLastName() + ".com");
        saveClient.click();
        sleepThread(1500);
        return PageFactory.initElements(webDriver, ClientsOverviewPage.class);
    }

}
