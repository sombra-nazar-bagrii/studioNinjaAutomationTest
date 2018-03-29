package tests;

import ComfigurationClasses.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageobject.header.Header;
import pageobject.singIn.SingInPage;

import java.io.*;
import java.util.Arrays;

/**
 * Created by sombra17 on 09.12.16.
 */
public class TestBase {
    protected Header startPage;
    protected SingInPage startPage2;
    private static WebDriver webDriver;
    protected static final String DASHBOARD_PAGE = "Dashboard";
    protected static final String CLIENT_OVERVIEW_PAGE = "Clients overview";
    protected static final String JOBS_OVERVIEW_PAGE = "Jobs Overview";
    protected static final String CLIENT_SEND_MODAL = "Send email to client";
    protected static final String CALENDAR_BUTTON = "Calendar button";
    protected static final String CALENDAR_WEEK_SECTION = "Calendar week section";
    protected static final String CALENDAR_MONTH_SECTION = "Calendar month section";

    @Parameters({ "browserName" })
    @BeforeSuite
    public void init(String browserName) throws Exception {
        webDriver = WebDriverFactory.getInstance(browserName);
        webDriver.get("https://stdn.pp.ua/login");
    }
    @BeforeMethod
    public void includeExclude(){
        startPage = PageFactory.initElements(webDriver,Header.class);
        startPage2 = PageFactory.initElements(webDriver, SingInPage.class);
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