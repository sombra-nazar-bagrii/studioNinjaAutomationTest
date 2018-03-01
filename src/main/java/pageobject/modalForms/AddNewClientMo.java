package pageobject.modalForms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.ClientConfiguration;
import pageobject.Page;
import pageobject.clients.Clients;
import pageobject.clients.SomeClient;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by sombra-15 on 09.09.17.
 */
public class AddNewClientMo extends Page {
    private final String isClientCompany = "no";

    @FindBy(xpath = ".//*[@ng-model = 'companyNameChecked']/following-sibling::label")
    private WebElement clientIsACompanyBox;

    @FindBy(xpath = ".//*[@ng-model = 'client.firstName']")
    private WebElement firstName;

    @FindBy(xpath = ".//*[@ng-model = 'client.lastName']")
    private WebElement lastName;

    @FindBy(xpath = ".//*[@ng-model = 'client.phone']")
    private WebElement phone;

    // write validation to symbols which could contains in this field
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

    /*
    @FindBy(xpath = "(.//*[starts-with(@id, 'taTextElement')])[2]")
    private WebElement notesFromJob;

    @FindBy(xpath = "(.//*[starts-with(@id, 'taTextElement')])[2]")
    private WebElement notesFromJob;
    */

    @FindBy(xpath = "(.//*[@ng-model='client.companyName'])[1]")
    private WebElement company;

    @FindBy(xpath = ".//*[contains(text(), 'Save client profile')]")
    private WebElement saveClient;


    public AddNewClientMo(WebDriver webDriver) {
        super(webDriver);
    }

    Random rand = new Random();

    public Clients createNewClient() {
        List<WebElement> list = Arrays.asList(firstName, lastName, phone, street, town, postcode, state, country, notesFromJob);
        if (isClientCompany.trim().equalsIgnoreCase("Yes")) {
            clickOnElement(clientIsACompanyBox);
            customClearAndSendValue(company, ClientConfiguration.getFirstName());
        }
        setAllValuesForAllElements(
                list,
                ClientConfiguration.getAllValues());
        customClearAndSendValue(
                email,
                ClientConfiguration.getFirstName().replaceAll(" ","") + rand.nextInt(99) +"@" + ClientConfiguration.getLastName() + ".com");
        saveClient.click();
        sleepThread(1500);
        return PageFactory.initElements(webDriver, Clients.class);
    }

    public SomeClient editClientInformation(){
        List<WebElement> list = Arrays.asList(firstName, lastName, phone, street, town, postcode, state, country);
        if (isClientCompany.trim().equalsIgnoreCase("Yes")) {
            clickOnElement(clientIsACompanyBox);
            customClearAndSendValue(company, ClientConfiguration.getFirstName());
        }
        setAllValuesForAllElements(
                list,
                ClientConfiguration.getAllValues());
        customClearAndSendValue(
                email,
                ClientConfiguration.getFirstName() + "@" + ClientConfiguration.getLastName() + ".com");
        clickOnElement(saveClient);
        return PageFactory.initElements(webDriver, SomeClient.class);
    }
}
