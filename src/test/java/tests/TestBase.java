package tests;

import Factory.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageobject.header.Header;
import pageobject.singIn.SingIn;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by sombra17 on 09.12.16.
 */
public class TestBase {
    protected Header startPage;
    protected SingIn startPage2;
    private static WebDriver webDriver;
    static final Properties properties = new Properties();

/*
    static {
        try (FileInputStream file = new FileInputStream("src/main/resources/properties.properties")){
            properties.load(file);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
*/
    @Parameters({ "browserName" })
    @BeforeSuite
    public void init(String browserName) throws Exception {
        webDriver = WebDriverFactory.getInstance(browserName);
        webDriver.get("https://stdn.pp.ua/login");
    }
    @BeforeMethod
    public void includeExclude(){
        startPage = PageFactory.initElements(webDriver,Header.class);
        startPage2 = PageFactory.initElements(webDriver, SingIn.class);
    }
    @AfterMethod
    public void afterMethod(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println(testResult.getStatus());
            File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) + ".jpg"));
        }
        webDriver.get("https://stdn.pp.ua");
    }
    @AfterSuite
    public void afterSuiteMethod(){
        webDriver.close();
    }
    }