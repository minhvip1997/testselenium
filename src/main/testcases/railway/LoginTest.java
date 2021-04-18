package railway;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeDriver;

import common.Utilities;
import constant.Constant;
import org.testng.annotations.Test;

public class LoginTest {
    JavascriptExecutor js;

    @BeforeMethod
    public void beforeMethod() {

        System.out.println("Pre-condition");
        System.setProperty("webdriver.chrome.driver", Utilities.getProjectPath() + "\\src\\main\\executables\\chromedriver.exe");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
        js = (JavascriptExecutor) Constant.WEBDRIVER;
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username and password");

        HomePage homePage = new HomePage();
        homePage.open();


        LoginPage loginPage = homePage.gotoLoginPage();


        js.executeScript("window.scrollBy(0,250)", "");

        String actualMsg = loginPage.login(Constant.username, Constant.password).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.username;

        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("TC02 - User can not log into Railway with invalid username and password");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        js.executeScript("window.scrollBy(0,250)","");

        String errorLoginMsg = loginPage.getLblLoginErrorMsg().getText();

        String actualMsg = loginPage.login(Constant.failUsernameLogin, Constant.failPasswordLogin).getWelcomeMessage();
    }
}
