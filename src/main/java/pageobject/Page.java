package pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageobject.header.Header;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * Created by sombra17 on 09.12.16.
 */

public abstract class Page {
    protected WebDriver webDriver;
    protected String tempEmail;
    FileInputStream file;
    Properties property = new Properties();

    protected final String CLIENT_NAME = "Test";
    protected final boolean modalsWithCC = true;

    @FindBy(xpath = "//div[contains(@id,'taTextElement')]/p")
    private WebElement angularFirstP;

	@FindBy(xpath = "//div[@class = 'toast toast-success']") //green
    protected WebElement tosterSuccess;

    @FindBy(xpath = "//div[@class = 'toast toast-error']") //red
    protected WebElement tosterError;

    @FindBy(xpath = "//div[@class = 'toast toast-info']") //blue
    protected WebElement tosterInfo;

    @FindBy(xpath = "//div[@class= 'toast toast-warning'") //yellow
    protected WebElement tosterWarning;

    @FindBy(xpath = "//div[@class ='toast-title']") //title
    protected WebElement tosterTitle;

    @FindBy(xpath = "//div[@class ='toast-message']") //message
    protected WebElement tosterMessage;

    protected Page(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getTempEmail() {
        return tempEmail;
    }

    public void setTempEmail(String tempEmail) {
        this.tempEmail = tempEmail;
    }

    protected String getTosterTitle(){
        return tosterTitle.getText();
    }

    protected String getTosterMessage(){
        return tosterMessage.getText();
    }

    protected boolean isToasterValid(String title, String message){
        if (getTosterTitle().equalsIgnoreCase(title) &&
                getTosterMessage().equalsIgnoreCase(message) &&
                isElementDisplayed(tosterError))
        return true;
        else return false;
    }

    protected WebDriver getWebDriver() {
        return webDriver;
    }

    protected void openNewWindow(String URL){
        webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "n");
        for (String winHandle : webDriver.getWindowHandles()) {
            System.out.println(winHandle);
            webDriver.switchTo().window(winHandle);
        }
        webDriver.get(URL);
    }

    protected void openNextTab(){
        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.TAB);
        webDriver.findElement(By.cssSelector("body")).sendKeys(selectLinkOpeninNewTab);
    }

    protected void clickOnElement(WebElement element){
        element.click();
    }

    protected boolean isElementPresent(WebElement element) {
        try {
            element.isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void waitSomeSec(WebDriver driver, int sec){
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    protected String containsAttribute (WebElement element, String attribute){
      return element.getAttribute(attribute);
    }

    protected void customSendKeys(WebElement element,String text){
        element.sendKeys(text);
    }

    protected void customClear(WebElement element){element.clear();}

    protected void customClearAndSendValue(WebElement webel, String str){
        webel.clear();
        webel.sendKeys(str);
    }

    protected void customSelectByVisibleText(WebElement element,String text){
        new Select(element).selectByVisibleText(text);
    }

    protected void customSelectByIndex(WebElement element, int index){
        new Select(element).selectByIndex(index);
    }

    protected void checkAllSelectedOptions(WebElement element){
        Select sel = new Select(element);
        for(int i = 0; i < sel.getOptions().size(); i ++){
            customSelectByIndex(element, i);
            webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
    }

    protected void waitForElement(WebElement element, WebDriver driver, int seconds){
        WebDriverWait wait = new WebDriverWait(driver,seconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void titleMessageCheck(String title, String message){
            Assert.assertTrue(getTosterTitle().equals(title));
            Assert.assertTrue(getTosterMessage().equals(message));
    }

    protected void isTextAreTheSame(String actual, String expected){
        Assert.assertTrue(actual.equals(expected));
    }

    protected String generateString() {
        char[] arr;
        final Random random = new Random();
        final int size = random.nextInt(10) + 5;
        arr = new char[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (char) (random.nextInt(25) + 65);
        }
        String value = new String(arr);
        return value.toLowerCase();
    }

    protected void generateValuesForAll(List<WebElement> n){
        for (WebElement count: n) {
            customClearAndSendValue(count, generateString());
        }
    }

    protected void setAllValuesForAllElements(List<WebElement> element, List<String> value){
        for(int i =0; i <element.size(); i++){
            customClearAndSendValue(element.get(i), value.get(i));
        }
    }

    public Header returnHeader(){
        return PageFactory.initElements(webDriver,Header.class);}
}


