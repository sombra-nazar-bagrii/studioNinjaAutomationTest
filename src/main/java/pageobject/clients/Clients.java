package pageobject.clients;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobject.Page;
import pageobject.modalForms.AddNewClientMo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sombra-15 on 12.07.17.
 */

public class Clients extends Page {

    @FindBy(xpath = "//button[@data-autotest-button='addNewClient']")//+
    private WebElement addNewClientButton;

    @FindBy(xpath = "//input[@data-autotest-field='search']")//+
    private WebElement searchInput;

    @FindBy(xpath = "//tbody[@data-autotest-area='tbody']/tr")//+
    private List <WebElement> rowsInSearchArea;

    @FindBy(xpath = "//tbody[@data-autotest-area='tbody']/tr[1]")//+
    private WebElement firstRowInSearchArea;

    public Clients(WebDriver webDriver) {
        super(webDriver);
    }

    public AddNewClientMo addNewClient(){
        waitForElement(addNewClientButton,webDriver,10);
        clickOnElement(addNewClientButton);
        return PageFactory.initElements(webDriver, AddNewClientMo.class);
    }

    public void editClient(){

    }

    public void sendKeysToSearchInput(String text){
        customSendKeys(searchInput,text);
    }

    public SendEmailFromClients goToSendEmailMo(){
        openPage(".//a[starts-with(@ng-href, '/clients/client')]");
        return PageFactory.initElements(webDriver, SendEmailFromClients.class);
    }

    public SomeClient goToSomeClientPage(){
        openPage(".//a[starts-with(@ng-href, '/clients/client')]");
        return PageFactory.initElements(webDriver, SomeClient.class);
    }
/*
    public <T extends WebElement> void goSome(T tClass){
        if (tClass instanceof SendEmailFromClients){

        }
    }
*/
    public void openPage(final String path){
        sendKeysToSearchInput(CLIENT_NAME);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElement(firstRowInSearchArea, webDriver,10);
        clickOnElement(firstRowInSearchArea.findElement(By.xpath(path)));
    }


    public void verifyThatSearchIsWorking(String textForValidation){

        sendKeysToSearchInput(textForValidation);
        try{
            waitForElement(rowsInSearchArea.get(0),webDriver,20);
            for(int i =0; i <= rowsInSearchArea.size(); i++) {
                    if (rowsInSearchArea.get(i).getText().equalsIgnoreCase(textForValidation)) {
                    System.out.println("search working");  // change to logs
                }
            }
        }catch (NoSuchElementException noSuch){
            System.out.println("Searching fall down");
        }catch (TimeoutException ex) {
            System.out.println("Searching fall down [2]");
        }
    }
}