package pageobject;

import Factory.Messages;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageobject.header.Header;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Created by sombra17 on 09.12.16.
 */

public abstract class Page {
    protected WebDriver webDriver;
    protected String tempEmail;
    FileInputStream file;
    Properties property = new Properties();

    protected final String CLIENT_NAME = "Client";
    protected final boolean modalsWithCC = true;
    protected final String ERROR_TYPE = "error";
    protected final String SUCCESS_TYPE = "success";
    protected final String INFO_TYPE = "info";
    protected final String WARNING_TYPE = "warning";

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

    private WebElement typeOfMessage(String type){
        if (type.equalsIgnoreCase(ERROR_TYPE)){
            return tosterError;
        } else if (type.equalsIgnoreCase(SUCCESS_TYPE)){
            return tosterSuccess;
        } else if (type.equalsIgnoreCase(INFO_TYPE)){
            return tosterInfo;
        } else if (type.equalsIgnoreCase(WARNING_TYPE)){
            return tosterWarning;
        } else{
            throw new NoSuchElementException("Type not valid");
        }
    }

    public boolean isToasterValid(String title, String message, String type){
        try {
            return getTosterTitle().equalsIgnoreCase(title) &&
                    getTosterMessage().equalsIgnoreCase(message) &&
                    isElementDisplayed(typeOfMessage(type));
        }
        catch (StaleElementReferenceException e){
            e.printStackTrace();
            return false;
        }
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
        waitForElement(element,webDriver,5);
        element.click();
    }
    protected void hardClick(WebDriver driver,WebElement element){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", element);
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

    protected void sleepThread(int mseconds){
        try {
            Thread.sleep(mseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    protected void waitForElements(By locator, WebDriver driver, int seconds){
        WebDriverWait wait = new WebDriverWait(driver,seconds);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
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

    public boolean checkMessageForCase(String caseType) {
        switch (caseType) {
            case "JobConf1":
            case "JobConf2":
            case "JobConf3":
            case "JobConf4":
            case "JobConf5":
            case "JobConf6":
                return isToasterValid(
                        Messages.JOB_CREATED.getTitle(),
                        Messages.JOB_CREATED.getMessage(),
                        Messages.JOB_CREATED.getType());
            case "JobConf7":
                return isToasterValid(
                        Messages.NO_CLIENTS_SELECTED.getTitle(),
                        Messages.NO_CLIENTS_SELECTED.getMessage(),
                        Messages.NO_CLIENTS_SELECTED.getType());
            case "JobConf8":
                return isToasterValid(
                        Messages.NO_JOB_NAME_ENTERED.getTitle(),
                        Messages.NO_JOB_NAME_ENTERED.getMessage(),
                        Messages.NO_JOB_NAME_ENTERED.getType());
            case "JobConf9":
            case "JobConf10":
                return isToasterValid(
                        Messages.NO_DURATION_SELECTED.getTitle(),
                        Messages.NO_DURATION_SELECTED.getMessage(),
                        Messages.NO_DURATION_SELECTED.getType());
            case "JobConf11":
                return isToasterValid(
                        Messages.TWO_THE_SAME_CLIENTS.getTitle(),
                        Messages.TWO_THE_SAME_CLIENTS.getMessage(),
                        Messages.TWO_THE_SAME_CLIENTS.getType());
            case "JobConf12":
                return isToasterValid(
                        Messages.INVALID_TIME.getTitle(),
                        Messages.INVALID_TIME.getMessage(),
                        Messages.INVALID_TIME.getType());
            default: throw new NoSuchMethodError();
        }
    }

    public Header returnHeader(){
        return PageFactory.initElements(webDriver,Header.class);}
}


