package tests;

import Factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import pageobject.header.Header;
import pageobject.singIn.SingIn;

/**
 * Created by sombra17 on 09.12.16.
 */
public class TestBase {
    protected Header startPage;
    protected SingIn startPage2;
    private static WebDriver webDriver;

    @Parameters({ "browserName" })
    @BeforeSuite
    public void init(String browserName) throws Exception {
        webDriver = WebDriverFactory.getInstance(browserName);
        webDriver.get("https://snfrankfurt.servehttp.com/");
    }
    @BeforeMethod
    public void includeExclude(){
        startPage = PageFactory.initElements(webDriver,Header.class);
        startPage2 = PageFactory.initElements(webDriver, SingIn.class);
    }
    @AfterMethod
    public void afterMethod(){webDriver.get("https://snfrankfurt.servehttp.com/");
    }
}