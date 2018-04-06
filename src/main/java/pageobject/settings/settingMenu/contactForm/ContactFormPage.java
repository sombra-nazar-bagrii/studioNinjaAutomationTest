package pageobject.settings.settingMenu.contactForm;

import ComfigurationClasses.ContactFormFactory;
import ComfigurationClasses.ContactFormField;
import ComfigurationClasses.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sombramac-19 on 3/28/18.
 */

public class ContactFormPage extends Page {

    @FindBy(xpath = ".//*[@ng-click='addNewField()']")
    private WebElement addNewField;

    @FindBy(xpath = ".//*[contains(text(), 'Save form')]")
    private WebElement saveForm;

    @FindBy(xpath = ".//*[contains(text(), 'Get code')]")
    private WebElement getCode;

    @FindBy(xpath = ".//*[@ng-model='contactForm.contactEmbedCode']")
    private WebElement textAreaWithEmbedCode;

    @FindBy(xpath = ".//*[@ng-model='contactForm.contactUrl']")
    private WebElement textAreaWithURLCode;

    @FindBy(xpath = ".//*[@ng-click='saveContactForm(getURLContactForm)']")
    private WebElement getURL;

    @FindBy(xpath = ".//*[@ng-click=\"copy2Clipboard(contactForm.contactUrl, 'url')\"]")
    private WebElement copyURL;

    @FindBy(xpath = ".//*[@dnd-list='contactForm.fields']")
    private WebElement allFields;

    @FindBy(xpath = "(.//*[@class='container-of-contact'])[last()]")
    private WebElement lastElementInList;

    @FindBy(xpath = "(.//*[@ng-click='openVariablesList(field)'])[last()]")
    private WebElement lastTypeField;

    @FindBy(xpath = "(.//input[@ng-model='field.label'])[last()]")
    private WebElement lastLableField;

    @FindBy(xpath = "(.//input[@ng-click='setMandatory(contactForm.fields)'])[last()]")
    private WebElement lastMandatoryCheckBox;

    @FindBy(xpath = ".//*[@class='drag-n-drop-close close dd-nodrag']")
    private List<WebElement> deleteButtons;

    @FindBy(xpath = "(.//*[@ng-click='openVariablesList(field)'])[last()]//following-sibling::ul/li")
    private List <WebElement> optionsForLastField;

    public ContactFormPage(WebDriver webDriver) {
        super(webDriver);
    }

    private void contactFormConfiguration (String configurations){
        clearContactForm();
        // TODO need to use if instead of try
        try {
            List<ContactFormField> fields = ContactFormFactory
                    .getContactFormConfigurations(configurations)
                    .getFieldsList();
            for (ContactFormField newField : fields) {
                createNewEmptyField();
                getOptionByName(newField.getFieldType());
                enterLabel(newField.getFieldLable());
                mandatoryState(newField.getMandatoryState());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        clickOnElement(saveForm);
    }

    private void createNewEmptyField(){
        scrollToTop();
        sleepThread(2000);
        addNewField.click();
        lastTypeField.click();
    }

    private void enterLabel(String label){
        customClearAndSendValue(lastLableField, label);
    }

    private void getOptionByName(String name){
        Map<String, WebElement> options = new HashMap<>();
        for (WebElement element: this.optionsForLastField) {
            options.put(element.getText(), element);
        }
            options.get(name).click();

    }

    private void mandatoryState(boolean mandatory){
        if(mandatory) {
            hardClick(webDriver,lastMandatoryCheckBox);
        }
    }

    private void clearContactForm(){
        for (WebElement close: deleteButtons) {
            clickOnElement(close);
        }
    }

    public EmbeddedContactForm goToEmbeddedContactForm(String configurations){
        contactFormConfiguration(configurations);
        clickOnElement(getCode);
        String iframeHTML = textAreaWithEmbedCode
                .getAttribute("value");
        String embeddedCode=iframeHTML.substring(iframeHTML.indexOf("src") +5,iframeHTML.indexOf("frameborder") -2);
        webDriver.get(embeddedCode);
        return PageFactory.initElements(webDriver, EmbeddedContactForm.class);
    }

    public HostedContactForm goToHostedContactForm(String configurations){
        contactFormConfiguration(configurations);
        clickOnElement(getURL);
        webDriver.get(textAreaWithURLCode.getAttribute("value"));
        return PageFactory.initElements(webDriver, HostedContactForm.class);
    }
}
