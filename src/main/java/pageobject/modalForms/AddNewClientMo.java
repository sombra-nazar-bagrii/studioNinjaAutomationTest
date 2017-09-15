package pageobject.modalForms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.Page;
import pageobject.clients.Clients;
import pageobject.clients.SomeClient;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sombra-15 on 09.09.17.
 */
public class AddNewClientMo extends Page {
    private final String isClientCompany = "No";

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

    @FindBy(xpath = "(.//*[starts-with(@id, 'taTextElement')])[1]")
    private WebElement notes;

    @FindBy(xpath = ".//*[contains(text(), 'Save client profile')]")
    private WebElement saveClient;


    public AddNewClientMo(WebDriver webDriver) {
        super(webDriver);
    }

    private static int count = 1;

    public Clients createNewClient() {
        List<WebElement> list = Arrays.asList(lastName, phone, street, town, postcode, state, country, notes);
        if (isClientCompany.trim().equalsIgnoreCase("Yes")) {
            clickOnElement(clientIsACompanyBox);
        }
        customClearAndSendValue(firstName, CLIENT_NAME + count++);
        customClearAndSendValue(email, CLIENT_NAME + "@" + generateString().toLowerCase() + ".com");
        generateValuesForAll(list);
        clickOnElement(saveClient);
        return PageFactory.initElements(webDriver, Clients.class);
    }
}
