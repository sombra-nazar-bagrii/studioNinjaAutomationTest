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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by sombra17 on 09.12.16.
 */
public class TestBase {
    protected Header startPage;
    protected SingIn startPage2;
    private static WebDriver webDriver;
    static final Properties properties = new Properties();

    static {
        try (FileInputStream file = new FileInputStream("src/main/resources/properties.properties")){
            properties.load(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    public void afterMethod(){webDriver.get("https://stdn.pp.ua/");
    }
}